package cn.hd.dao;

import java.util.List;


import cn.hd.model.McBean;
import cn.hd.utils.BasePage;

public interface IMcDao {
/**
 *  添加商品信息
 */
	public  int add(McBean mc);

	/**  mc
	 * 修改商品信息
	 */
	public  int  update(McBean mc);
	/**id
	 *  删除数据
	 */	
	public  int  delete(int id);
	/**
	 *  查询所有商品信息
	 */
	
	public  List<McBean> query();
	/**
	 * 查询单个商品信息
	 */	
	public  McBean  queryById(int  id);
	/**
	 * 查询分页相关数据
	 * @param mc 查询条件
	 * @param page 封装的currentpage和pagesize
	 * @return
	 * 			分页的相关数据
	 */
 
public BasePage<McBean> queryByPage(McBean mc,BasePage page );
}
