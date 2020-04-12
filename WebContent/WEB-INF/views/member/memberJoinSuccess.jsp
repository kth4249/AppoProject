<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/form-validation.css"
	type="text/css">
</head>
<body>

	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="container" id="conarea">

		<div class="row">
			<div class="col-md-12 text-center">

				<h4>
					<strong>회원가입이 정상적으로 완료되었습니다.</strong>
				</h4>

			</div>
		</div>

		<br>

		<div class="row mt-3">
			<div class="col-md-12">
				<button class="btn btn-primary btn-block" type="button" onclick="location.href='<%=request.getContextPath()%>/member/loginForm'">로그인</button>
				<button class="btn btn-info btn-block" type="button" onclick="location.href='<%=request.getContextPath()%>'">Home</button>
			</div>
		</div>
		
	</div>
	
	<div>
	<%@ include file="../common/footer.jsp"%>
	</div>



</body>
</html>