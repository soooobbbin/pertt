<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_admin.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<div class="left_menubar">
			<span class="adminPage_btn">관리자 페이지</span>
			<ul class="admin_cate_member">
				<li>회원 관리</li>
				<li>전체 회원</li>
				<li>탈퇴한 회원</li>
			</ul>
			<hr size="1" width="80%" color="#9a9a9a">
			<ul class="admin_cate_contents">
				<li>작품 조회</li>
				<li>전체 작품</li>
				<li>OTT</li>
				<li>카테고리</li>
				<li>등급</li>
			</ul>
			<hr size="1" width="80%" color="#9a9a9a">
			<div class="contentsRegis_btn">작품등록</div>
		</div>
		
		
	</div>
</div>
</body>
</html>





