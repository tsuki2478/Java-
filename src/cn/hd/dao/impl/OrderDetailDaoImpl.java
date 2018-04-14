package cn.hd.dao.impl;

import java.util.List;

import cn.hd.dao.IOrderDetailDao;
import cn.hd.model.OrderDetailBean;
import cn.hd.utils.BaseDao;

public class OrderDetailDaoImpl extends BaseDao implements IOrderDetailDao {

	private String sql;

	@Override
	public int add(OrderDetailBean detail) {
		sql = "insert into t_orderdetail(detailid,orderid,mcid,buynum)values(seq_t_ORDERDETAIL.nextval,?,?,?)";
		return super.baseUpdate(sql, detail.getOrderid(), detail.getMcid(), detail.getBuynum());
	}

	@Override
	public int update(OrderDetailBean detail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String orderid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDetailBean> query(OrderDetailBean detail) {
		sql = "select * from t_orderdetail ";
		return super.baseQuery(sql, OrderDetailBean.class);
	}

	@Override
	public OrderDetailBean queryById(String orderid) {
		sql = "select * from t_orderdetail where orderid=? ";
		return super.queryForSingle(sql, OrderDetailBean.class, orderid);
	}

}
