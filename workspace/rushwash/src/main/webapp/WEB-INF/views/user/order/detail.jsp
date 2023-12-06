<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="/rushwash/resources/css/user/order/detail.css">
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="/WEB-INF/views/user/common/user_header.jsp" %>

<main id="wrap">

    <div id="div1">
       주문내역 상세조회
       <br>
       <br>
    </div>
	<div id="div4">총 가격 : </div>
    <div id="div2">
        <table id="tt00">
            <thead>
                <tr id="th1">
                    <th>상품</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>주문일</th>
                    <th>진행상태</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="voList" var="vo">
                <tr>
                    <td>스터트</td>
                    <td>5,000 원</td>
                    <td>2</td>
                    <td>2021.02.15</td>
                    <td>세탁중</td>
                </tr>
            	</c:forEach>
            	<tr id="th2">
                    <td>수령인</td>
                    <td>핸드폰번호</td>
                    <td>주소</td>
                    <td colspan="2">요청사항</td>
                </tr>
                <c:forEach items="voList" var="vo">
                	<tr>
	                    <td>수령asdfasdfasd인</td>
	                    <td>핸드fasdfasdfas폰번호</td>
	                    <td>주dfasdfasdfasdf소</td>
	                    <td colspan="2">asdfasdfsdf</td>
               	 </tr>
                
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="div2">
		<button type="botton" >주문취소</button>
    </div>
</main>

<%@ include file="/WEB-INF/views/user/common/user_footer.jsp" %>

</body>
</html>