package com.kh.appoproject.product.model.dao;

import static com.kh.appoproject.common.JDBCTemplate.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.appoproject.member.model.dao.MemberDao;
import com.kh.appoproject.product.model.vo.Auction;
import com.kh.appoproject.product.model.vo.Image;
import com.kh.appoproject.product.model.vo.Order;
import com.kh.appoproject.product.model.vo.Product;

public class ProductDao {

	private Properties prop = null;
	
	public ProductDao() throws Exception{
		String fileName=ProductDao.class
				.getResource("/com/kh/appoproject/sql/product/product-query.properties")
				.getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
	}
	
	/** 전체 게시글 수 조회용 Dao
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String productItem) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int listCount = 0;
		String query = prop.getProperty("getListCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productItem);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	/** 게시판 목록 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return pList
	 * @throws Exception
	 */
	public List<Product> selectList(Connection conn, int currentPage, int limit, String productItem) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> pList = null;
		
		String query = prop.getProperty("selectList");
		try {
			int startRow = (currentPage - 1)*limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, productItem);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			pList = new ArrayList<Product>();
			Product product = null;
			
			while(rset.next()) {
				product = new Product(rset.getInt("PRODUCT_NO"), 
									  rset.getString("PRODUCT_TITLE"), 
									  rset.getString("PRODUCT_FORM"), 
									  rset.getInt("PRODUCT_COUNT"), 
									  rset.getInt("BASIC_PRICE"),
									  rset.getInt("AUCTION_IMMEDIATE_BID"),
									  rset.getInt("AUCTION_RESERVE_PRICE"), 
									  rset.getDate("AUCTION_DEADLINE"));
				pList.add(product);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}

	/** 다음 상품 번호 반환용 Dao
	 * @param conn
	 * @return productNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		int productNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				productNo = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(stmt);
		}
		return productNo;
	}


	/** 아이템번호 조회용 Dao
	 * @param conn
	 * @param itemName
	 * @return itemNo
	 * @throws Exception
	 */
	public int selectItemNo(Connection conn, String itemName) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int itemNo = 0;
		
