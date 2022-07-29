<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<!-- 
<script type="text/javascript">
$(document).ready(function(){
	 $('#moveDown').click(function(){
		 $('html, body').animate({
			 scrollTop:$('.main-netflix').offset().top
		 }, 'slow');
	 });
 });
</script>
-->
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
		<a><img src="${pageContext.request.contextPath}/images/logo_netflix2.png" class="mainlogo1"></a>
			
			<img src="${pageContext.request.contextPath}/images/down.png" class="moveDown">
			<div class="mainBtn">
			<input type="button" value="넷플릭스 별점 주러 가기>" class="moveBtn" onclick="location.href=''">
			<input type="button" value="넷플릭스 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=1'">
			</div>
		</div>
		<div class="main-disney">
		<a><img src="${pageContext.request.contextPath}/images/logo_disney2.png" class="mainlogo2"></a>
		
			<div class="mainBtn">
			<input type="button" value="디즈니+ 별점 주러 가기>" class="moveBtn" onclick="location.href=''">
			<input type="button" value="디즈니+ 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=2'">
			</div>
		</div>
		
		<div class="main-watcha">
		<a><img src="${pageContext.request.contextPath}/images/logo_watcha2.png" class="mainlogo3"></a>
		
			<div class="mainBtn">
			<input type="button" value="왓챠 별점 주러 가기>" class="moveBtn" onclick="location.href=''">
			<input type="button" value="왓챠 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=3'">
			</div>
		</div>
		<div class="main-tving">
		<a><img src="${pageContext.request.contextPath}/images/logo_tving2.png" class="mainlogo4"></a>
		
			<div class="mainBtn">
			<input type="button" value="티빙 별점 주러 가기>" class="moveBtn" onclick="location.href=''">
			<input type="button" value="티빙 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=4'">
			</div>
		</div>
		<div class="main-wavve">
		<a><img src="${pageContext.request.contextPath}/images/logo_wavve2.png" class="mainlogo5"></a>
		
			<div class="mainBtn">
			<input type="button" value="웨이브 별점 주러 가기>" class="moveBtn" onclick="location.href=''">
			<input type="button" value="웨이브 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=5'">
			</div>
		</div>
	</div>
</div>
</body>
</html>





