package com.kh.appoproject.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionForward{

	// 항상 반복했던 내용을 언제든 쓸 수 있게 static으로 선언한것뿐
	public static void errorPage(HttpServletRequest request, HttpServletResponse response, String errorMsg, Exception e) throws ServletException, IOException{
		String path = "/WEB-INF/views/common/errorPage.jsp";
		request.setAttribute("errorMsg", errorMsg + " 과정에서 오류가 발생하였습니다.");
		e.printStackTrace();
		
		request.getRequestDispatcher(path).forward(request,response);
	}
}