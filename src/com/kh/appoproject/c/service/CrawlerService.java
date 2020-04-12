package com.kh.appoproject.c.service;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import com.kh.appoproject.c.controller.Crawler;
import com.kh.appoproject.c.dao.CrawlerDao;
import com.kh.appoproject.c.vo.PItem;

public class CrawlerService {

	public int insertCrawler(List<PItem> pList) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		String itemName = null;
		String itemInfo = null;
		int marketPrice = 0;
		int count = 0;
		for(int i=0; i<pList.size(); i++) {
			itemName = pList.get(i).getItemName();
			itemInfo = pList.get(i).getItemInfo();
			marketPrice = pList.get(i).getMarketPrice();
			
			result = new CrawlerDao().insertCrawler(conn, itemName, itemInfo, marketPrice);
			
			if(result>0) {
				count++;
				result=0;
			}
		}
		if(count == pList.size()) {
			result = 1;
			commit(conn);
		} else {
			result = 0;
			rollback(conn);
		}
		return result;
	}
}
