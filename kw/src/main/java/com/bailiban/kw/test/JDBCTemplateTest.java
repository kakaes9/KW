package com.bailiban.kw.test;

import com.bailiban.kw.domain.Item;
import com.bailiban.kw.domain.ItemCat;
import com.bailiban.kw.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JDBCTemplateTest {
    //JDBCTemplate 测试 添加
    @Test
    public void test1() {
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        //写sql语句
        String sql="INSERT INTO item_cat VALUES (NULL,?,?)";
        //执行跟新（添加 删除 修改）
       template.update(sql,"奶茶",1);
       /* Object[] params = {
                "饮料",
                1
        };
        template.updata(sql,params);
    */
    }
    //查询所有分类
    @Test
    public void test2() {
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //写sql语句
        String sql="SELECT*FROM item_cat";
        //执行查询多头数据BeanPropertyRowMapper映射对象
        List<ItemCat> itemCats = template.query(sql, new BeanPropertyRowMapper<ItemCat>(ItemCat.class));
        //打印集合
        System.out.println(itemCats);
    }
    //根据id查询一个
    @Test
    public void test3() {
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //写sql语句
        String sql="SELECT*FROM item_cat where cid=? ";
        //查询一个 如果查不到 抛出EmptyResuDataAccesException
        ItemCat itemCat = null;
        try {
            itemCat = template.queryForObject(sql, new BeanPropertyRowMapper<>(ItemCat.class), 2);
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        System.out.println(itemCat);
    }
    //统计查询
    @Test
    public void test4() {
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //写sql语句
        String sql="select count(*) from item_cat";
        //查询
        Integer count = template.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

    //模糊查询
    @Test
    public void test5(){
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //写sql语句
        String sql="select * from item where name like ? ";
        String keyword ="手办";

        //查询
        List<Item> items = template.query(sql, new BeanPropertyRowMapper<>(Item.class), "%" + keyword + "%");

        System.out.println(items);
    }

    //根据订单标号添加收货信息
    @Test
    public  void test6(){
        //创建JDBCTemplate对象
        JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
        //写sql语句
        String sql ="UPDATE orders SET address=? ,name=?,telephone=? WHERE oid=?";
        //UPDATE orders SET address='武软' ,name='王大炮',telephone='12306' WHERE oid='a0560706c9584e90a6ae78c87d471e12';
        try {
            template.update(sql,"武软","王大炮","13545123035","a0560706c9584e90a6ae78c87d471e12");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

}
