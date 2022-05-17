package com.bailiban.kw.dao;

import com.bailiban.kw.domain.Order;
import com.bailiban.kw.domain.OrderItem;

import java.util.List;

public interface OrderDao {
    //添加订单信息
    void  insertOrder(Order order);
    //添加订单项信息
    void insertOrderItem(OrderItem orderItem);
    //根据订单编号 查询一个订单
    Order selectOnebyOid(String oid);
    //根据订单标号添加收货信息
    //Order selectOnebyupdate(String oid);
    //更新收货人信息
    void updateOrderInfo(Order order);
    //更新订单状态
    void updaOrderState(String oid ,int state);
    //根据用户id查询 该用户下的所有订单信息
    List<Order> selectOrdersByUid(int uid);
    //根据 订单编号查询订单下所有订单项信息
    List<OrderItem> selectOrderItemsByOid (String oid);

}
