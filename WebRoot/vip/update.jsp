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
  			<div class="panel-heading">类别信息</div>
  			<div class="panel-body">
  				<form class="form-horizontal" action="VipServlet?action=do_update" method="post">
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">会员卡号:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="vid" readonly="readonly" value="${vip.vid}"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">姓名:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="vname" value="${vip.vname}"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">电话:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="vphone" value="${vip.vphone}"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">生日:</label>
				    <div class="col-sm-10">
				      <input type="date" class="form-control" name="vbirthday" value="${vip.vbirthday}" pattern="yyyy-MM-dd"/>
				    </div>
				  </div>
				 <div class="form-group">
				    <label  class="col-sm-2 control-label">积分率:</label>
				    <div class="col-sm-10">
				      <select class="form-control"  name="vrate">
				      	<c:set var="vrate"> <c:out value="${vip.vrate}" /> </c:set>
				      	<option value="0.1" <c:if test="${vrate=='0.1'}">selected="selected"</c:if> >0.1</option>
 						<option value="0.05" <c:if test="${vrate=='0.05'}">selected="selected"</c:if>  >0.05</option>
				      </select>
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
