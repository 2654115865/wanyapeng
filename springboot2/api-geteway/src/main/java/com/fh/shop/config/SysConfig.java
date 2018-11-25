package com.fh.shop.config;

import com.fh.shop.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysConfig {

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}
