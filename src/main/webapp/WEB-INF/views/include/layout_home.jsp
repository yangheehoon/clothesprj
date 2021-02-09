<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css">
<script src="/resources/bootstrap/js/bootstrap.js"></script>
<title><tiles:insertAttribute name="title"/></title>
</head>

<body>
	<tiles:insertAttribute name="navbar"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
</body>	
<style type="text/css">
.footer {
	position: relative;
	bottom: 0px;
	left: 0%;
	height: 170px;
	width: 100%;
	background-color: black;
	color: white;
	text-align: center;
}
</style>
</html>