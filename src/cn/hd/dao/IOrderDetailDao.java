package cn.hd.dao;

import java.util.List;

import cn.hd.model.OrderBean;
import cn.hd.model.OrderDetailBean;

public interface IOrderDetailDao {

	/**
	 * ��Ӷ���������Ϣ
	 * @param detail ��װ���������
	 * @return
	 */
		public int  add(OrderDetailBean detail);
		 /**
		  * 
		  * �޸Ķ�����Ϣ����������
		  * @param detail
		  * @return
		  * 		-1��ʾ����ʧ��
		  * 		�������ֱ�ʾӰ������
		  */
		public  int  update(OrderDetailBean detail);
		 /**
		  * 
		  * ɾ��������Ϣ
		  * @param orderid  
		  * @return
		  * 		-1��ʾ����ʧ��
		  * 		�������ֱ�ʾӰ������
		  */
		public  int  delete(String 	orderid);
		 /**
		  * 
		  * ����������ѯ������Ϣ
		  * @param detail   
		  * @return
		  * 		��ѯ���
		  */
		public  List<OrderDetailBean>  query(OrderDetailBean detail)	;
		 /**
		  * 
		  * ���ݶ�����Ų�ѯ����������Ϣ
		  * @param orderid 
		  * @return
		  * 		��ѯ���
		  */
		public OrderDetailBean  queryById(String  orderid);
	}

