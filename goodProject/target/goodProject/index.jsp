<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<html>
<body>
    <%--跳转至登录页面--%>
    <% response.sendRedirect(request.getContextPath()+"/pages/login.jsp");%>
</body>
</html>
