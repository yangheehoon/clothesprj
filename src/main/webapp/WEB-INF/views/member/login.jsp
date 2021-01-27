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

<form name="fm">
	아이디:<input type="text" name="id">
	패스워드:<input type="password" name="pw">
	<button type="button" class="logincheck">로그인</button>
</form>
<span id="msg"></span>
</body>
<script type="text/javascript">
$(".logincheck").click(function(){
	var query = { id : fm.id.value, pw : fm.pw.value };
	
	$.ajax({
		url : "/member/logincheck",
		type : "post",
		data : query,
		error : function(){
			alert("통신에러");
		},
		success : function(data){			
			if(data=="idnone"){
				$("#msg").text("존재하지 않는 아이디입니다");	
			}else if(data=="pwfail"){
				$("#msg").text("비밀번호가 일치하지 않습니다");
			}else{
				location.href="/home";	
			}			
		}
	})
})
</script>
</html>