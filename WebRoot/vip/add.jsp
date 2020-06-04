<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  			<div class="panel-heading">会员信息</div>
  			<div class="panel-body">
  				<form class="form-horizontal" action="VipServlet?action=add" method="post">
  				  <div class="form-group">
				    <label  class="col-sm-2 control-label">会员卡号:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control"  placeholder="请填写卡号" name="vid"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">姓名:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control"  placeholder="请填写姓名" name="vname"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">电话:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control"  placeholder="请填写电话" name="vphone"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">生日:</label>
				    <div class="col-sm-10">
				      <input type="date" class="form-control" name="vbirthday" value="<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/>"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">积分率:</label>
				    <div class="col-sm-10">
				      <select class="form-control"  name="vrate">
				      	<option value="0.1">0.1</option>
				      	<option value="0.05">0.05</option>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-success">添加</button>
				      <button type="reset" class="btn btn-success" style="margin-left:50px;">重置</button>
				    </div>
				  </div>
				</form>
  			</div>
  		</div>
  	</div>
  </body>
</html>
