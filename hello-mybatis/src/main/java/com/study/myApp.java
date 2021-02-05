package com.study;

import com.study.domain.City;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class myApp {
    public static void main(String[] args) throws IOException {
        //访问mybatis读取student数据
        //1.定义mybatis主配置文件名称，从类路径开始target/clasess
        String config = "mybatis.xml";
        //2.读取config文件
        InputStream in = Resources.getResourceAsStream(config);
        //3.创建对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //4.创建对象
        SqlSessionFactory factory = builder.build(in);
        //5.获取sqlSession对象.重要
        SqlSession sqlSession = factory.openSession();
        //6.指定执行sql语句的标识（sql隐射文件中的namesapce + "." + 标签id）重要
        String sqlId = "com.study.dao.CityDao" + "." + "selectCity";
        //7执行sql语句
        List<City> cities = sqlSession.selectList(sqlId);
        //8.输出结果
        for (City city:cities
             ) {
            System.out.println(city);
        }
        //9.关闭资源
        sqlSession.close();

    }
}