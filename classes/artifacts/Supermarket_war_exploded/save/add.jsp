<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  			<div class="panel-heading">充值信息</div>
  			<div class="panel-body">
  				<form class="form-horizontal" action="SaveRecordServlet" method="post">
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">会员卡号:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control"  placeholder="请填写会员卡号" name="vid" />
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">充值金额:</label>
				    <div class="col-sm-10">
				      <input type="text" pattern="^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$" class="form-control"  placeholder="请填写充值金额" name="smoney"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">充值员帐号:</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control"  readonly="readonly" name="uid" value="${sessionScope.user.uid }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-success">充值</button>
				      
				    </div>
				  </div>
				</form>
  			</div>
  		</div>
  	</div>
  </body>
</html>
