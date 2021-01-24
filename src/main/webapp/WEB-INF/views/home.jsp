<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>ClothesHome</title>
</head>
<body>
<c:if test="${member == null}">
	<a href="/member/login">로그인</a>
</c:if>
<c:if test="${member != null}">
	<a href="/member/logout">로그아웃</a>
	<a href="/member/mypage">mypage</a>
</c:if>
<h1 style=" background-color: red; text-align: center;">
	최고의 품질을 자랑하는 Clothes에 오신것을 환영합니다.
</h1>
<a  style="float: right; font-size: x-large;" href="notice/list">공지</a>
<a  style="float: left; font-size: x-large;" href="customer/clotheslist">의류</a>
<br>
<br>
<a  style="float: right; font-size: x-large;" href="customer/boardlist">자유게시판</a>
<a  style="float: left; font-size: x-large;" href="customer/juwelrylist">잡화</a>
<br>
<br>
<a  style=" font-size: x-large;" href="customer/shoeslist">신발</a>

</body>
</html>
