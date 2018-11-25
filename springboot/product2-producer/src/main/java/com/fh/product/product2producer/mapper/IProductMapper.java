package com.fh.product.product2producer.mapper;

import com.fh.product.product2producer.po.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductMapper {

    @Insert("insert into t_goods (goodsName,goodsPhoto,goodsPrice,goodsStock,category1,category2,category3,brandId,cateInfo) values(#{goodsName},#{goodsPhoto},#{goodsPrice},#{goodsStock},#{category1},#{category2},#{category3},#{brandId},#{cateInfo})")
    void addProduct(Product product);


    @Results({
            @Result(column = "goodsName",property = "goodsName"),
            @Result(column = "goodsPrice",property = "goodsPrice"),
            @Result(column = "brandId",property = "brandId")
    })
    @Select("select goodsName,goodsPrice,brandId from t_goods")
    List<Product> queryProductList();
}
