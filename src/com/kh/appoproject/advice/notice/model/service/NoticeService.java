package com.kh.appoproject.advice.notice.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.appoproject.advice.notice.model.dao.NoticeDao;
import com.kh.appoproject.advice.notice.model.vo.Notice;

public class NoticeService {

	/** 공지사항 리스트 조회 Service
	 * @return List<Notice>
	 * @throws Exception
	 */
	public List<Notice> selectList(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		
		List<Notice> list = new NoticeDao().selectList(conn, currentPage, limit);
		
		close(conn);
		
		return list;
	}

	/** 전체 게시글수 조회 Service
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount() throws Exception{
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().getListCount(conn);
		
		close(conn);
		return listCount;
	}

	/** 공지글 상세 조회 Service
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(int noticeNo) throws Exception {
		Connection conn = getConnection();
		
		Notice notice = new NoticeDao().selectNotice(conn, noticeNo);
		
		close(conn);
		return notice;
	}

	/** 공지사항 검색용 Service
	 * @param searchKey
	 * @param searchValue
	 * @param currentPage 
	 * @param limit 
	 * @return slist
	 * @throws Exception
	 */
	public List<Notice> searchNotice(String searchKey, String searchValue, int limit, int currentPage) throws Exception {
		Connection conn = getConnection();
		
		String condition = null;
		
		searchValue = "'%'||'" + searchValue + "'||'%'";
		
		switch(searchKey) {
		case "title" : condition = " NOTICE_TITLE LIKE " + searchValue + ") "; break;
		case "content" : condition = " NOTICE_CONTENT LIKE " + searchValue + ") "; break;
		case "titcont" : condition = " (NOTICE_TITLE LIKE " + searchValue +
									 " OR NOTICE_CONTENT LIKE " +searchValue + ")) "; break;
		}
		
		List<Notice> list = new NoticeDao().searchNotice(conn, condition, limit, currentPage);
		
		close(conn);
		
		return list;
	}

	/** 검색 공지글수 확인 service
	 * @param searchKey
	 * @param searchValue
	 * @return listCount
	 * @throws Exception
	 */
	public int searchCount(String searchKey, String searchValue) throws Exception {
		
		Connection conn = getConnection();
		
		String condition = null;
		
		searchValue = "'%'||'" + searchValue + "'||'%'";
		
		switch(searchKey) {
		case "title" : condition = " NOTICE_TITLE LIKE " + searchValue; break;
		case "content" : condition = " NOTICE_CONTENT LIKE " + searchValue; break;
		case "titcont" : condition = " (NOTICE_TITLE LIKE " + searchValue +
									 " OR NOTICE_CONTENT LIKE " +searchValue + ") "; break;
		}
		
		int listCount = new NoticeDao().searchCount(conn, condition);
		
		close(conn);
		return listCount;
	}

	
}
