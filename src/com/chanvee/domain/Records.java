package com.chanvee.domain;

import java.util.Date;

public class Records {
	// ID,主键
	private Integer id;
	// 借阅用户名
	private String username;
	// 图书ISBN
	private String ISBN;
	// 图书名
	private String bookname;
	// 图书作者
	private String author;
	// 图书出版社
	private String publisher;
	// 借阅时间
	private java.util.Date borrow_date;
	// 借阅时间
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
