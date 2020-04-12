package com.kh.appoproject.member.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.member.model.vo.Member;
import com.kh.appoproject.product.model.vo.Product;

public class MemberDao {

	private Properties prop = null;

	public MemberDao() throws Exception {

		// member 관련 sql 파일을 관리할 properties 파일 생성

		String fileName = MemberDao.class.getResource("/com/kh/appoproject/sql/member/member-query.properties")
				.getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}

	/**
	 * 로그인용 Dao
	 * 
	 * @param conn
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, Member member) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member loginMember = null;

		String query = prop.getProperty("loginMember");

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMember_Id());
			pstmt.setString(2, member.getMember_Pwd());

			rset = pstmt.executeQuery();

			if (rset.next()) {

				int member_No = rset.getInt("MEMBER_NO");
				String member_Id = rset.getString("MEMBER_ID");
				String member_Pwd = rset.getString("MEMBER_PWD");
				String member_NM = rset.getString("MEMBER_NM");
				String member_Phone = rset.getString("MEMBER_PHONE");
				String member_Email = rset.getString("MEMBER_EMAIL");
				Date member_ErollDate = rset.getDate("MEMBER_ENROLLDATE");
				String member_Account = rset.getString("MEMBER_ACCOUNT");
				String member_Status = rset.getString("MEMBER_STATUS");
				String member_Address = rset.getString("MEMBER_ADDR");

				loginMember = new Member(member_No, member_Id, member_Pwd, member_NM, member_Phone, member_Email,
						member_ErollDate, member_Account, member_Status, member_Address);

			}
		} finally {

			close(rset);
			close(pstmt);
		}

		return loginMember;
	}

	/**
	 * 아이디 중복체크 확인용 Dao
	 * 
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("idDupCheck");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1); // 첫번째 값을 갖고 오겠다 count(*) 써도 됨 어차피 1행1열
			}

		} finally {

			close(rset);
			close(pstmt);

		}
		return result;
	}

	/**
	 * 회원가입용 Dao
	 * 
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int join(Connection conn, Member member) throws Exception {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("join");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMember_Id());
			pstmt.setString(2, member.getMember_Pwd());
			pstmt.setString(3, member.getMember_NM());
			pstmt.setString(4, member.getMember_Phone());
			pstmt.setString(5, member.getMember_Email());
			pstmt.setString(6, member.getMember_Address());
			pstmt.setString(7, member.getMember_Account());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 아이디찾기용 DAo
	 * 
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public String FindIdEm(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String member_Id = null;

		String query = prop.getProperty("findIdEmail");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMember_NM());
			pstmt.setString(2, member.getMember_Email());

			rset = pstmt.executeQuery();

			while (rset.next()) {
				member_Id = rset.getString("MEMBER_ID");
			}

		} finally {

			close(rset);
			close(pstmt);

		}
		return member_Id;
	}

	public String FindIdPh(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String member_Id = null;

		String query = prop.getProperty("findIdPhone");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMember_NM());
			pstmt.setString(2, member.getMember_Phone());

			rset = pstmt.executeQuery();

			while (rset.next()) {
				member_Id = rset.getString("MEMBER_ID");
			}

		} finally {

			close(rset);
			close(pstmt);

		}
		System.out.println(member_Id);
		return member_Id;
	}

	public String FindIdPwd(Connection conn, Member member) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String memberId = null;

		String query = prop.getProperty("findPwd");

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMember_Id());
			pstmt.setString(2, member.getMember_NM());
			pstmt.setString(3, member.getMember_Email());

			rset = pstmt.executeQuery();

			while (rset.next()) {
				memberId = rset.getString("MEMBER_ID");
			}


		} finally {
			close(rset);
			close(pstmt);

		}
		return memberId;
	}

	/** 현재 계정 일치 여부 확인용 Dao
	 * @param conn
	 * @param changeMember
	 * @return result
	 * @throws Exception
	 */
	public int checkAcc(Connection conn, Member changeMember) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkAcc");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, changeMember.getMember_Id());
			pstmt.setString(2, changeMember.getMember_Email());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
		
	}

	/**  비밀번호 수정용 Dao
	 * @param conn
	 * @param changeMember
	 * @return result 
	 * @throws Exception
	 */
	public int updateNewPwd(Connection conn, Member changeMember) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateNewPwd");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, changeMember.getMember_Pwd());
			pstmt.setString(2, changeMember.getMember_Id());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
		
		
	}
	
	/** 회원 정보 조회용 DAO
	 * @param conn
	 * @param memberId
	 * @return selectMember
	 * @throws Exception
	 */
	public Member selectMember(Connection conn, String memberId)throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member selectMember = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String member_Id = rset.getString("MEMBER_ID");
				String member_NM = rset.getString("MEMBER_NM");
				String member_Phone = rset.getString("MEMBER_PHONE");
				String member_Email = rset.getString("MEMBER_EMAIL"); 
				String member_Address = rset.getString("MEMBER_ADDR"); 
				String member_Account = rset.getString("MEMBER_ACCOUNT");
				selectMember = new Member(member_Id, member_NM, member_Phone, member_Email, member_Account, member_Address);
			}
			
			
			
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return selectMember;
	}
	

	/** 회원 정보 수정용 DAO
	 * @param member
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member, Connection conn)throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMember_Phone());
			pstmt.setString(2, member.getMember_Email());
			pstmt.setString(3, member.getMember_Address());
			pstmt.setString(4, member.getMember_Account());
			pstmt.setString(5, member.getMember_Id());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
	}



	/** 마감된 경매목록 가져오기 Dao
	 * @param conn
	 * @return pList
	 * @throws Exception
	 */
	public List<Product> selectCompleteAuction(Connection conn,String memberId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> pList = null;
		Product product = null;
		String query = prop.getProperty("selectCompleteAuction");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			pList = new ArrayList<Product>();
			while(rset.next()) {
				int productNo = rset.getInt("PRODUCT_NO");
				String productUsed = rset.getString("PRODUCT_USED");
				int auctionReservePrice = rset.getInt("AUCTION_RESERVE_PRICE");
				int biddingMember = rset.getInt("BIDDING_MEMBER");
				String imagePath = rset.getString("IMAGE_PATH");
				String deviceName = rset.getString("DEVICE_NAME");
				String itemName = rset.getString("ITEM_NAME");
				String itemInfo = rset.getString("ITEM_INFO");
				String destinationAddr = rset.getString("DESTINATION_ADDR");
				String destinationContact = rset.getString("DESTINATION_CONTACT");
				String destinationNote = rset.getString("DESTINATION_NOTE");
				String destinationName = rset.getString("DESTINATION_NAME");
				String destinationReceiber = rset.getString("DESTINATION_RECEIBER"); 
				
				product = new Product(productNo, productUsed, auctionReservePrice, biddingMember, itemName, itemInfo, deviceName, memberId, imagePath, destinationAddr, destinationContact, destinationNote, destinationName, destinationReceiber);
						
				pList.add(product);
			}
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}
	

}
