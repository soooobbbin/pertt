<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 별점 주기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ott_star.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ottStar.js"></script>
</head>
<body>
<div class="page-main">
<div class="align-center">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div align="center"></div>
    <img src="../images/logo_netflix.png" width=300>
    <h1>가성비</h1>
     <form class="mb-3" name="myform" id="myform" method="post">
		<fieldset>
			<span class="text-bold">별점을 선택해주세요</span>
			<input type="radio" name="reviewStar" value="5" id="rate1"><label
				for="rate1">★</label>
			<input type="radio" name="reviewStar" value="4" id="rate2"><label
				for="rate2">★</label>
			<input type="radio" name="reviewStar" value="3" id="rate3"><label
				for="rate3">★</label>
			<input type="radio" name="reviewStar" value="2" id="rate4"><label
				for="rate4">★</label>
			<input type="radio" name="reviewStar" value="1" id="rate5"><label
				for="rate5">★</label>
		</fieldset>
		<div>
			<textarea class="col-auto form-control" type="text" id="reviewContents"
					  placeholder="좋은 수강평을 남겨주시면 Cocolo에 큰 힘이 됩니다! 포인트 5000p도 지급!!"></textarea>
		</div>
	</form>
</div>
</div>
</body>
</html>