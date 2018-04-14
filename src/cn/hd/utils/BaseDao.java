package cn.hd.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.hd.model.McBean;

/**
 * ���ݿ�����Ĺ�����
 * 
 * @author xzl
 *
 */
public class BaseDao {

	/**
	 * ���ݿ�����ӣ��޸ģ�ɾ���Ĺ��з���
	 * 
	 * @param sql
	 *            SQL���
	 * @param params
	 *            �ɱ���� ���������� �ɱ������һ��Ҫ�����βε�ĩβλ�� �ɱ������ֵ�����ռλ����һһ��Ӧ
	 * @return -1��ʾ����ʧ�� �����ı�ʾ���³ɹ�
	 */
	public static int baseUpdate(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		// 1.ע��ͼ������ݿ���������
		try {
			// conn = DBUtil.getConnection();
			// ͨ��c3p0���ӳػ�ȡ���ݿ�����ͨ��
			conn = DataSourceConfig.getDataSource().getConnection();
			// 3.����SQL���
			// 4.����SQL���
			ps = conn.prepareStatement(sql);
			// System.out.println(params!=null);
			// ȡ���ɱ�����е�ֵ����ռλ��һһ��ֵ
			if (params != null) {
				// System.out.println(params.length);
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 5.ִ��SQL���
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����ʧ�ܣ�����");
		} finally {
			// 6.�ر���Դ
			DBUtil.close(conn, ps);
		}
		return -1;
	}

	/**
	 * ��ѯ��ṹ�����������ļ�¼��
	 * 
	 * @param sql
	 *            ��Ҫִ�е�sql���
	 * @param objs
	 *            sql����ж�Ӧ��ռλ����Ӧ�Ĳ���
	 * @return -1 ��ʾִ�г��� ����ֵ ��ʾ��¼������
	 */
	public static int baseQueryForCount(String sql, Object... objs) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			// ͨ��c3p0��ȡ����ͨ��
			conn = DataSourceConfig.getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			// ��ռλ�����и�ֵ
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) {
					ps.setObject(i + 1, objs[i]);
				}
			}
			// ��ʾ��ִ��sql���
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			DBUtil.close(conn, ps, rs);
		}
		return -1;
	}

	/**
	 * ��ѯ�����¼�ķ���
	 * 
	 * @param <T>
	 * @param sql
	 *            ��ѯ���
	 * @param cls
	 *            Class����
	 * @param params
	 *            �ɱ����
	 * @return
	 * 
	 */
	public static <T> List<T> baseQuery(String sql, Class<T> cls, Object... params) {
		List<T> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 1.ע��ͼ������ݿ���������
		// 2.��ȡ���ݿ�����ͨ��

		try {
			// conn = DBUtil.getConnection();
			// ͨ��c3p0���ӳػ�ȡ���ݿ�����ͨ��
			conn = DataSourceConfig.getDataSource().getConnection();
			// ����SQL���
			// 3.����SQL���
			ps = conn.prepareStatement(sql);
			// 4.��ռλ����ֵ
			if (params != null) {
				// �����ɱ��������ռλ������һһ��ֵ
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 5.ִ��SQL
			rs = ps.executeQuery();
			// Ԫ���ݣ�����������������������������������ÿһ�е������ƣ�
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ��ѯ�����������ж�����
			int columnCount = rsmd.getColumnCount();
			// 6.��������
			while (rs.next()) {
				// rs.next()���жϵ�ǰ���Ƿ�����һ�У����򷵻�true
				// ����ָ��ָ����һ�У����ҽ���һ�е����ݱ��浽ResultSet
				// ѭ���ж�ֱ��û����һ��Ϊֹ,����false
				// ͨ�����䴴��һ������ʵ��,�����б���Ҫ��һ���޲����Ĺ��캯��
				T obj = cls.newInstance();
				// ���ݲ�ѯ������������ѭ��ȡ��ÿһ�ж�Ӧ��ֵ
				for (int i = 1; i <= columnCount; i++) {
					Object value = rs.getObject(i);// ��ȡÿһ�е�ֵ
					// ��ȡÿһ�е�������,sid ,sname ,ssex
					String columnName = rsmd.getColumnName(i).toLowerCase();
					// System.out.println(columnName+","+value);
					// ���valueֵΪ�գ�����Ҫ�Զ����е����Խ��и�ֵ
					if (value == null || "".equals(value)) {
						continue;// ������ǰѭ����ִ����һ��ѭ��
					}
					// ����������ͨ�������ȡ�������еĳ�Ա����
					if (hasField(cls, columnName)) {
						Field f = cls.getDeclaredField(columnName);
						// true��ʾ���ԶԶ����˽�����Խ��з���
						f.setAccessible(true);
						// number��Ӧ��Java��BigDecimal
						if (value instanceof BigDecimal) {
							// value������С������������������������Ҫ���ж�
							BigDecimal val = (BigDecimal) value;
							// �жϳ�Ա������ʲô���͵�
							if (f.getType().getName().equals("int")) {
								// ����ȡ����valueֵ���浽obj�����еĶ�Ӧ�ĳ�Ա������
								f.set(obj, val.intValue());
							} else {
								f.set(obj, val.doubleValue());
							}
						} else {
							f.set(obj, value); // setSid()
						}
					}
				}
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7�ر���Դ
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * ��ѯ������¼�ķ���
	 * 
	 * @param <T>
	 * @param sql
	 *            ��ѯ���
	 * @param cls
	 *            Class����
	 * @param params
	 *            �ɱ����
	 * @return
	 * 
	 */
	public static <T> T queryForSingle(String sql, Class<T> cls, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 1.ע��ͼ������ݿ���������
		// 2.��ȡ���ݿ�����ͨ��
		// conn = DBUtil.getConnection();
		try {
			// ͨ��c3p0���ӳػ�ȡ���ݿ�����ͨ��
			conn = DataSourceConfig.getDataSource().getConnection();
			// ����SQL���
			// 3.����SQL���
			ps = conn.prepareStatement(sql);
			// 4.��ռλ����ֵ
			if (params != null) {
				// �����ɱ��������ռλ������һһ��ֵ
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 5.ִ��SQL
			rs = ps.executeQuery();
			// Ԫ���ݣ�����������������������������������ÿһ�е������ƣ�
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ��ѯ�����������ж�����
			int columnCount = rsmd.getColumnCount();
			// 6.��������
			if (rs.next()) {
				// rs.next()���жϵ�ǰ���Ƿ�����һ�У����򷵻�true
				// ����ָ��ָ����һ�У����ҽ���һ�е����ݱ��浽ResultSet
				// ѭ���ж�ֱ��û����һ��Ϊֹ,����false
				// ͨ�����䴴��һ������ʵ��,�����б���Ҫ��һ���޲����Ĺ��캯��
				T obj = cls.newInstance();
				// ���ݲ�ѯ������������ѭ��ȡ��ÿһ�ж�Ӧ��ֵ
				for (int i = 1; i <= columnCount; i++) {
					Object value = rs.getObject(i);// ��ȡÿһ�е�ֵ
					// ��ȡÿһ�е�������,sid ,sname ,ssex
					String columnName = rsmd.getColumnName(i).toLowerCase();
					// System.out.println(columnName+","+value);
					// ���valueֵΪ�գ�����Ҫ�Զ����е����Խ��и�ֵ
					if (value == null) {
						continue;// ������ǰѭ����ִ����һ��ѭ��
					}
					// ����������ͨ�������ȡ�������еĳ�Ա����
					if (hasField(cls, columnName)) {
						Field f = cls.getDeclaredField(columnName);
						// true��ʾ���ԶԶ����˽�����Խ��з���
						f.setAccessible(true);
						// ����ȡ����valueֵ���浽obj�����е�columnName��Ա������
						if (value instanceof BigDecimal) {
							BigDecimal val = (BigDecimal) value;
							if (f.getType().getName().equals("int")) {
								f.set(obj, val.intValue());
							} else {
								f.set(obj, val.doubleValue());
							}
						} else {
							f.set(obj, value); // setSid()
						}
					}
				}
				return obj;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7�ر���Դ
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * �жϳ�Ա�������Ƿ���ڶ�Ӧ�еĳ�Ա����
	 * 
	 * @param cls
	 * @param columnName
	 * @return
	 */
	private static boolean hasField(Class cls, String columnName) {
		// ��ȡ�����е����еĳ�Ա����
		Field field[] = cls.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			if (field[i].getName().equals(columnName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * select t2.*,rownum from(select t1.*,rownum num from(select * from t_mc
	 * where 1=1) t1 where rownum<= 15) t2 where t2.num>=11
	 */
	private final String QUERY_BEGIN = " select t2.*,rownum from (select t1.*,rownum num from  ( ";
	private final String QUERY_END = " ) t1 where rownum <= ?) t2 where t2.num >= ? ";
		
	/**
	 * 
	 * ��ҳ��ѯ
	 * 
	 * @param countSql
	 *            ��ѯ�ܵ��ܼ�¼sql
	 * @param querySql
	 *            �򵥲�ѯ���
	 * @param whereSql
	 *            ��ѯ������
	 * @param otherSql
	 *            ������sql
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @param currentPage
	 *            ��ǰҳ
	 * @param cls
	 *            ��Ӧ�������
	 * @param list
	 *            ռλ����Ӧ������
	 * @return ��ѯ���
	 */

	public <T> BasePage<T> queryByPage(StringBuilder countSql, StringBuilder querySql, StringBuilder whereSql,
			StringBuilder otherSql, int pageSize, int currentPage, Class cls, List list) {
		// 1��ѯ�ܵļ�¼��
		// �������
		countSql.append(whereSql.toString());
		int count = this.baseQueryForCount(countSql.toString(), list.toArray());
		// 2 ��ҳ��ѯ������
		// ��ѯ���������� select * from t_mc where 1=1 and mcname like '%abc%'
		querySql.append(whereSql.toString()); // ��ĳ���� ��ӻ����ӡ��ͷ���ͽ�β���
												// �����û��ֱ��ִ�� basequery��
		querySql.insert(0, QUERY_BEGIN); // ƴ�ӿ�ͷ���
		querySql.append(QUERY_END); // ƴ�ӽ�β���
		// 3 ����ҳ�����ռλ����ֵ
		list.add(pageSize * (currentPage)); // 15 ��
		list.add(pageSize * (currentPage-1)+ 1); // 11��ʼλ��
		List<T> queryList = this.baseQuery(querySql.toString(), cls, list
				.toArray()); /*
				  * ���Ƶ��ò�ѯ���� ����list����ת��Ϊ���� ����ռλ������ʱ��Ҫ���ɱ䳤�ȵĲ����б�
				  */ // ����ѯ���ݷ�װ��BasePage������
		BasePage<T> page = new BasePage<>();
		page.setSize(count);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setList(queryList);
		page.setTotalPage((count - 1) / pageSize + 1);
		// ����
		return page;
	}
}