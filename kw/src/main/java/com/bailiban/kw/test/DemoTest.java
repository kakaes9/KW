package com.bailiban.kw.test;

import com.bailiban.kw.domain.Item;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DemoTest  {
    // @Test 标记改方法是测试反法
    //测试 方法 public void
    @Test
    public  void test1(){

        int i=38;
        System.out.println(i);
    }
    //map集合的遍历
    @Test
    public void test6(){
        Map<String,String> map =new HashMap<>();
        map.put("name","旺财");
        map.put("color","黄色");
        //将map集合转换为set集合
        Set<Map.Entry<String,String>> entries=map.entrySet();

        for(Map.Entry<String,String>entry:entries){
            //获取key
            System.out.println(entry.getKey()+"-----"+entry.getValue());

        }
        Map<String, Item> map1 =new HashMap<>();
        map1.put("1",new Item(1,"橘子"));
        map1.put("2",new Item(2,"草莓"));
        for (Map.Entry<String,Item> entry:map1.entrySet()){
            System.out.println(entry.getValue().getName());
        }

    }

}
