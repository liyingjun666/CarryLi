package com.liyingjun.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

//数据库工具类
public class DatabaseUtil {
    public static SqlSession getsqlSession() throws IOException {

        //声明对象获取配置的资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");

        //使用类加载器加载配置文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

        // 声明sqlSession对象 执行配置文件中的sql语句
        SqlSession sqlSession = factory.openSession();

        return sqlSession;
    }
}
