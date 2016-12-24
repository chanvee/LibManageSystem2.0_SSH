<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书馆管理系统 - 用户管理</title>
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
    function editUserModal(obj) {
    	var tds = $(obj).parent().parent().find('td');
    	$('#u_id').val(tds.eq(0).text());
    	$('#u_name').val(tds.eq(1).text());
    	$('#u_password').val(tds.eq(2).text());
    	$('#u_email').val(tds.eq(4).text());	
    	if(tds.eq(3).text()=='男'){
    		$('#u_man').attr("checked","checked");
    	}
    	if(tds.eq(3).text()=='女'){
    		$('#u_woman').attr("checked","checked");
    	}
    	if(tds.eq(5).text()=='0'){
    		$('#u_level0').attr("checked","checked");
    	}
    	if(tds.eq(5).text()=='1'){
    		$('#u_level1').attr("checked","checked");
    	}
    	if(tds.eq(5).text()=='2'){
    		$('#u_level2').attr("checked","checked");
    	}
    	$('#editUserModal').modal('show');
    }
    </script>
    
    <script>
    	function delUserModal(obj){
    		var tds = $(obj).parent().parent().find('td');
    		var n = 100;
    		//alert('${pageContext.request.contextPath}/user.do?flag=delUser&id='+tds.eq(0).text());
        	//$('#u_id').val(tds.eq(0).text());
        	//href="${pageContext.request.contextPath}/user.do?flag=delUser&id=${u.id }"
        	$('#u_href').attr('href','${pageContext.request.contextPath}/user.do?flag=delUser&id='+tds.eq(0).text()); 
        	$('#delUserModal').modal('show');
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
                    <li>
                        <a href="${pageContext.request.contextPath}/manage.do?flag=dashboard"><i class="fa fa-fw fa-dashboard"></i> 仪表盘 </a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/manage.do?flag=usermanage"><i class="fa fa-fw fa-bar-chart-o"></i> 用户管理</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/manage.do?flag=bookmanage"><i class="fa fa-fw fa-table"></i> 图书管理</a>
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
          				<!-- 查找用户 -->
          				<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-search icon-middle'></i> 查找用户
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-md-4'>
                       				<form action="${pageContext.request.contextPath}/user.do?flag=findUserByName" method="post">
                       				<div class='input-group'>
                  						<input class='form-control' type="text" name='name' placeholder='请输入用户名...' type='text'>
                  							<span class='input-group-btn'>
                    						<button class='btn' type='submit'>
                      							<i class='icon-search'></i>
                    						</button>
                 							</span>
                					</div><font color = 'red'>${finduser_err}</font>
                					</form>
                					</div>              					
                       			</div>                     			
                       			</div>
                       		</div>
                       	</div>
                       	
                       	<!-- 用户预览 -->
                       	<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-table icon-middle'></i> 用户预览
                        				</h4>
                       				</div>
                       			
                       			<div class="panel-body">
                       				<div class='col-lg-12'>
                       					<table class='table table-bordered table-hover'>
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
								     			<!-- 查看用户 -->
								                <a class='btn btn-success' data-toggle='tooltip' href='${pageContext.request.contextPath}/user.do?flag=showUserInfo&id=${u.id }' title='查看用户'>
								                  <i class='icon-zoom-in'></i>
								                </a>
								                <!-- 编辑用户 -->
								                <a id="modal-85757" href="javascript:void(0)" onclick="editUserModal(this);" class='btn btn-info' title='编辑用户' data-toggle="modal" >
								                   <i class='icon-edit'></i>
								                </a>
								                <div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
   													<div class="modal-dialog">
      													<div class="modal-content">
         													<div class="modal-header">
            													<button type="button" class="close" data-dismiss="modal" aria-hidden="true"> 
            														&times;
            													</button>
            													<h4 class="modal-title" id="editUserModalLabel">
               														编辑用户
            													</h4>
         													</div>
         												<div class="modal-body text-left">
            												<div class="row">
									                    		<div class="col-lg-12">
									                    			<div class='panel panel-default'>
									                    				<div class='panel-heading'>
									                        				<h4 class="panel-title">
									                            				<i class='icon-edit icon-middle'></i> 编辑用户
									                        				</h4>
									                       				</div>
									                       			<section>
									                       			<div class="panel-body">
									                       				<form id="validateForm1" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user.do?flag=editUser&pageNow=${pageNow}">
									                       					<div class="form-group">
													                            <label class="col-lg-3 control-label">ID</label>
													                            <div class="col-lg-7">
													                                <input type="text" readonly="readonly" class="form-control" name="id" id="u_id"/>
													                            </div>
													                        </div>
									                       					
									                       					<div class="form-group">
													                            <label class="col-lg-3 control-label">用户名</label>
													                            <div class="col-lg-7">
													                                <input type="text" class="form-control" name="name" id="u_name"/>
													                            </div>
													                        </div>
													                        
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">密码</label>
													                            <div class="col-lg-7">
													                                <input type="text" class="form-control" name="password" id="u_password"/>
													                            </div>
													                        </div>
													
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">邮箱</label>
													                            <div class="col-lg-7">
													                                <input type="text" class="form-control" name="email" id="u_email" />
													                            </div>
													                        </div>				                        
													
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">性别</label>
													                            <div class="col-lg-7">
													                                <div class="radio">
													                                	<label>
													                                		<input id="u_man" type="radio" name="gender" value="男"/>男
													                                	</label>
                        															</div>
                        															<div class="radio">
                        																<label>
                        																	<input id="u_woman" type="radio" name="gender" value="女"/>女
                        																</label>
                        															</div>
													                            </div>
													                        </div>
													                        
													                        <div class="form-group">
													                            <label class="col-lg-3 control-label">等级</label>
													                            <div class="col-lg-7">
													                                <div class="radio">
													                                	<label>
													                                		<input id="u_level0" type="radio" name="level" value="0"/>超级管理员
													                                	</label> 																
													                                </div>
													                                <div class="radio">	
                        																<label>
                        																	<input id="u_level1" type="radio" name="level" value="1"/>管理员
                        																</label>
													                                </div>
													                                <div class="radio">
                        																<label>
                        																	<input id="u_level2" type="radio" name="level" value="2"/>用户
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
								                <!-- 删除用户 -->
								                <a id="modal-85757" href="javascript:void(0)" onclick="delUserModal(this);" class='btn btn-danger'  title='删除用户' data-toggle="modal" >
								                   <i class='icon-trash'></i>
								                </a>
								                <div class="modal fade" id="delUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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

            												确定删除用户吗？
         												</div>
         												<div class="modal-footer">
            												<button type="button" class="btn btn-default" data-dismiss="modal">
            													取消
           	 												</button>
           	 												<a id="u_href" class="btn btn-primary">确认</a>
            												<!-- <button type="button" class="btn btn-primary">
               													确认
            												</button> -->
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
                       		</div>
                       	</div>  
                       	
                       	<!-- 新增用户 -->
          				<div class="row">
                    		<div class="col-lg-12">
                    			<div class='panel panel-default'>
                    				<div class='panel-heading'>
                        				<h4 class="panel-title">
                            				<i class='icon-edit icon-middle'></i> 新增用户 
                        				</h4>
                       				</div>
                       			<section>
                       			<div class="panel-body">
                       				<form id="validateForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user.do?flag=addUser">
                       					<div class="form-group">
				                            <label class="col-lg-3 control-label">用户名</label>
				                            <div class="col-lg-5">
				                                <input type="text" class="form-control" name="name" /> <font color = 'red'>${adduser_err}</font>
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">密码</label>
				                            <div class="col-lg-5">
				                                <input type="password" class="form-control" name="password" />
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">确认密码</label>
				                            <div class="col-lg-5">
				                                <input type="password" class="form-control" name="confirmPassword" />
				                            </div>
				                        </div>
				
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">邮箱</label>
				                            <div class="col-lg-5">
				                                <input type="text" class="form-control" name="email" />
				                            </div>
				                        </div>				                        
				
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">性别</label>
				                            <div class="col-lg-5">
				                                <div class="radio">
				                                    <label>
				                                        <input type="radio" name="gender" value="男" /> 男
				                                    </label>
				                                </div>
				                                <div class="radio">
				                                    <label>
				                                        <input type="radio" name="gender" value="女" /> 女
				                                    </label>
				                                </div>
				                            </div>
				                        </div>
				                        
				                        <div class="form-group">
				                            <label class="col-lg-3 control-label">等级</label>
				                            <div class="col-lg-5">
				                                <div class="radio">
				                                    <label>
				                                        <input type="radio" name="level" value="0" /> 超级管理员
				                                    </label>
				                                </div>
				                                <div class="radio">
				                                    <label>
				                                        <input type="radio" name="level" value="1" /> 管理员
				                                    </label>
				                                </div>
				                                <div class="radio">
				                                    <label>
				                                        <input type="radio" name="level" value="2" /> 用户
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
    <script src="js/myvalidator.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
    
    
</body>
</html>