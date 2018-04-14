package cn.hd.service.impl;
import cn.hd.dao.IUserDao;
import cn.hd.dao.impl.UserDaoImpl;
import cn.hd.model.User;
import cn.hd.service.IUserService;

public class UserServiceImpl implements IUserService {
	  IUserDao dao  =  new  UserDaoImpl();
	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		return dao.login(userName, password);
	}

}
