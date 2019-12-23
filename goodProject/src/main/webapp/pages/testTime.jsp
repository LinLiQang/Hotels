<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>添加商品</title>
	<meta name="description" content="AdminLTE2定制版">
	<meta name="keywords" content="AdminLTE2定制版">
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
		$(function(){
			$("#sub").click(function(){
				$.post("${pageContext.request.contextPath}/room/findAllRoomByType",$("form").serialize(),function(data){
					if(data.flag){
						alert(data.msg);
						location.href="${pageContext.request.contextPath}/orders/findAll";
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
				<small>订单列表</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/pages/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
				<li><a href="${pageContext.request.contextPath}/orders/findAll">订单管理</a></li>
				<li class="active">订单列表</li>
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

						<div class="col-md-2 title">房间类型</div>
						<div class="col-md-4 data">
							<select class="form-control select2" style="width: 100%"
									name="type">
								<option value="0" selected="selected">单人房</option>
								<option value="2">双人房</option>
								<option value="3">豪华房</option>
								<option value="4">家庭房</option>
							</select>
						</div>


						<div class="col-md-2 title">开始时间</div>
						<div class="col-md-4 data">
							<div class="input-group date">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right" id="startTime" name="startTime">
							</div>
						</div>

						<div class="col-md-2 title">结束时间</div>
						<div class="col-md-4 data">
							<div class="input-group date">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right" id="endTime" name="endTime">
							</div>
						</div>

                        <div class="col-md-2 title">测试时间</div>
                        <div class="col-md-4 data">
							<div class="input-group date">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
                                <input type="text" class="form-control pull-right" id="testTime" name="testTime">
                            </div>
                        </div>


					</div>
				</div>

				<!--工具栏-->
				<div class="box-tools text-center">
					<button type="button" class="btn bg-maroon" id="sub">添加</button>
					<button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
				</div>
				<!--工具栏/-->
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
		$('#startTime').datetimepicker({
			format : "yyyy-mm-dd",
			autoclose : true,
			todayBtn : true,
			language : "zh-CN"
		});
	});

	$(document).ready(function() {
		// 激活导航位置
		setSidebarActive("order-manage");
		$("#starTime").datetimepicker({
			format : "yyyy-mm-dd",
		});
	});

	$(document).ready(function() {
		$('#endTime').datetimepicker({
			format : "yyyy-mm-dd",
			autoclose : true,
			todayBtn : true,
			language : "zh-CN"
		});
	});

	$(document).ready(function() {
		// 激活导航位置
		setSidebarActive("order-manage");
		$("#endTime").datetimepicker({
			format : "yyyy-mm-dd",
		});
	});

	$(document).ready(function() {
		$('#datepicker-a6').datepicker({
			autoclose: true,
			language: 'zh-CN'
		});
	});

	$(document).ready(function() {
		$('#testTime').datetimepicker({
			format : "yyyy-mm-dd",
			autoclose : true,
			todayBtn : true,
			language : "zh-CN"
		});
	});

	$(document).ready(function() {
		// 激活导航位置
		setSidebarActive("order-manage");
		$("#testTime").datetimepicker({
			format : "yyyy-mm-dd",
		});
	});


	$(document).ready(function() {
		// 激活导航位置
		setSidebarActive("order-manage");
	});
</script>
</body>

</html>