package com.chanvee.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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
import com.chanvee.service.interfaces.normalUserServiceInter;
import com.chanvee.web.forms.BookForm;

public class normalUserAction extends DispatchAction {
	
	private normalUserServiceInter normaluserService;
	
	public void setNormaluserService(normalUserServiceInter normaluserService) {
		this.normaluserService = normaluserService;
	}
	
	private BookServiceInter bookService;

	public void setBookService(BookServiceInter bookService) {
		this.bookService = bookService;
	}
	@Resource
	private ManageServiceInter manageService;

	public ActionForward borrowbook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginuser");
		if (user == null){
			return mapping.findForward("opererr");
		}
		String username = user.getName();
		Books book = new Books();
		List borrowrecordlist = normaluserService.shownotbackBookList(username);
		List backrecordlist = normaluserService.showbackBookList(username);
		if (borrowrecordlist == null || borrowrecordlist.size() == 0){
			request.setAttribute("borrow_info", "当前没有借阅的图书！");
			if (backrecordlist == null || backrecordlist.size() == 0){
				request.setAttribute("back_info", "当前没有归还的图书！");
			}else{
				request.setAttribute("backrecordlist", backrecordlist);
			}
		}else{
			request.setAttribute("borrowrecordlist", borrowrecordlist);
			if (backrecordlist == null || backrecordlist.size() == 0){
				request.setAttribute("back_info", "当前没有归还的图书！");
			}else{
				request.setAttribute("backrecordlist", backrecordlist);
			}
		}
		
		//List recommendlist = normaluserService.getHotBookList();
		List recommendlist = normaluserService.getRecommendBookList(username);
		if (recommendlist == null || recommendlist.size() == 0){
			request.setAttribute("recommend_info", "当前没有推荐的图书！");
		}else{
			request.setAttribute("recommendlist", recommendlist);
		}
		return mapping.findForward("borrowbook");
	}
	
	public ActionForward backbook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		return mapping.findForward("backbook");
	}
	
	public ActionForward usersetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		return mapping.findForward("usersetting");
	}
	
	public ActionForward dashboard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginuser");		
		if (user == null){
			return mapping.findForward("opererr");
		}
		String username = user.getName();
		request.setAttribute("user_total_records", manageService.getTotalRecordsForUser(username));
		request.setAttribute("user_total_notbacks", manageService.getTotalnotBackBooksForUser(username));
		return mapping.findForward("normaluserdashbord");
	}
	
	//通过用户查找跳转到用户详细信息的界面
	public ActionForward findBookByName(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
						
			//取出表单，我们先打通练习,我们简单验证
			BookForm bookForm=(BookForm)form;
			String name = bookForm.getBookname();
			//获取雇员
			//Books book=(Books) bookService.findByName(name);
			List booklist_info = bookService.findByName(name);
			if (booklist_info != null){
				request.setAttribute("booklist_info", booklist_info);
				return mapping.findForward("showBorrowBookInfo");
			}
			else {
				request.setAttribute("findbook_err", "找不到相应图书！");
				return borrowbook(mapping, form, request, response);
			}											
	}
	
	public ActionForward tryborrowBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//String username = request.getParameter("username");
		Users user = (Users) request.getSession().getAttribute("loginuser");
		String username = user.getName();
		if (username == null){
			return mapping.findForward("opererr");
		}
		String id=request.getParameter("id");			
		Books book=(Books) bookService.findById(Books.class, Integer.parseInt(id));
		if (book.getStatus() == 1){
			book.setStatus(0);
		}
		try {
			bookService.update(book);
		} catch (Exception e) {
			return mapping.findForward("opererr");
		}
			
		Records record =  new Records();
		record.setUsername(username);
		record.setISBN(book.getISBN());
		record.setBookname(book.getBookname());
		record.setAuthor(book.getAuthor());
		record.setPublisher(book.getPublisher());
		record.setBorrow_date(new Date());
		record.setBack_date(null);
		
		try{
			normaluserService.add(record);
		}catch (Exception e) {
			return mapping.findForward("opererr");
		}
		
		return borrowbook(mapping, form, request, response);
		//return mapping.findForward("borrowbook");									
	}
	
	
}
