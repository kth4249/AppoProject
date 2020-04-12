<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="UTF-8">
        <title>고객지원1:1문의결과</title>

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
                          <a class="nav-link active" 
                          href="<%=request.getContextPath()%>/advice/qna/main">1:1문의</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" 
                          href="<%=request.getContextPath()%>/advice/qna/list">문의내역확인</a>
                        </li>
                      </ul>
                    <hr>
                    <!-- 상단 제목 부분 -->
                    <div class="form-row">
                        <div class="form-group col-md-12 text-center">
                            <h3>1:1문의가 성공적으로 접수 되었습니다</h3>
                        </div>
                        
                    </div>
                    <hr>
                    <div class="form-row">
                        <div class="col-md-12 text-center">
                        <!-- 결과내용자리 -->
                        <br>
                        문의에 대한 답변은 고객지원 1:1문의 문의내역확인에서 확인 할 수 있습니다.<br>

                        일반적으로 접수일로부터 다음영업일까지 답변을 드리고 있으나,<br>

                        문의량이 많을 경우 다소 지연 될 수 있는 점 양해바랍니다.<br>
                        <br><br>
                        </div>
                        
                    </div>
                    <div class="form-row">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-5"></div>
                        <div class="form-group col-md-6">
                            <button type="submit" class="btn btn-primary"
                            onclick="location.href ='<%=request.getContextPath()%>/advice/qna/list'">
                            	문의내역으로</button>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <!-- footer자리 -->
        <%@ include file="../../common/footer.jsp"%>
        
        
</html>