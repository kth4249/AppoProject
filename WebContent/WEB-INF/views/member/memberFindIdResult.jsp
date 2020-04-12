<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
String member_Id = (String)request.getAttribute("member_Id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/signin.css" type="text/css">

<title>Appo_FindIdResult</title>
</head>
<body>

	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="container" id="conarea">

		<div class="mb-5">
			<h2 class="font-weight-bold text-center">아이디 찾기 결과</h2>
		</div>


		<div class="row">
			<div class="col-md-12">


				<form class="form-signin_1" action="loginForm" method="POST" name="FindPwd" onsubmit="return validate();">

					<h5 class="font-weight-bold text-center">
						회원님께서 찾고자 하는 아이디는<span class="member_Id">&nbsp;<%=member_Id%>&nbsp;</span>입니다.
					</h5>

					<div align="right">
						<button class="mt-3 mb-3 btn btn-primary" type="submit">로그인</button>
						
						<button class="mr-3 mt-3 mb-3 btn btn-info" type="button" 
						onclick="location.href='<%=request.getHeader("referer")%>'">비밀번호 찾기</button>
					</div>

				</form>


			</div>

		</div>

	</div>
	<div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>