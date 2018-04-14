package cn.hd.dao.impl;

 
 
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import cn.hd.dao.IOrderDetailDao;
import cn.hd.dao.IOrderdao;
import cn.hd.model.OrderBean;
import cn.hd.model.OrderDetailBean;
import cn.hd.utils.BaseDao;
import cn.hd.utils.BasePage;

public class OrderDaoImpl extends BaseDao implements IOrderdao {

	private String sql;
	IOrderDetailDao  dDao=  new OrderDetailDaoImpl();

	@Override
	public int add(OrderBean order) {
//获取订单编号
		String orderId= new Date().getTime()+"";
//sql="insert into t_orders(oderid,userid,quantity,alltype,totalprice,paytype,receivedtype,username,address,postcode,phoneno,email,orderdate)values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		sql="insert into T_ORDERS(orderid,userid,QUANTITY,ALLTYPE,TOTALPRICE,PAYTYPE,RECEIVEDTYPE,USERNAME,address,POSTCODE,PHONENO,email,ORDERDATE)values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		//保存订单主表数据


  int i = super.baseUpdate(sql, orderId,order.getUserid(),order.getQuantity(),order.getAlltype(),order.getTotalprice(),order.getPaytype()
			,order.getReceivedtype(),order.getUsername(),order.getAddress(),order.getPostcode(),order.getPhoneno(),order.getEmail());
	// 2.保存订单详情数据
	List<OrderDetailBean> list = order.getList();
	for (OrderDetailBean d : list) {
		// 将生成的订单编号保存到详情对象中
		d.setOrderid(orderId);
		// 添加详情信息
		dDao.add(d);	
	}
	return i;
	}

	@Override
	public int update(OrderBean order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String orderId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderBean> query(OrderBean order) {
		sql="selectr * from t_orders";
		
		return super.baseQuery(sql, OrderBean.class);
	}

	@Override
	public OrderBean queryById(String orderid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasePage<OrderBean> queryByPage(int currentPage, int pageSize, int userId) {
		// TODO Auto-generated method stub
		 StringBuilder countSql = new  StringBuilder("select count(1) from t_orders where 1=1");
		 StringBuilder querySql	= new  StringBuilder("select * 	from t_orders where 1=1");
		 StringBuilder whereSql = new  StringBuilder();
		 StringBuilder otherSql = new  StringBuilder();
//		 保存占位符参数的数据
		 List<Object>  list     =  new  ArrayList<>();
		 if (userId > 0) {
			whereSql.append("and userid= ? ");
			list.add(userId);
		 }
		 
		return super.queryByPage(countSql, querySql, whereSql, otherSql,
				pageSize, currentPage, OrderBean.class, list);
	}

}
