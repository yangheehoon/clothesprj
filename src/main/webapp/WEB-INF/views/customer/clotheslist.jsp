<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의류목록</title>
<style>
.one{
	overflow: hidden;
	text-overflow: ellipsis; 
	white-space: nowrap;
}
</style>
</head>
<body>

<h3>의류목록<a href="../home" style="float: right; margin-right: 10px;">홈으로</a></h3>

<fieldset >
		<legend>의류 검색 필드</legend>
		
		<form action="" style="float: left;">	

		<label>검색어</label> 
		<input type="text" name="q" value="${param.q}" /> 
		<input type="submit" value="검색" />
    	
    	</form>
    	
    	<c:if test="${sessionScope.member.id=='master'}">
			<label style="float: right;">&nbsp;</label>
			<div style="float: right;">
				<a href="clothesadd" ><button>의류 추가</button></a>
			</div>
 			<label style="float: right;">의류 관리  &nbsp;</label>    
 		</c:if>
 		    
 	</fieldset>  

<ul style="list-style-type: none;" >     <!--이미지225px-->
	<c:forEach var="cl" items="${clotheslist}">
	<li style="float: left; width: 20%; height: 300px;">
		<a href="clothesdetail?num=${cl.num}" ><img alt="" style="width: 85%; height: 225px;" src="/resources/customer/clothes/${cl.files}"></a>
		<br>
		<label style="font-style: italic;">${cl.price}</label>
		<br>
		<div class="one" style="width: 85%;">
		<a href="clothesdetail?${cl.num}">${cl.description}</a>
		</div>
	</li>
	</c:forEach>			
</ul>

<c:set var="page" value="${(empty param.p) ? 1 : param.p }"/>
<c:set var="startpage" value="${page-(page-1)%5}"/>
<c:set var="lastpage" value="${fn:substringBefore(Math.ceil(clothescount/5), '.')}"/>

<span style="margin-left: 1220px; ">${page}/${lastpage} pages</span>

<ul style="list-style-type: none; text-align: center; padding: 0px; margin: 0px;">
	
	<c:if test="${startpage>1 }">
		<a href="clotheslist?p=${startpage-1}">이전</a>
	</c:if>
	
	<c:if test="${startpage <= (lastpage-5) }">
		<c:forEach var="n" begin="0" end="4">
			<li style=" margin-left: 15px; display: inline-block;"><a href="clotheslist?p=${startpage+n}&q=${param.q}" style="color: ${((startpage+n)==page)? 'black' : 'gray'}">${startpage+n}</a></li>
		</c:forEach>
	</c:if>
	<c:if test="${startpage >(lastpage-5)}">
		<c:forEach var="n" begin="0" end="${(lastpage==0) ? 0 : lastpage-startpage}">
			<li style=" margin-left: 15px; display: inline-block;"><a href="clotheslist?p=${startpage+n}&q=${param.q}" style="color: ${((startpage+n)==page)? 'black' : 'gray'}">${startpage+n}</a></li>
		</c:forEach>
	</c:if>
	
	<c:if test="${startpage<(lastpage-5)}">
		<a href="clotheslist?p=${startpage+5}" style="margin-left: 15px;">다음</a>
	</c:if>
</ul>

</body>
</html>