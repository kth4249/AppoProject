package com.kh.appoproject.payment.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.kh.appoproject.payment.model.vo.Order;

import static com.kh.appoproject.common.JDBCTemplate.*;

public class OrderDao {

	private Properties prop = null;
	
	public OrderDao() {
		try {
			String fileName = OrderDao.class.getResource("/com/kh/payment/sql/payment-query.properties").getPath();
			prop = new Properties();
			
			prop.load(new FileReader(fileName));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String createMerchantUid(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		String merchantUid = null;
		
		String query = prop.getProperty("createMerchantUid");
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				merchantUid = rset.getString(1);
			}
			
		}finally {
			close(rset);
			close(stmt);
		}
		
		return merchantUid;
	}
	
	
	public int insertOrder(Connection conn, Order order, String merchantUid) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrder");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, merchantUid);
			pstmt.setString(2, order.getName());
			pstmt.setInt(3, order.getAmount());
			pstmt.setString(4, order.getBuyerName());
			pstmt.setString(5, order.getBuyerEmail());
			pstmt.setString(6, order.getBuyerTel());
			pstmt.setString(7, order.getBuyerPostCode());
			pstmt.setString(8, order.getBuyerAddr());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	
}









