<%--isELIgnored默认是true 要设置为false  表示解析el表达式--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--使用taglib指令 带入jstlb标签库--%>
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
    <title>酷玩周边商城-商品</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    <script>


       //function subtractValue() {

            //声明value变量并将获取的值赋给value

            //var value = document.getElementById("inputValueID").value;

            //窗口弹出input文本框输入的值

            //console.log(value);
            //if (value>1){
            //    var valuesubtract = value-1;
            //    console.log(valuesubtract);
            //    document.getElementById("inputValueID").value = valuesubtract;
            //}else{
            //   document.getElementById("inputValueID").value = value;
            //}
        //}

        //function plusValue(){
        //    var value = document.getElementById("inputValueID").value;

        //    console.log(value);
        //    if (value<${item.num}){
        //       var valueplus = value-0+1;
        //       console.log(valueplus);
        //      document.getElementById("inputValueID").value = valueplus;
        // }else {
        //    document.getElementById("inputValueID").value = value;
            //    alert("已超过最大库存");
            //}
        //}
        //onclick="subtractValue()"
        //onclick="plusValue()"


       //初始化
       $(function (){
           $("#btn-down").click(function (){
               //获取文本框对象
               let $buyNum =$("#buy-num");
               //获取文本框的值
               let num = $buyNum.val();
               //现在的值等于原来的值减一
               $buyNum.val(num>1?--num:1);

           })

           $("#btn-up").click(function (){
               //获取文本框对象
               let $buyNum =$("#buy-num");
               //获取文本框的值
               let num = $buyNum.val();
               //定义一个变量保存库存 最大值
               let max =${item.num};
               //现在的值等于原来的值加一
               $buyNum.val(num<max?++num:max);

           })

       })

    </script>
</head>

<body>
<!-- 头部 -->
<!--引入公共的头部-->
<jsp:include page="/header.jsp"/>
<!--主区域-->
<div class="container" style="margin-top: 10px;">
    <div class="row">
        <div class="col-md-12 col-xs-12">
            <div class="row" style="margin-bottom: 10px;">
                <div class="col-md-5 col-xs-5">
                    <div>
                        <a href="#"><img class="img-responsive" src="${pageContext.request.contextPath}/upload/${item.image}" /></a>
                    </div>
                </div>
                <div class="col-md-7 col-xs-7 product-info">
                    <div>
                        <h3 class="pord-name">${item.name}</h3>
                    </div>
                    <div class="pord-price clearfix">
                        <span class="pord-dispri">￥<strong>${item.shop_price}</strong></span>
                        <span class="pord-orpri">￥${item.market_price}</span>
                    </div>
                    <!--<div class="pord-detailbox clearfix">
                            <div class="pull-left pord-sold">已售：<span>1448</span></div>
                            <div class="pull-left pord-totalcom">累计评价：<span class="commentNum">48</span></div>
                        </div>-->
                    <div id="blk_detail_main_btn">
                        <form action="${pageContext.request.contextPath}/cart/add" method="post">
                            <input type="hidden" name="id" value="${item.id}">
                            <div class="mt10 pord-num clearfix">
                                <label class="pull-left pt10">数量</label>
                                <div class="pull-left ml42">
                                    <a href="javascript:;"
                                       id="btn-down" class="pull-left num-plus numbtn num-plus btn_detail_main_buy_min"><i
                                            class="ico-detail">-</i></a>
                                    <input id='buy-num' name="count" type="text" size="2" maxlength="3" stock_num="728" limit_num="30"
                                           class="fl nummidbtn num inpt_detail_main_buy_num" value="1">
                                    <a href="javascript:;"
                                       id="btn-up" class="pull-left numbtn num-minus btn_detail_main_buy_plus"><i
                                            class="ico-detail">+</i></a>
                                </div>
                                <span class="pord-stock">（库存：${item.num}）</span>
                            </div>
                            <div class="pord-btn clearfix">
                                <button type="submit" id="btn_detail_cart_add"
                                        class="fl joincart-btn">加入购物车</button>
                                <button type="submit" id="btn_detail_cart_buy" class="fl ml30 buy-btn">立即购买</button>
                                <a href="javascript:;" class="fl btn-collect">
                                    <i class="ico-detail ico-collect"></i>
                                    <span>收藏</span>
                                </a>
                                <a href="javascript:;" class="fl btn-collected" style="display: none">
                                    <i class="ico-detail ico-collect"></i>
                                    <span>已收藏</span>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-xs-12">
                    <div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
                        <ul id="myTabs" class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
                                   aria-expanded="true">商品介绍</a>
                            </li>
                            <li role="presentation">
                                <a href="#profile" role="tab" id="profile-tab" data-toggle="tab"
                                   aria-controls="profile">商品评论</a>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content ">
                            <div role="tabpanel" class="tab-pane fade in active" id="home"
                                 aria-labelledby="home-tab">
                                <img class="img-responsive center-block" src="${pageContext.request.contextPath}/upload/${item.idesc}" />
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
                                <div class="media">
                                    <div class="media-body">
                                        <h4 class="media-heading">王大炮：</h4>
                                        非常不错啊！！！
                                    </div>
                                </div>
                                <div class="media">
                                    <div class="media-body">
                                        <h4 class="media-heading">王中炮：</h4>
                                        非常不错啊！！！
                                    </div>
                                </div>
                                <div class="media">
                                    <div class="media-body">
                                        <h4 class="media-heading">王小炮：</h4>
                                        非常不错啊！！！
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部-->
<!--引入公共的脚部-->
<jsp:include page="/footer.jsp" />
</body>

</html>