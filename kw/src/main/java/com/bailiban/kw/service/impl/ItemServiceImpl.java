package com.bailiban.kw.service.impl;

import com.bailiban.kw.dao.ItemDao;
import com.bailiban.kw.dao.impl.ItemDaoImpl;
import com.bailiban.kw.domain.Item;
import com.bailiban.kw.domain.PageInfo;
import com.bailiban.kw.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    //service 依赖dao 创建dao
    private ItemDao itemDao =new ItemDaoImpl();
    @Override
    public List<Item> selectItemsByIflag(int iflag) {

        return itemDao.selectItemsByIflag(iflag);
    }

    /**
     *
     * @param cid   分类id
     * @param pageSize 页码大小
     * @param currentPage 当前页
     * @return
     */
    @Override
    public PageInfo<Item> selectPageInfoByCid(int cid, int pageSize, int currentPage) {

        //得到总的记录条数
        int totalCount = itemDao.selectCountByCid(cid);
        //根据页码大小和当前页计算出 起始索引
        int start =(currentPage-1)*pageSize;
        //查看当前页商品集合
        List<Item> items = itemDao.selectPageByCid(cid, start, pageSize);
        //实例化分页对象
        PageInfo<Item> pageInfo =new PageInfo<>(totalCount,currentPage,pageSize);
        //设置当前页的数据集合
        pageInfo.setList(items);
        //返回分页对象
        return pageInfo;
    }

    /**
     *
     * @param name   查询关键字
     * @param pageSize 页码大小
     * @param currentPage 当前页
     * @return
     */
    @Override
    public PageInfo<Item> selectPageInfoByNmae(String name, int pageSize, int currentPage) {
        //得到总的记录条数
        int totalCount = itemDao.selectCountByName(name);
        //根据页码大小和当前页计算出 起始索引
        int start =(currentPage-1)*pageSize;
        //查看当前页商品集合
        List<Item> items = itemDao.selectPageByNam(name,start,pageSize);
        //实例化分页对象
        PageInfo<Item> pageInfo =new PageInfo<>(totalCount,currentPage,pageSize);
        //设置当前页的数据集合
        pageInfo.setList(items);
        //返回分页对象
        return pageInfo;
    }

    @Override
    public Item selectOneById(int id) {
        return itemDao.selectOneById(id);
    }
}
