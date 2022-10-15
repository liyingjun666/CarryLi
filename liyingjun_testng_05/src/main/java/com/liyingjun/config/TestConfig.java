package com.liyingjun.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


//配置信息
public class TestConfig {
     public static String loginUrl;
     public static String updateUserInfoUrl;
     public static String getUserListUrl;
     public static String getUserInfoUrl;
     public static String addUserUrl;


     public static CloseableHttpClient closeableHttpClient;
//     获取cookie信息
     public static CookieStore store;


}
