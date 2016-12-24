package com.chanvee.service.imp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chanvee.basic.BasicService;
import com.chanvee.domain.Books;
import com.chanvee.domain.Records;
import com.chanvee.service.interfaces.normalUserServiceInter;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import sun.awt.Win32ColorModel24;

public class normalUserService extends BasicService implements normalUserServiceInter {
	
	// 通过用户名得到未归还图书记录
	public List shownotbackBookList(String username){
		String hql="from Records where username=? and back_date=null order by borrow_date desc ";
		Object []parameters ={username};
		List list=this.executeQuery(hql, parameters);
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
	}
	
	// 通过用户名得到已归还图书记录
	public List showbackBookList(String username){
		String hql="from Records where username=? and back_date!=null order by borrow_date desc ";
		Object []parameters ={username};
		List list=this.executeQuery(hql, parameters);			
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
	}
	
	// 根据当前时间得到前N天的时间
		public Date getBeforeNDay(Date dNow, int N){
				
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -N);  //设置为前一天
			dBefore = calendar.getTime();   //得到前一天的时间
				
			return dBefore;
		}
	
	public List getRecordListForRecommend(){
		int interval = 30; // 用于推荐的时间长度
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String dBefore = sdf.format(getBeforeNDay(new Date(), interval));
		String hql="from Records where borrow_date >"+"'" +dBefore+"%'";
		List list=this.executeQuery(hql, null);
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
	}
	
	public List getHotBookList(){
		List recommendlist = getRecordListForRecommend();
		
		// 计算图书被借阅的次数
		HashMap<String , Integer> hotbooks = new HashMap<String , Integer>();
		for(int i = 0; i < recommendlist.size(); i++)  
        {  
            Records rl = (Records) recommendlist.get(i);  
            if (hotbooks.containsKey(rl.getISBN())){
            	hotbooks.put(rl.getISBN(), hotbooks.get(rl.getISBN())+1);
            }
            else{
            	hotbooks.put(rl.getISBN(), 1);
            }
        }  
		// 按次数排序
		List<Map.Entry<String, Integer>> hotlist = new ArrayList<Map.Entry<String, Integer>>(hotbooks.entrySet());
		Collections.sort(hotlist, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		    	return o2.getValue().compareTo(o1.getValue());  
		    }
		});
		int cnt = 0;
		int N = 3;//表示推荐最热图书的数目
		ArrayList<String> hotbookname = new ArrayList<String> ();
		for (Map.Entry<String, Integer> mapping : hotlist) {  
              hotbookname.add(mapping.getKey());
              if (cnt >= N){
            	  break;
              }
              cnt += 1;
        }
		//List ll = getRecommendBookList();
		String hql="from Books where ISBN=? or ISBN=? or ISBN=?";
		Object []parameters ={hotbookname.get(0),hotbookname.get(1),hotbookname.get(2)};
		List list=this.executeQuery(hql, parameters);			
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
	}
	
	// 矩阵乘法
	public float[][] multiplication(float[][] matrixa, float[][] matrixb) {  
        if(matrixa[0].length == matrixb.length) {  
            float[][] result = new float[matrixa.length][matrixb[0].length];  
            for(int i=0; i<matrixa.length; i++) {  
                for(int j=0; j<matrixb[0].length; j++) {   
                    result[i][j] = calculateSingleResult(matrixa, matrixb, i, j);   
                }  
            }  
            return result;  
        }   
        else  
        {  
            return null;  
        }  
    }  
    
	// 矩阵乘法计算单个元素
    private float calculateSingleResult(float[][] matrixa, float[][] matrixb, int row, int col) {  
        float result = 0;  
        for(int k=0; k<matrixa[0].length; k++) {  
            result += matrixa[row][k] * matrixb[k][col];  
        }  
        return result;  
    } 
    
    // 得到矩阵的转置矩阵
    public float[][] reverseMatrix(float[][] matrix){
    	int m = matrix.length;  
        int n = matrix[0].length;  
  
        float[][] transMatrix = new float[n][m];  
  
        for (int i = 0; i < n; i++) {  
            for (int j = 0; j < m; j++) {  
                transMatrix[i][j] = matrix[j][i];  
            }  
        }  
  
        return transMatrix;  
    }
    
    // 将向量的倒数转化为对角矩阵
    public float[][] convert2diagMatrix(int[] v){
    	float result[][];
    	result = new float[v.length][v.length];
    	for (int i = 0; i < result.length; i++){
			for (int j = 0; j < result[i].length; j++){
				if (i==j){
					result[i][j] = (float) 1/v[i];
				}				
				else{
					result[i][j] = 0;
				}
			}
		}
    	return result;
    }
    
	// 物质扩散算法
	public float[][] MassDiffusion(float[][] reMatrix){
		float[][] result; // 预测的user-item矩阵
		result=new float[reMatrix.length][reMatrix[0].length];
		// 初始化预测矩阵
		for (int i = 0; i < result.length; i++){
			for (int j = 0; j < result[i].length; j++){
				result[i][j]=0;
			}
		}
		
		int ku[] = new int [reMatrix.length];// 用户的度向量
		int ki[] = new int [reMatrix[0].length];// 商品的度向量
		// 得到ku和ki
		for (int i = 0; i < reMatrix.length; i++){
			for (int j = 0; j < reMatrix[i].length; j++){
				if (reMatrix[i][j] == 1){
					ku[i] += 1;
				}				
			}
		}
		for (int j = 0; j < reMatrix[0].length; j++){
			for (int i = 0; i < reMatrix.length; i++){
				if (reMatrix[i][j] == 1){
					ki[j] += 1;
				}				
			}
		}
		float w1[][], w2[][], w[][];
		w2 = new float[reMatrix.length][reMatrix[0].length];
		w1 = new float[reMatrix[0].length][reMatrix.length];
		w2 = multiplication(reMatrix, convert2diagMatrix(ki));
		w1 = multiplication(reverseMatrix(reMatrix), convert2diagMatrix(ku));
		w = new float[reMatrix.length][reMatrix.length];
		w = multiplication(w2, w1);
		result = multiplication(w, reMatrix);	
		// 对于已选择过的商品，能量重置为0；
		for (int i = 0; i < result.length; i++){
			for (int j = 0; j < result[i].length; j++){
				if (reMatrix[i][j] == 1){
					result[i][j] = 0;
				}				
			}
		}
		return result;
	}
	
	// 根据预测的能量矩排序得到推荐的图书序号
	// 输入为预测矩阵，N表示推荐商品的个数
	public int[][] getrecommendIndex(float[][] preMatrix, int N){
		int [][] result = new int[preMatrix.length][N];
		for (int i = 0; i < preMatrix.length; i++){
			float [] vec = new float[preMatrix[i].length];
			//HashMap<Float, Integer> map = new HashMap<Float, Integer>();
			for (int j = 0; j < preMatrix[i].length; j++){				
				//map.put(preMatrix[i][j], j);
				vec[j] = preMatrix[i][j];				
			}
			//Arrays.sort(vec);
			
			int [] idx = new int[vec.length];
			for (int ii = 0; ii < vec.length; ii++){
				idx[ii] = ii;
			}
			for (int ii = 0; ii < vec.length; ii++){    
				for(int jj = ii+1;jj < vec.length; jj++){    
					if(vec[ii] < vec[jj]){
						float temp = vec[jj];
						vec[jj] = vec[ii];
						vec[ii] = temp;
						int temp1 = idx[jj];
						idx[jj] = idx[ii];
						idx[ii] = temp1;
					}
				}            
			}
			int cnt = 0;
			for (int k = 0; k <= idx.length; k++) {
				if (cnt >= N){
					break;
				}
				result[i][cnt] = idx[k];
				cnt += 1;
			}
		}
		return result;
	}
	
	public List getRecommendBookList(String username) {
		// 得到用于推荐的用户-图书借阅记录
		List recommendlist = getRecordListForRecommend();
		// 初始化用户和图书列表
		List<String> userList = new ArrayList<String>();
		List<String> itemList = new ArrayList<String>();
		List<String> ISBNList = new ArrayList<String>();
		for(int i = 0; i < recommendlist.size(); i++){  
			Records rl = (Records) recommendlist.get(i); 
			if (!userList.contains(rl.getUsername())){
				userList.add(rl.getUsername());
			}
			// 图书名+作者来确定item
			String item = rl.getBookname()+ "+" + rl.getAuthor();
			if (!itemList.contains(item)){
				itemList.add(item);
				ISBNList.add(rl.getISBN());
			}
        }
		// 如果用户是新用户，则推荐最热商品
		if (!userList.contains(username)){
			return(getHotBookList());
		}
		// 如果用户是老用户，则利用推荐算法推荐商品
		float[][] reMatrix; // 用于推荐的user-item矩阵
		reMatrix=new float[userList.size()][itemList.size()];
		// 初始化推荐矩阵
		for (int i = 0; i < reMatrix.length; i++){
			for (int j = 0; j < reMatrix[i].length; j++){
				reMatrix[i][j]=0;
			}
		}
		// 推荐矩阵赋值
		for(int i = 0; i < recommendlist.size(); i++){
			Records rl = (Records) recommendlist.get(i); 
			String item = rl.getBookname() + "+" + rl.getAuthor();
			if (userList.indexOf(rl.getUsername())!= -1 && itemList.indexOf(item)!= -1){
				reMatrix[userList.indexOf(rl.getUsername())][itemList.indexOf(item)] = 1;
			}
		}
		System.out.println(userList);
		System.out.println(itemList);
		// 物质扩散算法
		int preMatrix[][];
		preMatrix = new int[userList.size()][itemList.size()];
		// 得到预测的能量转移矩阵
		preMatrix = getrecommendIndex(MassDiffusion(reMatrix), 3);
		
		// 推荐的ISBN列表		
		List<String> recommendISBNList = new ArrayList<String>();
		for (int j = 0; j < preMatrix[0].length; j++){
			recommendISBNList.add(ISBNList.get(preMatrix[userList.indexOf(username)][j]));
		}
		
		String hql="from Books where ISBN=? or ISBN=? or ISBN=?";
		Object []parameters ={recommendISBNList.get(0),recommendISBNList.get(1),recommendISBNList.get(2)};
		List list=this.executeQuery(hql, parameters);			
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
	}


}
