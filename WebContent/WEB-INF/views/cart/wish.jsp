<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.cart.model.vo.Wish"%>
<%@page import="java.util.List"%>
<%
	List<Wish> wList = (List<Wish>)request.getAttribute("wList");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/productdetail.css"
	type="text/css">
<title>관심상품 조회</title>
</head>
<body>
<%@ include file="../common/header.jsp"%>
        <div class="container pd-100">
            <div class="row">
                <div class="col-sm-12 col-md-12 col-xs-12 col-lg-12">
                    <div class="cart-top-heading">
                        <div class="cart-top-heading">
                            <h1 class="cart-heading">관심상품</h1>
                        </div>
                        <div class="cart-notice py-4">
                            <span class="cart-notice text-muted"><small>가격, 옵션 등 상품정보가 변경된 경우 입찰이 불가할 수 있습니다.</small></span>
                        </div>
                        
                            <div class="table-content table-responsive">
                                <table>
                                    <thead>
                                        <tr class="product-top-tr">
                                            <th><input type="checkbox" id="checkall"></th>
                                            <th colspan="2">상품정보</th>
                                            <th>판매자</th>
                                            <th>현재가격</th>
                                            <th>즉시구매가격</th>
                                            <th>남은기간</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<% if(wList.isEmpty()){ %>
                                    	<tr>
                                    		<td colspan="6">관심상품에 담긴 상품이 없습니다.</td>
                                    	</tr>
                                    	<% } else{%>
                                    		<% for(Wish wish : wList){ %>
	                                        <tr>
	                                            <!-- 체크 -->
	                                            <td class="product-check">
	                                            	<input type="checkbox" name="chk" id="<%= wish.getAuctionNo() %>" value="<%= wish.getAuctionNo() %>">
	                                            </td>
	                                            <!-- 상품이미지 -->
	                                            <td class="product-thumnail">
	                                                <a class="thumbnail pull-left" href="<%= request.getContextPath()%>/product/detail?no=<%= wish.getAuctionNo()%>">
	                                            	<% if (wish.getImagePath()==null){ %> 
	                                            	<img class="media-object" src="<%= request.getContextPath()%>/resources/uploadImages/no.jpg" style="width: 72px; height: 72px;"> 
	                                            	<% } else{ %>
	                                                <img class="media-object" src="<%= request.getContextPath()%>/resources/uploadImages/<%=wish.getImagePath()%>" style="width: 72px; height: 72px;"> 
	                                                <% } %>
	                                            </a>
	                                            </td>
	                                            <!-- 상품정보 -->
	                                            <td class="product-name">
	                                                <span><a href="<%= request.getContextPath()%>/product/detail?no=<%= wish.getAuctionNo()%>"><%= wish.getProductTitle() %></a></span>
	                                                <br>
	                                                <span><%= wish.getDeviceName() %></span>
	                                                <br>
	                                                <span><%= wish.getItemName() %> </span>
	                                            </td>
	                                            <!-- 판매자 -->
	                                            <td class="product-seller"><%= wish.getMemberId() %> </td>
	                                            <!-- 현재가격 -->
	                                            <td class="product-subtotal"><strong><%= wish.getAuctionReservePrice() %></strong></td>
	                                            <!-- 즉시구매가격 -->
	                                            <% if(wish.getAuctionImmediateBid()!=0){ %>
	                                            <td class="product-price-cart"><%= wish.getAuctionImmediateBid() %></td>
	                                            <% } else{%>
	                                            <td>-</td>
	                                            <% } %>
	
	                                            <!-- 남은기간 -->
	                                            <td class="product-period">
		                                            <strong><%= wish.getAuctionDeadline() %></strong>
		                                            <br>
		                                            <button type="button" data-toggle="modal" data-target="#auction-modal" class="btn-outline-danger wish-order mClick" >
		                                          	  입찰하기
		                                            </button>
		                                            <% if(wish.getAuctionImmediateBid()!=0){ %>
		                                            <button type="button" class="btn-outline-primary order-btn">
		                                           	 즉시구매
		                                            </button>
	                                            <% } %>
	                                            </td>
	                                        </tr>
	                                        
	                                        <% } %>
	                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                            
                            
                            
                            
                            <div class="modal" id="auction-modal">
                                <div class="modal-dialog auction-modal mt-25em" role="document">
                                  <div class="modal-content">
                                    <div class="modal-body">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                          </button>
                                        <table class="table">
                                            <tr>
                                                <td class="modal-td">상품번호</td>
                                                <td id="pNo"></td>
                                            </tr>
                                            <tr>
                                                <td class="modal-td">경매마감일</td>
                                                <td id="dL"></td>
                                            </tr>
                                            <tr>
                                                <td class="modal-td">현재가</td>
                                                <td><span class="text-danger" id="modalCurrentPrice"></span>원</td>
                                            </tr>
                                            <tr>
                                                <td class="modal-td">즉시구매가</td>
                                                <td><span id="iP"></span>원</td>
                                            </tr>
                                            <tr>
                                                <td class="modal-td">입찰금액</td>
                                                <td>
                                                    <input value="" class="text-danger" id="currentPrice" style = "text-align:right;" readonly></input><label>원 보다 큰 금액만 입찰할 수 있습니다.</label>
                                                    <br>
                                                    <input type="number" id="biddingPrice" style = "text-align:right;">원 (천단위 미만 입력시 올림처리)
                                                    <p id="checkPrice"></p>
                                                </td>
                                            </tr>
                                        </table>
                                        <div class="modal-bt">
                                            <button type="button" class="btn btn-danger" id="goAuction">입찰하기</button>
                                        </div>
                                    </div>
                                  </div>
                                </div>
                            </div>
                            
                            
                            <div class="row">
                                <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
                                    <div class="delete-all mt-4">
                                        <div class="delete" id="deleteArea">
                                            <button class="btn btn-outline-dark" name="delete-cart" id="deleteWish">선택상품 삭제</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function(){
                $("#biddingPrice").on("input",function(){
                    if( parseInt($("#biddingPrice").val()) <= parseInt($("#currentPrice").val())){
                        $("#checkPrice").text("현재 입찰금액보다 큰 금액을 입력해주세요.").css("color","red");
                        return false;
                    }else{
                        $("#checkPrice").text("참여 가능한 금액입니다.").css("color","green");
                        return true;
                    }
                });
                
            	
                $("#checkall").click(function(){
                    var check = $("#checkall").prop("checked");
                    $("input[name=chk]").prop("checked", check);
                });
                
                // 선택한 체크 박스 삭제
                $("#deleteWish").on("click", function(){
                	
                	var check = $('input[name=chk]:checked');
                	
                	if(check.length == 0){
                		alert("선택한 상품이 없습니다."); 
                		return false;
                	}else{
                	
        	       		var checkArr = [];
        	        		
        	       		$.each(check,function(index,item){
        	        		checkArr.push($(item).prop("id"));
        	        	});
        	        		
        	       		console.log(checkArr.join());
        	       		
        	       		var $form = $("<form>").prop("action","deleteWish").prop("method","post");
        	       		var $input = $("<input>").prop("name","checkArr").val(checkArr.join());
        	       		$form.append($input);
        	       		
        	       		$("#deleteArea").append($form);
        	       		$form.hide();
        	       		
        	       		$form.submit();
                	}
                });
                
                
                // 모달에 값 가져오기
                $(".mClick").on("click",function(){
                	var pNo = $(this).parent().parent().children().children("input").val();
                	var dL = $(this).siblings().first().html();
                	var cP = $(this).parent().parent().children().eq(4).text();
                	var iP = $(this).parent().parent().children().eq(5).text();
                	
                	$("#pNo").text(pNo);
                	$("#dL").text(dL);
                	$("#modalCurrentPrice").text(cP);
                	$("#iP").text(iP);
                	$("#currentPrice").val(cP);
                	
                });
                
                // 천 단위 아래 내림
                $('#biddingPrice').on('change', function() {
                    var n = $(this).val(); 
                    n = Math.ceil(n/1000) * 1000; 
                    $(this).val(n);
                });
                
                // 입찰하기
                $("#goAuction").click(function(){
                	var biddingPrice = $("#biddingPrice").val();
                	
                	if(biddingPrice==""||biddingPrice.length == 0){
                		alert("금액을 입력하세요.");
                		$("#biddingPrice").focus();
                		return false;
                	}else if("<%=loginMember%>"=="null"){
                		alert("로그인이 필요합니다.");
                		location.href="<%=request.getContextPath() %>/member/loginForm";
                		return false;
                	}else{
                		var productNo = $("#pNo").text();
                		location.href="<%=request.getContextPath() %>/product/bProduct?productNo="+productNo+"&biddingPrice="+biddingPrice;
                	}
                });
                
            });
        </script>
</body>
</html>