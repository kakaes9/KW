<%--isELIgnored默认是true 要设置为false  表示解析el表达式--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--使用taglib指令 带入jstlb标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!--引入公共的头部-->
<jsp:include page="/header.jsp"/>
<div class="container" style="margin-top:25px;">
    <C:if test="${signVerified}">
        <div  class="alert alert-success center-block" role="alert" style="width: 500PX;">
            <h3 class="text-center">支付成功</h3>

            <dl class="dl-horizontal">
                <dt>订单号:</dt>
                <dd >${out_trade_no}</dd>
                <dt>支付宝交易号:</dt>
                <dd>${trade_no}</dd>
                <dt>交易金额:</dt>
                <dd>${total_amount}</dd>
            </dl>
            <p class="text-center"><a href="${pageContext.request.contextPath}/order/show">查看订单</a></p>
        </div>
    </C:if>

    <C:if test="${!signVerified}">
        <div class="alert alert-danger center-block" role="alert" style="width: 500PX;">
            <h3 class="text-center">支付失败</h3>
            <p class="text-center"><a href="">重新支付</a></p>
        </div>
    </C:if>
</div>
<!--底部-->
<!--引入公共的脚部-->
<jsp:include page="/footer.jsp" />
</body>

</html>