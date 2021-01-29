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
<form action="" name="fm">
<input type="hidden" value="${member.id}" name="id">
	패스워드 확인 : <input type="password" name="pw" id="ck">
	<button type="button" class="del">회원탈퇴</button>
	<span id="msg"></span>
</form>
</body>
<script type="text/javascript">
		
$(".del").click(function(){
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
				$("#msg").text("패스워드가 일치하지 않습니다")
			}	
		}
	})	
	
})

$("#ck").focus(function(){
	$("#msg").text("").val("");
});
</script>
</html>