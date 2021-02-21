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
	<form action="board_add2" method="post" name="fm" enctype="multipart/form-data" onsubmit="return check()">
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td><input class="form-control" type="text" name="title"  placeholder="게시글 제목"></td>
			</tr>	
			<tr>
				<td><textarea class="form-control" rows="10" cols="100" name="content" placeholder="게시글 내용"></textarea></td>
			</tr>
			<tr>
				<td><input class="form-control" type="file" name="files" multiple="multiple"> 
					<span style="color: gray;">ctrl/shipt버튼을 사용하면 파일을 여러개 첨부할 수 있습니다.</span>
				</td>
			</tr>
			<tr>
				<td><input class="btn btn-primary btn-sm" type="submit" value="게시글 등록" style="float: right;"></td>
			</tr>
		</table>
		<input type="hidden" name="writer_id" value="${sessionScope.member.nickname}">
	</form>
</body>

</html>