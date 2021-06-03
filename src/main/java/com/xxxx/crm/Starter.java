package com.xxxx.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * todo springBoot启动类
 */
@SpringBootApplication
@MapperScan("com.xxxx.crm.dao")
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }
}
