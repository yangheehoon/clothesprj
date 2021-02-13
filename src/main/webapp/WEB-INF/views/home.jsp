<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 글자수 필터 css 댓글과 합칠시 해당 블록 영역으로 인해 댓글과의 간격이 
멀어지거나 댓글이 잘리는 등의 문제로 jstl로 대체
<style> 
.link-bl-over-el{
	display: inline-block;
	width: 200px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; 
	vertical-align: bottom;
	}
</style> -->
<style>
.one{
	width: 100%;
	overflow: hidden;
	text-overflow: ellipsis; 
	white-space: nowrap;
}


@media (min-width: 768px) {
  .col-md-2-5{
	flex: 0 0 auto;
    width: 20%;
	}
}  
</style>

<div style="text-align: center;">
	<img src="/resources/home/배경4.jpg" width="100%" style="max-height: 600px; overflow: hidden;">
	<div style="position: absolute; top: 150px; left: 12%; right: 12%; color: white;">
	<h1 style="font-weight: 550;">
	 Clothes
	</h1>
	</div>
</div>
<div class="row" style="margin-right: 0px; margin-left: 0px;">
 
	<h2 style="font-weight: 550; padding: 10px;">New items</h2>     
	<c:forEach var="cl" items="${clotheslist}">
	 <div class="col-md-2-5" style="margin-bottom: 30px;">
		<div class="p-2">
		<a href="/customer/clothesdetail?num=${cl.num}" ><img class="mb-2" style="width: 100%; height: 300px; border-radius: 15px;" src="/resources/customer/clothes/${cl.files}"></a>
		<br>                                                  			            <!-- height:80%; *주의* 컨테이너가 컨테츠의 크기에 영향을 받기에 height: %는 크기 변화 없음,자연스러운 이미지 출력을 위해선 같은 크기의 이미지를 사용하여 img태그의 height의 크기를 지정해 주지 않는게 베스트-->           
		<label style="font-style: italic;">${cl.price}</label>
		<br>
		<div class="one">
		<a style="text-decoration: none; color: gray;" href="/customer/clothesdetail?${cl.num}">${cl.description}</a>
		</div>
		</div>
	</div>
	</c:forEach>			
  
</div>

<div class="row mb-3" style="margin-right: 0px; margin-left: 0px;">
	<div class="col-md-6">
		<div class="p-4 shadow rounded">
			<h3 class="rounded p-2" style="background-color: black; color: white;">자유게시판</h3>
			<ul class="list-unstyled" style="margin-bottom: 0;">
				<c:forEach var="b" items="${boardlist}" end="6">
					<!-- 글자수 필터 -->
					<c:set var="retitle" value="${b.title }"/>
        			<c:if test="${fn:length(b.title) > 35}">
        				<c:set var="retitle" value="${fn:substring(b.title, 0, 34)}..."/>
        			</c:if>
					
					<li style="background-image: url('/resources/home/point.png'); background-repeat: no-repeat; background-position: 0; padding-left: 12px; ">
                        <span>
                            <a style="text-decoration: none; color: black;" href="/board/board_detail?num=${b.num}">${retitle}</a>
                        </span>
                        <span style="color: red;"><c:if test="${b.cmt_count != 0}">[${b.cmt_count}]</c:if></span>
                        <%-- <span>${b.regdate }</span> --%>
                    </li>
				</c:forEach>
        	</ul>
		</div>
	</div>
	<div class="col-md-6">
		<div class=" p-4 shadow rounded">
			<h3 class="rounded p-2" style="background-color: black; color: white;">공지사항</h3>
			<ul class="list-unstyled" style="margin-bottom: 0;">
            	<c:forEach var="n" items="${list}" end="6">
            		<!-- 글자수 필터 -->
                    <c:set var="retitle2" value="${n.title }"/>
        			<c:if test="${fn:length(n.title) > 35}">
        				<c:set var="retitle2" value="${fn:substring(n.title, 0, 34)}..."/>
        			</c:if>
                    
                    <li style="background-image: url('/resources/home/point.png'); background-repeat: no-repeat; background-position: 0; padding-left: 12px; ">
                        <span>
                            <a style="text-decoration: none; color: black;" href="notice/detail?num=${n.num}">${retitle2}</a>
                        </span>
                        <span style="color: red;"><c:if test="${n.cmt_count != 0}">[${n.cmt_count}]</c:if></span>
                        <%-- <span>${n.regdate}</span> --%>
                    </li>
				</c:forEach>     
            </ul>
      </div>
      </div>
</div>

