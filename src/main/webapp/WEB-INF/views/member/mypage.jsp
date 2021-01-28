<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 품질을 자랑하는 Clothes</title>
</head>
<body>
<table>
<tr>
	<td>닉네임</td>
	<td>${member.nickname}</td>
</tr>
<tr>
	<td>이름</td>
	<td>${member.name}</td>
</tr>
<tr>
	<td>생년원일</td>
	<td>${member.birth}</td>
</tr>
<tr>
	<td>이메일</td>
	<td>${member.email}</td>
</tr>
<tr>
	<td>성별</td>
	<td>${member.gender}</td>
</tr>
<tr>
	<td>전화번호</td>
	<td>${member.phone_num}</td>
</tr>
</table>
<button type="button">회원정보 수정</button>
<button type="button" onclick="pwchange()">패스워드 변경</button>
<button type="button" onclick="del()">회원탈퇴</button>
</body>
<script type="text/javascript">
function del(){
	location.href="/member/delcheck";
}
function pwchange(){
	location.href="/member/pwchange";
}
</script>
</html>