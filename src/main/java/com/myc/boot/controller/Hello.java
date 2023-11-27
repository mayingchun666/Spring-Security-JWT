package com.myc.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('sys:dept:list')")
    public String hello() {
        return "hello";
    }
}
