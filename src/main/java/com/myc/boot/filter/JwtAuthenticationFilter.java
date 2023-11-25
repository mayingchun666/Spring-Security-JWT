package com.myc.boot.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myc.boot.domain.LoginUserDetails;
import com.myc.boot.utils.JwtUtil;
import com.myc.boot.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 请求头中获取token
        String token = request.getHeader("Authorization");
        if (StrUtil.isEmpty(token)) {
            // 没有token 直接放行 后续会被SecurityContext拦截
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token 获取用户信息
        String userId;
        try {
            userId = JwtUtil.parseJWT(token).getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token无效");
        }

        LoginUserDetails loginUserDetails = redisCache.getCacheObject("token_" + userId, LoginUserDetails.class);

        if (loginUserDetails == null) {
            throw new RuntimeException("token无效");
        }

        // 将用户信息放入SecurityContext中
        // todo 权限
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
