<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 품질을 자랑하는 Clothes</title>
<script type="text/javascript">
function del() {
	location.href="delcart"
}
</script>
</head>
<body>

	<table border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>주문상품정보</th>
			<th>수량</th>
			<th>가격</th>
		</tr>
		<c:forEach var="n" items="${nologin}">
		<tr>
			<td><div style="float: left;"><a href="clothesdetail?num=${n.num}"><img src="/resources/customer/clothes/${n.files}" style="width: 225px; height: 225px;"></a></div>
				<div style="display: inline-block; margin-top: 70px;"><a href="clothesdetail?num=${n.num}">${n.description}</a></div>
				<div>${n.name}</div>
				<div>${n.color}</div>
				<div>${n.size}</div></td>
			<td>1</td>
			<td>${n.price}원</td>
		</tr>
		</c:forEach>
	</table> 
	<%-- ${cookie.ck1.value}
	${cookie.ck2.value}
	${cookie.ck3.value} --%>
<br>
    <%-- ${cks}
    ${cks[0]}
    ${cks[1]} --%>

<div align="right" style="margin-right: 300px;">
	<button style="color: white; background-color: purple; padding: 10px; font-size: 20px; border-radius: 10px; border: 0px; cursor: pointer; outline: 0px;" onclick="del()">장바구니 비우기</button>
	<button style="color: white; background-color: purple; padding: 10px; font-size: 20px; border-radius: 10px; border: 0px; cursor: pointer; outline: 0px;">결제하기</button>
</div>


</body>
</html>