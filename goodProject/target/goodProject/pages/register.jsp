<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>注册</title>

    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">

    <script>
        //校验用户名
        function checkUsername(){
            var username = $("#username").val();
            var reg_username = /^\w{6,20}$/;
            var flag = reg_username.test(username);
            if(flag){
                $("#username").css("border","2px solid green");
                flag = true;
            }else{
                $("#username").css("border","2px solid red");
            }
            return flag;
        }

        function checkName(){
            var name = $("#name").val();
            var flag = false;
            if(name){
                $("#name").css("border","2px solid green");
                flag = true;
            }else{
                $("#name").css("border","2px solid red");
            }
            return flag;
        }

        //校验手机格式
        function checkTel(){
            var tel = $("#tel").val();
            var reg_tel = /^1(3|4|5|7|8)\d{9}$/;
            var flag = reg_tel.test(tel);
            if(flag){
                $("#tel").css("border","2px solid green");
                flag = true;
            }else{
                $("#tel").css("border","2px solid red");
            }
            return flag;
        }

        //校验密码
        function checkPassword(){
            var password = $("#password").val();
            var reg_password = /^\w{6,20}$/;
            var flag = reg_password.test(password);
            if(flag){
                $("#password").css("border","2px solid green");
                flag = true;
            }else{
                $("#password").css("border","2px solid red");
            }
            return flag;
        }

        //校验确认密码
        function checkPasswordAgain(){
            var checkPassword = $("#checkPassword").val();
            var password = $("#password").val();
            var flag = true;
            if(checkPassword != password){
                $("#checkPassword").css("border","2px solid red");
                flag = false;
            }else{
                $("#checkPassword").css("border","2px solid green");
            }
            return flag;
        }

        $(function(){
            $("#sub").click(function(){
                if(checkPassword() && checkName() && checkTel() && checkUsername() && checkPasswordAgain()){
                    $.post("${pageContext.request.contextPath}/user/add",$("form").serialize(),function(data){
                        if(data.flag){
                            alert(data.msg);
                            location.href="login.jsp";
                        }else{
                            alert(data.msg);
                        }
                    },'json');
                }else{
                    alert("数据格式不正确！");
                }
            });
            $("#username").blur(checkUsername);
            $("#tel").blur(checkTel);
            $("#name").blur(checkName);
            $("#password").blur(checkPassword);
            $("#checkPassword").blur(checkPasswordAgain);
        });

    </script>

</head>

<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">

        <h3>后台管理系统</h3>

    </div>

    <div class="register-box-body">
        <p class="login-box-msg">新用户注册</p>

        <form method="post" id="registerForm">
            <div class="form-group has-feedback">
                <input id="username" type="text" class="form-control" placeholder="用户名" name="username">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="tel" type="text" class="form-control" placeholder="手机号" name="tel">
                <span class="glyphicon glyphicon-phone form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="name" type="text" class="form-control" placeholder="真实姓名" name="name">
                <span class="glyphicon glyphicon-globe form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <select class="form-control select2" style="width: 100%;" name="sex">
                    <option value="男" selected="selected">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="密码" id="password" name="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="确认密码" id="checkPassword" name="checkPassword">
                <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox"> 我同意 <a href="#">协议</a>
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button id="sub" type="button" class="btn btn-primary btn-block btn-flat">注册</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <a href="./login.jsp" class="text-center">我有账号，现在就去登录</a>
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 2.2.3 -->
<!-- Bootstrap 3.3.6 -->
<!-- iCheck -->
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