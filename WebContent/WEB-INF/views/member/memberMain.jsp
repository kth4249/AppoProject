<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="//code.jquery.com/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
    <script type="text/JavaScript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>마이페이지_메인</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/myPage_Main.css">
</head>
<body>
	
	<%@ include file = "../common/header.jsp" %>

	
	<hr>
	
    <div class="container-fluid">
        <div class="row element">
            <div class="col-md-12" id="profile" onclick="location.href='myPage_MemberInfo.jsp';">
                <h6>Profile 회원정보</h6>
                 <p>고객님의 개인정보를 관리하는 공간입니다.</p>
            </div>
        </div>

        <br>
        <div class="row element">
            <div class="col-md-12" id="register" onclick="location.href='myPage_RegisterBasic.jsp';">
                <h6>Register 등록내역</h6>
                 <p>고객님께서 등록하신 일반 / 경매 판매 내역을 확인하실 수 있습니다.</p>
            </div>
        </div>
        
        <br>
        <div class="row element">
            <div class="col-md-12" id="productPurchase" onclick="location.href='#';">
                <h6>Product Purchase 구매내역 </h6>
                 <p>고객님께서 구매하신 상품 내역을 확인하실 수 있습니다.</p>
            </div>
        </div>

        <br>
        <div class="row element">
            <div class="col-md-12" id="notice" onclick="location.href='#';">
                <h6>Notice 알림</h6>
                 <p>고객님과 관련된 다양한 소식을 확인하실 수 있습니다.</p>
            </div>
        </div>

        <br>
        <div class="row element">
            <div class="col-md-12" id="chatting" onclick="location.href='#';">
                <h6>Chatting 1:1대화</h6>
                 <p>다른 고객님과의 대화를 통해 더 상세한 정보를 확인하실 수 있습니다.</p>
            </div>
        </div>
    </div>

    <hr>
    
    <%@include file = "../common/footer.jsp" %>
    
    
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>













































