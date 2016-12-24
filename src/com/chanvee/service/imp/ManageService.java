package com.chanvee.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.chanvee.basic.BasicService;
import com.chanvee.domain.Users;
import com.chanvee.service.interfaces.ManageServiceInter;

public class ManageService extends BasicService implements ManageServiceInter {

	public int getTotalUsers(){	
		String hql="select count(*) from Users";
		Object obj=this.uniqueQuery(hql, null);
		return Integer.parseInt(obj.toString());	
	}
	
	public int getTotalBooks(){	
		String hql="select count(*) from Books";
		Object obj=this.uniqueQuery(hql, null);
		return Integer.parseInt(obj.toString());	
	}
	
	public int getTotalRecords(){	
		String hql="select count(*) from Records";
		Object obj=this.uniqueQuery(hql, null);
		return Integer.parseInt(obj.toString());	
	}
	
	public int getTotalnotBackBooks(){	
		String hql="select count(*) from Records where back_date=null";
		Object obj=this.uniqueQuery(hql, null);
		return Integer.parseInt(obj.toString());	
	}
	
	public int getDayBorrows(String day){
		String hql="select count(*) from Records as re where re.borrow_date like"+"'" +day+"%'";
		Object obj=this.uniqueQuery(hql, null);
		return Integer.parseInt(obj.toString());
	}
	
	// 根据当前时间得到前N天的时间
	public Date getBeforeNDay(Date dNow, int N){
			
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -N);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间
			
		return dBefore;
	}
	
	public int getTotalRecordsForUser(String username){
		String hql="select count(*) from Records where username=?";
		Object []parameters ={username};
		Object obj=this.uniqueQuery(hql, parameters);
		return Integer.parseInt(obj.toString());
	}
	
	public int getTotalnotBackBooksForUser(String username){	
		String hql="select count(*) from Records where username=? and back_date=null";
		Object []parameters ={username};
		Object obj=this.uniqueQuery(hql, parameters);
		return Integer.parseInt(obj.toString());	
	}
	
	public int getDayBorrowsForUser(String day, String username){
		String hql="select count(*) from Records as re where re.username=? and re.borrow_date like"+"'" +day+"%'";
		Object []parameters ={username};
		Object obj=this.uniqueQuery(hql, parameters);
		return Integer.parseInt(obj.toString());
	}

}
