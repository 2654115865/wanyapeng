package com.fh.shop.mapper;

import com.fh.shop.model.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IBrandMapper {

    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "brandName",property = "brandName"),
            @Result(column = "logo",property = "logo"),
            @Result(column = "description",property = "description")
    })
    @Select("select id,brandName,logo,description from t_brand")
    List<Brand> queryBrandList();


    @Delete("delete from t_brand where id = #{value}")
    void deleteBrand(Integer id);

    @Update("update t_brand set brandName=#{brandName},logo=#{logo},description=#{description} where id = #{id}")
    void updateBrand(Brand brand);

    @Insert("insert into t_brand (brandName,logo,description) values(#{brandName},#{logo},#{description})")
    void addBrand(Brand brand);

    @Results({
            @Result(column = "brandName",property = "brandName")
    })
    @Select("select brandName from t_brand where id = #{value}")
    Brand queryBrand(Integer id);

    @Update("update t_brand set brandCount=brandCount+1 where id = #{value}")
    void updateBrandCount(Integer brandId);
}
