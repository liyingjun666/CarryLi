package com.carry.groups;

import org.testng.annotations.Test;

@Test(groups ="teacher")

public class GroupsOnClass3 {

    public void teacher1(){
        System.out.println("GroupsOnClass3中的teacher1方法运行");
    }

    public void teacher2(){
        System.out.println("GroupsOnClass3中的teacher2方法运行");
    }
}
