<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 추가</title>
</head>

<body>
	<form action="add2" >
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
				<td><input type="submit" value="공지글 등록" style="float: right;"></td>
			</tr>
		</table>
	</form>
</body>

</html>