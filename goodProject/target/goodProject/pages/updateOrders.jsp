<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑订单信息</title>
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


    <script type="text/javascript">
        $(function(){
            $("#sub").click(function(){
                $.post("${pageContext.request.contextPath}/orders/updateOrders",$("form").serialize(),function(data){
                    if(data.flag == true){
                        alert(data.msg);
                        location.href="${pageContext.request.contextPath}/orders/findById?oid=${orders.oid}";
                    }else{
                        alert(data.msg);
                    }
                },'json');
            });
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
                    订单管理
                    <small>编辑订单</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/pages/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/orders/findAll">订单列表</a></li>
                    <li class="active">编辑订单</li>
                </ol>
            </section>
            <!-- 内容头部 /-->

            <form method="post">
                <!-- 正文区域 -->
                <section class="content">

                    <!--订单信息-->
                    <div class="panel panel-default">
                        <div class="panel-heading">订单信息</div>
                        <div class="row data-type">

                            <div class="col-md-2 title">订单ID</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" value="${orders.oid}" name="oid" readonly="readonly">
                            </div>

                            <input type="hidden" value="${orders.user.uid}" name="uid">

                            <div class="col-md-2 title">订单状态</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%"
                                        name="ordersStatus">
                                    <c:if test="${orders.ordersStatus == 0}">
                                        <option value="0" selected="selected">已取消</option>
                                        <option value="1">正在进行</option>
                                        <option value="2">已完成</option>
                                        <option value="3">已评论</option>
                                    </c:if>
                                    <c:if test="${orders.ordersStatus == 1}">
                                        <option value="0">已取消</option>
                                        <option value="1" selected="selected">正在进行</option>
                                        <option value="2">已完成</option>
                                        <option value="3">已评论</option>
                                    </c:if>
                                    <c:if test="${orders.ordersStatus == 2}">
                                        <option value="0">已取消</option>
                                        <option value="1">正在进行</option>
                                        <option value="2" selected="selected">已完成</option>
                                        <option value="3">已评论</option>
                                    </c:if>
                                    <c:if test="${orders.ordersStatus == 3}">
                                        <option value="0">已取消</option>
                                        <option value="1">正在进行</option>
                                        <option value="2">已完成</option>
                                        <option value="3" selected="selected">已评论</option>
                                    </c:if>

                                </select>
                            </div>

                            <div class="col-md-2 title">用户姓名</div>
                            <div class="col-md-4 data">
                                <input type="text" id="username" class="form-control" value="${orders.user.name}" readonly="readonly">
                            </div>

                            <div class="col-md-2 title">客房ID</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%"
                                        name="rid" id="select">
                                    <c:forEach items="${rooms}" var="room">
                                        <c:if test="${room.rid == orders.room.rid}">
                                            <option value="${room.rid}" selected="selected">${room.rid}</option>
                                        </c:if>
                                        <c:if test="${room.rid != orders.room.rid}">
                                            <option value="${room.rid}">${room.rid}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2 title">订单开始时间</div>
                            <div class="col-md-4 data">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" class="form-control pull-right"
                                           id="startTime" value="${orders.startTimeStr}" name="startTime">
                                </div>
                            </div>

                            <div class="col-md-2 title">订单结束时间</div>
                            <div class="col-md-4 data">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" class="form-control pull-right"
                                           id="endTime" value="${orders.endTimeStr}" name="endTime">
                                </div>
                            </div>

                            <div class="col-md-2 title">订单价格(￥)</div>
                            <div class="col-md-4 data">
                                <input type="text" id="price" class="form-control"value="${orders.ordersPrice}" name="ordersPrice" readonly>
                            </div>


                        </div>
                    </div>

                    <!--工具栏-->
                    <div class="box-tools text-center">
                        <button type="button" class="btn bg-maroon" id="sub">修改</button>
                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>
                    <!--工具栏/-->
                    <!--订单信息/-->


                </section>
                <!-- 正文区域 /-->
            </form>
        </div>
        <!-- 内容区域 /-->

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

        $(document).ready(function() {
            $('#startTime').datepicker({
                format : "yyyy-mm-dd",
                autoclose : true,
                todayBtn : true,
                language : "zh-CN"
            });
        });

        $(document).ready(function() {
            // 激活导航位置
            setSidebarActive("order-manage");
            $("#starTime").datepicker({
                format : "yyyy-mm-dd",
            });
        });

        $(document).ready(function() {
            $('#endTime').datepicker({
                format : "yyyy-mm-dd",
                autoclose : true,
                todayBtn : true,
                language : "zh-CN"
            });
        });

        $(document).ready(function() {
            // 激活导航位置
            setSidebarActive("order-manage");
            $("#endTime").datepicker({
                format : "yyyy-mm-dd",
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

        //自动改变价格
        $(document).ready(function(){
            $('#select').change(function(){
                var thisRid = $('#select').children('option:selected').val();
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/room/findPrice",
                    data: {rid:thisRid},
                    dataType: "json",
                    success: function(data){
                        $('#price').val(data.price);
                    }
                });
            });
        });

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