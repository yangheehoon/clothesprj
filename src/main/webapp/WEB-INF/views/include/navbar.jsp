<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<style type="text/css">
.navbar-dark .navbar-nav .nav-link {
  color: white;
  font-weight: 600;
}
.navbar-dark .navbar-nav .nav-link:hover, .navbar-dark .navbar-nav .nav-link:focus {
  color: rgba(255, 255, 255, 0.65);
}

.dropdown-item:hover, .dropdown-item:focus {
  color: white;
  background-color: #0d6efd;
}
</style>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: black;">
  <div class="container-fluid">
    <a class="navbar-brand" href="/home" style="padding: 0px;">
		<img src="/resources/customer/clothes/coat.png" style="height: 40px; padding: 0px;">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target=".multi-collapse" aria-controls="navbarNavDropdown1 navbarNavDropdown2" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse multi-collapse navbar-collapse" id="navbarNavDropdown1">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="/home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/customer/clotheslist">의류목록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/board_list">자유게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/notice/list">공지사항</a>
        </li>
      </ul>
    </div>
    <div class="collapse multi-collapse navbar-collapse justify-content-end" id="navbarNavDropdown2">
    	<c:if test="${sessionScope.member == null}">
    	<ul class="navbar-nav">
        <li class="nav-item">
			<a href="/member/joinform" class="nav-link">회원가입</a>
		</li>
		<li class="nav-item">
			<a href="/member/login" class="nav-link">로그인</a>
		</li>
		</ul>
		</c:if>
		<c:if test="${sessionScope.member != null}">
		<ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            	${sessionScope.member.nickname}
          </a>
          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
            <li><a class="dropdown-item" href="/member/mypage">mypage</a></li>
            <li><a class="dropdown-item" href="/member/logout">로그아웃 </a></li>
          </ul>
        </li>
        </ul>
        </c:if>
    </div>
  </div>
</nav>	
