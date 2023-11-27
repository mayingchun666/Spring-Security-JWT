package com.myc.boot.service;

import com.myc.boot.domain.User;
import com.myc.boot.utils.ResponseResult;

public interface LoginService {

    public ResponseResult login(User user);

    ResponseResult logout();
}