		String query = prop.getProperty("selectItemNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, itemName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				itemNo = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return itemNo;
	}
	/** 상품 등록용 Dao
	 * @param conn
	 * @param product
	 * @param memberWriter
	 * @return
	 * @throws Exception
	 */
	public int insertProduct(Connection conn, Product product, int memberWriter) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, product.getProductNo());
			pstmt.setString(2, product.getProductTitle());
			pstmt.setString(3, product.getProductComment());
			pstmt.setString(4, product.getProductUsed());
			pstmt.setString(5, product.getProductForm());
			pstmt.setInt(6, memberWriter);
			pstmt.setInt(7, product.getItemCode());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	/** 일반 상품 추가용 Dao
	 * @param conn
	 * @param basicProduct
	 * @param memberWriter
	 * @return result
	 * @throws Exception
	 */
	public int insertBasic(Connection conn, Product basic, int productNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBasic");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productNo);
			pstmt.setInt(2, basic.getBasicPrice());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 경매 상품 등록용 Dao
	 * @param conn
	 * @param auctionProduct
	 * @param memberWriter
	 * @return result
	 * @throws Exception
	 */
	public int insertAuction(Connection conn, Product auction,int productNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAuction");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productNo);
			pstmt.setInt(2, auction.getAuctionImmediateBid());
			pstmt.setInt(3, auction.getAuctionReservePrice());
			pstmt.setDate(4, auction.getAuctionDeadline());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 파일(이미지) 정보 삽입용 Dao
	 * @param conn
	 * @param file
	 * @return result
	 * @throws Exception
	 */
	public int insertImage(Connection conn, Image file) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertImage");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, file.getImagePath());
			pstmt.setInt(2, file.getImageLevel());
			pstmt.setInt(3, file.getProductNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 썸네일 이미지 목록 조회 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param productItem
	 * @return 
	 * @throws Exception
	 */
	public ArrayList<Image> selectFileList(Connection conn, int currentPage, int limit, String productItem) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Image> fList = null;
		
		String query = prop.getProperty("selectFileList");
		try {
			int startRow = (currentPage - 1)*limit + 1; // 시작
			int endRow = startRow + limit - 1; 
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, productItem);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			fList = new ArrayList<Image>();
			Image file = null;
			
			while(rset.next()) {
				file = new Image();
				file.setImageNo(rset.getInt("IMAGE_NO"));
				file.setProductNo(rset.getInt("PRODUCT_NO"));
				file.setImagePath(rset.getString("IMAGE_PATH"));
				
				fList.add(file);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return fList;
	}

	/** 상품 상세 조회용 Dao
	 * @param conn
	 * @param productNo
	 * @return
	 * @throws Exception
	 */
	public Product selectProduct(Connection conn, int productNo) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;
		
		String query = prop.getProperty("selectProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				product = new Product(productNo, 
									  rset.getString("PRODUCT_TITLE"),
									  rset.getDate("PRODUCT_REG_DATE"),
									  rset.getString("PRODUCT_COMMENT"), 
									  rset.getString("PRODUCT_USED"), 
									  rset.getString("PRODUCT_FORM"), 
									  rset.getInt("PRODUCT_COUNT"), 
									  rset.getInt("MEMBER_REG"), 
									  rset.getInt("BASIC_PRICE"), 
									  rset.getInt("AUCTION_IMMEDIATE_BID"), 
									  rset.getInt("AUCTION_RESERVE_PRICE"), 
									  rset.getDate("AUCTION_DEADLINE"), 
									  rset.getInt("BIDDING_MEMBER"), 
									  rset.getString("ITEM_NAME"), 
									  rset.getString("ITEM_INFO"), 
									  rset.getString("DEVICE_NAME"),
									  rset.getString("MEMBER_ID"));
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return product;
	}

	/** 게시글 이미지 파일 조회용 Dao
	 * @param conn
	 * @param productNo
	 * @return files
	 * @throws Exception
	 */
	public List<Image> selectFiles(Connection conn, int productNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Image> files = null;
		String query = prop.getProperty("selectFiles");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			
			files = new ArrayList<Image>();
			Image file = null;
			
			while(rset.next()) {
				file = new Image(rset.getInt("IMAGE_NO"),
								 rset.getString("IMAGE_PATH"),
								 rset.getString("IMAGE_STATE"),
								 rset.getInt("IMAGE_LEVEL"),
								 rset.getInt("PRODUCT_NO"));
				files.add(file);
				
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return files;
	}
	

	/** 상품 게시글 수정용 Dao
	 * @param conn
	 * @param product
	 * @param memberWriter
	 * @return
	 * @throws Exception
	 */
	public int updateProduct(Connection conn, Product product, int memberWriter) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, product.getProductTitle());
			pstmt.setString(2, product.getProductComment());
			pstmt.setInt(3, product.getItemCode());
			pstmt.setInt(4, product.getProductNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return 1;
	}	
	

	/** 일반 상품 수정용 Dao
	 * @param conn
	 * @param basic
	 * @param productNo
	 * @return result
	 * @throws Exception
	 */
	public int updateBasic(Connection conn, Product basic, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateBasic");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, basic.getBasicPrice());
			pstmt.setInt(2, productNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return 1;
	}
	

	/** 이미지 수정용 Dao
	 * @param conn
	 * @param file
	 * @return
	 * @throws Exception
	 */

	public int deleteProduct(Connection conn, int no) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	/** 현재 회원이 게시글 이전 신고 여부 파악 Dao
	 * @param conn
	 * @param productNo
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int reportProductCount(Connection conn, int productNo, String memberId) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("reportProductCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, productNo);
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

	/** 게시글 신고 중복 방지 Dao
	 * @param conn
	 * @param productNo
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int reportProduct(Connection conn, int productNo, String memberId) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("reportProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, productNo);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 상품게시글 신고 1 증가
	 * @param conn
	 * @param productNo
	 * @return result
	 * @throws Exception
	 */
	public int updateProductDeclareCount(Connection conn, int productNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateProductDeclareCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int biddingProduct(Connection conn, int productNo, int biddingPrice, int memberNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("biddingProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, biddingPrice);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, productNo);
			pstmt.setInt(4, biddingPrice);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBeforeImage(Connection conn, String beforePath) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteBeforeImage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, beforePath);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	/** 아이템 목록 조회용 Dao
	 * @param conn
	 * @param deviceName
	 * @return iList
	 * @throws Exception
	 */
	public List<String> selectItem(Connection conn, String deviceName) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> iList = null;
		String query = prop.getProperty("selectItem");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deviceName);
			
			rset = pstmt.executeQuery();
			
			iList = new ArrayList<String>();
			String product = null;
			
			while(rset.next()) {
				product = new String(rset.getString("ITEM_NAME"));
				iList.add(product);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return iList;
	}

	public List<String> selectInfo(Connection conn, String itemName) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> gList = null;
		String query = prop.getProperty("selectInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, itemName);
			
			rset = pstmt.executeQuery();
			
			gList = new ArrayList<String>();
			String product = null;
			
			while(rset.next()) {
				product = new String(rset.getString("ITEM_INFO"));
				gList.add(product);
				System.out.println(gList);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return gList;
	}
	
	/** 전체 게시글 수 조회용 Dao
	 * @param conn
	 * @return listCount
	 */
	public int getListCount(Connection conn)throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		int listCount=0;
		String query = prop.getProperty("getListCount1");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}




	/** 일반상품 등록 게시판 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param memberId
	 * @return pList
	 * @throws Exception
	 */
	public List<Product> selectList1(Connection conn, int currentPage, int limit, String memberId) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> pList = null;
				
		String query = prop.getProperty("selectList1");
		
		
			
			
			try {

				int startRow = (currentPage - 1)* limit + 1;
				int endRow = startRow + limit -1;
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				
				rset = pstmt.executeQuery();
				
				pList = new ArrayList<Product>();	
				Product product = null;
				
				while(rset.next()) {
					product = new Product(rset.getInt("PRODUCT_NO"), 
										rset.getDate("PRODUCT_REG_DATE"), 
										rset.getString("PRODUCT_TITLE"), 
										rset.getString("PRODUCT_STATE"), 
										rset.getInt("PRODUCT_COUNT"));
					
					pList.add(product);
				}
				
			}finally {
				close(rset);
				close(pstmt);
				
			}
			
		return pList;
	}



	/** 옥션상품 등록 게시판 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param memberId
	 * @return aList
	 * @throws Exception
	 */
	public List<Auction> selectAuctionList(Connection conn, int currentPage, int limit, String memberId)throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Auction> aList = null;
		
		String query = prop.getProperty("selectAuctionList");
		
		try {
			
			int startRow = (currentPage - 1)* limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			aList = new ArrayList<Auction>();
			Auction auction = null;
			
			while(rset.next()) {
				
				auction = new Auction(rset.getInt("PRODUCT_NO"), 
										rset.getDate("PRODUCT_REG_DATE"),
										rset.getString("PRODUCT_TITLE"), 
										rset.getInt("PRODUCT_COUNT"), 
										rset.getInt("AUCTION_IMMEDIATE_BID"), 
										rset.getInt("AUCTION_RESERVE_PRICE"), 
										rset.getDate("AUCTION_DEADLINE"));
				aList.add(auction);
			}
			
			
			
		}finally {
			close(rset);
			close(pstmt);
		}
				
		
		
		
		
		return aList;
	}




	/** 구매내역 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param memberId
	 * @return oList
	 */
	public List<Order> selectOrderList(Connection conn, int currentPage, int limit, String memberId)throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Order> oList = null;
		
		String query = prop.getProperty("selectOrderList");
		
		try {
			
			int startRow = (currentPage -1)* limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			oList = new ArrayList<Order>();
			Order order = null;
			
			while(rset.next()) {
				order = new Order(rset.getInt("BASIC_PRICE"), 
									rset.getString("PRODUCT_TITLE"), 
									rset.getInt("ORDER_NO"), 
									rset.getString("ORDER_STATE"), 
									rset.getDate("ORDER_DATE"));
				
				oList.add(order);
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return oList;
	}




	public List<Order> selectOrderDetail(Connection conn, int memberReg, int productNo)throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Order> orderDetail = null;
				
		String query = prop.getProperty("selectOrderDetail");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberReg);
			pstmt.setInt(2, productNo);
			
			rset = pstmt.executeQuery();
			
			orderDetail = new ArrayList<Order>();
			Order order = null;
			
			while(rset.next()) {
				order = new Order(rset.getInt("BASIC_PRICE"), 
						  			rset.getString("PRODUCT_TITLE"),
						  			rset.getString("MEMBER_ID"), 
						  			rset.getInt("ORDER_NO"), 
						  			rset.getString("ORDER_STATE"), 
						  			rset.getDate("ORDER_DATE"));
				orderDetail.add(order);
			}
			
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return orderDetail;
	}
	

	public List<String> selectMarketPrice(Connection conn, String itemInfo, String itemName) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> mpList = null;
		String query = prop.getProperty("selectMarketPrice");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, itemName);
			pstmt.setString(2, itemInfo);
			
			rset = pstmt.executeQuery();
			
			mpList = new ArrayList<String>();
			String product = null;
			
			while(rset.next()) {
				product = new String(rset.getString("MARKET_PRICE"));
				mpList.add(product);
				System.out.println(mpList);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return mpList;
	}

}
