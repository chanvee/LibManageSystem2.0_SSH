package com.chanvee.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.chanvee.domain.Users;
import com.chanvee.service.imp.ManageService;
import com.chanvee.service.interfaces.ManageServiceInter;
import com.chanvee.service.interfaces.UserServiceInter;
import com.chanvee.web.forms.UserForm;


public class LoginAction extends DispatchAction {
	@Resource
	private UserServiceInter userService;
	@Resource
	private ManageServiceInter manageService;
	
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//取出表单，我们先打通练习,我们简单验证
		UserForm userForm=(UserForm)form;
		//构建一个Employee对象
		Users u=new Users();
		u.setName(userForm.getName());
		u.setPassword(userForm.getPassword());
		u=userService.checkUser(u);
		if(u!=null){
		//把雇员信息放入session,后面可以使用
			request.getSession().setAttribute("loginuser", u);
			if (u.getLevel()==0){
				request.setAttribute("total_users", manageService.getTotalUsers());
				request.setAttribute("total_books", manageService.getTotalBooks());
				request.setAttribute("total_records", manageService.getTotalRecords());
				request.setAttribute("total_notbacks", manageService.getTotalnotBackBooks());
				return mapping.findForward("admindashbord");
			}
			if (u.getLevel() == 1){
				request.setAttribute("total_books", manageService.getTotalBooks());
				request.setAttribute("total_records", manageService.getTotalRecords());
				request.setAttribute("total_notbacks", manageService.getTotalnotBackBooks());
				return mapping.findForward("libadmindashboard");
			}
			if (u.getLevel() == 2){
				request.setAttribute("user_total_records", manageService.getTotalRecordsForUser(u.getName()));
				request.setAttribute("user_total_notbacks", manageService.getTotalnotBackBooksForUser(u.getName()));
				return mapping.findForward("normaluserdashbord");
			}
		}else{
			return mapping.findForward("login");
		}
		return mapping.findForward("login");
	}
	
	//响应登录
	public ActionForward goMainFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//没有对身份验证![过滤器]
			
		if(request.getSession().getAttribute("loginuser")!=null){
			
			return mapping.findForward("ok");
		}else{
			return mapping.findForward("err");
		}
			
	}
		
	//响应注销请求
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("login");
	}

		
	public void setUserService(UserServiceInter userService) {
		//System.out.println("setEmployeeServiceInter 被调用");
		this.userService= userService;
	}
}
