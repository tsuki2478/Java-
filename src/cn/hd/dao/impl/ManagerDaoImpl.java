package cn.hd.dao.impl;

import cn.hd.dao.IManagerDao;
import cn.hd.model.ManagerBean;
import cn.hd.utils.BaseDao;

/**
 * 
 * Dao ʵ���� ��̨����Ա
 * 
 * @author Administrator
 *
 */

public class ManagerDaoImpl extends BaseDao implements IManagerDao {
	private String sql;

	@Override
	public ManagerBean login(String userName, String password) {
		sql = "select * from t_manager where musername=? and mpassword=?";

		// TODO Auto-generated method stub
		return super.queryForSingle(sql, ManagerBean.class, userName,password);
	}

}
