package cn.hd.dao;
/**
 * Dao��̨����Ա
 *   admin
 */

import cn.hd.model.ManagerBean;

public interface IManagerDao {
/**
 *  Ч���û��Ƿ��¼�ɹ�
 * @param userName �˺�
 * @param password	����
 * @return
 * 			null  ��ʾ��¼ʧ��
 */
	
	public ManagerBean login(String userName,String password);
}
