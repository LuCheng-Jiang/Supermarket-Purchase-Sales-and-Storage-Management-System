<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
  	<c:if test="${empty sessionScope.user}">
  		<c:redirect url="login.html"></c:redirect>
  	</c:if>
  	<div class="container" style="width:100%;">
  		<div class="panel panel-success">
  			<div class="panel-heading">收银</div>
  			<div class="panel-body">
  			
  				<form action="CheckoutServlet?action=list" method="post">
  					<input type="text" name="addPid" placeholder="请输入商品条码" style="margin-left:350px;"/>
  					<input type="submit" class="btn btn-sm btn-success" value="添加商品"/>
  					<input type="text" pattern="^[0-9]*[1-9][0-9]*$" name="addAmount" placeholder="请填写数量" style="width:100px;margin-left:50px;"/>
  				</form>
  				
  				<form action="CheckoutServlet?action=vip" method="post" style="font-weight:bold;">
  					<div style="position:absolute;">会员卡号：<input type="text" name="vid" value="${vip.vid }"/></div>
  					<div style="position:absolute;margin-left:250px;"><input type="submit" value="确定" class="btn btn-sm btn-success"></div>
  					<div style="position:absolute;margin-left:350px;margin-top:3px;">姓名：${vip.vname}</div>
  					<div style="position:absolute;margin-left:500px;margin-top:3px;">积分：${vip.vscore }</div>
  					<div style="position:absolute;margin-left:650px;margin-top:3px;">余额：${vip.vbalance }</div>
  					<a class="btn btn-sm btn-success" style="float:right;" href="CheckoutServlet?action=balance">余额结账</a>
  				</form>
  				
				<form action="CheckoutServlet?action=cash" method="post" style="font-weight:bold;margin-top:53px;font-size:20px;">
  					<div style="position:absolute;margin-top:3px;">共有商品种数：${fn:length(list)}</div>
<%
	float summary=0.00f;
	Map<String,Float> sum=new HashMap<String, Float>();
	if(session.getAttribute("sum")!=null){
		sum=(HashMap<String, Float>) session.getAttribute("sum");
	}
	Set<String> set=sum.keySet();
	for(String s:set){
		summary+=sum.get(s);
	}
	summary=Float.parseFloat(new DecimalFormat("0.00").format(summary));
%>
  					<div style="position:absolute;margin-left:220px;margin-top:3px;">共计：￥<%=summary %></div>
  					<div style="position:absolute;margin-left:420px;margin-top:3px;">找零：￥${change }</div>
  					<div style="position:absolute;margin-left:620px;margin-top:3px;">实收：￥<input type="text" pattern="^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$" name="cash" style="width:150px;"/></div>
  					<div style="float:right;"><input type="submit" value="现金结账" class="btn btn-sm btn-success"></div>
  				</form>
  				
  				<hr style="margin-top:100px;"/>
  				<table class="table table-bordered" style="text-align:center;">
			 		<tr>
			 			<td>商品条码</td>
			 			<td>商品名称</td>
			 			<td>规格等级</td>
			 			<td>单位</td>
			 			<td>当前库存</td>
			 			<td>会员价</td>
			 			<td>零售价</td>
			 			<td>数量</td>
			 			<td>金额</td>
			 			<td></td>
			 		</tr>
			 		<c:forEach items="${sessionScope.list}" var="product">
			 			<tr>
			 				<td>${product.pid}</td>
			 				<td>${product.pname}</td>
			 				<td>${product.pspec}</td>
			 				<td>${product.unit.uname}</td>
			 				<td><div style="float:left;">${product.pamount}</div><c:if test="${product.pminNumber>product.pamount}"><div style="font-size:12px;color:#f00;margin-top:3px;">库存不足</div></c:if></td>
			 				<td>${product.vipPrice}</td>
			 				<td>${product.salePrice}</td>
			 				<td>${sessionScope.map[product.pid]}</td>
			 				<td>${sessionScope.sum[product.pid]}</td>
			 				<td><a href="CheckoutServlet?action=delete&pid=${product.pid}" onclick="if(confirm('确认删除吗？')==false)return false;">删除</a></td>
			 			</tr>
			 		</c:forEach>
				</table>			
  			</div>
  		</div>
  	</div>
  </body>
</html>
