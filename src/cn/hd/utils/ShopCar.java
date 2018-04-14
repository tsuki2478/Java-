package cn.hd.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.hd.model.McBean;

/**
 * 购物车
 * @author dpb
 *
 */
public class ShopCar {
	//相关的属性
//购买的所有商品信息。。	
private List<McBean> list;
//	总的商品件数
private int  count;
//   总的类数
private  int  totalType;		
//   总价
private double  totalPrice;

// 相关的方法

private ShopCar(){
	list =new ArrayList();
}
/**
 * 给外界提供会话中唯一的购物车对象
 * session如果存在购物车 。那么直接返回
 * 			否则实例化一个新的返回。
 * @param session
 * @return
 */
public static  ShopCar getShopCar(HttpSession session){
//	从session作用域中取出购物车	
	Object obj = session.getAttribute("SESSION_SHOPCAR");
		if (obj != null) {
//			表示购物车存在
			return(ShopCar)  obj; 
		}
//		表示购物车不存在
//		新实例化一个购物车对象
		ShopCar  car = new 	ShopCar();
//		保存在作用域中
		session.setAttribute("SESSION_SHOPCAR", car);
		return car;
		
}
/**
 * 如何保证每个会话只有一个购物车对象？？？---》超时中每个人一两购物车--》类似单例
 * 怎么样保证 只有一个购物车对象---> 整个超时只有一个购物车-->单例
 *1： 私有化构造方法
 *2：  给外界提供一个静态的获取实例的方法  要接受一个参数 
 *如果public是普通方法。普通方法必须通过实例方法调用。。私有化外界无法调用
 * static静态。。
 */




/**
 * 往购物车中添加商品
 * 1 判断购物车中是否有该商品
 *    a,有将该商品的购买数量+1
 *    b，没有将该商品加集合中，购买数量视为1
 * 2 ,  初始化统计数据
 * @param mc
 */
public void  add(McBean mc){
//	表示购物车中没有该商品
	boolean  flag = true;
	//	遍历购物车
	for(McBean m:list){
		if (m.getMcid()==mc.getMcid()) {
//			表示购物车已经购买了该商品+1
			m.setShopNum(m.getShopNum()+1);
//			修改标志。 表明购物车中有该商品
			flag = false;
			
			break;
		}
		
	}
	if (flag==true) {
		list.add(mc);
//		同时设置购买数量+1
		mc.setShopNum(1);
	}
	//初始化统计数据
	init();
}
/**
 * 初始化统计数据
 */
public void init(){
//	重置
	count=0;
	totalPrice = 0;
	totalType  =0;
//	重新统计
	for(McBean mc:list){
		count += mc.getShopNum();
		totalPrice += mc.getPrice()*mc.getShopNum();
	}
	totalType = list.size();
}

/**
 * 根据id删除购物车的商品
 * @param mcid 需要删除的商品ID
 */
public  void  delete(int mcid){
//	遍历所有商品
	for (McBean mc : list) {
		if (mc.getMcid()==mcid) {
//			找到了需要移除了记录
			list.remove(mc);
			break;
		}
	}
	init();
}

/**
 * 修改购买的商品数量
 * @param mcid
 * @param num
 */
public  void   update(int mcid,int num){
		for (McBean mc : list) {
			if (mc.getMcid()==mcid) {
//				修改该商品的购买数量为传递的数量
				mc.setShopNum(num);
				break;
			}
		}
	init();
}
/**
 * 清空购物车
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
