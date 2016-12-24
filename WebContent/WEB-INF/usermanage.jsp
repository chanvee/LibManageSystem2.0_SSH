<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书馆管理系统 - 用户管理</title>
	<!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/application-a07755f5.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="font-awesome/css/font-awesome1.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">图书馆管理系统  </a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">              
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${loginuser.name} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/login.do?flag=logout"><i class="fa fa-fw fa-power-off"></i> 退出系统</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li >
                        <a href="${pageContext.request.contextPath}/manage.do?flag=dashboard"><i class="fa fa-fw fa-dashboard"></i> 仪表盘 </a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/manage.do?flag=usermanage"><i class="fa fa-fw fa-bar-chart-o"></i> 用户管理</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/manage.do?flag=bookmanage"><i class="fa fa-fw fa-table"></i> 图书管理</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/manage.do?flag=borrowmanage"><i class="fa fa-fw fa-edit"></i> 借阅管理</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
    <div id='page-wrapper'>
        <div class='panel panel-default'>
          <div class='panel-heading'>
          <i class='fa fa-fw fa-bar-chart-o icon-large'></i>
            	用户管理
          </div>
          <div class='panel-body filters'>
            <div class='row'>
              <div class='col-md-8'>
                	<h4>查找用户</h4>
              </div>
              <div class='col-md-4'>
                <div class='input-group'>
                  <input class='form-control' placeholder='请输入用户名...' type='text'>
                  <span class='input-group-btn'>
                    <button class='btn' type='button'>
                      <i class='icon-search'></i>
                    </button>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div class='panel-body'>
          <div class='row'>
              <div class='col-md-12'>
                	<h4>用户预览</h4>
              </div>
          </div>
          </div>
          <table class='table'>
            <thead>
               <tr align="center">
                <th style='text-align: center;'>ID</th>
                <th style='text-align: center;'>用户名</th>
                <th style='text-align: center;'>密码</th>
                <th style='text-align: center;'>性别</th>
                <th style='text-align: center;'>邮箱</th>
                <th style='text-align: center;'>等级</th>
                <th style='text-align: center;'>操作</th>
                </tr>
            </thead>                       
            <tbody>  
               <c:forEach items="${userlist}" var="u">                           
     			<tr align="center"><td>${u.id }</td><td>${u.name }</td>
     			<td>${u.password }</td><td>${u.gender }</td>
     			<td>${u.email }</td><td>${u.level }</td>
     			<td class='action'>
                <a class='btn btn-success' data-toggle='tooltip' href='#' title='查看用户'>
                  <i class='icon-zoom-in'></i>
                </a>
                <a class='btn btn-info' href='#' title='编辑用户'>
                   <i class='icon-edit'></i>
                </a>
                <a class='btn btn-danger' href='#' title='删除用户'>
                   <i class='icon-trash'></i>
                </a>
                </td>
     			</tr>  
     		</c:forEach> 
            </tbody>
          </table>
          <ul class='pagination pagination-sm'>
          	<c:if test="${pageNow != 1}">
          	<li>
          	 <a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${pageNow-1}">&laquo;</a>
          	</li>
          	</c:if>
          	<c:if test="${pageNow == 1}">
          	<li class="disabled">
          	 <a href="#">&laquo;</a>
          	</li>
          	</c:if>
          	<c:forEach var="i" begin="1" end="${pageCount}">
          	<c:if test="${i  != pageNow}">
          		<li>
            	<a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${i }">${i }</a>
    			</li>
    		</c:if>
    		<c:if test="${i  == pageNow}">
          		<li class="active">
            	<a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${i }">${i }</a>
    			</li>
    		</c:if>
    		</c:forEach> 
    		<c:if test="${pageNow != pageCount}">
    		<li>
          	 <a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${pageNow+1}">&raquo;</a>
          	</li> 
          	</c:if>
          	<c:if test="${pageNow == pageCount}">
    		<li class="disabled">
          	 <a href="#">&raquo;</a>
          	</li> 
          	</c:if>
           </ul>
          </div>
          
          <div class='panel panel-default grid'>
          <div class='panel-heading'>
          <i class='icon-table icon-large'></i>
            	用户管理
          </div>
          <div class='panel-body filters'>
            <div class='row'>
              <div class='col-md-8'>
                	<h4>查找用户</h4>
              </div>
              <div class='col-md-4'>
                <div class='input-group'>
                  <input class='form-control' placeholder='请输入用户名...' type='text'>
                  <span class='input-group-btn'>
                    <button class='btn' type='button'>
                      <i class='icon-search'></i>
                    </button>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div class='panel-body'>
          <div class='row'>
              <div class='col-md-12'>
                	<h4>用户预览</h4>
              </div>
          </div>
          </div>
          <table class='table'>
            <thead>
               <tr align="center">
                <th style='text-align: center;'>ID</th>
                <th style='text-align: center;'>用户名</th>
                <th style='text-align: center;'>密码</th>
                <th style='text-align: center;'>性别</th>
                <th style='text-align: center;'>邮箱</th>
                <th style='text-align: center;'>等级</th>
                <th style='text-align: center;'>操作</th>
                </tr>
            </thead>                       
            <tbody>  
               <c:forEach items="${userlist}" var="u">                           
     			<tr align="center"><td>${u.id }</td><td>${u.name }</td>
     			<td>${u.password }</td><td>${u.gender }</td>
     			<td>${u.email }</td><td>${u.level }</td>
     			<td class='action'>
                <a class='btn btn-success' data-toggle='tooltip' href='#' title='查看用户'>
                  <i class='icon-zoom-in'></i>
                </a>
                <a class='btn btn-info' href='#' title='编辑用户'>
                   <i class='icon-edit'></i>
                </a>
                <a class='btn btn-danger' href='#' title='删除用户'>
                   <i class='icon-trash'></i>
                </a>
                </td>
     			</tr>  
     		</c:forEach> 
            </tbody>
          </table>
          <ul class='pagination pagination-sm'>
          	<c:if test="${pageNow != 1}">
          	<li>
          	 <a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${pageNow-1}">&laquo;</a>
          	</li>
          	</c:if>
          	<c:if test="${pageNow == 1}">
          	<li class="disabled">
          	 <a href="#">&laquo;</a>
          	</li>
          	</c:if>
          	<c:forEach var="i" begin="1" end="${pageCount}">
          	<c:if test="${i  != pageNow}">
          		<li>
            	<a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${i }">${i }</a>
    			</li>
    		</c:if>
    		<c:if test="${i  == pageNow}">
          		<li class="active">
            	<a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${i }">${i }</a>
    			</li>
    		</c:if>
    		</c:forEach> 
    		<c:if test="${pageNow != pageCount}">
    		<li>
          	 <a href="${pageContext.request.contextPath}/manage.do?flag=usermanage&pageNow=${pageNow+1}">&raquo;</a>
          	</li> 
          	</c:if>
          	<c:if test="${pageNow == pageCount}">
    		<li class="disabled">
          	 <a href="#">&raquo;</a>
          	</li> 
          	</c:if>
           </ul>
          </div>
      </div>
    </div>    
    
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
</body>
</html>