<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>酷玩周边商城</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>

<body>
<!-- 头部 -->
<!--引入公共的头部-->
<jsp:include page="/header.jsp"/>

<!--
  主区域
-->
<div class="container" style="margin-top:25px;">
    <!--判断集合是否为空 -->


    <c:if test="${empty cart.cartItems}">
        <div class="jumbotron">
            <p class="text-center">
                您的购物车中还没有商品,
                <a class="" href="${pageContext.request.contextPath}/indexServlet"> 去逛逛吧</a>
            </p>
        </div>
    </c:if>

    <c:if test="${not empty cart.cartItems}">
        <div class="panel panel-success">
            <!-- Default panel contents -->
            <div class="panel-heading">
                <h3 class="panel-title">我的购物车</h3>
            </div>
            <!-- Table -->
            <table class="table table-condensed text-center table-responsive">
                <thead>
                <tr>
                    <th class="text-center">图片</th>
                    <th class="text-center">商品</th>
                    <th class="text-center">单价(元)</th>
                    <th class="text-center">数量</th>
                    <th class="text-center">小计</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart.cartItems}" var="entry">
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/upload/${entry.value.item.image}" class="buyimg" /></td>
                        <td>${entry.value.item.name}</td>
                        <td class="cart-item-price">¥${entry.value.item.shop_price}</td>
                        <td style="vertical-align: middle">
                            <div class=" input-group col-md-2 " style="margin:  0 auto;">
                                <input readonly name="count" type="text" class="form-control text-center" value="${entry.value.count}">
                            </div>
                        </td>
                        <td class="cart-item-price">¥${entry.value.subtotal}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart/delete?id=${entry.value.item.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>



                </tbody>
            </table>
        </div>
        <div class="text-right">
            商品金额：<span class="cart-money">¥${cart.total}元</span>
        </div>
        <div class="text-right"
             style="border-bottom: 1px solid #DDDDDD;border-top: 1px solid #DDDDDD; margin-top: 10px;padding:5px 0 5px 0;">
            <a class="btn  btn-danger btn-lg" href="${pageContext.request.contextPath}/cart/clean"><span class="glyphicon glyphicon-trash"
                                                    aria-hidden="true"></span>清空购物车</a>
            <a class="btn  btn-success btn-lg" href="${pageContext.request.contextPath}/order/submitOrder" role="button">提交订单</a>
        </div>
    </c:if>
</div>
<!--底部-->
<!--引入公共的脚部-->
<jsp:include page="/footer.jsp"/>
</body>

</html>