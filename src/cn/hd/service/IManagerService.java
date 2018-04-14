package cn.hd.service;

import cn.hd.model.ManagerBean;

//IManagerService
/**
 *  IManagerService 后台管理员
 * @author Administrator
 *
 */
public interface IManagerService {
	/**
	 *  效验用户是否登录成功
	 * @param userName 账号
	 * @param password	密码
	 * @return
	 * 			null  表示登录失败
	 */
		
		public ManagerBean login(String userName,String password);
	}
