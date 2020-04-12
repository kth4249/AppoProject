<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/carousel.css" type="text/css">
<meta charset="UTF-8">
<title>Appo_mainPage</title>
</head>
<body>
	<%@ include file="WEB-INF/views/common/header.jsp"%>
	<%@ include file="WEB-INF/views/common/nav.jsp"%>


	<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="<%=request.getContextPath()%>/resources/appoimg/아이폰11pro.PNG">
        <div class="container">
          <div class="carousel-caption text-left">
            <h1>iPhone 11 pro</h1>
            <p>iPhone 11 pro 로 갈아타야 하는 이유</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">보러가기</a></p>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/resources/appoimg/watch.png">
        <div class="container">
          <div class="carousel-caption text-left" id="watchtext">
          <div class="carouselarea2">
            <h1 id="carousel2">Apple Watch Series</h1>
            <p id="carousel2">다양한 Apple Watch Series를 저렴한 가격으로 구매할 수 있습니다</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">구매하기</a></p>
          </div>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/resources/appoimg/미니.PNG">
        <div class="container">
          <div class="carousel-caption text-right">
            <h1 id="carousel3">iPad mini</h1>
            <p id="carousel3">그 이름과는 다른 파워.</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">보러가기</a></p>
          </div>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>



  <!-- Marketing messaging and featurettes
              ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->

  <div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
      <div class="col-lg-4">

        <img src="<%=request.getContextPath()%>/resources/appoimg/44.png" alt="">
        <h2>iPhone SE</h2>
        <p>iPhone SE Silver 64GB 새상품</p>
        <p><a class="btn btn-secondary" href="#" role="button">보러가기 &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">

        <img src="<%=request.getContextPath()%>/resources/appoimg/55.png" alt="">
        <h2>Apple Watch</h2>
        <p>Apple Watch Pink 중고상품 경매</p>
        <p><a class="btn btn-secondary" href="#" role="button">보러가기 &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">

        <img src="<%=request.getContextPath()%>/resources/appoimg/66.png" alt="">
        <h2>iPhone X</h2>
        <p>iPhone X 즉시구매가 550,000원</p>
        <p><a class="btn btn-secondary" href="#" role="button">보러가기 &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->









  </div>
	
	<div>
		<%@ include file="WEB-INF/views/common/footer.jsp"%>
	</div>
</body>
</html>