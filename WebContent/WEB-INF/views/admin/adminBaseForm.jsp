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
    </style>
</head>
<body>
	 <div class="container-fluid">
	 	<%@ include file="adminHeader.jsp"%>
	 	<div class="row">
	 		<%@ include file="adminSidebar.jsp"%>
	 		<div class="col-md-10">
	 			<!-- 컨텐트 내용을 추가 -->
	 		</div>	
	 	</div>
	 </div>
	 <%@ include file="adminFooter.jsp"%>
</body>
</html>