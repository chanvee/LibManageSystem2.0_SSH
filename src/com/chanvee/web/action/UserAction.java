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
	
	
	//ɾ���û�
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		//��ȡ��Ա��name
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
		
		//��ת���û���ϸ��Ϣ�Ľ���
		public ActionForward showUserInfo(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//ȡ�����������ȴ�ͨ��ϰ,���Ǽ���֤
			String id=request.getParameter("id");
			System.out.println(id);
			//��ȡ�û�
			Users u=(Users) userService.findById(Users.class, Integer.parseInt(id));
			//��ʾ��Ա��Ϣ������һ��ҳ��
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
					request.setAttribute("record_info", "��ǰ�û�û�н��ļ�¼��");
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
		
		//ͨ���û�������ת���û���ϸ��Ϣ�Ľ���
		public ActionForward findUserByName(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
							
				//ȡ�����������ȴ�ͨ��ϰ,���Ǽ���֤
				UserForm userForm=(UserForm)form;
				String name = userForm.getName();

				//��ȡ��Ա
				Users u=(Users) userService.findByName(name);
				int pageNow=1;
				int pageSize=5;
				if (u != null){
					List recordlist = userService.getRecordList(u.getName(),5, pageNow);
					request.setAttribute("user_info", u);
					if (recordlist == null || recordlist.size()==0){
						request.setAttribute("record_info", "��ǰ�û�û�н��ļ�¼��");
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
					request.setAttribute("finduser_err", "�Ҳ�����Ӧ�û���");
					return mapping.findForward("usermanage");
				}											
			}
		
		//��ת���û���ϸ��Ϣ�Ľ���
		public ActionForward showUserRecord(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
				
			//��ȡ��Ա
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
					request.setAttribute("record_info", "��ǰ�û�û�н��ļ�¼��");
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
				request.setAttribute("finduser_err", "�Ҳ�����Ӧ�û���");
				return mapping.findForward("usermanage");
			}			
					
		}
		
		//��ת���û���ϸ��Ϣ�Ľ���
		public ActionForward goUpdateUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//��ȡ��Ա��id
			String id=request.getParameter("id");
			
			//��ȡ��Ա
			Users u=(Users) userService.findById(Users.class, Integer.parseInt(id));
			
			//��ʾ��Ա��Ϣ������һ��ҳ��
			request.setAttribute("user", u);
			
			return mapping.findForward("operok");			
		}
		
		//����û�
		public ActionForward addUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// TODO Auto-generated method stub
			
			//���������Ƿ��ܹ��õ��û��ύ�ĸ�������
			UserForm userForm=(UserForm)form;
			//System.out.println(userForm.getGender());
			
			//����һ��Users����
			
			Users user=new Users();
			user.setName(userForm.getName());
			user.setPassword(userForm.getPassword());
			user.setEmail(userForm.getEmail());
			user.setGender(userForm.getGender());
			user.setLevel(userForm.getLevel());
			
			// ����û����Ѵ���
			if (userService.checkUsername(user)){
				request.setAttribute("userlist", userService.showUserList(3, 1));
				int pageCount=userService.getPageCount(3);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageNow", 1);
				request.setAttribute("adduser_err", "�û����Ѵ��ڣ�");
				return mapping.findForward("usermanage");
			}

			//����
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
		
		//�޸��û�
		public ActionForward editUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// TODO Auto-generated method stub
					
			//���������Ƿ��ܹ��õ��û��ύ�ĸ�������
			UserForm userForm=(UserForm)form;
			//System.out.println(userForm.getEmail());
					
			//����һ��Users����
					
			Users user=new Users();
			user.setId(userForm.getId());
			user.setName(userForm.getName());
			user.setPassword(userForm.getPassword());
			user.setEmail(userForm.getEmail());
			user.setGender(userForm.getGender());
			user.setLevel(userForm.getLevel());

			//����
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
		
		//�޸��û�
		public ActionForward resetUser(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// TODO Auto-generated method stub
							
			//���������Ƿ��ܹ��õ��û��ύ�ĸ�������
			UserForm userForm=(UserForm)form;
			Users u = (Users) request.getSession().getAttribute("loginuser");
			if (u == null){
				return mapping.findForward("opererr");
			}
							
			//����һ��Users����
							
			Users user=new Users();
			user.setId(u.getId());
			user.setName(u.getName());
			user.setPassword(userForm.getPassword());
			user.setEmail(userForm.getEmail());
			user.setGender(u.getGender());
			user.setLevel(u.getLevel());
			

			//����
			try {
				userService.update(user);
			} catch (Exception e) {
				return mapping.findForward("opererr");
			}
			request.setAttribute("loginuser", user);
			request.setAttribute("reset_info", "�޸ĳɹ�");
			if(u.getLevel()==2){
				return mapping.findForward("usersetting");
			}
			if(u.getLevel()==1){
				return mapping.findForward("libadminusersetting");
			}
			
			return mapping.findForward("operok");
		}
	
}
