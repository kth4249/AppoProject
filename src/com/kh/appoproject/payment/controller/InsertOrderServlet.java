package com.kh.appoproject.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.appoproject.payment.model.service.OrderService;
import com.kh.appoproject.payment.model.vo.Order;

@WebServlet("/test/*")
public class InsertOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertOrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/test").length());
		System.out.println(command);
		
		if(command.equals("/insertOrder")) {
			Order order = new Order(request.getParameter("name"), 
									Integer.parseInt(request.getParameter("amount")), 
									request.getParameter("buyer_name"), 
									request.getParameter("buyer_email"), 
									request.getParameter("buyer_tel"), 
									request.getParameter("buyer_postcode"), 
									request.getParameter("buyer_addr"));
			try {
				String merchantUid = new OrderService().insertOrder(order);
				response.getWriter().print(merchantUid);
			}catch (Exception e) {
				System.out.println("error 발생");
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/insertImpUid")) {
			
			String impUid = request.getParameter("imp_uid");
			String merchantUid = request.getParameter("merchant_uid");
			
			//Order order = new Order
			
			System.out.println(impUid); // impUid DB에 저장하기(결제 성공시)
			// update구문이니 int result = 0 ; 구문으로 성공 행의 갯수 받아서 1이면 성공페이지 0이면 실패페이지로 이동
			System.out.println(merchantUid);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
