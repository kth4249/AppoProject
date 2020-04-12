<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Import API 일반결제 테스트</title>

<!-- STEP1아임포트 라이브러리 추가하기 -->
<!-- jQuery -->
<!--  <script src="https://code.jquery.com/jquery-1.12.4.min.js" ></script> -->
<script type="text/javascript"src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

</head>
<body>
	<table>
		<tr>
			<th>주문명</th>
			<td><input type="text" id="name" required></td>
		<tr>
		<tr>
			<th>결제할 금액</th>
			<td><input type="number" id="amount" required></td>
		<tr>
		<tr>
			<th>주문자명</th>
			<td><input type="text" id="buyer_name" required></td>
		<tr>
		<tr>
			<th>주문자 Email</th>
			<td><input type="email" id="buyer_email" required></td>
		<tr>
		<tr>
			<th>주문자 연락처</th>
			<td><input type="tel" id="buyer_tel" required></td>
		<tr>
		<tr>
			<th>주문자 우편번호</th>
			<td>
				<input type="text" id="buyer_postcode" class="form-control postcodify_postcode5" required>
				<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
			</td>
		<tr>
		<tr>
			<th>주문자 주소</th>
			<td><input type="text" id="buyer_addr1" class="form-control postcodify_address" required></td>
		<tr>
		<tr>
			<th>주문자 상세 주소</th>
			<td><input type="text" id="buyer_addr2" class="form-control postcodify_details" required></td>
		<tr>
	</table>
	<button id="btn">결제</button>
    <!-- jQuery와 postcodify 를 로딩한다. -->
    <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
    <script>
        // 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
        $(function(){
            $("#postcodify_search_button").postcodifyPopUp();
        });
    </script>
    
    <script>
		// 결제 버튼 클릭 시
		$("#btn").on("click",function(){
			var name = $("#name").val();
			var amount = $("#amount").val();
			var buyer_name = $("#buyer_name").val();
			var buyer_email = $("#buyer_email").val();
			var buyer_tel = $("#buyer_tel").val();
			var buyer_postcode = $("#buyer_postcode").val();
			var buyer_addr = $("#buyer_addr1").val() + " " + $("#buyer_addr2").val();
			
			var merchant_uid;
			
			$.ajax({
				url : "test/insertOrder",
				type : "post",
				data : {
					name : name,					// 주문명
					amount: amount,					// 결제할 금액
					buyer_email: buyer_email,		// 주문자 Email
					buyer_name: buyer_name,	 		// 주문자명 
					buyer_tel: buyer_tel,			// 주문자 연락처
					buyer_addr: buyer_addr,			// 주문자 주소 (선택항목)
					buyer_postcode: buyer_postcode	// 주문자 우편번호
				},
				success : function(merchant_uid){
					alert(merchant_uid);
					
					if(merchant_uid != ""){
						payment(name, amount, buyer_email, buyer_name, buyer_tel, buyer_addr, buyer_postcode, merchant_uid);
					}else{
						alert("실패");
					}
				},
				
				error : function(){
					alert("ajax 통신 실패");
				}
				
			});
			
		});
		
		function payment(name, amount, buyer_email, buyer_name, buyer_tel, buyer_addr, buyer_postcode, merchant_uid){
		
			// STEP2 가맹점 식별하기
			// IMP 객체의 init 함수의 인자에 가맹점 식별코드를 삽입하고 웹사이트의 결제 페이지에서 호출합니다.
	
			var IMP = window.IMP; // 생략해도 괜찮습니다.
			IMP.init("imp60474063"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.
			
			// STEP3결제창 호출코드 추가하기
			// IMP.request_pay(param, callback)을 호출합니다. 함수의 첫번째 인자인 param에 결제 요청에 필요한 속성과 값을 담습니다
			// IMP.request_pay(param, callback) 호출
			IMP.request_pay({ // param
				// param 객체 속성
				// https://docs.iamport.kr/tech/imp#param
				
				// pg : pg사 선택
				pg: "kakaopay", // (이니시스웹표준)
				
				// 결제 수단
				pay_method: "card", // (신용카드)
				
				// 가맹점에서 생성/관리하는 고유 주문번호 (필수항목)
				// 결제가 된 적이 있는 merchant_uid로는 재결제 불가
				// script 마지막 설명 참고 
				merchant_uid: merchant_uid,
				
				// 주문명
				name: name,  //(주문명 입력값)
				
				// 결제할 금액
				amount: amount,	// 결제할 금액 입력값
				
				// 주문자 Email (선택항목)
				buyer_email: buyer_email,
				
				// 주문자명 (선택항목)
				buyer_name: buyer_name,
				
				// 주문자 연락처 (선택항목)
				buyer_tel: buyer_tel
				
			}, function (rsp) { // callback
				/*
					- callback함수의 rsp객체
					IMP.request_pay()함수의 두번째 인자에는 JavaScript 함수를 지정합니다. 
					이를 callback함수라고 부르며, 결제 프로세스 완료 후 호출되는 함수입니다. 
					해당 함수가 호출될 때 결제 결과의 정보를 담고있는 rsp객체를 인자로써 가집니다. 
					해당 객체를 통해 결제 성공여부, 결제 에러 메시지, 결제 승인 시각 등의 정보를 확인할 수 있습니다.
				*/
				
			  	if (rsp.success) {  // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
				//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid,merchant_uid 전달하기
					$.ajax({
						url: "test/insertImpUid", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
						type: 'POST',
						dataType: 'json',
						data: {
							imp_uid : rsp.imp_uid,			// 아임포트 거래 고유 번호
							merchant_uid: rsp.merchant_uid//,	// 가맹점에서 생성/관리하는 고유 주문번호
							//name : name,					// 주문명
							//amount: amount,					// 결제할 금액
							//buyer_name: buyer_name,	 		// 주문자명 
							//buyer_email: buyer_email,		// 주문자 Email
							//buyer_tel: buyer_tel,			// 주문자 연락처
							//buyer_postcode: buyer_postcode,	// 주문자 우편번호
							//buyer_addr: buyer_addr			// 주문자 주소 (선택항목)
							//기타 필요한 데이터가 있으면 추가 전달
						}
					}).done(function(data) {
						//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
						if ( everythings_fine ) {
							var msg = '결제가 완료되었습니다.';
							/* msg += '\n고유ID : ' + rsp.imp_uid;
							msg += '\n상점 거래ID : ' + rsp.merchant_uid;
							msg += '\결제 금액 : ' + rsp.paid_amount;
							msg += '카드 승인번호 : ' + rsp.apply_num; */
					
							alert(msg);
						} else {
							//[3] 아직 제대로 결제가 되지 않았습니다.
							//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
						}
					});  
				 } else {
					 alert("결제 실패");
				 }
			});
		}
		/*
		주문번호(merchant_uid) 생성하기
		IMP.request_pay를 호출하기 전에 여러분의 서버에 주문 정보를 전달(데이터베이스에 주문정보 INSERT)하고 서버가 생성한 주문 번호를 param의 merchant_uid속성에 지정하기를 권장드립니다. 
		결제 완료 후 결제 위변조 여부를 검증하는 단계에서 신뢰도있는 검증을 위해 여러분의 서버에서 주문정보를 조회해야 하기 때문입니다.
		*/
    </script>
	
	
</body>
</html>