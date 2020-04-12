<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String memberId = (String) request.getAttribute("memberId");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/signin.css" type="text/css">
<title>Appo_FindPwdChange</title>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="container" id="conarea">

		<div class="mb-5">
			<h2 class="font-weight-bold text-center">비밀번호 찾기</h2>
		</div>


		<div class="row">
			<div class="col-md-12">


				<form class="form-signin p-5" action="FindPwdChange" method="POST"
					name="FindPwd" onsubmit="return validate();">

					<h4 class="mb-5 font-weight-bold text-center">새로운 비밀번호 지정</h4>

					<div class="center-block">
						<label for="inputNewPassword1" class="font-weight-bold">새로운
							비밀번호 입력</label> <input type="password" id="inputNewPassword1"
							name="inputNewPassword1" class="form-control"
							placeholder="8~20자의 영문 대/소문자, 숫자를 사용하세요." required autofocus>
					</div>


					<div class="center-block">
						<label for="inputNewPassword2" class="font-weight-bold">새로운
							비밀번호 재확인</label> <input type="password" id="inputNewPassword2"
							name="inputNewPassword2" class="form-control"
							placeholder="새로운 비밀번호를 재확인해주세요." required>
					</div>

					<div align="right">
						<button class="btn mr-3btn btn-info" type="button" onclick="location.href='<%=request.getHeader("referer")%>'">이전</button>
						<button class="btn btn-primary" type="submit" >확인</button>
					</div>
				</form>

			</div>
		</div>
	</div>

	<div>
		<%@ include file="../common/footer.jsp"%>
	</div>

	<script>
		function check(regExp, el, msg) {

			if (regExp.test(el.value)) {
				return true;
			} else {
				alert(msg);
				el.value = "";
				el.focus();
				return false;
			}
		}

		function validate() {

			var pass1 = document.getElementById("inputNewPassword1");
			var pass2 = document.getElementById("inputNewPassword2");

			var reg = /^[a-zA-Z0-9]{8,20}$/
			var m = "8~20자의 영문 대/소문자,숫자를 사용하세요"

			if (!check(reg, pass1, m)) {
				return false;
			}

			if (pass1.value != pass2.value) {
				alert("비밀번호를 확인해주세요.");
				pass2.focus();
				return false;
			}

		}
	</script>
</body>
</html>