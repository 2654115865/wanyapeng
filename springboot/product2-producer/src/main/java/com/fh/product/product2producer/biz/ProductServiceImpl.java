package com.fh.product.product2producer.biz;

import com.fh.ServiceResponse;
import com.fh.product.product2producer.mapper.IProductMapper;
import com.fh.product.product2producer.po.Product;
import com.fh.shop.api.BrandApiFeign;
import com.fh.shop.model.Brand;
import com.fh.shop.model.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Service("productService")
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private BrandApiFeign brandApiFeign;


    @Override
    public ServiceResponse addProduct(Product product) {
        productMapper.addProduct(product);
        Integer brandId = product.getBrandId();
        brandApiFeign.updateBrandCount(brandId);
        return ServiceResponse.success();
    }

    @Override
    public ServiceResponse queryProductList() {
        List<Product> productList = productMapper.queryProductList();
        List<ProductVo> productVoList = new ArrayList<>();
        for (Product product : productList) {
            ProductVo productVo = new ProductVo();
            productVo.setGoodsName(product.getGoodsName());
            productVo.setGoodsPrice(product.getGoodsPrice());
            ServiceResponse<Brand> response = brandApiFeign.queryBrand(product.getBrandId());
            productVo.setBrandName(response.getData().getBrandName());
            productVoList.add(productVo);
        }
        return ServiceResponse.success(productVoList);
    }
}
