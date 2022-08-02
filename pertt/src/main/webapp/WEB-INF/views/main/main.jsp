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
		<div class="main-rank">
		<h1>이달의 OTT 순위</h1>
			<a><img src="${pageContext.request.contextPath}/images/main_rank2.png"  id="main_rank2"></a>
			<a><img src="${pageContext.request.contextPath}/images/main_rank1.png"  id="main_rank1"></a>
			<a><img src="${pageContext.request.contextPath}/images/main_rank3.png"  id="main_rank3"></a>
		</div>
		
		<div class="rank1st-comment">"&nbsp; 오리지널 영화부터 드라마,예능까지 최고!&nbsp; "</div>
		
		<div class="main-netflix">
		<a><img src="${pageContext.request.contextPath}/images/logo_netflix2.png" class="mainlogo1"></a>
			
			<div class="mainBtn">
			<input type="button" value="넷플릭스 별점 주러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/ott/ottReview.do?ott_num=1'">
			<input type="button" value="넷플릭스 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=1'">
			</div>
		</div>
		<div class="main-disney">
		<a><img src="${pageContext.request.contextPath}/images/logo_disney2.png" class="mainlogo2"></a>
		
			<div class="mainBtn">
			<input type="button" value="디즈니+ 별점 주러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/ott/ottReview.do?ott_num=2'">
			<input type="button" value="디즈니+ 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=2'">
			</div>
		</div>
		
		<div class="main-watcha">
		<a><img src="${pageContext.request.contextPath}/images/logo_watcha2.png" class="mainlogo3"></a>
		
			<div class="mainBtn">
			<input type="button" value="왓챠 별점 주러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/ott/ottReview.do?ott_num=3'">
			<input type="button" value="왓챠 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=3'">
			</div>
		</div>
		<div class="main-tving">
		<a><img src="${pageContext.request.contextPath}/images/logo_tving2.png" class="mainlogo4"></a>
		
			<div class="mainBtn">
			<input type="button" value="티빙 별점 주러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/ott/ottReview.do?ott_num=4'">
			<input type="button" value="티빙 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=4'">
			</div>
		</div>
		<div class="main-wavve">
		<a><img src="${pageContext.request.contextPath}/images/logo_wavve2.png" class="mainlogo5"></a>
		
			<div class="mainBtn">
			<input type="button" value="웨이브 별점 주러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/ott/ottReview.do?ott_num=5'">
			<input type="button" value="웨이브 작품 보러 가기>" class="moveBtn" 
			onclick="location.href='${pageContext.request.contextPath}/contents/ottContents.do?ott_num=5'">
			</div>
		</div>
	</div>
</div>
</body>
</html>





