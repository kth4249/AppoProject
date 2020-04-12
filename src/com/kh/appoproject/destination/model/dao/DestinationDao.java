package com.kh.appoproject.destination.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.kh.appoproject.destination.model.vo.Destination;


public class DestinationDao {
	Properties prop = null;
	
	public DestinationDao() throws Exception{
		String fileName=DestinationDao.class
				.getResource("/com/kh/appoproject/sql/destination/destination-query.properties")
				.getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	public int addDest(Connection conn, Destination dest) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("addDest");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dest.getDestinationAddr());
			pstmt.setInt(2, dest.getMemberCode());
			pstmt.setString(3, dest.getDestinationContact());
			pstmt.setString(4, dest.getDestinationNote());
			pstmt.setString(5, dest.getDestinationName());
			pstmt.setString(6, dest.getDestinationReceiver());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int destNextNo(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		
		int destinationNo = 0;
		
		String query = prop.getProperty("destNextNo");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				destinationNo = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		
		return destinationNo;
	}

	public Destination selectDest(Connection conn, int destinationNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Destination dest = null;
		
		String query = prop.getProperty("selectDest");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, destinationNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dest = new Destination(rset.getInt("DESTINATION_NO"),
						rset.getString("DESTINATION_ADDR"),
						rset.getInt("MEMBER_CODE"),
						rset.getString("DESTINATION_CONTACT"),
						rset.getString("DESTINATION_NOTE"),
						rset.getString("DESTINATION_NAME"),
						rset.getString("DESTINATION_RECEIBER"));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return dest;
	}

	public int updateDest(Connection conn, Destination dest) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateDest");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dest.getDestinationAddr());
			pstmt.setString(2, dest.getDestinationContact());
			pstmt.setString(3, dest.getDestinationNote());
			pstmt.setString(4, dest.getDestinationName());
			pstmt.setString(5, dest.getDestinationReceiver());
			pstmt.setInt(6, dest.getDestinationNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
}
