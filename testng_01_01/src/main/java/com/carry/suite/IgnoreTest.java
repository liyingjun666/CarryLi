package com.carry.suite;

import org.testng.annotations.Test;

// 忽略测试
public class IgnoreTest {

    @Test
    void ignore1(){
        System.out.println("ignore1  runnind...");
    }

    @Test(enabled = false)
    void ignore2(){
        System.out.println("ignore2  runnind...");
    }
    @Test
    void ignore3(){
        System.out.println("ignore3  runnind...");
    }
}
