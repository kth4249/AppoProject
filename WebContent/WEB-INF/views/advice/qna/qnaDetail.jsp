<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.advice.qna.model.vo.Qna"%>
<%
	Qna qna = (Qna)request.getAttribute("qna");

	String currentPage = request.getParameter("currentPage");
	
	String qAnswer = null;
	
	if(qna.getAnswerContent() == null) {
		qAnswer = "N";
	} else {
		qAnswer = "Y";
	}
	
%>

<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="UTF-8">
        <title>고객지원1:1문의입력</title>
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
                <%--@ include file="sideMenu.jsp"--%>
                <!-- 사이드 메뉴 부분 -->
                <%@ include file="../comm/adviceSideMenu.jsp" %>
                <!--  공지사항 게시판 들어갈 부분 -->
                <div class="col-sm-8">
                    <ul class="nav nav-pills">
                        <li class="nav-item">
                          <a class="nav-link" href="<%= request.getContextPath()%>/advice/qna/main">1:1문의</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link active" href="<%= request.getContextPath()%>/advice/qna/list">문의내역확인</a>
                        </li>
                      </ul>
                    <hr>
                    <!-- 상단 제목 부분 -->
                    
                    <div class="form-row">
                        <div class="form-group col-md-2 text-center">
                            <label class="col-form-label">제목</label>
                        </div>
                        <div class="form-group col-md-10">
                            <input type="text" class="form-control" 
                            id="title" readonly value="<%= qna.getQnaTitle()%>">
                        </div>
                    </div>
                    <hr>
                    <div class="form-row">
                        <div class="form-group col-md-2 text-center">
                            <label>내용</label>
                        </div>
                        <div class="form-group col-md-10">
                        <textarea class="form-control" id="question" rows="10" 
                        style="resize: none;" readonly><%= qna.getQnaContent() %></textarea>
                        </div>
                    </div>
                    <hr>
                    <!-- 답변이 없을 경우 답변내용 숨기기 -->
                    <% if(qAnswer.equals("Y")) { %>
                    <div class="form-row" id="answer-form">
                        <div class="form-group col-md-2 text-center">
                            <label>답변</label>
                        </div>
                        <div class="form-group col-md-10">
                        <textarea class="form-control" id="answer" 
                        rows="10" style="resize: none;" readonly><%= qna.getAnswerContent() %>
                        </textarea>
                        </div>
                    </div>
                    <% } %>
                    
                    <div class="form-row">
                        <div class="form-group col-md-2 text-center"></div>
                        <div class="form-group col-md-12 text-center">
                            <button type="button" class="btn btn-primary"
                            onclick="location.href='<%= request.getContextPath()%>/advice/qna/list'">
                                                        되돌아가기</button>
                            <% if(qAnswer.equals("N")) {%>
                            <button type="submit" class="btn btn-primary"
                            onclick="location.href='<%= request.getContextPath()%>/advice/qna/updateForm?no=<%=qna.getQnaNo()%>'">수정</button>
                            <% } %>
                            <button type="button" class="btn btn-primary"
                            id="deleteBtn">삭제</button>
                            
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <!-- footer자리 -->
        <%@ include file="../../common/footer.jsp"%> 
        
        <!-- 삭제질문 -->
        <script>
        	$("#deleteBtn").on("click", function(){
        		if(confirm("정말 삭제하시겠습니까?")) {
        			location.href = "delete?no=<%=qna.getQnaNo() %>";
        		}
        	})
        </script>
        
    </body>
    
    
</html>