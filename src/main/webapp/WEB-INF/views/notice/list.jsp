<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
</head>
<body>
	<h3>공지사항 목록</h3>
	<!-- <h3>공지사항 검색폼</h3> -->
	
	<form action="">
		<fieldset>
			
		<legend>공지사항 검색 필드</legend>

		<label>검색분류</label> 
		<select name="f">
			<option ${(param.f == "title") ? "selected":""} value="title">제목</option>
			<option ${(param.f == "writer_id") ? "selected":""} value="writer_id">작성자</option>
		</select>
		<label>검색어</label> 
		<input type="text" name="q" value="${param.q}" /> 
		<input type="submit" value="검색" />
 		
 		</fieldset>  
	</form>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>

		</tr>

		<c:forEach var="n" items="${list }">
			<tr>
				<td>${n.num}</td>
				<td><a href="detail?num=${n.num}">${n.title} <c:if test="${!(n.cmt_count == 0)}">[${n.cmt_count}]</c:if></a></td>
				<td>${n.writer_id}</td>
				<td>${n.regdate}</td>
				<td>${n.hit}</td>
			</tr>
		</c:forEach>

	</table>
	
	<h3>현재 페이지</h3>
	
	<c:set var="page"  value="${(empty param.p) ? 1:param.p }" />
	<c:set var="startpage" value="${page-(page-1)%5 }"/>
	<c:set var="lastpage" value="${fn:substringBefore(Math.ceil(count/10),'.' )}"></c:set>
	
	<div>
		<span>${page}/${lastpage} pages</span>
	</div>
	
	<div>		
		<c:if test="${startpage > 5}">
			<span><a href="?p=${startpage-1 }">이전</a></span>
		</c:if>
	</div>
	
	<ul>
		
		 	<c:if test="${startpage+4 >= lastpage}">
				<c:forEach var="i" begin="0" end="${(lastpage==0) ? 0 : lastpage - startpage}">
				    <li><a href="?p=${startpage+i}&f=${param.f}&q=${param.q}" >${startpage+i }</a></li>
		    	</c:forEach>
		    </c:if>  
		    <c:if test="${startpage+4 < lastpage}">
		    	<c:forEach var="i" begin="0" end="4">
				    <li><a href="?p=${startpage+i}&f=${param.f}&q=${param.q}" >${startpage+i }</a></li>
		    	</c:forEach>
		    </c:if>
	</ul>
	
	<div>
		<c:if test="${startpage+5 <= lastpage }">
			<span><a href="?p=${startpage+5}">다음</a></span>
		</c:if>
	</div>
	
</body>
<footer>
	<div>
		<dl>
			<dt>주소:</dt>
			<dd>서울특별시</dd>
			<dt>관리자메일:</dt>
			<dd>yangheehoon@gmail.com</dd>
		</dl>
		<dl>
			<dt>관리자 번호</dt>
			<dd>000-0000-0000</dd>
		</dl>
	</div>
</footer>
</html>