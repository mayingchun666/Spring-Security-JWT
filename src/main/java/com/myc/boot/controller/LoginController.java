package com.myc.boot.controller;

import com.myc.boot.domain.User;
import com.myc.boot.service.LoginService;
import com.myc.boot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
