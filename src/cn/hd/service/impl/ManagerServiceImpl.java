package cn.hd.service.impl;

import cn.hd.dao.IManagerDao;
import cn.hd.dao.impl.ManagerDaoImpl;
import cn.hd.model.ManagerBean;
import cn.hd.service.IManagerService;
/**
 * Service 实现类 后台管理严
 * 
 * @author Administrator
 *
 */
public class ManagerServiceImpl implements IManagerService {
		IManagerDao   dao  =  new ManagerDaoImpl();
	@Override
	public ManagerBean login(String userName, String password) {
		// TODO Auto-generated method stub
		return dao.login(userName, password);
	}

}
