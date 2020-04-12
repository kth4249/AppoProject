<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List, com.kh.appoproject.product.model.vo.Product, com.kh.appoproject.product.model.vo.PageInfo" %>    
<%

	List<Product> pList = (List<Product>)request.getAttribute("pList");
	
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
<title>마이페이지-일반등록</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myPage_RegisterBasic.css" type="text/css">
</head>

<body>

	<%@ include file = "../common/header.jsp" %>	
	<%@ include file = "../common/nav.jsp" %>	

	 <div class="container wrap" style="margin-left: 30%; margin-bottom: 0%;">

        <div class="row" id="headWrap">
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
                           <th><button id="button-1">일반판매</button></th>
                           <th><button id="button-2" onclick="location.href='<%= request.getContextPath()%>/product/registerAuction'">경매판매</button></th>
                           <th colspan="4"></th>
                       </tr>
                       <tr class="middle">
                           <td id="register">등록일자<br>(등록번호)</td>
                           <td colspan="2" id="information">상품정보</td>
                           <td id="amount">수량</td>
                           <td id="sale">상품판매금액</td>
                           <td id="condition">판매처리 상태</td>
                       </tr>
                       <% if(pList.isEmpty()) {%>
                       	<tr>
                       		<td colspan="5">존재하는 게시글이 없습니다.</td>
                       	</tr>
                       <%} else { %>
                       <% for(Product product : pList){ %>
                       <tr class="bottom" onclick="location.href='#'" style="cursor: pointer;">
                        <td id="registerOut"><%= product.getProductEnrollDate() %><br>(<%= product.getProductNo() %>)</td>
                        <td colspan="2" id="informationOut"><%= product.getProductTitle() %></td>
                        <td id="amountOut">1</td>
                        <td id="saleOut"><%= product.getProductCount() %></td>
                        <td id="conditionOut"><%= product.getProductState() %></td>
                       </tr>
                      <% } %>
                     <% } %> 
                   </table>
            </div>
        </div>


        <div class="row" style="margin-bottom: 10%;" >
            <div class="col-md-3 move">
            </div>
            
            <div class="col-md-6" id="preNext" style="clear: both">
                <ul class="pagination">
	            	<% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/product/registerList?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/product/registerList?currentPage=<%= currentPage-1 %>">&lt;</a>
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
	                    	<a class="page-link" href="<%= request.getContextPath() %>/product/registerList?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/product/registerList?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/product/registerList?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
	                
	            </ul>
            </div>
            
            
            <div class="col-md-3">
            </div>
            
       </div>
    
	
	   		
	
	
    <script>
    
        $(".bottom").children().wrap('<a href="#" class="bottom_link"></a>');
    
    </script>
    
    
    
    <%@include file = "../common/footer.jsp" %>
    
</body>
</html>