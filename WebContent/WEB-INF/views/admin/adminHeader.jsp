<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)session.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" type="text/css">
<title>admin-header</title>
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

        .open-btn {
            cursor: pointer;
        }

    </style>
    <script>
		// 로그인 실패 메세지 출력 후
		// session에 남아있는 "msg" 속성 제거
		<% if(msg!= null) {%> 
			alert("<%= msg %>");
		<%  
			session.removeAttribute("msg");
			// session내에 남아있는 msg의 value를 제거
			}
		%>
	</script>
</head>
<body>
	    <!-- header1 -->
        <div class="row">
        <div class="col-md-9" style="background-color: rgb(44, 62, 80);">
            <nav class="navbar navbar-expand-lg navbar-dark">
                <a class="navbar-brand" href="#"><img src="<%= request.getContextPath() %>/resources/appoimg/appo3.png" alt="이미지" style="width: 40px;"></a>
                <a class="navbar-brand" href="#">Appo</a>
            </nav>
        </div>
        <div class="col-md-3" style="background-color: rgb(44, 62, 80);">
            <nav class="navbar navbar-expand-lg navbar-dark"></nav>
            <span>관리자님 환영합니다</span>&nbsp;&nbsp;&nbsp;
            <a class="btn btn-secondary" href="<%= request.getContextPath()%>/admin/logOut" role="button">로그아웃</a>
        </div>
    </div>

    <!-- header2 -->
    <div class="row">
        <div class="col-md-2">
            <p>
            	<h2>관리자페이지</h2>
            </p>
        </div>
        <div class="col-md-10">
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>