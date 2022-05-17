package com.bailiban.kw.dao;

import com.bailiban.kw.domain.ItemCat;

import java.util.List;

public interface ItemCatDao {

    //查询状态为正常的所有分类信息
    List<ItemCat> selectAll();

    //根据 分类id查询一个分类
    ItemCat selectOneByCid(int cid);
}
