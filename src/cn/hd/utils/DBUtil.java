package cn.hd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 连接数据库和关闭相关资源的工具
 * @author xzl
 *
 */
public class DBUtil {
	
	/**
	 * Oracle驱动文件的路径
	 */
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	/**
	 * 数据库的URL地址
	 */
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "j1612";  //数据库登录用户
	private static final String PASSWORD = "j1612";//数据库登录密码
	/**
	 * 连接数据库的公用方法
	 * @return
	 * 		返回数据库连接通道
	 */
	public static Connection getConnection(){
		//1.注册和加载数据库驱动程序
		try {
			//Class.forName(ORACLE_DRIVER);
			//2.获取数据库连接通道
			//return DriverManager.getConnection(URL, USER, PASSWORD);
			return DataSourceConfig.getDataSource().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("获取数据连接失败！");
		}
		return null;
	}
	
	/**
	 * 关闭相关的资源
	 * @param conn  连接通道
	 * @param ps    PreparedStatement对象，用于发送SQL语句
	 */
	public static void close(Connection conn,PreparedStatement ps){
		try{
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+",关闭资源失败");
		}
	}
	
	
	/**
	 * 关闭相关的资源
	 * @param conn  连接通道
	 * @param ps    PreparedStatement对象，用于发送SQL语句
	 * @param rs   结果集
	 */
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+",关闭资源失败");
		}
	}
	
}


