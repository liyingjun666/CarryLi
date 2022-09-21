package com.carry.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite运行ing.....");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite运行ing.....");
    }

    @BeforeTest
    void beforeTest(){
        System.out.println("beforeTest");
    }

    @AfterTest
    void afterTest(){
        System.out.println("afterTest");
    }
}
