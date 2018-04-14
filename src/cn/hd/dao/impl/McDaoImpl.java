package cn.hd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hd.dao.IMcDao;
import cn.hd.model.McBean;
import cn.hd.utils.BaseDao;
import cn.hd.utils.BasePage;
import cn.hd.utils.WebUtils;

public class McDaoImpl extends BaseDao implements IMcDao {
   String sql ="";
	@Override
	public int add(McBean mc) {
		sql="insert into t_mc(mcid,mcname,mcdecx,price,pic,flag,quantity,smalltypeid,createdate)  values(seq_t_mc.nextval,?,?,?,?,?,?,?,sysdate) ";
		// TODO Auto-generated method stub
		return super.baseUpdate(sql, mc.getMcname(),mc.getMcdecx(),mc.getPrice(),mc.getPic(),mc.getFlag(),mc.getQuantity(),mc.getSmalltypeid());
	}

	@Override
	public int update(McBean mc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<McBean> query() {
	sql="select * from t_mc ";
		// TODO Auto-generated method stub
		return super.baseQuery(sql, McBean.class);
	}

	@Override
	public McBean queryById(int id) {
		sql="select * from t_mc where mcid=?";
		// TODO Auto-generated method stub
		return super.queryForSingle(sql, McBean.class,id);
	}


	@Override
	public BasePage<McBean> queryByPage(McBean mc, BasePage page) {
		 StringBuilder countSql = new  StringBuilder("select count(1) from t_mc where 1=1");
		 StringBuilder querySql	= new  StringBuilder("select * 	from t_mc where 1=1");
		 StringBuilder whereSql = new  StringBuilder();
		 StringBuilder otherSql = new  StringBuilder();
//		 保存占位符参数的数据
		 List<Object>  list     =  new  ArrayList<>();
		 
		 //添加查询条件
		 if(mc != null){			 
			 //针对对象中属性一一判断
			 if (!WebUtils.isEmpty(mc.getMcname())) {
				//说明需要跟根据商品名称进行查询
				 whereSql.append(" and mcName like ? ");
				//需要对占位符中数据进行赋值
			 list.add("%"+mc.getMcname()+"%");
			 }
		 }
		 
			return super.queryByPage(countSql,
					querySql, whereSql, otherSql, 
					page.getPageSize(), page.getCurrentPage()
					, McBean.class, list);	
			}

}
