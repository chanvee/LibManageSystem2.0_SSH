package com.chanvee.web.forms;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	// �û���ʶ������
	private Integer id;
	// �û���
	private String name;
	//����
	private String password;
	// �û�����
	private String email;
	// �û��Ա�
	private String gender;
	// �û�����,����Ȩ�޹���0--ϵͳ����Ա��1--��ͨ����Ա��2--��ͨ�û�
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
