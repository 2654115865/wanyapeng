package com.fh.shop.app;


import com.fh.JedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securtyService")
public class SecurtyServiceImpl implements ISecurtyService {

    @Autowired
    private ISecurtyMapper securtyMapper;

    public String queryAppSecret(String appKey) {
        if(StringUtils.isEmpty(appKey)){
            return "";
        }
        String result = JedisUtil.get(appKey);
        if(!StringUtils.isEmpty(result)){
            return result;
        }
        String appSecret = securtyMapper.queryAppSecret(appKey);
        if(StringUtils.isEmpty(appSecret)){
            return "";
        }
        JedisUtil.set(appKey,appSecret);
        return appSecret;
    }
}
