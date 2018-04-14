package cn.hd.dao;

import java.util.List;

import cn.hd.model.McTypeBean;

public interface IMcTypeDao {
	/**
	 * �����Ʒ type ���������ӵ���Ϣ -1��ʾ����ʧ�� ������ʾӰ������
	 */

	public int add(McTypeBean type);

	/**
	 * �޸���Ʒ type ���������ӵ���Ϣ -1��ʾ����ʧ�� ������ʾӰ������
	 */
	public int update(McTypeBean type);

	/**
	 * ����idɾ����Ʒ��� id ��Ҫɾ���ļ�¼��� -1��ʾ����ʧ�� ������ʾӰ������
	 */
	public int delete(int id);

	/**
	 * ����id��ѯ��Ʒ������Ϣ param id ��ѯ���� ��ѯ�Ľ���� null��ʾ������
	 */
	public McTypeBean queryById(int id);
	/**
	 * ��ѯ������Ʒ������Ϣ param type ��ѯ���� ��ѯ�Ľ���� null��ʾû������
	 */

	public List<McTypeBean> query(McTypeBean type);

	/**
	 *��ѯ������Ʒ���� 
	 * @return
	 */
	public List<McTypeBean> queryFather();
	
	/**
	 * 
	 * ���ݴ����Ų�ѯС����Ϣ
	 * @param fatherid	��ѯ����
	 * @return
	 *				��ѯ���
	 */	
	public List<McTypeBean>querySmall(int fatherid);
}
