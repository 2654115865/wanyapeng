package com.fh.shop.api;

import com.fh.ServiceResponse;
import com.fh.shop.model.Brand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BrandApi {

    @GetMapping("/brand/list")
    public ServiceResponse<List<Brand>> queryBrandList();

    @DeleteMapping("/brand/{id}")
    public ServiceResponse deleteBrand(@RequestParam("id") Integer id);

    @PutMapping("/brand/update")
    public ServiceResponse updateBrand(@RequestBody Brand brand);

    @PostMapping("/brand/add")
    public ServiceResponse addBrand(Brand brand);

    @GetMapping("/brand")
    public ServiceResponse<Brand> queryBrand(@RequestParam("id") Integer id);
    @GetMapping("/brand/update")
    ServiceResponse updateBrandCount(@RequestParam("brandId") Integer brandId);
}
