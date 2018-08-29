package com.yjq.shares.util;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class HttpClientPartner {
    private CookieStore cookieStore = new BasicCookieStore();

    private String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";

    private String ip;
    private int port;
    private String type;
    private CloseableHttpClient client;

    public HttpClientPartner() {
        init();
    }

    public HttpClientPartner(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.type = "http";
        init();
    }

    public HttpClientPartner(String ip, int port, String type) {
        this.ip = ip;
        this.port = port;
        this.type = type;
        init();
    }

    public void init() {
        if (ip != null && !"".equals(ip) && port != 0) {
            //设置代理IP、端口、协议（请分别替换）
            HttpHost proxy = null;
            if (type != null && !"".equals(type)) {
                proxy = new HttpHost(ip, port, type);
            } else {
                proxy = new HttpHost(ip, port, "http");
            }
            //把代理设置到请求配置
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).setDefaultCookieStore(cookieStore).build();

        } else {
            client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        }
    }

    public String post(String url, List<BasicNameValuePair> list, String code) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 配置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000)
                .setCookieSpec(CookieSpecs.STANDARD)
                .setRedirectsEnabled(false)
                .build();
        httpPost.setConfig(requestConfig);

        // 设置post求情参数
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, code);
        httpPost.setEntity(entity);

        httpPost.setHeader("User-Agent",USER_AGENT);

        //发送请求
        HttpResponse httpResponse = client.execute(httpPost);

        String strResult = null;
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            strResult = EntityUtils.toString(httpResponse.getEntity());
            return strResult;
        } else {
            strResult = "Error Response: " + httpResponse.getStatusLine().toString();
        }
        return strResult;
    }


    public String get(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        // 配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setCookieSpec(CookieSpecs.STANDARD)
                .setRedirectsEnabled(false)// 默认允许自动重定向
                .build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent",USER_AGENT);
        //发送请求
        HttpResponse httpResponse = client.execute(httpGet);

        //判断是否请求成功 请求成功返回数据 否则返回Null 或报错
        String srtResult = null;
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            srtResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
        } else {
            srtResult = "Error Response: " + httpResponse.getStatusLine().toString();
        }
        return srtResult;
    }

    //设置Cookie
    public void setCookieStore(List<BasicClientCookie> cookielist) {
        for (BasicClientCookie cookie : cookielist) {
            cookieStore.addCookie(cookie);
        }
    }
    //中午转Unicode编码
    public String gbEncoding(String gbString) {
        try {
            gbString = URLEncoder.encode(gbString,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return gbString;
    }


    //关闭
    public void close(){
        if(client!=null){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
