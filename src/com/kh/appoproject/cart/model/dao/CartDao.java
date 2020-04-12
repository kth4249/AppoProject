package com.kh.appoproject.cart.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.cart.model.vo.Cart;
import com.kh.appoproject.member.model.dao.MemberDao;
import com.kh.appoproject.member.model.vo.Member;

public class CartDao {

	private Properties prop = null;
	public CartDao() throws Exception{
		String fileName=CartDao.class
				.getResource("/com/kh/appoproject/sql/cart/cart-query.properties")
				.getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}
	
	/** 카트 추가 중복 확인용 Dao
	 * @param conn
	 * @param productNo
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public int selectNoCount(Connection conn, int productNo, Member loginMember) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("selectNoCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, loginMember.getMember_No());
			pstmt.setInt(2, productNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}

		}finally {
			close(pstmt);
		}
		return result;
	}

	public int addCart(Connection conn, int productNo, Member loginMember) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("addCart");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, loginMember.getMember_No());
			pstmt.setInt(2, productNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Cart> selectCart(Connection conn, Member loginMember) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Cart> cList = null;
		
		String query = prop.getProperty("selectCart");
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, loginMember.getMember_No());
			
			rset = pstmt.executeQuery();
			cList = new ArrayList<Cart>();
			
			Cart cart = null;
			
			while(rset.next()) {
				cart = new Cart(rset.getInt("BASIC_NO"),
								rset.getString("PRODUCT_TITLE"), 
								rset.getString("DEVICE_NAME"), 
								rset.getString("ITEM_NAME"), 
								rset.getString("MEMBER_ID"), 
								rset.getInt("BASIC_PRICE"),
								rset.getString("IMAGE_PATH"));
				cList.add(cart);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return cList;
	}

	public int deleteCart(Connection conn, String checkArr,int memberNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteCart");
		
		query+= "(" + checkArr +  ")"; 
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
