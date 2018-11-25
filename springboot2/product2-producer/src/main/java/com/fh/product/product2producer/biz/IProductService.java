package com.fh.product.product2producer.biz;

import com.fh.ServiceResponse;
import com.fh.product.product2producer.po.Product;

public interface IProductService {
    ServiceResponse addProduct(Product product);

    ServiceResponse queryProductList();
}
