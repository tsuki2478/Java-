package cn.hd.service;

import java.util.List;


import cn.hd.model.McBean;
import cn.hd.utils.BasePage;

public interface IMcSerivce {

	/**
	 *  �����Ʒ��Ϣ
	 */
		public  int add(McBean mc);

		/**  mc
		 * �޸���Ʒ��Ϣ
		 */
		public  int  update(McBean mc);
		/**id
		 *  ɾ������
		 */	
		public  int  delete(int id);
		/**
		 *  ��ѯ������Ʒ��Ϣ
		 */
		
		public  List<McBean> query();
		/**
		 * ��ѯ������Ʒ��Ϣ
		 */	
		public  McBean  queryById(int  id);
		/**
		 * ��ѯ��ҳ�������
		 * @param mc ��ѯ����
		 * @param page ��װ��currentpage��pagesize
		 * @return
		 * 			��ҳ���������
		 */
		
		public BasePage<McBean> queryByPage(McBean mc,BasePage page);
	}
