package com.bailiban.kw.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单
 */
public class Order {
    /*
      `oid` VARCHAR(32) NOT NULL,
      `ordertime` DATETIME DEFAULT NULL,
      `total` DOUBLE DEFAULT NULL,
      `state` INT DEFAULT NULL, -- 1 未付款  2 已付款
      `address` VARCHAR(30) DEFAULT NULL,
      `name` VARCHAR(20) DEFAULT NULL,
      `telephone` VARCHAR(20) DEFAULT NULL,
      `uid` INT DEFAULT NULL,
     */
    private String oid; //订单号
    private Date ordertime;//下单时间
    private double total; //总计
    private int state;
    private String address;
    private String name;
    private String telephone;
    //表达的是 该订单属于哪个用户
    private  User user;
    //表达出 一个订单下有多个订单项（一对多关系）
    private List<OrderItem> orderItems =new ArrayList<>();


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
