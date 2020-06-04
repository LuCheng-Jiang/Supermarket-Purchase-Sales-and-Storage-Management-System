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
    
    <title>My JSP 'update.jsp' starting page</title>
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
  			<div class="panel-heading">商品信息</div>
  			<div class="panel-body">
  				<form class="form-horizontal" action="ProductServlet?action=do_update" method="post">
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">商品条码:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="pid" value="${product.pid }" readonly="readonly"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">商品名称:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="pname" value="${product.pname }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">种类:</label>
				    <div class="col-sm-10">
				      <select class="form-control"  name="cid">
				      	<c:forEach items="${category}" var="category">
				      		<option value="${category.cid}" <c:if test="${category.cid==product.category.cid}">selected="selected"</c:if>>${category.cname }</option>
				      	</c:forEach>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">规格等级:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="pspec" value="${product.pspec }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">单位:</label>
				    <div class="col-sm-10">
				      <select class="form-control"  name="uid">
				      	<c:forEach items="${unit}" var="unit">
				      		<option value="${unit.uid}" <c:if test="${unit.uid==product.unit.uid}">selected="selected"</c:if>>${unit.uname }</option>
				      	</c:forEach>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">报警数量:</label>
				    <div class="col-sm-10">
				      <input type="text" pattern="^[0-9]*[1-9][0-9]*$" class="form-control" name="pminNumber" value="${product.pminNumber }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">零售价:</label>
				    <div class="col-sm-10">
				      <input type="text" pattern="^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$" class="form-control" name="salePrice" value="${product.salePrice }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">会员价:</label>
				    <div class="col-sm-10">
				      <input type="text" pattern="^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$" class="form-control" name="vipPrice" value="${product.vipPrice }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-success">保存</button>
				    </div>
				  </div>
				</form>
  			</div>
  		</div>
  	</div>
  </body>
</html>
