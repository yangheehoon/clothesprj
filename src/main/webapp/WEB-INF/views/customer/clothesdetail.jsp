<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="c" value="${clothes}" />

<div style="float: left;">
<img src="/resources/customer/clothes/${c.files}" style="width: 500px; height: 500px;" ></td>
</div>
<h3>${c.name}</h3>
<span>${c.price}원</span>
<div>
	<ul>
		<li style="margin-bottom: 30px;">
			<div>색상</div>
			<ul>
				<li style="display: inline-block;"><img src="/resources/color/black.png" style="width: 50px; height: 50px;"></li>
				<li style="display: inline-block;"><img src="/resources/color/gray.png" style="width: 50px; height: 50px;"></li>
				<li style="display: inline-block;"><img src="/resources/color/ivory.jpg" style="width: 50px; height: 50px;"></li>
			</ul>
		</li>
		<li>
			<div>사이즈</div>
			<select>
				<option>사이즈를 선택해주세요.</option>
				<option>90</option>
				<option>95</option>
				<option>100</option>
				<option>105</option>
				<option>110</option>
				<option>115</option>
			</select>
		</li>
	</ul>
</div>
	
<div style="border-bottom-width: 1px; border-bottom-color: black; border-bottom-style: solid;
border-top-width: 1px; border-top-color: gray; border-top-style: solid; padding: 10px;"></div>

<h4>총 결제금액</h4>

<ul>
	<li style="display: inline-block;"><button>장바구니</button></li>
	<li style="display: inline-block;"><button>결제하기</button></li>	
</ul>

</body>
</html>