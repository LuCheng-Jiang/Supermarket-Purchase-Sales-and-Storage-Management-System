<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>收银员操作</title>
    <link rel="stylesheet" type="text/css" href="css/admin_style.css"/>
    <script type="text/javascript" src="js/admin.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">




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
                  <a href="CheckoutServlet?action=list" target="iframe">收银 </a>
              </li>


          </ul>
          <a href="login.html" class="exit">退出</a>
      </div>
      <div class="div2">
          <iframe name="iframe" src="CheckoutServlet?action=list"></iframe>
      </div>
      <div class="div3">
          <div class="bottom">收银员：${sessionScope.user.uname }</div>
          <div class="bottom" id="time" style="margin-right:100px;"></div>
      </div>
  </div>
  </body>
</html>
