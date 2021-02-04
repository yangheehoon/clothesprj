<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록</title>
</head>
<body>
	<h3>자유게시판 목록  
	<a href="../home" style="float: right; margin-right: 10px;">홈으로</a>
	</h3>
	
	<fieldset >
		<legend>게시판 검색 필드</legend>
		
		<form action="" style="float: left;">	

		<label>검색분류</label> 
		<select name="f">
			<option ${(param.f == "title") ? "selected":""} value="title">제목</option>
			<option ${(param.f == "writer_id") ? "selected":""} value="writer_id">작성자</option>
		</select>
		<label>검색어</label> 
		<input type="text" name="q" value="${param.q}" /> 
		<input type="submit" value="검색" />
    	
    	</form>
    		
    		<div style="float: right;">
    			<input type="hidden" id="sion" value="${sessionScope.member}">
				<a href="board_add" ><button onclick="logincheck()">게시글 작성</button></a>
			</div>
 			
 	</fieldset>  
	
	
	<table border="1" style="width: 60%; margin-left: auto; margin-right: auto;">
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
				<td><a href="board_detail?num=${n.num}">${n.title} <c:if test="${!(n.cmt_count == 0)}">[${n.cmt_count}]</c:if></a></td>
				<td>${n.writer_id}</td>
				<td>${n.regdate}</td>
				<td>${n.hit}</td>
			</tr>
		</c:forEach>

	</table>
	
	<!-- 현재페이지 -->
	<c:set var="page"  value="${(empty param.p) ? 1:param.p }" />
	<c:set var="startpage" value="${page-(page-1)%5 }"/>
	<c:set var="lastpage" value="${fn:substringBefore(Math.ceil(count/10),'.' )}"></c:set>
	
	<div>
		<span style="margin-left: 1220px;">${page}/${lastpage} pages</span>
	</div>
	
	
	<!-- 페이지리스트 -->
	<ul style="list-style: none; margin-left: 550px;">		
	<!-- 이전페이지 -->		
		<c:if test="${startpage > 5}">
			<li style="float: left; margin-right: 15px;"><a href="?p=${startpage-1 }">이전</a></li>
		</c:if>
	
		 	<c:if test="${startpage+4 >= lastpage}">
				<c:forEach var="i" begin="0" end="${(lastpage==0) ? 0 : lastpage - startpage}">
				    <li style="float: left; margin-right: 15px;"><a style="color: ${((startpage+i)==page) ? 'black' : 'gray'};" href="?p=${startpage+i}&f=${param.f}&q=${param.q}" >${startpage+i }</a></li>
		    	</c:forEach>
		    </c:if>  
		    <c:if test="${startpage+4 < lastpage}">
		    	<c:forEach var="i" begin="0" end="4">
				    <li style="float: left; margin-right: 15px;"><a style="color: ${((startpage+i)==page) ? 'black' : 'gray'}; " href="?p=${startpage+i}&f=${param.f}&q=${param.q}" >${startpage+i }</a></li>
		    	</c:forEach>
		    </c:if>
	<!-- 다음페이지 -->
		<c:if test="${startpage+5 <= lastpage }">
			<li style="float: left; margin-right: 15px;"><a href="?p=${startpage+5}">다음</a></li>
		</c:if>	
	</ul>
	
</body>

<footer>
<br>
	<div>
		<dl>
			<dt style="float: left;">주소:</dt>
			<dd>&nbsp; 서울특별시</dd>
		</dl>
		<dl>	
			<dt style="float: left;">관리자 메일:</dt>
			<dd>&nbsp; clothes@clothes.com</dd>
		</dl>
		<dl>
			<dt style="float: left;">관리자 번호:</dt>
			<dd>&nbsp; 000-0000-0000</dd>
		</dl>
	</div>
</footer>
<script type="text/javascript">
function logincheck(){
	if(document.getElementById("sion").value==""){
		alert("로그인 후 이용해주세요");
	}
}

</script>
</html>