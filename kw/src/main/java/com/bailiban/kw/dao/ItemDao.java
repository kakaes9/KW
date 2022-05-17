package com.bailiban.kw.dao;

import com.bailiban.kw.domain.Item;

import java.util.List;

public interface ItemDao {
    //查收热门和优惠商品
    List<Item> selectItemsByIflag(int iflag);
    //查询某个分类下商品总记录数
    int selectCountByCid(int cid);
    //根据商品名模糊查询 商品的总记录数
    int selectCountByName(String name);
    //根据分类id查询商品 分页数据
    List<Item> selectPageByCid(int cid,int start,int pageSize);
    //根据商品名 模糊查询 分页数据
    List<Item> selectPageByNam(String name,int start,int pageSize);
    //根据商品id查询一个商品
    Item selectOneById(int id);

}
