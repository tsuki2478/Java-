package cn.hd.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.hd.model.McBean;

/**
 * ���ﳵ
 * @author dpb
 *
 */
public class ShopCar {
	//��ص�����
//�����������Ʒ��Ϣ����	
private List<McBean> list;
//	�ܵ���Ʒ����
private int  count;
//   �ܵ�����
private  int  totalType;		
//   �ܼ�
private double  totalPrice;

// ��صķ���

private ShopCar(){
	list =new ArrayList();
}
/**
 * ������ṩ�Ự��Ψһ�Ĺ��ﳵ����
 * session������ڹ��ﳵ ����ôֱ�ӷ���
 * 			����ʵ����һ���µķ��ء�
 * @param session
 * @return
 */
public static  ShopCar getShopCar(HttpSession session){
//	��session��������ȡ�����ﳵ	
	Object obj = session.getAttribute("SESSION_SHOPCAR");
		if (obj != null) {
//			��ʾ���ﳵ����
			return(ShopCar)  obj; 
		}
//		��ʾ���ﳵ������
//		��ʵ����һ�����ﳵ����
		ShopCar  car = new 	ShopCar();
//		��������������
		session.setAttribute("SESSION_SHOPCAR", car);
		return car;
		
}
/**
 * ��α�֤ÿ���Ựֻ��һ�����ﳵ���󣿣���---����ʱ��ÿ����һ�����ﳵ--�����Ƶ���
 * ��ô����֤ ֻ��һ�����ﳵ����---> ������ʱֻ��һ�����ﳵ-->����
 *1�� ˽�л����췽��
 *2��  ������ṩһ����̬�Ļ�ȡʵ���ķ���  Ҫ����һ������ 
 *���public����ͨ��������ͨ��������ͨ��ʵ���������á���˽�л�����޷�����
 * static��̬����
 */




/**
 * �����ﳵ�������Ʒ
 * 1 �жϹ��ﳵ���Ƿ��и���Ʒ
 *    a,�н�����Ʒ�Ĺ�������+1
 *    b��û�н�����Ʒ�Ӽ����У�����������Ϊ1
 * 2 ,  ��ʼ��ͳ������
 * @param mc
 */
public void  add(McBean mc){
//	��ʾ���ﳵ��û�и���Ʒ
	boolean  flag = true;
	//	�������ﳵ
	for(McBean m:list){
		if (m.getMcid()==mc.getMcid()) {
//			��ʾ���ﳵ�Ѿ������˸���Ʒ+1
			m.setShopNum(m.getShopNum()+1);
//			�޸ı�־�� �������ﳵ���и���Ʒ
			flag = false;
			
			break;
		}
		
	}
	if (flag==true) {
		list.add(mc);
//		ͬʱ���ù�������+1
		mc.setShopNum(1);
	}
	//��ʼ��ͳ������
	init();
}
/**
 * ��ʼ��ͳ������
 */
public void init(){
//	����
	count=0;
	totalPrice = 0;
	totalType  =0;
//	����ͳ��
	for(McBean mc:list){
		count += mc.getShopNum();
		totalPrice += mc.getPrice()*mc.getShopNum();
	}
	totalType = list.size();
}

/**
 * ����idɾ�����ﳵ����Ʒ
 * @param mcid ��Ҫɾ������ƷID
 */
public  void  delete(int mcid){
//	����������Ʒ
	for (McBean mc : list) {
		if (mc.getMcid()==mcid) {
//			�ҵ�����Ҫ�Ƴ��˼�¼
			list.remove(mc);
			break;
		}
	}
	init();
}

/**
 * �޸Ĺ������Ʒ����
 * @param mcid
 * @param num
 */
public  void   update(int mcid,int num){
		for (McBean mc : list) {
			if (mc.getMcid()==mcid) {
//				�޸ĸ���Ʒ�Ĺ�������Ϊ���ݵ�����
				mc.setShopNum(num);
				break;
			}
		}
	init();
}
/**
 * ��չ��ﳵ
 */
public  void   clear(){
	list.clear();
	init();
}



public List<McBean> getList() {
	return list;
}
public void setList(List<McBean> list) {
	this.list = list;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public int getTotalType() {
	return totalType;
}
public void setTotalType(int totalType) {
	this.totalType = totalType;
}
public double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}
}
