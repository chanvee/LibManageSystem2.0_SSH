<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Error Page</title>
  </head>
  
  <body>
    
    <div class="container">
		<h4>555，似乎遇到问题了.....</h4>
        	您可以尝试 <a href="${pageContext.request.contextPath}/login.do?flag=logout">重新登录</a>
    </div> <!-- /container -->
  </body>
</html>
