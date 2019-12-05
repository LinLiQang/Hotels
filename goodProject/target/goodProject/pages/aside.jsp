<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

<aside class="main-sidebar">

	<section class="sidebar">

		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<%--<% String loginName = request.getSession().getAttribute("loginName").toString(); %>--%>
				<p><%--<%=loginName%>--%>管理员</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<ul class="sidebar-menu">

			<li id="admin-index"><a href="${pageContext.request.contextPath}/pages/home.jsp"><i class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview">
				<a href="#">
					<i class="fa fa-folder"></i> <span>评论管理</span>
					<span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
				</a>
				<ul class="treeview-menu">

					<li id="commentsList">
						<a href="${pageContext.request.contextPath}/comments/findAll?page=1&size=5">
							<i class="fa fa-circle-o"></i> 评论列表
						</a>
					</li>

				</ul>
			</li>

			<li class="treeview">
				<a href="#">
					<i class="fa fa-folder"></i> <span>用户管理</span>
					<span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
				</a>
				<ul class="treeview-menu">

					<li id="userList">
						<a href="${pageContext.request.contextPath}/user/findAll?page=1&size=5">
							<i class="fa fa-circle-o"></i> 用户列表
						</a>
					</li>

				</ul>
			</li>


			<li class="treeview">
				<a href="#">
					<i class="fa fa-folder"></i> <span>客房管理</span>
					<span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
				</a>
				<ul class="treeview-menu">

					<li id="roomList">
						<a href="${pageContext.request.contextPath}/room/findAll?page=1&size=5">
							<i class="fa fa-circle-o"></i> 客房列表
						</a>
					</li>

				</ul>
			</li>

			<li class="treeview">
				<a href="#">
					<i class="fa fa-folder"></i> <span>订单管理</span>
					<span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
				</a>
				<ul class="treeview-menu">

					<li id="ordersList">
						<a href="${pageContext.request.contextPath}/orders/findAll">
							<i class="fa fa-circle-o"></i> 订单列表
						</a>
					</li>

				</ul>
			</li>
		</ul>
	</section>
</aside>