package com.kh.appoproject.destination.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.appoproject.common.ExceptionForward;
import com.kh.appoproject.destination.model.service.DestinationService;
import com.kh.appoproject.destination.model.vo.Destination;
import com.kh.appoproject.member.model.vo.Member;

@WebServlet("/destination/*")
public class DestinationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DestinationController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 현재 요청 주소
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/destination").length()); // 제일 뒤에 들어오는 주소만 잘라냄
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		
		DestinationService destService = new DestinationService();
		
		if(command.equals("/addDest")) {
			String post = request.getParameter("post");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String destinationAddr = post + "," + address1 + "," + address2;
			
			String destinationName = request.getParameter("destName");
			String destinationReceiver = request.getParameter("receiveName");
			String destinationContact = request.getParameter("phone");
			String destinationNote = request.getParameter("note");
			
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			int memberCode= loginMember.getMember_No();
			
			Destination dest = new Destination(destinationAddr, memberCode, destinationContact, destinationNote, destinationName, destinationReceiver);
			
			try {
				
				 int result = destService.addDest(dest);
				 
				 response.getWriter().print(result);
				 
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "배송지 추가", e);
			}
			
		}
		
		if(command.equals("/updateDest")) {
			int destinationNo = Integer.parseInt(request.getParameter("destNo"));
			String post = request.getParameter("post");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String destinationAddr = post + "," + address1 + "," + address2;
			
			String destinationName = request.getParameter("destName");
			String destinationReceiver = request.getParameter("receiveName");
			String destinationContact = request.getParameter("phone");
			String destinationNote = request.getParameter("note");
			
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			int memberCode= loginMember.getMember_No();
			
			Destination dest = new Destination(destinationAddr, memberCode, destinationContact, destinationNote, destinationName, destinationReceiver);
			dest.setDestinationNo(destinationNo);
			System.out.println(dest);
			
			try {
				
				 int result = destService.updateDest(dest);
				 
				 response.getWriter().print(result);
				 
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "배송지 수정", e);
			}
			
		}
		
		
		
		else if(command.equals("/selectDest")) {
			int destinationNo = Integer.parseInt(request.getParameter("destNo"));
			System.out.println("전달받은 배송지넘버 : " + destinationNo);
			try {
				Destination dest = destService.selectDest(destinationNo);
				Gson gson = new Gson();
				
				gson.toJson(dest, response.getWriter());
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "배송지 표시", e);
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
