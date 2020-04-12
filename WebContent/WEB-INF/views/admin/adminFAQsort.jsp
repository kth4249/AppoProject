<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.appoproject.admin.model.vo.PageInfo"%>
<%@page import="com.kh.appoproject.admin.model.vo.FAQ"%>
<%@page import="java.util.List"%>
<%
	List<FAQ> fList = (List<FAQ>) request.getAttribute("fList");
	PageInfo pInf = (PageInfo) request.getAttribute("pInf");
	//String searchKey = request.getParameter("searchKey");
	//String searchValue = request.getParameter("searchValue");

	String faqClass = (String)request.getAttribute("faqClass");
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminBaseForm</title>
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

/* 컨텐츠 영역 스타일 */
.FAQ_quetion {
	cursor: pointer;
}

.FAQ_answer {
	position: relative;
	display: none;
}

.FAQ_modify {
	position: absolute;
	bottom: 0;
	right: 30px;
}

.FAQ_modify .btn-info {
	border-radius: 0.3rem;
}

.FAQ_quetion>div {
	position: relative;
}

.FAQ_quetion img {
	position: absolute;
	top: 10px;
	bottom: 0;
	left: 20px;
	right: 0;
	margin: auto;
	width: 30px;
}

.FAQ_answer p {
	margin-left: 150px;
	margin-right: 150px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="adminHeader.jsp"%>
		<div class="row">
			<%@ include file="adminSidebar.jsp"%>
			<div class="col-md-10">
				<!-- 컨텐트 내용을 추가 -->
				<div class="row">
					<div class="col-md-4 text-center">
						<a href="<%=request.getContextPath()%>/admin/support"
							class="btn btn-info btn-block my-2 mr-2 ml-2 px-5">공지사항</a>
					</div>
					<div class="col-md-4 text-center">
						<a href="<%=request.getContextPath()%>/admin/support/FAQ"
							class="btn btn-info btn-block active my-2 mr-2 ml-2 px-5">자주
							묻는 질문</a>
					</div>
					<div class="col-md-4 text-center">
						<a href="<%=request.getContextPath()%>/admin/support/QnA"
							class="btn btn-info btn-block my-2 mr-2 ml-2 px-5">QnA</a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="btn-block btn-group btn-group-toggle FAQclasses"
							data-toggle="buttons">
							<label class="btn btn-secondary"> <input
								type="radio" name="options" id="FAQall" autocomplete="off"
								> 전체
							</label> <label class="btn btn-secondary"> <input type="radio"
								name="FAQmenu" class="FAQmenu" autocomplete="off" value="배송문의"> 배송문의
							</label> <label class="btn btn-secondary"> <input type="radio"
								name="FAQmenu" class="FAQmenu" autocomplete="off" value="교환/환불"> 교환/환불
							</label> <label class="btn btn-secondary"> <input type="radio"
								name="FAQmenu" class="FAQmenu" autocomplete="off" value="주문결제"> 주문결제
							</label> <label class="btn btn-secondary"> <input type="radio"
								name="FAQmenu" class="FAQmenu" autocomplete="off" value="회원서비스"> 회원서비스
							</label>
						</div>
					</div>
				</div>
				<%
					if (fList.isEmpty()) {
				%>

				<%
					} else {
				%>
				<%
					for (FAQ faq : fList) {
				%>
				<div class="row FAQ_quetion">
					<div class="col-md-2">
						<img
							src="<%=request.getContextPath()%>/resources/appoimg/icon2.png">
					</div>
					<div class="col-md-10">
						<p style="line-height: 3rem;"><%=faq.getFaqTitle()%></p>
					</div>
				</div>
				<div class="row FAQ_answer">
					<div class="col-md-12">
						<hr>
						<p style="line-height: 30px; font-size: small;"><%=faq.getFaqContent()%></p>
						<br> <br>
						<div class="FAQ_modify">
							<a href="FAQmodifyForm?faqNo=<%=faq.getFaqNo() %>&currentPage=<%=currentPage %>" class="btn btn-info my-1 mr-1 ml-1 px-2 py-1">수정</a>
							<a onclick="return confirm('정말 삭제하시겠습니까?');" href="FAQmodify?faqNo=<%=faq.getFaqNo() %>" class="btn btn-info my-1 mr-1 ml-1 px-2 py-1">삭제</a>
						</div>
					</div>
				</div>
				<hr>
				<%
					}
				%>
				<%
					}
				%>

				<div class="row" id="moreFAQ">
					<div class="col-md-12">
						<button type="button" class="btn btn-block btn-outline-primary"
							onclick="moreFAQ()">더보기↓</button>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12" style="margin-bottom: 50px">
						<a href="FAQenrollForm" class="btn btn-info my-1 mr-1 ml-1 px-5 py-1"
							style="position: absolute; top: 0px; right: 30px;">등록</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>

		var currentPage = <%= currentPage %>;
		
		function moreFAQ() {
			currentPage = currentPage + 1
			$.ajax({
				url : "FAQsort?currentPage="+ currentPage + "&faqClass=" + faqClass,
				type : "get",
				dataType : "json",
				success : function(fList){
					console.log(fList)
					$.each(fList, function(i){
						var $divq = $("<div>").prop("class", "row FAQ_quetion");
						var $divi = $("<div>").prop("class", "col-md-2");
						var $img = $("<img>").prop("src", "<%=request.getContextPath()%>/resources/appoimg/icon2.png");
						var $divt = $("<div>").prop("class", "col-md-10");
						var $title = $("<p>").html(fList[i].faqTitle).css("line-height", "3rem");
							
						
						var $diva = $("<div>").prop("class", "row FAQ_answer");
						var $div12 = $("<div>").prop("class", "col-md-12");
						var $hr = $("<hr>");
						var $content = $("<p>").css("line-height", "30px").html(fList[i].faqContent);
						var $br = $("<br>")
						var $divm = $("<div>").prop("class", "FAQ_modify");
						var $btnm = $("<a>").prop("class", "btn btn-info my-1 mr-1 ml-1 px-2 py-1").text("수정");
						var $btnd = $("<a>").prop("class", "btn btn-info my-1 mr-1 ml-1 px-2 py-1").text("삭제");
						
						$divq.append($divi).append($divt);
						$divq.children(".col-md-2").append($img);
						$divq.children(".col-md-10").append($title);
						
						$divm.append($btnm).append($btnd);
						$div12.append($hr).append($content).append($br).append($br).append($divm);
						$diva.append($div12)
						
						console.log(fList[i].faqTitle)
						$("#moreFAQ").before($divq);
						$("#moreFAQ").before($diva);
						$("#moreFAQ").before($hr)
						
						$(".FAQ_answer").css("display", "none");
						$(".btn-info").css("color", "white");
						
						<%-- if(currentPage == <%=maxPage%>){
							$("#moreFAQ").css("display","none");
						} --%>
					})
				},
				error : function() {
					console.log("ajax 통신 실패")
				}
			})
			
			
		}
		
		
		$(function(){
			$(".FAQ_answer").css("display", "none");
			
			$(document).on("click", ".FAQ_quetion" ,function() {
	
				// 메뉴(div)를 클릭했을 때
				// 내용(p)이 display == none 인 경우
				if ($(this).next(".FAQ_answer").css("display") == "none") {
	
					// 선택한 메뉴의 형제 요소 중 p.contents를 숨김
					$(this).siblings(".FAQ_answer").slideUp();
					// 메뉴 다음 요소(p, 내용)을 보이게 함
					$(this).next().slideDown();
	
					// 메뉴가 열려 있는 경우
				} else {
					$(this).next().slideUp();
				}
	
			});
			
			$(".FAQmenu").click(function(e) {
				var faqClass = e.target.value;
				location.href = "FAQsort?faqClass=" + faqClass;
			})
			
			$("#FAQall").click(function(){
				location.href = "FAQ";
			})
		})
		
		
		if(currentPage == <%=maxPage%>){
			$("#moreFAQ").css("display","none");
		}
		
		
		$(".FAQclasses input").each(function(index, item){
			<%-- console.log(item);
			console.log(item.value);
			console.log("<%=faqClass%>") --%>
			if(item.value == "<%=faqClass%>"){
				$(this).prop("checked", true);
			} 
		})

	</script>

	<%@ include file="adminFooter.jsp"%>
</body>
</html>