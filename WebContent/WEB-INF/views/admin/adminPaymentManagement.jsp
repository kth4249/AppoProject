<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.admin.model.vo.ProductOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.appoproject.admin.model.vo.PageInfo"%>
<%

	List<ProductOrder> pList = (List<ProductOrder>)request.getAttribute("pList"); 
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	int addPayment = (int)request.getAttribute("addPayment");
	
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
	
	int pageFlag = (int)request.getAttribute("pageFlag");
	
	String searchKey = request.getParameter("searchKey");
	String searchContent = request.getParameter("searchContent");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재 관리 페이지</title>
</head>
<body>
	 <div class="container-fluid">
	 	<%@ include file="adminHeader.jsp"%>
	 	<div class="row">
	 		<%@ include file="adminSidebar.jsp"%>
	 		<div class="col-md-10">
	 			<!-- 컨텐트 내용을 추가 시작 -->
	 			<div class="row">
                        <div class="col-md-12">
                            <h3>결재관리</h3><hr>
                        </div> 
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-8">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <a class="btn btn-secondary btn-lg" href="adminPayment" role="button">전체조회</a>
                                <a class="btn btn-secondary btn-lg" href="adminAddPayment" role="button">추가거래</a>
                                <a class="btn btn-secondary btn-lg" href="adminNoCheck" role="button">검수미완료</a>
                                <a class="btn btn-secondary btn-lg" href="adminConfirm" role="button">구매확정</a>
                            </div>
                        </div>
                        <div class="form-group col-md-4">
                        </div> 
                    </div>
                    <br>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <table class="table text-center">
                                <thead class="thead-light">
                                  <tr>
                                    <th scope="col">주문번호</th>
                                    <th scope="col">구매일</th>
                                    <th scope="col">판매자 아이디</th>
                                    <th scope="col">구매자 아이디</th>
                                    <th scope="col">거래금액</th>
                                    <th scope="col">검수상태</th>
                                    <th scope="col">구매확정</th>
                                    <th scope="col">거래상세보기</th>
                                    <th scope="col">결재</th>
                                  </tr>
                                </thead>
                                <tbody>
                                <% if(pList.isEmpty()) {%>
                                	<tr>
	                					<td colspan="9">존재하는 거래가 없습니다.</td>
	                				</tr>
                                <% } else { %>
                                	<% for(ProductOrder productOrder : pList) { %>
                                	<tr>
                                    	<td><%= productOrder.getOrderNo() %></td>
                                    	<td><%= productOrder.getOrderDate() %></td>
                                    	<td><%= productOrder.getSellerId() %></td>
                                    	<td><%= productOrder.getBuyerId() %></td>
                                    	<td><%= productOrder.getPrice() %></td>
                                    	<td>
                                    		<% if(productOrder.getorderCheck() == 'N') { %>
                                    		<select class="form-control-sm" name="changeCheck">
                                            	<option selected value="N">검수미완료</option>
                                            	<option value="Y">검수완료</option>
                                        	</select>
                                    		<% } else { %>
                                    		<select class="form-control-sm" name="changeCheck">
                                            	<option value="N">검수미완료</option>
                                            	<option selected value="Y">검수완료</option>
                                        	</select>
                                    		<% } %>
                                    	</td>
                                    	<td>
                                    		<% if(productOrder.getOrderConfirm() == 'N') { %>
                                    			<input type="text" class="form-control-plaintext text-center" readonly value="비구매확정">
                                    			<input type="hidden" value="N" name="orderConfirm">
                                    		<% } else { %>
                                    			<input type="text" class="form-control-plaintext text-center" readonly value="구매확정">
                                    			<input type="hidden" value="Y" name="orderConfirm">
                                    		<% } %>	
                                    	</td>
                                    	<td><button type="button" class="btn btn-dark goPaymentInfo">상세보기</button>
                                    		<input type="hidden" class="productCode" value="<%=productOrder.getProductCode() %>">
                                    	</td>
                                    	<td><button type="button" class="btn btn-dark orderState">송금하기</button></td>
                                    </tr>
                                	<% } %>
                                <% } %>
                                </tbody>
                              </table>
                        </div>
                    </div>
                    
                    
                    <!-- 페이징 -->
                          <div class="text-center">
                            <ul class="pagination justify-content-center">
                            <!-- 맨처음으로 -->
                            <% if(currentPage > 1) { %>
                              <li class="page-item">
                              	<% if(pageFlag == 0) {%>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminPayment?currentPage=1">&laquo;</a>
                              	<%} else if(pageFlag == 1) {%>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminAddPayment?currentPage=1">&laquo;</a>
                              	<%}else if(pageFlag == 2){ %>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminNoCheck?currentPage=1">&laquo;</a>
                              	<%} else if(pageFlag == 3) {%>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminConfirm?currentPage=1">&laquo;</a>
                              	<%} else{ %>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/searchPayment?currentPage=1&searchKey=<%=searchKey%>&searchContent=<%=searchContent%>">&laquo;</a>
                              	<%} %>
                              	
                              </li>
                              <li>
	                		<!-- 이전으로(<) -->
	                			<% if(pageFlag == 0) {%>
	                				<a class="page-link" href="<%= request.getContextPath() %>/admin/adminPayment?currentPage=<%= currentPage-1 %>">&lt;</a>
	                			<%} else if(pageFlag == 1) {%>
	                				<a class="page-link" href="<%= request.getContextPath() %>/admin/adminAddPayment?currentPage=<%= currentPage-1 %>">&lt;</a>
	                			<%}else if(pageFlag == 2){ %>
	                				<a class="page-link" href="<%= request.getContextPath() %>/admin/adminNoCheck?currentPage=<%= currentPage-1 %>">&lt;</a>
	                			<%} else if(pageFlag == 3) {%>
	                				<a class="page-link" href="<%= request.getContextPath() %>/admin/adminConfirm?currentPage=<%= currentPage-1 %>">&lt;</a>
	                			<%} else{ %>
	                				<a class="page-link" href="<%= request.getContextPath() %>/admin/searchPayment?currentPage=<%= currentPage-1 %>&searchKey=<%=searchKey%>&searchContent=<%=searchContent%>">&lt;</a>
	                			<%} %>
	                			
	               			 </li>
                             <% } %>
                             <!-- 5개 페이지 목록 -->
                             <% for(int p = startPage; p <= endPage; p++){ %>
                             	<% if(p == currentPage) { %>
                             		<li>
		                    			<a class="page-link"><%= p %></a>
		                			</li>
		                		<% } else{ %>
		                			<li>
		                				<% if(pageFlag == 0) {%>
		                					<a class="page-link" href="<%= request.getContextPath() %>/admin/adminPayment?currentPage=<%= p %>"><%= p %></a>
		                				<%} else if(pageFlag == 1) {%>
		                					<a class="page-link" href="<%= request.getContextPath() %>/admin/adminAddPayment?currentPage=<%= p %>"><%= p %></a>
		                				<%}else if(pageFlag == 2){ %>
		                					<a class="page-link" href="<%= request.getContextPath() %>/admin/adminNoCheck?currentPage=<%= p %>"><%= p %></a>
		                				<%} else if(pageFlag == 3) {%>
		                					<a class="page-link" href="<%= request.getContextPath() %>/admin/adminConfirm?currentPage=<%= p %>"><%= p %></a>
		                				<%} else{ %>
		                					<a class="page-link" href="<%= request.getContextPath() %>/admin/searchPayment?currentPage=<%= p %>&searchKey=<%=searchKey%>&searchContent=<%=searchContent%>"><%= p %></a>
		                				<%} %>
		                				
	                				</li>
                             	<% } %>
                             <% } %>
                             <% if(currentPage < maxPage){ %>
								<!-- 다음 페이지로(>) -->
                              	<li>
                              		<% if(pageFlag == 0) {%>
                              			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminPayment?currentPage=<%= currentPage+1 %>">&gt;</a>
                              		<%} else if(pageFlag == 1) {%>
                              			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminAddPayment?currentPage=<%= currentPage+1 %>">&gt;</a>
                              		<%}else if(pageFlag == 2){ %>
                              			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminNoCheck?currentPage=<%= currentPage+1 %>">&gt;</a>
                              		<%} else if(pageFlag == 3) {%>
                              			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminConfirm?currentPage=<%= currentPage+1 %>">&gt;</a>
                              		<%} else{ %>
                              			<a class="page-link" href="<%= request.getContextPath() %>/admin/searchPayment?currentPage=<%= currentPage+1 %>&searchKey=<%=searchKey%>&searchContent=<%=searchContent%>">&gt;</a>
                              		<%} %>
	                			</li>                             
                               <!-- 맨뒤로 -->
                             	<li class="page-item">
                             		<% if(pageFlag == 0) {%>
                             			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminPayment?currentPage=<%= maxPage %>">&raquo;</a>
                             		<%} else if(pageFlag == 1) {%>
                             			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminAddPayment?currentPage=<%= maxPage %>">&raquo;</a>
                             		<%}else if(pageFlag == 2){ %>
                             			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminNoCheck?currentPage=<%= maxPage %>">&raquo;</a>
                             		<%} else if(pageFlag == 3) {%>
                             			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminConfirm?currentPage=<%= maxPage %>">&raquo;</a>
                             		<%} else{ %>
                             			<a class="page-link" href="<%= request.getContextPath() %>/admin/searchPayment?currentPage=<%= maxPage %>&searchKey=<%=searchKey%>&searchContent=<%=searchContent%>">&raquo;</a>
                             		<%} %>
                              	</li>
                             <% } %>                           
                            </ul>
                        </div>
                    
                    
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <div class="form-group row">
                                <label for="memberJoinDate" class="col-sm-4 col-form-label">추가된 거래 내역수</label>
                                <div class="col-sm-6">
                                    <input type="text" readonly class="form-control" id="addPayment" value="<%= addPayment%>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <form action="searchPayment" method="post" onsubmit="return validate();">
                    <div class="form-row">
                        <div class="form-group col-md-9">
                            <div class="input-group-text">
                                <input class="form-control" type="radio" name="searchKey" value="BUYERID" style="width:20px;height:20px;" checked>
                                <label class="col-sm-2" style="font-size: large">구매자 아이디</label>
                                <input class="form-control"type="radio" name="searchKey" value="SELLERID" style="width:20px;height:20px;">
                                <label class="col-sm-2" style="font-size: large">판매자 아이디</label>
                                <input class="form-control mr-sm-2 col-sm-6" type="text" placeholder="Search" name="searchContent" id="paymentSearchText">
                                <button class="btn btn-primary btn-lg" type="submit">검색</button>
                            </div>
                        </div>
                    </div>
                	</form>
	 			<!-- 컨텐트 내용을 추가 끝 -->
	 		</div>	
	 	</div>
	 </div>
	 <script>
	 	// 검색창 유효성 검사
	 	function validate() {
	 		if ($("#paymentSearchText").val().trim().length == 0) {
	             alert("내용을 입력하세요");
	             return false;
	         } 
	         return true;
	 	}
	 	$(function(){
	 		// 검색한 경우 
	 		var searchKey = "<%=searchKey%>";
	 		var searchContent = "<%=searchContent%>";
	 		
	 		if(searchKey != "null" && searchContent != "null") {
	 			$.each($("input[name=searchKey]"),function(index,item){
	 				if( $(item).val() == searchKey ) {
	 					$(item).prop("checked","true");
	 				}
	 			});
	 			$("input[name=searchContent]").val(searchContent);
	 		}
	 		
	 		// 상세조회 버튼 클릭시
	 		$(".goPaymentInfo").click(function(){
	 			 var productNo = $(this).next().val();
	        	 // 상품 번호를 얻는다(게시글 번호)
	        	 var buyerId = $(this).parent().parent().children().eq(3).html();
	        	 // 구매회원 아이디를 얻는다
	        	 var sellerId = $(this).parent().parent().children().eq(2).html();
	        	 // 판매회원 아이디를 얻는다.
	        	 
	        	 location.href = "adminPaymentInfo?productNo="+productNo+"&buyerId="+buyerId+"&sellerId="+sellerId;
	 		});
	 		
	 		// 검수 변경시
	 		$("select[name=changeCheck]").change(function(){
	 			var paymentCheck = $(this).val();
	 			var orderNo = $(this).parent().parent().children().eq(0).html();
	 			location.href = "adminPaymentCheck?orderNo="+orderNo+"&paymentCheck="+paymentCheck;
	 		});
	 		
	 		// 송금하기 유효성 검사
	 		$(".orderState").click(function(){
	 			var orderCheckValue = $(this).parent().parent().find("select[name=changeCheck]").val();
	 			var orderConfirmValue = $(this).parent().parent().find("input[name=orderConfirm]").val();
	 			
	 			if(orderCheckValue == 'Y' && orderConfirmValue == 'Y') {
	 				var orderNo = $(this).parent().parent().children().eq(0).html();	 	
	 				location.href="paymentFinish?orderNo="+orderNo;
	 			} else if(orderCheckValue == 'N') {
	 				alert("검수가 완료되지 않아서 결재완료를 할 수 없습니다.");
	 			} else if(orderConfirmValue == 'N') {
	 				alert("구매확정이 되지 않아서 결재완료를 할 수 없습니다.");
	 			}
	 			
	 		});
	 		
	 		
	 	});
	 </script>
	 <%@ include file="adminFooter.jsp"%>
</body>
</html>