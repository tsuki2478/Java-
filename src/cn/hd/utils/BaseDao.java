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
 * 数据库操作的工具类
 * 
 * @author xzl
 *
 */
public class BaseDao {

	/**
	 * 数据库的增加，修改，删除的共有方法
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            可变参数 类似于数组 可变参数的一定要放在形参的末尾位置 可变参数中值必须和占位符的一一对应
	 * @return -1表示更新失败 其他的表示更新成功
	 */
	public static int baseUpdate(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		// 1.注册和加载数据库驱动程序
		try {
			// conn = DBUtil.getConnection();
			// 通过c3p0连接池获取数据库连接通道
			conn = DataSourceConfig.getDataSource().getConnection();
			// 3.构建SQL语句
			// 4.发送SQL语句
			ps = conn.prepareStatement(sql);
			// System.out.println(params!=null);
			// 取出可变参数中的值，对占位符一一赋值
			if (params != null) {
				// System.out.println(params.length);
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 5.执行SQL语句
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("操作失败！！！");
		} finally {
			// 6.关闭资源
			DBUtil.close(conn, ps);
		}
		return -1;
	}

	/**
	 * 查询表结构中满足条件的记录数
	 * 
	 * @param sql
	 *            需要执行的sql语句
	 * @param objs
	 *            sql语句中对应的占位符对应的参数
	 * @return -1 表示执行出错 其他值 表示记录的条数
	 */
	public static int baseQueryForCount(String sql, Object... objs) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			// 通过c3p0获取连接通道
			conn = DataSourceConfig.getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			// 对占位符进行赋值
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) {
					ps.setObject(i + 1, objs[i]);
				}
			}
			// 显示的执行sql语句
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			DBUtil.close(conn, ps, rs);
		}
		return -1;
	}

	/**
	 * 查询多个记录的方法
	 * 
	 * @param <T>
	 * @param sql
	 *            查询语句
	 * @param cls
	 *            Class对象
	 * @param params
	 *            可变参数
	 * @return
	 * 
	 */
	public static <T> List<T> baseQuery(String sql, Class<T> cls, Object... params) {
		List<T> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 1.注册和加载数据库驱动程序
		// 2.获取数据库连接通道

		try {
			// conn = DBUtil.getConnection();
			// 通过c3p0连接池获取数据库连接通道
			conn = DataSourceConfig.getDataSource().getConnection();
			// 构建SQL语句
			// 3.发送SQL语句
			ps = conn.prepareStatement(sql);
			// 4.给占位符赋值
			if (params != null) {
				// 遍历可变参数，对占位符进行一一赋值
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 5.执行SQL
			rs = ps.executeQuery();
			// 元数据：作用是用来描述结果集（结果集中列数，每一列的列名称）
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取查询出来的数据有多少列
			int columnCount = rsmd.getColumnCount();
			// 6.处理结果集
			while (rs.next()) {
				// rs.next()会判断当前行是否有下一行，有则返回true
				// 并将指针指向下一行，并且将下一行的数据保存到ResultSet
				// 循环判断直到没有下一行为止,返回false
				// 通过反射创建一个对象实例,对象中必须要有一个无参数的构造函数
				T obj = cls.newInstance();
				// 根据查询出来的列数，循环取出每一列对应的值
				for (int i = 1; i <= columnCount; i++) {
					Object value = rs.getObject(i);// 获取每一列的值
					// 获取每一列的列名称,sid ,sname ,ssex
					String columnName = rsmd.getColumnName(i).toLowerCase();
					// System.out.println(columnName+","+value);
					// 如果value值为空，则不需要对对象中的属性进行赋值
					if (value == null || "".equals(value)) {
						continue;// 跳出当前循环，执行下一次循环
					}
					// 根据列名称通过反射获取到对象中的成员属性
					if (hasField(cls, columnName)) {
						Field f = cls.getDeclaredField(columnName);
						// true表示可以对对象的私有属性进行访问
						f.setAccessible(true);
						// number对应的Java中BigDecimal
						if (value instanceof BigDecimal) {
							// value可能是小数或者是整数，所以这里需要做判断
							BigDecimal val = (BigDecimal) value;
							// 判断成员变量是什么类型的
							if (f.getType().getName().equals("int")) {
								// 将获取到的value值保存到obj对象中的对应的成员属性中
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
			// 7关闭资源
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * 查询单个记录的方法
	 * 
	 * @param <T>
	 * @param sql
	 *            查询语句
	 * @param cls
	 *            Class对象
	 * @param params
	 *            可变参数
	 * @return
	 * 
	 */
	public static <T> T queryForSingle(String sql, Class<T> cls, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 1.注册和加载数据库驱动程序
		// 2.获取数据库连接通道
		// conn = DBUtil.getConnection();
		try {
			// 通过c3p0连接池获取数据库连接通道
			conn = DataSourceConfig.getDataSource().getConnection();
			// 构建SQL语句
			// 3.发送SQL语句
			ps = conn.prepareStatement(sql);
			// 4.给占位符赋值
			if (params != null) {
				// 遍历可变参数，对占位符进行一一赋值
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 5.执行SQL
			rs = ps.executeQuery();
			// 元数据：作用是用来描述结果集（结果集中列数，每一列的列名称）
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取查询出来的数据有多少列
			int columnCount = rsmd.getColumnCount();
			// 6.处理结果集
			if (rs.next()) {
				// rs.next()会判断当前行是否有下一行，有则返回true
				// 并将指针指向下一行，并且将下一行的数据保存到ResultSet
				// 循环判断直到没有下一行为止,返回false
				// 通过反射创建一个对象实例,对象中必须要有一个无参数的构造函数
				T obj = cls.newInstance();
				// 根据查询出来的列数，循环取出每一列对应的值
				for (int i = 1; i <= columnCount; i++) {
					Object value = rs.getObject(i);// 获取每一列的值
					// 获取每一列的列名称,sid ,sname ,ssex
					String columnName = rsmd.getColumnName(i).toLowerCase();
					// System.out.println(columnName+","+value);
					// 如果value值为空，则不需要对对象中的属性进行赋值
					if (value == null) {
						continue;// 跳出当前循环，执行下一次循环
					}
					// 根据列名称通过反射获取到对象中的成员属性
					if (hasField(cls, columnName)) {
						Field f = cls.getDeclaredField(columnName);
						// true表示可以对对象的私有属性进行访问
						f.setAccessible(true);
						// 将获取到的value值保存到obj对象中的columnName成员属性中
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
			// 7关闭资源
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * 判断成员属性中是否存在对应列的成员属性
	 * 
	 * @param cls
	 * @param columnName
	 * @return
	 */
	private static boolean hasField(Class cls, String columnName) {
		// 获取对象中的所有的成员属性
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
	 * 分页查询
	 * 
	 * @param countSql
	 *            查询总的总记录sql
	 * @param querySql
	 *            简单查询语句
	 * @param whereSql
	 *            查询的条件
	 * @param otherSql
	 *            其他的sql
	 * @param pageSize
	 *            每页显示的条数
	 * @param currentPage
	 *            当前页
	 * @param cls
	 *            对应的类对象
	 * @param list
	 *            占位符对应的数据
	 * @return 查询结果
	 */

	public <T> BasePage<T> queryByPage(StringBuilder countSql, StringBuilder querySql, StringBuilder whereSql,
			StringBuilder otherSql, int pageSize, int currentPage, Class cls, List list) {
		// 1查询总的记录数
		// 添加条件
		countSql.append(whereSql.toString());
		int count = this.baseQueryForCount(countSql.toString(), list.toArray());
		// 2 分页查询的数据
		// 查询语句添加条件 select * from t_mc where 1=1 and mcname like '%abc%'
		querySql.append(whereSql.toString()); // 在某部分 添加话语。不印象开头语句和结尾语句
												// （如果没有直接执行 basequery）
		querySql.insert(0, QUERY_BEGIN); // 拼接开头语句
		querySql.append(QUERY_END); // 拼接结尾语句
		// 3 给分页语句中占位符赋值
		list.add(pageSize * (currentPage)); // 15 结
		list.add(pageSize * (currentPage-1)+ 1); // 11开始位置
		List<T> queryList = this.baseQuery(querySql.toString(), cls, list
				.toArray()); /*
				  * 类似调用查询方法 。把list集合转化为数组 传递占位符参数时需要个可变长度的参数列表。
				  */ // 将查询数据封装到BasePage对象中
		BasePage<T> page = new BasePage<>();
		page.setSize(count);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setList(queryList);
		page.setTotalPage((count - 1) / pageSize + 1);
		// 返回
		return page;
	}
}