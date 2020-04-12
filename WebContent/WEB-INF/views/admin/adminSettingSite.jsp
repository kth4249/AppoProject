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
	 			<!-- 컨텐트 내용을 추가 시작 -->
	 			<form action="changeMarketPrice" method="post">
	 			<div class="form-row">
                     <div class="form-group col-md-12"> 
                         <hr>
                         <h5>시세 설정</h5>                          
                     </div>
                 </div>
                 <div class="form-row">
                     <div class="form-group col-md-10">
                         <div class="input-group-text">
                             <label class="col-sm-2" style="font-weight: bold;">시세변경할 상품명</label>
                             <select class="form-control col-sm-2 mr-sm-2" name="itemCode">
                                 <option value="101">Mac PRO 1TB</option>
                                 <option value="102">Mac Air 128GB SSD</option>
                                 <option value="103">Mac 256GB SSD</option>
                                 <option value="203">iPad mini</option>
                                 <option value="302">iPhone11</option>
                                 <option value="402">Watch NIKE</option>
                                 <option value="501">Acc Airpods</option>
                             </select>
                             
                             <input class="form-control mr-sm-2 col-sm-4" type="text" placeholder="변경할 시세 입력" name="marketPrice">
                             <button type="submit" class="btn btn-primary mr-sm-2 ">변경 하기</button>
                         </div>
                     </div> 
                 </div>
                 </form>
	 			<!-- 컨텐트 내용을 추가 끝 -->
	 		</div>	
	 	</div>
	 </div>
	 <%@ include file="adminFooter.jsp"%>
</body>
</html>