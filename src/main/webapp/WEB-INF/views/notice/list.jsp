<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3 style="text-align: center;">공지사항 목록  </h3>	

<fieldset class="mb-2">		
	<form action="" style="text-align:right; margin-right: 10px;">	
		<label>검색분류</label> 
			<select name="f">
				<option ${(param.f == "title") ? "selected":""} value="title">제목</option>
				<option ${(param.f == "writer_id") ? "selected":""} value="writer_id">작성자</option>
			</select>
		<label>검색어</label> 
		<input type="text" name="q" value="${param.q}" /> 
		<input type="submit" class="btn btn-primary btn-sm" style="vertical-align: 0px;" value="검색" />
	</form>    
    	
</fieldset> 	
 		    	
<table class="table table-sm">
	<!-- <table border="1" style="width: 60%; margin-left: auto; margin-right: auto;"> -->
	  <thead class="table-dark">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	  </thead>

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
	
	<!-- 현재페이지 -->
	<c:set var="page"  value="${(empty param.p) ? 1:param.p }" />
	<c:set var="startpage" value="${page-(page-1)%5 }"/>
	<c:set var="lastpage" value="${fn:substringBefore(Math.ceil(count/10),'.' )}"></c:set>
	
	<div class="mb-2">
		<span style="margin-left: 10px; color: gray;">${page}/${lastpage} pages</span>		
		<c:if test="${sessionScope.member.id=='master'}">
			<a href="add" ><button style="float: right; margin-right: 20px;" class="btn btn-primary btn-sm">공지글 추가</button></a>			 		    
		</c:if>
	</div>
	
	
	<!-- 페이지리스트 -->
	<ul style="list-style: none;  text-align: center; padding: 0px; margin: 0px;">		
	<!-- 이전페이지 -->		
		<c:if test="${startpage > 5}">
			<li style="display:inline-block;"><a href="?p=${startpage-1 }">이전</a></li>
		</c:if>
	
		 	<c:if test="${startpage+4 >= lastpage}">
				<c:forEach var="i" begin="0" end="${(lastpage==0) ? 0 : lastpage - startpage}">
				    <li style="display:inline-block; margin-left: 15px;"><a style="color: ${((startpage+i)==page) ? 'black' : 'gray'};" href="?p=${startpage+i}&f=${param.f}&q=${param.q}" >${startpage+i }</a></li>
		    	</c:forEach>
		    </c:if>  
		    <c:if test="${startpage+4 < lastpage}">
		    	<c:forEach var="i" begin="0" end="4">
				    <li style="display:inline-block; margin-left: 15px;"><a style="color: ${((startpage+i)==page) ? 'black' : 'gray'}; " href="?p=${startpage+i}&f=${param.f}&q=${param.q}" >${startpage+i }</a></li>
		    	</c:forEach>
		    </c:if>
	<!-- 다음페이지 -->
		<c:if test="${startpage+5 <= lastpage }">
			<li style="display:inline-block; margin-left: 15px;"><a href="?p=${startpage+5}">다음</a></li>
		</c:if>	
	</ul>
	