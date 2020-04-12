<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.kh.appoproject.product.model.vo.Product, com.kh.appoproject.product.model.vo.Image, com.kh.appoproject.product.model.vo.PageInfo"%>
<%@page import="java.util.List"%>
<%
	List<Product> pList = (List<Product>)request.getAttribute("pList");
	List<Image> fList = (List<Image>)request.getAttribute("fList");
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	String productItem = request.getParameter("item");
	String deviceNameUp = (String)request.getAttribute("deviceNameUp");
	
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/productdetail.css"
	type="text/css">
<title>상품게시판</title>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/nav.jsp"%>
	<section class="section-content py-5">
		<div class="container">
			<div class="row filter-basic">
				<main class="col-md-12">
					<div class="filter_product my-3">
						<div class="filter_holder d-flex justify-content-center">
							<ul class="d-sm-inline-flex pl-0">
								<li class="filter active current mx-3"><span
									class="font-weight-bold" onclick="filterSelection('all')">전체
										상품</span></li>
								<li class="filter mx-3"><span class="font-weight-bold"
									onclick="filterSelection('auction')">경매 상품</span></li>
								<li class="filter mx-3"><span class="font-weight-bold"
									onclick="filterSelection('basic')">일반 상품</span></li>
							</ul>
						</div>
					</div>
					<div class="form-inline mb-5 pb-3">
						<!--  
						<select class="mr-2 form-control">
							<option>최신순</option>
							<option>인기순</option>
						</select>
						-->
						<%-- 로그인이 되어있는 경우 --%>
						<% if(loginMember != null){ %>
						<button type="button" class="btn btn-danger ml-md-auto"
							id="insertBtn" onclick="location.href = 'insertForm';">판매하기</button>
						<% } %>
					</div>
					<div class="row">
						<% if(pList.isEmpty()){ %>
						<span>존재하는 게시글이 없습니다.</span>
						<% }else{ %>

						<% for(Product product : pList) {
                    			
                    		if(product.getProductForm().equals("A")){ %>
						<!-- 경매 상품 -->
						<div class="col-sm-6 col-md-3 mb-5 filterDiv god auction">
							<div class="product-grid mb-3 auctionselect">
								<div class="product-image">
									<a href="#"> 
									<input type="hidden" class="pNo auctionitem" value=<%= product.getProductNo() %>> 
										<%
	                                    	String src = request.getContextPath()+"/resources/uploadImages/no.jpg";
	                                    	for(Image file : fList){
	                                    		if(file.getProductNo() == product.getProductNo()){
	                                    			src = request.getContextPath()+"/resources/uploadImages/"+file.getImagePath();
	                                    		}
	                                    	}
	                                    %> 
	                                    <img class="pic1" src="<%= src %>">
									</a> <span class="product-new-label">경매</span>
									<% if(product.getAuctionImmediateBid()>0){ %>
									<span id="product-im-label">바로 구매</span>
									<% } %>
								</div>
								<div class="product-content">
									<h5 class="title py-3">
										<a href="#"><%= product.getProductTitle() %></a>
									</h5>
									<span class="price text-danger"><%= product.getAuctionReservePrice() %></span>
									<span>원</span>
									<div class="time-wrap mt-2">
										<span class="time text-muted"><%= product.getAuctionDeadline() %>&nbsp;까지</span>
									</div>
								</div>
							</div>
						</div>
						<% }else { %>
						<!-- 일반 상품 -->
						<div class="col-sm-6 col-md-3 mb-5 filterDiv god basic">
							<div class="product-grid mb-3 basicselect">
								<div class="product-image">
									<a href="#"> <input type="hidden" class="pNo basicitem"
										value=<%= product.getProductNo() %>> 
										<%
	                                    	String src = request.getContextPath()+"/resources/uploadImages/no.jpg";
	                                    	for(Image file : fList){
	                                    		if(file.getProductNo() == product.getProductNo()){
	                                    			src = request.getContextPath()+"/resources/uploadImages/"+file.getImagePath();
	                                    		}
	                                    	}
	                                    %> <img class="pic1" src="<%= src %>">
									</a>
								</div>
								<div class="product-content">
									<h5 class="title py-3">
										<a href="#"><%= product.getProductTitle() %></a>
									</h5>
									<span class="price text-danger"><%= product.getBasicPrice() %></span>
									<span>원</span>
								</div>
							</div>
						</div>
						<% } %>
						<% } %>
						<% } %>
					</div>
				</main>

				<!-- 페이징바 -->
				<div class="col-md-12 d-flex justify-content-center">
					<ul class="pagination mt-5 pt-2">
						<% if(currentPage > 1) {%>
						<li class="page-item disabled">
							<!-- 맨 처음으로(<<) --> <a class="page-link"
							href="<%= request.getContextPath() %>/product/list?item=<%= productItem %>&currentPage=1">&laquo;</a>
						</li>
						<% } %>

						<!-- 5개 페이지 목록 -->
						<% for(int p = startPage; p<= endPage; p++){ %>
						<% if(p == currentPage){ %>
						<li class="page-item active"><a class="page-link"><%= p %></a>
						</li>
						<% }else{ %>
						<li class="page-item"><a class="page-link"
							href="<%= request.getContextPath() %>/product/list?item=<%= productItem %>&currentPage=<%= p %>"><%= p %></a>
						</li>
						<% } %>
						<% } %>

						<!-- 맨 끝으로(>>) -->
						<% if(currentPage < maxPage){ %>
						<li class="page-item"><a class="page-link"
							href="<%= request.getContextPath() %>/product/list?item=<%= productItem %>&currentPage=<%= maxPage %>">&raquo;</a>
						</li>
						<% } %>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<script>
    
    // 게시글 상세보기 기능
    $(function(){
    	$(".auctionselect").click(function(){
    		var pNo = $(this).find("input").val();
    		location.href="<%= request.getContextPath() %>/product/detail?no="+ pNo + "&currentPage="+<%= currentPage%>;
    	});
    	$(".basicselect").click(function(){
    		var pNo = $(this).find("input").val();
    		location.href="<%= request.getContextPath() %>/product/detail?no="+ pNo + "&currentPage="+<%= currentPage%>;
    	});
    	
    });
    
    
    filterSelection("all")
    function filterSelection(c) {
        var x, i;
        x = document.getElementsByClassName("filterDiv");
        if (c == "all") c = "";
        
        for (i = 0; i < x.length; i++) {
            RemoveClass(x[i], "show");
            if (x[i].className.indexOf(c) > -1) AddClass(x[i], "show");
        }
    }

    // Show filtered elements
    function AddClass(element, name) {
        var i, arr1, arr2;
        arr1 = element.className.split(" ");
        arr2 = name.split(" ");
        for (i = 0; i < arr2.length; i++) {
            if (arr1.indexOf(arr2[i]) == -1) {
            element.className += " " + arr2[i];
            }
        }
    }

    // Hide elements that are not selected
    function RemoveClass(element, name) {
        var i, arr1, arr2;
        arr1 = element.className.split(" ");
        arr2 = name.split(" ");
        for (i = 0; i < arr2.length; i++) {
            while (arr1.indexOf(arr2[i]) > -1) {
            arr1.splice(arr1.indexOf(arr2[i]), 1);
            }
        }
        element.className = arr1.join(" ");
    }
    </script>
</body>
</html>