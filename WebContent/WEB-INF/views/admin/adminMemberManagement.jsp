<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.admin.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.appoproject.admin.model.vo.PageInfo"%>
<%
	List<Member> mList = (List<Member>)request.getAttribute("mList");
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	int enrollMember = (int)request.getAttribute("enrollMember");
	int allMember = (int)request.getAttribute("allMember");
	int searchFlag = (int)request.getAttribute("searchFlag"); // 검색 페이징에 필요, 0=memberlist 1=search 
	
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
	
	String selectKey = request.getParameter("selectKey");
	String selectContent = request.getParameter("selectContent");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 페이지</title>
</head>
<body>
	 <div class="container-fluid">
	 	<%@ include file="adminHeader.jsp"%>
	 	<div class="row">
	 		<%@ include file="adminSidebar.jsp"%>
	 		<div class="col-md-10">
	 			<!-- 컨텐트 내용을 추가 -->
	 			<div class="row">
                    <div class="col-md-12">
                        <h3>회원관리</h3><hr>
                    </div>  
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group row">
                            <label for="addTrans" class="col-sm-1 col-form-label">총 회원수</label>
                            <div class="col-sm-3">
                                <input type="text" readonly class="form-control" id="addTrans" value="<%=allMember%>">
                            </div>
                            <label for="noCheck" class="col-sm-2 col-form-label">오늘 추가 가입인원</label>
                            <div class="col-sm-3">
                                <input type="text" readonly class="form-control" id="noCheck" value="<%=enrollMember%>">
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-8">
                        <table class="table">
                            <thead class="thead-light">
                              <tr>
                                <th scope="col">회원번호</th>
                                <th scope="col">아이디</th>
                                <th scope="col">이름</th>
                                <th scope="col">이메일</th>
                                <th scope="col">setting</th>
                                <th scope="col">패널티</th>
                              </tr>
                            </thead>
                            <tbody>
                                <% if(mList.isEmpty()) { %>
                            	<tr>
	                				<td colspan="6">존재하는 회원이 없습니다.</td>
	                			</tr>
                            <% } else { %>
                              <% for(Member member : mList) { %>
                              		<tr>
                              			<td><%= member.getMemberNo() %></td>
                              			<td><%= member.getMemberId() %></td>
                              			<td><%= member.getMemberNM() %></td>
                              			<td><%= member.getMemberEmail() %></td>
                              			<td>
                              			    <i class="fas fa-cog open-btn" style="width: 25px; height: 25px;"></i>&nbsp;
                                    		<i class="fas fa-trash-alt delete-btn" style="width: 25px; height: 25px;"></i>
                              			</td>
                              			<td>
                              			<button type="button" class="btn btn-dark penaltyBtn" data-toggle="modal"
                                         data-target="#exampleModal">패널티</button>
                                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                                            aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">패널티 부여</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <!-- 패널티 부여 폼 -->
                                                        <form action="updatePenalty" method="post">
                                                            <div class="form-row">
                                                                <div class="form-group col-md-4">
                                                                    <label class="col-form-label">사용자 아이디</label>
                                                                </div>
                                                                <div class="form-group col-md-8">
                                                                    <input type="text" readonly
                                                                        class="form-control-plaintext penaltyMemberId"
                                                                        placeholder="해당사용자 아이디" name="penaltyId">
                                                                </div>
                                                            </div>
                                                            <div class="form-row">
                                                                <div class="form-group col-md-4">
                                                                    <label class="col-form-label">패널티 사유</label>
                                                                </div>
                                                                <div class="form-group col-md-8">
                                                                    <select class="form-control" name="penaltyReason">
                                                                        <option value="음란물 게시">음란물 게시</option>
                                                                        <option value="허위매물 게시">허위매물 게시</option>
                                                                        <option value="상업적 광고 및 홍보물 게시">상업적 광고 및 홍보물 게시</option>
                                                                        <option value="일반 욕설/비방물 게시">일반 욕설/비방물 게시</option>
                                                                        <option value="부모/성에 대한 욕설 게시">부모/성에 대한 욕설 게시</option>
                                                                        <option value="운영진/직원/회사 사칭 및 사기">운영진/직원/회사 사칭 및 사기</option>
                                                                        <option value="게시물 도배">게시물 도배</option>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group col-md-8"></div>
                                                                <div class="form-group col-md-4">
                                                                    <button class="btn btn-primary btn-block"
                                                                        type="submit">패널티부여</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">취소</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                              			</td>
                              		</tr>
                              	<% } %>
                              <% } %>
                            </tbody>
                          </table>
                          
                          <!-- 페이징 -->
                          <div class="text-center">
                            <ul class="pagination justify-content-center">
                            <!-- 맨처음으로 -->
                            <% if(currentPage > 1) { %>
                              <li class="page-item">
                              	<% if(searchFlag > 0) { %>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/searchMember?currentPage=1&selectKey=<%=selectKey%>&selectContent=<%=selectContent%>">&laquo;</a>
                              	<% } else {%>
                              		<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMember?currentPage=1">&laquo;</a>
                              	<% } %>
                              </li>
                              <li>
	                		<!-- 이전으로(<) -->
	                			<% if(searchFlag > 0) { %>
	                				<a class="page-link" href="<%= request.getContextPath() %>/admin/searchMember?currentPage=<%= currentPage-1 %>&selectKey=<%=selectKey%>&selectContent=<%=selectContent%>">&lt;</a>
	                			<% } else {%>
	                				<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMember?currentPage=<%= currentPage-1 %>">&lt;</a>
	                			<% } %>
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
		                				<% if(searchFlag > 0) { %>
		                					<a class="page-link" href="<%= request.getContextPath() %>/admin/searchMember?currentPage=<%= p %>&selectKey=<%=selectKey%>&selectContent=<%=selectContent%>"><%= p %></a>
		                				<% } else {%>
		                					<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMember?currentPage=<%= p %>"><%= p %></a>
		                				<% } %>
	                				</li>
                             	<% } %>
                             <% } %>
                             <% if(currentPage < maxPage){ %>
								<!-- 다음 페이지로(>) -->
                              	<li>
                              		<% if(searchFlag > 0) { %>
                              			<a class="page-link" href="<%= request.getContextPath() %>/admin/searchMember?currentPage=<%= currentPage+1 %>&selectKey=<%=selectKey%>&selectContent=<%=selectContent%>">&gt;</a>
                              		<% } else {%>
                              			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMember?currentPage=<%= currentPage+1 %>">&gt;</a>
                              		<% } %>
	                			</li>                             
                               <!-- 맨뒤로 -->
                             	<li class="page-item">
                             		<% if(searchFlag > 0) { %>
                             			<a class="page-link" href="<%= request.getContextPath() %>/admin/searchMember?currentPage=<%= maxPage %>&selectKey=<%=selectKey%>&selectContent=<%=selectContent%>">&raquo;</a>
                             		<% } else {%>
                             			<a class="page-link" href="<%= request.getContextPath() %>/admin/adminMember?currentPage=<%= maxPage %>">&raquo;</a>
                             		<% } %>
                              	</li>
                              	
                             <% } %>                           
                            </ul>
                        </div>
                        
                        <form action="searchMember" method="post" onsubmit="return validate();">
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <div class="input-group-text">
                                        <input class="form-control" type="radio" name="selectKey" id="idSearch" value="memberId" style="width:20px;height:20px;" checked>
                                        <label class="col-sm-2" style="font-size: large">아이디</label>
                                        <input class="form-control"type="radio" name="selectKey" id="nameSearch" value="memberNM" style="width:20px;height:20px;">
                                        <label class="col-sm-2" style="font-size: large">이름</label>
                                        <input class="form-control"type="radio" name="selectKey" id="emailSearch" value="memberEmail" style="width:20px;height:20px;">
                                        <label class="col-sm-2" style="font-size: large">이메일</label>
                                        <input class="form-control mr-sm-2 col-sm-4" type="text" placeholder="Search" id="searchText" name="selectContent">
                                        <button class="btn btn-primary btn-lg" type="submit">검색</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                
                    <div class="col-md-4" id="memberInfo">
                        <hr>
                        <p><button type="button" class="btn btn-outline-dark" id="close-btn">X</button> 닫기</p>
                        <hr>
                        <div class="row">
                            <div class="col-sm-5">
                                <input type="text" readonly class="form-control-plaintext" id="memberNM" value="회원이름">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-1">
                                <i class="far fa-user" style="width: 30px; height: 30px;"></i>
                            </div>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="memberId" value="회원아이디">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-1">
                                <i class="fas fa-mobile-alt" style="width: 30px; height: 30px;"></i>
                            </div>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="memberPhone" value="010-1234-1234">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-1">
                                <i class="fas fa-envelope" style="width: 30px; height: 30px;"></i>
                            </div>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="memberEmail" value="abc@naver.com">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group row">
                                	<label for="memberAddr" class="col-sm-3 col-form-label">주소</label>
                                    <div class="col-sm-9">
                                        <input type="text" readonly class="form-control-plaintext" id="memberAddr" value="주소">
                                    </div>
                                    <label for="memberJoinDate" class="col-sm-3 col-form-label">가입일</label>
                                    <div class="col-sm-9">
                                        <input type="date" readonly class="form-control-plaintext" id="memberEnrollDate" value="2020-01-08">
                                    </div>
                                    <label for="memberPanalty" class="col-sm-3 col-form-label">패널티</label>
                                    <div class="col-sm-9">
                                        <input type="number" readonly class="form-control-plaintext" id="memberPenalty" value="0">
                                    </div>
                                    <label for="memberPanalty" class="col-sm-3 col-form-label">계좌정보</label>
                                    <div class="col-sm-9">
                                        <input type="text" readonly class="form-control-plaintext" id="memberAccount" value="1111-1111-1111-1111">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-8">
                                <table class="table">
                                    <tr>
                                        <th style="background-color: lightgray;">회원삭제</th>
	                                    <td><button type="button" class="btn btn-secondary delete-btn-info">회원삭제</button></td>
                                    </tr>
                                  </table>
                            </div>
                        </div>
                        <hr>
                    </div>
	 			<!-- 컨텐트 내용 -->
	 		</div>	
	 	</div>
	 </div>
	 <script>
	 	// 검색창 유효성 검사
		 function validate() {
	         if ($("#searchText").val().trim().length == 0) {
	             alert("내용을 입력하세요");
	             return false;
	         } 
	         return true;
	     }
		
	     $(function () {
	    	// 검색한 경우 
		 		var selectKey = "<%=selectKey%>";
		 		var selectContent = "<%=selectContent%>";
		 		
		 		if(selectKey != "null" && selectContent != "null") {
		 			$.each($("input[name=selectKey]"),function(index,item){
		 				if( $(item).val() == selectKey ) {
		 					$(item).prop("checked","true");
		 				}
		 			});
		 			$("input[name=selectContent]").val(selectContent);
		 		}
	    	 
	    	 $("#memberInfo").hide();
	    	 
	    	 // 패널티 부여
	    	  $(".penaltyBtn").click(function(){ // 테이블 내부 버튼
	    		  var memberId = $(this).parent().parent().children().eq(1).html();
                   $(".penaltyMemberId").val(memberId);
               });
	    	 
	    	 // 회원 삭제
	         $(".delete-btn").click(function(){ // 테이블 내부 아이콘
	             if(confirm("정말 삭제하시겠습니까?")) {
	            	 var memberId = $(this).parent().parent().children().eq(1).html();
	            	 location.href = "deleteMember?memberId="+memberId;
	             }
	         }).css("cursor","pointer");
	    	 $(".delete-btn-info").click(function(){ // 상세조회 내부 버튼
	    		 if(confirm("정말 삭제하시겠습니까?")) {
	    			 var memberId = $("#memberId").val();
		    		 location.href = "deleteMember?memberId="+memberId;
	    		 }
	    	 });
	    	 
	    	 // 사이드바 색상
	         $(".nav-link").click(function () {
	             $(".nav-link").css("color", "black");
	             $(this).css("color", "white");
	         });
	         
	    	 // 회원상세조회 닫기
	         $("#close-btn").click(function() {
	             $("#memberInfo").hide();
	         });
	         
	    	 // 회원 상세조회
	         $(".open-btn").click(function(){
	        	 var memberId = $(this).parent().parent().children().eq(1).html();
	        	 // 회원 아이디를 얻는다
	        	 var memberNo = $(this).parent().parent().children().eq(0).html();
	        	 // 회원 번호를 얻는다
	        	 
	        	 $.ajax({
	        		 url : "memberInfo", // url은 필수 속성
						type : "post",
						data : {memberId:memberId,memberNo:memberNo}, // {key:value}에서 key는 문자열
						dataType : "json",
						success : function(member) { 
				
							$("#memberNM").val(member.memberNM).css({"font-size" : "x-large" , "font-weight" : "bolder"});
							$("#memberId").val(member.memberId);
							$("#memberPhone").val(member.memberPhone);
							$("#memberEmail").val(member.memberEmail);
							$("#memberEnrollDate").val(member.memberEnrollDate);
							$("#memberPenalty").val(member.memberPenalty); //패널티
							$("#memberAccount").val(member.memberAccount);
							$("#memberAddr").val(member.memberAddr);
							
						},
						error : function() {
							console.log("ajax 통신 실패");
						}
	        	 });
	        	 
	        	 $("#memberInfo").show();
	         });
	    	 
	     });
	     
	  
	 </script>
	 <%@ include file="adminFooter.jsp"%>
</body>
</html>