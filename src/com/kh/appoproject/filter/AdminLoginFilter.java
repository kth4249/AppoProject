package com.kh.appoproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.appoproject.admin.model.vo.AdminLogin;

//import com.kh.sjproject.member.model.vo.Member;

@WebFilter(urlPatterns = {"/admin/adminMain" ,"/admin/adminMember" , "/admin/adminPayment", "/admin/adminPaymentInfo", "/admin/adminSetting",
							"/admin/support/*", "/admin/boardManage"}) // filter를 적용할 servlet지정
public class AdminLoginFilter implements Filter {

    public AdminLoginFilter() {

    }
    
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 로그인 확인 -> Session 필요 -> request 필요
		// sendRedirect() -> response 필요
		HttpServletRequest req = (HttpServletRequest)request; // request에는 HttpServletRequest이 저장되어 있음(다운캐스팅 가능,이렇게 지정안해주면 HttpServletRequest의 메소드 사용 못함,부모 객체라서)
		HttpServletResponse res = (HttpServletResponse)response; // response도  HttpServletResponse가 저장되어 있음
		HttpSession session = req.getSession(); 
		
		//boolean adminCheck  = (boolean)session.getAttribute("adminCheck");
		AdminLogin adminLogin = (AdminLogin)session.getAttribute("adminLogin");
				
		if(adminLogin == null) { 
			// 로그인이 되어있지 않은 경우
			//res.sendRedirect(req.getContextPath()); // 메인페이지로 이동
			res.sendRedirect(req.getContextPath() + "/admin/login"); // 관리자 로그인 이동
		} else if(adminLogin.getAdminCheck() == false){
			// 로그인입력값이 일치하지 않거나 로그아웃 경우
			//res.sendRedirect(req.getContextPath()); // 메인페이지로 이동
			res.sendRedirect(req.getContextPath() + "/admin/login"); // 관리자 로그인 이동
		} else {
			chain.doFilter(request, response);
			// 로그인이 되어 있는 경우 다음 filter 또는 요청된 Servlet이나 JSP로 이동하라는 메소드
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
