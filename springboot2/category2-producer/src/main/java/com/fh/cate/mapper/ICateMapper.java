package com.fh.cate.mapper;

import com.fh.cate.po.Category;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ICateMapper {

    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "fatherId",property = "fatherId"),
            @Result(column = "categoryName",property = "categoryName")
    })
    @Select("select id,fatherId,categoryName from t_category where fatherId = #{value}")
    List<Category> queryCateList(Integer id);
}
