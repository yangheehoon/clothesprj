<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자세한 페이지</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
th.solid {
    text-align: left;
    border-bottom: 1px solid #ddd;}
    
td.solid {padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;}
</style>
<script type="text/javascript">

</script>
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
			<c:if test="${fn:endsWith(filename , '.txt') }">
				<c:set var="style" value="font-weight : bold; color : orange;"/>
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
	<summary style="color: blue">댓글[${d.cmt_count}]</summary>
		<form action="" name="fm1">
		<textarea rows="5" cols="50" name="cmt_content"
		onclick="if(this.value=='내용을 입력하세요.'){this.value=''}" >내용을 입력하세요.</textarea>
		<input type="hidden" name="num" value="${d.num}">
		<button type="button" class="cmt">댓글달기</button>
		</form>
	<table >
	<c:forEach var="c" items="${cmtlist}">
 		
 		<tr>
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
		<tr>
			<td></td>
			<td ><button type="button">댓글 삭제</button></td>
		</tr>
		<tr style="font-size: small;">
			<td ></td>
			<th style="text-align: left;"><details>
				<summary style="color: gray">답글달기</summary>
				<form action="" name="fm2"> <!-- 부모속성이 많아서인지 fm2안의 name들을 인식못함 class로 교체하여 값을 추출-->
				<textarea rows="5" cols="50" name="recontent" class="recontent"
				onclick="if(this.value='내용을 입력하세요.'){this.value=''}">내용을 입력하세요.</textarea>
				<input hidden="" name="cmt_num" value="${c.num }" class="cmt_num">
				<button type="button" class="recmt">답글달기</button>
				</form>
			</details></th>
		</tr>
		<tr style="font-size: small;">
			<td class="solid"></td>
			<th class="solid">
				<%-- <c:set var="re"  value="${recmtlist}"/> --%> 
                <c:set var="check" value="false"></c:set>   
				<c:forEach var="r" items="${recmtlist }">
					<c:if test="${c.num==r.notice_num}">
						<c:set var="check" value="true"></c:set>
					</c:if> 
				</c:forEach>
				<c:if test="${check==true}"> 				
				<details> 
				<summary style="color: blue">답글보기</summary>
						<c:forEach var="rec" items="${recmtlist }">
						<c:if test="${c.num==rec.notice_num}">
						<!-- cmt_num 값을 개체에서 notice_num으로 저장함-->					
						<table>
							<tr>
							<br>
							</tr>
						    <tr style="font-size: small;">
						    	<th>작성자</th>
								<td>${rec.writer_id }</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>${rec.content }</td>
							</tr>
							<tr style="font-size: small;">
								<th>작성일</th>
								<td>${rec.regdate }</td>
							</tr>
							<tr>
								<td class="solid"></td>
								<td class="solid"><button type="button">답글 삭제</button></td>
							</tr>
						</table>
						</c:if>
						</c:forEach>			
			</details> </c:if> </th>
		</tr>
		
	</c:forEach>

 	</table>
 
 	</details>	

<div>
	<a href="board_list">목록</a>
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
		<td><a href="board_detail?num=${prevdetail.num}">
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
			<td><a href="board_detail?num=${nextdetail.num }">
				${nextdetail.content}</a>
			</td>
		</c:if>
	</tr>
</table>

<br>
	<c:if test="${sessionScope.member.nickname==d.writer_id }">
	<form action="board_del" method="post">
		<input type="hidden" name="delnum" value="${d.num}">
		<input type="submit"  value="게시글 삭제">
	</form>
	</c:if>
	
<script type="text/javascript">
$(".cmt").click(function(){
	
	var query = { num : fm1.num.value, cmt_content : fm1.cmt_content.value };
	
	$.ajax({
		url : "/board/board_detail",
		type : "post",
		data : query,
		error : function(){
			alert("통신에러");
		},
		success : function(data){
			location.reload();
		}		
	})
})

/* var size = $(".recmt").length;

for(i=0;i<size;i++){ */
$(".recmt").click(function(){	
	var index = $(".recmt").index(this);
	
	var query = { num : fm1.num.value, recontent : $(".recontent").eq(index).val(),
			cmt_num : $(".cmt_num").eq(index).val()};
	
	$.ajax({
		url : "/board/board_detail",
		type : "post",
		data : query,
		error : function(){
			alert("통신에러");
		},
		success : function(data){
			location.reload();
		}		
	})
})

</script>

</body>
</html>