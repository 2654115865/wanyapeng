package com.fh.product.product2producer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.fh.product.product2producer.mapper")
@EnableDiscoveryClient
@EnableFeignClients("com.fh.shop.api")
public class Product2ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Product2ProducerApplication.class, args);
    }
}
