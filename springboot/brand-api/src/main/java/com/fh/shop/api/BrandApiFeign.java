package com.fh.shop.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="shop-brand-producer")
public interface BrandApiFeign extends BrandApi {
}
