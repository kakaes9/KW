package com.bailiban.kw.utils;
/*
工具类快速得到数据源
 */

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class JDBCUtils {
    //定义数据源 静态成员变量
    private static DruidDataSource dataSource;
    //一个web应用只需要一个数据源 只需要创建一次 使用静态代码块定义
    //静态代码块又且仅执行一次
    static {
        //创建一个链接池对象
        dataSource =new DruidDataSource();
        //将四要素从properties文件中读取出来
        //根据properties文件名创建一个资源读取对象 不要后缀。properties
        ResourceBundle rb = ResourceBundle.getBundle("druid8");
        //获取四要素
        String driverClassName = rb.getString("driverClassName");
        String url = rb.getString("url");
        String username = rb.getString("username");
        String password = rb.getString("password");
        //给资源设置四要素
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        System.out.println("静态代码块创建了数据源");
    }

    //定义一个静态方法获取数据源
    public static DataSource getDataSource(){
        return dataSource;
    }

}
