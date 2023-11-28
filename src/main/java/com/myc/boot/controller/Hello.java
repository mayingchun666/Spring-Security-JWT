package com.myc.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello")
    // spel表达式，@ss是自定义的注解，@ss是@PreAuthorize的别名
    @PreAuthorize("@ss.hasAuthority('sys:dept:list')")
    public String hello() {
        return "hello";
    }
}
