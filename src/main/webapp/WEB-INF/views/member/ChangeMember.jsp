<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css">
<title>최고의 품질을 자랑하는 Clothes</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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

function fileUpload(f) {

	  var fread = new FileReader();

	  fread.onload = function(e){

		$('#my_img').attr('src',e.target.result);

	  }

	fread.readAsDataURL(f.files[0]);

	}
</script>
<style type="text/css">

.filebox input[type="file"] {
  position: absolute; /* 파일태그의 공간이 남아있기에 절대위치를 지정해 폼의 공간에서 배제시킨다 */  
  width: 0px;
  height: 0px;
  padding: 0;
  overflow: hidden;  /* 사실상 파일태그의 공간이 0이기에 감출 것이없다 */
}
</style>
</head>
<body>
<form action="" method="post" name="fm" enctype="multipart/form-data" onsubmit="return CheckInfo()" style="width: 470px; padding: 30px; margin-left: auto; margin-right: auto;">
  <input type="hidden" name="id" value="${member.id }">
  <input type="hidden" name="pw" value="${member.pw }">
  	<div class="mb-3" style="text-align: center;">
		<img src="/resources/member/${member.pro_file}" id="my_img" style="width: 200px; height: 200px; border: 1px solid #dee2e6;">		
		<div class="filebox mt-2">
  			<label for="profileImg" class="btn btn-primary btn-sm">이미지 변경</label>
			<input type="file" id="profileImg" name="profileImg" onchange="fileUpload(this);">
		</div>
	</div>		
  	<div class="mb-3">
		<label class="form-label fw-bolder">닉네임</label>
		<input type="text" name="nick" value="${member.nickname }" class="form-control">
	</div>
	<div class="mb-3">
		<label class="form-label fw-bolder">이메일</label>
		<input type="text" name="email" value="${member.email }" class="form-control">
	</div>
	<div class="mb-3">
		<label class="form-label fw-bolder">전화번호</label>
		<input type="text" name="phone" value="${member.phone_num}" class="form-control">
	</div>
	<input type="submit" value="회원정보 변경" class="btn btn-primary" style="width: 100%;">
</form>
</body>
</html>