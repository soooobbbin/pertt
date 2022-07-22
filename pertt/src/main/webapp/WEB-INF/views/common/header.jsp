<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<div id="main_logo">
	<div id="main_menubar">
		<c:if test="${empty member_num}">
			<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		</c:if>
		<c:if test="${!empty member_num && member_auth == 1}">
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
		</c:if>
	
	</div>
	<h1 class="align-center">
		<a href="${pageContext.request.contextPath}/main/main.do">
			<img src="${pageContext.request.contextPath}/images/logo_pertt.png" width="290px" height="120px">
		</a>
	</h1>
</div>
<div id="main_nav">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/ott/intro.do">소개</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/ott/ottStar.do">ott별점주기</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/ott/ottRank.do">ott랭킹보기</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/contents/ottContents.do" id="netflix">넷플릭스</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/review/review.do" id="disney">디즈니</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/contents/ottContents.do" id="watcha">왓챠</a>
		</li>
		<li>
		<a href="${pageContext.request.contextPath}/contents/ottContents.do" id="wavve">웨이브</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/ott/recommend.do">OTT추천</a>
		</li>
	</ul>
</div>
<!-- header 끝 -->






