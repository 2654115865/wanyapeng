package com.fh.shop.biz;

import com.fh.ServiceResponse;
import com.fh.shop.mapper.IBrandMapper;
import com.fh.shop.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("brandService")
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true)
    public ServiceResponse<List<Brand>> queryBrandList() {
        List<Brand> brandList = brandMapper.queryBrandList();
        return  ServiceResponse.success(brandList);
    }

    @Override
    public ServiceResponse deleteBrand(Integer id) {
        brandMapper.deleteBrand(id);
        return ServiceResponse.success();
    }

    @Override
    public ServiceResponse updateBrand(Brand brand) {
        brandMapper.updateBrand(brand);
        return ServiceResponse.success();
    }

    @Override
    public ServiceResponse addBrand(Brand brand) {
        brandMapper.addBrand(brand);
        return ServiceResponse.success();
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResponse<Brand> queryBrand(Integer id) {
        Brand brand = brandMapper.queryBrand(id);
        return ServiceResponse.success(brand);
    }

    @Override
    public ServiceResponse updateBrandCount(Integer brandId) {
        brandMapper.updateBrandCount(brandId);
        return ServiceResponse.success();
    }
}
