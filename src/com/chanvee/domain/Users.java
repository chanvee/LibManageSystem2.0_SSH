package com.chanvee.domain;

public class Users {
	// �û�id������
	private Integer id;
	// �û���
	private String name;
	//�û�����
	private String password;
	// ����
	private String email;
	// �Ա�
	private String gender;
	// �û��ȼ�,�û�Ȩ�޹���0--ϵͳ����Ա1--��ͨ����Ա2--��ͨ�û�
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
