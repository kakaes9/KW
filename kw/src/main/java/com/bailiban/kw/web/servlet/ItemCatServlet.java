package com.bailiban.kw.web.servlet;

import com.bailiban.kw.domain.ItemCat;
import com.bailiban.kw.service.ItemCatService;
import com.bailiban.kw.service.impl.ItemCatServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/itemCatServlet")
public class ItemCatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("application/json;charset=utf-8");//响应乱码

        //
        ItemCatService itemCatService =new ItemCatServiceImpl();
        List<ItemCat> itemCats = itemCatService.selectAll();
        //
        ObjectMapper objectMapper=new ObjectMapper();
        //
        objectMapper.writeValue(response.getWriter(),itemCats);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //无论是get还是post请求都执行get方法集中关注点
        doGet(request,response);
    }
}
