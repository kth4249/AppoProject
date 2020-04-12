package com.kh.appoproject.payment.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.destination.model.vo.Destination;
import com.kh.appoproject.payment.model.vo.Payment;
import com.kh.appoproject.product.model.vo.Product;

public class PaymentDao {

	Properties prop = null;

	public PaymentDao() throws Exception {
		String fileName = PaymentDao.class.getResource("/com/kh/appoproject/sql/payment/payment-query.properties")
				.getPath();

		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	public List<Product> billingInfo(Connection conn, String condition) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		List<Product> pList = null;

		String query = prop.getProperty("billingInfo");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query + condition);

			System.out.println(condition);
			System.out.println(query + condition);

			pList = new ArrayList<Product>();
			Product product = null;
			while (rset.next()) {
				product = new Product();
				product.setProductNo(rset.getInt("PRODUCT_NO"));
				product.setProductTitle(rset.getString("PRODUCT_TITLE"));
				product.setProductComment(rset.getString("PRODUCT_COMMENT"));
				product.setProductUsed(rset.getString("PRODUCT_USED"));
				product.setMemberId(rset.getString("MEMBER_ID"));
				product.setDeviceName(rset.getString("DEVICE_NAME"));
				product.setItemName(rset.getString("ITEM_NAME"));
				product.setImagePath(rset.getString("IMAGE_PATH"));
				product.setProductForm(rset.getString("PRODUCT_FORM"));
				product.setAuctionReservePrice(rset.getInt("AUCTION_RESERVE_PRICE"));
				product.setBasicPrice(rset.getInt("BASIC_PRICE"));

				pList.add(product);
			}
		} finally {
			close(rset);
			close(stmt);
		}

		System.out.println(pList);
		return pList;
	}

	public List<Destination> selectDestination(Connection conn, int member_No) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Destination> dList = null;

		String query = prop.getProperty("selectDestination");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, member_No);
			rset = pstmt.executeQuery();
			Destination desti = null;
			dList = new ArrayList<Destination>();

			while (rset.next()) {
				desti = new Destination(rset.getInt("DESTINATION_NO"), rset.getString("DESTINATION_ADDR"),
						rset.getInt("MEMBER_CODE"), rset.getString("DESTINATION_CONTACT"),
						rset.getString("DESTINATION_NOTE"), rset.getString("DESTINATION_NAME"),
						rset.getString("DESTINATION_RECEIBER"));

				dList.add(desti);
			}
		} finally {
			close(rset);
			close(pstmt);
		}

		return dList;

	}

	public int insertOrder(Connection conn, String name, String amount, String buyer_name, String merchantUid)
			throws Exception {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertOrder");

		return 0;
	}
	
	public int insertProductOrder(Connection conn, Payment payment, int memberNo) throws Exception {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertProductOrder");
		
		System.out.println("=====insertProductOrder=====");
		System.out.println(payment.getDestinationAddr());
		System.out.println(payment.getOrderMethod());
		System.out.println(payment.getBasicNo());
		System.out.println(memberNo);
		System.out.println(query);
		try {
			
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, payment.getDestinationAddr());
			
			if(payment.getOrderMethod().equals("1")) {
				pstmt.setString(2, "C");
			}else {
				pstmt.setString(2, "P");
			}
			
			pstmt.setInt(3, payment.getBasicNo());
			pstmt.setInt(4, memberNo);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);
		}
		
		System.out.println("result : " + result);

		return result;
	}
	
	public int deleteCart(Connection conn, int memberNo, String basictNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteCart");
		
		System.out.println(query);
		try {
			
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1,memberNo);
			pstmt.setString(2, basictNo);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);
		}
		
		System.out.println("result : " + result);

		return result;		
	}

}
