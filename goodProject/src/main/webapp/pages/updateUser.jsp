<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑用户信息</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">

    <script>
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

        //校验密码
        function checkIDcard(){
            var IDcard = $("#IDcard").val();
            var reg_IDcard = /^[1-9]\d{5}(19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9X]$/;
            var flag = reg_IDcard.test(IDcard);
            if(flag){
                $("#IDcard").css("border","2px solid green");
                flag = true;
            }else{
                $("#IDcard").css("border","2px solid red");
            }
            return flag;
        }

        $(function(){
            $("#sub").click(function(){
                if(checkUsername() && checkTel() && checkName() && checkPassword()){

                    $.ajax({
                        async:false,
                        url: '${pageContext.request.contextPath}/user/updateUser',
                        type: 'POST',
                        cache: false,
                        data: new FormData($('#userForm')[0]),
                        processData: false,
                        dataType: 'json',
                        contentType: false,
                        success:function(data){
                            if(data.flag == true){
                                alert(data.msg);
                                location.href= "${pageContext.request.contextPath}/user/findByUid?uid="+${user.uid};
                            }else{
                                alert(data.msg);
                                window.location.href= "";
                            }
                        }
                    });
                }else{
                    alert("数据格式不对！");
                }
            });
            $("#username").blur(checkUsername);
            $("#tel").blur(checkTel);
            $("#name").blur(checkName);
            $("#password").blur(checkPassword);
            $("#IDcard").blur(checkIDcard);
        });
    </script>

</head>

<body class="hold-transition skin-purple sidebar-mini">

    <div class="wrapper">

        <!-- 页面头部 -->
        <jsp:include page="header.jsp"></jsp:include>
        <!-- 页面头部 /-->

        <!-- 导航侧栏 -->
        <jsp:include page="aside.jsp"></jsp:include>
        <!-- 导航侧栏 /-->

        <!-- 内容区域 -->
        <div class="content-wrapper">

            <!-- 内容头部 -->
            <section class="content-header">
                <h1>
                    用户管理
                    <small>编辑信息</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/pages/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/findAll">用户管理</a></li>
                    <li class="active">编辑信息</li>
                </ol>
            </section>
            <!-- 内容头部 /-->

            <form id="userForm" method="post">
                <!-- 正文区域 -->
                <section class="content">

                    <div class="panel panel-default">
                        <div class="panel-heading">用户信息</div>
                        <div class="row data-type">
                            <input type="hidden" value="${user.uid}" name="uid">

                            <div class="col-md-2 title">用户名</div>
                            <div class="col-md-4 data">
                                <input type="text" id="username" class="form-control" value="${user.username}" name="username">
                            </div>

                            <div class="col-md-2 title">真实姓名</div>
                            <div class="col-md-4 data">
                                <input type="text" id="name" class="form-control" value="${user.name}" name="name">
                            </div>

                            <div class="col-md-2 title">身份证号</div>
                            <div class="col-md-4 data">
                                <input type="text" id="IDcard" class="form-control" value="${user.IDcard}" name="IDcard">
                            </div>

                            <div class="col-md-2 title">性别</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%"
                                        name="sex">
                                    <c:if test="${user.sex.equals('男')}">
                                        <option value="男" selected="selected">男</option>
                                        <option value="女">女</option>
                                        <option value="保密">保密</option>
                                    </c:if>
                                    <c:if test="${user.sex.equals('女')}">
                                        <option value="男">男</option>
                                        <option value="女" selected="selected">女</option>
                                        <option value="保密">保密</option>
                                    </c:if>
                                    <c:if test="${user.sex.equals('保密')}">
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                        <option value="保密" selected="selected">保密</option>
                                    </c:if>
                                </select>
                            </div>

                            <div class="col-md-2 title">密码</div>
                            <div class="col-md-4 data">
                                <input type="text" id="password" class="form-control"value="${user.password}" name="password">
                            </div>

                            <div class="col-md-2 title">手机号</div>
                            <div class="col-md-4 data">
                                <input type="text" id="tel" class="form-control"value="${user.tel}" name="tel">
                            </div>

                            <div class="col-md-2 title">状态</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%"
                                        name="userStatus">
                                    <c:if test="${user.userStatus == 0}">
                                        <option value="0" selected="selected">封禁</option>
                                        <option value="1">普通用户</option>
                                        <option value="2">尊贵会员</option>
                                    </c:if>
                                    <c:if test="${user.userStatus == 1}">
                                        <option value="0">封禁</option>
                                        <option value="1" selected="selected">普通用户</option>
                                        <option value="2">尊贵会员</option>
                                    </c:if>
                                    <c:if test="${user.userStatus == 2}">
                                        <option value="0">封禁</option>
                                        <option value="1">普通用户</option>
                                        <option value="2" selected="selected">尊贵会员</option>
                                    </c:if>
                                </select>
                            </div>

                            <div class="col-md-2 title"></div>
                            <div class="col-md-4 data">
                            </div>

                            <div class="col-md-2 title">头像名称</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" value="${user.avatar}" readonly="readonly"/>
                            </div>

                            <div class="col-md-2 title">修改头像</div>
                            <div class="col-md-4 data">
                                <input type="file" class="form-control" name="photo"/>
                            </div>


                        </div>
                    </div>

                    <!--工具栏-->
                    <div class="box-tools text-center">
                        <button type="button" class="btn bg-maroon" id="sub">修改</button>
                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>
                    <!--工具栏/-->

                </section>
                <!-- 正文区域 /-->
            </form>
        </div>
        <!-- 内容区域 /-->

        <!-- 底部导航 -->
        <footer class="main-footer">
            <div class="pull-right hidden-xs">
                <b>Version</b> 1.0.8
            </div>
            <strong>Copyright &copy; 2014-2017 <a href="http://www.itcast.cn">研究院研发部</a>.</strong> All rights reserved.
        </footer>
        <!-- 底部导航 /-->

    </div>


    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script>
        $(document).ready(function() {
            // 选择框
            $(".select2").select2();

            // WYSIHTML5编辑器
            $(".textarea").wysihtml5({
                locale: 'zh-CN'
            });
        });


        // 设置激活菜单
        function setSidebarActive(tagUri) {
            var liObj = $("#" + tagUri);
            if (liObj.length > 0) {
                liObj.parent().parent().addClass("active");
                liObj.addClass("active");
            }
        }


        $(document).ready(function() {
            $('#datepicker-a3').datepicker({
                autoclose: true,
                language: 'zh-CN'
            });
        });


        $(document).ready(function() {
            $('#datepicker-a6').datepicker({
                autoclose: true,
                language: 'zh-CN'
            });
        });


        $(document).ready(function() {
            // 激活导航位置
            setSidebarActive("order-manage");
        });
    </script>
</body>

</html>