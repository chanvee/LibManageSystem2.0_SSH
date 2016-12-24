package com.chanvee.service.interfaces;

import java.util.List;

import com.chanvee.basic.BasicServiceInter;
import com.chanvee.domain.Books;
import com.chanvee.domain.Users;

public interface BookServiceInter extends BasicServiceInter {
	
	public List showBookList(int pageSize,int pageNow);
	
	public Boolean checkBookISBN(Books book);
	
	public int getPageCount(int pageSize);
	
	public int getPageCountForRecordByBookname(String bookname, int pageSize);
	
	public int getPageCountForRecordByISBN(String ISBN, int pageSize);
	
	public List findByName(String name);
	
	public List findByISBN(String ISBN);
	
	public List getRecordListByISBN(String ISBN, int pageSize,int pageNow);
	
	public List getRecordListByBookname(String bookname, int pageSize,int pageNow);

}
