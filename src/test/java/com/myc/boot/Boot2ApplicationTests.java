package com.myc.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boot2ApplicationTests {

    @Test
    void contextLoads() {

       String s = "123456";
        System.out.println(s.substring(0, 3));
    }

}
