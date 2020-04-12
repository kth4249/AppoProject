<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String memberId = (String) request.getAttribute("memberId");
%>
<%
	String member_Id = (String) request.getAttribute("member_Id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/signin.css" type="text/css">
<title>Appo_FindIdPwd</title>

<style>
/* number 태그 화살표 제거 */
input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

#phone_div {
	display: none;
}
</style>

</head>

<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="container" id="conarea">


		<div class="mb-5">
			<h2 class="font-weight-bold  text-center">아이디/비밀번호 찾기</h2>
		</div>


		<div class="row">
			<div class="col-md-6">


				<form class="form-signin" action="FindId" method="POST" name="FindId" onsubmit="return validate1();">

					<div class="findbox">

						<h4 class="mb-4">아이디 찾기</h4>

						<div>
							<label for="inputName">이름</label> <input type="text"
								id="inputName" name="inputName" class="form-control"
								placeholder="이름을 입력해주세요." required>
						</div>

						<div class="radio-e-ph mt-3">
							<input type="radio" id="e_radio" name="findIdRadio"
								onclick="setDisplay_em()" checked> <label for="e_radio">&nbsp;&nbsp;이메일</label>
						</div>

						<div class="radio-e-ph">
							<input type="radio" id="ph_radio" name="findIdRadio"
								onclick="setDisplay_ph()"> <label for="ph_radio">&nbsp;&nbsp;휴대폰</label>
						</div>

						<div id="email_div">
							<label for="inputEmail" class="em mt-3">이메일</label> <input
								type="email" id="inputEmail" name="inputEmail"
								class="form-control mb-3" placeholder="이메일을 입력해주세요.">
						</div>


						<div id="phone_div">
							<label for="phone1" class="em mt-3">휴대폰</label>

							<div class="row">

								<div class="col-md-4 mb-3">
									<select class="custom-select d-block w-100 form-control"
										name="phone1" id="phone1">
										<option value="">선택</option>
										<option>010</option>
										<option>011</option>
										<option>016</option>
										<option>070</option>
										<option>019</option>
									</select>
								</div>

								<div class="col-md-4 mb-3">
									<input type="number" class="form-control phone" name="phone2"
										id="phone2" maxlength="4">
								</div>

								<div class="col-md-4 mb-3">
									<input type="number" class="form-control phone" name="phone3"
										id="phone3" maxlength="4">
								</div>
								
								<input type="hidden" name="hide" id="hide" value="1">

							</div>
						</div>
						<div align="right">
							<button class="btn btn-primary" type="submit">아이디 찾기</button>
						</div>
					</div>
				</form>

			</div>




			<div class="col-md-6">


				<form class="form-signin" action="FindPwd" method="POST" name="FindPwdForm"
					onsubmit="return validate2();">

					<div class="findbox">
						<h4 class="mb-4">비밀번호 찾기</h4>

						<div>
							<label for="inputId" class="inputId">아이디</label> <input
								type="text" id="inputId" name="inputId" class="form-control"
								placeholder="아이디를 입력해주세요." required>
						</div>

						<div>
							<label for="inputName2" class="inputName2 mt-2">이름</label> <input
								type="text" id="inputName2" name="inputName2"
								class="form-control" placeholder="이름을 입력해주세요." required>
						</div>

						<div>
							<label for="inputEmail2" class="inputEmail2 mt-2">이메일</label> 
							<input type="email" id="inputEmail2" name="inputEmail2" class="form-control mb-3"
								placeholder="이메일을 입력해주세요." required>
						</div>

						<div align="right">
							<button class="btn btn-primary" type="submit">비밀번호 찾기</button>
						</div>

					</div>
				</form>
			</div>

		</div>
	</div>

	<div>
		<%@ include file="../common/footer.jsp"%> 
	</div>



	<script>
		// 이메일,휴대폰 라디오버튼 누르면 형식 체인지 구문
		function setDisplay_em() {
			$("#email_div").show();
			$("#phone_div").hide();
			FindIdCheck1.phone = true;
			FindIdCheck1.email = false;
			
			$("input[type=hidden][name=hide]").val("1");
		};

		function setDisplay_ph() {
			$("#email_div").hide();
			$("#phone_div").show();
			FindIdCheck1.phone = false;
			FindIdCheck1.email = true;
			
			$("input[type=hidden][name=hide]").val("2");

		};

		var FindIdCheck1 = {
			"name" : false,
			"email" : false,
			"phone" : true
		};

		$(function() {
			if ($(this).val().length > $(this).prop("maxLength")) {
				$(this).val($(this).val().slice(0, $(this).prop("maxLength")));
			}
		});

		function validate1() {

			var $name = $("#inputName");
			var $phone2 = $("#phone2");
			var $phone3 = $("#phone3");
			var $email = $("#inputEmail")

			var regExp = /^[가-힣]{2,}$/;
			if (!regExp.test($name.val())) {
				FindIdCheck1.name = false;
				alert("이름 형식이 유효하지 않습니다.");
			} else {
				FindIdCheck1.name = true;
			}

			if (!FindIdCheck1.email) {
				var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
				if (!regExp.test($email.val())) {
					alert("이메일 형식이 유효하지 않습니다.");
					FindIdCheck1.email = false;

				} else {
					FindIdCheck1.email = true;
				}
			}

			if (!FindIdCheck1.phone) {
				var regExp2 = /^\d{3,4}$/;
				var regExp3 = /^\d{4,4}$/;

				if (!regExp2.test($phone2.val())
						|| !regExp3.test($phone3.val())) {
					alert("유효하지 않은 전화번호 형식입니다.");
					FindIdCheck1.phone = false;
				} else {
					FindIdCheck1.phone = true;
				}
			}

			if (FindIdCheck1.name == true && FindIdCheck1.phone == true
					&& FindIdCheck1.email == true) {
				return true;
			} else {
				//alert("빈 칸을 모두 작성해주세요.");
				return false;
			}
			
		


		}

		function validate2() {

			var $id = $("#inputId");
			var $name = $("#inputName2");
			var $email = $("#inputEmail2");

			var regExp = /^[a-zA-z\d]{6,12}$/;
			if (!regExp.test($id.val())) {
				alert("아이디 형식이 유효하지 않습니다.");
				FindIdCheck2.id = false;
				return false;
			} else {
				FindIdCheck2.id = true;
			}

			var regExp = /^[가-힣]{2,}$/;
			if (!regExp.test($name.val())) {
				alert("이름 형식이 유효하지 않습니다.");
				FindIdCheck2.name = false;
				return false;
			} else {
				FindIdCheck2.name = true;
			}

			if (!FindIdCheck2.email) {
				var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
				if (!regExp.test($email.val())) {
					alert("이메일 형식이 유효하지 않습니다.");
					FindIdCheck2.email = false;
					return false;
				} else {
					FindIdCheck2.email = true;
				}
			}

			if (FindIdCheck2.name == true && FindIdCheck2.id == true
					&& FindIdCheck2.email == true) {
				return true;
			} else {
				//alert("빈 칸을 모두 작성해주세요.");
				return false;
			}
		}
		
		
		
		
	</script>




</body>
</html>



