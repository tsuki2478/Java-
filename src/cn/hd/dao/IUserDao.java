package cn.hd.dao;

import cn.hd.model.User;

/**
 * 
 * Dao  	ǰ̨�û�
 * @author Administrator
 *
 */
public interface IUserDao {
	
	/**
	 * ǰ̨��¼У��
	 * @param userName �û���
	 * @param password  ����
	 * @return
	 * 		null  ��ʾ��¼ʧ��
	 */
	public User login(String userName,String password	);
}
