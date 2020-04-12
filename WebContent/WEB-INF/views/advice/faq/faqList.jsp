<%@page import="com.kh.appoproject.advice.faq.model.vo.PageInfo"%>
<%@page import="com.kh.appoproject.advice.faq.model.vo.Faq"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// faq 리스트
	List<Faq> list = (List<Faq>)request.getAttribute("list");

	// 페이징
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int limit = pInf.getLimit();
	//int startPage = pInf.getStartPage();
	//int endPage = pInf.getEndPage();
	
	String faqClass = request.getParameter("faqClass");
	System.out.println(faqClass);

%>
<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="UTF-8">
        <title>자주묻는질문</title>
        <style>

            .qtype{
                color:#007bff;
                display: inline;
            }

            .atype{
                color:red;
                display: inline-block;
            }

            .detail{
                display: inline;
                /* margin-left: 30px; */
            }
                        
        </style>
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
                <%@ include file="../comm/adviceSideMenu.jsp" %>
                <div class="col-sm-8">
                    <h3>자주묻는질문</h3>
                    <hr>
                    <!-- 상단분류바 -->
                    <ul class="nav nav-pills">
                        <li class="nav-item asd">
                            <a class="nav-link" href="list">전체</a>
                        </li>
                        <li class="nav-item asd">
                            <a class="nav-link" href="subList?faqClass=배송문의">배송문의</a>
                        </li>
                        <li class="nav-item asd">
                            <a class="nav-link" href="subList?faqClass=교환/환불">교환/환불</a>
                        </li>
                        <li class="nav-item asd">
                            <a class="nav-link" href="subList?faqClass=주문결제">주문결제</a>
                        </li>
                        <li class="nav-item asd">
                            <a class="nav-link" href="subList?faqClass=회원서비스">회원서비스</a>
                        </li>
                    </ul>
                    <hr>
                    <!-- 상단 제목 부분 -->
                    <!-- 질문 / 답변1 -->
                    <% if(list.isEmpty()) {%>
                    <div class="form-row">
                    	<div class="form-group col-md-12">
                    		<h5> 존재하는 글이 없습니다</h5>
                    	</div>
                    </div>
                    <%} else {%>
                    	<%for (Faq faq : list) {%>
                    <div class="form-row ques">
                        <div class="form-group col-md-12">
                            <h5 class="qtype">Q</h5>
                            <h5 class="detail"><%=faq.getFaqTitle()  %></h5>
                        </div>
                    </div>
                    <div class="form-row answer">
                        <div class="form-group col-md-12">
                            <h5 class="atype">A</h5>
                            <h5 class="detail"><%=faq.getFaqContent() %></h5>
                        </div>
                    </div>
                    	<%} %>
                    <%} %>
                    
                    <% if(currentPage < maxPage) { %>
                    <!-- 더보기 버튼 -->
                    <div class="form-row">
                        <div class="form-group col-md-12  text-center">
                            <button type="button" class="btn btn-primary"
                            onclick="location.href='list?limit=<%=limit%>&add=5'">더보기</button>
                        </div>
                    </div>
                    <% } %>
                    <!-- 검색창  -->
                    <div>
                        <!-- 가운데정렬시 class="text-center" -->
                        <form action="search" method="GET" class="text-center" 
                        	id="searchForm" onsubmit="return checkValue();">
                            <input type="text" name="searchKey" id="searchKey" 
                            class="form-control" style="width:40%; display: inline-block;">
                            <button class="form-control btn btn-primary" 
                            style="width:100px; display: inline-block; 
                            margin-bottom: 5px;" >검색</button>
                        </form>
                    </div> 
                    <!-- 검색창끝 -->
                    
                </div>
            </div>
        </div>
        <br><br>
        <!-- footer자리 -->
        <%@ include file="../../common/footer.jsp"%>
	    <script>
	    
		    $(function(){
		    					
		    	/* 네비바 선택 active 활성화 */
		    	var faqClass = "<%=faqClass%>";
		    	// console.log("faqClass : " + faqClass);
		    	// var type = $(".asd > a").eq(3).html();
		    	// console.log(type);
		    	// 재대로해보자
		    	// 카테고리 변경
		    	// console.log(faqClass == type);
		    	
		    	
		    	for(var i=0; i<5; i++) {
		    		if(faqClass == "null") {
		    			$(".asd > a").eq(0).addClass("active");
		    		
		    		} else if(faqClass == $(".asd > a").eq(i).html()) {
		    			//console.log("값일치");
		    			//console.log($(".asd > a").eq(i));
		    			$(".asd > a").eq(i).addClass("active");
		    		}
		    	} 
		  
				$(".answer").css("display", "none");
				
				// 질문답변 slide 스크립트
		        $(".ques").mouseenter(function(){
		            $(this).children().css("cursor", "pointer");
		        }).click(function(){
		            // 메뉴가 닫혀있는 경우
		            if($(this).next().css("display") == "none") {
		                //ques의 answer클래스의 모든 속성을 닫고
		                $(this).siblings(".answer").slideUp();
		                //ques의 다음 속성값을 열어라
		                $(this).next().slideDown();
		            // 메뉴가 열여있는 경우
		            } else {
		                // 닫아라
		                $(this).next().slideUp();
		            }
		        });
				
		    });
		    
		 	// 검색창 내용 유무 확인
			function checkValue(){
				if ($("#searchKey").val().trim().length == 0) {
					alert("검색내용을 입력해주세요");
					return false;
				} else {
					return true;
				}
			}
		 	
		 	
		 	
			// $("navColor").prop("class", "active"); 
	    </script>
    </body>
   
</html>