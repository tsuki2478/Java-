package cn.hd.service.impl;

import java.util.List;

import cn.hd.dao.IOrderdao;
import cn.hd.dao.impl.OrderDaoImpl;
import cn.hd.model.OrderBean;
import cn.hd.service.IOrderService;
import cn.hd.utils.BasePage;

public class OrderServiceImpl implements  IOrderService{
	IOrderdao dao=new OrderDaoImpl();

	@Override
	public int add(OrderBean order) {
		// TODO Auto-generated method stub
		return dao.add(order);
	}

	@Override
	public int update(OrderBean order) {
		// TODO Auto-generated method stub
		return dao.update(order);
	}

	@Override
	public int dalete(String orderId) {
		// TODO Auto-generated method stub
		return dao.delete(orderId);
	}

	@Override
	public List<OrderBean> query(OrderBean order) {
		// TODO Auto-generated method stub
		return dao.query(order);
	}

	@Override
	public OrderBean queryById(String orderid) {
		// TODO Auto-generated method stub
		return dao.queryById(orderid);
	}

	@Override
	public BasePage<OrderBean> queryByPage(int currentPage, int pageSize, int userId) {
		// TODO Auto-generated method stub
		return dao.queryByPage(currentPage, pageSize, userId);
	}

}
