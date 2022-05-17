package com.bailiban.kw.service;

import com.bailiban.kw.domain.Item;
import com.bailiban.kw.domain.PageInfo;

import java.util.List;

public interface ItemService {
    //查收热门和优惠商品
    List<Item> selectItemsByIflag(int iflag);
    //根据 分类id 分页查询商品信息
    PageInfo<Item> selectPageInfoByCid(int cid , int pageSize, int currentPage);
    //根据商品名 分页模糊查询商品信息
    PageInfo<Item> selectPageInfoByNmae(String name, int pageSize, int currentPage);
    //根据商品id查询一个商品
    Item selectOneById(int id);

}
