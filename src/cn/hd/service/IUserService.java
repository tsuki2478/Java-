package cn.hd.service;

import cn.hd.model.User;

public interface IUserService {
	/**
	 * ǰ̨��¼У��

	 * @param userName �û���
	 * @param password  ����
	 * @return
	 * 		null  ��ʾ��¼ʧ��
	 */
	public User login(String userName,String password	);
}
