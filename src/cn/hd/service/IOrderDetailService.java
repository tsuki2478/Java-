package cn.hd.service;

import java.util.List;

import cn.hd.model.OrderDetailBean;

/**
 *  service ��������
 * @author Administrator
 *
 */


public interface IOrderDetailService {

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
		public  int  dalete(String 	orderid);
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

