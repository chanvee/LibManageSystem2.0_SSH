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
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/normaluser.do?flag=borrowbook"><i class="fa fa-fw fa-bar-chart-o"></i> 图书借阅</a>
                    </li>
                    <li>
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
            				图书借阅
            			</h3>
          			</div>
          			<div class="panel-body">
          				<!-- 查找用户 -->
          				<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-search icon-middle'></i> 查找图书
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-md-4'>
                       				<form action="${pageContext.request.contextPath}/normaluser.do?flag=findBookByName" method="post">
                       				<div class='input-group'>
                  						<input class='form-control' type="text" name='bookname' placeholder='请输入图书名...' type='text'>
                  							<span class='input-group-btn'>
                    						<button class='btn' type='submit'>
                      							<i class='icon-search'></i>
                    						</button>
                 							</span>
                					</div><font color = 'red'>${findbook_err}</font>
                					</form>
                					</div>              					
                       			</div>                     			
                       			</div>
                       		</div>
                       	</div>
                       	<!-- 显示已借阅图书 -->
                       	<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-table icon-middle'></i> 未归还图书
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-md-8'>
                       					<c:if test="${empty borrow_info}">
                       					<table class="table table-bordered table-hover">
			                                <thead>
			                                    <tr>
			                                        <th style='text-align: center;'>ISBN</th>
			                                        <th style='text-align: center;'>图书名</th>
			                                        <th style='text-align: center;'>作者</th>
			                                        <th style='text-align: center;'>出版社</th>
			                                        <th style='text-align: center;'>借阅时间</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                <c:forEach items="${borrowrecordlist}" var="borrow_record">   
			                                	<tr align="center">
			                                        <td>${borrow_record.ISBN}</td>
			                                        <td>${borrow_record.bookname}</td>
			                                        <td>${borrow_record.author}</td>
			                                        <td>${borrow_record.publisher}</td>
			                                        <td>${borrow_record.borrow_date}</td>
			                                    </tr>
			                                </c:forEach> 
			                                </tbody>
			                            </table>
			                            </c:if>
			                            <c:if test="${!empty borrow_info}">
			                            	<font color = 'red'>${borrow_info}</font>
			                            </c:if>
                					</div>              					
                       			</div>                     			
                       			</div>
                       		</div>
                       	</div>
                       	<!-- 显示推荐的图书 -->
                       	<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-dashboard icon-middle'></i> 猜你喜欢
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-md-12'>
                       					<table class="table table-bordered table-hover">
			                                <thead>
			                                    <tr>
			                                        <th style='text-align: center;'>ISBN</th>
			                                        <th style='text-align: center;'>图书名</th>
			                                        <th style='text-align: center;'>作者</th>
			                                        <th style='text-align: center;'>出版社</th>
			                                        <th style='text-align: center;'>状态</th>
			                                        <th style='text-align: center;'>借阅</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                <c:forEach items="${recommendlist}" var="recommend_book">   
			                                	<tr align="center">
			                                        <td>${recommend_book.ISBN}</td>
			                                        <td>${recommend_book.bookname}</td>
			                                        <td>${recommend_book.author}</td>
			                                        <td>${recommend_book.publisher}</td>
			                                        <td>
			                                        <c:if test="${recommend_book.status == 1}">
			                                        	可借
			                                        </c:if>
			                                        <c:if test="${recommend_book.status == 0}">
			                                        	不可借
			                                        </c:if>
			                                        </td>
			                                        <td>
			                                        <c:if test="${recommend_book.status == 1}">
			                                        	<a class='btn btn-primary' data-toggle='tooltip' href='${pageContext.request.contextPath}/normaluser.do?flag=tryborrowBook&id=${recommend_book.id }' title='借阅图书'>
			                                        		 借阅
								                		</a>
								                	</c:if>
								                	<c:if test="${recommend_book.status == 0}">
			                                        	<a class='btn btn-primary disabled' data-toggle='tooltip' title='借阅图书'>
			                                        		 借阅
								                		</a>
								                	</c:if>
			                                        </td>
			                                    </tr>
			                                </c:forEach> 
			                                </tbody>
			                            </table>
			                            <c:if test="${!empty recommend_info}">
			                            	<font color = 'red'>${recommend_info}</font>
			                            </c:if>
                					</div>              					
                       			</div>                     			
                       			</div>
                       		</div>
                       	</div> 
                       	<!-- 显示已归还图书 -->
                       	<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-table icon-middle'></i> 已归还图书
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-md-12'>
                       					<c:if test="${empty back_info}">
                       					<table class="table table-bordered table-hover">
			                                <thead>
			                                    <tr>
			                                        <th style='text-align: center;'>ISBN</th>
			                                        <th style='text-align: center;'>图书名</th>
			                                        <th style='text-align: center;'>作者</th>
			                                        <th style='text-align: center;'>出版社</th>
			                                        <th style='text-align: center;'>借阅时间</th>
			                                        <th style='text-align: center;'>归还时间</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                <c:forEach items="${backrecordlist}" var="back_record">   
			                                	<tr align="center">
			                                        <td>${back_record.ISBN}</td>
			                                        <td>${back_record.bookname}</td>
			                                        <td>${back_record.author}</td>
			                                        <td>${back_record.publisher}</td>
			                                        <td>${back_record.borrow_date}</td>
			                                        <td>${back_record.back_date}</td>
			                                    </tr>
			                                </c:forEach> 
			                                </tbody>
			                            </table>
			                            </c:if>
			                            <c:if test="${!empty back_info}">
			                            	<font color = 'red'>${back_info}</font>
			                            </c:if>
                					</div>              					
                       			</div>                     			
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