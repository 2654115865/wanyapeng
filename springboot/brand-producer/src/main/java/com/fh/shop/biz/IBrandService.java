package com.fh.shop.biz;

import com.fh.ServiceResponse;
import com.fh.shop.model.Brand;


import java.util.List;

public interface IBrandService {


    public ServiceResponse<List<Brand>> queryBrandList();

    ServiceResponse deleteBrand(Integer id);

    ServiceResponse updateBrand(Brand brand);

    ServiceResponse addBrand(Brand brand);

    ServiceResponse<Brand> queryBrand(Integer id);

    ServiceResponse updateBrandCount(Integer brandId);
}
