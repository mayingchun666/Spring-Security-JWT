package com.myc.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.myc.boot.mapper")
public class Boot2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Boot2Application.class, args);
        System.out.println(run);
    }

}
