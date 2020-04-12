<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.advice.qna.model.vo.Qna"%>
<%@page import="com.kh.appoproject.advice.qna.model.vo.PageInfo"%>

<%@page import="java.util.List"%>
<%
	/* qna리스트*/
	List<Qna> list = (List<Qna>)request.getAttribute("list");
	/* 페이지 */
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
	// answerYn 처리
	for(Qna qna : list) {
		
		if(qna.getAnswerContent() == null) {
			qna.setAnswerYn("접수완료");
		} else {
			qna.setAnswerYn("답변완료");
		}
	}
	// 검색키
	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");

%>
<!DOCTYPE html>

<html lang="ko">
    <head>
    <meta charset="UTF-8">
        <title>문의내역확인(목록)</title>
       
    </head>
    <body>
    	<%@ include file="../../common/header.jsp"%>
        <%@ include file="../../common/nav.jsp"%>
        <div class="container">
            
            <div class="container-fluid top20">
                <div class="row">
                    <div class="col-md-12">
                        <h1>고객지원</h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <!-- 사이드 메뉴 부분 -->
                <%@ include file="../comm/adviceSideMenu.jsp" %>
                <!--  공지사항 게시판 들어갈 부분 -->
                <div class="col-sm-8">
                    <ul class="nav nav-pills">
                        <li class="nav-item">
                            <!-- 활성화 active 클래스 -->
                            <a class="nav-link" href="<%= request.getContextPath()%>/advice/qna/main">1:1문의</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="<%=request.getContextPath()%>/advice/qna/list">문의내역확인</a>
                        </li>
                      </ul>
                    <hr>
                    <!-- 상단 제목 부분 -->
                    <table class="table" id="list-table"> 
                        <thead> 
                            <tr class="thead"> 
                                <th scope="col">No</td> 
                                <th scope="col">처리상태</td> 
                                <th scope="col">제목</td> 
                            </tr> 
                        </thead> 
                        <tbody>
                        	<% if(list.isEmpty()) {%> 
                            <tr> 
                                <td colspan="3"> 존재하는 게시글이 없습니다.</td> 
                            </tr> 
                            <% } else { %>
                            	<% for(Qna qna : list) { %>
                            		<tr>
                            			<td> <%= qna.getQnaNo() %> </td>
                            			<td> <%= qna.getAnswerYn() %> </td>
                            			<td> <%= qna.getQnaTitle() %> </td>
                            		</tr>
                            	<% } %>
                            <% } %>
                            
                        </tbody> 
                    </table>
                    
                    <!-- 검색창 -->
                    <div>
                        <!-- 가운데정렬시 class="text-center" -->
                        <form action="search" method="GET" class="text-center"
                        	 id="searchForm" onsubmit="return checkValue();">
                            <select name="searchKey" class="form-control" 
                            style="width:100px; display: inline-block;">
                                <!-- <option value="title" selected>글제목</option> -->
                                <option value="title">글제목</option>
                                <option value="content">내용</option>
                                <option value="titcont">제목+내용</option>
                            </select>
                            <input type="text" name="searchValue" class="form-control" 
                            style="width:25%; display: inline-block;">
                            <button class="form-control btn btn-primary" 
                            style="width:100px; display: inline-block; 
                            margin-bottom: 5px;">검색</button>
                        </form>
                    </div>
                    <!-- 검색창끝 -->
                    <!-- 검색창 스크립트-->
                    <script>
                    	$(function(){
                    		var searchKey = "<%= searchKey %>";
                    		var searchValue = "<%= searchValue %>";
                    		
                    		if(searchKey != "null" && searchValue != "null"){
                    			$.each($("select[name=searchkey] > option"), function(index, item){
                    				if( $(item).val() == searchKey){
                    					$(item).prop("selected", "true");
                    				}
                    			});
                    			
                    			$("input[name=searchValue]").val(searchValue);
                    		}
                    	});
                    	
                    	// 검색창 내용 유무 확인
            			function checkValue(){
            				if ($("#searchValue").val().trim().length == 0) {
            					alert("검색내용을 입력해주세요");
            					return false;
            				} else {
            					return true;
            				}
            			}
                    </script>
                    <br>
                    <!-- 페이징바 -->
                    <div class="form-row">
                        <div class="form-group col-md-12">
                        <ul class="pagination justify-content-center">
                        	<% if(currentPage > 1) { %>
                            <li class="page-item">
                            	<!-- 맨처음으로-->
                                <a class="page-link" href="<%= request.getContextPath() %>/advice/qna/list?currentPage=1">&laquo;</a>
                            </li>
                            	<!-- 이전으로 -->
                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath() %>/advice/qna/list?currentPage=<%= currentPage-1 %>">&lt;</a>
                            </li>
                            <% } %>
                            
                            <!-- 5개의 페이지 목록 -->
                            <% for(int p = startPage; p <= endPage; p++){ %>
                            	<% if(p == currentPage) { %>
                           	<li class="page-item">
                                <a class="page-link"><%= p %></a>
                            </li>
                            	<% } else { %>
                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath() %>/advice/qna/list?currentPage=<%= p %>"><%= p %></a>
                            </li>
                            	<% } %>
                            <% } %>
                            <!-- 다음 페이지로-->
                            <% if(currentPage < maxPage) {%>
                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath() %>/advice/qna/list?currentPage=<%= currentPage+1 %>">&gt;</a>
                            </li>
                            
                            <!-- 맨끝으로-->
                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath() %>/advice/qna/list?currentPage=<%= maxPage %>">&raquo;</a>
                            </li>
                            <% } %>
                        </ul>
                    	</div>
                    <!-- 1,2,3,4,5 끝 -->
                	</div>
        		</div>
        	</div>
        </div>
        <br><br>
        <!-- footer자리 -->
        <%@ include file="../../common/footer.jsp"%>
        
        <script>
        	// 게시글 상세보기 기능(jQuery)
        	$(function(){
        		$("#list-table td").click(function(){
        			var qnaNo = $(this).parent().children().eq(0).text().trim();
        			location.href="<%= request.getContextPath() %>/advice/qna/detail?no="+qnaNo+"&currentPage="+<%=currentPage%>;
        		}).mouseenter(function(){
        			$(this).parent().css("cursor", "pointer");
        		});
        	})
        </script>

    </body>
</html>