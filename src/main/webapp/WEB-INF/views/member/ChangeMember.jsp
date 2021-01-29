<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 품질을 자랑하는 Clothes</title>
<script type="text/javascript">
function CheckInfo(){
	if(fm.nick.value==""){
		alert("닉네임을 입력해주세요.");
		return false;
	}else if(fm.email.value==""){
		alert("이메일을 입려해주세요.");
		return false;
	}else if(fm.phone.value==""){
		alert("전화번호를 입력해주세요.");
		return false;
	}else if (isNaN(fm.phone.value)) {
		alert("전화번호는 숫자만 입력할 수 있습니다");
		return false;
	}	
	return true;	
}
</script>
</head>
<body>
<form action="" name="fm" onsubmit="return CheckInfo()">
  <input type="hidden" name="id" value="${member.id }">
  <input type="hidden" name="pw" value="${member.pw }">
	<table>	
	<tr>
		<td>닉네임</td>
		<td><input type="text" name="nick" value="${member.nickname }"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" value="${member.email }"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="phone" value="${member.phone_num}"></td>
	</tr>
	</table>
	<input type="submit" value="회원정보 변경">
</form>
</body>
</html>