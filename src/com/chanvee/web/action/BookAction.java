package com.chanvee.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.chanvee.domain.Books;
import com.chanvee.domain.Users;
import com.chanvee.service.interfaces.BookServiceInter;
import com.chanvee.web.forms.BookForm;

public class BookAction extends DispatchAction {
	
	private BookServiceInter bookService;

	public void setBookService(BookServiceInter bookService) {
		this.bookService = bookService;
	}
	
	//删除用户
		public ActionForward delBook(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
				
			//获取雇员的name
			String id=request.getParameter("id");
			try {
				bookService.delById(Books.class, Integer.parseInt(id));
			} catch (Exception e) {
				return mapping.findForward("opererr");
			}
			Users user = (Users) request.getSession().getAttribute("loginuser");
			if (user == null){
				return mapping.findForward("opererr");
			}
			ActionForward actionForward = new ActionForward();
			if (user.getLevel()==0){
				actionForward.setPath("/manage.do?flag=bookmanage");
			}
			if (user.getLevel()==1){
				actionForward.setPath("/libadmin.do?flag=bookmanage");
			}
			
			actionForward.setRedirect(true);
			return actionForward;
			
			}
			
			//跳转到用户详细信息的界面
			public ActionForward showBookInfo(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				//取出表单，我们先打通练习,我们简单验证
				Users user = (Users) request.getSession().getAttribute("loginuser");
				if (user == null){
					return mapping.findForward("opererr");
				} 
				String id=request.getParameter("id");
				System.out.println(id);
				//获取用户
				Books book=(Books) bookService.findById(Books.class, Integer.parseInt(id));				
				//显示图书信息，在下一个页面
				String s_pageNow=request.getParameter("pageNow");
				int pageNow=1;
				int pageSize=5;
				if(s_pageNow!=null){
					pageNow=Integer.parseInt(s_pageNow);
				}
				if (book != null){
					request.setAttribute("book_info", book);
					List recordlist = bookService.getRecordListByISBN(book.getISBN(), pageSize, pageNow);
					request.setAttribute("finded_bookname", book.getBookname());
					if (recordlist == null || recordlist.size()==0){
						request.setAttribute("record_info", "当前没有借阅记录！");
					}else{
						request.setAttribute("record_list", recordlist);
						int pageCount=bookService.getPageCountForRecordByISBN(book.getISBN(), pageSize);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", pageNow);
					}
					if (user.getLevel()==0)//根据用户的不同等级跳转页面
					{
						return mapping.findForward("showBookInfo1");
					}
					else if (user.getLevel()==1){
						return mapping.findForward("libadminshowBookInfo1");
					}
					else{
						return mapping.findForward("opererr");
					}
				} 
				else{
					return mapping.findForward("opererr");
				}
			}
			
			//通过用户查找跳转到用户详细信息的界面
			public ActionForward findBookByName(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
								
					//取出表单，我们先打通练习,我们简单验证
					BookForm bookForm=(BookForm)form;
					String bookname = bookForm.getBookname();
					//获取雇员
					//Books book=(Books) bookService.findByName(name);
					List booklist_info = bookService.findByName(bookname);
					Users user = (Users) request.getSession().getAttribute("loginuser");
					if (user == null){
						return mapping.findForward("opererr");
					} 
					if (booklist_info != null){
						int pageNow=1;
						int pageSize=5;
						List recordlist = bookService.getRecordListByBookname(bookname,pageSize,pageNow);
						request.setAttribute("booklist_info", booklist_info);
						request.setAttribute("finded_bookname", bookname);
						if (recordlist == null || recordlist.size()==0){
							request.setAttribute("record_info", "当前没有借阅记录！");
						}else{
							request.setAttribute("record_list", recordlist);
							int pageCount=bookService.getPageCountForRecordByBookname(bookname, pageSize);
							request.setAttribute("pageCount", pageCount);
							request.setAttribute("pageNow", pageNow);
						}
						if (user.getLevel()==0)//根据用户的不同等级跳转页面
						{
							return mapping.findForward("showBookInfo");
						}
						else if (user.getLevel()==1){
							return mapping.findForward("libadminshowBookInfo");
						}
						else{
							return mapping.findForward("opererr");
						}
						
					}
					else {
						request.setAttribute("booklist", bookService.showBookList(3, 1));
						int pageCount=bookService.getPageCount(3);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", 1);
						request.setAttribute("findbook_err", "找不到相应图书！");
						if (user.getLevel()==0)//根据用户的不同等级跳转页面
						{
							return mapping.findForward("bookmanage");
						}
						else if (user.getLevel()==1){
							return mapping.findForward("libadminbookmanage");
						}
						else{
							return mapping.findForward("opererr");
						}
						//return mapping.findForward("bookmanage");
					}											
				}
			
