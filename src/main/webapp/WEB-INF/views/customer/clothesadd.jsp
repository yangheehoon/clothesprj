<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의류목록 추가</title>
</head>
<body>
<form action="clothesadd2">	
	<table>
		<tr>
			<th>의류명:</th>
			<td><input type="text" name="clothesname"></td>
		</tr>
		<tr>
			<th>가격:</th>
			<td><input type="text" name="clothesprice"></td>
		</tr>
		<tr>
			<th>상세설명:</th>
			<td><textarea rows="10" cols="100" name="clothesdescription"></textarea></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="file" name="clothesfiles"></td>
		</tr>
		<tr>
			<td></td>
			<td><input style="float: right;" type="submit" value="의류 추가"></td>
		</tr>
	</table>
</form>
</body>
</html>