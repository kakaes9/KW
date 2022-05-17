package com.bailiban.kw.web.servlet;

import com.bailiban.kw.domain.User;
import com.bailiban.kw.service.UserService;
import com.bailiban.kw.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramostear.captcha.HappyCaptcha;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// user/register     /user/* *就是register
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {


    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取表单提交的数据
        String username = request.getParameter("username");//用户名
        String password = request.getParameter("password");//密码
        String captcha = request.getParameter("captcha");//验证码

        //System.out.println(username+"--"+password+"--"+captcha);

        //验证码是否输入正确
        //public static boolean verification( HttpServiceRequest request, String code , boolean);
        //返回true验证通过 false验证不通过
        //boole ignoreCase 是否忽视大小写 true忽视 false不忽视
        boolean verification = HappyCaptcha.verification(request, captcha, true);
        //如果返回false验证失败
        if (!verification){
            //向request域中放入错误信息提示
            request.setAttribute("error","请输入正确验证码");
            //将错误信息转发到login.jsp显示让用户再次输入
            //request.getRequestDispatcher("/login.jsp").forward(request,response);
            //结束方法的执行
            return "/login.jsp";
        }
        //验证用户和密码是否正确
        UserService userService =new UserServiceImpl();
        User user = userService.userLogin(username, password);
        //如果登录失败
        if(username==null){

            //向request域中放入错误信息提示
            request.setAttribute("error","请输入正确的用户名或密码");
            //将错误信息转发到login.jsp显示让用户再次输入
            //request.getRequestDispatcher("/login.jsp").forward(request,response);
            //结束方法的执行
            return "/login.jsp";
        }else{
            //登录成功 将用户对象放入session中 跟踪用户登录状态
            request.getSession().setAttribute("user",user);

            //重定向到首页
            response.sendRedirect(request.getContextPath()+"/indexServlet");
            return null;
        }
    }
    public String exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //销毁session
        request.getSession().invalidate();
        // 跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return null;
    }
    public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接受表单提交数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        //将数据封装进对象
        User user =new User(username,password,name,email,tel);
        //创建service
        UserService userService =new UserServiceImpl();
        userService.userRegister(user);

        //注册成功跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return null;
    }

    //ajax
    public String checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json;charset=utf-8");//响应乱码
        //创建service
        UserService userService =new UserServiceImpl();
        //接受提交的用户名
        String username = request.getParameter("username");
        //根据用户名 查询用户是否存在
        User user = userService.selectUserByUsername(username);

        //创建一个map集合 用于返回json数据
        Map<String ,String> map = new HashMap<>();
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

        return null;
    }

}
