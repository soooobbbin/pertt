<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 랭킹</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ott-rank.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="rank">
	<h2>OTT 랭킹순위</h2>
	<img id="medal1" src="../images/medal1.png">
	<img id="medal2" src="../images/medal2.png">
	<img id="medal3" src="../images/medal3.png">
	<img id="ott-image" src="${pageContext.request.contextPath}/images/${contents.ott_num }/${contents.poster}">
	</div>
</div>
</body>
</html>