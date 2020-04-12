package com.kh.appoproject.advice.faq.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.advice.faq.model.vo.Faq;

public class FaqDao {

	private Properties prop = null;
	
	public FaqDao() throws Exception{
		
		String fileName
		= FaqDao.class
			.getResource("/com/kh/appoproject/sql/faq/faq-query.properties")
			.getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
		
	}
	/** faq글수 조회용 dao
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next() ) {
				listCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	/** faq 리스트 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public List<Faq> selectList(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Faq> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Faq>();
			Faq faq = null;
			while(rset.next()) {
				faq = new Faq(rset.getInt("FAQ_NO"), 
						rset.getString("FAQ_CLASS"),
						rset.getString("FAQ_TITLE"), 
						rset.getString("FAQ_CONTENT")
						);
				list.add(faq);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	/** 서브리스트글수 조회용 Dao
	 * @param conn
	 * @param faqClass
	 * @return listCount
	 * @throws Exception
	 */
	public int getSubListCount(Connection conn, String faqClass) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("getSubListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faqClass);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	/** faq 서브목록 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param faqClass
	 * @return list
	 * @throws Exception
	 */
	public List<Faq> faqSubList(Connection conn, int currentPage, int limit, String faqClass) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Faq> list = null;
		
		String query = prop.getProperty("faqSubList");
		
		try {
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow   = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faqClass);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Faq>();
			Faq faq = null;
			
			while(rset.next()) {
				faq = new Faq(rset.getInt("FAQ_NO"), 
						rset.getString("FAQ_CLASS"),
						rset.getString("FAQ_TITLE"), 
						rset.getString("FAQ_CONTENT")
						);
				list.add(faq);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	/** 검색된 글수 조회용 Service
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int searchCount(Connection conn, String condition) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("searchCount");
		try {
			pstmt = conn.prepareStatement(query);
			//System.out.println(query);
			
			pstmt.setString(1, condition);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	/** faq 검색 리스트 조회 dao
	 * @param conn
	 * @param condition
	 * @param currentPage
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<Faq> searchList(Connection conn, String condition, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Faq> list = null;
		
		String query = prop.getProperty("searchList");
		
		try {
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, condition);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Faq>();
			Faq faq = null;
			while(rset.next()) {
				faq = new Faq(rset.getInt("FAQ_NO"), 
						rset.getString("FAQ_CLASS"),
						rset.getString("FAQ_TITLE"), 
						rset.getString("FAQ_CONTENT")
						);
				list.add(faq);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}

}
