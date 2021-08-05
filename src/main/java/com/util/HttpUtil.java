package com.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 参数放到url里
     * @param url
     * @param header
     * @return
     */
    public static String doGet(String url,Map<String,String> header){
        //1、创建 CloseableHttpClient 同步请求对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2、HttpGet(final String uri)：创建 http get 请求对象
        HttpGet httpGet = new HttpGet(url);

        // 3、设置超时时间、请求时间、socket 时间都为 15 秒，允许重定向
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000)
                        .setConnectionRequestTimeout(15000)
                        .setSocketTimeout(15000)
                        .setRedirectsEnabled(true)
                        .build();
        httpGet.setConfig(requestConfig);
        //设置请求头
        if(!header.isEmpty()){
            Set<Map.Entry<String, String>> entrys =header.entrySet();
            for(Map.Entry<String, String> e:entrys){
                httpGet.setHeader(e.getKey(),e.getValue());
            }
        }
        // 4、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
        // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
        String result ="";
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            // 5、获取响应结果, 状态码 200 表示请求成功
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            System.out.println("响应状态码：" + statusCode);
            if (statusCode == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                // 使用指定的字符编码解析响应消息实体
                result = EntityUtils.toString(httpEntity,"UTF-8");
                System.out.println("result：" + result);
            }else {
                logger.error("未正常响应statusCode="+statusCode);
            }
        }catch (Exception e){
            logger.error("doGet访问失败||url="+url,e);
        }
        return result;
    }

    public static String doPost(String url,Map<String,String> header,String postParams){
        //1、创建 CloseableHttpClient 同步请求对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2、HttpPost(final String uri)：创建 http post 请求对象, 设置请求头信息
        HttpPost httpPost = new HttpPost(url);

        //设置请求头
        if(!header.isEmpty()){
            Set<Map.Entry<String, String>> entrys =header.entrySet();
            for(Map.Entry<String, String> e:entrys){
                httpPost.setHeader(e.getKey(),e.getValue());
            }
        }else {
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        }

        // 3、设置 post 请求正文参数
        httpPost.setEntity(new StringEntity(postParams, "UTF-8"));

        // 4、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
        // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
        String result="";
        try{
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 5、获取响应结果, 状态码 200 表示请求成功
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("响应状态码：" + statusCode);
            if (statusCode == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                // 使用指定的字符编码解析响应消息实体
                result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("result：" + result);
            }else {
                logger.error("未正常响应statusCode="+statusCode);
            }
        }catch (Exception e){
            logger.error("doPost访问失败||url="+url+"||header"+header.toString()+"||postParams="+postParams,e);
        }
        return result;
    }

    public static String doPut(String url,Map<String,String> header,String putParams){
        //1、创建 CloseableHttpClient 同步请求对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2、HttpPut(final String uri)：创建 http put 请求对象, 设置请求头信息
        HttpPut httpPut=new HttpPut(url);

        //设置请求头
        if(!header.isEmpty()){
            Set<Map.Entry<String, String>> entrys =header.entrySet();
            for(Map.Entry<String, String> e:entrys){
                httpPut.setHeader(e.getKey(),e.getValue());
            }
        }else {
            httpPut.setHeader("Content-Type", "application/json;charset=utf8");
        }

        // 3、设置 put 请求正文参数
        httpPut.setEntity(new StringEntity(putParams, "UTF-8"));



        // 4、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
        // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
        String result="";
        try{System.out.println(EntityUtils.toString(httpPut.getEntity()));
            HttpResponse httpResponse = httpClient.execute(httpPut);
            // 5、获取响应结果, 状态码 200 表示请求成功
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("响应状态码：" + statusCode);
            if (statusCode == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                // 使用指定的字符编码解析响应消息实体
                result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("result：" + result);
            }else {
                logger.error("未正常响应statusCode="+statusCode);
            }
        }catch (Exception e){
            logger.error("doPut访问失败||url="+url+"||header="+header.toString()+"||putParams="+putParams,e);
        }
        return result;
    }

    /**
     * 参数放在url里
     * @param url
     * @param header
     * @return
     */
    public static String doDel(String url,Map<String,String> header){
        //1、创建 CloseableHttpClient 同步请求对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2、HttpDelete(final String uri)：创建 http del 请求对象, 设置请求头信息
        HttpDelete httpDelete=new HttpDelete(url);

        //设置请求头
        if(!header.isEmpty()){
            Set<Map.Entry<String, String>> entrys =header.entrySet();
            for(Map.Entry<String, String> e:entrys){
                httpDelete.setHeader(e.getKey(),e.getValue());
            }
        }

        // 4、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
        // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
        String result="";
        try{
            HttpResponse httpResponse = httpClient.execute(httpDelete);
            // 5、获取响应结果, 状态码 200 表示请求成功
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("响应状态码：" + statusCode);
            if (statusCode == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                // 使用指定的字符编码解析响应消息实体
                result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("result：" + result);
            }else {
                logger.error("未正常响应statusCode="+statusCode);
            }
        }catch (Exception e){
            logger.error("doDel访问失败||url="+url+"||header="+header.toString(),e);
        }
        return result;
    }

    public static void main(String[] args) {
        String param="{\"data\":{\"hello\":\"hello\"}}";
        param= JSONObject.toJSONString(param);


//        String get=doGet("http://localhost:8080/demo/helloGet?param="+1,new HashMap<>());
//        JSONObject jsonObject=new JSONObject(JSONObject.parseObject(get,Map.class));
//        JSONObject dataget=jsonObject.getJSONObject("data");
//        System.out.println(dataget.toJSONString());


//        String del=doDel("http://localhost:8080/demo/helloDel?param="+1,new HashMap<>());
//        JSONObject json=new JSONObject(JSONObject.parseObject(del,Map.class));
//        JSONObject datadel=json.getJSONObject("data");
//        System.out.println(datadel.toJSONString());

        String put=doPut("http://localhost:8080/demo/helloPut",new HashMap<>(),param);
        JSONObject object=new JSONObject(JSONObject.parseObject(put,Map.class));
        JSONObject dataput=object.getJSONObject("data");
        System.out.println(dataput.toJSONString());

//        String post=doPost("http://localhost:8080/demo/helloPost",new HashMap<>(),param);
//        JSONObject j=new JSONObject(JSONObject.parseObject(post,Map.class));
//        JSONObject datapost=j.getJSONObject("data");
//        System.out.println(datapost.toJSONString());

    }
}
