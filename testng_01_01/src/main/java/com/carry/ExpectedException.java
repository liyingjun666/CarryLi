package com.carry;

import org.testng.annotations.Test;

// 异常测试
public class ExpectedException {

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是我的异常测试");
         throw new RuntimeException();

    }
}
