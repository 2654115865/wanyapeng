package com.fh.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@MapperScan("com.fh.shop.app")
public class ApiGetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGetewayApplication.class, args);
    }
}
