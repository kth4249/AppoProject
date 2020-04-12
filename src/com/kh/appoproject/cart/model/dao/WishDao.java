package com.kh.appoproject.cart.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.cart.model.vo.Wish;
import com.kh.appoproject.member.model.vo.Member;

public class WishDao {
	
	private Properties prop = null;
	public WishDao() throws Exception{
		String fileName=WishDao.class
				.getResource("/com/kh/appoproject/sql/cart/wish-query.properties")
				.getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

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

	public int addWish(Connection conn, int productNo, int memberNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("addWish");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, productNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Wish> selectWish(Connection conn, int memberNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Wish> wList = null;
		
		String query = prop.getProperty("selectWish");
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			wList = new ArrayList<Wish>();
			
			Wish wish = null;
			
			while(rset.next()) {
				wish = new Wish(rset.getInt("AUCTION_NO"), 
								rset.getString("PRODUCT_TITLE"), 
								rset.getString("DEVICE_NAME"), 
								rset.getString("ITEM_NAME"), 
								rset.getString("MEMBER_ID"),  
								rset.getInt("AUCTION_IMMEDIATE_BID"), 
								rset.getInt("AUCTION_RESERVE_PRICE"),
								rset.getString("IMAGE_PATH"),
								rset.getDate("AUCTION_DEADLINE"));
				wList.add(wish);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return wList;
	}

	public int deleteWish(Connection conn, String checkArr, int memberNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteWish");
		
		query+="(" + checkArr +")";
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
