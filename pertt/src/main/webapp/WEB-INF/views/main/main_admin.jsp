<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_admin.css" type="text/css">
</head>
<body>
<div class="page-main">
	<div class="content-main">
	<h2>전체 회원</h2>
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
		<div class="admin-main">
			<div class="admin_main_h2"><h2>전체 회원</h2></div>
			<div class="admin_main_p"><p>관리메뉴에서 회원 등급 변경이 가능하며, 내보내기(탈퇴)기능이 가능합니다.</p></div>
		</div>
	</div>
</div>
</body>
</html>






