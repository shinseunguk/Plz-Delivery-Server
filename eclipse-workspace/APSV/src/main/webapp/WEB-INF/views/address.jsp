<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
    * {
        margin:0;
        padding:0;
    }
    html {
        width: 100%;
        height : 100%;
        overflow-y:hidden;
    }
    body {
        width : 100%;
        height : 100%;
        overflow : hidden;
    }
    iframe {
        overflow : hidden;
    }
</style>
<head>
</head>
<body>
	<?php
	 header("Content-Type: text/html; charset=UTF-8"); 
	 ?>
	<!-- <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
	<!-- <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> -->
	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript"> 
	new daum.Postcode({
		oncomplete: function(data) {
			if(data.userSelectedType=="R"){
				// userSelectedType : 검색 결과에서 사용자가 선택한 주소의 타입
				// return type : R - roadAddress, J : jibunAddress 
				// TestApp 은 안드로이드에서 등록한 이름 
				window.launchdinner.setAddress(data.zonecode, data.roadAddress, data.buildingName);
			var address = data.roadAddress
			} else{
				window.launchdinner.setAddress(data.zonecode, data.jibunAddress, data.buildingName); 
			address = data.jibunAddress
				}
		 },
		  width : '100%',
		  height : '100%',
		 
		  }).embed(document.body);
	</script>
</body>

</html>

