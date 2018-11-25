package com.fh.cate.biz;

import com.fh.ServiceResponse;
import com.fh.cate.mapper.ICateMapper;
import com.fh.cate.po.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cateService")
@Transactional(rollbackFor = Exception.class)
public class CateServiceImpl implements ICateService {
    @Autowired
    private ICateMapper cateMapper;

    @Override
    @Transactional(readOnly = true)
    public ServiceResponse queryCateList(Integer id) {
        List<Category> categoryList = cateMapper.queryCateList(id);
        return ServiceResponse.success(categoryList);
    }
}
