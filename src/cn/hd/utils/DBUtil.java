package cn.hd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * �������ݿ�͹ر������Դ�Ĺ���
 * @author xzl
 *
 */
public class DBUtil {
	
	/**
	 * Oracle�����ļ���·��
	 */
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	/**
	 * ���ݿ��URL��ַ
	 */
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "j1612";  //���ݿ��¼�û�
	private static final String PASSWORD = "j1612";//���ݿ��¼����
	/**
	 * �������ݿ�Ĺ��÷���
	 * @return
	 * 		�������ݿ�����ͨ��
	 */
	public static Connection getConnection(){
		//1.ע��ͼ������ݿ���������
		try {
			//Class.forName(ORACLE_DRIVER);
			//2.��ȡ���ݿ�����ͨ��
			//return DriverManager.getConnection(URL, USER, PASSWORD);
			return DataSourceConfig.getDataSource().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("��ȡ��������ʧ�ܣ�");
		}
		return null;
	}
	
	/**
	 * �ر���ص���Դ
	 * @param conn  ����ͨ��
	 * @param ps    PreparedStatement�������ڷ���SQL���
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
			System.out.println(e.getMessage()+",�ر���Դʧ��");
		}
	}
	
	
	/**
	 * �ر���ص���Դ
	 * @param conn  ����ͨ��
	 * @param ps    PreparedStatement�������ڷ���SQL���
	 * @param rs   �����
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
			System.out.println(e.getMessage()+",�ر���Դʧ��");
		}
	}
	
}


