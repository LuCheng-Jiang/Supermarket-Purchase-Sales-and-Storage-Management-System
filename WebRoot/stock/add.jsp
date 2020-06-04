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
    
    <title>My JSP 'add.jsp' starting page</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
    <div class="container">
  		<div class="panel panel-success">
  			<div class="panel-heading">进货信息</div>
  			<div class="panel-body">
  				<form class="form-horizontal" action="StockServlet?action=add" method="post">
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">进货流水号:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control"  placeholder="请填写进货流水号" name="sid" />
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">供货商:</label>
				    <div class="col-sm-10">
				      <select class="form-control"  name="providerId">
				      	<c:forEach items="${provider}" var="provider">
				      		<option value="${provider.pid}">${provider.pname }</option>
				      	</c:forEach>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">商品名称:</label>
				    <div class="col-sm-10">
				      <select class="form-control"  name="productId">
				      	<c:forEach items="${product}" var="product">
				      		<option value="${product.pid}">${product.pname }</option>
				      	</c:forEach>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">进货数量:</label>
				    <div class="col-sm-10">
				      <input type="text" pattern="^[0-9]*[1-9][0-9]*$" class="form-control" placeholder="请填写进货数量" name="samount"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">进货价格:</label>
				    <div class="col-sm-10">
				      <input type="text" pattern="^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$" class="form-control" placeholder="请填写进货价格" name="sprice"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-success">保存</button>
				      <button type="reset" class="btn btn-success" style="margin-left:50px;">重置</button>
				    </div>
				  </div>
				</form>
  			</div>
  		</div>
  	</div>
  </body>
</html>
