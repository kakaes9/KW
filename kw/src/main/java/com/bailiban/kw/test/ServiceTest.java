package com.bailiban.kw.test;

import com.bailiban.kw.domain.ItemCat;
import com.bailiban.kw.domain.Order;
import com.bailiban.kw.service.ItemCatService;
import com.bailiban.kw.service.OrderService;
import com.bailiban.kw.service.impl.ItemCatServiceImpl;
import com.bailiban.kw.service.impl.OrderServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.List;

public class ServiceTest {
    @Test
    public void test1() throws JsonProcessingException {
        ItemCatService itemCatService =new ItemCatServiceImpl();
        List<ItemCat> itemCats = itemCatService.selectAll();
        System.out.println(itemCats);
        //将list集合转换为json对象的集合
        //1.创建核心对象
        ObjectMapper objectMapper =new ObjectMapper();
        String rs = objectMapper.writeValueAsString(itemCats);
        System.out.println(rs);
    }

    //测试service
    @Test
    public void test2(){
        OrderService orderService =new OrderServiceImpl();
        List<Order> orders =orderService.selectOrdersByUid(1);
        System.out.println(orders);
    }
}
