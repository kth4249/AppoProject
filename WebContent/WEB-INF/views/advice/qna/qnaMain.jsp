<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="UTF-8">
        <title>일대일문의입력</title>

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
                <!--  폼시작-->
                <form action="<%=request.getContextPath()%>/advice/qna/insert" method="post"
                     onsubmit="return validate();">
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
                    <!-- 글 등록 부분-->
                    <!-- 상단 제목 부분 -->
                    
                    <div class="form-row">
                        <div class="form-group col-md-2 text-center">
                            <label class="col-form-label">제목</label>
                        </div>
                        <div class="form-group col-md-10">
                            <input type="text" class="form-control" 
                            placeholder="제목을 입력해주세요" id="title"
                            name="title">
                        </div>
                    </div>
                    <hr>
                    <div class="form-row">
                        <div class="form-group col-md-2 text-center">
                            <label>내용</label>
                        </div>
                        <div class="form-group col-md-10">
                        <textarea class="form-control" id="content" 
                        name="content" rows="10"></textarea>
                        </div>
                    </div>
                
                    <div class="form-row">
                        <div class="form-group col-md-2"></div>
                        <div class="form-group col-md-10 text-center">
                            <h6>질문을 구체적으로 입력해주세요. <br>
                                정확하고 빠른 답변에 도움이 됩니다. (0/4000자 영문기준)</h6>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6"></div>
                        <div class="form-group col-md-6">
                            <button type="submit" class="btn btn-primary">제출</button>
                        </div>
                        
                    </div>
                    <!-- 제출끝 -->
                </form>
                <!-- 폼끝 -->
                </div>
                <!-- 공지사항 게시판끝  -->
            </div>
        </div>
        <br><br>
        <!-- footer자리 -->
		<%@ include file="../../common/footer.jsp"%>
		
		<script>
			// 제목 내용 유효성 검사
			function validate() {
				if ($("#title").val().trim().length == 0) {
					alert("제목을 입력해 주세요.");
					$("#title").focus();
					return false;
				}
				
				if ($("#content").val().trim().length == 0) {
					alert("내용을 입력해주세요.");
					$("#content").focus();
					return false;
				}
			}
		
		</script>
		
    </body>
</html>