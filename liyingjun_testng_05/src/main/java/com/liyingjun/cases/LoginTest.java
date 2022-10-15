package com.liyingjun.cases;

import com.alibaba.fastjson.JSONObject;
import com.liyingjun.config.TestConfig;
import com.liyingjun.model.InterfaceName;
import com.liyingjun.model.LoginCase;
import com.liyingjun.utils.ConfigFile;
import com.liyingjun.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTest {
    private CookieStore store;

    @BeforeTest(groups = "loginTrue",description = "测试准备工作，获取httpClient对象")
    public void beforeTest(){
        //获取url信息
        TestConfig.getUserInfoUrl = ConfigFile.geyUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.geyUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFile.geyUrl(InterfaceName.ADDUSER);
        TestConfig.updateUserInfoUrl = ConfigFile.geyUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.loginUrl = ConfigFile.geyUrl(InterfaceName.LOGIN);

        TestConfig.closeableHttpClient = HttpClients.custom().build();
    }

    @Test(groups = "loginTrue",description = "登陆成功接口")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getsqlSession();
        LoginCase loginCase =session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //下边代码为写完接口的测试代码
        String result =getResult(loginCase);
        //处理结果判断返回值是否符合预期

    }

    @Test(description = "用户登陆失败接口")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getsqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);



        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);

    }

    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");

        //将参数信息添加到方法中
        StringEntity entity =new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //声明一个对象来进行相应结果的储存
        String result;

        //执行post方法
        HttpResponse response =TestConfig.closeableHttpClient.execute(post);

        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        this.store= new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(store).build();

        return  result;
    }

}
