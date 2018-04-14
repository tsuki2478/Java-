package cn.hd.dao.impl;

import java.util.List;


import cn.hd.dao.IMcTypeDao;
import cn.hd.model.McTypeBean;
import cn.hd.utils.BaseDao;
/*
*Dao实现类
*
*/


public class McTypeDaoImpl extends BaseDao implements IMcTypeDao {
 private String sql ="";
	@Override
	public int add(McTypeBean type) {
		sql ="insert into t_mctype(typeid,typename,fatherid)values(seq_t_mctype.nextval,?,?)";
		// TODO Auto-generated method stub
		return super.baseUpdate(sql, type.getTypename(),type.getFatherid());
	}

	@Override
	public int update(McTypeBean type) {
	sql ="update t_mctype set typename=?,fatherid=? where typeid=?";
	
		
		// TODO Auto-generated method stub
		return super.baseUpdate(sql, type.getTypename(),type.getFatherid(),type.getTypeid());
	}

	@Override
	public int delete(int id) {
	sql ="delete from t_mctype where typeid=? or fatherid=?";	
		// TODO Auto-generated method stub
		return super.baseUpdate(sql, id,id);
	}

//	<重点> basequeryById(sql, McTypeBean.class, id);
	@Override
	public McTypeBean queryById(int id) {
		// TODO Auto-generated method stub
	sql ="select * from t_mctype where typeid=?";
		
	return super.queryForSingle(sql, McTypeBean.class, id);

				}

	@Override
	public List<McTypeBean> query(McTypeBean type) {
		// TODO Auto-generated method stub
	sql ="select * from t_mctype";	
		return super.baseQuery(sql, McTypeBean.class);
	}

	@Override
	public List<McTypeBean> queryFather() {
		sql="select * from t_mctype where fatherid = 0 ";
		// TODO Auto-generated method stub
	
		return super.baseQuery(sql, McTypeBean.class);
	}

	@Override
	public List<McTypeBean> querySmall(int fatherid) {
		sql="select * from t_mctype where fatherid = ? ";
		// TODO Auto-generated method stub
		return super.baseQuery(sql, McTypeBean.class,fatherid);
	}

}
