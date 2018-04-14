package cn.hd.dao;

import java.util.List;

import cn.hd.model.OrderBean;
import cn.hd.utils.BasePage;

/**
 * dao ������
 * @author Administrator
 *
 */
public interface IOrderdao {
 /**
  * 
  * ���Ӷ�����Ϣ
  * @param order  ��װ����Ҫ���ӵ�����
  * @return
  * 		-1��ʾ����ʧ��
  * 		�������ֱ�ʾӰ������
  */
	public int  add(OrderBean order);
	 /**
	  * 
	  * �޸Ķ�����Ϣ����������
	  * @param order   
	  * @return
	  * 		-1��ʾ����ʧ��
	  * 		�������ֱ�ʾӰ������
	  */
	public  int  update(OrderBean order);
	 /**
	  * 
	  * ɾ��������Ϣ
	  * @param order   
	  * @return
	  * 		-1��ʾ����ʧ��
	  * 		�������ֱ�ʾӰ������
	  */
	public  int  delete(String orderId);
	 /**
	  * 
	  * ����������ѯ������Ϣ
	  * @param order   
	  * @return
	  * 		��ѯ���
	  */
	public  List<OrderBean>  query(OrderBean order);
	 /**
	  * 
	  * ���ݶ�����Ų�ѯ������Ϣ
	  * @param order   
	  * @return
	  * 		��ѯ���
	  */
	public OrderBean  queryById(String  orderid);

/**
 * ��ҳ��ѯ������Ϣ
 * @param currentPage
 * @param pageSize
 * @param userId
 * @return
 */
	public   BasePage<OrderBean>    queryByPage(int currentPage,int pageSize,int userId);
}