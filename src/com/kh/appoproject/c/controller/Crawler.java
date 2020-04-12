package com.kh.appoproject.c.controller;

import static com.kh.appoproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kh.appoproject.c.service.CrawlerService;
import com.kh.appoproject.c.vo.PItem;

public class Crawler {

	public static void main(String[] args) {
		
		String itemName = "";
		String itemInfo = "";
		int price = 0;
		boolean checkChange = false;
		int count = 0;
		String c = "";
		String[] temp;
		String url = "https://price.cetizen.com/";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
		 
		
		Elements element = doc.select("div#make_1");    

		System.out.println("============================================================");

		Iterator<Element> ie2 = element.select("span.clr04").iterator();
		//Iterator<Element> ie3 = element.select("span.p11").iterator();
 
		
		List<PItem> pList = new ArrayList<PItem>();
		PItem pItem = null;
		
		while (ie2.hasNext()) {
			for(int i=0;i<3;i++) {
				switch(i) {
				case 0 : itemName = ie2.next().text(); break;
				case 1 : itemInfo = ie2.next().text(); 
				temp = itemInfo.split(" ");
				itemInfo = temp[temp.length-1];
				break;
				case 2 : price = Integer.parseInt(ie2.next().text().replace("원", "").replace(",", "")); break;
				}
			}	
			pItem = new PItem(itemName, itemInfo, price);
			pList.add(pItem);
			//System.out.println("itemName : " + itemName +"\t" + " itemInfo : " + itemInfo +"\t" + " price : " + price + "\t" + "\t");
		}
		try {
			int result = new CrawlerService().insertCrawler(pList);
			
			System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println(pList);
		System.out.println("============================================================");
	}


	




	private static void ContainsText(Iterator<Element> ie2, String string) {
		// TODO Auto-generated method stub
		
	}

}
