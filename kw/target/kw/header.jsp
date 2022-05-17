<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 头部 -->
<header>
    <div class="container-fluid wz-notice">
        <div class="container">
            <li class="icon-notice"></li>
            <span>酷玩周边商城手办热销中！，点击 <a href="#">查看详情></a> </span>
        </div>
    </div>
    <nav class="container-fluid wz-info">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-xs-6">
                    <a class="icon-wzry pull-left" href="#">王者荣耀</a>
                    <div class="fl ico-comm i-solgan">可以触摸的欢乐时光</div>
                </div>
                <div class="col-md-4 hidden-xs">
                    <div class="search-box">
                        <input type="text" id="keyword" class="pull-left" placeholder="请输入想要找的宝贝" />
                        <button type="submit" id="btn-search" class="pull-left">搜索</button>
                    </div>
                </div>
                <div class="col-md-4 col-xs-6" style="margin-top: 40px;">
                    <ul class="list-inline text-right ">

                        <c:if test="${user==null}">
                            <li>
                                <a class="font1" href="${pageContext.request.contextPath}/login.jsp"><span class="glyphicon glyphicon-user"
                                                                                                           aria-hidden="true"></span>&nbsp;登录</a>
                            </li>
                        </c:if>
                        <c:if test="${user!=null}">
                            <li class="dropdown">
                                <span data-toggle="dropdown" class="font1" href="javascript:void(0);"> 欢迎您：${user.name} <span class="caret"></span></span>
                                <ul class="dropdown-menu" >
                                    <li><a href="${pageContext.request.contextPath}/order/show"><span class="glyphicon glyphicon-list-alt"></span> 订单管理</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="${pageContext.request.contextPath}/user/exit"><span class="glyphicon glyphicon-log-out"></span> 退出登录</a></li>
                                </ul>
                            </li>
                        </c:if>


                        <li>
                            <a class="font1" href="${pageContext.request.contextPath}/cart.jsp"><span
                                    class="glyphicon glyphicon-shopping-cart"></span>&nbsp;购物车</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
    <!--导航条-->
    <nav class="navbar navbar-inverse ">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/indexServlet">商城首页</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <script>
                    $(function (){
                        $.post(
                            "${pageContext.request.contextPath}/itemCatServlet",
                            function (data){
                                console.log(data)
                                //console.log(typeof data)
                                //
                                var $ul= $(".wz-nav");
                                var str ="";

                                for(var i in data){
                                    // console.log(data[i].name)
                                    str +='<li><a href="${pageContext.request.contextPath}/item/queryPageByCid?cid='+data[i].cid+'&p=1">'+data[i].name+'</a></li>'

                                }
                                //console.log(str)
                                //
                                $ul.html(str);

                            }
                        );

                        //给搜索按钮绑定点击事件
                        $("#btn-search").click(function () {
                            let keyword = $.trim($("#keyword").val());
                            console.log(keyword+":"+keyword.length);
                            //如果有关键字才请求
                            if(keyword.length>0){
                                location.href="${pageContext.request.contextPath}/item/search?keyword="+keyword+"&p=1";
                            }
                        });

                        //定义一个方法 根据 参数名 key 获取 对应的值
                        //function getParamValue( key){

                            //console.log(key);
                            //获取参数字符串 ?keyword=手办&p=1
                            //let search = location.search;
                            //console.log(search);
                            //search= search.substring(1);
                            //console.log(search);
                            //从& 切割
                            //let arr = search.split("&");
                            //遍历数组
                            //for (let i=0;i<arr.length;i++){
                             //   console.log([i]);
                                //根据=切割
                             //   let strings = arr[i].split("=");
                                //判断 如果第一个元素的值 是否和 key 传进来的值相等
                            //   if (key == strings[0]){
                                    //如果相等 第二个元素的值就是我们要的参数值
                                    //直接返回参数值
                                 //   return strings[1];
                              //  }
                           // }
                            //如果没有返回false
                         //   return false;
                       // }

                        //调用方法
                        //let paramValue =decodeURI(getParamValue("keyword"));
                        //getParamValue(paramValue);
                       //if (paramValue) {
                       //    $("#keyword").val(decodeURI(paramValue));

                       //}

                        let uri = location.href;
                        console.log(uri);
                        if(uri.indexOf("keyword")==-1){return;}
                        let params = location.search;
                        console.log(params);
                        let index = params.indexOf("=");
                        let index2 = params.indexOf("&");
                        console.log(index);
                        console.log(index2);
                        if(index>-1){
                            let str = params.substring(index+1,index2);
                            $("#keyword").val(decodeURI(str));
                        }



                    });
                </script>
                <ul class=" nav navbar-nav wz-nav font2">
                    <li>
                        <a href="">分类一</a>
                    </li>
                    <li>
                        <a href="">分类二</a>
                    </li>
                    <li>
                        <a href="">分类三</a>
                    </li>
                    <li>
                        <a href="">分类四</a>
                    </li>
                    <li>
                        <a href="">分类五</a>
                    </li>
                </ul>
                <form class="hidden-md  hidden-lg navbar-right " role="search">
                    <div class="  form-group">
                        <input type="text" class="form-control" placeholder="请搜索商品..">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</header>