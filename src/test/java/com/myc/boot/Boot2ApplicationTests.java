package com.myc.boot;

import com.myc.boot.domain.User;
import com.myc.boot.mapper.UserMapper;
import com.myc.boot.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class Boot2ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Test
    void test3() throws Exception {
        String token = JwtUtil.createJWT("123");
        String jwtStr = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiYThmZmYwNGRmMTA0MGU4YmFmZWU5MmJkZWMwZGE2YyIsInN1YiI6IjEyMyIsImlzcyI6InNnIiwiaWF0IjoxNzAwNzQ2NzM4LCJleHAiOjE3MDA3NTAzMzh9.uiFXFNX5H46MpE2t_IBabF-k6zJ5qCGVUSHSe4HHJRA";
        System.out.println(token);
        Claims claims = JwtUtil.parseJWT(jwtStr);
        System.out.println(claims.getSubject());
    }

    @Test
    void test2() {

        String encode = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(encode2);
        boolean matches = passwordEncoder.matches("123456", encode);
        System.out.println(matches);
    }

    @Test
    void test() {

        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
