package cn.hd.service;

import cn.hd.model.ManagerBean;

//IManagerService
/**
 *  IManagerService ��̨����Ա
 * @author Administrator
 *
 */
public interface IManagerService {
	/**
	 *  Ч���û��Ƿ��¼�ɹ�
	 * @param userName �˺�
	 * @param password	����
	 * @return
	 * 			null  ��ʾ��¼ʧ��
	 */
		
		public ManagerBean login(String userName,String password);
	}
