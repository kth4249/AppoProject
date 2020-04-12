<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.appoproject.member.model.vo.Member"%>

<%
	String msg = (String) session.getAttribute("msg");
	Member loginMember = (Member) session.getAttribute("loginMember");
%>



<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
		crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/product.css" type="text/css">
		
		<script>
		
			<% if (msg != null) { %> 
	        	 alert("<%= msg %>");
			<% session.removeAttribute("msg");} %>
			

		
		</script>
		
		
	<title>Appo_header</title>
	</head>
	
	
	<body>
		<div class="row">
			<div class="col-md-12">
	
				<nav class="site-header sticky-top py-1">
	
	
					<div class="container d-flex flex-column flex-md-row justify-content-between">
						<a class="py-2" href="/AppoProject" aria-label="Product"> 
						<img src="<%=request.getContextPath() %>/resources/appoimg/appologo_final.png" width="24" height="auto"
							fill="none" stroke="currentColor" stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" role="img"
							viewBox="0 0 24 24" focusable="false">&nbsp;&nbsp; 
							<span class="logotxt mr-5">Appo</span></a> 
							<a class="py-2 d-none d-md-inline-block" href="<%= request.getContextPath()%>/product/list?item=Mac">Mac</a> <a
							class="py-2 d-none d-md-inline-block" href="<%= request.getContextPath()%>/product/list?item=iPad">iPad</a> <a
							class="py-2 d-none d-md-inline-block" href="<%= request.getContextPath()%>/product/list?item=iPhone">iPhone</a> <a
							class="py-2 d-none d-md-inline-block" href="<%= request.getContextPath()%>/product/list?item=Watch">Watch</a> <a
							class="py-2 d-none d-md-inline-block" href="<%= request.getContextPath()%>/product/list?item=Acc">Acc</a> <a
							class="py-2 d-none d-md-inline-block" href="<%= request.getContextPath()%>/advice/notice/list">고객지원</a> <a
							class="py-2" href="#" aria-label="Product">
	
							<li class="py-2 nav-item dropdown show d-none d-md-inline-block">
	
	
								<a class="p-0 nav-link dropdown-toggle" data-toggle="dropdown"
								href="#" role="button" aria-haspopup="true" aria-expanded="true"><img
									src="<%=request.getContextPath() %>/resources/appoimg/mypageicon.png" width="30" height="auto"></a>
	
								<% if(loginMember != null) { %>
								
								<div class="dropdown-menu">
									<a class="dropdown-item" href="<%=request.getContextPath()%>/member/infoForm">내정보</a> 
									<a class="dropdown-item" href="<%=request.getContextPath() %>/product/registerList">등록내역</a>
									<a class="dropdown-item" href="<%=request.getContextPath() %>/product/orderList">판매내역</a> 
									<a class="dropdown-item" href="<%=request.getContextPath() %>/member/alarm?memberId=<%=loginMember.getMember_Id()%>">알림</a>
									<a class="dropdown-item" href="<%=request.getContextPath()%>/cart/selectCart">장바구니</a> 
									<a class="dropdown-item" href="<%= request.getContextPath()%>/wish/wishlist">관심상품</a>
									<a class="dropdown-item" href="#">1:1대화</a> 
									<a class="dropdown-item" href="<%=request.getContextPath() %>/member/logoutForm">로그아웃</a>
								</div>
								
								<% }else{ %>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="<%=request.getContextPath() %>/member/loginForm">로그인</a>
	
								</div>
								<% } %>
	
						</li>
	
						</a>
	
					</div>
	
				</nav>
	
			</div>
		</div>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	</body>
</html>