package com.bailiban.kw.service.impl;

import com.bailiban.kw.dao.ItemCatDao;
import com.bailiban.kw.dao.impl.ItemCatDaoImpl;
import com.bailiban.kw.domain.ItemCat;
import com.bailiban.kw.service.ItemCatService;

import java.util.List;

public class ItemCatServiceImpl implements ItemCatService {
    //
    private ItemCatDao itemCatDao =new ItemCatDaoImpl();
    @Override
    public List<ItemCat> selectAll() {
        return itemCatDao.selectAll();
    }

    @Override
    public ItemCat selectOneByCid(int cid) {
        return itemCatDao.selectOneByCid(cid);
    }
}
