package com.kh.appoproject.advice.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.appoproject.advice.notice.model.service.NoticeService;
import com.kh.appoproject.advice.notice.model.vo.Notice;
import com.kh.appoproject.advice.notice.model.vo.PageInfo;
import com.kh.appoproject.common.ExceptionForward;

@WebServlet("/advice/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public NoticeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청주소
		String uri = request.getRequestURI();
		//System.out.println("uri : " + uri);
		
		String contextPath = request.getContextPath();
		//System.out.println("contextPath : " + contextPath);
		
		String command = uri.substring((contextPath + "/advice/notice").length());
		
		String path = null;
		RequestDispatcher view = null;
		String msg = null;
		
		NoticeService noticeService = new NoticeService();

		// 공지사항 목록 조회용 Controller
		if(command.equals("/list")) {
			
			try {
				// 페이징 처리
				int listCount = noticeService.getListCount();
				//
				// System.out.println(listCount);
				
				int limit = 5;
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
				
				//System.out.println(pInf);
				
				// 게시글 목록 조회
				List<Notice> list = noticeService.selectList(currentPage, limit);
				
				path = "/WEB-INF/views/advice/notice/noticeList.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 목록 조회", e);
			} 
			
		}
		
		else if(command.equals("/detail")) {
			int noticeNo = Integer.parseInt(request.getParameter("no"));
			try {
				Notice notice = noticeService.selectNotice(noticeNo);
				
				if(notice != null) {
					request.setAttribute("notice", notice);
					path = "/WEB-INF/views/advice/notice/noticeDetail.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} else {
					request.getSession().setAttribute("msg", "공지글 상세 조회 실패");
					response.sendRedirect("list");
				}
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지글 상세 조회", e);
			}
		}
		
		
		// 공지글 검색용 Controller
		else if(command.equals("/search")) {
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			try {
				
				// 페이징 처리
				// 검색 내용 총갯수 
				int listCount = noticeService.searchCount(searchKey, searchValue);
												
				int limit = 5; //글제한
				int pagingBarSize = 5; //페이징바길이제한
				
				int currentPage = 0; //현재페이지
				int maxPage = 0; //전체페이지(끝페이지)
				int startPage = 0; //시작페이지번호
				int endPage = 0; //끝페이지번호
				
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
				
				
				List<Notice> list = noticeService.searchNotice(searchKey, searchValue, limit, currentPage);
				System.out.println(list);
				
				path = "/WEB-INF/views/advice/notice/noticeSearchList.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pInf", pInf);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 검색", e);
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
