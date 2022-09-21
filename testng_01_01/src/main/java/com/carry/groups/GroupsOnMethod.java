package com.carry.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod  {

    @Test(groups = "server")
    public void  test1(){
        System.out.println("测试方法1");
    }

    @Test(groups = "server")
    public void  test2(){
        System.out.println("测试方法2");
    }

    @Test(groups = "client")
    public void  test3(){
        System.out.println("测试方法3");
    }

    @Test(groups = "client")
    public void  test4(){
        System.out.println("测试方法4");
    }

    @Test
    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.println("服务端组运行前方法");
    }

    @Test
    @AfterGroups("server")
     public void afterGroupsOnServer(){
        System.out.println("服务端组运行后方法");
    }
}
