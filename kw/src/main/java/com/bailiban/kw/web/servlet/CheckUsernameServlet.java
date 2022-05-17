package com.bailiban.kw.web.servlet;

import com.bailiban.kw.domain.User;
import com.bailiban.kw.service.UserService;
import com.bailiban.kw.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckUsernameServlet", value = "/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");//响应乱码
        //创建service
        UserService userService =new UserServiceImpl();
        //接受提交的用户名
        String username = request.getParameter("username");
        //根据用户名 查询用户是否存在
        User user = userService.selectUserByUsername(username);

        //创建一个map集合 用于返回json数据
        Map<String ,String>map = new HashMap<>();
        //进行判断
        if (user!=null){
            map.put("code","0");
            map.put("rs","用户名已经被使用");
        }else{
            map.put("code","1");
            map.put("rs","用户名可以使用");
        }

        //将map集合 转换为json数据 通过字符串输出
        ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.writeValue(response.getWriter(),map);
        //System.out.println("请求了");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //无论是get还是post请求都执行get方法集中关注点
        doGet(request,response);
    }
}
