package com.kh.appoproject.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.appoproject.cart.model.service.CartService;
import com.kh.appoproject.cart.model.vo.Cart;
import com.kh.appoproject.common.ExceptionForward;
import com.kh.appoproject.member.model.vo.Member;


@WebServlet("/cart/*")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 현재 요청 주소
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/cart").length()); // 제일 뒤에 들어오는 주소만 잘라냄
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		
		CartService cartService = new CartService();
		
		
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		// 장바구니 추가
		if(command.equals("/add")) {
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			
			try {
				PrintWriter out = response.getWriter();
				
				int result = cartService.selectNoCount(productNo, loginMember); // 장바구니에 상품이 있는지 없는지 상품 번호 조회
				
				if(result > 0) {
					out.print(0);
				}else {
					result = cartService.addCart(productNo, loginMember);
					
					if(result > 0) 	out.print(result);
					else 			out.print(-1);
				}
				
				
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "장바구니 번호 조회", e);
			}
		}
		
		
		// 장바구니 목록 조회
		else if(command.equals("/selectCart")) {
			
			try {
				List<Cart> cList = cartService.selectCart(loginMember);
				request.setAttribute("cList", cList);
				
				path = "/WEB-INF/views/cart/cart.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "장바구니 조회", e);
			}
		}
		
		// 장바구니에서 삭제
		else if(command.equals("/deleteCart")) {
			
			String checkArr = request.getParameter("checkArr");
			System.out.println(checkArr);
			
			try {
				int result = cartService.deleteCart(checkArr, loginMember.getMember_No());
				
				if(result>0) {
					msg ="장바구니에서 삭제했습니다.";
				}else {
					msg ="삭제실패";
				}
				request.getSession().setAttribute("msg", msg);
				
				response.sendRedirect("selectCart");
				
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "장바구니 삭제", e);
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
