package cn.hd.service.impl;

import java.util.List;

import cn.hd.dao.IMcTypeDao;
import cn.hd.dao.impl.McTypeDaoImpl;
import cn.hd.model.McTypeBean;
import cn.hd.service.IMcTypeService;
import cn.hd.utils.BaseDao;
/*

*Service实现类 商品类别
*/

public class McTypeServiceImpl implements IMcTypeService {

//	获取商品类别对应的Dao实例
	IMcTypeDao	dao  = new McTypeDaoImpl();
	@Override
	public int add(McTypeBean type) {
		// TODO Auto-generated method stub
		return dao.add(type);
	}

	@Override
	public int update(McTypeBean type) {
		// TODO Auto-generated method stub
		return dao.update(type);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public McTypeBean queryById(int id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}

	@Override
	public List<McTypeBean> query(McTypeBean type) {
		// TODO Auto-generated method stub
		return dao.query(type);
	}

	@Override
	public List<McTypeBean> queryFather() {
		// TODO Auto-generated method stub
		return dao.queryFather();
	}

	@Override
	public List<McTypeBean> querySmall(int fatherid) {
		// TODO Auto-generated method stub
		return dao.querySmall(fatherid);
	}

}
