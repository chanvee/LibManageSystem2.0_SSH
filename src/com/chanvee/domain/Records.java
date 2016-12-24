package com.chanvee.domain;

import java.util.Date;

public class Records {
	// ID,����
	private Integer id;
	// �����û���
	private String username;
	// ͼ��ISBN
	private String ISBN;
	// ͼ����
	private String bookname;
	// ͼ������
	private String author;
	// ͼ�������
	private String publisher;
	// ����ʱ��
	private java.util.Date borrow_date;
	// ����ʱ��
	private java.util.Date back_date;
	
	public Records(){
		
	}
	
	public Records(String username, String ISBN,String bookname, String author, String publisher, Date borrow_date, Date back_date){
		this.username = username;
		this.ISBN = ISBN;
		this.bookname = bookname;
		this.author = author;
		this.publisher = publisher;
		this.borrow_date = borrow_date;
		this.back_date = back_date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public java.util.Date getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(java.util.Date borrow_date) {
		this.borrow_date = borrow_date;
	}

	public java.util.Date getBack_date() {
		return back_date;
	}

	public void setBack_date(java.util.Date back_date) {
		this.back_date = back_date;
	}

}
