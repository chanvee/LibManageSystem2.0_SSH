<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书馆管理系统 </title>
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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/manage.do?flag=dashboard">图书馆管理系统  </a>
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
                        <a href="${pageContext.request.contextPath}/libadmin.do?flag=dashboard"><i class="fa fa-fw fa-dashboard"></i> 仪表盘 </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/libadmin.do?flag=backbook"><i class="fa fa-fw fa-bar-chart-o"></i> 图书归还</a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/libadmin.do?flag=bookmanage"><i class="fa fa-fw fa-table"></i> 图书管理</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/libadmin.do?flag=usersetting"><i class="fa fa-fw fa-edit"></i> 用户设置</a>
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
            				用户管理
            			</h3>
          			</div>
          			<div class="panel-body">
                       	<!-- 显示详细信息 -->
          				<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-search icon-middle'></i> 图书详细信息
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-md-4 text-center'>
                       					<table class="table table-bordered table-hover">
			                                <thead>
			                                    <tr>
			                                        <th style='text-align: center;'>图书</th>
			                                        <th style='text-align: center;'>信息</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<tr align="center">
			                                        <td>ID</td>
			                                        <td>${book_info.id}</td>
			                                    </tr>
			                                    <tr align="center">
			                                        <td>ISBN</td>
			                                        <td>${book_info.ISBN}</td>
			                                    </tr>
			                                    <tr align="center">
			                                        <td>图书名</td>
			                                        <td>${book_info.bookname}</td>
			                                    </tr>
			                                    <tr align="center">
			                                        <td>作者</td>
			                                        <td>${book_info.author}</td>
			                                    </tr>
			                                    <tr align="center">
			                                        <td>出版社</td>
			                                        <td>${book_info.publisher}</td>
			                                    </tr>
			                                    <tr align="center">
			                                        <td>状态</td>
			                                        <td>
													<c:if test="${book_info.status == 1}">
														 可借
								                	</c:if>
								                	<c:if test="${book_info.status == 0}">
			                                        	 不可借
								                	</c:if>
													</td>
			                                    </tr>
			                                </tbody>
			                            </table>
                					</div>              					
                       			</div>                     			
                       			</div>
                       		</div>
                       	</div> 
                       	<!-- 显示用户借阅信息 -->
                       	<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-search icon-middle'></i> 用户借阅信息
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-md-12'>
                       					<c:if test="${!empty record_info}">
                       						<font color = 'red'>${record_info}</font>
                       					</c:if>
                       					<c:if test="${empty record_info}">
                       					<table class="table table-bordered table-hover">
			                                <thead>
			                                    <tr>
			                                        <th style='text-align: center;'>ID</th>
			                                        <th style='text-align: center;'>用户名</th>
			                                        <th style='text-align: center;'>ISBN</th>
			                                        <th style='text-align: center;'>图书名</th>
			                                        <th style='text-align: center;'>作者</th>
			                                        <th style='text-align: center;'>出版社</th>
			                                        <th style='text-align: center;'>借阅时间</th>
			                                        <th style='text-align: center;'>归还时间</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                <c:forEach items="${record_list}" var="record">   
			                                	<tr align="center">
			                                        <td>${record.id}</td>
			                                        <td>${record.username}</td>
			                                        <td>${record.ISBN}</td>
			                                        <td>${record.bookname}</td>
			                                        <td>${record.author}</td>
			                                        <td>${record.publisher}</td>
			                                        <td>${record.borrow_date}</td>
			                                        <td>
			                                        <c:if test="${!empty record.back_date}">
														 ${record.back_date}
								                	</c:if>
								                	<c:if test="${empty record.back_date}">
			                                        	 未归还
								                	</c:if>
			                                        </td>
			                                    </tr>
			                                </c:forEach> 
			                                </tbody>
			                            </table>
			                            
			                            <ul class='pagination pagination-sm'>
							          	<c:if test="${pageNow != 1}">
							          	<li>
							          	 <a href="${pageContext.request.contextPath}/book.do?flag=showBookRecord1&ISBN=${book_info.ISBN }&pageNow=${pageNow-1}">&laquo;</a>
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
							            	<a href="${pageContext.request.contextPath}/book.do?flag=showBookRecord1&ISBN=${book_info.ISBN}&pageNow=${i }">${i }</a>
							    			</li>
							    		</c:if>
							    		<c:if test="${i  == pageNow}">
							          		<li class="active">
							            	<a href="${pageContext.request.contextPath}/book.do?flag=showBookRecord1&ISBN=${book_info.ISBN }&pageNow=${i }">${i }</a>
							    			</li>
							    		</c:if>
							    		</c:forEach> 
							    		<c:if test="${pageNow != pageCount}">
							    		<li>
							          	 <a href="${pageContext.request.contextPath}/book.do?flag=showBookRecord1&ISBN=${book_info.ISBN}&pageNow=${pageNow+1}">&raquo;</a>
							          	</li> 
							          	</c:if>
							          	<c:if test="${pageNow == pageCount}">
							    		<li class="disabled">
							          	 <a href="#">&raquo;</a>
							          	</li> 
							          	</c:if>
							           </ul>
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