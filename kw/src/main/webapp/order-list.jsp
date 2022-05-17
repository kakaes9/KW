<%--isELIgnored默认是true 要设置为false  表示解析el表达式--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--使用taglib指令 带入jstlb标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--格式化--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
<div class="container" style="margin-top: 20px;">
    <c:forEach items="${orders}" var="order">
        <div class="list-group">
            <h4 class="list-group-item-heading">订单号:${order.oid}
                状态:
                <c:if test="${order.state==1}">
                    <a href="${pageContext.request.contextPath}/order/pay?WIDout_trade_no=${order.oid}" class="btn btn-sm btn-warning">未付款</a>
                </c:if>
                <c:if test="${order.state==2}">
                    <span class="label-success">已付款</span>
                </c:if>
                </h4>
            <div class="list-group-item">
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>图片</th>
                        <th>商品</th>
                        <th>单价(元)</th>
                        <th>数量</th>
                        <th>小计</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${order.orderItems}" var="oi">
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/upload/${oi.item.image}" class="buyimg" /></td>
                            <td>${oi.item.name}</td>
                            <td>¥${oi.item.shop_price}</td>
                            <td>${oi.count}</td>
                            <td>¥${oi.subtotal}</td>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
            </div>
            <div class="text-right list-group-item" style="margin-bottom: 10px;">
                <address>
                    收货地址：${order.address}<br/>
                    收货人：${order.name}<br/>
                    联系方式：${order.telephone}<br/>
                    下单日期：<fmt:formatDate value="${order.ordertime}" pattern="yyyy年MM月dd日hh时mm分ss秒"></fmt:formatDate>
                </address>
                商品金额：<span class="xiangq_font3">¥${order.total}元</span>
            </div>
        </div>
    </c:forEach>
</div>
<!--底部-->
<!--引入公共的脚部-->
<jsp:include page="/footer.jsp" />
</body>

</html>