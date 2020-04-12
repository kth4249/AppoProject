<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.kh.appoproject.product.model.vo.Auction, com.kh.appoproject.product.model.vo.PageInfo" %>    
<%

	List<Auction> aList = (List<Auction>)request.getAttribute("aList");
	
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
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="//code.jquery.com/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
    <script type="text/JavaScript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>마이페이지_경매등록</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/myPage_RegisterAuction.css">
</head>
<body>

	<%@include file = "../common/header.jsp" %>
	<%@include file = "../common/nav.jsp" %>
	
	<div class="container wrap" style="margin-left: 28.5%;">

        <div class="row">
            <div class="col-md-12" id="head1">
                sale List <br>
                등록내역조회
            <div class="row">

                 <hr>

            <div class="col-md-12" id="head2">
                - 일반판매나 경매판매 등록 정보를 확인하실 수 있습니다.<br>
                - 주문번호를 클릭하시면 해당 판매에 대한 상세내역을 보실 수 있습니다.
            </div>
         </div>

         <br><br><br><br>

        <div class="row">
            <div class="col-md-12">
                   <table>
                       <tr>
                           <th><button id="button-1" onclick="location.href='<%= request.getContextPath() %>/product/registerList'">일반판매</button></th>
                           <th><button id="button-2">경매판매</button></th>
                           <th colspan="5"></th>
                       </tr>
                       <tr class="middle">
                           <td id="register">등록일자<br>(등록번호)</td>
                           <td colspan="2" id="information">상품정보</td>
                           <td id="amount">수량</td>
                           <td id="sale">경매 시작 금액</td>
                           <td id="condition">즉시입찰가</td>
                           <td id="countDown">남은 시간</td>
                       </tr>
                       <% if(aList.isEmpty()) {%>
                       	<tr>
                       		<td colspan="5">존재하는 게시글이 없습니다.</td>
                       	</tr>
                       <%} else { %>
                       <% for(Auction auction : aList){ %>
                       <tr class="bottom">
                            <td id="registerOut"><%= auction.getProductEnrollDate() %><br>(<%= auction.getProductNo() %>)</td>
                            <td colspan="2" id="informationOut"><%= auction.getProductTitle() %></td>
                            <td id="amountOut"><%= auction.getProductCount() %></td>
                            <td id="saleOut"><%= auction.getAuctionReservePrice() %></td>
                            <td id="conditionOut"><%= auction.getAuctionImmediateBid() %></td>
                            <td id="countDownOut"><%= auction.getAuctionDeadline() %></td>
                     </tr>
		              <% } %>
                     <% } %> 
                   </table>
            </div>
        </div>

        <!-- <div class="row">
            <div class="col-md-2">
            </div> -->
            
            
            <div class="col-md-12" id="preNext" style="clear: both;">
            	<ul class="pagination">
	            	<% if(currentPage > 0) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/product/registerAuction?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/product/registerAuction?currentPage=<%= currentPage-1 %>">&lt;</a>
	                </li>
	                <% } %>
	                
	                <!-- 10개의 페이지 목록 -->
	                <% for(int p = startPage; p <= endPage; p++){ %>
	                	<% if(p == currentPage) { %>
		                <li>
		                    <a class="page-link"><%= p %></a>
		                </li>
	                	<% } else{ %>
                		<li>
	                    	<a class="page-link" href="<%= request.getContextPath() %>/product/registerAuction?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/product/registerAuction?currentPage=<%= currentPage %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/product/registerAuction?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
	                
	            </ul>
                <%-- <a href="#" >
                    <img src="<%= request.getContextPath() %>/resources/appoimg/back.png" class="preNextImg">
                </a>
                &nbsp;&nbsp;
                <a id="check1" href="#" role="button" class="btn btn-link btn-mini check float-center" >1</a>
                <a id="check2" href="#" role="button" class="btn btn-link btn-mini check float-center" >2</a>
                <a id="check3" href="#" role="button" class="btn btn-link btn-mini check float-center" >3</a>
                &nbsp;&nbsp;
                <a href="#">
                    <img src="<%= request.getContextPath() %>/resources/appoimg/next.png" class="preNextImg">
                </a> --%>
            </div>
            
            
            <!-- <div class="col-md-4">
          	</div>

    	</div> -->
    
    <%@include file = "../common/footer.jsp" %>
    
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>