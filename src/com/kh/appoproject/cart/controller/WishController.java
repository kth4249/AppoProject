package com.kh.appoproject.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.appoproject.cart.model.service.WishService;
import com.kh.appoproject.cart.model.vo.Wish;
import com.kh.appoproject.common.ExceptionForward;
import com.kh.appoproject.member.model.vo.Member;

@WebServlet("/wish/*")
public class WishController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WishController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 현재 요청 주소
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/wish").length()); // 제일 뒤에 들어오는 주소만 잘라냄
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		
		WishService wishService = new WishService();
		
		
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		// 관심상품 추가
		if(command.equals("/add")) {
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			
			try {
				PrintWriter out = response.getWriter();
				
				int result = wishService.selectNoCount(productNo, loginMember); // 관심상품에 상품이 있는지 없는지 상품 번호 조회
				
				if(result > 0) {
					out.print(0);
				}else {
					result = wishService.addWish(productNo, loginMember.getMember_No());
					
					if(result > 0) 	out.print(result);
					else 			out.print(-1);
				}
				
				
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "관심상품 번호 조회", e);
			}
		}
		
		// 관심상품 목록 조회
		else if(command.equals("/wishlist")) {
			try {
				List<Wish> wList = wishService.selectWish(loginMember.getMember_No());
				
				request.setAttribute("wList", wList);
				
				path = "/WEB-INF/views/cart/wish.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "관심상품 조회", e);
			}
		}
		
		else if(command.equals("/deleteWish")) {
			String checkArr = request.getParameter("checkArr");
			System.out.println(checkArr);
			try {
				int result = wishService.deleteWish(checkArr, loginMember.getMember_No());
				
				if(result>0) {
					msg = "관심상품에서 삭제했습니다.";
				}else {
					msg = "삭제실패";
				}
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect("wishlist");
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "관심상품 삭제", e);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
