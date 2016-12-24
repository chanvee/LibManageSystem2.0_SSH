package com.chanvee.service.interfaces;

import java.util.List;

import com.chanvee.basic.BasicServiceInter;
import com.chanvee.domain.Users;



public interface UserServiceInter extends BasicServiceInter{
	// ����һЩ����
	public List showUserList(int pageSize,int pageNow);

	//����ù�Ա���ڣ��򷵻ظù�Ա��������Ϣ�����򷵻�null
	public Users checkUser(Users u);
	
	public Boolean checkUsername(Users u);
	
	public int getPageCount(int pageSize);
	
	public Users findByName(String name);
	
	public List getRecordList(String username, int pageSize,int pageNow);
	
	public int getPageCountForRecord(String username, int pageSize);

}
