package com.myc.boot;

import com.myc.boot.domain.User;
import com.myc.boot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Boot2ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test() {

        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
