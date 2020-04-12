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

.QnA_quetion {
	position: relative;
	cursor: pointer;
}

.QnA_quetion .btn {
	position: absolute;
	bottom: 0;
	right: 30px;
}

.QnA_modify .btn-success {
	border-radius: 0.3rem;
}

#QnA_enroll {
	position: absolute;
	bottom: 0px;
	right: 10px;
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
						<a href="<%=request.getContextPath()%>/admin/support"
							class="btn btn-info btn-block my-2 mr-2 ml-2 px-5">공지사항</a>
					</div>
					<div class="col-md-4 text-center">
						<a href="<%=request.getContextPath()%>/admin/support/FAQ"
							class="btn btn-info btn-block my-2 mr-2 ml-2 px-5">자주
							묻는 질문</a>
					</div>
					<div class="col-md-4 text-center">
						<a href="<%=request.getContextPath()%>/admin/support/QnA"
							class="btn btn-info btn-block active my-2 mr-2 ml-2 px-5">QnA</a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="btn-block btn-group btn-group-toggle"
							data-toggle="buttons">
							<label class="btn btn-secondary active"> <input
								type="radio" name="options" id="option1" autocomplete="off"
								checked> 전체 문의내역
							</label> <label class="btn btn-secondary"> <input type="radio"
								name="options" id="option2" autocomplete="off"> 미완료된
								문의내역
							</label>
						</div>
					</div>
				</div>
				<div class="row QnA_quetion">
					<div class="col-md-3">
						<span id="QnA_member" class="d-flex justify-content-center"
							style="line-height: 50px; font-size: large;">DukSu1234</span> <span
							id="QnA_enroll">2019/12/27</span>
					</div>
					<div class="col-md-6">
						<p class="ml-5 mt-4">왜안되요?짱나네요</p>
					</div>
					<div class="col-md-3">
						<span class="badge badge-pill badge-secondary"
							style="position: absolute; bottom: 0px; right: 20px">미완료</span> <span
							class="badge badge-pill badge-info"
							style="position: absolute; bottom: 0px; right: 70px">new</span>
					</div>
				</div>
				<hr>

			</div>
		</div>
	</div>
	<%@ include file="adminFooter.jsp"%>
</body>
</html>