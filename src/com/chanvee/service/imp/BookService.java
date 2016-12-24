package com.chanvee.service.imp;

import java.io.Serializable;
import java.util.List;

import com.chanvee.basic.BasicService;
import com.chanvee.domain.Books;
import com.chanvee.service.interfaces.BookServiceInter;

public class BookService extends BasicService implements BookServiceInter {

	public List showBookList(int pageSize, int pageNow) {
		String hql="from Books order by id";
		return this.executeQueryByPage(hql, null, pageNow, pageSize);
	}

	public Boolean checkBookISBN(Books book) {
		String hql="from Books where ISBN=?";
		Object []parameters ={book.getISBN()};
		List list=this.executeQuery(hql, parameters);
		if(list.size()==0){
			return false;
		}else{
			return true;
		}
	}
	

	public int getPageCount(int pageSize) {
		String hql="select count(*) from Books";
		
		return this.queryPageCount(hql, null, pageSize);
	}
	
	public int getPageCountForRecordByBookname(String bookname, int pageSize){
		
		String hql="select count(*) from Records where bookname=?";
		Object []parameters ={bookname};
		return this.queryPageCount(hql, parameters, pageSize);
	}
	
	public int getPageCountForRecordByISBN(String ISBN, int pageSize){
		
		String hql="select count(*) from Records where ISBN=?";
		Object []parameters ={ISBN};
		return this.queryPageCount(hql, parameters, pageSize);
	}

	public List findByName(String name) {
		// TODO Auto-generated method stub
		String hql="from Books where bookname=?";
		Object []parameters ={name};
		List list=this.executeQuery(hql, parameters);			
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
	}
	
	public List findByISBN(String ISBN) {
		// TODO Auto-generated method stub
		String hql="from Books where ISBN=?";
		Object []parameters ={ISBN};
		List list=this.executeQuery(hql, parameters);			
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
	}
	
	//通过ISBN显示图书的借阅记录
	public List getRecordListByISBN(String ISBN, int pageSize,int pageNow){
		String hql="from Records where ISBN=? order by borrow_date desc ";
		Object []parameters ={ISBN};
		return this.executeQueryByPage(hql, parameters, pageNow, pageSize);
	}
	
	//通过book显示图书的借阅记录
	public List getRecordListByBookname(String bookname, int pageSize,int pageNow){
		String hql="from Records where bookname=? order by borrow_date desc ";
		Object []parameters ={bookname};
		return this.executeQueryByPage(hql, parameters, pageNow, pageSize);
	}
	
	

}
