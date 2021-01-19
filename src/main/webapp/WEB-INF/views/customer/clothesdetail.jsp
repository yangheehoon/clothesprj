<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 품질을 자랑하는 Clothes</title>
<script type="text/javascript">
function check() {
	if(fm.color.value==""){
		alert("색상을 선택해주세요.");
		return false;
	}else if (fm.size.value=="") {
		alert("사이즈를 선택해주세요.");
		return false;
	}
	return true;
}
function check2() {
	if(fm.color.value!=="" && fm.size.value!==""){
		window.open("/resources/popup.jsp","장바구니에 추가되었습니다.",
				"width = 500, height = 500, top = 100, left = 200, location = no");
	}
}	
function check3() {
	var name =fm.name.value;
	var size =fm.size.value;
	var color =fm.color.value;	
	
	if(fm.color.value!=="" && fm.size.value!==""){
		location.href="pay?name="+name+"&color="+color+"&size="+size;
	}	
}	
</script>
<style type="text/css">
.sp{
	   visibility:hidden;
	   display: block;
	   text-align: center;
	   color: gray;
	   cursor:pointer;
}
.img{
	   border-style : solid;
       border-color: #dcdcdc;
       border-radius: 10px;
       cursor:pointer;
}
input[type="radio"] + label:hover .sp{
	   visibility: visible;
}
input[type="radio"]:checked + label .img{
       border-color: #cc33ff;
       
}
input[type="radio"]:checked + label .sp{
       visibility: visible;
       color: #cc33ff;
}

</style>
</head>

<body>
<fieldset>
	<label>의류 목록 관리</label>
	<input type="button" value="의류 삭제">
</fieldset>

<c:set var="c" value="${clothes}" />

<div style="width: 100%; height: 510px;">
	
	<div style="float: left;">
		<img src="/resources/customer/clothes/${c.files}" style="width: 500px; height: 500px; background-color: white;" ></td>
	</div>
  
  <form name="fm" onsubmit="return check()" target="param">	
  	<input hidden="" name="num" value="${c.num}">
  	<input hidden="" name="name" value="${c.name}">	
	<h2>${c.name}</h2>
	<h3>${c.price}원</h3>

	<div style="border-bottom-width: 1px; border-bottom-color: #dcdcdc; border-bottom-style: solid; margin-bottom: 30px;"></div>

  <div>
	<ul style="list-style-type: none;">
		<li style="margin-bottom: 30px;">
			<div style="float: left; margin-right: 30px;">색상</div>
			<ul style="list-style-type: none;">
				<li style="display: inline-block;"><input type="radio" name="color" id="ra1" style="display: none;" value="블랙"><label for="ra1"><img class="img" src="/resources/color/black.png" style="width: 50px; height: 50px;"><span class="sp">블랙</span></label></li>
				<li style="display: inline-block;"><input type="radio" name="color" id="ra2" style="display: none;" value="그레이"><label for="ra2"><img class="img" src="/resources/color/gray.png" style="width: 50px; height: 50px;"><span class="sp">그레이</span></label></li>
				<li style="display: inline-block;"><input type="radio" name="color" id="ra3" style="display: none;" value="아이보리"><label for="ra3"><img class="img" src="/resources/color/ivory.jpg" style="width: 50px; height: 50px;"><span class="sp">아이보리</span></label></li>
			</ul> 
		</li>
		<li>
			<div style="float: left; margin-right: 30px;">사이즈</div>
			<select name="size">
				<option value="">사이즈를 선택해주세요.</option>
				<option value="90">90</option>
				<option value="95">95</option>
				<option value="100">100</option>
				<option value="105">105</option>
				<option value="110">110</option>
				<option value="115">115</option>
			</select>
		</li>
	</ul>
  </div>
  <br>	

  <div style="border-bottom-width: 1px; border-bottom-color: black; border-bottom-style: solid;
  border-top-width: 1px; border-top-color: gray; border-top-style: solid; padding: 20px;"></div>

  <br>
  <h4>총 결제금액</h4>

  <ul>
	<li style="display: inline-block;">
		<input type="submit" style="padding: 20px; font-size: 30px; background-color: #ff0099; color: white; border-radius: 15px; outline: 0px; width: 280px; margin-right: 20px; border: 0px; cursor: pointer;" value="장바구니" formaction="addcart" onclick="check2()"></li>
	<li style="display: inline-block;">
		<input type="submit" style="padding: 20px; font-size: 30px; background-color: purple; color: white; border-radius: 15px; outline: 0px; width: 280px; border: 0px; cursor: pointer;" value="결제하기" formaction="pay" onclick="check3()"></li>	
  </ul>

  </form>
  <iframe name="param" style="display: none;"></iframe>
</div>


<div style="border-top-width: 5px; border-top-color: gray; border-top-style: solid; padding: 10px;"></div>

<div style="text-align: center;">
	<label>${c.description}</label>
</div>

</body>
</html>