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
<title>마이페이지_탈퇴2</title>
	<link rel="stylesheet" type="text/css" scr="<%=request.getContextPath() %>/css/myPage_secession2.css">
</head>
<body>
	
	<%@include file = "../common/header.jsp" %>
	
	 <div class="container">

        <div class="row">
            <div class="col-md-12">
                <h3 id="ment">아래 글자를 입력해주세요.</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <input type="text" class="btn btn-secondary box" id="message" readonly>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <input type="text" class="btn btn-secondary box" id="inputBox">
                <br><br>
                <span id="idvalidate"></span>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <input type="submit" class="btn btn-primary box" id="checkBox" value="확인">
            </div>
        </div>


    </div>


    <script type="text/javascript">
    

        

             var content = document.getElementById("message");

             var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
             var string_length = 6;
             var randomstring = '';
                  for (var i=0; i<string_length; i++) {
                     var rnum = Math.floor(Math.random() * chars.length);
                      randomstring += chars.substring(rnum,rnum+1);
                  };
                  
            $(function(){
                $("#message").val(randomstring);
            });     
                
       
            $(function(){
               $("#checkBox").click(function(){
                   if($("#message").val() == $("#inputBox").val()){
                        $(location).attr('href','#');
                   }else{
                       $("#idvalidate").text("다시 입력해주세요.");
                   }
               })
            });
    
    
    </script>
    
    <%@include file = "../common/footer.jsp" %>
	
	    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>