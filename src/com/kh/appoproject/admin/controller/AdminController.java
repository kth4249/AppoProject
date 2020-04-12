package com.kh.appoproject.admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.appoproject.admin.model.service.AdminService;
import com.kh.appoproject.admin.model.vo.AdminLogin;
import com.kh.appoproject.admin.model.vo.Destination;
import com.kh.appoproject.admin.model.vo.FAQ;
import com.kh.appoproject.admin.model.vo.Image;
import com.kh.appoproject.admin.model.vo.Member;
import com.kh.appoproject.admin.model.vo.Notice;
import com.kh.appoproject.admin.model.vo.PageInfo;
import com.kh.appoproject.admin.model.vo.Product;
import com.kh.appoproject.admin.model.vo.ProductItemDevice;
import com.kh.appoproject.admin.model.vo.ProductOrder;
import com.kh.appoproject.common.ExceptionForward;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 모든주소
		String contextPath = request.getContextPath(); // 최상위주소
		String command = uri.substring((contextPath+"/admin").length()); // mapping주소
		HttpSession session = request.getSession();
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		AdminService adminService = new AdminService();
		

		
		if(command.equals("/login")) {
			// 로그인
			
			AdminLogin adminLogin = new AdminLogin("admin01", "pass01", false);
			session.setAttribute("adminLogin", adminLogin);
			path = "/WEB-INF/views/admin/adminLogin.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

		
		else if(command.equals("/loginCheck")) {
			// 로그인 확인
			AdminLogin adminLogin = (AdminLogin)session.getAttribute("adminLogin");
			String checkId = adminLogin.getAdminId();
			String checkPwd = adminLogin.getAdminPwd();
			
			String inputId = request.getParameter("adminId");
			String inputPwd = request.getParameter("adminPwd");
			
			if(checkId.equals(inputId) && checkPwd.equals(inputPwd)) {
				adminLogin.setAdminCheck(true);
				session.setAttribute("adminLogin", adminLogin);
				response.sendRedirect("adminMain");
			} else {
				session.setAttribute("msg", "로그인 정보가 유효하지 않습니다."); 
				response.sendRedirect("login");
			}
			
		}
		else if(command.equals("/logOut")) {
			// 로그아웃
			AdminLogin adminLogin = (AdminLogin)session.getAttribute("adminLogin");
			adminLogin.setAdminCheck(false);
			session.setAttribute("adminLogin", adminLogin);
			response.sendRedirect("login");
		}
		else if(command.equals("/adminMain")) {
			// 관리자 페이지 메인
			try {
				// 현재날짜 가져오기
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 등록한 회원수
				int enrollMember = adminService.enrollMemberCount(today);
				request.setAttribute("enrollMember", enrollMember);
				// 오늘 등록한 회원테이블 가져오기
				// 페이징 처리 시작
				int listCount = enrollMember; // 오늘 가입 회원 수
				int limit = 3;			// 한 페이지에 보여질 회원의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInfMember = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				request.setAttribute("pInfMember", pInfMember);
				// 페이징 처리 끝
				List<Member> mList = adminService.enrollMemberToday(today,currentPage,limit);
				if(mList != null ) {
					request.setAttribute("mList", mList);
				}
				
				// 3회이상 신고게시글 수(블라인드 처리 안됨)
				int overReport = adminService.overReportCount();
				request.setAttribute("overReport", overReport);
				// 신고테이블 가져오기
				// 페이징 처리 시작
				listCount = overReport; // 오늘 가입 회원 수
				limit = 3;			// 한 페이지에 보여질 회원의 수를 지정, DB전달
				pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				startPage = 0; 		// 페이징바 시작 페이지 번호
				endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage1") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage1"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInfDeclare = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				request.setAttribute("pInfDeclare", pInfDeclare);
				// 페이징 처리 끝
				List<Product> pList = adminService.selectOverDeclare(currentPage,limit);
				if(pList != null ) {
					request.setAttribute("pList", pList);
				}
				
				// 오늘 추가 거래수
				int addPayment = adminService.addPaymentCount(today);
				request.setAttribute("addPayment", addPayment);
				// 검수 미완료 수 
				int noCheck = adminService.noCheckCount();
				request.setAttribute("noCheck", noCheck);
				// 구매 확정(미결재) 수
				int confirmComplete = adminService.confirmCompleteCount();
				request.setAttribute("confirmComplete", confirmComplete);
				// 미답변 1:1문의 수(QNA수 - ANSWER수)
				int noAnswer = adminService.noAnswerCount();
				request.setAttribute("noAnswer", noAnswer);
				
				path = "/WEB-INF/views/admin/adminMain.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "메인페이지 조회", e);
			}
	
		}
		else if(command.equals("/adminMember")) {
			// 회원관리 이동
			try {
				// 검색용 회원 표시
				int searchFlag = 0;
				//페이징 처리
				int listCount = adminService.getListCount(); // 전체 회원 수
				int limit = 3;			// 한 페이지에 보여질 회원의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				//
				
				//모든 회원 수 가져오기
				//오늘 날짜
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 등록한 회원수
				int enrollMember = adminService.enrollMemberCount(today);
				request.setAttribute("enrollMember", enrollMember);
				
				// 오늘 회원수 가져오기
				int allMember = adminService.getListCount();
				request.setAttribute("allMember", allMember);
				
				List<Member> mList = adminService.selectMember(currentPage,limit);
				if(mList != null ) {
					request.setAttribute("mList", mList);
				}
				request.setAttribute("pInf", pInf);
				request.setAttribute("searchFlag", searchFlag);
				path = "/WEB-INF/views/admin/adminMemberManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "회원조회", e);
			}
			
		}
		else if(command.equals("/memberInfo")) {
			// 회원 상세정보 조회
			try {
				String memberId = request.getParameter("memberId");
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));

				Member member = adminService.selectMemberInfo(memberId);
				int penalty = adminService.penaltyCount(memberNo); // 회원의 패널티 횟수
				
				JSONObject jsonMember = new JSONObject();
				jsonMember.put("memberNM", member.getMemberNM());
				jsonMember.put("memberId", member.getMemberId());
				jsonMember.put("memberPhone", member.getMemberPhone());
				jsonMember.put("memberEmail", member.getMemberEmail());
				jsonMember.put("memberEnrollDate", member.getMemberEnrollDate()+"");
				jsonMember.put("memberPenalty", penalty);
				jsonMember.put("memberAccount", member.getMemberAccount());
				jsonMember.put("memberAddr", member.getMemberAddr());
				
				response.getWriter().print(jsonMember);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "회원정보 상세조회", e);
			}
		}
		else if(command.equals("/updatePenalty")) {
			// 패널티 추가
			String penaltyId = request.getParameter("penaltyId");
			String penaltyReason = request.getParameter("penaltyReason");
			try {
				Member member = adminService.selectMemberInfo(penaltyId);
				if(member != null) { // 회원 조회 성공시
					int memberNo = member.getMemberNo(); // 회원번호
					int result = adminService.updatePenalty(memberNo,penaltyReason);
					if(result > 0) {
						msg = "패널티가 등록되었습니다.";
					}else {
						msg = "패널티등록이 실패했습니다.";	
					}
					session.setAttribute("msg", msg);
					response.sendRedirect("adminMember");
				} else {
					session.setAttribute("msg", "회원이 존재하지 않습니다"); 
					response.sendRedirect("adminMember");
				}
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "패널티등록", e);
			}
			
			
		}
		else if(command.equals("/deleteMember")) {
			// 회원삭제
			String memberId = request.getParameter("memberId");
			try {
				int result = adminService.deleteMember(memberId);
				if(result > 0) {
					msg = "회원이 삭제되었습니다.";
				} else {
					msg = "회원삭제가 실패했습니다.";	
				}
				session.setAttribute("msg", msg);
				response.sendRedirect("adminMember");
				
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "회원삭제", e);
			}
		}
		else if(command.equals("/searchMember")) {
			// 검색용 회원 표시
			int searchFlag = 1;
			// 회원관리 검색
			String searchKey = request.getParameter("selectKey");
			String searchContent = request.getParameter("selectContent");
			try {
				
				//페이징 처리
				int limit = 3;			// 한 페이지에 보여질 회원의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				// 검색된 회원 수
				int listCount = adminService.searchMemberCount(searchKey,searchContent); 
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				//
				//모든 회원 수 가져오기
				//오늘 날짜
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 등록한 회원수
				int enrollMember = adminService.enrollMemberCount(today);
				request.setAttribute("enrollMember", enrollMember);
				
				// 오늘 회원수 가져오기
				int allMember = adminService.getListCount();
				request.setAttribute("allMember", allMember);
				//
				List<Member> mList = adminService.searchMember(searchKey,searchContent,currentPage,limit);
				request.setAttribute("mList", mList);
				request.setAttribute("pInf", pInf);	
				request.setAttribute("searchFlag", searchFlag);
				path = "/WEB-INF/views/admin/adminMemberManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "회원 검색", e);
			}	
			
		}
		else if(command.equals("/adminPayment")) {
			// 결재관리 이동
			try {
				// 전체 조회
				int pageFlag = 0;
				request.setAttribute("pageFlag", pageFlag);
				
				//페이징 처리
				int listCount = adminService.selectPaymentCount(); // 전체 거래 수
				int limit = 3;			// 한 페이지에 보여질 거래의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				request.setAttribute("pInf", pInf);
				//
				
				//오늘 날짜
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 추가 된 거래수
				int addPayment = adminService.addPaymentCount(today);
				request.setAttribute("addPayment", addPayment);
				
				List<ProductOrder> pList = adminService.selectPayment(currentPage,limit);
				if(pList != null ) {
					request.setAttribute("pList", pList);
				}
				
				path = "/WEB-INF/views/admin/adminPaymentManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "회원조회", e);
			}
		}
		else if(command.equals("/adminAddPayment")) {
			// 오늘 추가된 거래만 조회
			
			try {
				// 오늘 추가 거래만 조회
				int pageFlag = 1;
				request.setAttribute("pageFlag", pageFlag);
				
				//오늘 날짜
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 추가 된 거래수
				int addPayment = adminService.addPaymentCount(today);
				request.setAttribute("addPayment", addPayment);
				
				//페이징 처리
				int listCount = addPayment; // 오늘 추가된 거래수
				int limit = 3;			// 한 페이지에 보여질 거래의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				request.setAttribute("pInf", pInf);
				//
				
				List<ProductOrder> pList = adminService.selectTodayPayment(today,currentPage,limit);
				if(pList != null ) {
					request.setAttribute("pList", pList);
				}
				
				
				path = "/WEB-INF/views/admin/adminPaymentManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "오늘 추가된 거래 조회", e);
			}
		}
		else if(command.equals("/adminNoCheck")) {			
			// 미검수된 거래만 조회
			
			try {
				// 미검수된 거래 조회
				int pageFlag = 2;
				request.setAttribute("pageFlag", pageFlag);
				
				//오늘 날짜
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 추가 된 거래수
				int addPayment = adminService.addPaymentCount(today);
				request.setAttribute("addPayment", addPayment);
				
				//페이징 처리
				int listCount = adminService.noCheckCount(); // 미검수된 거래 수
				int limit = 3;			// 한 페이지에 보여질 거래의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				request.setAttribute("pInf", pInf);
				//
				
				List<ProductOrder> pList = adminService.selectNoCheckPayment(currentPage,limit);
				if(pList != null ) {
					request.setAttribute("pList", pList);
				}
				
				path = "/WEB-INF/views/admin/adminPaymentManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "미검수된 거래 조회", e);
			}
		}
		else if(command.equals("/adminConfirm")) {
			// 구매확정된 거래만 조회
			
			try {
				// 구매확정 거래 조회
				int pageFlag = 3;
				request.setAttribute("pageFlag", pageFlag);
				
				//오늘 날짜
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 추가 된 거래수
				int addPayment = adminService.addPaymentCount(today);
				request.setAttribute("addPayment", addPayment);
				
				//페이징 처리
				int listCount = adminService.confirmCompleteCount(); // 구매확정(결재중) 거래수 
				int limit = 3;			// 한 페이지에 보여질 거래의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				request.setAttribute("pInf", pInf);
				//
				
				List<ProductOrder> pList = adminService.selectConfirmComplete(currentPage,limit);
				if(pList != null ) {
					request.setAttribute("pList", pList);
				}
				
				path = "/WEB-INF/views/admin/adminPaymentManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "구매확정 거래 조회", e);
			}
		}
		else if(command.equals("/searchPayment")) {
			// 결재관리 검색
			try {
				// 결재 검색 조회
				int pageFlag = 4;
				request.setAttribute("pageFlag", pageFlag);
				
				//오늘 날짜
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String today = format.format(date);
				
				// 오늘 추가 된 거래수
				int addPayment = adminService.addPaymentCount(today);
				request.setAttribute("addPayment", addPayment);
				
				// 검색조건 / 검색내용 
				String searchKey = request.getParameter("searchKey");
				String searchContent = request.getParameter("searchContent");
				
				//페이징 처리
				int listCount = adminService.searchPaymentCount(searchKey,searchContent); // 검색된 거래 수
				int limit = 3;			// 한 페이지에 보여질 거래의 수를 지정, DB전달
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수(12345 / 1234 이런것 지정)
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수 , DB전달
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0; 		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize + 1;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage  <= endPage) { // 최대 페이지수가 페이징바 끝 페이지 번호보다 작을 경우
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				request.setAttribute("pInf", pInf);
				//
				List<ProductOrder> pList = adminService.searchUserPayment(currentPage,limit,searchKey,searchContent);
				if(pList != null ) {
					request.setAttribute("pList", pList);
				}
				
				path = "/WEB-INF/views/admin/adminPaymentManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "거래검색", e);
			}
		}
		else if(command.equals("/adminPaymentInfo")) {
			// 결재 상세정보 이동
			
			
			System.out.println("서블릿 실행");
			
			int productNo = Integer.parseInt(request.getParameter("productNo")); // 해당 상품게시글
			System.out.println(productNo);
			String buyerId = request.getParameter("buyerId"); // 구매자 아이디
			System.out.println(buyerId);
			String sellerId = request.getParameter("sellerId"); // 판매자 아이디
			System.out.println(sellerId);

			try {
			// 구매자 정보 가져오기
			Member buyMember = adminService.selectMemberInfo(buyerId);
			request.setAttribute("buyMember", buyMember);
			System.out.println(buyMember);
			
			/*
			// 구매자 삭제로 조회 불가시
			if(buyMember == null) {
				msg = "구매자가 조회되지 않습니다";
				session.setAttribute("msg", msg);
				response.sendRedirect("adminPayment");
			}
			*/
			
			// 판매자 정보 가져오기
			Member sellMember = adminService.selectMemberInfo(sellerId);
			request.setAttribute("sellMember", sellMember);
			System.out.println(sellMember);
			
			/*
			// 판매자 삭제로 조회 불가시
			if(sellMember == null) {
				msg = "판매자가 조회되지 않습니다";
				session.setAttribute("msg", msg);
				response.sendRedirect("adminPayment");
			}
			*/
			
			// 거래정보 가져오기
			ProductOrder orderInfo = adminService.productOrderInfo(productNo);
			request.setAttribute("orderInfo", orderInfo);
			System.out.println(orderInfo);
			// 상품 정보 가져오기
			ProductItemDevice deviceInfo = adminService.deviceInform(productNo);
			request.setAttribute("deviceInfo", deviceInfo);
			System.out.println(deviceInfo);
			// 판매자 패널티 조회하기
			
			// 
			String orderDestination = orderInfo.getOrderDestination();
			System.out.println(orderDestination);
			Destination destination = adminService.selectDestination(buyMember.getMemberNo(),orderDestination);
			System.out.println("??");
			request.setAttribute("destination", destination);
			System.out.println(destination);
			// 배송정보 가져오기
			
			int penalty = adminService.penaltyCount(sellMember.getMemberNo());
			request.setAttribute("penalty", penalty);
			System.out.println(penalty);
			// 상품 이미지 조회(썸네일)
			String imagePath = adminService.selectProductImage(productNo);
			request.setAttribute("imagePath", imagePath);
			System.out.println(imagePath);
			
			
			path = "/WEB-INF/views/admin/adminPaymentInform.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "거래 상세 조회", e);
			}
			
		}
		else if(command.equals("/adminPaymentCheck")) {
			// 거래 상세보기에서 검수여부 변경
			
			int orderNo = Integer.parseInt(request.getParameter("orderNo")); // 주문번호
			char paymentCheck = request.getParameter("paymentCheck").charAt(0); // 검수여부
			try {
				int result = adminService.updatePaymentCheck(orderNo,paymentCheck);
				
				if(result > 0) {
					msg = "검수가 변경되었습니다.";
				}else {
					msg = "검수변경이 실패했습니다.";	
				}
				session.setAttribute("msg", msg);
				response.sendRedirect("adminPayment");
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "검수여부변경", e);
			}
			
		}
		else if(command.equals("/adminSetting")) {
			// 사이트 설정 이동
			path = "/WEB-INF/views/admin/adminSettingSite.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		else if(command.equals("/moveProductDetail")) {
			// 결재중인 게시글 조회
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			try {
			// 상품번호로 product정보 얻기 / request에 저장
			Product product = adminService.selectProduct(productNo);
			request.setAttribute("product", product);
			// 상품번호로 이미지 파일 얻기 / request에 저장
			ArrayList<Image> files = adminService.selectAllImage(productNo);
			request.setAttribute("files", files);
			// 변수 넘겨주기
			int currentPage = 1;
			int cartresult = 0;
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("cartresult", cartresult);
			
			path = "/WEB-INF/views/product/productDetail.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 조회", e);
			}
		}
		
	
		else if(command.equals("/paymentFinish")) {
			// 결재완료
			int orderNo = Integer.parseInt(request.getParameter("orderNo"));
			try {
				int result = adminService.paymentFinish(orderNo);
				if(result > 0)	msg="결재완료";
				else 			msg="결재실패";
				
				session.setAttribute("msg", msg);
				response.sendRedirect("adminSetting");
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "결재과정", e);
			}
	
		}
		
		else if(command.equals("/changeMarketPrice")) {
			// 시세 변경
			int itemCode = Integer.parseInt(request.getParameter("itemCode"));
			int marketPrice = Integer.parseInt(request.getParameter("marketPrice"));
			
			try {
				int result = adminService.changeMarketPrice(itemCode,marketPrice);
				if(result > 0)	msg="변경성공";
				else			msg="변경실패";
				
				session.setAttribute("msg", msg);
				response.sendRedirect("adminSetting");
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "시세변경", e);
			}
		}
		
		// admin 고객지원관리-공지사항
		// 게시글 조회 목록용 Controller
		else if(command.equals("/support")) {
			
			try {
				// 페이징 처리 (pagenation)
				// 눈에 보이는 게시판에 일정 개수의 게시글만 노출되고
				// 나머지는 페이지로 구분하여 숫자 형태로 보여주게하는 방법
				
				// 현재 게시글 전체 수
				
				int listCount = adminService.getNoticeCount();
				 
				int limit = 10;			// 한 페이지에 보여줄 게시글의 수
				int pagingBarSize = 10;	// 보여질 페이징바의 페이지 개수
				
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0;		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				// currentPage - 현재 페이지 번호를 표시할 변수
				// 처음 게시판 목록으로 화면이 전환이 되면 1페이지가 보여야 함
				if(request.getParameter("currentPage")==null) {
					currentPage = 1;
				} else {
					// 전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				
				// maxPage - 총 페이지 수 (== 마지막 페이지)
				// limit == 5일 경우
				// 게시글의 개수가 50개일 경우 : 10페이지
				maxPage = (int)Math.ceil((double)listCount/limit);
				
				
				// startPage - 페이징바의 시작 페이지 번호
				// 페이징바에 수가 10개씩 보여질 경우
				// 시작번호는 1, 11, 21, 31, ...
				startPage = (currentPage-1) / pagingBarSize * pagingBarSize + 1 ;
				
				// endPage - 페이징바 끝 페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				System.out.println(pInf);
				
				List<Notice> nList = adminService.noticeList(currentPage, limit);
				
				
				path = "/WEB-INF/views/admin/adminNotice.jsp";
				request.setAttribute("nList", nList);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 목록 조회", e);
				
			}
				
			
		} 
		
		// admin 공지사항 등록폼
		else if(command.equals("/support/noticeInsertForm")) {
			path = "/WEB-INF/views/admin/adminNoticeInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		}
		
		// admin 공지사항 등록
		else if(command.equals("/support/noticeInsert")) {
			String noticeClass = request.getParameter("noticeClass");
			String noticeTitle = request.getParameter("noticeTitle");
			String noticeContent = request.getParameter("noticeContent");
			
			Notice notice = new Notice(noticeTitle, noticeContent, noticeClass);
			
			try {
				int result = adminService.noticeInsert(notice);
				
				if(result > 0) {
					msg = "공지사항 등록 성공";
				} else {
					msg = "등록 실패";
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/admin/support");
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 등록", e);
			}
			
			
		}
		
		// admin 공지사항 수정 폼
		else if(command.equals("/support/noticeModifyForm")) {
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			
			try {
				Notice notice = adminService.selectNotice(noticeNo);
				request.setAttribute("notice", notice);
				if(notice != null) {
					path = "/WEB-INF/views/admin/adminNoticeModify.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					msg = "게시글 불러오기 실패";
					response.sendRedirect(request.getHeader("referer"));
				}
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시글을 불러오는", e);
			}
			
		}
		
		// admin 공지사항 수정
		else if(command.equals("/support/noticeModify")) {
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			String noticeClass = request.getParameter("noticeClass");
			String noticeTitle = request.getParameter("noticeTitle");
			String noticeContent = request.getParameter("noticeContent");
			String currentPage = request.getParameter("currentPage");
			Notice notice = new Notice(noticeNo, noticeTitle, noticeContent, noticeClass);
			
			try {
				int result = adminService.noticeUpdate(notice);
				if(result > 0) {
					msg = "공지사항 수정 성공";
				} else {
					msg = "공지사항 수정 실패";
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect("../support?currentPage=" + currentPage);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 수정", e);
			}
		}
		
		// admin 공지사항 삭제
		else if(command.equals("/support/noticeDelete")) {
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			String currentPage = request.getParameter("currentPage");
			
			try {
				int result = adminService.noticeDelete(noticeNo);
				
				if(result > 0) {
					msg = "공지사항 삭제 성공";
				} else {
					msg = "공지사항 삭제 실패";
				}
				
				path = "../support?currentPage="+currentPage;
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 삭제", e);
			}
		}
		
		
		// admin FAQ
		else if(command.equals("/support/FAQ")) {
			//String menu = request.getParameter("FAQmenu");
			try {
				int listCount = adminService.getFAQCount();
				 
				int limit = 5;
				
				int currentPage = 0;
				int maxPage = 0;
				
				if(request.getParameter("currentPage")==null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				System.out.println(currentPage);
				maxPage = (int)Math.ceil((double)listCount/limit);
				
				PageInfo pInf = new PageInfo(listCount, limit, currentPage, maxPage);
				
				List<FAQ> fList = adminService.faqList(currentPage, limit);
				
				
				if(currentPage == 1) {
					request.setAttribute("fList", fList);
					request.setAttribute("pInf", pInf);
					
					path = "/WEB-INF/views/admin/adminFAQ.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					response.setCharacterEncoding("UTF-8");
					
					Gson gson = new Gson();
					
					gson.toJson(fList, response.getWriter());
				}
				
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "FAQ 글 목록 조회", e);
			}
			
			
		}
		
		else if(command.equals("/support/FAQsort")) {
			String faqClass = request.getParameter("faqClass");
			System.out.println(faqClass);
			try {
				int listCount = adminService.getFAQSortCount(faqClass);
				 
				int limit = 5;
				
				int currentPage = 0;
				int maxPage = 0;
				
				if(request.getParameter("currentPage")==null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				System.out.println(currentPage);
				maxPage = (int)Math.ceil((double)listCount/limit);
				
				PageInfo pInf = new PageInfo(listCount, limit, currentPage, maxPage);
				
				List<FAQ> fList = adminService.faqSortList(currentPage, limit, faqClass);
				
				
				if(currentPage == 1) {
					request.setAttribute("fList", fList);
					request.setAttribute("pInf", pInf);
					request.setAttribute("faqClass", faqClass);
					
					path = "/WEB-INF/views/admin/adminFAQsort.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					response.setCharacterEncoding("UTF-8");
					
					Gson gson = new Gson();
					
					gson.toJson(fList, response.getWriter());
				}
				
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "FAQ 글 목록 조회", e);
			}
		}
		
		else if(command.equals("/support/FAQenrollForm")) {
			path = "/WEB-INF/views/admin/adminFAQenroll.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		}
		
		else if(command.equals("/support/FAQenroll")) {
			String faqClass = request.getParameter("FAQclass");
			String faqTitle = request.getParameter("FAQtitle");
			String faqContent = request.getParameter("FAQcontent");
			
			FAQ faq = new FAQ(faqClass, faqTitle, faqContent);
			
			System.out.println(faq);
			
			try {
				int result = adminService.faqEnroll(faq);
				
				if(result > 0) {
					msg = "자주 묻는 질문 등록 성공";
				} else {
					msg = "자주 묻는 질문 등록 실패";					
				}
				
				response.sendRedirect("FAQ");
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "자주 묻는 질문 등록", e);
			}
		}
		
		else if(command.equals("/support/FAQmodifyForm")) {
			int faqNo = Integer.parseInt(request.getParameter("faqNo"));
			
			try {
				FAQ faq = adminService.selectFAQ(faqNo);
				System.out.println(faq);
				if(faq != null) {
					request.setAttribute("faq", faq);
					path = "/WEB-INF/views/admin/adminFAQModify.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					msg = "자주 묻는 질문 불러오기 실패";
					response.sendRedirect(request.getHeader("referer"));
				}
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "자주 묻는 질문을 불러오는", e);
			}

		}
		
		
		else if(command.equals("/support/FAQmodify")) {
			int faqNo = Integer.parseInt(request.getParameter("faqNo"));
			String faqClass = request.getParameter("FAQclass");
			String faqTitle = request.getParameter("FAQtitle");
			String faqContent = request.getParameter("FAQcontent");
			
			FAQ faq = new FAQ(faqNo, faqClass, faqTitle, faqContent);
			
			System.out.println(faq);
			
			try {
				int result = adminService.faqModify(faq);
				
				if(result > 0) {
					msg = "자주 묻는 질문 수정 성공";
				} else {
					msg = "자주 묻는 질문 수정 실패";
				}
				
				response.sendRedirect("FAQ");
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "자주 묻는 질문 등록", e);
			}
		}
		
		
		else if(command.equals("/support/FAQdelete")) {
			int faqNo = Integer.parseInt(request.getParameter("faqNo"));
			
			try {
				int result = adminService.faqDelete(faqNo);
				
				if(result > 0) {
					msg = "자주 묻는 질문 삭제 성공";
				} else {
					msg = "자주 묻는 질문 삭제 실패";
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect("FAQ");
				
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "자주 묻는 질문 삭제", e);
			}
		}
		
		
		
		else if(command.equals("/support/QnA")) {
			path = "/WEB-INF/views/admin/adminQnA.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		}
		
		
		
		
		
		// admin 게시글관리
		else if(command.equals("/boardManage")) {
			try {
				int listCount = adminService.getBoardCount();
				 
				int limit = 8;			// 한 페이지에 보여줄 게시글의 수
				int pagingBarSize = 10;	// 보여질 페이징바의 페이지 개수
				
				int currentPage = 0;	// 현재 페이지 번호를 표시할 변수
				int maxPage = 0;		// 전체 페이지의 수(== 마지막 페이지)
				int startPage = 0;		// 페이징바 시작 페이지 번호
				int endPage = 0;		// 페이징바 끝 페이지 번호
				
				if(request.getParameter("currentPage")==null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil((double)listCount/limit);
				
				
				startPage = (currentPage-1) / pagingBarSize * pagingBarSize + 1 ;
				
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				System.out.println(pInf);
				
				List<Product> pList = adminService.boardList(currentPage, limit);
				
				
				path = "/WEB-INF/views/admin/adminBoardManage.jsp";
				request.setAttribute("pList", pList);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 관리 조회", e);
				
			}
				
		}
		
		// 게시반 관리 페이지 게시글 삭제
		else if(command.equals("/boardDelete")) {
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			String currentPage = request.getParameter("currentPage");
			
			try {
				int result = adminService.boardDelete(productNo);
				
				if(result > 0) {
					msg = "게시글 삭제 성공";
				} else {
					msg = "게시글 삭제 실패";
				}
				
				path = "boardManage?currentPage="+currentPage;
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 삭제", e);
			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
