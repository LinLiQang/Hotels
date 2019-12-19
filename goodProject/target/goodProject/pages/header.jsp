<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

<!-- 页面头部 -->
<header class="main-header">


	<!-- Logo -->
	<a href="${pageContext.request.contextPath}/pages/home.jsp" class="logo">
		<!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>后台</b></span>
		<!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>后台</b>管理系统</span>
	</a>


	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<%--<% String loginName = request.getSession().getAttribute("loginName").toString();%>--%>
						<span class="hidden-xs">欢迎回来，<%--<%=loginName %>--%>管理员</span>
					</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header">
							<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg" class="img-circle" alt="User Image">

							<p>
								管理员 <%---- <%=loginName %>--%>
								<%--<small>最后登录 11:20AM</small>--%>
							</p>
						</li>

						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
							</div>
							<div class="pull-right">
								<a href="${pageContext.request.contextPath}/user/quit" class="btn btn-default btn-flat">退出</a>
							</div>
						</li>
					</ul>
				</li>

			</ul>
		</div>
	</nav>
</header>
<!-- 页面头部 /-->