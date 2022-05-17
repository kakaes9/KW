package com.bailiban.kw.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.bailiban.kw.utils.JDBCUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DruidTest {
    //测试德鲁伊链接池的使用
    @Test
    public void test1() throws SQLException {
        //创建一个链接池对象
        DruidDataSource dataSource =new DruidDataSource();
        //将四要素从properties文件中读取出来
        //根据properties文件名创建一个资源读取对象 不要后缀。properties
        ResourceBundle rb = ResourceBundle.getBundle("druid");
        String driverClassName = rb.getString("driverClassName");
        String url = rb.getString("url");
        String username = rb.getString("username");
        String password = rb.getString("password");
        System.out.println(driverClassName);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);

        //给资源设置四要素
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //从链接池中获取链接对象
        System.out.println(dataSource.getConnection());
    }

    //测试工具类的使用
    @Test
    public void test2() throws SQLException {
        //测试工具类获取数据源
        DataSource dataSource = JDBCUtils.getDataSource();
        DataSource dataSource1 = JDBCUtils.getDataSource();
        DataSource dataSource2 = JDBCUtils.getDataSource();
        DataSource dataSource3 = JDBCUtils.getDataSource();

        System.out.println(dataSource.getConnection());
    }
}
