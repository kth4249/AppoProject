<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-4 mt-5">
		<ul class="list-group">
			<li class="list-group-item list-group-item-action">
			<a href="<%=request.getContextPath()%>/advice/notice/list">공지사항</a></li>
			<li class="list-group-item list-group-item-action">
			<a href="<%=request.getContextPath()%>/advice/faq/list">자주묻는질문</a></li>
			<li class="list-group-item list-group-item-action">
			<a href="<%=request.getContextPath()%>/advice/qna/main">1:1문의</a></li>
		</ul>
   </div>
</body>
</html>