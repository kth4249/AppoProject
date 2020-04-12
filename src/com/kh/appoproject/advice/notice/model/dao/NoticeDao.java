package com.kh.appoproject.advice.notice.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.advice.notice.model.vo.Notice;

public class NoticeDao {
	
	private Properties prop = null;
	
	public NoticeDao() throws Exception{
		
		String fileName
		= NoticeDao.class
			.getResource("/com/kh/appoproject/sql/notice/notice-query.properties")
			.getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	/** 전체 공지글 조회용 Dao
	 * @param conn
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> selectList(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> list = null;
		
		String query = prop.getProperty("selectList");
		
		
		try {
			
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			Notice notice = null;
			
			while(rset.next()) {
				notice = new Notice(rset.getInt("NOTICE_NO"), 
								rset.getString("NOTICE_CLASS"),
								rset.getString("NOTICE_TITLE"),
								rset.getDate("NOTICE_CREATEDATE")
								);
				
				list.add(notice);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/** 전체 게시글 수 조회용 Dao
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
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	/** 공지글 상세조회 Dao
	 * @param conn
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(Connection conn, int noticeNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		
		String query = prop.getProperty("selectNotice");
		System.out.println(query);
		System.out.println(noticeNo);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				notice = new Notice(noticeNo,
						rset.getString("NOTICE_TITLE"),
						rset.getString("NOTICE_CONTENT")
						);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return notice;
	}

	/** 공지사항 검색 조회용 Dao
	 * @param conn
	 * @param condition
	 * @param currentPage 
	 * @param limit 
	 * @return slist
	 * @throws Exception
	 */
	
	public List<Notice> searchNotice(Connection conn, String condition, int limit, int currentPage) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> list = null;
				
		String query1 = prop.getProperty("searchNotice1");
		String query2 = prop.getProperty("searchNotice2");
				
		try {
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query1+condition+query2);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			//System.out.println(query1+condition+query2);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			Notice notice = null;
			
			while(rset.next()) {
				notice = new Notice(rset.getInt("NOTICE_NO"), 
						rset.getString("NOTICE_CLASS"),
						rset.getString("NOTICE_TITLE"),
						rset.getDate("NOTICE_CREATEDATE")
						);
				list.add(notice);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	} 

	/** 검색 공지글수 확인 dao
	 * @param conn
	 * @param condition
	 * @return int countList
	 * @throws Exception
	 */
	public int searchCount(Connection conn, String condition) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		
		int listCount = 0;
		
		String query1 = prop.getProperty("searchCount");
				
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query1 + condition);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	

}
