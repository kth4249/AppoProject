<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.admin.model.vo.ProductOrder"%>
<%@page import="com.kh.appoproject.admin.model.vo.Member"%>
<%@page import="com.kh.appoproject.admin.model.vo.ProductItemDevice"%>
<%@page import="com.kh.appoproject.admin.model.vo.Image"%>
<%@page import="com.kh.appoproject.admin.model.vo.Destination"%>
<%
	Member buyMember = (Member)request.getAttribute("buyMember");
	Member sellMember = (Member)request.getAttribute("sellMember");
	ProductOrder orderInfo = (ProductOrder)request.getAttribute("orderInfo");
	ProductItemDevice deviceInfo = (ProductItemDevice)request.getAttribute("deviceInfo");
	int penalty = (int)request.getAttribute("penalty");
	Destination destination = (Destination)request.getAttribute("destination");
	String imagePath = (String)request.getAttribute("imagePath");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재 상세정보</title>
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
                            <h3>결재상세보기</h3><hr>
                        </div> 
                    </div>
                    <form action="adminPaymentCheck?orderNo=<%=orderInfo.getOrderNo() %>" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <table class="table">
                                <thead class="thead-light">
                                  <tr>
                                    <th scope="col">주문번호</th>
                                    <th scope="col">상품종류</th>
                                    <th scope="col">상품명</th>
                                    <th scope="col">상품사진</th>
                                    <th scope="col">거래금액</th>
                                    <th scope="col">구매일</th>
                                    
                                  </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <td><%=orderInfo.getOrderNo() %></td>
                                    <td><%=deviceInfo.getDeviceName() %></td>
                                    <td><%=deviceInfo.getItemName() %></td>
                                    <td>
                                    	<%  if(imagePath != null) {
                                    		imagePath = request.getContextPath() + "/resources/uploadImages/" + imagePath;	
                                    	} else {
                                    		imagePath = request.getContextPath() + "/resources/uploadImages/logoImg.png";
                                    	}%>
                                        <img src= "<%= imagePath %>" alt="이미지" width="100" height="50">
                                    </td>
                                    <td><%=orderInfo.getPrice() %></td>
                                    <td><%=orderInfo.getOrderDate() %></td>
                                    
                                  </tr>
                                </tbody>
                              </table>
                            <hr>
                        </div>
                    </div>
                    <br>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <div class="form-group col-md-12"><h4>결재정보</h4></div>
                            <table class="table table-bordered text-center">
                                <col style="background-color: #dee2e6;">
                                  <tr>
                                    <th scope="row">결재방식</th>
                                    <% if(orderInfo.getOrderMethod() == 'C') {%>
                                    	<td>카드결재</td>
                                    <%} else {%>
                                    	<td>무통장입금</td>
                                    <% } %>
                                  </tr>
                                  <tr>
                                    <th scope="row">결재일자</th>
                                    <td><%= orderInfo.getOrderDate() %></td>
                                  </tr>
                                  <tr>
                                    <th scope="row">배송지</th>
                                    <td><%= destination.getDestinationAddr() %></td>
                                  </tr>
                                  <tr>
                                  	<th scope="row">수령인 연락처</th>
                                  	<td><%= destination.getDestinationContact() %></td>
                                  </tr>
                                  <tr>
                                  	<th scope="row">배송메모</th>
                                  	<% if(destination.getDestinationNote() == null) {%>
                                  		<td>배송메모는 없습니다.</td>
                                  	<% } else {%>
                                  		<td><%=destination.getDestinationNote() %></td>
                                  	<% } %>
                                  	
                                  </tr>
                                  <tr>
                                  	<th scope="row">수령인</th>
                                  	<td><%= destination.getDestinationName() %></td>
                                  </tr>
                                  <tr>
                                    <th scope="row">검수상태</th>
                                    <td>
                                        <% if(orderInfo.getorderCheck() == 'N') { %>
                                    		<select class="form-control" name="paymentCheck">
                                            	<option selected value="N">검수미완료</option>
                                            	<option value="Y">검수완료</option>
                                        	</select>
                                    	<% } else { %>
                                    		<select class="form-control" name="paymentCheck">
                                            	<option value="N">검수미완료</option>
                                            	<option selected value="Y">검수완료</option>
                                        	</select>
                                    	<% } %> 
                                    </td>
                                  </tr>
                                  <tr>
                                    <th scope="row">구매확정여부</th>
                                    <td>
                                        <% if(orderInfo.getOrderConfirm() == 'N') { %>
                                    		<input type="text" class="form-control-plaintext text-center" readonly value="비구매확정">
                                    	<% } else { %>
                                    		<input type="text" class="form-control-plaintext text-center" readonly value="구매확정">
                                    	<% } %>	
                                    </td>
                                  </tr>
                            </table>
                            <hr>
                        </div>
                    </div>
                    <!--  -->
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <div class="form-row">
                                <div class="form-group col-md-12"><h4>판매자</h4></div>
                                <div class="form-group col-md-12">
                                    <table class="table table-bordered text-center">
                                        <col style="background-color: #dee2e6;">
                                          <tr>
                                            <th scope="row">이름</th>
                                            <td><%=sellMember.getMemberNM() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">아이디</th>
                                            <td><%=sellMember.getMemberId() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">이메일</th>
                                            <td><%=sellMember.getMemberEmail() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">핸드폰번호</th>
                                            <td><%=sellMember.getMemberPhone() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">주소</th>
                                            <td><%=sellMember.getMemberAddr() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">패널티</th>
                                            <td><%=penalty %></td>
                                          </tr>
                                      </table>
                                      <hr>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <div class="form-row">
                                <div class="form-group col-md-12"><h4>구매자</h4></div>
                                <div class="form-group col-md-12">
                                    <table class="table table-bordered text-center">
                                        <col style="background-color: #dee2e6;">
                                          <tr>
                                            <th scope="row">이름</th>
                                            <td><%=buyMember.getMemberNM() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">아이디</th>
                                            <td><%=buyMember.getMemberId() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">이메일</th>
                                            <td><%=buyMember.getMemberEmail() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">핸드폰번호</th>
                                            <td><%=buyMember.getMemberPhone() %></td>
                                          </tr>
                                          <tr>
                                            <th scope="row">주소</th>
                                            <td><%= buyMember.getMemberAddr()%></td>
                                          </tr>
                                      </table>
                                      <hr>
                                </div>
                                <div class="form-group col-md-9"></div>
                                <div class="form-group col-md-3">
                                    <button type="submit" class="btn btn-dark btn-sm">저장하기</button>
                                    <a class="btn btn-dark btn-sm" href="adminPayment">나가기</a>
                                </div>
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