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

import com.kh.appoproject.member.model.vo.Member;


@WebFilter(urlPatterns = {"/member/mypage.do",
						"/member/changePwd.do",
						"/member/secession.do"
						,"/wish/*",
						"/cart/*",
						"/product/insertForm",
						"/product/updateForm",
						"/advice/qna/*"})
public class LoginFilter implements Filter {

   
    public LoginFilter() {
     
    }


	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 로그인 확인 -> Session -> request
		// sendRedirect() -> response
		
		// getSession 은 http 프로토콜 통신에서만 사용가능하다
		// -> 부모인 ServletRequest/HttpServletResponse을 자식인 HttpServletRequest/HttpServletResponse로 다운캐스팅 해준다.
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			// 로그인이 되어있지 않은 경우 
			session.setAttribute("msg", "잘못된 경로로 접근하였습니다.");
			res.sendRedirect(req.getContextPath() + "/member/loginForm" ); // 메인페이지로 이동
		}else {
			chain.doFilter(request, response);
			// 로그인이 되어있는 경우
			// 다음 필터 또는 요청된 servlet/jsp로 이동 
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
