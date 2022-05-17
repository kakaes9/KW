package com.bailiban.kw.dao.impl;

import com.bailiban.kw.dao.ItemDao;
import com.bailiban.kw.domain.Item;
import com.bailiban.kw.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ItemDaoImpl implements ItemDao {

    //创建JDBCTemplate
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Item> selectItemsByIflag(int iflag) {
        //sql语句
        String sql ="select * from item where iflag = ? and status =1 order by created desc LIMIT 6";
        //执行查询
        List<Item> items = template.query(sql, new BeanPropertyRowMapper<>(Item.class), iflag);
        //返回

        return items;
    }

    @Override
    public int selectCountByCid(int cid) {
        //sql语句
        String sql ="select count(*) from item where cid = ? ";

        try {
            Integer count = template.queryForObject(sql, Integer.class, cid);
            return count;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int selectCountByName(String name) {
        //sql语句
        String sql ="select count(*) from item where name like ? ";

        try {
            Integer count = template.queryForObject(sql, Integer.class, "%"+name+"%");
            return count;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Item> selectPageByCid(int cid, int start, int pageSize) {
        //sql语句
        String sql="select *from item where cid = ? limit ?,?";
        List<Item> items = template.query(sql, new BeanPropertyRowMapper<>(Item.class), cid, start, pageSize);
        return items;
    }

    @Override
    public List<Item> selectPageByNam(String name, int start, int pageSize) {
        //sql语句
        String sql="select *from item where name like  ? limit ?,?";
        List<Item> items = template.query(sql, new BeanPropertyRowMapper<>(Item.class), "%"+name+"%", start, pageSize);
        return items;
    }

    @Override
    public Item selectOneById(int id) {
        //sql语句
        String sql="select * from item where id = ? ";
        //查询一个
        try {
            Item item = template.queryForObject(sql, new BeanPropertyRowMapper<>(Item.class), id);
            return item;
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        return null;
    }
}
