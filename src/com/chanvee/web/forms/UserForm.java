package com.chanvee.web.forms;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	// 用户标识，主键
	private Integer id;
	// 用户名
	private String name;
	//密码
	private String password;
	// 用户邮箱
	private String email;
	// 用户性别
	private String gender;
	// 用户级别,用于权限管理，0--系统管理员，1--普通管理员，2--普通用户
	private Integer level;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	

}
