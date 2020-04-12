<%@page import="com.kh.appoproject.admin.model.vo.Product"%>
<%@page import="com.kh.appoproject.admin.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
	List<Product> pList = (List<Product>) request.getAttribute("pList");
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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
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
					<div class="col-md-12">
						<h2>&nbsp;게시판 관리</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<hr>
						<h5>&nbsp;최근 블라인드 된 게시글 수 : 10</h5>
						<hr>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col">선택</th>
									<th scope="col">글 번호</th>
									<th scope="col">카테고리</th>
									<th scope="col">글 제목</th>
									<th scope="col">작성자</th>
									<th scope="col">신고수</th>
									<th scope="col">삭제</th>
									<th scope="col">패널티</th>
								</tr>
							</thead>
							<tbody>
								<%
									if (pList.isEmpty()) {
								%>
								<tr>
									<td colspan="4">존재하는 공지사항이 없습니다.</td>
								</tr>
								<%
									} else {
								%>
								<!-- <form action=""> -->
								<%
									for (Product product : pList) {
								%>
									<tr>
										<th scope="row">
											<div class="custom-control custom-checkbox">
												<input type="checkbox" class="custom-control-input"
													id="customCheck1"> <label
													class="custom-control-label" for="customCheck1"></label>
											</div>
										</th>
										<th scope="row"><%=product.getProductNo()%></th>
										<td class="notice-class"><%= product.getDeviceName() %></td>
										<td class="notice-title"><%= product.getProductTitle() %></td>
										<td><%=product.getMemberId() %></td>
										<td><%=product.getProductDeclareCount() %></td>
										<td><a onclick="return confirm('정말 삭제하시겠습니까?');"
										href="boardDelete?productNo=<%=product.getProductNo()%>&currentPage=<%=currentPage%>">
										<img 
												src="<%=request.getContextPath() %>/resources/appoimg/bin.png"></a></td>
										<td class="pl-1"><a onclick="return confirm('정말 패널티를 주시겠습니까?');"href="BoardPenalty?memberId=<%=product.getMemberId()%>"
												class="btn btn-sm btn-secondary">패널티</a></td>
									</tr>
									
								<%
									}
								%>
								<%
									}
								%>
							</tbody>
							<!-- </form> -->
						</table>
						
						<script>
							<%-- function boardDelete() {
								if(comfirm("정말 삭제하시겠습니까?")){
									location.href = "boardDelete?productNo=<%=product.getProductNo()%>&currentPage=<%=currentPage%>";
								}
							} --%>
						
						</script>
						<hr>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 d-flex flex-row-reverse">
						<div class="FAQ_modify">
							<button class="btn btn-info my-1 mr-1 ml-1 px-2 py-1">선택
								삭제</button>
							<button class="btn btn-info my-1 mr-1 ml-1 px-2 py-1">선택
								패널티</button>
						</div>
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
									href="<%=request.getContextPath()%>/admin/boardManage?currentPage=<%=currentPage - 1%>">&laquo;</a></li>

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
									href="<%=request.getContextPath()%>/admin/boardManage?currentPage=<%=p%>"><%=p%></a></li>
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
	</div>
	<%@ include file="adminFooter.jsp"%>
</body>
</html>