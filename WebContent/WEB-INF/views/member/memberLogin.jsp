<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.appoproject.member.model.vo.Member"%>
<%
	Member member = (Member) request.getAttribute("member");

	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");
	
	boolean save = false; // 아이디 저장 체크박스 값을 수정하기 위한변수
	String saveId = ""; // 쿠키게 저장된 saveId라는 키가 가지고 있는 값을 저장할 변수
	Cookie[] cookies = request.getCookies(); // 전달받은 쿠키 저장
	
	// 서버 첫 시작시 request.getCookies()의 값이 null
	// -> if문으로 처리하지 않는경우 페이지 로딩시 NullPointException이 발생됨
	if(cookies != null){
		for(Cookie c : cookies){
			// 쿠키 객체에서 name을 얻어와 그 값이 "saveId"와 같은지 비교
			//		== key, 속성
			if(c.getName().equals("saveId")){
				saveId = c.getValue();
				save = true;
				
				// 클라이언트 : 서버에게 전달받은 쿠키와 안의 sessiondId를 갖고있음.
				// 서버 : 쿠키를 생성해서 안에 sessionId를 저장하여 클라이언트에게 전달
			}
			
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/signin2.css" type="text/css">
<title>Appo_login</title>
</head>
<body>



	<div>
		<%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%>

		<div class="row">
			<div class="col-md-12 mt-5">
				<form class="form-signin" method="POST" action="login" onsubmit="return loginValidate();">
					<img src="<%=request.getContextPath()%>/resources/appoimg/appologo_White.png"
						class="mb-4 rounded mx-auto d-block" width="72" height="auto">

					<h1 class="h2 mb-3 font-weight-bold text-center">로그인</h1>

					<label for="inputId" class="sr-only">아이디</label> <input name="member_Id" value="<%= saveId%>"
						type="text" id="inputId" class="form-control"
						placeholder="아이디" required autofocus> <label
						for="inputPassword" class="sr-only">비밀번호</label> <input name="member_Pwd"
						type="password" id="inputPassword" class="form-control"
						placeholder="비밀번호" required>

					<div class="checkbox mb-3">
						<label> 
							<input type="checkbox" id="save" name="save" <%= save ? "checked" :"" %>><small>&nbsp;&nbsp;아이디 저장</small>
						</label>
					</div>
					
	
					

					<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>

					<div class="text-center">
						<br> 
						<a href="<%=request.getContextPath()%>/member/Findacc">아이디 / 비밀번호 찾기</a>
						<br> 
						<a href="<%=request.getContextPath()%>/member/agreeForm">회원가입</a>
					</div>

					<p class="mt-5 mb-3 text-muted small text-center">&copy; Appo
						Corp. All Rights Reserved.</p>
					<br> <br>

				</form>

				<p class="text-center">
					문의사항이 있을경우 : <a href="#">고객지원</a>
				</p>
				<br>
				<hr>

			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>

	<script>
		function loginValidate() {
			if ($("#member_Id").val().trim().length == 0) {
				alert("아이디를 입력하세요");
				$("#member_Id").focus();

				return false;
			}

			if ($("#member_Pwd").val().trim().length == 0) {
				alert("비밀번호를 입력하세요.");
				$("#member_Pwd").focus();

				return false;
			}
			return true;

		}
	</script>
</body>
</html>