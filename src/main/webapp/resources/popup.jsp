<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function move() {
	window.opener.location.href="/customer/cart"
	window.close();
}

</script>
</head>
<body>
<h3 style="text-align: center;">장바구니에 추가되었습니다.</h3>
<ul style="text-align: center; padding: 0px;">
	<li style="display: inline-block;">
		<button onclick="move()" style="padding: 10px; font-size: 20px; background-color: #ff0099; color: white; border-radius: 15px; outline: 0px; width: 200px; cursor: pointer;">장바구니로 이동</a></button>
	</li>
	<li style="display: inline-block;">
		<button onclick="window.close()" style="padding: 10px; font-size: 20px; background-color: purple; color: white; border-radius: 15px; outline: 0px; width: 200px; cursor: pointer;">쇼핑 계속하기</button>
	</li>
</ul>
</body>
</html>