package com.liyingjun.utils;

import com.liyingjun.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

// 获取url工具类
public class ConfigFile {
    //声明对象bundle 获取application.properties文件信息
    private static ResourceBundle bundle =ResourceBundle.getBundle("application", Locale.CHINA);

    //拼接url
    public static String geyUrl(InterfaceName name){

        //获取.properties文件中的url信息
        String address = bundle.getString("test.url");
        String uri = "";

        //最终测试url
        String testurl ;

        //判断name值是否与配置文件信息保持一致
        if (name == InterfaceName.GETUSERLIST){
            uri= bundle.getString("getUserList.uri");
        }
        if (name == InterfaceName.LOGIN ){
            uri= bundle.getString("login.uri");
        }
        if (name == InterfaceName.ADDUSER ){
            uri= bundle.getString("addUser.uri");
        }
        if (name == InterfaceName.GETUSERINFO ){
            uri= bundle.getString("getUserInfo.uri");
        }
        if (name == InterfaceName.UPDATEUSERINFO ){
            uri= bundle.getString("updateUserInfo.uri");
        }

        //拼接url+接口名称
        testurl= address+uri;
        return testurl;
    }
}
