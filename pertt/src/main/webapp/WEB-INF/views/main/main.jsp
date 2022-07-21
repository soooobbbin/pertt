<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h1>이달의 OTT 순위</h1>
		<div class="main-rank">
			<a><img src="${pageContext.request.contextPath}/images/main_rank (2).jpg" width="250" height="350"></a>
			<a><img src="${pageContext.request.contextPath}/images/main_rank (1).jpg" width="200" height="280"></a>
			<a><img src="${pageContext.request.contextPath}/images/main_rank (3).jpg" width="200" height="280"></a>
		</div>
		
		<div class="rank1st-comment">"오리지널 영화부터 드라마,예능까지 최고!"</div>
		
		<div class="main-netflix">
			
		</div>
	</div>
</div>
</body>
</html>





