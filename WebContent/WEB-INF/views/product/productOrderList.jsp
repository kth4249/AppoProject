<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List, com.kh.appoproject.product.model.vo.Order, com.kh.appoproject.product.model.vo.PageInfo" %>    
<%

	List<Order> oList = (List<Order>)request.getAttribute("oList");
	
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");

	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지_구매내역</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/myPage_OrderList.css">
</head>
<body>

	<%@include file = "../common/header.jsp" %>
	<%@include file = "../common/nav.jsp" %>
	
	 <div class="container wrap" style="margin-left: 28%; margin-top: 2%;">

        <div class="row">
            <div class="col-md-12" id="head1">
                older List <br>
                구매내역조회
            <div class="row">

                 <hr>

            <div class="col-md-12" id="head2">
                - 일반판매나 경매판매 등록 정보를 확인하실 수 있습니다.<br>
                - 주문번호를 클릭하시면 해당 주문에 대한 상세내역을 보실 수 있습니다.
            </div>
         </div>

         <br>

        <div class="row">
            <div class="col-md-12">
                   <table>
                       <tr class="middle">
                           <td id="register">주문일자<br>(주문번호)</td>
                           <td colspan="2" id="information">상품정보</td>
                           <td id="amount">수량</td>
                           <td id="sale">상품 구매 금액</td>
                           <td id="condition">주문 처리 상태</td>
                       </tr>
                       <% if(oList.isEmpty()) {%>
                       	<tr>
                       		<td colspan="5">존재하는 게시글이 없습니다.</td>
                       	</tr>
                       <%} else { %>
                       <% for(Order order : oList){ %>
                       <tr class="bottom" onclick="location.href='#'" style="cursor: pointer;">
                            <td id="registerOut"><%= order.getOrderDate() %><br><%= order.getOrderNo() %></td>
                            <td colspan="2" id="informationOut"><%= order.getProductTitle() %></td>
                            <td id="amountOut">1</td>
                            <td id="saleOut"><%= order.getBasicPrice() %></td>
                            <td id="conditionOut"><%= order.getOrderState() %></td>
                     </tr>
                      <% } %>
                     <% } %>                  
                   </table>
            </div>
        </div>

        <br>

        <div class="row" style="margin-bottom: 10%;">
            <div class="col-md-2">
            </div>
            <div class="col-md-6" id="preNext">
                <a href="#" >
                    <img src="<%=request.getContextPath() %>/resources/appoimg/back.png" class="preNextImg">
                </a>
                &nbsp;&nbsp;
                <a id="check1" href="#" role="button" class="btn btn-link btn-mini check float-center" >1</a>
                <a id="check2" href="#" role="button" class="btn btn-link btn-mini check float-center" >2</a>
                <a id="check3" href="#" role="button" class="btn btn-link btn-mini check float-center" >3</a>
                &nbsp;&nbsp;
                <a href="#">
                    <img src="<%=request.getContextPath() %>/resources/appoimg/next.png" class="preNextImg">
                </a>
            </div>
            <div class="col-md-4">
            </div>

    </div>
    
    
    
    <%@include file = "../common/footer.jsp" %>
</body>
</html>
























