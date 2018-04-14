package cn.hd.model;

import java.sql.Date;

import java.util.List;

/**
 * JavaBean 订单主表
 * @author dpb
 *
 */
public class OrderBean {
	private String orderid;
	private int userid;
	private int quantity;
	private int alltype;
	private double totalprice;
	private String paytype;
	private String receivedtype;
	private String username;
	private String address;
	private String postcode;
	private String phoneno;
	private String email;
	private Date orderdate;
	private String status;
	private String approveduser;
	private Date approveddate;
	private String msg;
	// 保存订单主表下关联的所有订单详情
	private List<OrderDetailBean> list;
	
	public String getOrderid() {
		return orderid;
	}

	public OrderBean(String orderid, int userid, int quantity, int alltype, double totalprice, String paytype,
			String receivedtype, String username, String address, String postcode, String phoneno, String email,
			Date orderdate, String status, String approveduser, Date approveddate, String msg,
			List<OrderDetailBean> list) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.quantity = quantity;
		this.alltype = alltype;
		this.totalprice = totalprice;
		this.paytype = paytype;
		this.receivedtype = receivedtype;
		this.username = username;
		this.address = address;
		this.postcode = postcode;
		this.phoneno = phoneno;
		this.email = email;
		this.orderdate = orderdate;
		this.status = status;
		this.approveduser = approveduser;
		this.approveddate = approveddate;
		this.msg = msg;
		this.list = list;
	}

	public OrderBean() {
		super();
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAlltype() {
		return alltype;
	}

	public void setAlltype(int alltype) {
		this.alltype = alltype;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getReceivedtype() {
		return receivedtype;
	}

	public void setReceivedtype(String receivedtype) {
		this.receivedtype = receivedtype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproveduser() {
		return approveduser;
	}

	public void setApproveduser(String approveduser) {
		this.approveduser = approveduser;
	}

	public Date getApproveddate() {
		return approveddate;
	}

	public void setApproveddate(Date approveddate) {
		this.approveddate = approveddate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<OrderDetailBean> getList() {
		return list;
	}

	public void setList(List<OrderDetailBean> list) {
		this.list = list;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "OrderBean [orderid=" + orderid + ", userid=" + userid + ", quantity=" + quantity + ", alltype="
				+ alltype + ", totalprice=" + totalprice + ", paytype=" + paytype + ", receivedtype=" + receivedtype
				+ ", username=" + username + ", address=" + address + ", postcode=" + postcode + ", phoneno=" + phoneno
				+ ", email=" + email + ", orderdate=" + orderdate + ", status=" + status + ", approveduser="
				+ approveduser + ", approveddate=" + approveddate + ", msg=" + msg + ", list=" + list + "]";
	}
	
	
}