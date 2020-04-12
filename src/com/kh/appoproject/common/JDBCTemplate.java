package com.kh.appoproject.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	private static Connection conn = null;
	/*	Connection을 계속 안 닫고 이용하면 서버 과부하?
			싱글톤패턴을 포기하거나 DBCP를 이용하거나
			우리는 싱글톤패턴을 포기
			커넥션을 계속 끊을 수 있게 됨*/
	
	public static Connection getConnection() {
				
		try {
			
			if(conn == null || conn.isClosed()) { 
					
				Properties prop = new Properties();
				
				String fileName
					= JDBCTemplate.class
					  .getResource("/com/kh/appoproject/sql/driver.properties")
					  .getPath();
					// .java가 컴파일된 버전(.class)의 자원을 얻어와라 경로에 있는
				
				prop.load(new FileReader(fileName));
				
				// ojdbc6 라이브러리를 
				// WebContent/WEB-INF/lib 폴더에 추가 
				
				// jdbc 드라이버 로드
				Class.forName(prop.getProperty("driver"));
								
				// Connection 객체 생성
				conn = DriverManager
						.getConnection(prop.getProperty("url"), 
									   prop.getProperty("user"), 
									   prop.getProperty("password"));
				
				conn.setAutoCommit(false);
			}			
				
		} catch(Exception e) {
				
				e.printStackTrace();
				
		} 
		
		return conn;
		
	}
	

	public static void close(Statement stmt) { 
		
		try {
			
			if(stmt != null && !stmt.isClosed()) { 
				
				stmt.close();
				
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public static void close(ResultSet rset) {  

		try {
		
			if(rset != null && !rset.isClosed()) { 
			
				rset.close();
			
			}
		
		} catch(SQLException e) {
		
			e.printStackTrace();
		
		}
	}
	
	public static void close(Connection conn) { 

		try {
		
			if(conn != null && !conn.isClosed()) {
			
				conn.close();
			
			}
		
		} catch(SQLException e) {
		
			e.printStackTrace();
		
		}
	}
	

	public static void commit(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				// 현재 conn이 무언가를 참조하고 있고 통로가 닫혀있지 않다면
				
				conn.commit();
				
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	
	public static void rollback(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				
				conn.rollback();
				
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
	}	
	

}
