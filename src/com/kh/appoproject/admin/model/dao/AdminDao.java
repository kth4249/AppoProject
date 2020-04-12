package com.kh.appoproject.admin.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.admin.model.vo.Destination;
import com.kh.appoproject.admin.model.vo.FAQ;
import com.kh.appoproject.admin.model.vo.Image;
import com.kh.appoproject.admin.model.vo.Member;
import com.kh.appoproject.admin.model.vo.Notice;
import com.kh.appoproject.admin.model.vo.Product;
import com.kh.appoproject.admin.model.vo.ProductItemDevice;
import com.kh.appoproject.admin.model.vo.ProductOrder;

import static com.kh.appoproject.common.JDBCTemplate.*;

public class AdminDao {
	private Properties prop = null;
	
	public AdminDao() throws Exception{
		String filename = AdminDao.class.getResource("/com/kh/appoproject/sql/admin/admin-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(filename));
	}

	/** 모든 회원 정보 조회 Dao
	 * @param conn
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMember(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		String query = prop.getProperty("selectMember");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
	
			mList = new ArrayList<Member>();
			Member member = null;
			while(rset.next()) {
				int memberNo = rset.getInt("MEMBER_NO");
				String memberId = rset.getString("MEMBER_ID");
				String memberNM = rset.getString("MEMBER_NM");
				String memberPhone = rset.getString("MEMBER_PHONE");
				String memberEmail = rset.getString("MEMBER_EMAIL");
				
				member = new Member(memberNo, memberId, memberNM, memberPhone, memberEmail);
				mList.add(member);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return mList;
	}

	/** 아이디로 회원조회 Dao
	 * @param conn
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member selectMemberInfo(Connection conn, String memberId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberInfo");
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				int memberNo = rset.getInt("MEMBER_NO");
				String memberNM = rset.getString("MEMBER_NM");
				String memberPhone = rset.getString("MEMBER_PHONE");
				String memberEmail = rset.getString("MEMBER_EMAIL");
				Date memberEnrollDate = rset.getDate("MEMBER_ENROLLDATE");
				String memberAccount = rset.getString("MEMBER_ACCOUNT");
				String memberAddr = rset.getString("MEMBER_ADDR");
				
				member = new Member(memberNo, memberId, memberNM, memberPhone, memberEmail, memberAddr, memberEnrollDate, memberAccount);;
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	/** 회원삭제 Dao
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(Connection conn, String memberId) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	/** 패널티 추가 Dao
	 * @param conn
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePenalty(Connection conn, int memberNo, String penaltyReason) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePenalty");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, penaltyReason);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	/** 회원 검색용 Dao
	 * @param conn
	 * @param condition
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> searchMember(Connection conn, String condition, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		Member member = null;
		String query1 = prop.getProperty("searchMember1");
		String query2 = prop.getProperty("searchMember2");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = conn.prepareStatement(query1 + condition + query2);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			mList = new ArrayList<Member>();
			while(rset.next()) {
				int memberNo = rset.getInt("MEMBER_NO");
				String memberId = rset.getString("MEMBER_ID");
				String memberNM = rset.getString("MEMBER_NM");
				String memberPhone = rset.getString("MEMBER_PHONE");
				String memberEmail = rset.getString("MEMBER_EMAIL");
				Date memberEnrollDate = rset.getDate("MEMBER_ENROLLDATE");
				String memberAccount = rset.getString("MEMBER_ACCOUNT");
				member = new Member(memberNo, memberId, memberNM, memberPhone, memberEmail, memberEnrollDate, memberAccount);
				mList.add(member);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return mList;
	}

	/** 전체 회원수 조회용 Dao
	 * @param conn
	 * @return result
	 */
	public int getListCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("getListCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 오늘 등록한 회원수 Dao
	 * @param conn
	 * @param today
	 * @return result
	 * @throws Exception
	 */
	public int enrollMemberCount(Connection conn, String today) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("enrollMemberCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, today);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	/** 오늘 추가된 거래 Dao
	 * @param conn
	 * @param today
	 * @return result
	 * @throws Exception
	 */
	public int addPaymentCount(Connection conn, String today) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("addPaymentCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, today);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	/** 미검수된 거래수 Dao
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int noCheckCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("noCheckCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} 
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 미구매확정 거래수 Dao
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int confirmCompleteCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("confirmCompleteCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 1:1문의 질문수 조회 Dao
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	
	public int noAnswerCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("noAnswerCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 검색한 회원 수 조회 Dao
	 * @param conn
	 * @param condition
	 * @param currentPage
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public int searchMemberCount(Connection conn, String condition) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query1 = prop.getProperty("searchMemberCount1");
		String query2 = prop.getProperty("searchMemberCount2");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query1 + condition + query2);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 전체 거래수 조회 Dao
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int selectPaymentCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("selectPaymentCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 전체 거래 조회 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectPayment(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductOrder> pList = null;
		String query = prop.getProperty("selectPayment");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
	
			pList = new ArrayList<ProductOrder>();
			ProductOrder productOrder = null;
			while(rset.next()) {
				
				int orderNo = rset.getInt("ORDER_NO");
				char orderState = rset.getString("ORDER_STATE").charAt(0);
				String orderDestination = rset.getString("ORDER_DESTINATION");
				char orderCheck = rset.getString("ORDER_CHECK").charAt(0);
				Date orderDate = rset.getDate("ORDER_DATE");
				char orderMethod = rset.getString("ORDER_METHOD").charAt(0);
				String importNo = rset.getString("IMPORT_NO");
				char orderConfirm = rset.getString("ORDER_CONFIRM").charAt(0);
				String buyerId = rset.getString("BUYERID");
				String sellerId = rset.getString("SELLERID");
				char productForm = rset.getString("PRODUCT_FORM").charAt(0);
				int productCode = rset.getInt("PRODUCT_CODE");
				int price = 0;
				if(productForm == 'A') {
					price = rset.getInt("AUCTION_RESERVE_PRICE");
				} else {
					price = rset.getInt("BASIC_PRICE");
				}
				
				productOrder = new ProductOrder(orderNo, orderState, orderDestination, orderCheck, orderDate, orderMethod, importNo, orderConfirm, productCode, buyerId, sellerId, price);
				pList.add(productOrder);
				
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}

	/** 회원 패널티 횟수 조회 Dao
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int penaltyCount(Connection conn, int memberNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("penaltyCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	
	/** 오늘 가입한 회원 조회 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param today
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> enrollMemberToday(Connection conn, int currentPage, int limit, String today) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		String query = prop.getProperty("enrollMemberToday");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, today);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
	
			mList = new ArrayList<Member>();
			Member member = null;
			while(rset.next()) {
				String memberId = rset.getString("MEMBER_ID");
				String memberNM = rset.getString("MEMBER_NM");
				String memberPhone = rset.getString("MEMBER_PHONE");
				String memberEmail = rset.getString("MEMBER_EMAIL");
				
				member = new Member(memberId, memberNM, memberPhone, memberEmail);
				mList.add(member);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return mList;
	}

	/** 블라인드 처리가 안된 3회이상 신고 게시글 수 조회 Dao
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int overReportCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("overReportCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 거래 상세 조회 Dao
	 * @param conn
	 * @param productNo
	 * @return orderInfo
	 * @throws Exception
	 */
	public ProductOrder productInfo(Connection conn, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProductOrder orderInfo = null;
		String query = prop.getProperty("productInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				int orderNo = rset.getInt("ORDER_NO");
				char orderState = rset.getString("ORDER_STATE").charAt(0);
				String orderDestination = rset.getString("ORDER_DESTINATION");
				char orderCheck = rset.getString("ORDER_CHECK").charAt(0);
				Date orderDate = rset.getDate("ORDER_DATE");
				char orderMethod = rset.getString("ORDER_METHOD").charAt(0);
				String importNo = rset.getString("IMPORT_NO");
				char orderConfirm = rset.getString("ORDER_CONFIRM").charAt(0);
				String buyerId = rset.getString("BUYERID");
				String sellerId = rset.getString("SELLERID");
				char productForm = rset.getString("PRODUCT_FORM").charAt(0);
				int productCode = rset.getInt("PRODUCT_CODE");
				int price = 0;
				if(productForm == 'A') {
					price = rset.getInt("AUCTION_RESERVE_PRICE");
				} else {
					price = rset.getInt("BASIC_PRICE");
				}
				
				orderInfo = new ProductOrder(orderNo, orderState, orderDestination, orderCheck, orderDate, orderMethod, importNo, orderConfirm, productCode, buyerId, sellerId, price);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		return orderInfo;
	}

	/** 상품 상세 조회 Dao
	 * @param conn
	 * @param productNo
	 * @return deviceInfo
	 * @throws Exception
	 */
	public ProductItemDevice deviceInform(Connection conn, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProductItemDevice deviceInfo = null;
		String query = prop.getProperty("deviceInform");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				String itemName = rset.getString("ITEM_NAME");
				String itemInfo = rset.getString("ITEM_INFO");
				String deviceName = rset.getString("DEVICE_NAME");
				
				deviceInfo = new ProductItemDevice(itemName, itemInfo, deviceName);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return deviceInfo;
	}

	/** 배송지 조회용 Dao
	 * @param conn
	 * @param memberNo
	 * @return destination
	 * @throws Exception
	 */
	public Destination selectDestination(Connection conn, int memberNo,String orderDestination) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Destination destination = null;
		System.out.println(memberNo);
		String query = prop.getProperty("selectDestination");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				int destinationNo = rset.getInt("DESTINATION_NO");
				String destinationContact = rset.getString("DESTINATION_CONTACT");
				String destinationNote = rset.getString("DESTINATION_NOTE");
				String destinationName = rset.getString("DESTINATION_RECEIBER");
				
				destination = new Destination(destinationNo, orderDestination, destinationContact, destinationNote, destinationName, memberNo);
				System.out.println(destination);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return destination;
	}

	/** 거래 상세보기 검수 변경 Dao
	 * @param conn
	 * @param orderNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePaymentCheck(Connection conn, int orderNo, String paymentCheck) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePaymentCheck");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, paymentCheck);
			pstmt.setInt(2, orderNo);
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	/** 상품 이미지 조회 Dao
	 * @param conn
	 * @param productNo
	 * @return imagePath
	 * @throws Exception
	 */
	public String selectProductImage(Connection conn, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String imagePath = null;
		String query = prop.getProperty("selectProductImage");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				imagePath = rset.getString("IMAGE_PATH");
			}
			
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return imagePath;
	}

	/** 블라인드가 안된 3회이상 신고게시판 조회 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<Product> selectOverDeclare(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> pList = null;
		String query = prop.getProperty("selectOverDeclare");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
	
			pList = new ArrayList<Product>();
			Product product = null;
			while(rset.next()) {
				int productNo = rset.getInt("PRODUCT_NO");
				String productTitle = rset.getString("PRODUCT_TITLE");
				String memberId = rset.getString("MEMBER_ID");
				product = new Product(productNo, productTitle, memberId);
				pList.add(product);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}

	/** 오늘 추가된 거래 조회 Dao
	 * @param conn
	 * @param today
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectTodayPayment(Connection conn, String today, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductOrder> pList = null;
		String query = prop.getProperty("selectTodayPayment");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, today);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			pList = new ArrayList<ProductOrder>();
			ProductOrder productOrder = null;
			while(rset.next()) {
				int orderNo = rset.getInt("ORDER_NO");
				char orderState = rset.getString("ORDER_STATE").charAt(0);
				String orderDestination = rset.getString("ORDER_DESTINATION");
				char orderCheck = rset.getString("ORDER_CHECK").charAt(0);
				Date orderDate = rset.getDate("ORDER_DATE");
				char orderMethod = rset.getString("ORDER_METHOD").charAt(0);
				String importNo = rset.getString("IMPORT_NO");
				char orderConfirm = rset.getString("ORDER_CONFIRM").charAt(0);
				String buyerId = rset.getString("BUYERID");
				String sellerId = rset.getString("SELLERID");
				char productForm = rset.getString("PRODUCT_FORM").charAt(0);
				int productCode = rset.getInt("PRODUCT_CODE");
				int price = 0;
				if(productForm == 'A') {
					price = rset.getInt("AUCTION_RESERVE_PRICE");
				} else {
					price = rset.getInt("BASIC_PRICE");
				}
				
				productOrder = new ProductOrder(orderNo, orderState, orderDestination, orderCheck, orderDate, orderMethod, importNo, orderConfirm, productCode, buyerId, sellerId, price);
				pList.add(productOrder);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}

	/** 미검수 거래 조회 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectNoCheckPayment(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductOrder> pList = null;
		String query = prop.getProperty("selectNoCheckPayment");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			pList = new ArrayList<ProductOrder>();
			ProductOrder productOrder = null;
			while(rset.next()) {
				int orderNo = rset.getInt("ORDER_NO");
				char orderState = rset.getString("ORDER_STATE").charAt(0);
				String orderDestination = rset.getString("ORDER_DESTINATION");
				char orderCheck = rset.getString("ORDER_CHECK").charAt(0);
				Date orderDate = rset.getDate("ORDER_DATE");
				char orderMethod = rset.getString("ORDER_METHOD").charAt(0);
				String importNo = rset.getString("IMPORT_NO");
				char orderConfirm = rset.getString("ORDER_CONFIRM").charAt(0);
				String buyerId = rset.getString("BUYERID");
				String sellerId = rset.getString("SELLERID");
				char productForm = rset.getString("PRODUCT_FORM").charAt(0);
				int productCode = rset.getInt("PRODUCT_CODE");
				int price = 0;
				if(productForm == 'A') {
					price = rset.getInt("AUCTION_RESERVE_PRICE");
				} else {
					price = rset.getInt("BASIC_PRICE");
				}
				
				productOrder = new ProductOrder(orderNo, orderState, orderDestination, orderCheck, orderDate, orderMethod, importNo, orderConfirm, productCode, buyerId, sellerId, price);
				pList.add(productOrder);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}

	/** 구매확정(결재중)된 거래 조회 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<ProductOrder> selectConfirmComplete(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductOrder> pList = null;
		String query = prop.getProperty("selectConfirmComplete");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			pList = new ArrayList<ProductOrder>();
			ProductOrder productOrder = null;
			while(rset.next()) {
				int orderNo = rset.getInt("ORDER_NO");
				char orderState = rset.getString("ORDER_STATE").charAt(0);
				String orderDestination = rset.getString("ORDER_DESTINATION");
				char orderCheck = rset.getString("ORDER_CHECK").charAt(0);
				Date orderDate = rset.getDate("ORDER_DATE");
				char orderMethod = rset.getString("ORDER_METHOD").charAt(0);
				String importNo = rset.getString("IMPORT_NO");
				char orderConfirm = rset.getString("ORDER_CONFIRM").charAt(0);
				String buyerId = rset.getString("BUYERID");
				String sellerId = rset.getString("SELLERID");
				char productForm = rset.getString("PRODUCT_FORM").charAt(0);
				int productCode = rset.getInt("PRODUCT_CODE");
				int price = 0;
				if(productForm == 'A') {
					price = rset.getInt("AUCTION_RESERVE_PRICE");
				} else {
					price = rset.getInt("BASIC_PRICE");
				}
				
				productOrder = new ProductOrder(orderNo, orderState, orderDestination, orderCheck, orderDate, orderMethod, importNo, orderConfirm, productCode, buyerId, sellerId, price);
				pList.add(productOrder);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}
	
	/** 검색된 거래수 조회 Dao
	 * @param conn
	 * @param condition
	 * @return result
	 * @throws Exception
	 */
	public int searchPaymentCount(Connection conn, String condition) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query1 = prop.getProperty("searchPaymentCount1");
		String query2 = prop.getProperty("searchPaymentCount2");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query1 + condition + query2);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}

	/** 결재관리 검색 조회 Dao
	 * @param conn
	 * @param condition
	 * @param currentPage
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<ProductOrder> searchUserPayment(Connection conn, String condition, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductOrder> pList = null;
		String query1 = prop.getProperty("searchUserPayment1");
		String query2 = prop.getProperty("searchUserPayment2");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query1 + condition + query2);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			pList = new ArrayList<ProductOrder>();
			ProductOrder productOrder = null;
			while(rset.next()) {
				int orderNo = rset.getInt("ORDER_NO");
				char orderState = rset.getString("ORDER_STATE").charAt(0);
				String orderDestination = rset.getString("ORDER_DESTINATION");
				char orderCheck = rset.getString("ORDER_CHECK").charAt(0);
				Date orderDate = rset.getDate("ORDER_DATE");
				char orderMethod = rset.getString("ORDER_METHOD").charAt(0);
				String importNo = rset.getString("IMPORT_NO");
				char orderConfirm = rset.getString("ORDER_CONFIRM").charAt(0);
				String buyerId = rset.getString("BUYERID");
				String sellerId = rset.getString("SELLERID");
				char productForm = rset.getString("PRODUCT_FORM").charAt(0);
				int productCode = rset.getInt("PRODUCT_CODE");
				int price = 0;
				if(productForm == 'A') {
					price = rset.getInt("AUCTION_RESERVE_PRICE");
				} else {
					price = rset.getInt("BASIC_PRICE");
				}
				
				productOrder = new ProductOrder(orderNo, orderState, orderDestination, orderCheck, orderDate, orderMethod, importNo, orderConfirm, productCode, buyerId, sellerId, price);
				pList.add(productOrder);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}

	/** 지정된 상품게시글 정보 조회 Dao
	 * @param conn
	 * @param productNo
	 * @return product
	 * @throws Exception
	 */
	public Product selectProduct(Connection conn, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;
		String query = prop.getProperty("selectProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				Date productRegDate = rset.getDate("PRODUCT_REG_DATE");
				String productTitle = rset.getString("PRODUCT_TITLE");
				String productComment = rset.getString("PRODUCT_COMMENT");
				char productUsed = rset.getString("PRODUCT_USED").charAt(0);
				char productForm = rset.getString("PRODUCT_FORM").charAt(0);
				char productState = rset.getString("PRODUCT_STATE").charAt(0);
				int productCount = rset.getInt("PRODUCT_COUNT");
				int productDeclareCount = rset.getInt("PRODUCT_DECLARE_COUNT");
				int memberReg = rset.getInt("MEMBER_REG");
				int itemCode = rset.getInt("ITEM_CODE");
				
				product = new Product(productNo, productRegDate, productTitle, productComment, productUsed, productForm, productState, productCount, productDeclareCount, memberReg, itemCode);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return product;
	}

	/** 상품 게시글 모든 이미지 조회 Dao
	 * @param conn
	 * @param productNo
	 * @return files
	 * @throws Exception
	 */
	public ArrayList<Image> selectAllImage(Connection conn, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> files = null;
		Image image = null;
		String query = prop.getProperty("selectAllImage");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			files = new ArrayList<Image>();
			while(rset.next()) {
				int imageNo = rset.getInt("IMAGE_NO");
				String imagePath = rset.getString("IMAGE_PATH");
				char imageState = rset.getString("IMAGE_STATE").charAt(0);
				int imageLevel = rset.getInt("IMAGE_LEVEL");
				image = new Image(imageNo, imagePath, imageState, imageLevel, productNo);
				files.add(image);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return files;
	}

	

	/** 1:1문의 답변 수 조회 dao
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int noAnswerCountOther(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("noAnswerCountOther");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} 
		finally {
			close(rset);
			close(stmt);
		}
		return result;
	}
	
	/** 결재완료 Dao
	 * @param conn
	 * @param orderNo
	 * @return result
	 * @throws Exception
	 */
	public int paymentFinish(Connection conn, int orderNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("paymentFinish");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	/** 시세변경 Dao
	 * @param conn
	 * @param itemCode
	 * @param marketPrice
	 * @return result
	 * @throws Exception
	 */
	public int changeMarketPrice(Connection conn, int itemCode, int marketPrice) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("changeMarketPrice");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, marketPrice);
			pstmt.setInt(2, itemCode);
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 공지사항 전체 게시글 수 조회용 Dao
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int getNoticeCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int noticeCount = 0;
		String query = prop.getProperty("getNoticeCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				noticeCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		
		return noticeCount;

	}


	/** 공지사항 목록 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> noticeList(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> nList = null;
		
		String query = prop.getProperty("noticeList");
		
		try {
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage - 1) * limit + 1;
			int endRow   = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			nList = new ArrayList<Notice>();
			Notice notice = null;
			
			while(rset.next()) {
				notice = new Notice(rset.getInt("NOTICE_NO"), rset.getString("NOTICE_TITLE"), rset.getDate("NOTICE_CREATEDATE"), rset.getString("NOTICE_CLASS"));
				
				nList.add(notice);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return nList;
	}


	/** 다음 공지사항 번호 반환용 Dao
	 * @param conn
	 * @return noticeNo
	 * @throws Exception
	 */
	public int noticeNextNo(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int noticeNo = 0;
		
		String query = prop.getProperty("noticeNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				noticeNo = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return noticeNo;
	}


	/** 공지사항 등록용 Dao
	 * @param conn
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	public int noticeInsert(Connection conn, Notice notice) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("noticeInsert");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, notice.getNoticeNo());
			pstmt.setString(2, notice.getNoticeClass());
			pstmt.setString(3, notice.getNoticeTitle());
			pstmt.setString(4, notice.getNoticeContent());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public Notice selectNotice(Connection conn, int noticeNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		
		String query = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice(noticeNo, rset.getString("NOTICE_TITLE"), rset.getString("NOTICE_CONTENT"), rset.getString("NOTICE_CLASS"));
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}


	public int noticeUpdate(Connection conn, Notice notice) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("noticeUpdate");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeClass());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setInt(4, notice.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 공지사항 삭제용 Dao
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int noticeDelete(Connection conn, int noticeNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("noticeDelete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	/** FAQ 게시글 수 조회용 Dao
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getFAQCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int faqCount = 0;
		String query = prop.getProperty("getFAQCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				faqCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		
		return faqCount;
	}


	public List<FAQ> faqList(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FAQ> fList = null;
		
		String query = prop.getProperty("faqList");
		
		try {
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage - 1) * limit + 1;
			int endRow   = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<FAQ>();
			FAQ faq = null;
			
			while(rset.next()) {
				faq = new FAQ(rset.getInt("FAQ_NO"), rset.getString("FAQ_CLASS"),
						rset.getString("FAQ_TITLE"), rset.getString("FAQ_CONTENT"));
				fList.add(faq);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;

	}


	public int faqNextNo(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int faqNo = 0;
		
		String query = prop.getProperty("faqNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				faqNo = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return faqNo;
	}


	public int faqEnroll(Connection conn, FAQ faq) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("faqEnroll");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faq.getFaqNo());
			pstmt.setString(2, faq.getFaqClass());
			pstmt.setString(3, faq.getFaqTitle());
			pstmt.setString(4, faq.getFaqContent());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int getFAQSortCount(Connection conn, String faqClass) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int faqCount = 0;
		String query = prop.getProperty("getFAQSortCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faqClass);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				faqCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return faqCount;
	}


	public List<FAQ> faqSortList(Connection conn, int currentPage, int limit, String faqClass) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FAQ> fList = null;
		
		String query = prop.getProperty("faqSortList");
		
		try {
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage - 1) * limit + 1;
			int endRow   = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faqClass);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<FAQ>();
			FAQ faq = null;
			
			while(rset.next()) {
				faq = new FAQ(rset.getInt("FAQ_NO"), rset.getString("FAQ_CLASS"),
						rset.getString("FAQ_TITLE"), rset.getString("FAQ_CONTENT"));
				fList.add(faq);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}


	public FAQ selectFAQ(Connection conn, int faqNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FAQ faq = null;
		
		String query = prop.getProperty("selectFAQ");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				faq = new FAQ(faqNo, rset.getString("FAQ_CLASS"), rset.getString("FAQ_TITLE"), rset.getString("FAQ_CONTENT"));
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return faq;
	}


	public int faqModify(Connection conn, FAQ faq) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("faqModify");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faq.getFaqClass());
			pstmt.setString(2, faq.getFaqTitle());
			pstmt.setString(3, faq.getFaqContent());
			pstmt.setInt(4, faq.getFaqNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int faqDelete(Connection conn, int faqNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("faqDelete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int getBoardCount(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		int boardCount = 0;
		String query = prop.getProperty("getBoardCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				boardCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		
		return boardCount;

	}


	public List<Product> boardList(Connection conn, int currentPage, int limit) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> pList = null;
		
		String query = prop.getProperty("boardList");
		
		try {
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage - 1) * limit + 1;
			int endRow   = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			pList = new ArrayList<Product>();
			Product product = null;
			
			while(rset.next()) {
				product = new Product(rset.getInt("PRODUCT_NO"),
						rset.getString("PRODUCT_TITLE"), rset.getString("DEVICE_NAME"), 
						rset.getString("MEMBER_ID"));
				
				product.setProductDeclareCount(rset.getInt("PRODUCT_DECLARE_COUNT"));
				
				pList.add(product);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return pList;
	}


	public int boardDelete(Connection conn, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("boardDelete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
}
