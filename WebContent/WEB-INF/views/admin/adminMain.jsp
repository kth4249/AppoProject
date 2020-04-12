<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.admin.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.appoproject.admin.model.vo.PageInfo"%>
<%@page import="com.kh.appoproject.admin.model.vo.Product"%>
<%
	List<Member> mList = (List<Member>)request.getAttribute("mList");
	List<Product> pList = (List<Product>)request.getAttribute("pList");
	PageInfo pInfMember = (PageInfo)request.getAttribute("pInfMember"); // 회원 페이징
	PageInfo pInfDeclare = (PageInfo)request.getAttribute("pInfDeclare"); // 신고게시판 페이징
	
	int enrollMember = (int)request.getAttribute("enrollMember");
	int addPayment = (int)request.getAttribute("addPayment");
	int noCheck = (int)request.getAttribute("noCheck");
	int confirmComplete = (int)request.getAttribute("confirmComplete");
	int noAnswer = (int)request.getAttribute("noAnswer");
	int overReport = (int)request.getAttribute("overReport");
	
	// 회원 페이징
	int listCount = pInfMember.getListCount();
	int currentPage = pInfMember.getCurrentPage();
	int maxPage = pInfMember.getMaxPage();
	int startPage = pInfMember.getStartPage();
	int endPage = pInfMember.getEndPage();
	
	// 신고게시판 페이징
	int pListCount = pInfDeclare.getListCount();
	int pCurrentPage = pInfDeclare.getCurrentPage();
	int pMaxPage = pInfDeclare.getMaxPage();
	int pStartPage = pInfDeclare.getStartPage();
	int pEndPage = pInfDeclare.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인페이지</title>
<style>
        .nav {
            background-color: rgb(44, 62, 80);
            height: 100%;
        }
        .nav a {
            color: black;
        }

        .nav a:hover {
            color: white;
        }

        .navbar {
            background-color: rgb(44, 62, 80);
        }

        span {
            color: rgb(149, 165, 166);
        }
    </style>