			//跳转到用户详细信息的界面
			public ActionForward showBookRecord(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
					
				//获取雇员
				String bookname = request.getParameter("bookname");
				List booklist_info = bookService.findByName(bookname);
				Users user = (Users) request.getSession().getAttribute("loginuser");
				if (user == null){
					return mapping.findForward("opererr");
				} 
				String s_pageNow=request.getParameter("pageNow");
				int pageNow=1;
				int pageSize=5;
				if(s_pageNow!=null){
					pageNow=Integer.parseInt(s_pageNow);
				}	
				if (booklist_info != null){
					List recordlist = bookService.getRecordListByBookname(bookname,pageSize,pageNow);
					request.setAttribute("booklist_info", booklist_info);
					request.setAttribute("finded_bookname", bookname);
					if (recordlist == null || recordlist.size()==0){
						request.setAttribute("record_info", "当前用户没有借阅记录！");
					}else{
						request.setAttribute("record_list", recordlist);
						int pageCount=bookService.getPageCountForRecordByBookname(bookname, pageSize);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", pageNow);
					}
					if (user.getLevel()==0)//根据用户的不同等级跳转页面
					{
						return mapping.findForward("showBookInfo");
					}
					else if (user.getLevel()==1){
						return mapping.findForward("libadminshowBookInfo");
					}
					else{
						return mapping.findForward("opererr");
					}
					
				}
				else {
					request.setAttribute("booklist", bookService.showBookList(3, 1));
					int pageCount=bookService.getPageCount(3);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageNow", 1);
					request.setAttribute("findbook_err", "找不到相应图书！");
					if (user.getLevel()==0)//根据用户的不同等级跳转页面
					{
						return mapping.findForward("bookmanage");
					}
					else if (user.getLevel()==1){
						return mapping.findForward("libadminbookmanage");
					}
					else{
						return mapping.findForward("opererr");
					}
					//return mapping.findForward("bookmanage");
				}						
						
			}
			
			//跳转到用户详细信息的界面
			public ActionForward showBookRecord1(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
					
				//获取雇员
				String ISBN = request.getParameter("ISBN");
				List booklist_info = bookService.findByISBN(ISBN);
				Users user = (Users) request.getSession().getAttribute("loginuser");
				if (user == null){
					return mapping.findForward("opererr");
				} 
				String s_pageNow=request.getParameter("pageNow");
				int pageNow=1;
				int pageSize=5;
				if(s_pageNow!=null){
					pageNow=Integer.parseInt(s_pageNow);
				}	
				if (booklist_info != null){
					Books book=(Books) booklist_info.get(0);
					request.setAttribute("book_info", book);
					List recordlist = bookService.getRecordListByISBN(ISBN,pageSize,pageNow);
					request.setAttribute("booklist_info", booklist_info);
					if (recordlist == null || recordlist.size()==0){
						request.setAttribute("record_info", "当前用户没有借阅记录！");
					}else{
						request.setAttribute("record_list", recordlist);
						int pageCount=bookService.getPageCountForRecordByISBN(ISBN, pageSize);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", pageNow);
					}
					if (user.getLevel()==0)//根据用户的不同等级跳转页面
					{
						return mapping.findForward("showBookInfo1");
					}
					else if (user.getLevel()==1){
						return mapping.findForward("libadminshowBookInfo1");
					}
					else{
						return mapping.findForward("opererr");
					}
					
				}
				else {
					request.setAttribute("booklist", bookService.showBookList(3, 1));
					int pageCount=bookService.getPageCount(3);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageNow", 1);
					request.setAttribute("findbook_err", "找不到相应图书！");
					if (user.getLevel()==0)//根据用户的不同等级跳转页面
					{
						return mapping.findForward("bookmanage");
					}
					else if (user.getLevel()==1){
						return mapping.findForward("libadminbookmanage");
					}
					else{
						return mapping.findForward("opererr");
					}
					//return mapping.findForward("bookmanage");
				}						
						
			}
			
