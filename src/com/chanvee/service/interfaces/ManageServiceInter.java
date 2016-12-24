package com.chanvee.service.interfaces;

import java.util.Date;
import java.util.List;

import com.chanvee.basic.BasicServiceInter;

public interface ManageServiceInter extends BasicServiceInter {
	
	// �õ����û���
	public int getTotalUsers();
	
	// �õ����û���
	public int getTotalBooks();
		
	// �õ����û���
	public int getTotalRecords();
	
	//�õ�δ�黹ͼ����
	public int getTotalnotBackBooks();
	
	// �õ�ÿ�ս�����
	public int getDayBorrows(String day);
	
	// ���ݵ�ǰʱ��õ�ǰN���ʱ��
	public Date getBeforeNDay(Date dNow, int N);
	
	// �õ��û��ܽ��Ĵ���
	public int getTotalRecordsForUser(String username);
	
	//�õ��û�δ�黹ͼ����
	public int getTotalnotBackBooksForUser(String username);
	
	// �õ��û�ÿ�ս�����
	public int getDayBorrowsForUser(String day, String username);
	
}
