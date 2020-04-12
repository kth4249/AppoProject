package com.kh.appoproject.advice.faq.model.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.appoproject.advice.faq.model.dao.FaqDao;
import com.kh.appoproject.advice.faq.model.vo.Faq;

public class FaqService {

	/** faq 글수  조회 Service
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount() throws Exception {
		Connection conn = getConnection();
		
		int listCount = new FaqDao().getListCount(conn);
		
		close(conn);
		return listCount;
	}

	
	/** faq 리스트 조회 Service
	 * @param currentPage
	 * @param limit
	 * @return list
	 * @throws Exception
	 */
	public List<Faq> selectList(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		
		List<Faq> list = new FaqDao().selectList(conn, currentPage, limit);
		
		close(conn);
		
		return list;
	}


	/** 서브리스트 글수 조회 Service
	 * @param faqClass
	 * @return listCount
	 * @throws Exception
	 */
	public int getSubListCount(String faqClass) throws Exception{
		Connection conn = getConnection();
		
		int listCount = new FaqDao().getSubListCount(conn, faqClass);
		
		close(conn);
		return listCount;
	}


	/** 서브리스트 가져오기
	 * @param currentPage
	 * @param limit
	 * @param faqClass
	 * @return
	 * @throws Exception
	 */
	public List<Faq> faqSubList(int currentPage, int limit, String faqClass) throws Exception{
		Connection conn = getConnection();
		List<Faq> list = new FaqDao().faqSubList(conn, currentPage, limit, faqClass);
		
		close(conn);
		return list;
	}


	/** 검색된Faq글수 조회용 Service
	 * @param searchKey
	 * @return listCount
	 * @throws Exception
	 */
	public int searchCount(String searchKey) throws Exception{
		Connection conn = getConnection();
		
		String condition = null;
		
		//condition = "'%'||'" + searchKey + "'||'%'";
		//이렇게 하려면 dao에서 ?에 넣는게 아니라 stmt로 쿼리문을 합쳐야됨
		
		condition = "%"+searchKey+"%";
		
		int listCount = new FaqDao().searchCount(conn, condition);
		
		close(conn);
		return listCount;
	}


	/** faq 검색 리스트 조회 service
	 * @param limit
	 * @param currentPage
	 * @param searchKey
	 * @return
	 * @throws Exception
	 */
	public List<Faq> searchList(int limit, int currentPage, String searchKey) throws Exception {
		Connection conn = getConnection();
		
		String condition = "%"+searchKey+"%";
		
		List<Faq> list = new FaqDao().searchList(conn, condition, currentPage, limit);
		
		close(conn);
		return list;
	}

}
