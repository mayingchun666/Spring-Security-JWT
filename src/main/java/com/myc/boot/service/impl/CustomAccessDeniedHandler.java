package com.myc.boot.service.impl;

import com.alibaba.fastjson.JSON;
import com.myc.boot.utils.ResponseResult;
import com.myc.boot.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), accessDeniedException.getMessage(), null);
        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(response, jsonString);
    }
}
