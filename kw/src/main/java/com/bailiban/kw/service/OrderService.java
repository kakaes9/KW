package com.bailiban.kw.service;

import com.bailiban.kw.domain.Order;

import java.util.List;

public interface OrderService {
    //生成订单
    void submitOrder(Order order);
    //根据订单编号 查询一个订单
    Order selectOnebyOid(String oid);
    //更新收货人信息
    void updateOrderInfo(Order order);
    //更新订单状态
    void updaOrderState(String oid ,int state);
    //根据用户ID查询 该用户所有订单信息
    List<Order> selectOrdersByUid(int uid);


}
