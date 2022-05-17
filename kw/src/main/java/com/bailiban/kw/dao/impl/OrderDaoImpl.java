package com.bailiban.kw.dao.impl;

import com.bailiban.kw.dao.OrderDao;
import com.bailiban.kw.domain.Item;
import com.bailiban.kw.domain.Order;
import com.bailiban.kw.domain.OrderItem;
import com.bailiban.kw.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    //创建JDBCTemplate
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void insertOrder(Order order) {
        //sql语句
        String sql ="insert into orders values (?,?,?,?,?,?,?,?)";
        //参数数组
        Object[] params ={

                order.getOid(),
                order.getOrdertime(),
                order.getTotal(),
                order.getState(),
                order.getAddress(),
                order.getName(),
                order.getTelephone(),
                order.getUser().getUid()
        };
        template.update(sql,params);



    }

    @Override
    public void insertOrderItem(OrderItem orderItem) {
        //sql语句
        String sql ="insert into order_item values(?,?,?,?,?)";
        //参数数组
        Object[] params ={
            orderItem.getItemid(),
            orderItem.getCount(),
            orderItem.getSubtotal(),
            orderItem.getItem().getId(),
            orderItem.getOrder().getOid()
        };
        template.update(sql,params);

    }

    @Override
    public Order selectOnebyOid(String oid) {
        //sql语句
        String sql="SELECT * from orders where oid = ? ";
        try {
            Order order = template.queryForObject(sql, new BeanPropertyRowMapper<>(Order.class), oid);
            return order;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  @Override
     *     public Order selectOnebyupdate(String oid) {
     *         //sql语句
     *         String sql ="UPDATE orders SET address=? ,name=?,telephone=? WHERE oid=?";
     *         //参数数组
     *         Object[]params={
     *
     *         };
     *         template.update(sql,params);
     *         return null;
     *     }
     */
    @Override
    public void updateOrderInfo(Order order) {
        //sql语句
        String sql ="UPDATE orders SET address=? ,name=?,telephone=? WHERE oid=?";
        //参数数组
        Object[]params={
                order.getAddress(),
                order.getName(),
                order.getTelephone(),
                order.getOid()
        };
        //更新
        template.update(sql,params);

    }
    //根据用户id查询 该用户下的所有订单信息
    @Override
    public void updaOrderState(String oid, int state) {
       //sql语句
       String sql="update orders set state=? where oid=? ";
       //更新
       template.update(sql,state,oid);
    }
    //根据用户id查询 该用户下的所有订单信息
    @Override
    public List<Order> selectOrdersByUid(int uid) {
        //sql语句
        String sql="select o.oid , o.state , o.total , o.address , o.name , o.telephone , o.ordertime from orders o where uid = ? order by ordertime desc ";
        //查询
        List<Order> orders = template.query(sql, new BeanPropertyRowMapper<>(Order.class), uid);
        return orders;
    }
    //根据 订单编号查询订单下所有订单项信息
    @Override
    public List<OrderItem> selectOrderItemsByOid(String oid) {
        //sql语句
        String sql="select oi.count , oi.subtotal ,i.name, i.image , i.shop_price from order_item oi, item i where oi.id=i.id and oid=?";

        //查询 手动映射
        List<OrderItem> orderItems = template.query(sql, new RowMapper<OrderItem>() {
            @Override
            public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {

                //创建订单项
                OrderItem orderItem = new OrderItem();
                orderItem.setCount(resultSet.getInt("count"));
                orderItem.setSubtotal(resultSet.getDouble("subtotal"));
                //创建商品对象
                Item item = new Item();
                item.setImage(resultSet.getString("image"));
                item.setName(resultSet.getString("name"));
                item.setShop_price(resultSet.getDouble("shop_price"));

                //将商品对象设置到订单项中
                orderItem.setItem(item);
                //返回订单项
                return orderItem;
            }
        }, oid);
        //返回订单项的集合
        return orderItems;
    }
}
