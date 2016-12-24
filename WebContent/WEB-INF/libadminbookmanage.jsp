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
    
    <script>
    function editBookModal(obj) {
    	var tds = $(obj).parent().parent().find('td');
    	$('#book_id').val(tds.eq(0).text());
    	$('#book_ISBN').val(tds.eq(1).text());
    	$('#book_name').val(tds.eq(2).text());
    	$('#book_author').val(tds.eq(3).text());
    	$('#book_publisher').val(tds.eq(4).text());	
    	if(tds.eq(5).text()=='可借'){
    		$('#book_status1').attr("checked","checked");
    	}
    	if(tds.eq(5).text()=='不可借'){
    		$('#book_status0').attr("checked","checked");
    	}
    	$('#editBookModal').modal('show');
    }
    </script>
    
    <script>
    	function delBookModal(obj){
    		var tds = $(obj).parent().parent().find('td');
        	$('#book_href').attr('href','${pageContext.request.contextPath}/book.do?flag=delBook&id='+tds.eq(0).text()); 
        	$('#delBookModal').modal('show');
    	}
    </script>
    
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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/libadmin.do?flag=dashboard">图书馆管理系统  </a>
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
            				图书管理
            			</h3>
          			</div>
          			<div class="panel-body">
          				<!-- 查找图书 -->
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
                       				<form action="${pageContext.request.contextPath}/book.do?flag=findBookByName" method="post">
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
                       	
                       	<!-- 图书预览 -->
                       	<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-table icon-middle'></i> 图书预览
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-lg-12'>
                       					<table class='table table-bordered table-hover'>
            								<thead>
	               								<tr align="center">
	                							<th style='text-align: center;'>ID</th>
	                							<th style='text-align: center;'>ISBN</th>
								                <th style='text-align: center;'>图书名</th>
								                <th style='text-align: center;'>作者</th>
								                <th style='text-align: center;'>出版社</th>
								                <th style='text-align: center;'>状态</th>
								                <th style='text-align: center;'>操作</th>
								                </tr>
							            	</thead>                       
							            	<tbody>  
								               <c:forEach items="${booklist}" var="book">                           
								     			<tr align="center"><td>${book.id }</td><td>${book.ISBN}</td>
								     			<td>${book.bookname }</td><td>${book.author }</td>
								     			<td>${book.publisher }</td>
								     			<td>
								     			<c:if test="${book.status==1}">
                       								可借
                       							</c:if>
                       							<c:if test="${book.status==0}">
                       								不可借
                       							</c:if>
								     			</td>
								     			<td class='action'>
								     			<!-- 查看图书 -->
								                <a class='btn btn-success' data-toggle='tooltip' href='${pageContext.request.contextPath}/book.do?flag=showBookInfo&id=${book.id }' title='查看图书'>
								                  <i class='icon-zoom-in'></i>
								                </a>
								                <!-- 编辑图书 -->
								                <a id="modal-85757" href="javascript:void(0)" onclick="editBookModal(this);" class='btn btn-info' title='编辑图书' data-toggle="modal" >
								                   <i class='icon-edit'></i>
								                </a>
								                <div class="modal fade" id="editBookModal" tabindex="-1" role="dialog" aria-labelledby="editBookModalLabel" aria-hidden="true">
   													<div class="modal-dialog">
      													<div class="modal-content">
         													<div class="modal-header">
            													<button type="button" class="close" data-dismiss="modal" aria-hidden="true"> 
            														&times;
            													</button>
            													<h4 class="modal-title" id="editBookModalLabel">
               														编辑图书
            													</h4>
         													</div>
         												<div class="modal-body text-left">
            												<div class="row">
									                    		<div class="col-lg-12">
									                    			<div class='panel panel-default'>
									                    				<div class='panel-heading'>
									                        				<h4 class="panel-title">
									                            				<i class='icon-edit icon-middle'></i> 编辑图书
									                        				</h4>
									                       				</div>
									                       			<section>
									                       			<div class="panel-body">
									                       				<form id="validateForm1" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/book.do?flag=editBook&pageNow=${pageNow}">
									                       					<div class="form-group">
													                            <label class="col-lg-3 control-label">ID</label>
													                            <div class="col-lg-7">
													                                <input type="text" readonly="readonly" class="form-control" name="id" id="book_id"/>
													                            </div>
													                        </div>
									                       					
									                       					<div class="form-group">
													                            <label class="col-lg-3 control-label">ISBN</label>
													                            <div class="col-lg-7">
													                                <input type="text" class="form-control" name="ISBN" id="book_ISBN"/>
													                            </div>
													                        </div>
													                        
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">图书名</label>
													                            <div class="col-lg-7">
													                                <input type="text" class="form-control" name="bookname" id="book_name"/>
													                            </div>
													                        </div>
													
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">作者</label>
													                            <div class="col-lg-7">
													                                <input type="text" class="form-control" name="author" id="book_author" />
													                            </div>
													                        </div>
													                        
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">出版社</label>
													                            <div class="col-lg-7">
													                                <input type="text" class="form-control" name="publisher" id="book_publisher" />
													                            </div>
													                        </div>					                        
													
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">状态</label>
													                            <div class="col-lg-7">
													                                <div class="radio">
													                                	<label>
													                                		<input id="book_status1" type="radio" name="status" value="1"/>可借
													                                	</label>
                        															</div>
                        															<div class="radio">
                        																<label>
                        																	<input id="book_status0" type="radio" name="status" value="0"/>不可借
                        																</label>
                        															</div>
													                            </div>
													                        </div>
													                        
													                        <div class="form-group">
													                            <div class="col-lg-9 col-lg-offset-3">
													                                <button type="submit" class="btn btn-primary" name="signup" value="Sign up">提交</button>
													                                <button type="button" class="btn btn-info" id="resetBtn1">重置</button>
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
      												</div><!-- /.modal-content -->
												</div><!-- /.modal -->
								                <!-- 删除图书 -->
								                <a id="modal-85757" href="javascript:void(0)" onclick="delBookModal(this);" class='btn btn-danger'  title='删除图书' data-toggle="modal" >
								                   <i class='icon-trash'></i>
								                </a>
								                <div class="modal fade" id="delBookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   													<div class="modal-dialog">
      													<div class="modal-content">
         													<div class="modal-header">
            													<button type="button" class="close" data-dismiss="modal" aria-hidden="true"> 
            														&times;
            													</button>
            													<h4 class="modal-title" id="myModalLabel">
               														警告
            													</h4>
         													</div>
         												<div class="modal-body">
            												确定删除图书吗？
         												</div>
         												<div class="modal-footer">
            												<button type="button" class="btn btn-default" data-dismiss="modal">
            													取消
           	 												</button>
           	 												<a id="book_href" class="btn btn-primary">确认</a>
         												</div>
      													</div><!-- /.modal-content -->
													</div><!-- /.modal -->
												</div>
								                </td>
								     			</tr>  
								     			</c:forEach> 
							            	</tbody>
							          </table>
							          <ul class='pagination pagination-sm'>
							          	<c:if test="${pageNow != 1}">
							          	<li>
							          	 <a href="${pageContext.request.contextPath}/libadmin.do?flag=bookmanage&pageNow=${pageNow-1}">&laquo;</a>
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
							            	<a href="${pageContext.request.contextPath}/libadmin.do?flag=bookmanage&pageNow=${i }">${i }</a>
							    			</li>
							    		</c:if>
							    		<c:if test="${i  == pageNow}">
							          		<li class="active">
							            	<a href="${pageContext.request.contextPath}/libadmin.do?flag=bookmanage&pageNow=${i }">${i }</a>
							    			</li>
							    		</c:if>
							    		</c:forEach> 
							    		<c:if test="${pageNow != pageCount}">
							    		<li>
							          	 <a href="${pageContext.request.contextPath}/libadmin.do?flag=bookmanage&pageNow=${pageNow+1}">&raquo;</a>
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
                       		</div>
                       	</div>  
                       	
                       	<!-- 新增图书 -->
          				<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-edit icon-middle'></i> 新增图书 
                        				</h4>
                       				</div>
                       			<section>
                       			<div class="panel-body">
                       				<form id="validateForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/book.do?flag=addBook">
                       					<div class="form-group">
				                            <label class="col-lg-3 control-label">ISBN</label>
				                            <div class="col-lg-5">
				                                <input type="text" class="form-control" name="ISBN" /> <font color = 'red'>${addbook_err}</font>
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">图书名</label>
				                            <div class="col-lg-5">
				                                <input type="text" class="form-control" name="bookname" />
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">作者</label>
				                            <div class="col-lg-5">
				                                <input type="text" class="form-control" name="author" />
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">出版社</label>
				                            <div class="col-lg-5">
				                                <input type="text" class="form-control" name="publisher" />
				                            </div>
				                        </div>				                        
				
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">状态</label>
				                            <div class="col-lg-5">
				                                <div class="radio">
				                                    <label>
				                                        <input type="radio" name="status" value="1" /> 可借
				                                    </label>
				                                </div>
				                                <div class="radio">
				                                    <label>
				                                        <input type="radio" name="status" value="0" /> 不可借
				                                    </label>
				                                </div>
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <div class="col-lg-9 col-lg-offset-3">
				                                <button type="submit" class="btn btn-primary" name="signup" value="Sign up">提交</button>
				                                <button type="button" class="btn btn-info" id="resetBtn">重置</button>
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
    <script src="js/bookvalidator.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
    
    
</body>
</html>