package com.fh.cate.controller;

import com.fh.ServiceResponse;
import com.fh.cate.biz.ICateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CateController {

    @Resource(name="cateService")
    private ICateService cateService;

    @GetMapping("/cate/{id}")
    public ServiceResponse queryCateList(@PathVariable Integer id){
        return cateService.queryCateList(id);
    }
}
