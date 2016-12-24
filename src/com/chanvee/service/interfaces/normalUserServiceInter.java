package com.chanvee.service.interfaces;

import java.util.List;

import com.chanvee.basic.BasicServiceInter;
import com.chanvee.domain.Books;

public interface normalUserServiceInter extends BasicServiceInter {
		
	public List shownotbackBookList(String username);
	
	public List showbackBookList(String username);
	
	// �õ����һ���µĽ��ļ�¼�������Ƽ�
	public List getRecordListForRecommend();
	
	// �õ����ȵ�ͼ���б�
	public List getHotBookList();
	
	// �õ��Ƽ���ͼ���б�
	public List getRecommendBookList(String username);

}
