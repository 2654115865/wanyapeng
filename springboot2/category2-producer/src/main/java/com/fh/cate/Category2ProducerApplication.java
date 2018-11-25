package com.fh.cate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.fh.cate.mapper")
@EnableDiscoveryClient
public class Category2ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Category2ProducerApplication.class, args);
    }
}
