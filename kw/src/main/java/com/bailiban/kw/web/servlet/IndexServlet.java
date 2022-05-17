package com.bailiban.kw.web.servlet;

import com.bailiban.kw.domain.Item;
import com.bailiban.kw.service.ItemService;
import com.bailiban.kw.service.impl.ItemServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        //创建service
        ItemService itemService =new ItemServiceImpl();
        //获取热门商品
        List<Item> rm_items = itemService.selectItemsByIflag(1);
        //将数据放入request转发到视图index.jsp
        request.setAttribute("rm_items",rm_items);
        //获取优惠商品
        List<Item> yh_items = itemService.selectItemsByIflag(2);
        //将数据放入request转发到视图index.jsp
        request.setAttribute("yh_items",yh_items);

        //转发
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        doGet(request,response);
    }
}
