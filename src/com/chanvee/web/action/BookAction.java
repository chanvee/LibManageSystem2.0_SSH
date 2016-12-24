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
	
	//ɾ���û�
		public ActionForward delBook(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
				
			//��ȡ��Ա��name
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
			
			//��ת���û���ϸ��Ϣ�Ľ���
			public ActionForward showBookInfo(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				//ȡ�����������ȴ�ͨ��ϰ,���Ǽ���֤
				Users user = (Users) request.getSession().getAttribute("loginuser");
				if (user == null){
					return mapping.findForward("opererr");
				} 
				String id=request.getParameter("id");
				System.out.println(id);
				//��ȡ�û�
				Books book=(Books) bookService.findById(Books.class, Integer.parseInt(id));				
				//��ʾͼ����Ϣ������һ��ҳ��
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
						request.setAttribute("record_info", "��ǰû�н��ļ�¼��");
					}else{
						request.setAttribute("record_list", recordlist);
						int pageCount=bookService.getPageCountForRecordByISBN(book.getISBN(), pageSize);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", pageNow);
					}
					if (user.getLevel()==0)//�����û��Ĳ�ͬ�ȼ���תҳ��
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
			
			//ͨ���û�������ת���û���ϸ��Ϣ�Ľ���
			public ActionForward findBookByName(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
								
					//ȡ�����������ȴ�ͨ��ϰ,���Ǽ���֤
					BookForm bookForm=(BookForm)form;
					String bookname = bookForm.getBookname();
					//��ȡ��Ա
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
							request.setAttribute("record_info", "��ǰû�н��ļ�¼��");
						}else{
							request.setAttribute("record_list", recordlist);
							int pageCount=bookService.getPageCountForRecordByBookname(bookname, pageSize);
							request.setAttribute("pageCount", pageCount);
							request.setAttribute("pageNow", pageNow);
						}
						if (user.getLevel()==0)//�����û��Ĳ�ͬ�ȼ���תҳ��
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
						request.setAttribute("findbook_err", "�Ҳ�����Ӧͼ�飡");
						if (user.getLevel()==0)//�����û��Ĳ�ͬ�ȼ���תҳ��
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
			
			//��ת���û���ϸ��Ϣ�Ľ���
			public ActionForward showBookRecord(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
					
				//��ȡ��Ա
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
						request.setAttribute("record_info", "��ǰ�û�û�н��ļ�¼��");
					}else{
						request.setAttribute("record_list", recordlist);
						int pageCount=bookService.getPageCountForRecordByBookname(bookname, pageSize);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", pageNow);
					}
					if (user.getLevel()==0)//�����û��Ĳ�ͬ�ȼ���תҳ��
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
					request.setAttribute("findbook_err", "�Ҳ�����Ӧͼ�飡");
					if (user.getLevel()==0)//�����û��Ĳ�ͬ�ȼ���תҳ��
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
			
			//��ת���û���ϸ��Ϣ�Ľ���
			public ActionForward showBookRecord1(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
					
				//��ȡ��Ա
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
						request.setAttribute("record_info", "��ǰ�û�û�н��ļ�¼��");
					}else{
						request.setAttribute("record_list", recordlist);
						int pageCount=bookService.getPageCountForRecordByISBN(ISBN, pageSize);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("pageNow", pageNow);
					}
					if (user.getLevel()==0)//�����û��Ĳ�ͬ�ȼ���תҳ��
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
					request.setAttribute("findbook_err", "�Ҳ�����Ӧͼ�飡");
					if (user.getLevel()==0)//�����û��Ĳ�ͬ�ȼ���תҳ��
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
			
			//��ת���û���ϸ��Ϣ�Ľ���
			public ActionForward goUpdateBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				//��ȡ��Ա��id
				String id=request.getParameter("id");
				
				//��ȡ��Ա
				Books book=(Books) bookService.findById(Books.class, Integer.parseInt(id));
				
				//��ʾ��Ա��Ϣ������һ��ҳ��
				request.setAttribute("book", book);
				
				return mapping.findForward("operok");			
			}
			
			//����û�
			public ActionForward addBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				// TODO Auto-generated method stub
				
				//���������Ƿ��ܹ��õ��û��ύ�ĸ�������
				BookForm bookForm=(BookForm)form;
				
				//����һ��books����
				
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
				// ���ͼ��ISBN�Ѵ���
				if (bookService.checkBookISBN(book)){
					request.setAttribute("booklist", bookService.showBookList(3, 1));
					int pageCount=bookService.getPageCount(3);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageNow", 1);
					request.setAttribute("addbook_err", "ISBN�Ѵ��ڣ�");
					if (user.getLevel()==0){
						return mapping.findForward("bookmanage");
					}
					else if(user.getLevel()==1){
						return mapping.findForward("libadminbookmanage");
					}else{
						return mapping.findForward("opererr");
					}
					
					
				}

				//����
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
			
			//�޸��û�
			public ActionForward editBook(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				// TODO Auto-generated method stub
						
				//���������Ƿ��ܹ��õ��û��ύ�ĸ�������
				BookForm bookForm=(BookForm)form;
				//����һ��books����
						
				Books book=new Books();
				book.setId(bookForm.getId());
				book.setISBN(bookForm.getISBN());
				book.setBookname(bookForm.getBookname());
				book.setAuthor(bookForm.getAuthor());
				book.setPublisher(bookForm.getPublisher());
				book.setStatus(bookForm.getStatus());
				
				//����
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
