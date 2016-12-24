package com.chanvee.service.interfaces;

import java.util.Date;
import java.util.List;

import com.chanvee.basic.BasicServiceInter;

public interface ManageServiceInter extends BasicServiceInter {
	
	// 得到总用户数
	public int getTotalUsers();
	
	// 得到总用户数
	public int getTotalBooks();
		
	// 得到总用户数
	public int getTotalRecords();
	
	//得到未归还图书数
	public int getTotalnotBackBooks();
	
	// 得到每日借阅数
	public int getDayBorrows(String day);
	
	// 根据当前时间得到前N天的时间
	public Date getBeforeNDay(Date dNow, int N);
	
	// 得到用户总借阅次数
	public int getTotalRecordsForUser(String username);
	
	//得到用户未归还图书数
	public int getTotalnotBackBooksForUser(String username);
	
	// 得到用户每日借阅数
	public int getDayBorrowsForUser(String day, String username);
	
}
