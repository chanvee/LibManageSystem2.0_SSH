package com.chanvee.web.forms;

import org.apache.struts.action.ActionForm;

public class BookForm extends ActionForm {
	// 图书主键
		private Integer id;
		//图书编号
		private String ISBN;
		//图书名
		private String bookname;
		//图书作者
		private String author;
		//图书出版社
		private String publisher;
		//图书状态，1为可借，0为不可借
		private Integer status;
		
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
