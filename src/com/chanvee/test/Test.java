package com.chanvee.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chanvee.domain.Books;
import com.chanvee.domain.Records;
import com.chanvee.domain.Users;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf = (SessionFactory) aContext.getBean("sessionFactory");
		
		Session s = sf.openSession();
		//Users user = new Users("陈威","123","chanvee@qq.com","男",0);
		//Books book = new Books("ISBN-01","矩阵理论","黄廷祝","高等教育出版社",1);
		//Records record = new Records("chanvee", "ISBN-01","矩阵理论","黄廷祝","高等教育出版社",new Date(),1);
		//Transaction ts= s.beginTransaction();
		//s.save(record);
		//ts.commit();
		
	}

}
