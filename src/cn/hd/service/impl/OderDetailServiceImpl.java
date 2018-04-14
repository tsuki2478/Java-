package cn.hd.service.impl;

import java.util.List;


import cn.hd.dao.IOrderDetailDao;
import cn.hd.dao.impl.OrderDetailDaoImpl;
import cn.hd.model.OrderDetailBean;

public class OderDetailServiceImpl implements IOrderDetailDao {
		IOrderDetailDao    dao =new OrderDetailDaoImpl();
	@Override
	public int add(OrderDetailBean detail) {
		// TODO Auto-generated method stub
		return dao.add(detail);
	}

	@Override
	public int update(OrderDetailBean detail) {
		// TODO Auto-generated method stub
		return dao.update(detail);
	}

	@Override
	public int delete(String orderid) {
		// TODO Auto-generated method stub
		return dao.delete(orderid);
	}

	@Override
	public List<OrderDetailBean> query(OrderDetailBean detail) {
		// TODO Auto-generated method stub
		return dao.query(detail);
	}

	@Override
	public OrderDetailBean queryById(String orderid) {
		// TODO Auto-generated method stub
		return null;
	}

}
