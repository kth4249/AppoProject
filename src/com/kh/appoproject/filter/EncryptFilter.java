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

import com.kh.appoproject.wrapper.EncryptWrapper;


@WebFilter(urlPatterns = {"/member/login","/member/join"
		,"/member/FindPwdChange","/member/deleteMember"})
public class EncryptFilter implements Filter {

  
    public EncryptFilter() {
        
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest h_request = (HttpServletRequest)request;
		// Encrypt Wrapper 객체생성
		EncryptWrapper encWrapper =new EncryptWrapper(h_request);
		chain.doFilter(encWrapper, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
