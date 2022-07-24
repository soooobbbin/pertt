<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품 등록 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_admin.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>작품 등록 완료</h2>
		<div class="result-display">
			작품 등록이 완료되었습니다.
			<br>
			<input type="button" value="홈으로"
				  onclick="location.href='${pageContext.request.contextPath}/main/main_admin.do'">
		</div>
	</div>
</div>
</body>
</html>