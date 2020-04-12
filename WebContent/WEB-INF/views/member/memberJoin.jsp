<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.member.model.vo.Member"%>
<%
	Member member = (Member) request.getAttribute("member");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/form-validation.css"
	type="text/css">


<title>Appo_Join</title>
<style>
/* number 태그 화살표 제거 */
input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="py-5 text-center">
		<!--  py-5 : padding y축(top) 3rem만큼 준 것 -->
		<img class="d-block mx-auto mb-4"
			src="<%=request.getContextPath()%>/resources/appoimg/appologo_White.png"
			width="72" height="auto">
		<h2 class="font-weight-bold">회원정보 입력</h2>
	</div>



	<div id="container" class="container">

		<form class="needs-validation" onsubmit="return validate();" name="memberJoin" action="join" method="POST">

			<div class="mb-3">
				<label for="id">아이디</label>
					<input type="text" class="form-control" name="id" id="id"
						placeholder="영어 소문자, 숫자 총 6~12글자" maxlength="12" required>
						
			<div><span id="checkId">&nbsp;</span></div>
			
			</div>
			
			

			<div class="mb-3">
				<label for="pwd1">비밀번호</label> 
				<input type="password" class="form-control" name="pwd1" id="pwd1" placeholder="영문 대/소문자, 숫자 총 8~20자" required>
				<div><span id="checkPwd1">&nbsp;</span></div>
			
			</div>

			<div class="mb-3">
				<label for="pwd2">비밀번호 재확인</label> 
				<input type="password" class="form-control" name="pwd2" id="pwd2" placeholder="비밀번호를 다시한번 입력해주세요." required>
				<div><span id="checkPwd2">&nbsp;</span></div>
			</div>

			<div class="mb-3">
				<label for="name">이름</label> 
				<input type="text" class="form-control" name="name" id="name" placeholder="이름을 입력해주세요." required>
			</div>


			<div class="mb-3">
				<label for="email">이메일</label> 
				<input type="email" class="form-control" name="email" id="email" placeholder="이메일을 입력해주세요.">
			</div>


			<label for="phone1">휴대전화</label>
			<div class="row">

				<div class="col-md-4 mb-3">

					<select class="custom-select d-block w-100" name="phone1" required>
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
						id="phone2" maxlength="4" required>
				</div>

				<div class="col-md-4 mb-3">
					<input type="number" class="form-control phone" name="phone3"
						id="phone3" maxlength="4" required>
				</div>

			</div>
			<br>


			<div class="row mb-3 form-row">
				<div class="col-md-3">
					<label for="postcodify_search_button">우편번호</label>
				</div>
				<div class="col-md-3">
					<input type="text" name="post"
						class="form-control postcodify_postcode5">
				</div>
				<div class="col-md-3">
					<button type="button" class="btn btn-primary"
						id="postcodify_search_button">검색</button>
				</div>
			</div>

			<div class="row mb-3 form-row">
				<div class="col-md-3">
					<label for="address1">도로명 주소</label>
				</div>
				<div class="col-md-9">
					<input type="text" class="form-control postcodify_address"
						name="address1" id="address1">
				</div>
			</div>

			<div class="row mb-3 form-row">
				<div class="col-md-3">
					<label for="address2">상세주소</label>
				</div>
				<div class="col-md-9">
					<input type="text" class="form-control postcodify_details"
						name="address2" id="address2">
				</div>
			</div>


			<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
			<script>
				$(function() {
					$("#postcodify_search_button").postcodifyPopUp();
				});
			</script>


			<br> <label for="account">계좌번호</label>
			<div class="row">

				<div class="col-md-4 mb-3">

					<select class="custom-select d-block w-100" name="bank" required>
						<option value="">은행 선택</option>
						<option>우리은행</option>
						<option>국민은행</option>
						<option>하나은행</option>
						<option>카카오뱅크</option>
						<option>외환은행</option>
						<option>기업은행</option>
						<option>새마을금고</option>
					</select>
				</div>

				<div class="col-md-8 mb-3">
					<input type="text" class="form-control" name="accNo" id="accNo"
						placeholder="'-' 를 포함하여 주십시오" required>
				</div>
			</div>

			<hr class="mb-4">
			<button class="btn btn-primary btn-lg btn-block" type="submit">가입 완료</button>

			<div class="my-5 pt-5 text-muted text-center text-small">
				<p class="mb-1">&copy; APPO INC.ALL RIGHTS RESERVED.</p>
			</div>

		</form>
	</div>
	<div>
		<%@ include file="../common/footer.jsp"%>
	</div>




	<script>
		var joinCheck = {
			"id" : false,
			"idDup" : false,
			"pwd1" : false,
			"pwd2" : false,
			"name" : false,
			"phone" : false,
			"email" : false
		};

		$(document).ready(function() {

				var $id = $("#id");
				var $pwd1 = $("#pwd1");
				var $pwd2 = $("#pwd2");
				var $name = $("#name");
				var $phone2 = $("#phone2");
				var $phone3 = $("#phone3");
				var $email = $("#email");
				var $accNo = $("#accNo");

				// 아이디  유효성 검사
				// 영어 대,소문자 + 숫자, 총 6~12글자
				$id.on("input", function() {
					var regExp = /^[a-zA-z\d]{6,12}$/;
					if (!regExp.test($id.val())) {
						$("#checkId").text("유효하지 않은 형식의 아이디 입니다.").css("color", "red");
						$("#id").css("border-color", "red");
						joinCheck.id = false;

					} else {
						joinCheck.id = true;
						
						$.ajax({
							url : "idDupCheck",
							data : {
								id : $id.val()
							},
							type : "GET",
							success : function(result) {
								if (result == "yes") {
									$("#checkId").text("사용가능한 아이디 입니다.").css("color", "green");
									$("#id").css("border-color", "green");
									
									joinCheck.idDup=true;
								}else{
									$("#checkId").text("사용할 수 없는 아이디 입니다.").css("color", "red");
									$("#id").css("border-color", "red");
									
									joinCheck.idDup=false;
								}

							},
							error : function(e) {
								console.log("아이디 중복 검사 ajax 실패");
								console.log(e);
							}

						});

					}
				});
			
				

				// 비밀번호
				// 영어 대,소문자 + 숫자, 총 8~20글자
				$pwd1.on("input", function() {
					var regExp = /^[a-zA-z\d]{8,20}$/;

					if (!regExp.test($pwd1.val())) {
						$("#checkPwd1").text("유효하지 않은 형식의 비밀번호 입니다.").css("color", "red");
						$("#pwd1").css("border-color", "red");
						
						joinCheck.pwd1 = false;

					} else {
						$("#checkPwd1").text("사용가능한 비밀번호 입니다.").css("color", "green");
						$("#pwd1").css("border-color", "green");
						joinCheck.pwd1 = true;
					}
				});

				// 비번 확인
				$pwd2.on("input", function() {
					
					if ($pwd1.val().trim() != $pwd2.val().trim()) {
						
						$("#checkPwd2").text("비밀번호가 일치하지 않습니다.").css("color", "red");
						$("#pwd2").css("border-color", "red");
						joinCheck.pwd2 = false;
						
					} else {
						$("#checkPwd2").text("비밀번호가 일치합니다.").css("color", "green");
						$("#pwd2").css("border-color", "green");
						joinCheck.pwd2 = true;
					}
				});

				// 이름
				$name.on("input", function() {
					var regExp = /^[가-힣]{2,}$/;
					if (!regExp.test($(this).val())) {
						$("#name").css("border-color", "red");
						joinCheck.name = false;
					} else {
						$("#name").css("border-color", "green");
						joinCheck.name = true;
					}
				});

				// 전화번호 관련
				$(".phone").on("input",function() {

					// 전화번호 input 태그에 4글자 이상 입력하지 못하게 하는 이벤트
					if ($(this).val().length > $(
							this).prop("maxLength")) {
						$(this).val($(this).val().slice(0,$(this).prop("maxLength")));
					}

					// 전화번호 유효성 검사
					var regExp2 = /^\d{3,4}$/; // 숫자 3~4 글자
					var regExp3 = /^\d{4,4}$/; // 숫자 4 글자

					if (!regExp2
							.test($phone2.val())) {
						$("#phone2").css("border-color","red");
						joinCheck.phone = false;
						
					} else {
						$("#phone2").css("border-color","green");
						joinCheck.phone = true;
					}

					if (!regExp3.test($phone3.val())) {
						$("#phone3").css("border-color","red");
						joinCheck.phone = false;
					} else {
						$("#phone3").css("border-color","green");
						joinCheck.phone = true;
					}

				});

				// 이메일 유효성 검사
				$email.on("input", function() {
					var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
					if (!regExp.test($email.val())) {
						$("#email").css("border-color", "red");
						joinCheck.email = false;

					} else {
						$("#email").css("border-color", "green");
						joinCheck.email = true;
					}
				});
				
			

			});

		// submit 동작
		function validate() {

			for ( var key in joinCheck) {
				if (!joinCheck[key]) {
					alert("일부 입력값이 잘못되었습니다.");
					var id = "#" + key;
					$(id).focus();
					return false;
				}
			}
		}
	</script>
</body>
</html>