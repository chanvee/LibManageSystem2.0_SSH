package com.chanvee.domain;

public class Books {
	// ͼ������
	private Integer id;
	//ͼ����
	private String ISBN;
	//ͼ����
	private String bookname;
	//ͼ������
	private String author;
	//ͼ�������
	private String publisher;
	//ͼ��״̬��1Ϊ�ɽ裬0Ϊ���ɽ�
	private Integer status;
	
	public Books(){
		
	}
	public Books(String ISBN, String bookname, String author, String publiser, Integer status) {
		this.ISBN = ISBN;
		this.bookname = bookname;
		this.author = author;
		this.publisher = publiser;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
