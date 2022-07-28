<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.dropdown > #intro').hover(function(){
		$('.dropdown-content',this).stop().slideDown(500);
	},
	function(){
		$('.dropdown-content',this).stop().slideUp(300);
		
	});
});
</script>
<!-- header 시작 -->
<div id="main_logo">
	<div id="main_menubar">
		<c:if test="${empty user_num}">
			<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		</c:if>
		<c:if test="${!empty user_num}">
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</c:if>
		<c:if test="${!empty user_num && user_auth == 1}">
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
		</c:if>
		<c:if test="${!empty user_num && user_auth == 2}">
			<a href="${pageContext.request.contextPath}/main/main_admin.do">관리자 홈</a>
		</c:if>
	
	
	</div>
	<h1 class="align-center">
		<a href="${pageContext.request.contextPath}/main/main.do">
			<img src="${pageContext.request.contextPath}/images/logo_pertt.png" width="300px" height="150px">
		</a>
	</h1>
</div>
<div id="main_nav">
	<ul>
		<div class="dropdown">
		<li>
			<a href="${pageContext.request.contextPath}/ott/intro.do" id="intro">소개</a>
		<li>
		<div class="dropdown-content">
			<a href="${pageContext.request.contextPath}/ott/ottReview.do">ott별점주기</a>
			<br><br>
			<a href="${pageContext.request.contextPath}/ott/ottRank.do">ott랭킹보기</a>
		</div>
		</div>
		<li>
			<a href="${pageContext.request.contextPath}/contents/ottContents.do?ott_num=1" id="netflix">NETFLIX</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/contents/ottContents.do?ott_num=2" id="disney">Disney+</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/contents/ottContents.do?ott_num=3" id="watcha">WATCHA</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/contents/ottContents.do?ott_num=4" id="tving">TVING</a>
		</li>
		<li>
		<a href="${pageContext.request.contextPath}/contents/ottContents.do?ott_num=5" id="wavve">Wavve</a>
		</li>
		<div class="recommend">
		<li>
			<a href="${pageContext.request.contextPath}/ott/recommend.do" id="recommend">OTT추천</a>
		</li>
		</div>
	</ul>
</div>
<!-- header 끝 -->






