package com.kh.appoproject.c.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.c.controller.Crawler;
import com.kh.appoproject.c.vo.PItem;

public class CrawlerDao {

	public int insertCrawler(Connection conn, String itemName, String itemInfo, int marketPrice) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String fileName=Crawler.class
				.getResource("/c/sql/crawler-query.properties")
				.getPath();
		
		Properties prop = null;
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
		
		
		String query = prop.getProperty("insertCrawler");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, itemName);
			pstmt.setString(2, itemInfo);
			pstmt.setInt(3, marketPrice);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
}
