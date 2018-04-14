package cn.hd.model;

/**
 * JavaBean 订单详情
 * @author dpb
 *
 */
public class OrderDetailBean {

	/* DETAILID NUMBER primary key,
	  ORDERID  VARCHAR2(20),
	  MCID     NUMBER,
	  BUYNUM   NUMBER*/
	private int detailid;
	private String orderid;
	private int mcid;
	private int buynum;
	
	// 和订单主表的关系  1vs1
	private OrderBean order;

	public OrderDetailBean(int detailid, String orderid, int mcid, int buynum, OrderBean order) {
		super();
		this.detailid = detailid;
		this.orderid = orderid;
		this.mcid = mcid;
		this.buynum = buynum;
		this.order = order;
	}

	public OrderDetailBean() {
		super();
	}

	public int getDetailid() {
		return detailid;
	}

	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getMcid() {
		return mcid;
	}

	public void setMcid(int mcid) {
		this.mcid = mcid;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	public OrderBean getOrder() {
		return order;
	}

	public void setOrder(OrderBean order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetailBean [detailid=" + detailid + ", orderid=" + orderid + ", mcid=" + mcid + ", buynum="
				+ buynum + ", order=" + order + "]";
	}

	
	
}