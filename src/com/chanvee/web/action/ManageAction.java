package com.chanvee.web.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

//import org.apache.bsf.debug.meta.JsCallbacksDispatcher;
//import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.chanvee.domain.Users;
import com.chanvee.service.interfaces.BookServiceInter;
import com.chanvee.service.interfaces.ManageServiceInter;
import com.chanvee.service.interfaces.UserServiceInter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ManageAction extends DispatchAction {
	
	private UserServiceInter userService;
	private BookServiceInter bookService;

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}	

	public void setBookService(BookServiceInter bookService) {
		this.bookService = bookService;
	}
	@Resource
	private ManageServiceInter manageService;

	public ActionForward dashboard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("total_users", manageService.getTotalUsers());
		request.setAttribute("total_books", manageService.getTotalBooks());
		request.setAttribute("total_records", manageService.getTotalRecords());
		request.setAttribute("total_notbacks", manageService.getTotalnotBackBooks());
		return mapping.findForward("dashboard");
	}
	
	public ActionForward usermanage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String s_pageNow=request.getParameter("pageNow");
		int pageNow=1;
		if(s_pageNow!=null){
			pageNow=Integer.parseInt(s_pageNow);
		}		
		request.setAttribute("userlist", userService.showUserList(3, pageNow));
		//测试
		int pageCount=userService.getPageCount(3);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		//打通线路
		return mapping.findForward("usermanage");
	}
	
	public ActionForward bookmanage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String s_pageNow=request.getParameter("pageNow");
		int pageNow=1;
		if(s_pageNow!=null){
			pageNow=Integer.parseInt(s_pageNow);
		}
		int pageCount=bookService.getPageCount(3);
		System.out.println(pageCount);
		request.setAttribute("booklist", bookService.showBookList(3, pageNow));
		//测试
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		//打通线路
		return mapping.findForward("bookmanage");
	}
	
	public ActionForward borrowmanage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		return mapping.findForward("borrowmanage");
	}
	
	// 得到最近一周每日总借阅数
	public ActionForward getDayBorrows(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		// TODO Auto-generated method stub
		
	    JSONArray data=new JSONArray();
	    JSONObject ele = new JSONObject();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
	    for (int i=7;i>=1;i--){
	    	String dBefore = sdf.format(manageService.getBeforeNDay(new Date(), i));
	    	ele.put("day", dBefore);
	    	ele.put("numbers", manageService.getDayBorrows(dBefore));
	    	data.add(ele);
	    }
	    PrintWriter out = response.getWriter();
	    out.print(data.toString());
	    
		return null;
	}
	
	// 得到最近一周每日总借阅数
	public ActionForward getDayBorrowsForUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		// TODO Auto-generated method stub
		
		Users user = (Users) request.getSession().getAttribute("loginuser");
		String username = user.getName();
		if (username == null){
			return mapping.findForward("opererr");
		}
		 JSONArray data=new JSONArray();
		 JSONObject ele = new JSONObject();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		 for (int i=7;i>=1;i--){
		    String dBefore = sdf.format(manageService.getBeforeNDay(new Date(), i));
		    ele.put("day", dBefore);
		    ele.put("numbers", manageService.getDayBorrowsForUser(dBefore, username));
		    System.out.println(manageService.getDayBorrowsForUser(dBefore, username));
		    data.add(ele);
		 }
		 PrintWriter out = response.getWriter();
		 out.print(data.toString());
		    
		 return null;
	}
	
	
}
