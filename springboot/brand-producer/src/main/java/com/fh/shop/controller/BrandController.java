package com.fh.shop.controller;

import com.fh.ServiceResponse;
import com.fh.shop.api.BrandApi;
import com.fh.shop.biz.IBrandService;
import com.fh.shop.model.Brand;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BrandController implements BrandApi {

    @Resource(name="brandService")
    private IBrandService brandService;


    @Override
    public ServiceResponse<List<Brand>> queryBrandList() {
        ServiceResponse<List<Brand>> response = brandService.queryBrandList();
        return response;
    }

    @Override
    public ServiceResponse deleteBrand(Integer id) {
        return brandService.deleteBrand(id);
    }

    @Override
    public ServiceResponse updateBrand(Brand brand) {
        return brandService.updateBrand(brand);
    }

    @Override
    public ServiceResponse addBrand(Brand brand) {
        return brandService.addBrand(brand);
    }

    @Override
    public ServiceResponse<Brand> queryBrand(Integer id) {
        ServiceResponse<Brand> response = brandService.queryBrand(id);
        return response;
    }

    @Override
    public ServiceResponse updateBrandCount(Integer brandId) {
        return brandService.updateBrandCount(brandId);
    }
}
