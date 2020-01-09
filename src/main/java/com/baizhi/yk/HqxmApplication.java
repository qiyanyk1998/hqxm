package com.baizhi.yk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.baizhi.yk.dao")
public class HqxmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HqxmApplication.class, args);
    }

}
