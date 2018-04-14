package cn.hd.dao;

import cn.hd.model.User;

/**
 * 
 * Dao  	前台用户
 * @author Administrator
 *
 */
public interface IUserDao {
	
	/**
	 * 前台登录校验
	 * @param userName 用户名
	 * @param password  密码
	 * @return
	 * 		null  表示登录失败
	 */
	public User login(String userName,String password	);
}
