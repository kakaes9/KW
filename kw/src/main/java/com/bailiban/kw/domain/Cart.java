package com.bailiban.kw.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 */

public class Cart {
    //购物车的集合
    private Map<Integer,CartItem> cartItems =new HashMap<>();
    //总计
    private double total;

    //添加购物项的方法
    public  void addCartItem(CartItem cartItem){

        //获取该购物项在购物车的唯一标识 商品id
        int id = cartItem.getItem().getId();
        //根据唯一标识 判断该购物项在购物车中是否存在
        if (cartItems.containsKey(id)) {
            //如果存在
            //更新当前购物项的数量即可
            CartItem cartItem_old = cartItems.get(id);//得到原来的购物项
            //将其购买更新 现在的数量=原来的数量-添加的数量
            cartItem_old.setCount(cartItem_old.getCount()+cartItem.getCount());

        }else {
            //如果不存在该购物项 直接添加到 map集合
            cartItems.put(id,cartItem);
        }
        //更新总计 现在的总计等于原来的总计 +添加的购物项小计
        total += cartItem.getSubtotal();
    }

    //删除方法
    /**
     *
     * @param id 商品id删除
     */
    public void delete(int id){
        // V remove(object key); 返回的是被删除的一项
        CartItem remove = cartItems.remove(id);
        //更新总计
        total -=remove.getSubtotal();
    }

    //清空方法
    public void clear(){

        cartItems.clear();
    }

    public void clarCartItem(CartItem cartItem){

        cartItems.clear();
        //更新总计
        total =0;
    }




    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer, CartItem> cartItems) { this.cartItems = cartItems; }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
