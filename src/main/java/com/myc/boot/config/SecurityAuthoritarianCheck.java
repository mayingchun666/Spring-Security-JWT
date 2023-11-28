package com.myc.boot.config;


import com.myc.boot.domain.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ss")
public class SecurityAuthoritarianCheck {

    public boolean hasAuthority(String authority) {
        // 获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取当前登录用户的权限信息
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        // 权限列表
        List<String> permissions = loginUserDetails.getPermissions();

        return permissions.contains(authority);
    }
}
