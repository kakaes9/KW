package com.bailiban.kw.web.servlet;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bailiban.kw.domain.*;
import com.bailiban.kw.service.OrderService;
import com.bailiban.kw.service.impl.OrderServiceImpl;
import com.bailiban.kw.utils.AlipayConfig;
import com.bailiban.kw.utils.UuidUtil;
import org.springframework.util.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    //创建service
    private OrderService orderService=new OrderServiceImpl();
    //提交订单的方法
    public  String submitOrder(HttpServletRequest request , HttpServletResponse response){

        //在提交订单的时候 要验证用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        //如果没有登录
        if (user==null){
            //向request域中放错误信息
            request.setAttribute("error","请您先登录在下单");
            return "/login.jsp";
        }

        //将购物车从sess中取出
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //创建一个订单对象
        Order order =new Order();
        order.setOid(UuidUtil.getUuid());//订单编号
        order.setOrdertime(new Date());//下单时间
        order.setState(1);//订单状态 未付款
        order.setTotal(cart.getTotal());//总计
        order.setUser(user);//订单输入哪个用户

        //遍历购物车的购物项 创建对应的订单项
        for(Map.Entry<Integer, CartItem> entry:cart.getCartItems().entrySet()){
            //创建购物项
            OrderItem orderItem =new OrderItem();
            orderItem.setItemid(UuidUtil.getUuid());//购物项的编号
            orderItem.setOrder(order);//该购物项属于哪个订单
            orderItem.setCount(entry.getValue().getCount());//购买数量
            orderItem.setSubtotal(entry.getValue().getSubtotal());//小计
            orderItem.setItem(entry.getValue().getItem());//设置购物项对应的商品
            //将该订单项添加到集合中
            order.getOrderItems().add(orderItem);

        }
        //生成订单oo
        System.out.println(order);
        orderService.submitOrder(order);

        //清空购物车
        cart.clear();
        request.getSession().removeAttribute("cart");
        //将订单放入request域
        request.setAttribute("order",order);


        return "/order.jsp";
    }

    //支付方法 起点
    public String pay(HttpServletRequest request , HttpServletResponse response) throws IOException, AlipayApiException {
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = request.getParameter("WIDout_trade_no");
        //接受收货人信息
        String address = request.getParameter("address");
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        //如果有收货人地址
        if (!StringUtils.isEmpty(address)&&!StringUtils.isEmpty(name)&&!StringUtils.isEmpty(telephone)){
            //将信息封装进order
            Order o =new Order();
            o.setOid(out_trade_no);
            o.setAddress(address);
            o.setName(name);
            o.setTelephone(telephone);
            //跟新 收货人 联系方式 地址信息
            orderService.updateOrderInfo(o);
        }
        //根据订单编号查询要付款的订单
        Order order = orderService.selectOnebyOid(out_trade_no);
        if (StringUtils.isEmpty(order.getAddress())||StringUtils.isEmpty(order.getName())||StringUtils.isEmpty(order.getTelephone())){
            //如果没有收货人地址 回显地址输入页面
            request.setAttribute("oid",out_trade_no);
            return "/address.jsp";

        }


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);



        //付款金额，必填
        double total_amount = order.getTotal();
        //从session中取出 用户
        User user = (User) request.getSession().getAttribute("user");
        //订单名称，必填
        String subject = user.getName()+"的订单";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        response.getWriter().println(result);

        return null;
    }
    //支付回调方法 终点
    public String callBack(HttpServletRequest request , HttpServletResponse response) throws AlipayApiException {

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //验签标记 true 表示支付成功   false表示失败
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        //将标记放入request域
        request.setAttribute("signVerified",signVerified);

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //付款金额
            String total_amount = request.getParameter("total_amount");

            //将交易信息放入request域
            request.setAttribute("out_trade_no",out_trade_no);
            request.setAttribute("trade_no",trade_no);
            request.setAttribute("total_amount",total_amount);
            //支付成功 更新订单的状态为已支付
            orderService.updaOrderState(out_trade_no,2);

        }else {

        }
        //——请在这里编写您的程序（以上代码仅作参考）——
        return "/pay-result.jsp";
    }

    //查看用户订单方法
    public String show(HttpServletRequest request , HttpServletResponse response){

        //从session中取出用户
        User user = (User) request.getSession().getAttribute("user");
        //查询当前用户下的订单
        List<Order> orders = orderService.selectOrdersByUid(user.getUid());
        //翻入request域中
        request.setAttribute("orders",orders);
        return "/order-list.jsp";

    }

}
