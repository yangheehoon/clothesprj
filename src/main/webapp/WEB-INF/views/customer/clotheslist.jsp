<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    	
		<label style="float: right;">&nbsp;</label>
		<div style="float: right;">
			<a href="clothesadd" ><button>의류 추가</button></a>
		</div>
 		<label style="float: right;">의류 관리  &nbsp;</label>    
 		    
 	</fieldset>  

<ul style="list-style-type: none;">
	<c:forEach var="cl" items="${clotheslist}">
	<li style="float: left; width: 230px; height: 300px; margin-right: 15px;">
		<a href="clothesdetail?${cl.num}" ><img alt="" style="width: 225px; height: 225px;" src="/resources/customer/clothes/${cl.files}"></a>
		<label style="font-style: italic;">${cl.price}</label>
		<br>
		<div class="one">
		<a href="clothesdetail?${cl.num}">${cl.description}</a>
		</div>
	</li>
	</c:forEach>			
</ul>
 
</body>
</html>