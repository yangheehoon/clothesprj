<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
 <c:set var="d" value="${detail}"/>
 
<details id="sm">
	<summary style="color: blue" id="sm">댓글[${d.cmt_count}]</summary>
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
		<tr style="font-size: small;">
			<td ></td>
			<th style="text-align: left;"><details>
				<summary style="color: gray">답글달기</summary>
				<form action="" name="fm2">
				<textarea rows="5" cols="50" name="recontent" 
				onclick="if(this.value='내용을 입력하세요.'){this.value=''}">내용을 입력하세요.</textarea>
				<input hidden="" name="cmt_num" value="${c.num }">
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
								<td class="solid"></td>
							</tr>
						</table>
						</c:if>
						</c:forEach>			
			</details> </c:if> </th>
		</tr>
		
	</c:forEach>

 	</table>
 
 	</details>	

</body>
</html>