package cn.hd.service;

import java.util.List;

import cn.hd.model.OrderBean;
import cn.hd.utils.BasePage;

/**
 * 订单主表
 * @author Administrator
 *
 */
public interface IOrderService {

	/**
	  * 
	  * 添加订单信息
	  * @param order  封装的需要添加的数据
	  * @return
	  * 		-1表示操作失误
	  * 		其他数字表示影响行数
	  */
		public int  add(OrderBean order);
		 /**
		  * 
		  * 修改订单信息及订单详情
		  * @param order   
		  * @return
		  * 		-1表示操作失误
		  * 		其他数字表示影响行数
		  */
		public  int  update(OrderBean order);
		 /**
		  * 
		  * 删除订单信息
		  * @param order   
		  * @return
		  * 		-1表示操作失误
		  * 		其他数字表示影响行数
		  */
		public  int  dalete(String orderId);
		 /**
		  * 
		  * 根据条件查询订单信息
		  * @param order   
		  * @return
		  * 		查询结果
		  */
		public  List<OrderBean>  query(OrderBean order);
		 /**
		  * 
		  * 根据订单编号查询订单信息
		  * @param order   
		  * @return
		  * 		查询结果
		  */
		public OrderBean  queryById(String  orderid);
	
		/**
		 * 分页查询订单信息
		 * @param currentPage
		 * @param pageSize
		 * @param userId
		 * @return
		 */
			public   BasePage<OrderBean>    queryByPage(int currentPage,int pageSize,int userId);
		}
