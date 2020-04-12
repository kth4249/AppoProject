<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appo_nav</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css" type="text/css">
</head>
<body>
<nav class="site-header2">
        <form>
            <div class="container justify-content-between py-2">
                <span class="py-2 d-none d-md-inline-block" id="quotetxt1">시세확인</span>
                <div class="form-group mb-0" id="quote">
                	<select class="option exampleSelect" id="exampleSelect1">
                        <option>기기 선택</option>
                        <option value="Mac">Mac</option>
                        <option value="iPhone">iPhone</option>
                        <option value="iPad">iPad</option>
                        <option value="Watch">Watch</option>
                        <option value="Acc">Acc</option>
                    </select>
                	<select class="option exampleSelect" id="exampleSelect2">
                    </select>
                    <select class="option exampleSelect" id="exampleSelect3">
                   	</select>
                </div>
                <span class="py-2 d-none d-md-inline-block" id="quotetxt2">&nbsp;</span>
            </div>
        </form>

    </nav>
<script>
$(function(){
	$("#exampleSelect1").change(function(){
		var deviceName = $("option:selected").val();
		
		$.ajax({
			url : "<%=request.getContextPath()%>/product/selectCate",
			data : {deviceName:deviceName},
			type : "get",
			dataType : "json",
			success : function(iList){
				$("#exampleSelect2").html("");
				$("#exampleSelect2").append('<option>기종선택</option>');
				$.each(iList, function(i){
					//console.log(iList[0].iList);
					var $option = $('<option>').prop("class","itemName").text(iList[i].iList);
					$("#exampleSelect2").append($option);
				});
			},
			error : function() {
				console.log("ajax 통신 실패");
			}
		});
	});
    $(document).on("click","#exampleSelect2",function(){
		var itemName = $("#exampleSelect2 option:selected").text();
		
		$.ajax({
			url : "<%=request.getContextPath()%>/product/selectInfo",
			data : {itemName:itemName},
			type : "get",
			dataType : "json",
			success : function(gList){
				//console.log(gList);
				$("#exampleSelect3").html("");
				$("#exampleSelect3").append('<option>용량선택</option>');
				$.each(gList, function(i){
					var $option = $('<option>').prop("class","itemInfo").text(gList[i].gList);
					$("#exampleSelect3").append($option);
				});
			},
			error : function() {
				console.log("ajax 통신 실패");
			}
		});
	});
    $(document).on("click","#exampleSelect3",function(){
		var itemInfo = $("#exampleSelect3 option:selected").text();
		var itemName = $("#exampleSelect2 option:selected").text();
		
		$.ajax({
			url : "<%=request.getContextPath()%>/product/selectPrice",
			data : {itemInfo:itemInfo, itemName:itemName},
			type : "get",
			dataType : "json",
			success : function(mpList){
				console.log(mpList);
				$("#quotetxt2").html("&nbsp");
				$.each(mpList, function(i){
					$("#quotetxt2").append(mpList[i].mpList);
				});
			},
			error : function() {
				console.log("ajax 통신 실패");
			}
		});
	});
});

</script>
</body>
</html>