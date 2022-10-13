package com.liyingjun.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url ;
    //读取配置文件中信息
    private ResourceBundle bundle;
    //  存储cookie信息变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        // 读取application文件中的url
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result ;
        //从配置文件中拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testurl = this.url+uri;

        //获取cookie信息
        this.store = new BasicCookieStore();
        CloseableHttpClient httpClient =HttpClients.custom().setDefaultCookieStore(store).build();

        //获取对象执行http访问
        HttpGet get = new HttpGet(testurl);

        //创建客户端连接对象
        CloseableHttpResponse response =httpClient.execute(get);
        // 打印返回值
        result= EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

        // 读取cookie信息
        List<Cookie> cookies = store.getCookies();
        for (Cookie cookie:cookies){
            String name =cookie.getName();
            String value =cookie.getValue();
            System.out.println("cookie name="+name+ "cookie value ="+value);
        }

    }

    //依赖testGetCookies
    @Test(dependsOnMethods = {"testGetCookies"})
    public void  testGetWithCookies() throws IOException {
        String uri =bundle.getString("test.get.with.cookies");
        String testUrl = this.url+uri;

        CloseableHttpClient httpClient =HttpClients.custom().setDefaultCookieStore(store).build();
        HttpGet get = new HttpGet(testUrl);

        CloseableHttpResponse response =httpClient.execute(get);
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+statusCode);
        if (statusCode == 200){
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(result);
        }else{
            System.out.println("访问/get/with/cookies失败");
        }
    }

}
