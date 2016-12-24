package com.chanvee.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.User;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.aspectj.weaver.NameMangler;

import com.chanvee.domain.Records;
import com.chanvee.domain.Users;
import com.chanvee.service.interfaces.UserServiceInter;
import com.chanvee.service.interfaces.normalUserServiceInter;
import com.chanvee.web.forms.UserForm;


public class UserAction extends DispatchAction {

	private UserServiceInter userService;

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}
	
	private normalUserServiceInter normaluserService;
	
	public void setNormaluserService(normalUserServiceInter normaluserService) {
		this.normaluserService = normaluserService;
	}
	
	
	//删除用户
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		//获取雇员的name
		String id=request.getParameter("id");
		try {
			userService.delById(Users.class, Integer.parseInt(id));
			//return mapping.findForward("operok");
		} catch (Exception e) {
			return mapping.findForward("opererr");
		}
			
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("/manage.do?flag=usermanage");
		actionForward.setRedirect(true);
		return actionForward;
		
		}
		
		//跳转到用户详细信息的界面
		public ActionForward showUserInfo(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//取出表单，我们先打通练习,我们简单验证
			String id=request.getParameter("id");
			System.out.println(id);
			//获取用户
			Users u=(Users) userService.findById(Users.class, Integer.parseInt(id));
			//显示雇员信息，在下一个页面
			String s_pageNow=request.getParameter("pageNow");
			int pageNow=1;
			int pageSize=5;
			if(s_pageNow!=null){
				pageNow=Integer.parseInt(s_pageNow);
			}
			if (u != null){
				List recordlist = userService.getRecordList(u.getName(),5, pageNow);
				request.setAttribute("user_info", u);
				if (recordlist == null || recordlist.size()==0){
					request.setAttribute("record_info", "当前用户没有借阅记录！");
				}else{
					request.setAttribute("record_list", recordlist);
					int pageCount=userService.getPageCountForRecord(u.getName(), pageSize);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageNow", pageNow);
				}
				
				return mapping.findForward("showUserInfo");
			} 
			else{
				return mapping.findForward("opererr");
			}
		}
		
		//通过用户查找跳转到用户详细信息的界面
		public ActionForward findUserByName(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
							
				//取出表单，我们先打通练习,我们简单验证
				UserForm userForm=(UserForm)form;
				String name = userForm.getName();

				//获取雇员
				Users u=(Users) userService.findByName(name);
				int pageNow=1;
				int pageSize=5;
				if (u != null){
					List recordlist = userService.getRecordList(u.getName(),5, pageNow);
					request.setAttribute("user_info", u);
					if (recordlist == null || recordlist.size()==0){
						request.setAttribute("record_info", "当前用户没有借阅记录！");
					}else{
						request.setAttribute("record_list", recordlist);
						int pageCount=userService.getPageCountForRecord(u.getName(), pageSize);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", pageNow);
					}
					return mapping.findForward("showUserInfo");
				}
				else {
					request.setAttribute("userlist", userService.showUserList(3, 1));
					int pageCount=userService.getPageCount(3);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageNow", 1);
					request.setAttribute("finduser_err", "找不到相应用户！");
					return mapping.findForward("usermanage");
				}											
			}
		
		//跳转到用户详细信息的界面
		public ActionForward showUserRecord(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
				
			//获取雇员
			String name = request.getParameter("username");
			Users u=(Users) userService.findByName(name);
			String s_pageNow=request.getParameter("pageNow");
			int pageNow=1;
			int pageSize=5;
			if(s_pageNow!=null){
				pageNow=Integer.parseInt(s_pageNow);
			}		
			if (u != null){
				List recordlist = userService.getRecordList(u.getName(),pageSize, pageNow);
				request.setAttribute("user_info", u);
				if (recordlist == null || recordlist.size()==0){
					request.setAttribute("record_info", "当前用户没有借阅记录！");
				}else{
					request.setAttribute("record_list", recordlist);
					int pageCount=userService.getPageCountForRecord(u.getName(), pageSize);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageNow", pageNow);
				}
				return mapping.findForward("showUserInfo");
			}
			else {
				request.setAttribute("userlist", userService.showUserList(3, 1));
				int pageCount=userService.getPageCount(3);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageNow", 1);
				request.setAttribute("finduser_err", "找不到相应用户！");
				return mapping.findForward("usermanage");
			}			
					
		}
		
		//跳转到用户详细信息的界面
		public ActionForward goUpdateUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//获取雇员的id
			String id=request.getParameter("id");
			
			//获取雇员
			Users u=(Users) userService.findById(Users.class, Integer.parseInt(id));
			
			//显示雇员信息，在下一个页面
			request.setAttribute("user", u);
			
			return mapping.findForward("operok");			
		}
		
		//添加用户
		public ActionForward addUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// TODO Auto-generated method stub
			
			//看看这里是否能够得到用户提交的各种数据
			UserForm userForm=(UserForm)form;
			//System.out.println(userForm.getGender());
			
			//创建一个Users对象
			
			Users user=new Users();
			user.setName(userForm.getName());
			user.setPassword(userForm.getPassword());
			user.setEmail(userForm.getEmail());
			user.setGender(userForm.getGender());
			user.setLevel(userForm.getLevel());
			
			// 如果用户名已存在
			if (userService.checkUsername(user)){
				request.setAttribute("userlist", userService.showUserList(3, 1));
				int pageCount=userService.getPageCount(3);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageNow", 1);
				request.setAttribute("adduser_err", "用户名已存在！");
				return mapping.findForward("usermanage");
			}

			//保存
			try {
				userService.add(user);
			} catch (Exception e) {
				return mapping.findForward("opererr");
			}
			
			ActionForward actionForward = new ActionForward();
			actionForward.setPath("/manage.do?flag=usermanage");
			actionForward.setRedirect(true);
			return actionForward;

		}
		
		//修改用户
		public ActionForward editUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// TODO Auto-generated method stub
					
			//看看这里是否能够得到用户提交的各种数据
			UserForm userForm=(UserForm)form;
			//System.out.println(userForm.getEmail());
					
			//创建一个Users对象
					
			Users user=new Users();
			user.setId(userForm.getId());
			user.setName(userForm.getName());
			user.setPassword(userForm.getPassword());
			user.setEmail(userForm.getEmail());
			user.setGender(userForm.getGender());
			user.setLevel(userForm.getLevel());

			//保存
			try {
				userService.update(user);
			} catch (Exception e) {
				return mapping.findForward("opererr");
			}
					
			String pageNow = request.getParameter("pageNow");
			System.out.println(pageNow);
			ActionForward actionForward = new ActionForward();
			actionForward.setPath("/manage.do?flag=usermanage&pageNow="+pageNow);
			actionForward.setRedirect(true);
			return actionForward;

			//return mapping.findForward("operok");
		}
		
		//修改用户
		public ActionForward resetUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// TODO Auto-generated method stub
							
			//看看这里是否能够得到用户提交的各种数据
			UserForm userForm=(UserForm)form;
			Users u = (Users) request.getSession().getAttribute("loginuser");
			if (u == null){
				return mapping.findForward("opererr");
			}
							
			//创建一个Users对象
							
			Users user=new Users();
			user.setId(u.getId());
			user.setName(u.getName());
			user.setPassword(userForm.getPassword());
			user.setEmail(userForm.getEmail());
			user.setGender(u.getGender());
			user.setLevel(u.getLevel());
			

			//保存
			try {
				userService.update(user);
			} catch (Exception e) {
				return mapping.findForward("opererr");
			}
			request.setAttribute("loginuser", user);
			request.setAttribute("reset_info", "修改成功");
			if(u.getLevel()==2){
				return mapping.findForward("usersetting");
			}
			if(u.getLevel()==1){
				return mapping.findForward("libadminusersetting");
			}
			
			return mapping.findForward("operok");
		}
	
}
