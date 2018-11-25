package com.fh.product.controller;

import com.fh.CheckSum;
import com.fh.HttpclientPost;
import com.fh.ServiceResponse;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@PropertySource("classpath:path.properties")
public class ProductController {

    @Value("${find-cate-url}")
    private String cateUrl;
    @Value("${find-brand-url}")
    private String brandUrl;
    @Value("${add-product-url}")
    private String productUrl;
    @Value("${find-productlist-url}")
    private String productListUrl;

    @GetMapping("/category/querycategoryListById")
    public ServiceResponse querycategoryListById(Integer id){
        String result = HttpclientPost.httpGet(cateUrl+ id, null, null,true);
        Gson gson = new Gson();
        ServiceResponse serviceResponse = gson.fromJson(result, ServiceResponse.class);
        return serviceResponse;
    }

    @GetMapping("/goods/queryBrands")
    public ServiceResponse queryBrands(){
        String result = HttpclientPost.httpGet(brandUrl, null, null,true);
        Gson gson = new Gson();
        ServiceResponse serviceResponse = gson.fromJson(result, ServiceResponse.class);
        return serviceResponse;



    }

    @PostMapping("/goods/addGoods")
    public ServiceResponse addGoods(@RequestParam Map product){
        String result = HttpclientPost.httpPost(productUrl, product, null,true);
        Gson gson = new Gson();
        ServiceResponse serviceResponse = gson.fromJson(result, ServiceResponse.class);
        return serviceResponse;
    }

    @GetMapping("/goodsList")
    public ServiceResponse queryList(){


        String result = HttpclientPost.httpGet(productListUrl, null, null,true);
        Gson gson = new Gson();
        ServiceResponse serviceResponse = gson.fromJson(result, ServiceResponse.class);
        return serviceResponse;
    }


}
