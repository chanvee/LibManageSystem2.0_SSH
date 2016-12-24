package com.chanvee.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.chanvee.domain.Books;
import com.chanvee.domain.Records;
import com.chanvee.domain.Users;
import com.chanvee.service.interfaces.BookServiceInter;
import com.chanvee.service.interfaces.ManageServiceInter;
import com.chanvee.service.interfaces.libAdminServiceInter;
import com.chanvee.service.interfaces.normalUserServiceInter;
import com.chanvee.web.forms.BookForm;
import com.chanvee.web.forms.UserForm;

public class libAdminAction extends DispatchAction {
	
	private libAdminServiceInter libadminService;
	private BookServiceInter bookService;
	private normalUserServiceInter normaluserService;
	
	public void setLibadminService(libAdminServiceInter libadminService) {
		this.libadminService = libadminService;
	}
	public void setBookService(BookServiceInter bookService) {
		this.bookService = bookService;
	}	
	public void setNormaluserService(normalUserServiceInter normaluserService) {
		this.normaluserService = normaluserService;
	}
	@Resource
	private ManageServiceInter manageService;

	public ActionForward dashboard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("total_books", manageService.getTotalBooks());
		request.setAttribute("total_records", manageService.getTotalRecords());
		request.setAttribute("total_notbacks", manageService.getTotalnotBackBooks());
		return mapping.findForward("libadmindashboard");
	}
	
	public ActionForward backbook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		return mapping.findForward("backbook");
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
		request.setAttribute("booklist", bookService.showBookList(3, pageNow));
		//测试
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		
		return mapping.findForward("libadminbookmanage");
	}
	
	public ActionForward usersetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		return mapping.findForward("libadminusersetting");
	}
	
	//通过用户查找跳转到用户详细信息的界面
	public ActionForward findBorrowingBookByUsername(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
						
			//取出表单，我们先打通练习,我们简单验证
			UserForm userForm=(UserForm)form;
			String name = userForm.getName();
			//得到用户借阅的图书列表
			List borrowrecordlist = normaluserService.shownotbackBookList(name);
			if (borrowrecordlist == null || borrowrecordlist.size() == 0){
				request.setAttribute("borrow_info", "当前用户没有借阅的图书！");
			}else{
				request.setAttribute("borrowrecordlist", borrowrecordlist);
			}
			return mapping.findForward("libadminshowBorrowBookInfo");											
	}
	
	public ActionForward trybackBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//String username = request.getParameter("username");
		String username = request.getParameter("username");
		if (username == null){
			return mapping.findForward("opererr");
		}
		String ISBN=request.getParameter("ISBN");			
		List book_list= bookService.findByISBN(ISBN);
		Books book = (Books) book_list.get(0);
		if (book.getStatus() == 0){
			book.setStatus(1);
		}
		try {
			bookService.update(book);
		} catch (Exception e) {
			return mapping.findForward("opererr");
		}
		
		String id = request.getParameter("id");
		Records re = (Records) libadminService.findById(Records.class, Integer.parseInt(id));
		Records record =  new Records();
		record.setId(Integer.parseInt(id));
		record.setUsername(re.getUsername());
		record.setISBN(re.getISBN());
		record.setBookname(re.getBookname());
		record.setAuthor(re.getAuthor());
		record.setPublisher(re.getPublisher());
		record.setBorrow_date(re.getBorrow_date());
		record.setBack_date(new Date());
		try{
			normaluserService.merge(record);
		}catch (Exception e) {
			return mapping.findForward("opererr");
		}
		//得到用户借阅的图书列表
		List borrowrecordlist = normaluserService.shownotbackBookList(username);
		if (borrowrecordlist == null || borrowrecordlist.size() == 0){
			request.setAttribute("borrow_info", "当前用户没有借阅的图书！");
		}else{
			request.setAttribute("borrowrecordlist", borrowrecordlist);
		}
		request.setAttribute("back_info", "恭喜你，归还成功！");
		return mapping.findForward("libadminshowBorrowBookInfo");							
	}

}
