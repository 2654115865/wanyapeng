package com.fh.shop.filter;

import com.fh.CheckSum;
import com.fh.JedisUtil;
import com.fh.ServiceResponse;
import com.fh.shop.app.ISecurtyService;
import com.fh.shop.model.TokenEnum;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends ZuulFilter {

    @Resource(name = "securtyService")
    private ISecurtyService securtyService;

    @Override
    public String filterType() {
        return "pre";//zuul中过滤器的类型 "pre","routing","post","error"
    }

    @Override
    public int filterOrder() {
        return 0;//过滤器的优先级[顺序],数字越小优先级越高
    }

    @Override
    public boolean shouldFilter() {
        return true;//代表该过滤器是否执行,true:执行,false:不执行
    }
    private static final long EXPIRE = 60*1000;
    @Override //真正的出来逻辑
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String appKey = request.getHeader("appKey");
        String time = request.getHeader("time");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        //先判断请求头部不为空
        if(StringUtils.isEmpty(appKey) || StringUtils.isEmpty(time) || StringUtils.isEmpty(sign) || StringUtils.isEmpty(nonce)){
            ServiceResponse error = ServiceResponse.error(TokenEnum.HEADER_IS_MISS);
            buildResponse(currentContext, error);
            return null;
        }
        //验证是否超时
        if(Long.parseLong(time)+EXPIRE < System.currentTimeMillis()){
            ServiceResponse error = ServiceResponse.error(TokenEnum.TOKEN_TIME_OUT);
            buildResponse(currentContext, error);
            return null;
        }
        //验证nonce
        boolean success = JedisUtil.setNxExist(nonce, "1", 60);
        if(!success){
            ServiceResponse error = ServiceResponse.error(TokenEnum.ERROR_IS_ATTACK);
            buildResponse(currentContext, error);
            return null;
        }
        //验证appKey是否不存在
        String appSecret = securtyService.queryAppSecret(appKey);
        if(StringUtils.isEmpty(appSecret)){
            ServiceResponse error = ServiceResponse.error(TokenEnum.APPKEY_IS_EMPTY);
            buildResponse(currentContext, error);
            return null;
        }
        //验证签名是否正确
        String checkSum = CheckSum.getCheckSum(appSecret, nonce, time);
        if(!checkSum.equals(sign)){
            ServiceResponse error = ServiceResponse.error(TokenEnum.SIGN_IS_ERROR);
            buildResponse(currentContext, error);
            return null;
        }
        return null;
    }

    private void buildResponse(RequestContext currentContext, ServiceResponse error) {
        Gson gson = new Gson();
        String result = gson.toJson(error);
        //设置编码格式和响应类型
        currentContext.getResponse().setContentType("application/json; charset=utf-8");
        //设置响应内容
        currentContext.setResponseBody(result);
        //禁止路由
        currentContext.setSendZuulResponse(false);
    }
}
