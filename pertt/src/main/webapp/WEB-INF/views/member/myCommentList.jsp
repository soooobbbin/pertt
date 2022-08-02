<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 댓글 모아보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myComment.js"></script>
</head>
<body>
	<!-- 댓글 보이는 곳 -->
	<div id="my_com"></div>
	
	<!-- 페이지 -->
	<div class="paging-button" style="display:none">
		<input id="next_btn" type="button" value="내 댓글 더보기">
	</div>
</body>
</html>