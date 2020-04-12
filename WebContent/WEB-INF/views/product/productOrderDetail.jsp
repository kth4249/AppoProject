<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List, com.kh.appoproject.product.model.vo.Order" %>    
<%

	List<Order> orderDetail = (List<Order>)request.getAttribute("orderDetail");
	

	

%>         
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="//code.jquery.com/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
    <script type="text/JavaScript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>마이페이지_구매내역상세</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/myPage_OrderDetail.css">
</head>
<body>
	
	<%@include file = "../common/header.jsp" %>
	<%@include file = "../common/nav.jsp" %>
	
	 <div class="container wrap" style="margin-left: 26%;">

        <div class="row">
            <div class="col-md-12" id="head1">
                older List <br>
         </div>

         

        <div class="row">
            <div class="col-md-12">
                   <table id="table1" >

                    <tr>
                        <td colspan="3" rowspan="5" id="img"><img src="<%= request.getContextPath() %>/resources/appoimg/아이폰5.jpg" width="80%" height="70%"></td>
                        <td colspan="5" id="orderDate">주문일자(주문번호) : 19-04-03(1234567)</td>
                    </tr>
                    <% if(orderDetail.isEmpty()) {%>
                       	<tr>
                       		<td colspan="5">존재하는 게시글이 없습니다.</td>
                       	</tr>
                       <%} else { %>
                       <% for(Order order : orderDetail){ %>
                    <tr>
                        <td class="tag-1">제품명</td>
                        <td class="context-1" colspan="3"><%= order.getProductTitle() %></td>
                        <!-- <td class="tag-1">색상</td>
                        <td class="context-1">화이트</td> -->
                    </tr>
                    <tr>
                        <td class="tag-1">용량</td>
                        <td class="context-1">1</td>
                        <td class="tag-1">구매금액</td>
                        <td class="context-1"><%= order.getBasicPrice() %></td>
                    </tr>
                    <tr>
                        <td class="tag-1">구매시기</td>
                        <td class="context-1"><%= order.getOrderDate() %></td>
                        <td class="tag-1">판매자명</td>
                        <td class="context-1"><%= order.getMemberReg() %></td>
                    </tr>
                    <tr>
                        <td class="tag-1">배송상태</td>
                        <td class="context-1"><%= order.getOrderState() %></td>
                        <td colspan="3"></td>
                    </tr>
                      <% } %>
                     <% } %>                     
                   </table>
            </div>
        </div>




        <div class="row">
            <div class="col-md-12" style="margin-bottom: 5%;">
            <a id="checked" href="#" role="button" class="btn btn-primary btn-mini check">확인</a>
            </div>
        </div>

    </div>
    
	
	
	<%@include file = "../common/footer.jsp" %>
	
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>