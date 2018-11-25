package com.fh;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpclientPost {


    public static void main(String[] args) {
  /*      Map map = new HashMap();
        map.put("userName","zhangLQ");
        map.put("age","20");
        map.put("sex","1");
        httpGet("http://www.baidu.com",map,null);*/
    }


    public static String httpPut(String url,Map<String,String> params,Map<String,String> headers,boolean isValidate){
        //打开浏览器
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut(url);
        if(params != null && !params.isEmpty()){
            Gson gson = new Gson();
            String paramJson = gson.toJson(params);
            StringEntity stringEntity = new StringEntity(paramJson,"utf-8");
            stringEntity.setContentType("application/json");
            httpPut.setEntity(stringEntity);
        }

        buildHeader(headers,httpPut,isValidate);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpPut != null){
                httpPut.releaseConnection();
            }
            if(httpClient != null){
                try {
                    httpClient.close();
                    httpClient = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }
    public static  void buildHeader(Map<String,String> headers, HttpUriRequest request,boolean isValidate){
        if(headers != null && !headers.isEmpty()){
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                request.addHeader(next.getKey(),next.getValue());
            }
        }
        if (isValidate){
            request.addHeader("appKey","d6de7b87-994f-4bda-a439-fc718539d3e5");
            String nonce = UUID.randomUUID().toString().replace("-","0").toLowerCase()+RandomStringUtils.randomNumeric(6)+System.currentTimeMillis();
            request.addHeader("nonce",nonce);
            String time = new Date().getTime()+"";
            request.addHeader("time",time);
            String sign = CheckSum.getCheckSum("87340b0d-c72e-4b25-beef-0a36be0dbfe8",nonce,time);
            request.addHeader("sign",sign);
        }
    }
    public static String httpGet(String url,Map<String,String> params,Map<String,String> headers,boolean isValidate){
        //打开浏览器
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if(params != null && !params.isEmpty()){
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                nameValuePairs.add(new BasicNameValuePair(key,value));
            }
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
                String entity = EntityUtils.toString(formEntity, "utf-8");
                url+="?"+entity;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HttpGet httpGet = new HttpGet(url);
        buildHeader(headers,httpGet,isValidate);
        String result = "";
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpGet != null){
                httpGet.releaseConnection();
            }
            if(httpClient != null){
                try {
                    httpClient.close();
                    httpClient = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;
    }


    public static String httpPost(String url,Map<String,String> params,Map<String,String> headers,boolean isValidate){

        //打开浏览器
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        CloseableHttpResponse response=null;
        String result ="";
        if (null!=params && params.size()>0){
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                pairs.add(new BasicNameValuePair(key,value));
            }
        }
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
            buildHeader(headers,httpPost,isValidate);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null!=response){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=httpPost){
                httpPost.releaseConnection();
            }
            if(null != httpClient){
                try {
                    httpClient.close();
                    httpClient=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return result;
    }
}
