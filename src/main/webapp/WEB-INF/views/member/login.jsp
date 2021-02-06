<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css">
<title>최고의 품질을 자랑하는 Clothes</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<form name="fm" style="width: 470px; padding: 30px; margin-left: auto; margin-right: auto;">
	<div class="mb-3">
		<label class="form-label fw-bolder">ID</label>
		<input type="text" name="id" class="form-control">
		<span id="msg1"></span>
	</div>
	<div class="mb-3">
		<label class="form-label fw-bolder">Password</label>
		<input type="password" name="pw" class="form-control">
		<span id="msg2"></span>
	</div>
	<button type="button" id="logincheck" class="btn btn-primary" style="width: 100%;">로그인</button> 
</form>

</body>
<script type="text/javascript">
$("#logincheck").click(function(){
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
				$("#msg1").text("존재하지 않는 아이디입니다").css("color","red");
				$("#msg2").text("");
			}else if(data=="pwfail"){
				$("#msg1").text("");	
				$("#msg2").text("비밀번호가 일치하지 않습니다").css("color","red");
			}else{
				location.href="/home";	
			}			
		}
	})
})
</script>
</html>