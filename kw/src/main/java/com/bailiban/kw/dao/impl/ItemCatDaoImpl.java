package com.bailiban.kw.dao.impl;

import com.bailiban.kw.dao.ItemCatDao;
import com.bailiban.kw.dao.ItemDao;
import com.bailiban.kw.domain.ItemCat;
import com.bailiban.kw.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ItemCatDaoImpl implements ItemCatDao {
    //创建JDBCTemplate
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<ItemCat> selectAll() {
        //sql语句
        String sql="SELECT * FROM item_cat where status=1 ";
        List<ItemCat> itemCats = template.query(sql, new BeanPropertyRowMapper<>(ItemCat.class));
        return itemCats;
    }

    @Override
    public ItemCat selectOneByCid(int cid) {
        //sql语句
        String sql="SELECT * FROM item_cat where cid = ? ";
        try {
            ItemCat itemCat = template.queryForObject(sql, new BeanPropertyRowMapper<>(ItemCat.class), cid);
            return itemCat;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
