package com.bailiban.kw.web.servlet;

import com.bailiban.kw.domain.Cart;
import com.bailiban.kw.domain.CartItem;
import com.bailiban.kw.domain.Item;
import com.bailiban.kw.service.ItemService;
import com.bailiban.kw.service.impl.ItemServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {
    //创建service
    private ItemService itemService =new ItemServiceImpl();
    //添加购物车的方法
    public String add(HttpServletRequest request, HttpServletResponse response){

        //接受购买商品的id
        int id=Integer.parseInt(request.getParameter("id"));
        //接受购买商品的数量
        int count =Integer.parseInt(request.getParameter("count"));
        //System.out.println(id);
        //System.out.println(count);

        //根据id得到商品对象
        Item item =itemService.selectOneById(id);
        //根据购买数量和商品对象 创建一个购物项
        CartItem cartItem =new CartItem(item,count);
        //从session中取出购物车
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //判断session中是否存在购物车
        if (cart==null){
            //1.如果没有创建 并将购物项添加到购物车
            cart =new Cart();
            //将购物项添加到购物车中
            cart.addCartItem(cartItem);
            //在将购物车保存在session中
            session.setAttribute("cart",cart);

        }else {
            //如果有 直接将购物项添加到购物车
            cart.addCartItem(cartItem);
            //在将购物车保存在session中
            session.setAttribute("cart",cart);
        }
        return "/cart.jsp";
    }

    //删除购物车的方法
    public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //接受传过来的id
        int id =Integer.parseInt(request.getParameter("id"));
        //从session中取出购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.delete(id);
        }
        //再将购物车保存到session中
        request.getSession().setAttribute("cart",cart);

        //删除成功重定向到购物车页面
        response.sendRedirect(request.getContextPath()+"/cart.jsp");

        return null;
    }
    public String clean(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //从session中取出购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.clear();

        //清理成功重定向到购物车页面
        response.sendRedirect(request.getContextPath()+"/cart.jsp");
        return null;
    }
}


