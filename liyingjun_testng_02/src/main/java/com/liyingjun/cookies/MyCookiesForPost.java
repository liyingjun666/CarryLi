package com.liyingjun.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url ;
    private ResourceBundle bundle ;    //读取配置文件中信息

    private CookieStore store;    //  存储cookie信息变量

    @BeforeTest
    public void beforeTest(){
        String result ;
        // 读取application.properties 文件中的url信息
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url =bundle.getString("test.url");
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
    public void testPostMethod() throws IOException, JSONException {
        String uri =bundle.getString("test.post.with.cookies");
        // 拼接测试地址
        String testUrl = this.url+uri;

        //声明一个Clinet对象
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(store).build();

        //声明post方法
        HttpPost post = new HttpPost(testUrl);

        // 添加参数
        JSONObject param = new JSONObject();
        param.put("name","liwenshuai");
        param.put("age","18");

        //设置请求信息
        post.setHeader("contet-type","application/json");
        //将参数信息添加到方法
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明对象存储响应结果
        String result ;
        //设置cookies信息

        //执行post方法
        CloseableHttpResponse response = httpClient.execute(post);
        //获取相应结果
        result = EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

        //将返回结果转化为json对象
        JSONObject jsonObject = new JSONObject(result);
        //获取结果值
        String  success = (String) jsonObject.get("liwenshuai");
        String status = (String) jsonObject.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);
    }


}
