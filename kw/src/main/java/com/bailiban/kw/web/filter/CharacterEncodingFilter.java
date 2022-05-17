package com.bailiban.kw.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    //初始化方法 创建过滤器后立即执行
    public void init(FilterConfig config) throws ServletException {

    }

    //销毁方法 过滤器销毁之前执行
    public void destroy() {
    }

    // 拦截 方法 请求一次拦截一次
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        //向下转型
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //统一解决请求乱吗问题
        req.setCharacterEncoding("utf-8");//请求乱码

            //获取拦截的URI地址
        String uri = req.getRequestURI();
        //System.out.println(uri);

          //如果uri地址中不包含。css才设置
        if (!uri.contains(".css")){
             res.setContentType("text/html;charset=utf-8");//响应乱码              
        }
        //System.out.println("拦截了");
        //放行方法
        chain.doFilter(request, response);
    }
}
