<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.product.model.vo.Product"%>
<%@page import="com.kh.appoproject.product.model.vo.Image"%>
<%@page import="java.util.ArrayList"%>
<%
	Product product = (Product)request.getAttribute("product");
	ArrayList<Image> files = (ArrayList<Image>)request.getAttribute("files");
	
	String currentPage = request.getParameter("currentPage");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/productdetail.css"
	type="text/css">

</head>
<body>
    <%@ include file="../common/header.jsp"%>
    <%@ include file="../common/nav.jsp"%>
    <div class="product-details ptb-100 pb-90">
        <div class="container product-details-container">
            <div class="row">
                <!-- 이미지 -->
                <div class="col-md-12 col-lg-7 col-12">
					<div class="product-details-large tab-content mr-35">
						<div class="product-details-large tab-content">
                    <% if(files == null) {%>
                        <p>이미지가 없습니다.</p>
                    <% } %>

                    <% if(files != null){ %>
                        <%  for(int i=0; i<files.size(); i++) { %>
                        <% if(i==0) { 
                                    String src1 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <div class="tab-pane active show fade" id="pro-details1" role="tabpanel">
                            <div> <img src="<%= src1%>">
                            </div>
                        </div>
                        <% } else if(i==1) {
                                    String src2 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <div class="tab-pane fade" id="pro-details2" role="tabpanel">
                            <div> <img src="<%= src2%>">
                            </div>
                        </div>
                        <% } else if(i==2) {
                                    String src3 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <div class="tab-pane fade" id="pro-details3" role="tabpanel">
                            <div> <img src="<%= src3%>">
                            </div>
                        </div>
                        <% } else if(i==3) {
                                    String src4 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <div class="tab-pane fade" id="pro-details4" role="tabpanel">
                            <div> <img src="<%= src4%>">
                            </div>
                        </div>
                        <% } else if(i==4) {
                                    String src5 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <div class="tab-pane fade" id="pro-details5" role="tabpanel">
                            <div> <img src="<%= src5%>">
                            </div>
                        </div>
                        <% } %>
                        <% } %>
                    <% } %>
                    </div>
                    <div class="product-details-small nav mt-2 overflow-hidden" role=tablist>
                        <% if(files != null){ %>
                        <%  for(int i=0; i<files.size(); i++) { %>
                        <% if(i==0) { 
                                    String src1 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <a class="active mr-10" href="#pro-details1" data-toggle="tab" role="tab" aria-selected="true">
                            <img src="<%= src1 %>">
                        </a>
                        <% } else if(i==1) {
                                    String src2 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <a class="mr-10" href="#pro-details2" data-toggle="tab" role="tab" aria-selected="true"> <img
                                src="<%= src2 %>">
                        </a>
                        <% } else if(i==2) {
                                    String src3 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <a class="mr-10" href="#pro-details3" data-toggle="tab" role="tab" aria-selected="true"> <img
                                src="<%= src3 %>">
                        </a>
                        <% } else if(i==3) {
                                    String src4 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <a class="mr-10" href="#pro-details4" data-toggle="tab" role="tab" aria-selected="true"> <img
                                src="<%= src4 %>">
                        </a>
                        <% } else if(i==4) {
                                    String src5 = request.getContextPath()+"/resources/uploadImages/"+files.get(i).getImagePath(); %>
                        <a class="mr-10" href="#pro-details5" data-toggle="tab" role="tab" aria-selected="true"> <img
                                src="<%= src5 %>">
                        </a>
	                    <% } %>
	                    <% } %>
	                    <% } %>
	                    </div>
	                </div>
	            </div>
	            <!-- 상품 정보 오른쪽 -->
	            <div class="col-md-12 col-lg-5 col-12">
	                <div class="product-details-content">
	                    <div class="product-details-num-report">
	                        <div class="product-details-num pb-2 my-4">
	                            <span>상품번호 : </span> <span id="details-num"><%=product.getProductNo()%></span>
	                            
	                            <% if(loginMember != null){ %>
									<% if((loginMember.getMember_No() == product.getMemberReg()) && product.getProductForm().equals("B")){ %>
									<button class="btn btn-outline-secondary btn-sm float-right" id="deleteBtn">삭제</button>
									<a href="updateForm?no=<%= product.getProductNo() %>&item=<%=product.getDeviceName() %>" role="button" class="btn btn-outline-secondary btn-sm float-right"
		                                id="productModify">수정</a>
									<% } else if(loginMember.getMember_No() != product.getMemberReg()){ %>
									<button class="btn btn-danger btn-sm float-right" id="report-button" onclick="declare();">신고</button>
									<% } else{%>
									<button class="btn btn-outline-secondary btn-sm float-right" id="deleteBtn">삭제</button>
									<% } %>
								<% } %>
	                        </div>
	                    </div>
	                    <h3 class="pb-4" id="details-title"><%=product.getProductTitle()%></h3>
	                    <div class="product-details-categorize pb-1">
	                        <div class="details-inform-left wd-100 mr-4">
	                            <span>제품정보</span>
	                        </div>
	                        <span id="details-categorize1"><%=product.getDeviceName()%></span>
	                        <span id="details-categorize2"><%=product.getItemName()%></span>
	                        <span id="details-categorize3"></span>
	                    </div>
	                    <hr>
	                    <div class="product-details-seller pb-1">
	                        <div class="details-inform-left wd-100 mr-4">
	                            <span>판매자 정보</span>
	                        </div>
	                        <span id="details-seller"><%=product.getMemberId()%></span>
	                    </div>
	                    <div class="product-details-date pb-1">
	                        <div class="details-inform-left wd-100 mr-4">
	                            <span>상품 등록일</span>
	                        </div>
	                        <span id="details-date"><%=product.getProductRegDate()%></span>
	                    </div>
	                    <hr>
	                    <div class="product-details-del pb-1">
	                        <div class="details-inform-left wd-100 mr-4">
	                            <span>배송방법</span>
	                        </div>
	                        <span id="details-del">판매자->Appo->구매자</span>
	                    </div>
	                    <div class="product-details-fee pb-1">
	                        <div class="details-inform-left wd-100 mr-4">
	                            <span>배송비</span>
	                        </div>
	                        <span id="details-del-fee">3000 원</span>
	                    </div>
	                    <hr>
	                
	
		                <!-- 일반 경매 -->
		                <% if(product.getProductForm().equals("B")) {%>
		                <div class="product-details-price pb-4">
		                    <div class="d-inline">상품금액</div>
		                    <div class="details-price d-inline float-right">
		                        <span id="details-price"><%= product.getBasicPrice() %></span> <span>원</span>
		                    </div>
		                </div>
						
		                <form class="quickview-cart">
		                    <input type="hidden" name="productNo" value=<%= product.getProductNo() %>>
		                    <div class="d-inline-flex">
		                    <% if(loginMember == null){ %>
		                    	<!--  <a href="#" role="button" class="btn px-5 mr-2" id="buy-btn1">구매하기</a>-->
		                    <% } else if(loginMember != null && !loginMember.getMember_Id().equals(product.getMemberId())){ %>
		                        <!--  <a href="#" role="button" class="btn px-5 mr-2" id="buy-btn1">구매하기</a>-->
		                        <a href="#cart-modal" data-toggle="modal" role="button"
		                            class="btn btn-secondary px-5 mr-2" id="addCart" onclick="addCart()">장바구니</a> 
		                    <% } %>
		                    </div>
		                </form>
		                <% } else{ %>
		
		                <div class="product-details-deadline pb-1 p-0">
		                    <div class="details-inform-left wd-100 mr-4 p-0">
		                        <span>경매 마감일</span>
		                    </div>
		                    <div class="details-deadline d-inline">
		                        <span id="details-deadline"><%= product.getAuctionDeadline() %></span>
		                    </div>
		                </div>
		                <div class="product-details-dir pb-1 p-0">
		                    <div class="details-inform-left wd-100 mr-4">
		                        <span>바로 구매가</span>
		                    </div>
		                    <div class="details-dir-price d-inline">
		                     <% if(product.getAuctionImmediateBid()!=0){ %>
		                        <span id="details-dir-price"><%= product.getAuctionImmediateBid() %></span>
		                        <span>원</span>
		                     <% } else { %>
		                     	<span>-</span>
		                     <% } %>
		                    </div>
		                </div>
		                <div class="product-details-price pb-4">
		                    <div class="d-inline">현재 입찰금액</div>
		                    <div class="details-price d-inline float-right">
		                        <span id="details-price"><%= product.getAuctionReservePrice() %></span>
		                        <span>원</span>
		                    </div>
		                </div>
		                <form class="quickview-cart">
		                    <input type="hidden" name="productNo" value=<%= product.getProductNo() %>>
		                    <div class="d-inline-flex">
		                    <% if(loginMember == null){ %>
		                    	<a href="#" data-toggle="modal" data-target="#auction-modal" role="button" class="btn px-5 mr-1"
		                            id="auction-btn">입찰하기</a>
		                       <!--  <a href="#" role="button" class="btn px-5 mr-1"
		                            id="buy-btn2">바로구매</a> -->
		                    <% } else if(loginMember != null && !loginMember.getMember_Id().equals(product.getMemberId())){ %>
		                        <a href="#" data-toggle="modal" data-target="#auction-modal" role="button" class="btn px-5 mr-1"
		                            id="auction-btn">입찰하기</a> 
		                        <% if(product.getAuctionImmediateBid()!=0){ %>
		                       <!--  <a href="#" role="button" class="btn px-5 mr-1"
		                            id="buy-btn2">바로구매</a> -->
		                        <% } %>   
		                        <a href="#wish-modal" data-toggle="modal" role="button" class="btn btn-secondary px-3" id="addWish" onclick="addWish()">관심상품</a>
		                    <% } %>
		                    </div>
		                </form>
		
		                <% } %>
	                </div>
            	</div>
            	
            	
                <!-- 모달 -->
                <!-- 장바구니 모달 -->
                <div class="modal" id="cart-modal">
                    <div class="modal-dialog" role="document" style="margin-top:22em;">
                        <div class="modal-content">
                            <div class="modal-body">
                            	<p></p>
                                <p>상품을 장바구니에 담았습니다.</p>
                                <p>
                                    <strong>장바구니로 이동하시겠습니까?</strong>
                                </p>
                            </div>
                            <div class="cart-modal-footer">
                                <button type="button" class="btn go-cart" id="go-cart" onclick="location.href='/AppoProject/cart/selectCart'">장바구니 가기</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <!-- 입찰 모달 -->
                <div class="modal" id="auction-modal">
                    <div class="modal-dialog auction-modal" role="document" style="margin-top:12em;">
                        <div class="modal-content">
                            <div class="modal-body">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <form method="POST" action="">
                                    <table class="table">
                                        <tr>
                                            <td class="modal-td">상품번호</td>
                                            <td><%= product.getProductNo() %></td>
                                        </tr>
                                        <tr>
                                            <td class="modal-td">경매마감일</td>
                                            <td><%= product.getAuctionDeadline() %></td>
                                        </tr>
                                        <tr>
                                            <td class="modal-td">현재가</td>
                                            <td><span class="text-danger" id="modalCurrentPrice"><%= product.getAuctionReservePrice() %></span>원
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="modal-td">바로구매가</td>
                                            <td>
                                            <% if(product.getAuctionImmediateBid()!=0){ %>
                                            <span><%= product.getAuctionImmediateBid() %></span>원
                                            <% } else {%>
                                            <span>-</span>
                                            <% } %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="modal-td">입찰금액</td>
                                            <td><input value="<%= product.getAuctionReservePrice() %>" class="text-danger"
                                                    id="currentPrice" style="text-align: right;" readonly></input>
                                                    <label>원 보다 큰 금액만 입찰할 수 있습니다.</label> <br> 
                                                <input type="number" id="biddingPrice" style="text-align: right;" step="1000">원
                                                (천단위 미만 입력시 올림처리)
                                                <p id="checkPrice"></p>
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="modal-bt">
                                        <button type="submit" class="btn btn-danger" id="goAuction">입찰하기</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 경매 -->
                <div class="modal" id="wish-modal">
                    <div class="modal-dialog mt-22" role="document" style="margin-top:22em;">
                        <div class="modal-content">
                            <div class="modal-body">
                            	<p></p>
                                <p>상품을 관심상품에 담았습니다.</p>
                                <p><strong>관심상품 페이지로 이동하시겠습니까?</strong></p>
                            </div>
                            <div class="cart-modal-footer">
                                <button type="button" class="btn go-cart" id="go-wish" onclick="location.href='/AppoProject/wish/wishlist'">관심상품 가기</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                            </div>
                        </div>
                    </div>
                </div>            	
            	
            	
            	
            	
            	
            	
            </div>
        </div>
    </div>

    <!-- 상품설명/제품정보 -->
    <div class="product-description-review-area pb-90 pt-2">
        <div class="container">
            <div class="product-description-review text-center">
                <div class="description-review-title nav" role=tablist>
                    <a class="active" href="#pro-dec" data-toggle="tab" role="tab" aria-selected="true"> 상품설명 </a> 
                </div>
                <hr>
                <div class="description-review-text tab-content pt-5">
                    <div class="tab-pane active show fade" id="pro-dec" role="tabpanel">
                    <% if(product.getProductComment()!=null){ %>
                        <p><%= product.getProductComment() %></p>
                    <% } else {%>
                    	<p> 등록한 상품 설명이 없습니다. </p>
                    <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<script>
       $(function(){
           $("#biddingPrice").on("input",function(){
               if( parseInt($("#biddingPrice").val()) <= parseInt($("#currentPrice").val())){
                   $("#checkPrice").text("현재 입찰금액보다 큰 금액을 입력해주세요.").css("color","red");
                   return false;
               }else{
                   $("#checkPrice").text("참여 가능한 금액입니다.").css("color","green");
                   return true;
               }
           });
           
           // 천 단위 아래 내림
           $('#biddingPrice').on('change', function() {
               var n = $(this).val(); 
               n = Math.ceil(n/1000) * 1000; 
               $(this).val(n);
           });
           
           // 삭제 확인
           $("#deleteBtn").on("click",function(){
        	  if(confirm("정말 삭제하시겠습니까?")) location.href = "delete?no=<%= product.getProductNo()%>&item=<%=product.getDeviceName() %>";
           });
      });
      
       // 신고
       function declare(event){
     	  <%if(loginMember!=null){ %>
   			 if(confirm("정말 신고하시겠습니까?")){ 
 				var memberId = "<%= loginMember.getMember_Id()%>";
 				var productNo = "<%= product.getProductNo()%>";
 				$.ajax({
 					url : "reportProduct",
 					type : "post",
 					data : {memberId:memberId,productNo:productNo},
 					success : function(msg) {
 						alert(msg);
 					},
 					error : function() {
 						alert("ajax 통신 실패");
 					}
 				});
 			}
     	  <%}%>
       }
       
      // 장바구니 추가
      function addCart(event){
   			// 상품 번호를 server로 전달
   			var productNo = <%=product.getProductNo()%>;
   			
   			var check = true;
   			
   			$.ajax({
   				url : "<%= request.getContextPath() %>/cart/add",
   				type : "get",
   				data : {productNo:productNo},
   				success : function(result){
   					if(result == 0){
   						$("#cart-modal").find(".modal-body").children().eq(0).text("이미 장바구니에 담긴 상품입니다.");
   					}else if(result > 0){
   						check = false;
   					}
   				},
   				error: function(){
   					console.log("ajax 실패");
   				}
   			});
   			if(check)  event.preventDefault();	
      }
      
      // 관심상품 추가
      function addWish(event){
  		   var productNo = <%=product.getProductNo()%>;
  		   var check = true;
  		   $.ajax({
  				url : "<%= request.getContextPath() %>/wish/add",
  			   type : "get",
  			   data : {productNo:productNo},
  			   success:function(result){
  				   if(result==0){
  					   $("#wish-modal").find(".modal-body").children().eq(0).text("이미 관심상품에 담긴 상품입니다.");
  				   }else if(result>0){
  					   check = false;
  				   }
  			   },
  			   error : function(){
  				   console.log("ajax 실패");
  			   }
  		   });
  		   if(check) event.preventDefault();
      }
      
      // 입찰하기
  		$("#goAuction").click(function (){
            var biddingPrice = $("#biddingPrice").val();
            if( biddingPrice == "" || biddingPrice.length == 0){
                alert("금액을 입력하세요.");
                $("#biddingPrice").focus();
                return false;
            }else if("<%=loginMember%>"=="null"){
            	alert("로그인이 필요합니다.");
        		location.href="<%=request.getContextPath() %>/member/loginForm";
        		return false;
            }else{
            	var productNo = <%=product.getProductNo()%>;
            	var check = true;
            	$.ajax({
        			  url : "biddingProduct",
        			  type : "post",
        			  data: {productNo:productNo,biddingPrice:biddingPrice},
        			  success : function(result){
        				  if(result==0){
        					  alert("경매 입찰 신청 실패");
        				  }else if(result>0){
        					  alert("입찰 신청되었습니다.");
        					  $("#biddingPrice").val("");
        					  $("#details-price").text(biddingPrice);
        					  $("#modalCurrentPrice").text(biddingPrice);
        					  $("#currentPrice").val(biddingPrice);
        					  $("#checkPrice").text("");
        					  check = false;
        				  }
        			  },
        			  error : function(){
        				  alert("ajax 통신 실패");
        			  }
        		});
            	if(check) event.preventDefault();
            }
 		});
  		
     
   </script>
</body>
</html>