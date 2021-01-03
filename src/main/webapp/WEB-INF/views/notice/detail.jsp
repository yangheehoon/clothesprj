<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자세한 페이지</title>
</head>
<body>

<table border="1">
  <c:set var="d" value="${detail}"/>
    <tr>
		<th>게시글 넘버</th>
		<td>${d.num}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${d.title}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${d.writer_id}</td>
	</tr>
	<tr>
		<th>게시글 내용</th>
		<td>${d.content}</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<c:forTokens var="filename" items="${d.files }" delims="," varStatus="st">
			<c:set var="style" value=""/>
			<c:if test="${fn:endsWith(filename , '.zip')}">
				<c:set var="style" value="font-weight : bold; color: red; " />
			</c:if>
			<c:if test="${fn:endsWith(filename, '.png') }">
				<c:set var="style" value="font-weight : bold; color: blue;"/>
			</c:if>
			<c:if test="${fn:endsWith(filename , '.gif') }">
				<c:set var="style" value="font-weight : bold; color : green;"/>
			</c:if>
			<a href="${filename}" style="${style}">${filename}</a>
			<c:if test="${!st.last }">
			/
			</c:if>
			</c:forTokens>
		</td>	
	</tr>
	<tr>
		<th>조회수</th>
		<td>${d.hit}</td>
	</tr>
</table>

<div>
	<a href="list">목록</a>
</div>

<table border="1">
    <tr>
		<th>이전글</th>
		<c:if test="${empty prevdetail}">
			<td>
				이전글이 없습니다.
			</td>
		</c:if>
		
		<c:if test="${!empty prevdetail}">
		<td><a href="detail?num=${prevdetail.num}">
			${prevdetail.content}</a>
		</td>
		</c:if>
	</tr>
	
	<tr>
		<th>다음글</th>
		<c:if test="${empty nextdetail}">
			<td>
				다음글이 없습니다.
			</td>
		</c:if>
	
		<c:if test="${!empty nextdetail}">
			<td><a href="detail?num=${nextdetail.num }">
				${nextdetail.content}</a>
			</td>
		</c:if>
	</tr>
</table>


</body>
</html>