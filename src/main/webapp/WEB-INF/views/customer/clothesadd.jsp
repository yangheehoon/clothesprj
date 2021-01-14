<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의류목록 추가</title>
<script type="text/javascript">
function check() {
	if (fm.n.value=="") {
		alert("이름을 입력해주세요");
		return false;
	}else if (fm.p.value=="") {
		alert("가격을 입력해주세요");
		return false;
	}else if (isNaN(fm.p.value)) {
		alert("가격에는 숫자만 입력할 수 있습니다");
		return false;
	}else if (fm.d.value=="") {
		alert("상세설명을 입력해주세요");
		return false;
	}else if (fm.f.value=="") {
		alert("이미지 파일을 첨부해주세요");
		return false;
	}
		return true;
}
</script>
</head>
<body>
<form action="clothesadd2" name="fm" method="post" enctype="multipart/form-data" onsubmit="return check()">	
	<table>
		<tr>
			<th>의류명:</th>
			<td><input type="text" name="n"></td>
		</tr>
		<tr>
			<th>가격:</th>
			<td><input type="text" name="p"></td>
		</tr>
		<tr>
			<th>상세설명:</th>
			<td><textarea rows="10" cols="100" name="d"></textarea></td>
		</tr>
		<tr>
			<th>이미지:</th>
			<td><input type="file" name="f"></td>
		</tr>
		<tr>
			<td></td>
			<td><input style="float: right;" type="submit" value="의류 추가"></td>
		</tr>
	</table>
</form>
</body>
</html>