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
	<form action="join" name="fm" onsubmit="return check()" style="width: 470px; padding: 30px; margin-left: auto; margin-right: auto;">
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder" style="width: 100%;">아이디</label>
				<input type="text" name="id" id="userid" class="form-control form-control-sm" style="width: 63.6%; display: inline-block;">
				<button type="button" id="idcheck" class="btn btn-primary">아이디 중복검사</button>
				<span class="msg"></span>
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">패스워드</label>
				<input type="password" name="pw" class="form-control form-control-sm">
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">패스워드 확인</label>
				<input type="password" name="pw2" class="form-control form-control-sm">
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">닉네임</label>
				<input type="text" name="nick" class="form-control form-control-sm">
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">이름</label>
				<input type="text" name="name" class="form-control form-control-sm">
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">생년원일</label>
				<input type="text" name="birth" class="form-control form-control-sm">
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">이메일</label>
				<input type="email" name="email" class="form-control form-control-sm">
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">성별</label>
				<select name="gen" class="form-select form-select-sm">
						<option>성별</option>
						<option>남자</option>
						<option>여자</option>
				</select>
			</div>
			<div class="mb-3">
				<label class="col-form-label-sm fw-bolder">전화번호</label>
				<input type="text" name="phone" id="ph" class="form-control form-control-sm">
			</div>
		<input type="submit" value="회원가입" class="btn btn-primary" style="width: 100%;">
	</form>

</body>

<script type="text/javascript">

$("#idcheck").click(function(){
	
	var query = { id : $("#userid").val()};
	
	if($("#userid").val() != "" && idcheckSpecial() != true){
	
	$.ajax({
		url : "/member/idcheck",
		type : "post",
		data : query,
		error : function(){
			alert("ajax 통신에러");
		},
		success : function(data){
			
			if(data=="idnone"){
				$(".msg").text("사용가능한 아이디입니다").css("color","blue").val("complete");
			}else{
				$(".msg").text("이미존재하는 아이디입니다")
				.css("color","red").val("")
			}
		}
	});
	
	}else if($("#userid").val() == ""){
		alert("아이디를 입력해주세요");
	}else if(idcheckSpecial() == true){
		alert("특수문자 또는 공백은 포함할 수 없습니다");
	}
	
});

$("#userid").focus(function(){
	$(".msg").text("").val("");
	//$("#userid").val("");
})

function idcheckSpecial() {  //아이디값 특수문자와 공백 체크
	var pattern = /[`~!@#$%^&*|\\\'\";:\/?\s]/gi; 
	
	if(pattern.test(fm.id.value) == true) { 		
			return true;
	}else { 
		    return false;
	} }


function check() {
	if (fm.id.value=="") {
		alert("아이디를 입력해주세요");
		return false;
	}else if(idcheckSpecial()==true){
		alert("특수문자 또는 공백은 포함할 수 없습니다");
		return false;
	}else if ($(".msg").val()=="") {
		alert("아이디 중복검사를 해주세요");
		return false;
	}else if(fm.pw.value.length < 8){
		alert("패스워드를 8자리 이상 입력해주세요");
		return false;
	}else if (fm.pw2.value=="") {
		alert("패스워드 확인을 입력해주세요");
		return false;
	}else if(fm.pw.value!=fm.pw2.value){
		alert("패스워드가 일치하지 않습니다");
		return false;
	}else if (fm.nick.value=="") {
		alert("닉네임을 입력해주세요");
		return false;
	}else if (fm.name.value=="") {
		alert("이름을 입력해주세요");
		return false;
	}else if (fm.birth.value.length !=6) {
		alert("생년월일 6자리를 입력해주세요");
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
		$("#ph").val(fm.phone.value.replace(/\-/g,""));
		return false;
	}
		return true;
}

</script>

</html>