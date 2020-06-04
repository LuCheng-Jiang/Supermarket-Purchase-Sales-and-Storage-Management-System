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
    
    <title></title>
	<script type="text/javascript" src="js/list.js"></script>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/list.css"/>
    
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
  	<div class="container" style="width:100%;">
  		<div class="panel panel-success">
  			<div class="panel-heading">用户信息</div>
  			<div class="panel-body">
  				<form action="UserServlet?action=query_object" method="post">
  					<a href="user/add.jsp" class="btn btn-sm btn-success">添加用户</a>
  					<input type="text" name="uid" placeholder="请输入要查询的用户名" style="margin-left:350px;"/>
  					<input type="submit" class="btn btn-sm btn-success" value="查询"/>
  					<a href="UserServlet?action=list" class="btn btn-sm btn-success" style="float:right;">返回全部</a>
  				</form>
  				<hr/>
  				<table class="table table-bordered" style="text-align:center;">
			 		<tr>
			 			<td>序号</td>
			 			<td>用户名</td>
			 			<td>密码</td>
			 			<td>真实姓名</td>
			 			<td>角色</td>
			 			<td></td>
			 			<td></td>
			 		</tr>
<%
	int i=0;
	int page1=1;
	if(request.getAttribute("curPage")!=null){
		page1=(Integer)request.getAttribute("curPage");
	}
%>
			 		<c:forEach items="${list}" var="user">
			 			<tr>
			 				<%i++; %>
			 				<td style="font-weight:bold;color:#f00;"><%=(page1-1)*7+i %></td>
			 				<td>${user.uid}</td>
			 				<td>${user.upassword}</td>
			 				<td>${user.uname}</td>
			 				<td>
				 				<c:if test="${user.urole==0}">收银员</c:if>
								<c:if test="${user.urole==1}">管理员</c:if>
							</td>
			 				<td><a href="UserServlet?action=update&uid=${user.uid}">修改</a></td>
			 				<td><a href="UserServlet?action=delete&uid=${user.uid}&page=${curPage}" onclick="if(confirm('确认删除吗？')==false)return false;">删除</a></td>
			 			</tr>
			 		</c:forEach>
				</table>
				<div style="float:left;">共${count}条记录</div>
				<div id="page" style="margin-left:440px;">
					<c:if test="${curPage==1}">
						首页 上一页
					</c:if>
					<c:if test="${curPage!=1}">
						<a href="UserServlet?action=list">首页</a>	
						<a href="UserServlet?action=list&page=${curPage-1}">上一页</a>
					</c:if>
					<c:if test="${curPage==pageCount}">
						下一页 尾页
					</c:if>
					<c:if test="${curPage!=pageCount}">
						<a href="UserServlet?action=list&page=${curPage+1}">下一页</a>
						<a href="UserServlet?action=list&page=${pageCount}">尾页</a>
					</c:if>
					<div style="float:right;margin-left:355px;">第${curPage}/${pageCount}页</div>
				</div>
				<div id="flag" style="float:right;color:#fff;">${showPage}</div>
  			</div>
  		</div>
  	</div>
  </body>
</html>
