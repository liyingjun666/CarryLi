package com.carry.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
     public void testDataProvider(String name ,int age ){
        System.out.println("name="+ name+ "   :age= "+age);
     }

     @DataProvider(name = "data")
     public Object[][] provideData(){
        Object[][] o = new Object[][]{
                {"李",10},
                {"李1",120},
                {"111",12}
        } ;
        return o ;
    }

    @Test(dataProvider = "methodData")
    public  void  test1 (String name ,int age){
        System.out.println("test111111方法  name ="+name+";age="+age);
    }

    @Test(dataProvider = "methodData")
    public  void  test2 (String name ,int age){
        System.out.println("test22222方法  name ="+name+";age="+age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){
        Object [][] result = null ;
        if (method.getName().equals("test1")){
            result= new Object[][]{
                    {"测试1",20},
                    {"测试2",30},
                    {"测试3",40}
            };
        }else if (method.getName().equals("test2")){
            result =new Object[][]{
                    {"测试4",40},
                    {"测试5",50},
                    {"测试6",60}
            };
        }
        return result;
    }
}
