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
			//创建连接池
			dataSource = new ComboPooledDataSource();
//			dataSource.setDriverClass("com.mysql.jdbc.Driver");
//			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/EMSDB");
			dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=EMSDB");
			dataSource.setUser("sa");
			dataSource.setPassword("123456");
			//设置连接池的相关属性
			dataSource.setMaxPoolSize(10);//最大连接数
			dataSource.setMinPoolSize(1);//最小连接数
			dataSource.setInitialPoolSize(2);//初始连接数
			dataSource.setMaxStatements(3);//设置缓存statements 数
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}*/
	
	public static Connection getConn() {
		 
			try {
				//1. 注册驱动
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2. 创建连接
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
	
	//关闭数据库连接
	public static void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//关闭PreparedStatment、ResultSet
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

	/** 调用此方法可以完成对数据库的增、删、改操作
	 * @return
	 */
	public static int executeUpdate(String sql, Object[] params) {
		Connection conn = getConn();
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("123");
		try {
			//创建语句
			//定义sql语句，此时？代表占位符
			//操作句柄
			pstmt = conn.prepareStatement(sql);
			//判断是否有占位符，有，则给占位符赋值
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
	
	/** 查询 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(String sql, String[] params){
		
		ResultSet result = null;
		PreparedStatement pstmt = null;
		//获得连接 
		Connection conn = getConn();
		try {
			//创建语句对象
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
	
	/** 查询 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(String sql, Map<Object, Object> params){
		
		ResultSet result = null;
		PreparedStatement pstmt = null;
		//获得连接 
		Connection conn = getConn();
		try {
			//创建语句对象
			pstmt = conn.prepareStatement(sql);
			if (params != null) {
				int i=0;
				Set<Object> keyset = params.keySet();
				for (Object k : keyset) {
					String key = (String)k;
//					System.out.println("map 值："+params.get(key));
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
