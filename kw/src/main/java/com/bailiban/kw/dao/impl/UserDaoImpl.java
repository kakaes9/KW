package com.bailiban.kw.dao.impl;

import com.bailiban.kw.dao.UserDao;
import com.bailiban.kw.domain.User;
import com.bailiban.kw.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    //创建JDBCTemplate
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User userLogin(String username, String passeard) {
        //sql语句
        String sql="select * from user where username= ? and password = ? ";

        //查询一个API
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, passeard);
        return user;
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        return null;
    }

    @Override
    public void userRegister(User user) {
        //sql语句
        String sql ="INSERT INTO user VALUES (NULL,?,?,?,?,?) ";
        //定义参数数组
        Object[] params ={
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getTelephone()
        };
        //执行update
        template.update(sql,params);
    }

    @Override
    public User selectUserByUsername(String username) {
        //sql语句
        String sql="select * from user where username = ? ";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
            return user;
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        return null;
    }


}
