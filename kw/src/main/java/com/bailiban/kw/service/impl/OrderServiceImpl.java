package com.bailiban.kw.service.impl;

import com.bailiban.kw.dao.OrderDao;
import com.bailiban.kw.dao.impl.OrderDaoImpl;
import com.bailiban.kw.domain.Order;
import com.bailiban.kw.domain.OrderItem;
import com.bailiban.kw.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    //创建dao
    private OrderDao orderDao=new OrderDaoImpl();
    @Override
    public void submitOrder(Order order) {
        //System.out.println(111);
        //向订单表中添加订单信息
        orderDao.insertOrder(order);
        //向订单表中添加订单项信息
        for (OrderItem orderItem : order.getOrderItems()){
            orderDao.insertOrderItem(orderItem);
        }

    }

    @Override
    public Order selectOnebyOid(String oid) {
        return orderDao.selectOnebyOid(oid);
    }

    @Override
    public void updateOrderInfo(Order order) {
        orderDao.updateOrderInfo(order);

    }

    @Override
    public void updaOrderState(String oid, int state) {
        orderDao.updaOrderState(oid,state);
    }
    //根据用户ID查询 该用户所有订单信息
    @Override
    public List<Order> selectOrdersByUid(int uid) {

        //查询该用户下所有订单信息
        List<Order> orders = orderDao.selectOrdersByUid(uid);
        //遍历订单
        for (Order order :orders){
            //根据订单编号查询该订单下的所有订单项
            List<OrderItem> orderItems = orderDao.selectOrderItemsByOid(order.getOid());
            //将订单项的集合 设置到订单中
            order.setOrderItems(orderItems);
        }
        return orders;
    }
}
