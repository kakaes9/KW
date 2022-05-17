package com.bailiban.kw.domain;

/**
 * 订单项
 */

public class OrderItem {
    /*
      `itemid` VARCHAR(32) NOT NULL,
      `count` INT DEFAULT NULL,
      `subtotal` DOUBLE DEFAULT NULL,
      `id` INT  DEFAULT NULL,
      `oid` VARCHAR(32) DEFAULT NULL,
     */

    private String itemid;//订单项 编号
    private  int count;//数量
    private double subtotal;//小计
    //表达的是 该项买的是什么商品
    private  Item item;
    //表达订单项和订单 多对一 一个订单项属于一个订单
    private  Order order;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
