<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
	
	Member member = (Member)request.getAttribute("member");
	
	
	String[] phone = member.getMember_Phone().split("-");
	String[] address = {"-", "-", "-"};
	if(!member.getMember_Address().equals(",,")){
		address = member.getMember_Address().split(",");
	}



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    
<title>마이페이지_회원정보</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage_MemeberInfo.css" type="text/css">
</head>
<body>
	
	<%@include file = "../common/header.jsp" %>
	<%@include file = "../common/nav.jsp" %>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<div class="container" style="margin-left: 25%; margin-top: 5%; margin-bottom: 10%"> 
		
        <form class="form-inline" role="form" method="POST" action="infoModify" >

            <div class="row container" id="section">
                <div class="col-md-12 ">
                    <p id="basicInfo">기본정보</p>
                </div>
            </div>

                <hr id="line1" align="left">

            <div class="row container section2">
                <div class="col-md-3 lef">
                  		  아이디
                </div>
                <div class="col-md-4 middle">
                    <input class="form-group btn btn-outline-dark inputText" type="text" id="idInput"
                        value="<%= member.getMember_Id() %>" readonly>
                        <br>
                        <span id="idValidate"></span>
                </div>
                <div class="col-md-5 right">
                    (영문(소문자)/숫자 4자 이상을 입력해 주세요.)
                </div>
                                
            </div>
            
                <hr id="line2" align="left">
			
            <div class="row container section2" style="margin-bottom: -2%;">
                <div class="col-md-3 lef">
                  		  비밀번호
                    <span class="sure">*</span>
                </div>
                <div class="col-md-4 middle">
                    <input class="form-group btn btn-outline-dark inputText" type="password" id="pwdInput"
                        placeholder="비밀번호를 입력해주세요">
                        <br>
                        <span id="pwdValidate"></span>
                </div>

                <div class="col-md-5 comment right">
                    (8 ~ 20자의 영문 대/소문자, 숫자를 사용하세요)
                </div>
            </div>

            <hr id="line3" align="left">

            <div class="row container section2" style="margin-bottom: -2%;">
                <div class="col-md-3 lef">
                		    비밀번호확인
                    <span class="sure">*</span>
                </div>
                <div class="col-md-4 middle">
                    <input class="form-group btn btn-outline-dark inputText" type="password" id="pwdCheck"
                        placeholder="비밀번호를 한 번 더 입력해주세요">
                        <br>
                        <span id="pwdValidate2"></span>
                </div>

                <div class="col-md-5 comment right">
                </div>
            </div>

            <hr id="line4" align="left">


            <div class="row container section2">
                <div class="col-md-3 lef">
                   		 이름
                </div>
                <div class="col-md-4 middle">
                    <input class="form-group btn btn-outline-dark inputText" type="text" id="name"
                        value="<%= member.getMember_NM() %>" readonly>
                </div>

                <div class="col-md-5 comment right">
                </div>
                
            </div>

            <hr id="line5" align="left">
            

            <div class="row container section2">
                <div class="col-md-2 left2">
                   		 주소
                </div>
                <div class="col-md-10">
                    <input type="text" id="textAdd1" class="form-group btn btn-outline-dark middle2" name="post" value="<%= address[0]%>">
                    <span class="bar"> - </span>
                    
                    <input type="button" class="form-group btn btn-primary " id="addressBtn" value="우편번호"
                        onclick="addressBtn_execDaumPostcode();"><br>
                        
                    <div>
                    <input class="btn btn-outline-dark addrEtc" id="basicAddr" type="text" name="address1" value="<%= address[1]%>">
                    </div>
                    <div>
                    <input class="btn btn-outline-dark addrEtc" id="addAddr" type="text" name="address2" value="<%= address[2]%>">
                    </div>
                </div>
            </div>

            <hr id="line6" align="left">

            <div class="row container section2">
                <div class="col-md-2 left2">
                   		 전화번호
                </div>
                <div class="col-md-10">
                    <input type="text" class="form-group btn btn-outline-dark inputPhone" id="phone" name="phone1" value="<%= phone[0]%>">
                    <span class="bar2"> - </span>
                    <input type="text" class="form-group btn btn-outline-dark inputPhone" name="phone2" value="<%= phone[1]%>">
                    <span class="bar2"> - </span>
                    <input type="text" class="form-group btn btn-outline-dark inputPhone" name="phone3" value="<%= phone[2]%>">
                </div>
               
            </div>

            <hr id="line7" align="left">

            <div class="row container section2">
                <div class="col-md-2 left2">
                   		 이메일
                </div>
                <div class="col-md-10" >
                    <input type="email" class="form-group btn btn-outline-dark inputEmail" id="emailInput" name="email" value="<%= member.getMember_Email()%>">
                    <span id="emailValidate"></span>
                </div>
                
            </div>

            <hr id="line8" align="left">

            <div class="row container section2">
                <div class="col-md-2 left2">
                   		 계좌정보
                </div>
                <div class="col-md-10" id="account">
                    <!--  <select id="bank" class="form-group">
                        <option>---은행---</option>
                        <option>국민은행</option>
                        <option>신한은행</option>
                        <option>농협은행</option>
                        <option>우리은행</option>
                    </select> -->
                    <div><input type="text" id="accountNum" class="form-group btn btn-outline-dark"
                            name="account" value="<%= member.getMember_Account()%>"></div>
                </div>
            </div>

            
            <br>
          

            <div class="row container section3">
                <div class="col-md-8" id="bottomMiddle">
                    <input type="submit" class="btn btn-primary" id="modify" value="회원정보수정" >
                    <input type="button" class="btn btn-primary" id="cancle" value="취소">
                </div>

                <div class="col-md-4" id="bottomRight">
                    <input type="button" class="btn btn-primary" id="withDraw" value="탈퇴">
                </div>

            </div>


        </form>
        
        
    </div>
    <%@include file = "../common/footer.jsp" %> 
	
	
    <script>



        $(function(){

            var regExp = /^[a-z][a-z\d]{3,15}$/;
            $("#idInput").keyup(function(){
                if(regExp.test($(this).val())){
                    $("#idValidate").text("사용가능한아이디형식입니다.").css({"color":"#28A0FF","font-weight":"bold"});
                }else{
                    $("#idValidate").text("사용불가능한아이디형식입니다.").css({"color":"red","font-weight":"bold"});
                }
              
            })

        });

        $("#idInput").focusout(function(){
            $("#idValidate").text(" ");
        });


