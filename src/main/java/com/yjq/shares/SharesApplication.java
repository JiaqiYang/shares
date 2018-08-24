package com.yjq.shares;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yjq.shares.mapper")
@SpringBootApplication
public class SharesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharesApplication.class, args);
    }
}
