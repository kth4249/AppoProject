<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.appoproject.admin.model.vo.Notice"%>
<%@page import="com.kh.appoproject.admin.model.vo.PageInfo"%>
<%
	List<Notice> nList = (List<Notice>) request.getAttribute("nList");
	PageInfo pInf = (PageInfo) request.getAttribute("pInf");
	//String searchKey = request.getParameter("searchKey");
	//String searchValue = request.getParameter("searchValue");

	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
%>

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
							class="btn btn-info btn-block active my-2 mr-2 ml-2 px-5">공지사항</a>
					</div>
					<div class="col-md-4 text-center">
						<a href="<%=request.getContextPath()%>/admin/support/FAQ"
							class="btn btn-info btn-block my-2 mr-2 ml-2 px-5">자주 묻는 질문</a>
					</div>
					<div class="col-md-4 text-center">
						<a href="<%=request.getContextPath()%>/admin/support/QnA"
							class="btn btn-info btn-block my-2 mr-2 ml-2 px-5">QnA</a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-hover" id="notice-table">
							<thead>
								<tr>
									<th scope="col">글 번호</th>
									<th scope="col">분류</th>
									<th scope="col">글 제목</th>
									<th scope="col">작성날짜</th>
								</tr>
							</thead>
							<tbody>
								<%
									if (nList.isEmpty()) {
								%>
								<tr>
									<td colspan="4">존재하는 공지사항이 없습니다.</td>
								</tr>
								<%
									} else {
								%>
								<%
									for (Notice notice : nList) {
								%>
								<tr>
									<th><%=notice.getNoticeNo()%></th>
									<td><%=notice.getNoticeClass()%></td>
									<td><%=notice.getNoticeTitle()%></td>
									<td><%=notice.getNoticeCreateDate()%></td>
								</tr>
								<%
									}
								%>
								<%
									}
								%>
							</tbody>
						</table>
						<hr>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<a href="support/noticeInsertForm"
							class="btn btn-info my-1 mr-1 ml-1 px-5 py-1"
							style="position: absolute; top: 0px; right: 30px;">등록</a>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12 d-flex justify-content-center">
						<div>
							<ul class="pagination pagination">
								<li
									class="page-item 
								<%if (currentPage == 1) {%>
								disabled
								<%}%>
								"><a
									class="page-link"
									href="<%=request.getContextPath()%>/admin/support?currentPage=<%=currentPage - 1%>">&laquo;</a></li>

								<%
									for (int p = startPage; p <= endPage; p++) {
								%>
								<%
									if (p == currentPage) {
								%>

								<li class="page-item active"><a class="page-link" href="#"><%=p%></a></li>
								<%
									} else {
								%>
								<li class="page-item"><a class="page-link"
									href="<%=request.getContextPath()%>/admin/support?currentPage=<%=p%>"><%=p%></a></li>
								<%
									}
								%>
								<%
									}
								%>
								<li
									class="page-item <%if (currentPage == maxPage) {%> disabled <%}%>"><a
									class="page-link"
									href="<%=request.getContextPath()%>/admin/support?currentPage=<%=currentPage + 1%>">&raquo;</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="adminFooter.jsp"%>


		<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#notice-table td").click(function(){
				var noticeNo = $(this).parent().children().eq(0).html();
				console.log(noticeNo);
				// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
				location.href=
					"<%=request.getContextPath()%>/admin/support/noticeModifyForm?noticeNo="
											+ noticeNo
											+ "&currentPage="
											+ <%=currentPage%>;

								}).mouseenter(function() {
							$(this).parent().css("cursor", "pointer");

						});

			});
		</script>
</body>
</html>