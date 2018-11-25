package com.fh.product.product2producer.controller;

import com.fh.ServiceResponse;
import com.fh.product.product2producer.biz.IProductService;
import com.fh.product.product2producer.po.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProductController{

    @Resource(name="productService")
    private IProductService productService;

    @PostMapping("/product/add")
    public ServiceResponse addProduct(Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/product/list")
    public ServiceResponse productList(){
        return productService.queryProductList();
    }


}
