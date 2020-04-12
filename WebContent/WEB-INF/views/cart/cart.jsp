<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>   
<%@page import="com.kh.appoproject.cart.model.vo.Cart"%>
<%
	List<Cart> cList = (List<Cart>)request.getAttribute("cList");

	int fee = 3000;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 조회</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/productdetail.css"
	type="text/css">
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>
    <div class="container pd-100">
        <div class="row">
            <div class="col-sm-12 col-md-12 col-xs-12 col-lg-12">
                <div class="cart-top-heading">
                    <div class="order2 col-lg-6 col-md-6 py-5 cart-top-heading">
                        <h1 class="cart-heading">장바구니</h1>
                    </div>
    
                    <div class="order3 col-lg-6 col-md-6 py-5">
                        <div class="order col-md-2">
                            <span class="num1"><strong>01</strong></span>
                            <span class="numtitle1 "><strong>&nbsp;장바구니</strong></span>
                        </div>
    
                        <div class="order col-md-2">
                            <span class="text-muted"> > </span>
                            <span class="num2 text-muted">02</span>
                            <span class="numtitle2 text-muted">&nbsp;주문/결제</span>
                        </div>
    
                        <div class="order col-md-2">
                            <span class="text-muted">></span>
                            <span class="num3 text-muted">03</span>
                            <span class="numtitle3 text-muted">&nbsp;주문완료</span>
                        </div>
                    </div>
                    <div class="cart-notice py-4">
                        <span class="cart-notice text-muted"><small>가격, 옵션 등 상품정보가 변경된 경우 입찰이 불가할 수 있습니다.</small></span>
                    </div>
                    <%-- <form method="GET" action="<%=request.getContextPath()%>/payment/BillingInfo"> --%>
                        <div class="table-content table-responsive">
                            <table>
                                <thead>
                                     <tr class="product-top-tr">
                                        <th><input type="checkbox" class="feefee" id="checkall" value="0"></th>
                                        <th colspan="2">상품정보</th>
                                        <th>판매자</th>
                                        <th>배송비</th>
                                        <th>가격</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<% if(cList.isEmpty()) {%>
                                	<tr>
										<td colspan="6">장바구니에 담긴 상품이 없습니다.</td>
                                	</tr>
                                	<% } else {%>
	                                	<% for(Cart cart : cList){ %>
	                                    <tr>
	                                        <!-- 체크 -->
	                                        <td class="product-check">
	                                        	<input type="checkbox" class="feefee" name="chk" id="<%= cart.getBasicNo() %>" value="<%= cart.getBasicPrice()%>">
	                                        </td>
	                                        <!-- 상품이미지 -->
	                                        <td class="product-thumnail">
	                                            <a class="thumbnail pull-left" href="<%= request.getContextPath()%>/product/detail?no=<%= cart.getBasicNo()%>">
	                                            	<% if (cart.getImagePath()==null){ %> 
	                                            	<img class="media-object" src="<%= request.getContextPath()%>/resources/uploadImages/no.jpg" style="width: 72px; height: 72px;"> 
	                                            	<% } else{ %>
	                                                <img class="media-object" src="<%= request.getContextPath()%>/resources/uploadImages/<%=cart.getImagePath()%>" style="width: 72px; height: 72px;"> 
	                                                <% } %>
	                                            </a>
	                                        </td>
	                                        <!-- 상품정보 -->
	                                        <td class="product-name">
	                                        	
	                                            <span><a href="<%= request.getContextPath()%>/product/detail?no=<%= cart.getBasicNo()%>"><%= cart.getProductTitle() %></a></span>
	                                            <br>
	                                            <span><%= cart.getDeviceName() %></span>
	                                            <br>
	                                            <span><%= cart.getItemName() %></span>
	                                            <br>
	                                        </td>
	                                        <!-- 판매자 -->
	                                        <td class="product-seller"><%= cart.getMemberId() %> </td>
	                                        <!-- 배송비 -->
	                                        <td class="product-price-cart"><%= fee %></td>
	                                        <!-- 즉시구매가격 -->
	                                        <td class="product-subtotal"><strong><%= cart.getBasicPrice() %></strong></td>
	                                    </tr>
	                                    
	                                    <!-- 장바구니 상품금액 합 -->
                                   		<% } %>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
                                <div class="delete-all mt-4">
                                    <div class="delete" id="deleteArea">
                                        <button class="button" name="delete-cart" id="deleteCart">선택상품 삭제</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-5 col-md-5 ml-auto">
                                <div class="cart-page-total pt-4">
                                    <h3 class="mb-3">Cart totals</h3>
                                    <ul class="pl-0">
                                        <li>상품금액<span class="float-right" id="sum"></span></li>
                                        <li>배송비<span class="float-right"><%= fee %></span></li>
                                        <li>결제금액<span class="float-right" id="total_sum"></span></li>
                                    </ul>
                                    <button <%-- href="<%=request.getContextPath()%>/payment/addPayment" --%> class="mt-3 px-4 py-2" id="cart-order">주문하기</button>
                                    <a href="<%=request.getContextPath() %>" class="mt-3 px-4 py-2" id="cart-home">메인으로</a>
                                </div>
                            </div>
                        </div>
                        
                </div>
            </div>
        </div>
    </div>

    <script>
    $(document).ready(function(){
        // 최상단 체크박스 클릭
        $("#checkall").click(function(){
            if($("#checkall").prop("checked")){
                //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
                $("input[name=chk]").prop("checked",true);
            }else{//클릭이 안되있으면
                //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
                $("input[name=chk]").prop("checked",false);
            }
        })
        
        // 선택한 체크 박스 삭제
        $("#deleteCart").on("click", function(){
        	
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
	       		
	       		var $form = $("<form>").prop("action","deleteCart").prop("method","post");
	       		var $input = $("<input>").prop("name","checkArr").val(checkArr.join());
	       		$form.append($input);
	       		
	       		$("#deleteArea").append($form);
	       		$form.hide();
	       		
	       		$form.submit();
        	}
        });
        
        $("#cart-order").on("click", function(){
        	
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
	       		
	       		var $form = $("<form>").prop("action","<%=request.getContextPath()%>/payment/BillingInfo").prop("method","post");
	       		var $input = $("<input>").prop("name","chk").val(checkArr.join());
	       		$form.append($input);
	       		
	       		$("#cart-order").append($form);
	       		$form.hide();
	       		
	       		$form.submit();
        	}
        });
    });
    $(".feefee").change(function(){
        var sum = 0;
        var fee = 3000;
        $('.feefee').each(function(){
           if($(this).is(":checked")==true){
              console.log($(this).val());
              sum+=parseInt($(this).val());
           }
        });
        $("#sum").text(sum);
        $("#total_sum").text(sum+fee);
     })
    </script>
</body>
</html>