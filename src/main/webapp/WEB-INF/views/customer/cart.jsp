<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 품질을 자랑하는 Clothes</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function del() {
	location.href="delcart"
}

</script>
</head>
<body>

	<table class="table table-sm border-dark" style="width: 970px; padding: 30px; margin-left: auto; margin-right: auto;">
		<thead style="background-color: #E6E6E6;">
		<tr>
			<!-- <th style="text-align: center;">선택</th> -->
			<th></th>
			<th style="text-align: center;">주문상품정보</th>
			<th style="text-align: center;">수량</th>
			<th style="text-align: center;">가격</th>
			<th></th>
		</tr>
		</thead>
		<c:forEach var="p" items="${cart}">
		<c:set var="hap_price" value="${hap_price+p.price}"/>
		<tr>		  
			<!-- <td valign="middle" style="text-align: center;"><input type="checkbox" value="check"></td> -->
			<td style="text-align: center;"><a href="clothesdetail?num=${p.num}"><img src="/resources/customer/clothes/${p.files}" style="width: 150px; height: 170px; margin-top: 10px; margin-bottom: 10px;"></a></td>
			<td valign="middle"><div><a href="clothesdetail?num=${p.num}" style="text-decoration: none;">${p.description}</a></div>
				<div>${p.name}</div>
				<div>${fn:substringBefore(p.color,'.')}</div>
				<div>${p.size}</div></td>
			<td valign="middle" style="text-align: center;"><input class="quantity" type="hidden" value="1"><span class="quantity2">1</span>&nbsp;<button class="quantity_plus">+</button><button class="quantity_minus">-</button></td>
			<td valign="middle" style="text-align: center;"><input class="pr" type="hidden" value="${p.price}">${p.price}원</td>
			<td valign="middle"><button type="button" class="product_del btn btn-primary">삭제</button></td>
		</tr>
		</c:forEach>		
	</table> 
<h4 style="text-align: right; margin-right: 190px;"><input class="qtyhap" type="hidden" value="${cart.size()}">상품갯수 : <span class="qtyhap2">${cart.size()}</span></h4>	
<h3 style="color: blue; text-align: right; margin-right: 190px;"><input class=prhap type="hidden" value="${hap_price }">합계금액 : <span class="prhap2">${hap_price }</span></h3>

<br>

<div align="right" style="margin-right: 190px;">
	<button style="color: white; background-color: purple; padding: 10px; font-size: 20px; border-radius: 10px; border: 0px; cursor: pointer; outline: 0px;" onclick="del()">장바구니 비우기</button>
	<button style="color: white; background-color: purple; padding: 10px; font-size: 20px; border-radius: 10px; border: 0px; cursor: pointer; outline: 0px;">결제하기</button>
</div>


</body>
<script type="text/javascript">
$(".product_del").click(function(){	
	
	var index = $(".product_del").index(this);
	
	
	var query = { product_index : index };

	$.ajax({
		url : "/customer/cart",
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

$(".quantity_plus").click(function(){	
	
	var index = $(".quantity_plus").index(this);
	
	var re1 = $(".quantity").eq(index).val()*1+1;
	var re2 = $(".pr").eq(index).val()*1;
	var re3 = $(".prhap").val()*1+re2;
	var re4 = $(".qtyhap").val()*1+1;
	
	$(".quantity").eq(index).val(re1);
	$(".quantity2").eq(index).text(re1);
	$(".prhap").val(re3);
	$(".prhap2").text(re3);
	$(".qtyhap").val(re4);
	$(".qtyhap2").text(re4);
	
})

$(".quantity_minus").click(function(){	
	
	var index = $(".quantity_minus").index(this);
	
	var re1 = $(".quantity").eq(index).val()*1-1;
	var re2 = $(".pr").eq(index).val()*1;
	var re3 = $(".prhap").val()*1-re2;
	var re4 = $(".qtyhap").val()*1-1;
	
	if(re1!=0){
		
		$(".quantity").eq(index).val(re1);
		$(".quantity2").eq(index).text(re1);
		$(".prhap").val(re3);
		$(".prhap2").text(re3);
		$(".qtyhap").val(re4);
		$(".qtyhap2").text(re4);
	}
})
</script>
</html>