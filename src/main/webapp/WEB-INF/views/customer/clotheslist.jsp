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

<h3 style="text-align: center;">의류목록</h3>

<fieldset class="mb-2">
	<form action="" style="text-align: right; margin-right: 10px;">	
		<label>검색어</label> 
		<input type="text" name="q" value="${param.q}" /> 
		<input type="submit" class="btn btn-primary btn-sm" style="vertical-align: 0px;" value="검색" />	
    </form>
</fieldset>  

<ul class="list-group list-group-horizontal">     <!--이미지225px-->
	<c:forEach var="cl" items="${clotheslist}">
	<li class="list-group-item" style="float: left; width: 20%; height: 300px;">
		<a href="clothesdetail?num=${cl.num}" ><img alt="" style="width: 100%; height: 80%;" src="/resources/customer/clothes/${cl.files}"></a>
		<br>                                                  <!-- width: 85% height: 225px;-->           
		<label style="font-style: italic;">${cl.price}</label>
		<br>
		<div class="one" style="width: 100%;"> <!-- width: 85% -->
		<a href="clothesdetail?${cl.num}">${cl.description}</a>
		</div>
	</li>
	</c:forEach>			
</ul>
<br>

<c:set var="page" value="${(empty param.p) ? 1 : param.p }"/>
<c:set var="startpage" value="${page-(page-1)%5}"/>
<c:set var="lastpage" value="${fn:substringBefore(Math.ceil(clothescount/5), '.')}"/>

<div>
	<span style="margin-left: 10px; color: gray;">${page}/${lastpage} pages</span>
	<c:if test="${sessionScope.member.id=='master'}">
			<a href="clothesadd" ><button style="float: right; margin-right: 20px;" class="btn btn-primary btn-sm">의류 추가</button></a>	    
 	</c:if>
</div>

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