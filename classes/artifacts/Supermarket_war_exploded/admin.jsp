<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员操作</title>
    <link rel="stylesheet" type="text/css" href="css/admin_style.css"/>
    <script type="text/javascript" src="js/admin.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<c:if test="${empty sessionScope.user}">
  		<c:redirect url="login.html"></c:redirect>
  	</c:if>
    <div class="container">
		<div class="div1">
			<ul>

				<li>
					<a href="ProductServlet?action=list" target="iframe">商品管理</a>
				</li>
				<li>
					<a href="ProviderServlet?action=list" target="iframe">供货管理</a>
				</li>
				<li>
					<a href="VipServlet?action=list" target="iframe">会员管理</a>
				</li>
				<li>
					<a href="UserServlet?action=list" target="iframe">员工管理</a>
				</li>
				<li>
					<a href="UnitServlet?action=list" target="iframe">单位管理</a>
				</li>
				<li>
					<a href="CategoryServlet?action=list" target="iframe">类别管理</a>
				</li>
				<li>
					<a href="record/saveRecord.jsp" target="iframe">系统管理</a>
				</li>
			</ul>
			<a href="login.html" class="exit">退出</a>
		</div>
		<div class="div2">
			<iframe name="iframe" src="ProductServlet?action=list"></iframe>
		</div>
		<div class="div3">
			<div class="bottom">管理员：${sessionScope.user.uname }</div>
			<div class="bottom" id="time" style="margin-right:100px;"></div>
		</div>
	</div>
  </body>
</html>
