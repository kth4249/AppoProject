<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminBaseForm</title>
<style>
.nav {
	background-color: rgb(44, 62, 80);
	height: 100%;
}

.nav a {
	color: black;
}

.nav a:hover {
	color: white;
}

.navbar {
	background-color: rgb(44, 62, 80);
}

span {
	color: rgb(149, 165, 166);
}

/* FAQenroll css */
.FAQ_answer {
	position: relative;
}

.FAQ_modify {
	position: absolute;
	bottom: 0;
	right: 30px;
}

.FAQ_modify .btn-info {
	border-radius: 0.3rem;
}

.FAQ_quetion>div {
	position: relative;
}

.FAQ_quetion img {
	position: absolute;
	top: 10px;
	bottom: 0;
	left: 20px;
	right: 0;
	margin: auto;
	width: 30px;
}

.FAQ_answer p {
	margin-left: 150px;
	margin-right: 150px;
}

#FAQ_textarea {
	background-color: rgba(211, 211, 211, 0.596);
	border: 1px solid white;
	border-radius: 15px;
	padding: 30px 50px;
	resize: none;
}

#FAQ_textarea:focus {
	outline: none;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="adminHeader.jsp"%>
		<div class="row">
			<%@ include file="adminSidebar.jsp"%>
			<div class="col-md-10">
				<!-- 컨텐트 내용을 추가 -->
				<div class="row">
					<div class="col-md-4 text-center">
						<a href="FAQ"
							class="btn btn-info btn-block active my-2 mr-2 ml-2 px-5">자주
							묻는 질문</a>
					</div>
					<div class="col-md-8 text-center">
						<h3 style="position: absolute; top: 10px;">Frequently Asked
							Questions</h3>
					</div>
				</div>


				<form action="FAQenroll" method="post">
					<div class="row">
						<div class="col-md-12">
							<div class="btn-block btn-group btn-group-toggle"
								data-toggle="buttons">
								<label class="btn btn-secondary"> <input type="radio"
									name="FAQclass" id="option2" autocomplete="off" checked
									value="배송문의"> 배송문의
								</label> <label class="btn btn-secondary"> <input type="radio"
									name="FAQclass" id="option3" autocomplete="off" value="교환/환불">
									교환/환불
								</label> <label class="btn btn-secondary"> <input type="radio"
									name="FAQclass" id="option4" autocomplete="off" value="주문결제">
									주문결제
								</label> <label class="btn btn-secondary"> <input type="radio"
									name="FAQclass" id="option5" autocomplete="off" value="회원서비스">
									회원서비스
								</label>
							</div>
						</div>
					</div>
					<div class="row FAQ_quetion">
						<div class="col-md-2">
							<img
								src="<%=request.getContextPath()%>/resources/appoimg/icon2.png">
						</div>
						<div class="col-md-10">
							<input type="text" name="FAQtitle"
								style="line-height: 2rem; width: 100%;" class="my-2 pl-1">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-12">
							<textarea name="FAQcontent" id="FAQ_textarea" cols="30"
								 rows="15" class="btn-block"></textarea>
							<p style="line-height: 30px; font-size: small;"></p>
							<br> <br>
							<div class="FAQ_modify">
								<button type="submit"
									class="btn btn-info my-1 mr-1 ml-1 px-2 py-1">
									등록</a>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	<%@ include file="adminFooter.jsp"%>
</body>
</html>