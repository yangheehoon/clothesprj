<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 품질을 자랑하는 Clothes</title>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<form action="" name="fm" style="width: 470px; padding: 30px; margin-left: auto; margin-right: auto;">
  <input type="hidden" value="${member.id}" name="id">
	<div class="mb-3">
		<label class="form-label fw-bolder">패스워드 확인</label>
		<input type="password" name="pw" id="ck" class="form-control">
		<span id="msg"></span>
	</div>
	<button type="button" id="del" class="btn btn-primary" style="width: 100%;">회원탈퇴</button>
</form>
</body>
<script type="text/javascript">
		
$("#del").click(function(){
	var query = { pw : fm.pw.value , id : fm.id.value}
	
	$.ajax({
		url : "/member/pwcheck",
		type : "post",
		data : query,
		error : function(){
			alert("통신에러");
		},
		success : function(data){
			if(data=="success") {				
				if(confirm("정말로 탈퇴하시겠습니까?")){
					location.href="/member/del?id="+fm.id.value+"&pw="+fm.pw.value;
				}else{
					alert("회원탈퇴가 취소되었습니다.");
				}
			}else{
				$("#msg").text("패스워드가 일치하지 않습니다").css("color","red")
			}	
		}
	})	
	
})

$("#ck").focus(function(){
	$("#msg").text("").val("");
});
</script>
</html>