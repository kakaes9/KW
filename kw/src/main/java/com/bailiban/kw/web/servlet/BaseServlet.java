package com.bailiban.kw.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的URI  /user/login    /user/exit   /user/register
        String uri = req.getRequestURI();
        //System.out.println(uri);
        //分析uri 解析出 要执行的反法名
        //1.获取最后一个/的索引
        int index = uri.lastIndexOf("/");
        //2.从 后一个位置截取
        String methodName = uri.substring(index + 1);
        //System.out.println(methodName);
        //根据反法名 获取该方法名对应的 方法对象
        //System.out.println(this);
        //Method getmethod(String name ,class<?>...parametertypes)
        /*
            string name 方法名称
         */
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用该方法
            //public Object invoke(Object obj,Object...args)
            /*
                Object obj 该方法所属于的对象
                Object...args参数列表
                放回 Object 该方法调用后的放回值
             */
            String invoke = (String) method.invoke(this, req, resp);
            //System.out.println(invoke);
            //返回值规则  如果放回null 表示重定向  如果放回的不是null表示 转发
            //如果放回不是空值 格式必须是 /视图名 如果   /index。jsp
            if (invoke!=null){
                req.getRequestDispatcher(invoke).forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
