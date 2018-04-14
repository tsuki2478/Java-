package cn.hd.dao.impl;

import cn.hd.dao.IUserDao;
import cn.hd.model.User;
import cn.hd.utils.BaseDao;

public class UserDaoImpl extends BaseDao implements IUserDao {
			private String sql;
	@Override
	public User login(String userName, String password) {
sql = "select * from t_user where username=? and password=?";
return super.queryForSingle(sql, User.class,userName,password);
	}

}
