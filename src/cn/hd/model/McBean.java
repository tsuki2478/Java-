package cn.hd.model;

import java.sql.Date;

/**
 * 
 * javabean
 * @author Administrator
 *
 */


public class McBean {
 

//		���
	  private  int  mcid;
//	 	����
	  private String mcname;
//	  	����
	  private String  mcdecx;
//	  	�۸�
	  private  double		price;
//	 	ͼƬ����
	  private String   pic;
//	  	�Ƿ�ȱ��  0��ȱ 1ȱ
	  private String  flag;
//	  	С����
	  private int  smalltypeid;
//		����ʱ��
	  private Date  createdate;
//	  	����
	  private int     quantity;
//	  �û�����ĸ���Ʒ����
	  private int  shopNum;
	public McBean(int shopNum) {
	super();
	this.shopNum = shopNum;
}


	public int getShopNum() {
		return shopNum;
	}


	public void setShopNum(int shopNum) {
		this.shopNum = shopNum;
	}


	public McBean() {
	super();
}
 
 
	public McBean(int mcid, String mcname, String mcdecx, double price, String pic, String flag, int smalltypeid,
			Date createdate, int quantity) {
		super();
		this.mcid = mcid;
		this.mcname = mcname;
		this.mcdecx = mcdecx;
		this.price = price;
		this.pic = pic;
		this.flag = flag;
		this.smalltypeid = smalltypeid;
		this.createdate = createdate;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "McBean [mcid=" + mcid + ", mcname=" + mcname + ", mcdecx=" + mcdecx + ", price=" + price + ", pic="
				+ pic + ", flag=" + flag + ", smalltypeid=" + smalltypeid + ", createdate=" + createdate + ", quantity="
				+ quantity + ", shopNum=" + shopNum + "]";
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getMcid() {
		return mcid;
	}
	public void setMcid(int mcid) {
		this.mcid = mcid;
	}
	public String getMcname() {
		return mcname;
	}
	public void setMcname(String mcname) {
		this.mcname = mcname;
	}
	public String getMcdecx() {
		return mcdecx;
	}
	public void setMcdecx(String mcdecx) {
		this.mcdecx = mcdecx;
	}
	 
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getSmalltypeid() {
		return smalltypeid;
	}
	public void setSmalltypeid(int smalltypeid) {
		this.smalltypeid = smalltypeid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	  
	
}
