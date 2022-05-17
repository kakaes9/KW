package com.bailiban.kw.test;

import com.bailiban.kw.dao.ItemDao;
import com.bailiban.kw.dao.impl.ItemDaoImpl;
import com.bailiban.kw.domain.Item;
import com.bailiban.kw.domain.PageInfo;
import com.bailiban.kw.service.ItemService;
import com.bailiban.kw.service.impl.ItemServiceImpl;
import org.junit.Test;

import java.util.List;

public class DaoTest {
    //测试商品dao
    @Test
    public void  test(){
        //创建dao
        ItemDao itemDao =new ItemDaoImpl();
        List<Item> rm_items = itemDao.selectItemsByIflag(1);
        System.out.println(rm_items);
    }

    //测试分页
    @Test
    public  void test2(){
        ItemService itemService =new ItemServiceImpl();

        PageInfo<Item> pageInfo = itemService.selectPageInfoByCid(1, 6, 3);

        System.out.println("当前页："+pageInfo.getCurrentPage());
        System.out.println("上一页："+pageInfo.getPrePage());
        System.out.println("下一页："+pageInfo.getNextPage());
        System.out.println("总页数："+pageInfo.getTotalPages());
        System.out.println("总记录数："+pageInfo.getTotalCount());
        //当前页的数据集合
        System.out.println(pageInfo.getList());
    }

    @Test
    public  void test3(){
        ItemService itemService =new ItemServiceImpl();

        PageInfo<Item> pageInfo = itemService.selectPageInfoByNmae("手办", 6, 3);

        System.out.println("当前页："+pageInfo.getCurrentPage());
        System.out.println("上一页："+pageInfo.getPrePage());
        System.out.println("下一页："+pageInfo.getNextPage());
        System.out.println("总页数："+pageInfo.getTotalPages());
        System.out.println("总记录数："+pageInfo.getTotalCount());
        //当前页的数据集合
        System.out.println(pageInfo.getList());
    }
}
