<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <title>登录</title>

    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">

    <script type="text/javascript">
        function changeCheckCode(img){
            img.src = "${pageContext.request.contextPath}/user/checkCode?"+new Date().getTime();
        }

        $(function(){
            $("#sub").click(function(){
                $.post("${pageContext.request.contextPath}/user/login",$("form").serialize(),function(data){
                    //alert(data.flag);
                    if(data.flag){
                        alert(data.msg);
                        location.href="home.jsp";
                    }else{
                        alert(data.msg);
                        location.href="login.jsp";
                    }
                },'json');
            })
        })
    </script>

</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">

        <h3>后台管理系统</h3>

    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <h4><p class="login-box-msg">登录系统</p></h4>

        <form method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input id="username" type="text" class="form-control" placeholder="用户名" name="username">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="password" type="password" class="form-control" placeholder="密码" name="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="验证码" style="width: 50%" name="checkCode">
                <span style="position: absolute;right: 20px;top:0px"><img id="checkImg" src="${pageContext.request.contextPath}/user/checkCode" alt="" onclick="changeCheckCode(this)"></span>
            </div>
            <br>
            <div class="row">
                <div class="col-xs-4"></div>
                <div class="col-xs-4">
                    <button id="sub" type="button" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <div class="col-xs-4"></div>
                <!-- /.col -->
            </div>
        </form>

        <a href="${pageContext.request.contextPath}/pages/findPassword.jsp">忘记密码</a>
        <a href="${pageContext.request.contextPath}/pages/register.jsp" class="text-center" style="float: right">新用户注册</a>
    </div>

</div>

<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>
