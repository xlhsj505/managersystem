package com.picaas.comm;

import java.awt.List;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.management.Query;

import com.mchange.lang.ArrayUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	private static ComboPooledDataSource dataSource;
	static Connection conn;
	/*static{
		
		try {
			//�������ӳ�
			dataSource = new ComboPooledDataSource();
//			dataSource.setDriverClass("com.mysql.jdbc.Driver");
//			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/EMSDB");
			dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=EMSDB");
			dataSource.setUser("sa");
			dataSource.setPassword("123456");
			//�������ӳص��������
			dataSource.setMaxPoolSize(10);//���������
			dataSource.setMinPoolSize(1);//��С������
			dataSource.setInitialPoolSize(2);//��ʼ������
			dataSource.setMaxStatements(3);//���û���statements ��
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}*/
	
	public static Connection getConn() {
		 
			try {
				//1. ע������
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2. ��������
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=PICAAS", "sa", "123456");
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return conn;
	}
	
	//�ر����ݿ�����
	public static void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�ر�PreparedStatment��ResultSet
	public static void closeResultAndStatment(ResultSet result, 
			PreparedStatement stmt) {
		try {
			if (result != null) {
				result.close();				
			}
			if (stmt != null) {
				stmt.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/** ���ô˷���������ɶ����ݿ������ɾ���Ĳ���
	 * @return
	 */
	public static int executeUpdate(String sql, Object[] params) {
		Connection conn = getConn();
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("123");
		try {
			//�������
			//����sql��䣬��ʱ������ռλ��
			//�������
			pstmt = conn.prepareStatement(sql);
			//�ж��Ƿ���ռλ�����У����ռλ����ֵ
			if (params != null) {
				for (int i=1; i<=params.length; i++){
					pstmt.setObject(i, params[i-1]);
				}
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultAndStatment(null, pstmt);
			closeConn(conn);
		}
		return result;
	}
	
	/** ��ѯ 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(String sql, String[] params){
		
		ResultSet result = null;
		PreparedStatement pstmt = null;
		//������� 
		Connection conn = getConn();
		try {
			//����������
			pstmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i=0; i<params.length; i++){
					pstmt.setString(i+1, params[i]);
				}
			}
			result = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}
	
	/** ��ѯ 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(String sql, Map<Object, Object> params){
		
		ResultSet result = null;
		PreparedStatement pstmt = null;
		//������� 
		Connection conn = getConn();
		try {
			//����������
			pstmt = conn.prepareStatement(sql);
			if (params != null) {
				int i=0;
				Set<Object> keyset = params.keySet();
				for (Object k : keyset) {
					String key = (String)k;
//					System.out.println("map ֵ��"+params.get(key));
					pstmt.setObject(i+1,  params.get(key));
					i++;
				}
			}
			result = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}
	
}
