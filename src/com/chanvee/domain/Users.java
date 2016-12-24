package com.chanvee.domain;

public class Users {
	// 用户id，主键
	private Integer id;
	// 用户名
	private String name;
	//用户密码
	private String password;
	// 邮箱
	private String email;
	// 性别
	private String gender;
	// 用户等级,用户权限管理0--系统管理员1--普通管理员2--普通用户
	private Integer level;
	
	public Users(){
		
	}
	public Users(String name, String password, String email, String gender, Integer level) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.level = level;
	}
	
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
