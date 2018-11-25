package com.fh.shop.app;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ISecurtyMapper {

    @Results({
            @Result(column = "appSecret",property = "appSecret")
    })

    @Select("select appSecret from t_app where appKey=#{value}")
    String queryAppSecret(String appKey);
}
