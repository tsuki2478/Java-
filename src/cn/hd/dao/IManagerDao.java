package cn.hd.dao;
/**
 * Dao后台管理员
 *   admin
 */

import cn.hd.model.ManagerBean;

public interface IManagerDao {
/**
 *  效验用户是否登录成功
 * @param userName 账号
 * @param password	密码
 * @return
 * 			null  表示登录失败
 */
	
	public ManagerBean login(String userName,String password);
}
