package com.kh.appoproject.advice.faq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.appoproject.advice.faq.model.service.FaqService;
import com.kh.appoproject.advice.faq.model.vo.Faq;
import com.kh.appoproject.advice.faq.model.vo.PageInfo;
import com.kh.appoproject.common.ExceptionForward;

@WebServlet("/advice/faq/*")
public class FaqController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청주소
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/advice/faq").length());
		
		String path = null;
		RequestDispatcher view = null;
		String msg = null;
		
		FaqService faqService = new FaqService();
		
		// faq 메인 이동 Controller
		if(command.equals("/list")) {
			
			try {
				// 페이징 처리
				int listCount = faqService.getListCount();
				
				// System.out.println("faq 글수 : " + listCount);
				// 최대글수제어
				
				int add = 0;
				int limit = 5;
				// 더보기 눌렀을때 controller 재사용을 위한 코드
				if(request.getParameter("add")!=null) {
					add = Integer.parseInt(request.getParameter("add"));
				} 
				// System.out.println("add:" + add);
				if(request.getParameter("limit")!=null) {
					limit = Integer.parseInt(request.getParameter("limit")) + add;
				}
				
				//System.out.println("limit:" + limit);
				int pagingBarSize = 5;
				
				int currentPage = 0;
				int maxPage = 0;
				int startPage = 0;
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				// 총 페이지수
				maxPage = (int)Math.ceil( ( (double)listCount / limit) );
				// 시작페이지번호
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				// 끝페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				// 게시글 목록 조회
				List<Faq> list = faqService.selectList(currentPage, limit);
				
				//System.out.println("faq 리스트 " + list);
				
				request.setAttribute("pInf", pInf);
				request.setAttribute("list", list);
				
				path = "/WEB-INF/views/advice/faq/faqList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "FAQ 목록 조회", e);
			}
		} 
		
		// faq 서브메뉴 이동
		else if(command.equals("/subList")) {
			String faqClass = request.getParameter("faqClass");
			//System.out.println("faqClass : " + faqClass);
			try {
				int listCount = faqService.getSubListCount(faqClass);
				
				// System.out.println("faq 글수 : " + listCount);
				// 최대글수제어
				int add = 0;
				int limit = 5;
				// 더보기 눌렀을때 controller 재사용을 위한 코드
				if(request.getParameter("add")!=null) {
					add = Integer.parseInt(request.getParameter("add"));
				} 
				// System.out.println("add:" + add);
				if(request.getParameter("limit")!=null) {
					limit = Integer.parseInt(request.getParameter("limit")) + add;
				}
				
				//System.out.println("limit:" + limit);
				int pagingBarSize = 5;
				
				int currentPage = 0;
				int maxPage = 0;
				int startPage = 0;
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				// 총 페이지수
				maxPage = (int)Math.ceil( ( (double)listCount / limit) );
				// 시작페이지번호
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				// 끝페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<Faq> list = faqService.faqSubList(currentPage, limit, faqClass);
				
				//System.out.println(list);
				
				request.setAttribute("pInf", pInf);
				request.setAttribute("list", list);
				
				path = "/WEB-INF/views/advice/faq/faqList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "FAQ 서브목록조회", e);
			}
		}
		
		else if(command.equals("/search")) {
			String searchKey = request.getParameter("searchKey");
			try {
				int listCount = faqService.searchCount(searchKey);
				
				// System.out.println("검색용글수 " + listCount);
				
				// System.out.println("faq 글수 : " + listCount);
				// 최대글수제어
				
				int add = 0;
				int limit = 5;
				// 더보기 눌렀을때 controller 재사용을 위한 코드
				if(request.getParameter("add")!=null) {
					add = Integer.parseInt(request.getParameter("add"));
				} 
				// System.out.println("add:" + add);
				if(request.getParameter("limit")!=null) {
					limit = Integer.parseInt(request.getParameter("limit")) + add;
				}
				
				//System.out.println("limit:" + limit);
				int pagingBarSize = 5;
				
				int currentPage = 0;
				int maxPage = 0;
				int startPage = 0;
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				// 총 페이지수
				maxPage = (int)Math.ceil( ( (double)listCount / limit) );
				// 시작페이지번호
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				// 끝페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<Faq> list = faqService.searchList(limit, currentPage, searchKey);
				
				//System.out.println("검색리스트 : " + list);
				
				request.setAttribute("pInf", pInf);
				request.setAttribute("list", list);
				
				path = "/WEB-INF/views/advice/faq/faqSearchList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "검색과정", e);
				
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
