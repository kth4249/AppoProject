package com.kh.appoproject.advice.qna.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.appoproject.advice.notice.model.dao.NoticeDao;
import com.kh.appoproject.advice.notice.model.vo.Notice;
import com.kh.appoproject.advice.qna.model.dao.QnaDao;
import com.kh.appoproject.advice.qna.model.vo.Qna;

public class QnaService {

	/** qna 등록용 service
	 * @param qna
	 * @return
	 * @throws Exception
	 */
	public int insertQna(Qna qna) throws Exception {
		
		Connection conn = getConnection();
		
		QnaDao qnaDao = new QnaDao();
		
		int result = 0;
		
		// 글번호 가져오기
		int qnaNo = qnaDao.selectNextNo(conn);
		// 글번호 테스트
		System.out.println(qnaNo);
		
		if(qnaNo > 0) {
			
			qna.setQnaNo(qnaNo);
			result = qnaDao.insertQna(conn, qna);
				
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	/** qna고객글수 조회용 service
	 * @param memberNo 
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int listCount = new QnaDao().getListCount(conn, memberNo);
		
		close(conn);
		return listCount;
	}

	/** qna 리스트 조회 Service
	 * @param currentPage
	 * @param limit
	 * @param memberNo
	 * @return list
	 */
	public List<Qna> selectList(int currentPage, int limit, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		List<Qna> list = new QnaDao().selectList(conn, currentPage, limit, memberNo);
		
		close(conn);
		
		return list;
	}

	/** qna 상세 조회 Service
	 * @param qnaNo
	 * @return qna
	 * @throws Exception
	 */
	public Qna selectQna(int qnaNo) throws Exception{
		Connection conn = getConnection();
		
		Qna qna = new QnaDao().selectQna(conn, qnaNo);
		
		close(conn);
		
		return qna;
	}

	/** qna 수정 화면용 Service
	 * @param no
	 * @return qna
	 * @throws Exception
	 */
	public Qna updateForm(int no) throws Exception{
		
		Connection conn = getConnection();
		
		// qna 상세조회
		Qna qna = new QnaDao().selectQna(conn, no);
		
		
		// DB에 저장된 내용을 textarea에 출력할 경우
		// <br>로 저장되어있는 부분을 \r\n으로 변경해야 함.
		qna.setQnaContent(
				qna.getQnaContent().replace("<br>", "\r\n"));
		
		close(conn);
		return qna;
	}

	/** qna 수정 service
	 * @param qna
	 * @return
	 * @throws Exception
	 */
	public int updateQna(Qna qna) throws Exception{
		Connection conn = getConnection();
		
		// 수정된 내용이 DB에 저장될 경우 개행문자 변경 필요
		qna.setQnaContent(
				qna.getQnaContent().replace("\r\n", "<br>"));
		
		int result = new QnaDao().updateQna(conn, qna);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return  result;
	}
 
	/** qna 삭제용 Service
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int deleteQna(int no) throws Exception{
		Connection conn = getConnection();
		
		int result = new QnaDao().deleteQna(conn, no);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		return result;
	}

	/** qna 글수 확인 Service
	 * @param memberNo
	 * @param searchKey
	 * @param searchValue
	 * @return listCount
	 */
	public int searchCount(int memberNo, String searchKey, String searchValue) throws Exception{
		
		Connection conn = getConnection();
		
		String condition = null;
		
		searchValue = "'%'||'" + searchValue + "'||'%'";
		switch(searchKey) {
		case "title" : condition = " QNA_TITLE LIKE " + searchValue; break;
		case "content" : condition = " QNA_CONTENT LIKE " + searchValue; break;
		case "titcont" : condition = " (QNA_TITLE LIKE " + searchValue +
									 " OR QNA_CONTENT LIKE " +searchValue + ") "; break;
		}
		
		int listCount = new QnaDao().searchCount(conn, condition, memberNo);
		close(conn);
		return listCount;
	}

	/** qna 검색용 Service
	 * @param searchKey
	 * @param searchValue
	 * @param limit
	 * @param currentPage
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Qna> searchQna(String searchKey, String searchValue, int limit, int currentPage, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		String condition = null;
		
		searchValue = "'%'||'" + searchValue + "'||'%'";
		
		switch(searchKey) {
		case "title" : condition = " QNA_TITLE LIKE " + searchValue + ") "; break;
		case "content" : condition = " QNA_CONTENT LIKE " + searchValue + ") "; break;
		case "titcont" : condition = " (QNA_TITLE LIKE " + searchValue +
									 " OR QNA_CONTENT LIKE " +searchValue + ")) "; break;
		}
		
		List<Qna> list = new QnaDao().searchQna(conn, condition, memberNo, limit, currentPage);
		
		close(conn);
		
		return list;
	}

}
