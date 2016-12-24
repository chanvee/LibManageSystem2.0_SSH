package com.chanvee.service.interfaces;

import java.util.List;

import com.chanvee.basic.BasicServiceInter;
import com.chanvee.domain.Users;



public interface UserServiceInter extends BasicServiceInter{
	// 声明一些方法
	public List showUserList(int pageSize,int pageNow);

	//如果该雇员存在，则返回该雇员的完整信息，否则返回null
	public Users checkUser(Users u);
	
	public Boolean checkUsername(Users u);
	
	public int getPageCount(int pageSize);
	
	public Users findByName(String name);
	
	public List getRecordList(String username, int pageSize,int pageNow);
	
	public int getPageCountForRecord(String username, int pageSize);

}
