<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css">
<title>최고의 품질을 자랑하는 Clothes</title>
</head>
<body>
<div style="text-align: center;">
	<!-- <form action=""> -->
	<img src="/resources/member/${member.pro_file}" style="width: 200px; height: 200px; border: 1px solid #dee2e6;">	
	<br><!-- 
	<input type="file" id="profileImg" name="profileImg">
							  <input type="submit" name="업로드" value="이미지변경" id="upimg">
	<input type="submit" name="업로드" value="이미지변경" id="upimg"
								disabled="disabled" class="btn btn-primary"> -->						
	<!-- </form> -->
</div>			
<div style="width: 470px; padding: 30px; margin-left: auto; margin-right: auto; ">		
<table class="table">
<tr>
	<th>닉네임</th>
	<td>${member.nickname}</td>
</tr>
<tr>
	<th>이름</th>
	<td>${member.name}</td>
</tr>
<tr>
	<th>생년원일</th>
	<td>${member.birth}</td>
</tr>
<tr>
	<th>이메일</th>
	<td>${member.email}</td>
</tr>
<tr>
	<th>성별</th>
	<td>${member.gender}</td>
</tr>
<tr>
	<th>전화번호</th>
	<td>${member.phone_num}</td>
</tr>
</table>
</div>

<div style="text-align: center;">
	<button type="button" onclick="ChangeMember()" class="btn btn-primary">회원정보 수정</button>
	<button type="button" onclick="ChangePw()" class="btn btn-primary">패스워드 변경</button>
	<button type="button" onclick="CheckDel()" class="btn btn-primary">회원탈퇴</button>
</div>

</body>
<script type="text/javascript">
function ChangeMember(){
	location.href="/member/ChangeMember"
}
function ChangePw(){
	location.href="/member/ChangePw";
}
function CheckDel(){
	location.href="/member/CheckDel";
}
</script>
</html>