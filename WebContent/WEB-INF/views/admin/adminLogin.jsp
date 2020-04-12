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
    <title>관리자 로그인 페이지</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .btn {
            width: 100%;
            font-weight: bold;
            font-size: large;
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
	<div class="login-wrapper container-fluid" style="padding-top: 180px;">
        <div class="row">
            <!-- margin -->
            <div class="col-md-3">
            </div>

            <!-- content -->
            <div class="col-md-6">
                <div class="row">
                    <!-- left -->
                    <div class="col-md-4">
                        <img src="<%=request.getContextPath() %>/resources/appoimg/appo.png" alt="이미지" style="width: 100%; height: 100%;">
                    </div>
                    <!-- right -->
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <h1>Appo Administrator Mode</h1>
                                <p>관리자 로그인을 원하시면 <strong>아이디</strong>와 <strong>비밀번호</strong>를 입력해주세요.</p>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <form action="loginCheck" method="post" onsubmit="return loginValidate();">
                                    <div class="form-group row">
                                      <label for="inputEmail3" class="col-sm-3 col-form-label">아이디</label>
                                      <div class="col-sm-9">
                                        <input type="text" class="form-control" id="adminId" name="adminId">
                                    </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="inputEmail3" class="col-sm-3 col-form-label">비밀번호</label>
                                        <div class="col-sm-9">
                                          <input type="password" class="form-control" id="adminPwd" name="adminPwd">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <button type="submit" class="btn btn-primary">관리자 로그인</button>
                                    </div>
                                </form>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="copyright"><address>
                            서울특별시 중구 남대문로 120 대일빌딩 2F 강의장A <span class="line">|</span> <span class="black">관리자 전화 :</span> 010-1234-1234
                            </address>
                            <p class="copy">Appo사이트 방문을 원할시<a href="<%= request.getContextPath()%>"> Appo 사이트 방문 </a>를 누르세요</p></div>
                    </div>
                </div>
            </div>

            <!-- margin -->
            <div class="col-md-3">
            </div>
        </div>
    </div>
    <script>
    	// 로그인 유효성 검사
    	function loginValidate() {

        	if( $("#adminId").val().trim().length == 0 ) { // 아이디 미입력시
	            alert("아이디를 입력하세요");
	            $("#adminId").focus(); // 커서 이동
	            return false;
            }
        	if( $("#adminPwd").val().trim().length == 0 ) { // 비밀번호 미입력시 
	            alert("비밀번호를 입력하세요");
	            $("#adminPwd").focus(); // 커서 이동
	            return false;
            }
            	return true;
        	}
    </script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>