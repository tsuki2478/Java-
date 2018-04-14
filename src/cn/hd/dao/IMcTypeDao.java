package cn.hd.dao;

import java.util.List;

import cn.hd.model.McTypeBean;

public interface IMcTypeDao {
	/**
	 * 添加商品 type 保存的有添加的信息 -1表示操作失败 其他表示影响行数
	 */

	public int add(McTypeBean type);

	/**
	 * 修改商品 type 保存的有添加的信息 -1表示操作失败 其他表示影响行数
	 */
	public int update(McTypeBean type);

	/**
	 * 根据id删除商品类别 id 需要删除的记录标号 -1表示操作失败 其他表示影响行数
	 */
	public int delete(int id);

	/**
	 * 根据id查询商品类别的信息 param id 查询条件 查询的姐过。 null表示不存在
	 */
	public McTypeBean queryById(int id);
	/**
	 * 查询所有商品类别的信息 param type 查询条件 查询的结果。 null表示没有数据
	 */

	public List<McTypeBean> query(McTypeBean type);

	/**
	 *查询所有商品大类 
	 * @return
	 */
	public List<McTypeBean> queryFather();
	
	/**
	 * 
	 * 根据大类编号查询小类信息
	 * @param fatherid	查询条件
	 * @return
	 *				查询结果
	 */	
	public List<McTypeBean>querySmall(int fatherid);
}
