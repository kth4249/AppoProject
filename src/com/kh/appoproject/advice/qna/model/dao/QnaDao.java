package com.kh.appoproject.advice.qna.model.dao;

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
import com.kh.appoproject.advice.qna.model.dao.QnaDao;
import com.kh.appoproject.advice.qna.model.vo.Qna;

public class QnaDao {

private Properties prop = null;

	public QnaDao() throws Exception {
		
		String fileName
		// 컴파일된 버전		
		= QnaDao.class // 자원을 얻어와라()
			.getResource("/com/kh/appoproject/sql/qna/qna-query.properties")
			.getPath();//풀경로를 나타내라
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	/** 글번호가져오는 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		int qnaNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				qnaNo = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		
		return qnaNo;
	}

	/** qna 등록 dao
	 * @param conn
	 * @param qna
	 * @return
	 * @throws Exception
	 */
	public int insertQna(Connection conn, Qna qna) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qna.getQnaNo());
			pstmt.setString(2, qna.getQnaTitle());
			pstmt.setString(3, qna.getQnaContent());
			pstmt.setInt(4, qna.getMemberNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** qna 고객글수 조회용 Dao
	 * @param conn
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int memberNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("getListCount");
		// System.out.println("쿼리문 : " + query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
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

	/** qna 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param memberNo
	 * @return list
	 */
	public List<Qna> selectList(Connection conn, int currentPage, int limit, int memberNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Qna> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Qna>();
			Qna qna = null;
			
			while(rset.next()) {
				qna = new Qna(rset.getInt("QNA_NO"),
						rset.getString("QNA_TITLE"),
						rset.getString("QNA_CONTENT"),
						rset.getString("ANSWER_CONTENT")
						);
				list.add(qna);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/** qna 상세조회 Dao
	 * @param conn
	 * @param qnaNo
	 * @return
	 * @throws Exception
	 */
	public Qna selectQna(Connection conn, int qnaNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna qna = null;
		
		String query = prop.getProperty("selectQna");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				qna = new Qna(qnaNo,
						rset.getString("QNA_TITLE"),
						rset.getString("QNA_CONTENT"),
						rset.getString("ANSWER_CONTENT")
						);
						
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return qna;
	}

	/** qna 수정 dao
	 * @param conn
	 * @param qna
	 * @return
	 * @throws Exception
	 */
	public int updateQna(Connection conn, Qna qna) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qna.getQnaTitle());
			pstmt.setString(2, qna.getQnaContent());
			pstmt.setInt(3, qna.getQnaNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** qna 삭제용 Dao
	 * @param conn
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int deleteQna(Connection conn, int no) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 검색 qna글수 확인 dao
	 * @param conn
	 * @param condition
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int searchCount(Connection conn, String condition, int memberNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int listCount = 0;
		
		String query1 = prop.getProperty("searchCount");
		
		try {
			pstmt = conn.prepareStatement(query1 + condition);
			pstmt.setInt(1, memberNo);
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

	public List<Qna> searchQna(Connection conn, String condition, int memberNo, int limit, int currentPage) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Qna> list = null;
		
		String query1 = prop.getProperty("searchQna1");
		String query2 = prop.getProperty("searchQna2");
		
		try {
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query1+condition+query2);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			System.out.println(pstmt);
			System.out.println(query1+condition+query2);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Qna>();
			
			Qna qna = null;
			
			while(rset.next()) {
				qna = new Qna(rset.getInt("QNA_NO"),
						rset.getString("QNA_TITLE"),
						rset.getString("QNA_CONTENT"),
						rset.getString("ANSWER_CONTENT")
						);
				list.add(qna);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	
}
