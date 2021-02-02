<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 추가</title>
<script type="text/javascript">
function check() {
	if(fm.title.value=="") {
		alert("제목을 입력해주세요");
		return false;
	}else if(fm.content.value=="") {
		alert("내용을 입력해주세요");
		return false;
	}
	return true;
}
</script>
</head>

<body>
	<form action="board_add2" name="fm" onsubmit="return check()">
		<table>
			<tr>
				<th>게시글 제목:</th>
				<td><input type="text" name="title"></td>
			</tr>	
			<tr>
				<th>게시글 내용:</th>	
				<td><textarea rows="10" cols="100" name="content"></textarea></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="file" name="files"></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="게시글 등록" style="float: right;"></td>
			</tr>
		</table>
		<input type="hidden" name="writer_id" value="${sessionScope.member.nickname}">
	</form>
</body>

</html>