package com.bailiban.kw.web.servlet;

import com.bailiban.kw.domain.Item;
import com.bailiban.kw.domain.ItemCat;
import com.bailiban.kw.domain.PageInfo;
import com.bailiban.kw.service.ItemCatService;
import com.bailiban.kw.service.ItemService;
import com.bailiban.kw.service.impl.ItemCatServiceImpl;
import com.bailiban.kw.service.impl.ItemServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/item/*")
public class ItemServlet extends BaseServlet{

    //创建service
    private ItemService itemService =new ItemServiceImpl();
    private ItemCatService itemCatService =new ItemCatServiceImpl();

    //根据分类 分页显示商品信息
    public String queryPageByCid(HttpServletRequest request, HttpServletResponse response){

        //获取商品分类id
        int cid = Integer.parseInt(request.getParameter("cid"));
        //获取当前页
        int p = Integer.parseInt(request.getParameter("p"));

        PageInfo<Item> pageInfo = itemService.selectPageInfoByCid(cid, 6, p);

        //放入request域中
        request.setAttribute("pageInfo",pageInfo);
        //根据 分类id查询 分类
        ItemCat itemCat = itemCatService.selectOneByCid(cid);
        //放入request域中
        request.setAttribute("itemCat",itemCat);
        return "/category.jsp";
    }


    public String search(HttpServletRequest request, HttpServletResponse response){

        //获取查询关键字
        String keyword =request.getParameter("keyword");
        //获取当前页
        int p =Integer.parseInt(request.getParameter("p"));
        //得到分页对象
        PageInfo<Item> pageInfo = itemService.selectPageInfoByNmae(keyword, 6, p);
        //放入request域中
        request.setAttribute("pageInfo",pageInfo);
        //将查询关键字放入request域
        request.setAttribute("keyword",keyword);
        return "/search.jsp";
    }

    //显示商品详情的方法
    public String detail(HttpServletRequest request,HttpServletResponse response){

        //获取id
        int id=Integer.parseInt(request.getParameter("id"));
        //根据id查询
        Item item = itemService.selectOneById(id);
        //放入request域中
        request.setAttribute("item",item);


        return "/item.jsp";
    }
}