			//跳转到用户详细信息的界面
			public ActionForward goUpdateBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				//获取雇员的id
				String id=request.getParameter("id");
				
				//获取雇员
				Books book=(Books) bookService.findById(Books.class, Integer.parseInt(id));
				
				//显示雇员信息，在下一个页面
				request.setAttribute("book", book);
				
				return mapping.findForward("operok");			
			}
			
			//添加用户
			public ActionForward addBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				// TODO Auto-generated method stub
				
				//看看这里是否能够得到用户提交的各种数据
				BookForm bookForm=(BookForm)form;
				
				//创建一个books对象
				
				Books book=new Books();
				
				book.setISBN(bookForm.getISBN());
				book.setBookname(bookForm.getBookname());
				book.setAuthor(bookForm.getAuthor());
				book.setPublisher(bookForm.getPublisher());
				book.setStatus(bookForm.getStatus());
				
				Users user = (Users) request.getSession().getAttribute("loginuser");
				if (user == null){
					return mapping.findForward("opererr");
				}
				// 如果图书ISBN已存在
				if (bookService.checkBookISBN(book)){
					request.setAttribute("booklist", bookService.showBookList(3, 1));
					int pageCount=bookService.getPageCount(3);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageNow", 1);
					request.setAttribute("addbook_err", "ISBN已存在！");
					if (user.getLevel()==0){
						return mapping.findForward("bookmanage");
					}
					else if(user.getLevel()==1){
						return mapping.findForward("libadminbookmanage");
					}else{
						return mapping.findForward("opererr");
					}
					
					
				}

				//保存
				try {
					bookService.add(book);
				} catch (Exception e) {
					return mapping.findForward("opererr");
				}
				
				ActionForward actionForward = new ActionForward();
				if (user.getLevel()==0){
					actionForward.setPath("/manage.do?flag=bookmanage");
				}
				if (user.getLevel()==1){
					actionForward.setPath("/libadmin.do?flag=bookmanage");
				}
				
				actionForward.setRedirect(true);
				return actionForward;

			}
			
			//修改用户
			public ActionForward editBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				// TODO Auto-generated method stub
						
				//看看这里是否能够得到用户提交的各种数据
				BookForm bookForm=(BookForm)form;
				//创建一个books对象
						
				Books book=new Books();
				book.setId(bookForm.getId());
				book.setISBN(bookForm.getISBN());
				book.setBookname(bookForm.getBookname());
				book.setAuthor(bookForm.getAuthor());
				book.setPublisher(bookForm.getPublisher());
				book.setStatus(bookForm.getStatus());
				
				//保存
				try {
					bookService.update(book);
				} catch (Exception e) {
					return mapping.findForward("opererr");
				}
						
				String pageNow = request.getParameter("pageNow");
				Users user = (Users) request.getSession().getAttribute("loginuser");
				if (user == null){
					return mapping.findForward("opererr");
				}
				ActionForward actionForward = new ActionForward();
				if (user.getLevel()==0){
					actionForward.setPath("/manage.do?flag=bookmanage&pageNow="+pageNow);
				}
				if (user.getLevel()==1){
					actionForward.setPath("/libadmin.do?flag=bookmanage&pageNow="+pageNow);
				}
				actionForward.setRedirect(true);
				return actionForward;
			}

}
