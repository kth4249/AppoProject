<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/productdetail.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>상품 등록</title>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>
	<div class="container my-5 py-5">
		<div class="row product-reg">
			<h2 class="col-md-12 d-flex py-5">
				기본정보 <span class="ml-3">*필수항목</span>
			</h2>
			<form method="POST"
				action="<%= request.getContextPath()%>/product/insert"
				name="product-form" class="col-md-12 form-horizontal"
				enctype="multipart/form-data" role="form"
				onsubmit="return validate();">
				<!-- 상품이미지 -->
				<div class="row product-reg-img d-flex py-4 bor">
					<div class="col-md-3 d-flex p-reg-left">
						<label for="product-img">상품이미지<span>*</span></label>
					</div>
					<div class="col-md-9">
						<div class="form-inline mb-2 product-img-area">
							<!-- 대표이미지 -->
							<div class="mr-2 mb-2 boardImg" id="titleImgArea">
								<img id="titleImg" width="150" height="150">
							</div>

							<!-- 다른 이미지 -->
							<div class="mr-2 mb-2 boardImg" id="contentImgArea1">
								<img id="contentImg1" width="150" height="150">
							</div>

							<div class="mr-2 mb-2 boardImg" id="contentImgArea2">
								<img id="contentImg2" width="150" height="150">
							</div>

							<div class="mr-2 mb-2 boardImg" id="contentImgArea3">
								<img id="contentImg3" width="150" height="150">
							</div>

							<div class="mr-2 mb-2 boardImg" id="contentImgArea4">
								<img id="contentImg3" width="150" height="150">
							</div>
						</div>
						<div id="fileArea">
							<input type="file" id="img1" name="img1"
								accept=".gif, .jpg, .png, .jpeg" onchange="LoadImg(this,1)">
							<input type="file" id="img2" name="img2"
								accept=".gif, .jpg, .png, .jpeg" onchange="LoadImg(this,2)">
							<input type="file" id="img3" name="img3"
								accept=".gif, .jpg, .png, .jpeg" onchange="LoadImg(this,3)">
							<input type="file" id="img4" name="img4"
								accept=".gif, .jpg, .png, .jpeg" onchange="LoadImg(this,4)">
							<input type="file" id="img5" name="img5"
								accept=".gif, .jpg, .png, .jpeg" onchange="LoadImg(this,5)">
						</div>


						<div class="img-notice mt-3">
							<b>* 대표이미지 포함 1장 이상의 사진을 올려주세요.</b> <br> - 상품 이미지는
							600x600에 최적화 되어 있습니다. <br> - jpg/jpeg/png/gif 확장자만 등록 가능합니다. <br>
							- 사이즈에 따라 이미지가 깨지는 경우가 발생할 수 있습니다. <br> - 개당 이미지 크기는 최대 10M 입니다.
						</div>
					</div>
				</div>
				<div class="row d-flex py-4 bor">
					<div class="col-md-3 d-flex p-reg-left">
						<label for="product-title">제목<span>*</span></label>
					</div>
					<div class="col-md-9">
						<div>
							<input type="text" class="form-control" name="productTitle"
								id="productTitle" maxlength="40" placeholder="상품 제목을 입력해주세요."
								required>
							<p>
								<span id="counter" class="text-danger">0</span>/40
							</p>
						</div>
					</div>
				</div>
				<div class="row d-flex py-4 bor">
					<div class="col-md-3 d-flex p-reg-left">
						<label for="product-category">판매상품품목<span>*</span></label>
					</div>
					<div class="col-md-9">
						<div>
							<div class="reg-category-wrap">
								<div class="reg-category reg-ca1">
									<ul class="item-list itemArea pt-3">
										<li class="ca-li1">Mac</li>
										<li class="ca-li1">iPad</li>
										<li class="ca-li1">iPhone</li>
										<li class="ca-li1">Watch</li>
										<li class="ca-li1">Acc</li>
									</ul>
								</div>
								<div class="reg-category reg-ca2">
									<ul class="item-list pt-3" id="item-list2">
									</ul>
								</div>
								<div class="reg-category reg-ca3">
									<ul class="item-list pt-3" id="item-list3">
									</ul>
								</div>
							</div>
							<div class="select-category">
								<label>선택한 판매상품 : </label> <input type="text" name="deviceName" id="deviceName"
									readonly> <input type="text" name="itemName" id="itemName" readonly>
								<input type="text" name="item3" id="item3" readonly>
							</div>
						</div>
					</div>
				</div>
				<div class="row d-flex py-4 bor">
					<div class="col-md-3 d-flex p-reg-left">
						<label for="product-used">중고여부<span>*</span></label>
					</div>
					<div class="col-md-9">
						<div class="d-flex">
							<label for="used" class="cb"> <input id="used"
								type="radio" name="productUsed" value="Y" checked>중고상품
							</label> <label for="new" class="cb"> <input id="new"
								type="radio" name="productUsed" value="N">새상품
							</label>
						</div>
					</div>
				</div>
				<div class="row d-flex py-4 bor">
					<div class="col-md-3 d-flex p-reg-left">
						<label for="product-auction-basic">판매방법<span>*</span></label>
					</div>
					<div class="col-md-9">
						<div class="d-flex aform">
							<label for="auction" class="cb aform"> <input id="auction"
								type="radio" name="productForm" class="radio-value" value="A"
								>경매
							</label> <label for="basic" class="cb aform"> <input id="basic"
								type="radio" name="productForm" class="radio-value" value="B"
								>일반판매
							</label>
						</div>
					</div>
				</div>

				<div id="auctionBox" class="selectBox">
					<div class="row d-flex py-4 bor">
						<div class="col-md-3 d-flex p-reg-left">
							<label for="product-auction-price">즉시입찰가</label>
						</div>
						<div class="col-md-9">
							<input id="product-auction-price" type="number"
								name="immediateBid" class="number form-control mr-1"
								placeholder="숫자만 입력해주세요" step="1000" disabled>원
							&nbsp;&nbsp;&nbsp;&nbsp; <span class="font-italic"></span>
						</div>
					</div>
					<div class="row d-flex py-4 bor">
						<div class="col-md-3 d-flex p-reg-left">
							<label for="product-start-price">경매시작가<span>*</span></label>
						</div>
						<div class="col-md-9">
							<input id="product-start-price" type="number" name="reservePrice"
								placeholder="숫자만 입력해주세요" class="number form-control mr-1"
								step="1000" disabled required>원 &nbsp;&nbsp;&nbsp;&nbsp; <span
								class="font-italic">* 천단위(1000) 입력만 가능합니다</span>
						</div>
					</div>
					<div class="row d-flex py-4 bor">
						<div class="col-md-3 d-flex p-reg-left">
							<label for="auction-date">경매 기간<span>*</span></label>
						</div>
						<div class="col-md-3">
							<input type="text" id="testDatepicker" name="auctionDeadline" disabled required> 까지
						</div>
					</div>
					<div class="row d-flex py-4 bor">
						<div class="col-md-3 d-flex p-reg-left">
							<label for="product-basic-price"></label>가격<span>*</span>
						</div>
						<div class="col-md-9">
							<input id="product-basic-price" type="number" name="basicPrice"
								placeholder="숫자만 입력해주세요" class="number form-control mr-1"
								step="1000" disabled required>원 &nbsp;&nbsp;&nbsp;&nbsp; <span
								class="font-italic">* 천단위(1000) 입력만 가능합니다 (천단위 미만 입력시 올림처리)</span>
						</div>
					</div>
				</div>

				<div class="row d-flex py-4 bor">
					<div class="col-md-3 d-flex p-reg-left">
						<label for="product-msg"></label>설명
					</div>
					<div class="col-md-9">
						<textarea class="form-control" rows="10" name="productComment"
							placeholder="내용을 입력해주세요" style="resize: none"></textarea>
					</div>
				</div>

				<div class="row d-flex py-4 bor reg">
					<div class="col-md-12 text-center">
						<input type="submit" class="btn" value="등록하기">
					</div>
				</div>
			</form>
		</div>
	</div>

	<script>
			$(function(){
		        $(".aform").click(function(){
		            if($("#auction").prop("checked")){
		                $("#product-auction-price, #product-start-price, #testDatepicker").attr("disabled", false);
		                $("#product-basic-price").attr("disabled", true);
		            }else if($("#basic").prop("checked")){
		                $("#product-auction-price, #product-start-price, #testDatepicker").attr("disabled", true);
		                $("#product-basic-price").attr("disabled", false);
		            }
		        });
		        
		    });
			
	        $('.ca-li1').click(function(){
	            var value = $(this).text();
	            $("#deviceName").val(value);
	        });
	        $(document).on("click",".ca-li2",function(){
	            var value = $(this).text();
	            $("#itemName").val(value);
	        });
	        $(document).on("click",".ca-li3",function(){
	            var value = $(this).text();
	            $("#item3").val(value);
	        });
        
            
            $(function() {
                // 실시간 제목 문자길이 검사
                $("#productTitle").on("input", function(){
                        var inputLength = $(this).val().length; // 문자길이 저장
                        $("#counter").html(inputLength);
                    });

                // 가격 천단위 미만 입력시 올림처리 (즉시입력가 제외)
                $('#product-basic-price, #product-start-price').on('change', function() {
                    var n = $(this).val(); 
                    n = Math.ceil(n/1000) * 1000; 
                    $(this).val(n);
                });

                // 파일 선택 버튼이 있는 영역을 보이지 않게함
                $("#fileArea").hide();

                // 이미지 영역 클릭 시 파일 첨부창 띄우기
                $("#titleImgArea").click(function(){
                    $("#img1").click();
                });
                $("#contentImgArea1").click(function(){
                    $("#img2").click();
                });
                $("#contentImgArea2").click(function(){
                    $("#img3").click();
                });
                $("#contentImgArea3").click(function(){
                    $("#img4").click();
                });
                $("#contentImgArea4").click(function(){
                    $("#img5").click();
                });
                
                // 카테고리1
                $(".ca-li1").click(function(){
                	var deviceName = $(this).text();
                	$.ajax({
                        url : "selectCate",
                        data : {deviceName:deviceName},
                        type : "get",
                        dataType : "json",
                        success : function(iList){
                           console.log(iList);
                           $("#item-list2").html("");
                           $.each(iList, function(i) {
                              console.log(iList[i].iList);
                              var $li = $("<li>").prop("class","ca-li2").html(iList[i].iList);
              				  $("#item-list2").append($li);
                           });
                           
                        },
                        error : function() {
                           console.log("ajax 통신 실패");
                        }
                     });
                });
                // 카테고리2
                $(document).on("click",".ca-li2",function(){
                	var itemName = $(this).text();
                	$.ajax({
                        url : "selectInfo",
                        data : {itemName:itemName},
                        type : "get",
                        dataType : "json",
                        success : function(gList){
                           console.log(gList);
                           $("#item-list3").html("");
                           $.each(gList, function(i) {
                              console.log(gList[i].gList);
                              var $li = $("<li>").prop("class","ca-li3").html(gList[i].gList);
              				  $("#item-list3").append($li);
                           });
                           
                        },
                        error : function() {
                           console.log("ajax 통신 실패");
                        }
                     });
                });
                
                
            });

            // 각각의 영역에 파일을 첨부했을 경우 미리 보기가 가능하도록 하는 함수
            function LoadImg(value, num){
                if(value.files && value.files[0]){
                    var reader = new FileReader();

                    reader.onload = function(e){
                        switch(num){
                        case 1 : $("#titleImg").prop("src", e.target.result); break;
                        case 2 : $("#contentImg1").prop("src", e.target.result); break;
                        case 3 : $("#contentImg2").prop("src", e.target.result); break;
                        case 4 : $("#contentImg3").prop("src", e.target.result); break;
                        case 5 : $("#contentImg4").prop("src", e.target.result); break;
                        }
                    }
                    reader.readAsDataURL(value.files[0]);
                }
            }           
            
            
            // submit 동작
            function validate(){
            	// 사진 파일 검사
            	
            	var check = $("#img1").val();
                if(!check){
                    alert("대표 이미지를 등록해주세요");
                    $("#img1").focus();
                    return false;
                }


                var itemCheck = $("#deviceName").val();
                if(!itemCheck){
                    alert("대분류 카테고리를 선택해주세요");
                    $("#item1").focus();
                    return false;
                }
                var itemCheck2 = $("#itemName").val();
                if(!itemCheck2){
                    alert("중분류 카테고리를 선택해주세요");
                    $("#item2").focus();
                    return false;
                }

                var formCheck = $('input[name=productForm]:checked').val();
                if(!formCheck){
                    alert("판매 방법을 선택해주세요");
                    return false;
                }
                
            }
            
            $(function(){
		        $("#testDatepicker").datepicker({minDate : 1,
					 maxDate : 10,
					 dateFormat : 'yy-mm-dd',
					 dayNamesMin : [ '월', '화', '수', '목', '금', '토', '일' ],
					 monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
									'8월', '9월', '10월', '11월','12월' ]
				});
            });
        </script>
        
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	    integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
	    crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" 
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	    
</body>
</html>