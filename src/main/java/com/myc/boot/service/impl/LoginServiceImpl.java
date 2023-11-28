package com.myc.boot.service.impl;

import com.myc.boot.domain.LoginUserDetails;
import com.myc.boot.domain.User;
import com.myc.boot.service.LoginService;
import com.myc.boot.utils.JwtUtil;
import com.myc.boot.utils.RedisCache;
import com.myc.boot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        // 登录 默认会调用UserDetailServiceImpl.loadUserByUsername
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 获取用户信息
        LoginUserDetails principal = (LoginUserDetails) authentication.getPrincipal();

        String id = principal.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);

        // 存reids
        redisCache.setCacheObject("token_" + id, principal);

        // 返回token
        HashMap map = new HashMap();
        map.put("token", jwt);
        return new ResponseResult(200, "登录成功", map);
    }


    @Override
    public ResponseResult logout() {
        // SecurityContextHolder中获取用户信息
        LoginUserDetails loginUserDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String useId = loginUserDetails.getUser().getId().toString();
        // redis删除用户信息
        redisCache.deleteObject("token_" + useId);

        return new ResponseResult(200, "退出成功");
    }
}