</head>
<body>
	 <div class="container-fluid">
	 	<%@ include file="adminHeader.jsp"%>
	 	<div class="row">
	 		<%@ include file="adminSidebar.jsp"%>
	 		<div class="col-md-10">
	 			<div class="row">
                    <div class="col-md-6">
                        <h3>회원등록</h3>
                        <hr>
                        <div class="form-group row">
                            <label for="addMember" class="col-sm-3 col-form-label">추가 회원수</label>
                            <div class="col-sm-4">
                                <input type="text" readonly class="form-control" id="addMember" value="<%=enrollMember%>">
                            </div>
                        </div>
                        <!-- 테이블 -->
                        <table class="table">
                            <thead class="thead-light">
                              <tr>
                                <th scope="col">아이디</th>
                                <th scope="col">이름</th>
                                <th scope="col">전화번호</th>
                                <th scope="col">이메일</th>
                              </tr>
                            </thead>
                            <tbody>
                            <% if(mList.isEmpty()) { %>
                            	<tr>
	                				<td colspan="4">존재하는 회원이 없습니다.</td>
	                			</tr>
                            <% } else { %>
                              <% for(Member member : mList) { %>
                              		<tr>
                              			<td><%= member.getMemberId() %></td>
                              			<td><%= member.getMemberNM() %></td>
                              			<td><%= member.getMemberPhone() %></td>
                              			<td><%= member.getMemberEmail() %></td>
                              		</tr>
                              	<% } %>
                              <% } %>
                            </tbody>
                          </table>
                          
                        <!-- 페이징 처리 -->
                        <div class="text-center">
                            <ul class="pagination justify-content-center">
                            <!-- 맨처음으로 -->
                            <% if(currentPage > 1) { %>
                              <li class="page-item">
                              	<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage=1">&laquo;</a>
                              </li>
                              <li>
	                		<!-- 이전으로(<) -->
	                			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage=<%= currentPage-1 %>">&lt;</a>
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
		                				<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage=<%= p %>"><%= p %></a>
	                				</li>
                             	<% } %>
                             <% } %>
                             <% if(currentPage < maxPage){ %>
								<!-- 다음 페이지로(>) -->
                              	<li>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage=<%= currentPage+1 %>">&gt;</a>
	                			</li>                             
                               <!-- 맨뒤로 -->
                             	<li class="page-item">
                             		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage=<%= maxPage %>">&raquo;</a>
                              	</li>
                             <% } %>                           
                            </ul>
                        </div>
                        
                        
                    </div>
                    <div class="col-md-6">
                        <h3>신고게시판</h3>
                        <hr>
                        <div class="form-group row">
                            <label for="addReportlist" class="col-sm-5 col-form-label">3회이상 신고게시글(노블라인드)</label>
                            <div class="col-sm-4">
                                <input type="text" readonly class="form-control" id="addReportlist" value="<%=overReport%>">
                            </div>
                        </div>
                        <!-- 테이블 -->
                        <table class="table">
                            <thead class="thead-light">
                              <tr>
                                <th scope="col">게시글번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                              </tr>
                            </thead>
                            <tbody>
                              <% if(pList.isEmpty()) { %>
                            	<tr>
	                				<td colspan="3">존재하는 회원이 없습니다.</td>
	                			</tr>
                          	<% } else { %>
                              <% for(Product product : pList) { %>
                              		<tr>
                              			<td><%= product.getProductNo() %></td>
                              			<td><%= product.getProductTitle() %></td>
                              			<td><%= product.getMemberId() %></td>
                              		</tr>
                              	<% } %>
                              <% } %>
                            </tbody>
                          </table>
                        <!-- 페이징 시작 -->
                        <div class="text-center">
                            <ul class="pagination justify-content-center">
                            <!-- 맨처음으로 -->
                            <% if(pCurrentPage > 1) { %>
                              <li class="page-item">
                              	<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage1=1">&laquo;</a>
                              </li>
                              <li>
	                		<!-- 이전으로(<) -->
	                			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage1=<%= pCurrentPage-1 %>">&lt;</a>
	               			 </li>
                             <% } %>
                             <!-- 5개 페이지 목록 -->
                             <% for(int p = pStartPage; p <= pEndPage; p++){ %>
                             	<% if(p == pCurrentPage) { %>
                             		<li>
		                    			<a class="page-link"><%= p %></a>
		                			</li>
		                		<% } else{ %>
		                			<li>
		                				<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage1=<%= p %>"><%= p %></a>
	                				</li>
                             	<% } %>
                             <% } %>
                             <% if(pCurrentPage < pMaxPage){ %>
								<!-- 다음 페이지로(>) -->
                              	<li>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage1=<%= pCurrentPage+1 %>">&gt;</a>
	                			</li>                             
                               <!-- 맨뒤로 -->
                             	<li class="page-item">
                             		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMain?currentPage1=<%= pMaxPage %>">&raquo;</a>
                              	</li>
                             <% } %>                           
                            </ul>
                        </div>
                        <!-- 페이징끝 -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-12">
                                <h3>결재관리</h3>
                                <hr>
                                <div class="form-group row">
                                    <label for="addTrans" class="col-sm-2 col-form-label">오늘 추가된 거래수</label>
                                    <div class="col-sm-3">
                                        <input type="text" readonly class="form-control" id="addTrans" value="<%=addPayment%>">
                                    </div>
                                    <label for="noCheck" class="col-sm-2 col-form-label">검수 미완료 거래수</label>
                                    <div class="col-sm-3">
                                        <input type="text" readonly class="form-control" id="noCheck" value="<%=noCheck%>">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="confirm" class="col-sm-2 col-form-label">구매 확정(결재중) 거래수</label>
                                    <div class="col-sm-3">
                                        <input type="text" readonly class="form-control" id="confirm" value="<%=confirmComplete%>">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <h3>고객지원관리</h3>
                                <hr>
                                <div class="form-group row">
                                    <label for="noAnswer" class="col-sm-2 col-form-label">미답변된 1:1 문의 수</label>
                                    <div class="col-sm-3">
                                        <input type="text" readonly class="form-control" id="noAnswer" value="<%=noAnswer%>">
                                    </div>
                                </div>
                            </div>
                        </div>
                  </div>
	 		</div>	
	 	</div>
	 </div>
	 <%@ include file="adminFooter.jsp"%>
	 </div>
</body>
</html>