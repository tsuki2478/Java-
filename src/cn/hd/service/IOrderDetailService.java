package cn.hd.service;

import java.util.List;

import cn.hd.model.OrderDetailBean;

/**
 *  service 订单详情
 * @author Administrator
 *
 */


public interface IOrderDetailService {

	/**
	 * 添加订单详情信息
	 * @param detail 封装的添加数据
	 * @return
	 */
		public int  add(OrderDetailBean detail);
		 /**
		  * 
		  * 修改订单信息及订单详情
		  * @param detail
		  * @return
		  * 		-1表示操作失误
		  * 		其他数字表示影响行数
		  */
		public  int  update(OrderDetailBean detail);
		 /**
		  * 
		  * 删除订单信息
		  * @param orderid  
		  * @return
		  * 		-1表示操作失误
		  * 		其他数字表示影响行数
		  */
		public  int  dalete(String 	orderid);
		 /**
		  * 
		  * 根据条件查询订单信息
		  * @param detail   
		  * @return
		  * 		查询结果
		  */
		public  List<OrderDetailBean>  query(OrderDetailBean detail)	;
		 /**
		  * 
		  * 根据订单编号查询订单详情信息
		  * @param orderid 
		  * @return
		  * 		查询结果
		  */
		public OrderDetailBean  queryById(String  orderid);
	}