/*           $(function(){

            var regExp = /^[a-z\d][a-zA-Z\d]{6,20}$/;
            $("#pwdInput").keyup(function(){
                if(regExp.test($(this).val())){
                    $("#pwdValidate").text("사용가능한비밀번호입니다.").css({"color":"#28A0FF","font-weight":"bold"});
                }else{
                    $("#pwdValidate").text("사용불가능한비밀번호형식입니다.").css({"color":"red","font-weight":"bold"});
                }
            })

        });  */   
        
        
        $(function(){
        	$("#pwdInput").keyup(function(){
        		if("<%= member.getMember_Pwd() %>" == $(this).val()){
        			$("#pwdValidate").text("비밀번호가 일치합니다.").css({"color":"#28A0FF", "font-weight":"bold"});
        		}else{
        			$("#pwdValidate").text("비밀번호가 일치하지 않습니다.").css({"color":"red", "font-weight":"bold"});
        		}
        	});
        }); 

        
        $("#pwdInput").focusout(function(){
            $("#pwdValidate").text(" ");
        });
        
        

        $(function(){
            $("#pwdCheck").keyup(function(){
                if($("#pwdInput").val() == $(this).val()){
                    $("#pwdValidate2").text("비밀번호가일치합니다.").css({"color":"#28A0FF","font-weight":"bold"});
                }else{
                    $("#pwdValidate2").text("비밀번호가일치하지않습니다.").css({"color":"red","font-weight":"bold"});
                }
            });
        });

        $("#pwdCheck").focusout(function(){
            $("#pwdValidate2").text(" ");
        });
        
        
        


        var regExp = /^[a-zA-Z0-9_]{4,}@[\w]+(\.[\w]+){1,3}$/;
        $(function(){
            $("#emailInput").keyup(function(){
                if(regExp.test($(this).val())){
                    $("#emailValidate").text("양식에 일치합니다.").css({"color":"#28A0FF","font-weight":"bold"});
                }else{
                    $("#emailValidate").text("양식에 맞지 않습니다.").css({"color":"red","font-wight":"bold"});
                }
            });
        });


        $("#emailInput").focusout(function(){
            $("#emailValidate").text(" ");
        });
	
        
        
        
        


         function addressBtn_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {

                    var addr = '';
                    var extraAddr = '';


                    if (data.userSelectedType === 'R') {
                        addr = data.roadAddress;
                    } else {
                        addr = data.jibunAddress;
                    }


                    if (data.userSelectedType === 'R') {

                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }

                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }



                        document.getElementById("textAdd1").value = data.zonecode;
                        document.getElementById("basicAddr").value = addr;
                        document.getElementById("addAddr").focus();
                    }
                }
            }).open();
        } 
         
    </script>
		
	
	
	
	
</body>
</html>