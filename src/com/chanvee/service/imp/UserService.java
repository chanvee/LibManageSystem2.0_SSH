package com.chanvee.service.imp;

import java.util.List;


import com.chanvee.basic.BasicService;
import com.chanvee.domain.Users;
import com.chanvee.service.interfaces.UserServiceInter;


public class UserService extends BasicService implements UserServiceInter {

		//显示所有雇员
		public List showUserList(int pageSize,int pageNow){
			String hql="from Users order by id";
			return this.executeQueryByPage(hql, null, pageNow, pageSize);
		}
		
		public int getPageCount(int pageSize){
			
			String hql="select count(*) from Users";
			
			return this.queryPageCount(hql, null, pageSize);
		}
		
		public int getPageCountForRecord(String username, int pageSize){
			
			String hql="select count(*) from Records where username=?";
			Object []parameters ={username};
			return this.queryPageCount(hql, parameters, pageSize);
		}
		
		

		//验证用户
		public Users checkUser(Users u) {
			// TODO Auto-generated method stub
			String hql="from Users where name=? and password=?";
			Object []parameters ={u.getName(),u.getPassword()};
			List list=this.executeQuery(hql, parameters);
			if(list.size()==0){
				return null;
			}else{
				return (Users) list.get(0);
			}
		}
		
		//检测用户名是否重名
		public Boolean checkUsername(Users u) {
			// TODO Auto-generated method stub
			String hql="from Users where name=?";
			Object []parameters ={u.getName()};
			List list=this.executeQuery(hql, parameters);
			if(list.size()==0){
				return false;
			}else{
				return true;
			}
		}
		
		// 通过 name 查找用户
		public Users findByName(String name){
			String hql="from Users where name=?";
			Object []parameters ={name};
			List list=this.executeQuery(hql, parameters);			
			if(list.size()==0){
				return null;
			}else{
				return (Users) list.get(0);
			}
			
		}
		
		//显示用户的借阅记录
		public List getRecordList(String username, int pageSize,int pageNow){
			String hql="from Records where username=?order by borrow_date desc ";
			Object []parameters ={username};
			return this.executeQueryByPage(hql, parameters, pageNow, pageSize);
		}

}
