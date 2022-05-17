<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>酷玩周边商城-注册</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        .error{
            color: #a94442;
        }
        .success{
            color: #4cae4c;
        }
    </style>
</head>

<body>
<!-- 头部 -->
<!--引入公共的头部-->
<jsp:include page="/header.jsp"/>

<div class="container-fluid rg-container">

    <div class="rg-layout ">
        <div class="rg-left col-md-3 pt20">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="col-md-6 mt80">
            <form id="reg-form" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/register">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text" name="username" required class="form-control" id="username" placeholder="请输入用户名">
                        <span id="j-username"></span>
                    </div>
                    <script>
                        //页面初始化
                        $(function (){

                            var isok = false;

                            $("#username").blur(function (){
                                checkUsername();
                            })
                            //封装ajax验证 用户名是否存在的方法
                            function checkUsername(){
                                //定义一个标记变量 验证是否通过
                                //当失去焦点的时候 先获取用户输入的用户名
                                var username = $("#username").val();
                                //console.log(username);
                                //发生Ajax请求
                                $.post(
                                    "${pageContext.request.contextPath}/user/checkUsername",
                                    {"username":username},
                                    function (data){
                                        console.log(data)
                                        //根据返回结果 提示用户
                                        if(data.code==1){
                                            //如果可以注册
                                            $("#j-username").addClass("success").removeClass("error").text(data.rs);
                                            isok=true;
                                        }else {
                                            $("#j-username").addClass("error").removeClass("success").text(data.rs);
                                        }

                                    }
                                )
                                return isok;
                            }

                            //注册表单提交事件
                            $("#reg-form").submit(function () {

                                checkUsername();
                                return isok;
                            });
                        })

                    </script>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" required name="password" id="password"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" required class="form-control" name="email" id="email" placeholder="请输入邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 required control-label">姓名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="tel" class="col-sm-2 required control-label">手机号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="tel" id="tel" placeholder="请输入手机号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">注册</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3 rg-right pt20">
            <p class="pull-right">已有账号?
                <a href="#">立即登录</a>
            </p>
        </div>

    </div>

</div>

</div>
<!--底部-->
<!--引入公共的脚部-->
<jsp:include page="/footer.jsp" />
</body>

</html>