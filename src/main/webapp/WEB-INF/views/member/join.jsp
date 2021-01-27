<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 품질을 자랑하는 Clothes</title>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script> 

</head>
<body>
	<form action="join" name="fm" onsubmit="return check()">
		<table>
			<tr>				
				<td>아이디</td>
				<td><input type="text" name="id" id="userid"></td>
				<td><button type="button" class="idcheck">아이디 중복체크</button></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr>				
				<td>패스워드</td>
				<td><input type="text" name="pw"></td>
			</tr>
			<tr>				
				<td>닉네임</td>
				<td><input type="text" name="nick"></td>
			</tr>
			<tr>				
				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>				
				<td>생년원일</td>
				<td><input type="text" name="birth"></td>
			</tr>
			<tr>				
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>				
				<td>성별</td>
				<td><select name="gen">
						<option>성별</option>
						<option>남자</option>
						<option>여자</option>
					</select></td>
			</tr>
			<tr>				
				<td>전화번호</td>
				<td><input type="text" name="phone"></td>
			</tr>				
		</table>
		<input type="submit" value="회원가입">
	</form>

</body>

<script type="text/javascript">

$(".idcheck").click(function(){
	
	var query = { id : $("#userid").val()};
	
	if($("#userid").val() != ""){
	
	$.ajax({
		url : "/member/idcheck",
		type : "post",
		data : query,
		error : function(){
			alert("ajax 통신에러");
		},
		success : function(data){
			
			if(data=="1"){
				$(".msg").text("사용가능한 아이디입니다").css("color","blue").val("complete");
			}else{
				$(".msg").text("이미존재하는 아이디입니다")
				.css("color","red").val("")
			}
		}
	});
	
	}else{
		alert("아이디를 입력해주세요");
	}
	
});

$("#userid").click(function(){
	$(".msg").text("").val("");
	//$("#userid").val("");
})


function check() {
	if (fm.id.value=="") {
		alert("아이디를 입력해주세요");
		return false;
	}else if ($(".msg").val()=="") {
		alert("아이디 중복검사를 해주세요");
		return false;
	}else if (fm.pw.value=="") {
		alert("패스워드를 입력해주세요");
		return false;
	}else if (fm.nick.value=="") {
		alert("닉네임을 입력해주세요");
		return false;
	}else if (fm.name.value=="") {
		alert("이름을 입력해주세요");
		return false;
	}else if (fm.birth.value=="") {
		alert("생년월일을 입력해주세요");
		return false;
	}else if (isNaN(fm.birth.value)) {
		alert("생년월일은 숫자만 입력할 수 있습니다");
		return false;
	}else if (fm.email.value=="") {
		alert("이메일을 입력해주세요");
		return false;
	}else if (fm.gen.value=="성별") {
		alert("성별을 선택해주세요");
		return false;
	}else if (fm.phone.value=="") {
		alert("전화번호를 입력해주세요");
		return false;
	}else if (isNaN(fm.phone.value)) {
		alert("전화번호는 숫자만 입력할 수 있습니다");
		return false;
	}
		return true;
}
</script>

</html>