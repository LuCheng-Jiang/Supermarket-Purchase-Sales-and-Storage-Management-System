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
  			<div class="panel-heading">商品类别信息</div>
  			<div class="panel-body">
  				<form action="CategoryServlet?action=queryByName" method="post">
  					<a href="category/add.jsp" class="btn btn-sm btn-success">添加类别</a>
  					<input type="text" name="cname" placeholder="请输入要查询的类别名称" style="margin-left:350px;"/>
  					<input type="submit" class="btn btn-sm btn-success" value="查询"/>
  					<a href="CategoryServlet?action=list" class="btn btn-sm btn-success" style="float:right;">返回全部</a>
  				</form>
  				<hr/>
  				<table class="table table-bordered" style="text-align:center;">
			 		<tr>
			 			<td>序号</td>
			 			<td>类别编号</td>
			 			<td>类别名称</td>
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
			 		<c:forEach items="${list}" var="category">
			 			<tr>
			 				<%i++; %>
			 				<td style="font-weight:bold;color:#f00;"><%=(page1-1)*7+i %></td>
			 				<td>${category.cid}</td>
			 				<td>${category.cname}</td>
			 				<td><a href="CategoryServlet?action=update&cid=${category.cid}">修改</a></td>
			 				<td><a href="CategoryServlet?action=delete&cid=${category.cid}" onclick="if(confirm('确认删除吗？')==false)return false;">删除</a></td>
			 			</tr>
			 		</c:forEach>
				</table>
				<div style="float:left;">共${count}条记录</div>
				<div id="page" style="margin-left:440px;">
					<c:if test="${curPage==1}">
						首页 上一页
					</c:if>
					<c:if test="${curPage!=1}">
						<a href="CategoryServlet?action=list">首页</a>	
						<a href="CategoryServlet?action=list&page=${curPage-1}">上一页</a>
					</c:if>
					<c:if test="${curPage==pageCount}">
						下一页 尾页
					</c:if>
					<c:if test="${curPage!=pageCount}">
						<a href="CategoryServlet?action=list&page=${curPage+1}">下一页</a>
						<a href="CategoryServlet?action=list&page=${pageCount}">尾页</a>
					</c:if>
					<div style="float:right;margin-left:355px;">第${curPage}/${pageCount}页</div>
				</div>
				<div id="flag" style="float:right;color:#fff;">${showPage}</div>
  			</div>
  		</div>
  	</div>
  </body>
</html>
