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
	<style type="text/css">
	  table tr td {
	           vertical-align: middle;
               text-align: center;
	  }
	
	</style>

  </head>
  
  <body>
  	<div class="container" style="width:100%;">
  		<div class="panel panel-success">
  			<div class="panel-heading">商品信息</div>
  			<div class="panel-body">
  				<form action="ProductServlet?action=queryByKey" method="post">
  					<a href="ProductServlet?action=before_add" class="btn btn-sm btn-success">添加商品</a>
  					<a href="StockServlet?action=before_add" class="btn btn-sm btn-success">进货</a>
  					<input type="text" name="key" placeholder="请输入商品条码/名称/类别" style="margin-left:278px;"/>
  					<input type="submit" class="btn btn-sm btn-success" value="查询"/>
  					<a href="ProductServlet?action=list" class="btn btn-sm btn-success" style="float:right;">返回全部</a>
  				</form>
  				<hr/>
  				<table class="table table-bordered" style="text-align:center;">
			 		<tr>
			 			<td>序号</td>
			 			<td>商品条码</td>
			 			<td>商品二维码</td>
			 			<td>商品名称</td>
			 			<td>种类</td>
			 			<td>规格等级</td>
			 			<td>单位</td>
			 			<td>报警数量</td>
			 			<td>零售价</td>
			 			<td>会员价</td>
			 			<td>库存量</td>
						<c:if test="${sessionScope.user.urole==1}"><td></td></c:if>
						<c:if test="${sessionScope.user.urole==1}"><td></td></c:if>
					</tr>
<%
	int i=0;
	int page1=1;
	if(request.getAttribute("curPage")!=null){
		page1=(Integer)request.getAttribute("curPage");
	}
%>
			 		<c:forEach items="${list}" var="product">
			 			<tr>
			 				<%i++; %>
			 				<td style="font-weight:bold;color:#f00;"><%=(page1-1)*7+i %></td>
			 				<td>${product.pid}</td>
			 				<td><img alt="商品二维码" src="<%=basePath %>/${product.pic}"></td>
			 				<td>${product.pname}</td>
			 				<td>${product.category.cname}</td>
			 				<td>${product.pspec}</td>
			 				<td>${product.unit.uname}</td>
			 				<td>${product.pminNumber}</td>
			 				<td>${product.salePrice}</td>
			 				<td>${product.vipPrice}</td>
			 				<td><div style="float:left;">${product.pamount}</div><c:if test="${product.pminNumber>product.pamount}"><div style="font-size:12px;color:#f00;margin-top:3px;">库存不足</div></c:if></td>
							<c:if test="${sessionScope.user.urole==1}"><td><a href="ProductServlet?action=update&pid=${product.pid}">修改</a></td></c:if>


							<c:if test="${sessionScope.user.urole==1}"><td><a href="ProductServlet?action=delete&pid=${product.pid}&page=${curPage}" onclick="if(confirm('确认删除吗？')==false)return false;">删除</a></td></c:if>
			 			</tr>
			 		</c:forEach>


				</table>
				<div style="float:left;">共${count}条记录</div>
				<div id="page" style="margin-left:440px;">
					<c:if test="${curPage==1}">
						首页 上一页
					</c:if>
					<c:if test="${curPage!=1}">
						<a href="ProductServlet?action=list">首页</a>	
						<a href="ProductServlet?action=list&page=${curPage-1}">上一页</a>
					</c:if>
					<c:if test="${curPage==pageCount}">
						下一页 尾页
					</c:if>
					<c:if test="${curPage!=pageCount}">
						<a href="ProductServlet?action=list&page=${curPage+1}">下一页</a>
						<a href="ProductrServlet?action=list&page=${pageCount}">尾页</a>
					</c:if>
					<div style="float:right;margin-left:355px;">第${curPage}/${pageCount}页</div>
				</div>
				<div id="flag" style="float:right;color:#fff;">${showPage}</div>
  			</div>
  		</div>
  	</div>
  </body>
</html>
