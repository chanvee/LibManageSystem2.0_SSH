<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet"  type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet"  type="text/css" href="css/bootstrap-theme.css">
	<link rel="stylesheet"  type="text/css" href="css/bootstrap-theme.min.css">
	<link rel="stylesheet"  type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/npm.js"></script>	
	<style type="text/css">
	<!--
	body {
 		background-image: url(img/lib_back3.jpg);
 		background-size:100% 120%;
	}
	-->
	.loginbox { width: 400px; height: auto; float: right; background: #fff; margin: 15% 10%; padding: 15px 30px; box-shadow: 2px 4px 6px #666; }
	</style>
	<!--<body style= "width:100%; height:100%;background-image: url(img/lib_back3.jpg)">-->
 </head>
  
  <body>
    <jsp:forward page="/WEB-INF/login.html"></jsp:forward>
  </body>
</html>
