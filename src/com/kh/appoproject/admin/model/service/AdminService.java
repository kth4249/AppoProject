package com.kh.appoproject.admin.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.appoproject.admin.model.dao.AdminDao;
import com.kh.appoproject.admin.model.vo.Destination;
import com.kh.appoproject.admin.model.vo.FAQ;
import com.kh.appoproject.admin.model.vo.Image;
import com.kh.appoproject.admin.model.vo.Member;
import com.kh.appoproject.admin.model.vo.Notice;
import com.kh.appoproject.admin.model.vo.Product;
import com.kh.appoproject.admin.model.vo.ProductItemDevice;
import com.kh.appoproject.admin.model.vo.ProductOrder;

import static com.kh.appoproject.common.JDBCTemplate.*;

public class AdminService {

	/** 모든 회원 정보 Service
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMember(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<Member> mList = new AdminDao().selectMember(conn,currentPage,limit);
		close(conn);
		return mList;
	}

	/** 아이디로 회원정보 조회 Service
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member selectMemberInfo(String memberId) throws Exception{
		Connection conn = getConnection();
		Member member = new AdminDao().selectMemberInfo(conn,memberId);
		close(conn);
		return member;
	}

	/** 회원삭제 Service
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(String memberId) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().deleteMember(conn,memberId);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}

	/** 패널티 추가 Service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePenalty(int memberNo,String penaltyReason) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().updatePenalty(conn,memberNo,penaltyReason);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}

	/** 회원 검색용 Service
	 * @param searchKey
	 * @param searchContent
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> searchMember(String searchKey, String searchContent,int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		String condition = "";
		
		searchContent = "'%' || '" + searchContent + "' || '%'";
		
		switch(searchKey) {
		case "memberId" : condition = " MEMBER_ID LIKE " + searchContent; break;
		case "memberNM" : condition = " MEMBER_NM LIKE " + searchContent; break;
		case "memberEmail" : condition = " MEMBER_EMAIL LIKE " + searchContent; break;
		}
		
		List<Member> mList = new AdminDao().searchMember(conn,condition,currentPage,limit);
		close(conn);
		return mList;
	}

	/** 전체 회원 수 조회용 Service
	 * @return result
	 * @throws Exception
	 */
	public int getListCount() throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().getListCount(conn);
		close(conn);
		return result;
	}

	/** 오늘 등록한 회원수 Service
	 * @param today
	 * @return result
	 * @throws Exception
	 */
	public int enrollMemberCount(String today) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().enrollMemberCount(conn,today);
		close(conn);
		return result;
	}

	/** 오늘 추가된 거래수 Service
	 * @param today
	 * @return result
	 * @throws Exception
	 */
	public int addPaymentCount(String today) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().addPaymentCount(conn,today);
		close(conn);
		return result;
	}

	/** 미검수된 거래수 Service
	 * @return result
	 * @throws Exception
	 */
	public int noCheckCount() throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().noCheckCount(conn);
		close(conn);
		return result;
	}

	/** 미구매확정 거래수 Service
	 * @return result
	 * @throws Exception
	 */
	public int confirmCompleteCount() throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().confirmCompleteCount(conn);
		close(conn);
		return result;
	}

	/** 미답변 문의수 Service
	 * @return result
	 * @throws Exception
	 */
	
	public int noAnswerCount() throws Exception{
		Connection conn = getConnection();
		int answerCount = new AdminDao().noAnswerCount(conn);
		int qnaCount = new AdminDao().noAnswerCountOther(conn);
		int result = qnaCount - answerCount;
		close(conn);
		return result;
	}

	/** 검색된 회원수 조회 Service
	 * @param searchKey
	 * @param searchContent
	 * @param currentPage
	 * @param limit
	 * @return result
	 * @throws Exception
	 */
	public int searchMemberCount(String searchKey, String searchContent) throws Exception{
		Connection conn = getConnection();
		String condition = "";
		
		searchContent = "'%' || '" + searchContent + "' || '%'";
		
		switch(searchKey) {
		case "memberId" : condition = " MEMBER_ID LIKE " + searchContent; break;
		case "memberNM" : condition = " MEMBER_NM LIKE " + searchContent; break;
		case "memberEmail" : condition = " MEMBER_EMAIL LIKE " + searchContent; break;
		}
		
		int result = new AdminDao().searchMemberCount(conn,condition);
		close(conn);
		return result;
	}

	/** 전체 거래수 조회 Service
	 * @return result
	 * @throws Exception
	 */
	public int selectPaymentCount() throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().selectPaymentCount(conn);
		close(conn);
		return result;
	}

	/** 전체 거래 조회 Service
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectPayment(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<ProductOrder> pList = new AdminDao().selectPayment(conn,currentPage,limit);
		close(conn);
		return pList;
	}

	/** 회원의 패널티 횟수 조회 Service
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int penaltyCount(int memberNo) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().penaltyCount(conn,memberNo);
		close(conn);
		return result;
	}

	/**  오늘 가입한 회원 조회 Service
	 * @param today
	 * @param currentPage
	 * @param limit
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> enrollMemberToday(String today, int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<Member> mList = new AdminDao().enrollMemberToday(conn,currentPage,limit,today);
		close(conn);
		return mList;
	}

	/** 블라인드 처리가 안된 3회이상 신고 게시글 조회 Service
	 * @return result
	 * @throws Exception
	 */
	public int overReportCount() throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().overReportCount(conn);
		close(conn);
		return result;
	}
 
	/** 거래 상세 조회 Dao
	 * @param productNo
	 * @return orderInfo
	 * @throws Exception
	 */
	public ProductOrder productOrderInfo(int productNo) throws Exception{
		Connection conn = getConnection();
		ProductOrder orderInfo = new AdminDao().productInfo(conn,productNo);
		close(conn);
		return orderInfo;
	}

	/** 상품 상세 조회 Dao
	 * @param productNo
	 * @return deviceInfo
	 * @throws Exception
	 */
	public ProductItemDevice deviceInform(int productNo) throws Exception{
		Connection conn = getConnection();
		ProductItemDevice deviceInfo = new AdminDao().deviceInform(conn,productNo);
		close(conn);
		return deviceInfo;
	}

	/** 배송지 조회하기
	 * @param memberNo
	 * @return destination
	 * @throws Exception
	 */
	public Destination selectDestination(int memberNo,String orderDestination) throws Exception{
		Connection conn = getConnection();
		Destination destination = new AdminDao().selectDestination(conn,memberNo,orderDestination);
		close(conn);
		return destination;
	}


	/** 거래 상세정보 검수 변경 Service
	 * @param orderNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePaymentCheck(int orderNo,char paymentCheck) throws Exception{
		Connection conn = getConnection();
		String paymentCheck1 = paymentCheck + "";
		int result = new AdminDao().updatePaymentCheck(conn,orderNo,paymentCheck1);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}

	/** 상품 이미지 조회 Service
	 * @param productNo
	 * @return imgPath
	 * @throws Exception
	 */
	public String selectProductImage(int productNo) throws Exception{
		Connection conn = getConnection();
		String imgPath = new AdminDao().selectProductImage(conn,productNo);
		close(conn);
		return imgPath;
	}

	/** 블라인드가 안된 신고3회이상 신고게시판 조회
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<Product> selectOverDeclare(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<Product> pList = new AdminDao().selectOverDeclare(conn,currentPage,limit);
		close(conn);
		return pList;
	}

	/** 오늘 추가된 거래 조회 Service
	 * @param today
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectTodayPayment(String today, int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<ProductOrder> pList = new AdminDao().selectTodayPayment(conn,today,currentPage,limit);
		close(conn);
		return pList;
	}

	/** 미검수된 거래 조회 Service
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectNoCheckPayment(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<ProductOrder> pList = new AdminDao().selectNoCheckPayment(conn,currentPage,limit);
		close(conn);
		return pList;
	}

	/** 구매확정(결재중)된 거래 조회 Service
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectConfirmComplete(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<ProductOrder> pList = new AdminDao().selectConfirmComplete(conn,currentPage,limit);
		close(conn);
		return pList;
	}

	/** 검색된 거래수 조회 Service
	 * @param searchKey
	 * @param searchContent
	 * @return result
	 * @throws Exception
	 */
	public int searchPaymentCount(String searchKey, String searchContent) throws Exception{
		Connection conn = getConnection();
		String condition = "";
		
		searchContent = "'%' || '" + searchContent + "' || '%'";
		
		switch(searchKey) {
		case "BUYERID" : condition = " BUYERID LIKE " + searchContent; break;
		case "SELLERID" : condition = " SELLERID LIKE " + searchContent; break;
		}
		
		int result = new AdminDao().searchPaymentCount(conn,condition);
		close(conn);
		return result;
	}

	/** 결재관리 검색 조회 Service
	 * @param currentPage
	 * @param limit
	 * @param searchKey
	 * @param searchContent
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> searchUserPayment(int currentPage, int limit, String searchKey, String searchContent) throws Exception{
		Connection conn = getConnection();
		String condition = "";
		
		searchContent = "'%' || '" + searchContent + "' || '%'";
		
		switch(searchKey) {
		case "BUYERID" : condition = " BUYERID LIKE " + searchContent; break;
		case "SELLERID" : condition = " SELLERID LIKE " + searchContent; break;
		}
		
		List<ProductOrder> pList = new AdminDao().searchUserPayment(conn,condition,currentPage,limit);
		close(conn);
		return pList;
	}

	/** 지정된 상품게시글 정보 조회 Service
	 * @param productNo
	 * @return product
	 * @throws Exception
	 */
	public Product selectProduct(int productNo) throws Exception{
		Connection conn = getConnection();
		Product product = new AdminDao().selectProduct(conn,productNo);
		close(conn);
		return product;
	}

	/** 상품 게시글 모든 이미지 조회 Service
	 * @param productNo
	 * @return files
	 * @throws Exception
	 */
	public ArrayList<Image> selectAllImage(int productNo) throws Exception{
		Connection conn = getConnection();
		ArrayList<Image> files = new AdminDao().selectAllImage(conn,productNo);
		close(conn);
		return files;
	}

	
	
	/** 결재완료 Service
	 * @param orderNo
	 * @return result
	 * @throws Exception
	 */
	public int paymentFinish(int orderNo) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().paymentFinish(conn,orderNo);
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}

	/** 시세변경
	 * @param itemCode
	 * @param marketPrice
	 * @return result
	 * @throws Exception
	 */
	public int changeMarketPrice(int itemCode, int marketPrice) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().changeMarketPrice(conn,itemCode,marketPrice);
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}
	
	
	/** 공지사항 게시글 수 조회 Service
	 * @return listCount
	 * @throws Exception
	 */
	public int getNoticeCount() throws Exception{
		Connection conn = getConnection();
		
		int listCount = new AdminDao().getNoticeCount(conn);
		
		close(conn);
		return listCount;
	}

	/** 공지사항 리스트 조회용 Service
	 * @param currentPage
	 * @param limit
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> noticeList(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<Notice> nList = new AdminDao().noticeList(conn, currentPage, limit);
		
		close(conn);
		return nList;
	}

	/** 공지사항 등록용 Service
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	public int noticeInsert(Notice notice) throws Exception{
		Connection conn = getConnection();
		
		AdminDao adminDao = new AdminDao();
		int result = 0;
		
		int noticeNo = adminDao.noticeNextNo(conn);
		
		if(noticeNo > 0) {
			result = 0;
			notice.setNoticeNo(noticeNo);
			result = adminDao.noticeInsert(conn, notice);
			
			if(result > 0) commit(conn);
			else			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	/** 공지사항 수정 조회용 Service
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(int noticeNo) throws Exception{
		Connection conn = getConnection();
		Notice notice = new AdminDao().selectNotice(conn, noticeNo);
		
		close(conn);
		return notice;
	}

	/** 공지사항 수정용 Service
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	public int noticeUpdate(Notice notice) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().noticeUpdate(conn, notice);
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 공지사항 삭제용 Service
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int noticeDelete(int noticeNo) throws Exception{
		Connection conn = getConnection();
		
		int result = new AdminDao().noticeDelete(conn, noticeNo);
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}

	/**FAQ 게시글 수 조회 Service
	 * @return listCount
	 * @throws Exception
	 */
	public int getFAQCount() throws Exception{
		Connection conn = getConnection();
		
		int listCount = new AdminDao().getFAQCount(conn);
		
		close(conn);
		return listCount;
	}

	/** FAQ 리스트 조회 Service
	 * @param currentPage
	 * @param limit
	 * @return fList
	 * @throws Exception
	 */
	public List<FAQ> faqList(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<FAQ> fList = new AdminDao().faqList(conn, currentPage, limit);
		
		close(conn);
		return fList;
		
	}

	/** FAQ 등록
	 * @param faq
	 * @return
	 * @throws Exception
	 */
	public int faqEnroll(FAQ faq) throws Exception{
		Connection conn = getConnection();
		AdminDao adminDao = new AdminDao();
		int result = 0;
		int faqNo = adminDao.faqNextNo(conn);
		
		if(faqNo > 0) {
			System.out.println(faqNo);
			faq.setFaqNo(faqNo);
			result = new AdminDao().faqEnroll(conn, faq);
			
			if(result>0)	commit(conn);
			else			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	/** 정렬된 FAQ 조회
	 * @param faqClass
	 * @return
	 * @throws Exception
	 */
	public int getFAQSortCount(String faqClass) throws Exception{
		Connection conn = getConnection();
		
		int listCount = new AdminDao().getFAQSortCount(conn, faqClass);
		
		close(conn);
		return listCount;

	}

	public List<FAQ> faqSortList(int currentPage, int limit, String faqClass) throws Exception{
		Connection conn = getConnection();
		List<FAQ> fList = new AdminDao().faqSortList(conn, currentPage, limit, faqClass);
		
		close(conn);
		return fList;
	}

	public FAQ selectFAQ(int faqNo) throws Exception{
		Connection conn = getConnection();
		FAQ faq = new AdminDao().selectFAQ(conn, faqNo);
		
		close(conn);
		return faq;
	}

	public int faqModify(FAQ faq) throws Exception{
		Connection conn = getConnection();
		int result = new AdminDao().faqModify(conn, faq);
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int faqDelete(int faqNo) throws Exception{
		Connection conn = getConnection();
		
		int result = new AdminDao().faqDelete(conn, faqNo);
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}

	public int getBoardCount() throws Exception{
		Connection conn = getConnection();
		
		int listCount = new AdminDao().getBoardCount(conn);
		
		close(conn);
		return listCount;
	}

	
	
	
	/** 게시글 관리 Service
	 * @param currentPage
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<Product> boardList(int currentPage, int limit) throws Exception{
		Connection conn = getConnection();
		List<Product> pList = new AdminDao().boardList(conn, currentPage, limit);
		
		close(conn);
		return pList;
	}

	public int boardDelete(int productNo) throws Exception{
		Connection conn = getConnection();
		
		int result = new AdminDao().boardDelete(conn, productNo);
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}
	
	

}
