<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 추천</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ott_re.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main align-center">
		<h2>나에게 맞는 OTT 추천받기</h2><br>
		<p>당신의 취향을 모두 선택하세요! 당신에게 딱 맞는 OTT를 퍼티가 추천해드려요.</p>
		<form action="write.do" method="post" id="ottRecommend_form"  enctype="multipart/form-data">
		<div class="checkbox1">
			<input type="checkbox" id="cb1" value="korea">한국 콘텐츠
			<input type="checkbox" id="cb2" value="english">영미권 콘텐츠
			<input type="checkbox" id="cb3" value="japan">일본 콘텐츠
		</div>
		<div class="checkbox2">
			<input type="checkbox" id="cb4" value="movie">영화
			<input type="checkbox" id="cb5" value="drama">드라마
			<input type="checkbox" id="cb6" value="variety">예능 
		</div>
		<div class="checkbox3">
			<input type="checkbox" id="cb7" value="indi">인디
			<input type="checkbox" id="cb8" value="animation">애니메이션
			<input type="checkbox" id="cb9" value="SF">SF
			<input type="checkbox" id="cb10" value="kbs">지상파 방송
			<input type="checkbox" id="cb11" value="cj">케이블 방송
		</div>
		
		</form>
	</div>
</div>
</body>
</html>