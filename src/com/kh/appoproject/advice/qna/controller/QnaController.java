package com.kh.appoproject.advice.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.appoproject.advice.qna.model.vo.PageInfo;
import com.kh.appoproject.advice.qna.model.service.QnaService;
import com.kh.appoproject.advice.qna.model.vo.Qna;
import com.kh.appoproject.common.ExceptionForward;
import com.kh.appoproject.member.model.vo.Member;


@WebServlet("/advice/qna/*")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청주소
		String uri = request.getRequestURI();
				
		String contextPath = request.getContextPath();
				
		String command = uri.substring((contextPath + "/advice/faq").length());
		
		
		String path = null;
		RequestDispatcher view = null;
		String msg = null;
		
		QnaService qnaService = new QnaService();
		// 리퀘스트에서 로그인 멤버 가져오기
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		// 1:1문의 메인(로그인 확인) Controller
		if(command.equals("/main")) {
			if(loginMember == null) {
				System.out.println(loginMember);
				
				response.sendRedirect(request.getContextPath() + "/member/loginForm");
			} else {
				
				path = "/WEB-INF/views/advice/qna/qnaMain.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
		}
		// 1:1문의 등록 Controller
		else if(command.equals("/insert")) {
			
			try {
				String qnaTitle = request.getParameter("title");
				String qnaContent = request.getParameter("content");
				int memberNo = loginMember.getMember_No();
				
				
				Qna qna = new Qna(qnaTitle, qnaContent, memberNo);
				
				int result = qnaService.insertQna(qna);
												
				if(result>0) {
					path = "/WEB-INF/views/advice/qna/qnaResult.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("msg", "글등록실패");
					response.sendRedirect("main");
				}
			
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "1:1문의등록", e);
			}
		}
		
		// qna 목록 조회용 Controller
		else if(command.equals("/list")) {
			
			try {
				// 페이징 처리
				int memberNo = loginMember.getMember_No();
				// System.out.println("memberNo :" + memberNo);
				
				int listCount = qnaService.getListCount(memberNo);
				
				// System.out.println("글목록갯수:" + listCount);
				
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
				
				// 게시글 목록 조회
				List<Qna> list = qnaService.selectList(currentPage, limit, memberNo);
				
				// System.out.println(list);
				
				path = "/WEB-INF/views/advice/qna/qnaList.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 목록 조회", e);
			}
		}
		
		// qna 상세보기
		else if(command.equals("/detail")) {
			int qnaNo = Integer.parseInt(request.getParameter("no"));
			try {
				Qna qna = qnaService.selectQna(qnaNo);
				
				// System.out.println("qna : " + qna);
				
				if(qna != null) {
					request.setAttribute("qna", qna);
					path = "/WEB-INF/views/advice/qna/qnaDetail.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "QNA 상세 조회", e);
			}
		}
		// qna 수정화면 Controller
		else if(command.equals("/updateForm")) {
			// DB에서 해당 글을 조회하여 화면으로 전달
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				Qna qna = qnaService.updateForm(no);
				
				if(qna != null) {
					path = "/WEB-INF/views/advice/qna/qnaUpdate.jsp";
					request.setAttribute("qna", qna);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("msg", "qna 수정화면 출력 실패");
				}
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "qna수정화면으로 전환하는", e);
			}
		}
		
		// qna 수정 Controller
		else if(command.equals("/update")) {
			
			int qnaNo = Integer.parseInt(request.getParameter("no"));
			String qnaTitle = request.getParameter("title");
			String qnaContent = request.getParameter("content");
			
			Qna qna = new Qna(qnaNo, qnaTitle, qnaContent);
			
			try {
				int result = qnaService.updateQna(qna);
				
				System.out.println("수정결과 :" + result);
				
				if(result > 0) msg="Qna가 수정되었습니다.";
				else           msg="Qna 수정 실패";
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect("list");
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "QNA 수정", e);
			}
		}
		
		// qna 삭제 Controller
		else if(command.equals("/delete")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				int result = qnaService.deleteQna(no);
				//System.out.println("삭제결과:" + result);
				
				if(result>0) {
					msg = "QNA가 삭제되었습니다.";
					path = "list";
				} else {
					msg = "QNA 삭제 실패";
					path = "detail?no="+no;
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "QNA 삭제", e);
			}
		}
		
		// qna 검색용 Controller
		else if(command.equals("/search")) {
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			int memberNo = loginMember.getMember_No();
			
			try {
				
				// 페이징 처리
				// 검색 내용 총갯수 
				int listCount = qnaService.searchCount(memberNo, searchKey, searchValue);
				// 글갯수 테스트
				System.out.println("검색용 글갯수 : " + listCount);
				
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
				
				List<Qna> list = qnaService.searchQna(searchKey, searchValue, limit, currentPage, memberNo);
				
				// 검색글가져오나 확인
				System.out.println("검색한 리스트 : " + list);
				path = "/WEB-INF/views/advice/qna/qnaSearchList.jsp";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
