<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.advice.notice.model.vo.Notice"%>
<% 
	Notice notice = (Notice)request.getAttribute("notice"); 
	
	String currentPage = request.getParameter("currentPage");
%>

<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="UTF-8">
        <title>공지사항상세</title>

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
                    <h3>공지사항</h3>
                    <hr>
                        <!-- 상단 제목 부분 -->
                        <div class="form-row">
                            <div class="form-group col-md-2 text-center">
                                <label class="col-form-label" for="inputDefault">제목</label>
                            </div>
                            <div class="form-group col-md-10">
                                <input type="text" class="form-control" id="inputDefault" 
                                value="<%= notice.getNoticeTitle() %>" readonly>
                            </div>
                        </div>
                        <hr>
                        <div class="form-row">
                            <div class="form-group col-md-2 text-center">
                                <label for="exampleTextarea">내용</label>
                            </div>
                            <div class="form-group col-md-10">
                            <textarea class="form-control" id="exampleTextarea" 
                            rows="10" style="resize: none;" readonly><%= notice.getNoticeContent() %></textarea>
                            </div>
                        </div>
                        <hr>
                        <div class="form-row">
                            <div class="form-group col-md-12 text-center">
                                <a href="list?currentPage=<%=currentPage %>" class="btn btn-primary">되돌아가기</a>
                            </div>
                            
                        </div>
    
                    
                </div>
                <!-- col-md-8 끝 -->
            </div>
            <!-- div class="row" 끝나는 부분 -->
            
        <!-- container 끝나는 부분 -->
        </div>
        <!-- footer자리 -->
        <%@ include file="../../common/footer.jsp"%>

    </body>
</html>