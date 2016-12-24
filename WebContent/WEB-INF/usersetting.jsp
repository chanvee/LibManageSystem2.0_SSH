<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书馆管理系统</title>
	<!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- <link href="css/application-a07755f5.css" rel="stylesheet"> -->

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="font-awesome/css/font-awesome1.min.css" rel="stylesheet" type="text/css">
    
    <!-- validator -->
    <link href="css/bootstrapValidator.css" rel="stylesheet">
    
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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/normaluser.do?flag=dashboard">图书馆管理系统  </a>
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
                    <li>
                        <a href="${pageContext.request.contextPath}/normaluser.do?flag=dashboard"><i class="fa fa-fw fa-dashboard"></i> 仪表盘 </a>
                    </li>
                    <li >
                        <a href="${pageContext.request.contextPath}/normaluser.do?flag=borrowbook"><i class="fa fa-fw fa-bar-chart-o"></i> 图书借阅</a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/normaluser.do?flag=usersetting"><i class="fa fa-fw fa-edit"></i> 用户设置</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">
            <div class="container-fluid">
				<div class='panel panel-default'>
					<div class='panel-heading'>
          				<h3 class="panel-title" >
          					<i class='fa fa-fw fa-bar-chart-o icon-large'></i>
            				用户设置
            			</h3>
          			</div>
          			<div class="panel-body">
          				<!-- 修改用户 -->
          				<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-edit icon-middle'></i> 修改用户
                        				</h4>
                       				</div>
                       			<section>
                       			<div class="panel-body">                       				
                       				<form id="validateForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user.do?flag=resetUser">
                       					
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">新密码</label>
				                            <div class="col-lg-5">
				                                <input type="password" class="form-control" name="password" />
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">确认新密码</label>
				                            <div class="col-lg-5">
				                                <input type="password" class="form-control" name="confirmPassword" />
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">新邮箱</label>
				                            <div class="col-lg-5">
				                                <input type="text" class="form-control" name="email" />
				                            </div>
				                        </div>				                        
				                        
				                        <div class="form-group">
				                            <div class="col-lg-9 col-lg-offset-3">
				                                <button type="submit" class="btn btn-primary" name="signup" value="Sign up">提交</button>
				                                <button type="button" class="btn btn-info" id="resetBtn">重置</button>
				                                <font color = 'red'>${reset_info}</font>
				                            </div>
				                        </div>            				
                       				</form>             					
                       			</div> 
                       			</section>                    			
                       			</div>
                       		</div>
                       	</div>           	      			
          			</div>				
				</div>
			</div>
		</div>
	</div>
	
	<!-- jQuery -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrapValidator.js"></script>
    <script src="js/myvalidator.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
    
    
</body>
</html>