<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>添加客房</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />

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
        $(function(){
            $("#sub").click(function(){
                $.ajax({
                    async:false,
                    url: '${pageContext.request.contextPath}/room/add',
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#roomForm')[0]),
                    processData: false,
                    dataType: 'json',
                    contentType: false,
                    success:function(data){
                        if(data.flag == true){
                            alert(data.msg);
                            window.location.href= "pages/home.jsp";
                        }else{
                            alert(data.msg);
                            window.location.href= "pages/home.jsp";
                        }
                    }
                });
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
                    客房管理
                    <small>客房列表</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/pages/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/room/findAll">客房管理</a></li>
                    <li class="active">添加客房</li>
                </ol>
            </section>
            <!-- 内容头部 /-->

            <form id="roomForm" method="post" enctype="multipart/form-data">
                <!-- 正文区域 -->
                <section class="content">

                    <!--客房信息-->
                    <div class="panel panel-default">
                        <div class="panel-heading">添加客房</div>
                        <div class="row data-type">

                            <div class="col-md-2 title">客房ID</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" placeholder="客房ID" value="" name="rid">
                            </div>

                            <div class="col-md-2 title">客房价格</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" placeholder="客房价格" value="" name="roomPrice">
                            </div>

                            <div class="col-md-2 title">客房类型</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%"
                                        name="type">
                                    <option value="1" selected="selected">单人房</option>
                                    <option value="2">双人房</option>
                                    <option value="3">豪华房</option>
                                    <option value="4">家庭房</option>
                                </select>
                            </div>

                            <div class="col-md-2 title">客房状态</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%"
                                        name="roomStatus">
                                    <option value="0" selected="selected">正在维修</option>
                                    <option value="1">正在运营</option>
                                </select>
                            </div>

                            <div class="col-md-2 title">客房图片一</div>
                            <div class="col-md-4 data">
                                <input type="file" class="form-control" name="firstImg"/>
                            </div>

                            <div class="col-md-2 title">客房图片二</div>
                            <div class="col-md-4 data">
                                <input type="file" class="form-control" name="secondImg"/>
                            </div>

                            <div class="col-md-2 title">客房图片三</div>
                            <div class="col-md-4 data">
                                <input type="file" class="form-control" name="thirdImg"/>
                            </div>

                            <div class="col-md-2 title">客房图片四</div>
                            <div class="col-md-4 data">
                                <input type="file" class="form-control" name="forthImg"/>
                            </div>

                            <div class="col-md-2 title">客房图片五</div>
                            <div class="col-md-4 data">
                                <input type="file" class="form-control" name="fifthImg"/>
                            </div>

                            <div class="col-md-2 title"></div>
                            <div class="col-md-4 data"></div>

                            <div class="col-md-2 title rowHeight2x">客房简介</div>
                            <div class="col-md-10 data rowHeight2x">
                                <textarea class="form-control" rows="3" placeholder="客房简介" name="introduction"></textarea>
                            </div>

                            <div class="col-md-2 title rowHeight2x">客房描述</div>
                            <div class="col-md-10 data rowHeight2x">
                                <textarea class="form-control" rows="3" placeholder="客房描述" name="detail"></textarea>
                            </div>


                        </div>
                    </div>

                    <!--工具栏-->
                    <div class="box-tools text-center">
                        <button type="submit" class="btn bg-maroon" id="sub">添加</button>
                        <button type="button" class="btn bg-default" onclick="location.href='${pageContext.request.contextPath}/room/findAll'">返回</button>
                    </div>
                    <!--工具栏/-->
                    <!--客房信息/-->
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