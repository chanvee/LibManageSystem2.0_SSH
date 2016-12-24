package com.chanvee.service.interfaces;

import java.util.List;

import com.chanvee.basic.BasicServiceInter;
import com.chanvee.domain.Books;

public interface normalUserServiceInter extends BasicServiceInter {
		
	public List shownotbackBookList(String username);
	
	public List showbackBookList(String username);
	
	// 得到最近一个月的借阅记录，用于推荐
	public List getRecordListForRecommend();
	
	// 得到最热的图书列表
	public List getHotBookList();
	
	// 得到推荐的图书列表
	public List getRecommendBookList(String username);

}
