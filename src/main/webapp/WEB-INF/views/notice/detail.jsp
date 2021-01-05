<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자세한 페이지</title>
<style>
th.solid {padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;}
    
td.solid {padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;}
</style>
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


<details>
	<summary style="color: blue">댓글</summary>
		<form action="">
		<textarea rows="5" cols="50" onclick="if(this.value=='내용을 입력하세요.'){this.value=''}" >내용을 입력하세요.</textarea>
		<input type="submit" value="댓글달기">
		</form>
	<table >
	<c:forEach var="c" items="${cmtlist}">
 		
 		<tr>
			<th > <br> </th>
			<td > <br> </td>
		</tr>
 		<tr style="font-size: small;">
 			<th>작성자	</th>
			<td>${c.writer_id}</td>
		</tr>
		<tr>
			<th>내용	</th>
			<td>${c.content }</td>
		</tr>
		<tr style="font-size: small;">
			<th >작성일	</th>
			<td >${c.regdate }</td>
		</tr>
		<tr style="font-size: small;">
			<td class="solid"></td>
			<th class="solid"><details>
				<summary style="color: blue">답글달기</summary>
				<p>아직안만듬</p>
			</details></th>
		</tr>
		
	</c:forEach>

 	</table>
 
 	</details>	

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