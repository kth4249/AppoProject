package com.kh.appoproject.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.kh.appoproject.common.ExceptionForward;
import com.kh.appoproject.common.MyFileRenamePolicy;
import com.kh.appoproject.member.model.vo.Member;
import com.kh.appoproject.product.model.service.ProductService;
import com.kh.appoproject.product.model.vo.Auction;
import com.kh.appoproject.product.model.vo.Image;
import com.kh.appoproject.product.model.vo.Order;
import com.kh.appoproject.product.model.vo.PageInfo;
import com.kh.appoproject.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/product/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 현재 요청 주소
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/product").length()); // 제일 뒤에 들어오는 주소만 잘라냄
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		
		ProductService productService = new ProductService();
		
		// 쿼리스트링으로 아이템(deviceName) 얻어옴
		String productItem = request.getParameter("item");
		// 게시글 목록 조회용 Controller
		if(command.equals("/list")) {
			
			try {
				// 페이징 처리
				
				// 현재 게시글 전체 수
				
				int listCount = productService.getListCount(productItem); // DB에서 꺼내옴
				
				int limit = 12; // 한 페이지에 보여질 게시글 수
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수
				
				int currentPage = 0; // 현재 페이지 번호를 표시할 변수
				int maxPage = 0; // 전체 페이지 수 (== 마지막 페이지)
				int startPage = 0; // 페이징바 시작 페이지 번호
				int endPage = 0; // 페이징바 끝 페이지 번호
				
				
				// 다른 페이지에서 처음 게시판 목록으로 화면이 전환되면 1페이지가 보여야함
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				}else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( (double)listCount / limit );
				
				startPage = (currentPage - 1) / pagingBarSize * pagingBarSize + 1;
				endPage = startPage + pagingBarSize - 1;			
				
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				// currentPage(몇 페이지)와 limit(9개) 만 DB에 전달해서 해당하는 페이지 글만 조회
				List<Product> pList = productService.selectList(currentPage, limit, productItem);
				
				List<Image> fList = productService.selectFileList(currentPage, limit, productItem);
				
				//System.out.println(pList);
				//System.out.println(fList);
				
				path = "/WEB-INF/views/product/productList.jsp";
				request.setAttribute("pList", pList);
				request.setAttribute("pInf", pInf);
				request.setAttribute("fList", fList);
				
				//request.setAttribute("productItem", productItem);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "상품 게시판 목록 조회", e);
			}
		}
		
		// 상품 판매 화면 출력용 Controller
		else if(command.equals("/insertForm")) {
			path = "/WEB-INF/views/product/productInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		// 상품 등록용 Controller
		else if(command.equals("/insert")) {
			
			try {
				if(ServletFileUpload.isMultipartContent(request)) {
					int maxSize = 10 * 1024 * 1024;
					
					String root = request.getSession().getServletContext().getRealPath("/");
					
					String savePath = root + "resources/uploadImages/";
					
					// MultipartRequest 객체 생성
					// 	-> 객체가 생성되는 순간에 request, 파일경로 지정, 최대파일 크기지정, 문자 인코딩 형식 지정
					//	-> 변경된 파일명으로 지정된 경로로 파일이 저장됨
					MultipartRequest multiRequest = 
							new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
					
					ArrayList<String> saveFiles = new ArrayList<String>();
					
					Enumeration<String> files = multiRequest.getFileNames();
					
					while(files.hasMoreElements()) {
						String name = files.nextElement();
						
						if(multiRequest.getFilesystemName(name) != null) {
							saveFiles.add(multiRequest.getFilesystemName(name));
						}
					}
					
					String productTitle = multiRequest.getParameter("productTitle");
					String productComment = multiRequest.getParameter("productComment");
					String productUsed = multiRequest.getParameter("productUsed");
					String productForm = multiRequest.getParameter("productForm");
					String deviceName = multiRequest.getParameter("deviceName");
					int itemCode = 0;

					
					String bprice = multiRequest.getParameter("basicPrice");
					int basicPrice = 0;
					if(bprice!=null) basicPrice = Integer.parseInt(bprice);
					
					
					String iBid = multiRequest.getParameter("immediateBid");
					System.out.println(iBid);
					int auctionImmediateBid = 0;
					if(iBid != null && !iBid.equals("")) auctionImmediateBid = Integer.parseInt(iBid);
					
					
					String rprice = multiRequest.getParameter("reservePrice");
					int auctionReservePrice = 0;
					if(rprice!=null) auctionReservePrice = Integer.parseInt(rprice);
					
					
					String deadline = multiRequest.getParameter("auctionDeadline");
					//System.out.println(deadline);
					Date auctionDeadline = null;
					if(deadline!=null) auctionDeadline = Date.valueOf(deadline);
					
					Product product = new Product(productTitle, productComment, productUsed, productForm, itemCode);
					Product basic = new Product(basicPrice);
					Product auction = new Product(auctionImmediateBid, auctionReservePrice, auctionDeadline);
					
					Member loginMember = (Member)request.getSession().getAttribute("loginMember");
					
					//품목코드
					String itemName = multiRequest.getParameter("itemName");
					
					int memberWriter = loginMember.getMember_No();
					
					ArrayList<Image> fList = new ArrayList<Image>();
					
					for(int i = saveFiles.size()-1 ; i>=0 ; i--) {
						Image file = new Image();
						//file.setImagePath(savePath+saveFiles.get(i));
						file.setImagePath(saveFiles.get(i));
						
						if( (i == saveFiles.size() -1 ) && multiRequest.getFilesystemName("img1") != null ) {
							file.setImageLevel(1);
						}else {
							file.setImageLevel(2);
						}
						fList.add(file);
					}
					
					int result = productService.insertProduct(itemName, productForm, product, auction, basic, memberWriter, fList, savePath);
					
					if (result>0)	msg = "게시글 등록 성공";
					else			msg = "게시글 등록 실패";
					
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect("list?item="+deviceName);
					
				}
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}
		
		// 게시글 상세 조회 Controller
		else if(command.equals("/detail")) {
			System.out.println(request.getParameter("no"));
			int productNo = Integer.parseInt(request.getParameter("no"));
			
			try {
				Product product = productService.selectProduct(productNo);
				
				if(product != null) {
					List<Image> files = productService.selectFiles(productNo);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files); // 리퀘스트로 전달
					}
					
					path = "/WEB-INF/views/product/productDetail.jsp";
					request.setAttribute("product", product);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}else {
					request.getSession().setAttribute("msg", "게시글 상세 조회 실패");
					//response.sendRedirect("list"); // 목록으로
				}
				
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 상세 조회", e);
			}
		}
		
		// 상품 글 수정 화면 Controller
		else if(command.equals("/updateForm")) {
			// DB에서 해당 글을 조회하여 화면으로 전달
			int no = Integer.parseInt(request.getParameter("no"));
			try {
				Product product = productService.updateForm(no);
				
				if(product!=null) {
					
					List<Image> files = productService.selectFiles(no);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files); // 리퀘스트로 전달
					}
					
					path="/WEB-INF/views/product/productUpdate.jsp";
					request.setAttribute("product", product);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}else {
					request.getSession().setAttribute("msg", "상품 수정화면 출력 실패");
				}
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "상품 글 수정화면으로 전환", e);
			}
		}
		
		// 상품 글 수정 Controller
		else if(command.equals("/update")) {
			try {
				int maxSize = 10 * 1024 * 1024;
				String root = request.getSession().getServletContext().getRealPath("/");
				String savePath = root + "resources/uploadImages/";
				System.out.println("savePath : " + savePath);
				// MultipartRequest 객체 생성
				// 	-> 객체가 생성되는 순간에 request, 파일경로 지정, 최대파일 크기지정, 문자 인코딩 형식 지정
				//	-> 변경된 파일명으로 지정된 경로로 파일이 저장됨
				MultipartRequest multiRequest = 
						new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				int productNo = Integer.parseInt(multiRequest.getParameter("no"));
				
				
				// *********** 글만 등록 ************
				String productTitle = multiRequest.getParameter("productTitle");
				String productComment = multiRequest.getParameter("productComment");
				String deviceName = multiRequest.getParameter("deviceName");
				int itemCode = 0;
				//품목코드
				String itemName = multiRequest.getParameter("itemName");
				
				int basicPrice = Integer.parseInt(multiRequest.getParameter("basicPrice"));
				
				Product product = new Product(productNo, productTitle, productComment, itemCode);
				Product basic = new Product(basicPrice);
				Member loginMember = (Member)request.getSession().getAttribute("loginMember");
				int memberWriter = loginMember.getMember_No();
				
				String[] beforeImg = multiRequest.getParameterValues("beforeImg");
				System.out.println("beforeImg : " + Arrays.toString(beforeImg));
				
				ArrayList<Image> fList = null;
				
				// 수정된 파일이 있는 경우
				if(ServletFileUpload.isMultipartContent(request)) {
					ArrayList<String> saveFiles = new ArrayList<String>();
					Enumeration<String> files = multiRequest.getFileNames();
					while(files.hasMoreElements()) {
						String name = files.nextElement();
						if(multiRequest.getFilesystemName(name) != null) {
							saveFiles.add(multiRequest.getFilesystemName(name));
						}
					}
				
					// ***** 업로드 파일 목록 생성 ******
					fList = new ArrayList<Image>();
				
					for(int i = saveFiles.size()-1 ; i>=0 ; i--) {
						Image file = new Image();
						//file.setImagePath(savePath+saveFiles.get(i));
						file.setImagePath(saveFiles.get(i));
						
						if( (i == saveFiles.size() -1 ) && multiRequest.getFilesystemName("img1") != null ) {
							file.setImageLevel(1);
						}else {
							file.setImageLevel(2);
						}
						fList.add(file);
					}	
				}
			
				int result = ProductService.updateProduct(itemName, product, basic, memberWriter, fList, beforeImg, savePath);
				
				if (result>0)	msg = "게시글 수정 성공";
				else			msg = "게시글 수정 실패";
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect("list?item="+deviceName);
				
			}catch(Exception e) {
			ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}
		
		
		// 게시글 삭제 Controller
		else if(command.equals("/delete")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			String deviceName = request.getParameter("item");
			try {
				int result = ProductService.deleteProduct(no);
				
				if(result>0) {
					msg="상품 게시글이 삭제되었습니다.";
					path="list?item="+deviceName;
				}else {
					msg="상품 게시글 삭제 실패";
					path="delete?no="+no;
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 삭제", e);
			}
		}
		
		// 신고하기 기능
		else if(command.equals("/reportProduct")) {
			
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			String memberId = request.getParameter("memberId");
			
			try {
				// service이동 / dao이동 업데이트
				int result = productService.reportProduct(productNo,memberId);
				if(result == -1) {
					msg = "이미 신고한 게시글 입니다.";
				}else if(result == 0) {
					msg = "게시글 신고에 실패하였습니다";
				} else {
					msg = "게시글을 신고하였습니다.";
				}
				response.getWriter().print(msg);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "신고하기", e);
			}
		}
		
		// 입찰 신청 기능 (모달 ajax)
		else if(command.equals("/biddingProduct")) {
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			int biddingPrice = Integer.parseInt(request.getParameter("biddingPrice"));
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			
			System.out.println("productNo:"+productNo);
			System.out.println("biddingPrice:"+biddingPrice);
			System.out.println("loginMember:"+loginMember.getMember_No());
			try {
				int result = productService.biddingProduct(productNo, biddingPrice, loginMember.getMember_No());
				PrintWriter out = response.getWriter();
				System.out.println("result1:" +result);
				if(result>0) out.print(result);
				else		 out.print(0);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "입찰 신청", e);
			}
		}
		
		// 입찰 신청 기능 (페이지 새로고침)
		else if(command.equals("/bProduct")) {
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			int biddingPrice = Integer.parseInt(request.getParameter("biddingPrice"));
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			
			System.out.println("productNo:"+productNo);
			System.out.println("biddingPrice:"+biddingPrice);
			System.out.println("loginMember:"+loginMember.getMember_No());
			try {
				int result = productService.biddingProduct(productNo, biddingPrice, loginMember.getMember_No());
				if(result>0) {
					msg="입찰 신청되었습니다.";
				}else	{
					msg="경매 입찰 신청 실패";
				}
				request.getSession().setAttribute("msg", msg);
				view = request.getRequestDispatcher("/wish/wishlist");
				view.forward(request, response);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "입찰 신청", e);
			}
		}
		
		// 카테고리 선택
		else if(command.equals("/selectCate")) {
			String deviceName = request.getParameter("deviceName");
			
			try {
				List<String> iList = productService.selectItem(deviceName);
				
				JSONObject jo = null;
				JSONArray jArr = new JSONArray();
				
				for(String str : iList) {
					jo = new JSONObject();
					jo.put("iList", str);
					
					jArr.add(jo);
				}
				//System.out.println(iList);
				response.getWriter().print(jArr);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "목록 조회", e);
			}
		}
		
		// 카테고리2 선택(용량)
		else if(command.equals("/selectInfo")) {
			String itemName = request.getParameter("itemName");
			
			try {
				List<String> gList = productService.selectInfo(itemName);
				
				JSONObject jo = null;
				JSONArray jArr = new JSONArray();
				
				for(String str : gList) {
					jo = new JSONObject();
					jo.put("gList", str);
					
					jArr.add(jo);
				}
				//System.out.println(iList);
				response.getWriter().print(jArr);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "목록 조회", e);
			}
		}
		
		// 카테고리2 선택(시세)
		else if(command.equals("/selectPrice")) {
			String itemInfo = request.getParameter("itemInfo");
			String itemName = request.getParameter("itemName");
			System.out.println(itemInfo);
			try {
				List<String> mpList = productService.selectMarketPrice(itemInfo,itemName);
				System.out.println("mpList : "+mpList);
				JSONObject jo = null;
				JSONArray jArr = new JSONArray();
				
				for(String str : mpList) {
					jo = new JSONObject();
					jo.put("mpList", str);
					
					jArr.add(jo);
				}
				//System.out.println(iList);
				response.getWriter().print(jArr);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "목록 조회", e);
			}
		}
		
		else if(command.equals("/registerList")) {
			try {
				HttpSession session = request.getSession();
				Member loginMember = (Member)session.getAttribute("loginMember");
				String memberId = loginMember.getMember_Id();
				
				int listCount = productService.getListCount();
				
				int limit = 10;
				
				int pagingBarsize = 5;
				
				int currentPage = 0;
				
				int maxPage = 0;
				
				int startPage = 0;
				
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1 ; 
				}else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil(((double)listCount / limit));
				
				startPage = (currentPage - 1) / pagingBarsize * pagingBarsize + 1;
				
				endPage = startPage + pagingBarsize -1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarsize, currentPage, maxPage, startPage, endPage);
				
				
				System.out.println(pInf);
				
				List<Product> pList = productService.selectList(currentPage, limit, memberId);
				
				System.out.println("pList : " + pList);
				
				path = "/WEB-INF/views/product/productRegisterBasic.jsp";
				request.setAttribute("pList", pList);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 목록 조회", e);
			}
		}

		else if(command.equals("/registerAuction")) {
			
			try {
				
				HttpSession session = request.getSession();
				Member loginMember = (Member)session.getAttribute("loginMember");
				String memberId = loginMember.getMember_Id();
				
				int listCount = productService.getListCount();
				
				int limit = 5;
				
				int pagingBarsize = 5;
				
				int currentPage = 0;
				
				int maxPage = 0;
				
				int startPage = 1;
				
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1 ; 
				}else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil(((double)listCount / limit));
				
				startPage = (currentPage - 1) / pagingBarsize * pagingBarsize + 1;
				
				endPage = startPage + pagingBarsize -1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarsize, currentPage, maxPage, startPage, endPage);
				
				List<Auction> aList = ProductService.selectAuctionList(currentPage, limit, memberId);
				
				System.out.println("aList : " + aList);
				
				path = "/WEB-INF/views/product/productRegisterAuction.jsp";
				request.setAttribute("aList", aList);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);				
				
				
			}catch(Exception e) {
				
				ExceptionForward.errorPage(request, response, "게시판 목록 조회", e);
			}
			
			
			
			
		}
		
		
		
		
		else if(command.equals("/orderList")) {
			
			try {
				
				HttpSession session = request.getSession();
				Member loginMember = (Member)session.getAttribute("loginMember");
				String memberId = loginMember.getMember_Id();
				
				
					int listCount = productService.getListCount();
					
					int limit = 5;
					
					int pagingBarsize = 5;
					
					int currentPage = 0;
					
					int maxPage = 0;
					
					int startPage = 1;
					
					int endPage = 0;
					
					if(request.getParameter("currentPage") == null) {
						currentPage = 1 ; 
					}else {
						currentPage = Integer.parseInt(request.getParameter("currentPage"));
					}
					
					maxPage = (int)Math.ceil(((double)listCount / limit));
					
					startPage = (currentPage - 1) / pagingBarsize * pagingBarsize + 1;
					
					endPage = startPage + pagingBarsize -1;
					if(maxPage <= endPage) {
						endPage = maxPage;
					}
					
					PageInfo pInf = new PageInfo(listCount, limit, pagingBarsize, currentPage, maxPage, startPage, endPage);
				
					List<Order> oList = productService.selectOrderList(currentPage, limit, memberId);
				
					path = "/WEB-INF/views/product/productOrderList.jsp";
					request.setAttribute("oList", oList);
					request.setAttribute("pInf", pInf);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				
			}catch(Exception e) {
				
			}
			
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
