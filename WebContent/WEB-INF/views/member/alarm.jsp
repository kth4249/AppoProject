<%@page import="com.kh.appoproject.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
	List<Product> pList = (List<Product>)request.getAttribute("pList");
	String imagePath = "";
	
	request.setAttribute("pList", pList);
	int count = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appo_mainPage</title>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<script type="text/JavaScript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/mypage_OrderList.css">
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>
	 <div class="container wrap" style="margin-left: 30%; margin-bottom: 0%;">
		<div class="row">
            <div class="col-md-12" id="head1">
                    Auction List <br>
					낙찰 상품 조회                                            
                <div class="row">
                     <hr>  
                	<div class="col-md-12" id="head2">
                    - 낙찰된 경매 상품의 정보를 확인하실 수 있습니다.<br>
                    - 결재하기를 클릭하면 결재창을 통해 구매를 하실 수 있습니다.
                	</div>
                </div>
			</div>
		</div>
	
		<br>
         <div class="row">
             <div class="col-md-12">
                 <table class="table">
                     <thead>
                         <tr>
                             <th>상품정보</th>
                             <th>판매자</th>
                             <th>낙찰가격</th>
                             <th>결재하기</th>
                         </tr>
                     </thead>
                     <tbody>
                     <% if(pList.isEmpty()) { %>
                            <tr>
	                			<td colspan="4">존재하는 회원이 없습니다.</td>
	                		</tr>
	                <% } else { %>
                       <% for(Product product : pList) { %>
                         <tr>
                             <td>
                                 <%  if(product.getImagePath() != null) {
                                    imagePath = request.getContextPath() + "/resources/uploadImages/" + product.getImagePath();	
                                  }%> 
                                 <img src= "<%= imagePath %>" alt="이미지" width="100" height="50">
                                 <span><%= product.getDeviceName() %></span>&nbsp;
                                 <span><%= product.getItemName() %></span>&nbsp;
                                 <span><%= product.getItemInfo() %></span>
                             </td>
                             <td><%= product.getMemberId() %></td>
                             <td><%= product.getAuctionReservePrice() %></td>
                             <td><a class="btn btn-primary paymentBtn" href="payAuction?count=<%=count%>" role="button">Link</a></td>
                             <% count++; %> 
                         </tr>
                    	<% } %>
                    <% } %>
                     </tbody>
                 </table>
             </div>
         </div>
         
	<div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</div>
</body>
</html>