package cn.hd.service.impl;

import java.util.List;

import cn.hd.dao.IMcDao;
import cn.hd.dao.impl.McDaoImpl;
import cn.hd.model.McBean;
import cn.hd.service.IMcSerivce;
import cn.hd.utils.BasePage;
/**
 *  service  实现类商品信息
 * @author Administrator
 *
 */

public class McServiceImpl implements IMcSerivce {

//获取Dao实例
	
	IMcDao dao=	new McDaoImpl();
	
	@Override
	public int add(McBean mc) {
		// TODO Auto-generated method stub
		return dao.add(mc);
	}

	@Override
	public int update(McBean mc) {
		// TODO Auto-generated method stub
		return dao.update(mc);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public List<McBean> query() {
		// TODO Auto-generated method stub
		return dao.query();
	}

	@Override
	public McBean queryById(int id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}

	@Override
	public BasePage<McBean> queryByPage(McBean mc, BasePage page) {
		// TODO Auto-generated method stub
		return dao.queryByPage(mc, page);
	}
}
