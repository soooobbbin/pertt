<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${contents.title }</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<img src="${pageContext.request.contextPath}/images/dug_main.png">
	<div class="contents-detail">
		<img src="${pageContext.request.contextPath}/images/dug_poster.jpg">
		<span class="review_title">${contents.title }</span>
		<!-- ott_num에 따라 ott 이름 다르게 표시 -->
		<c:if test="${contents.ott_num == 1 }">
			<span>NETFLIX</span>
		</c:if>
		<c:if test="${contents.ott_num == 2 }">
			<span>DISNEY+</span>
		</c:if>
		<c:if test="${contents.ott_num == 3 }">
			<span>WATCHA</span>
		</c:if>
		<c:if test="${contents.ott_num == 4 }">
			<span>TVING</span>
		</c:if>
		<c:if test="${contents.ott_num == 5 }">
			<span>WAVVE</span>
		</c:if>
		<span>${contents.release } ${contents.genre } ${contents.country }</span>
		<span>${contents.produce}</span>
		<span>토마토</span>
	</div><!-- end of contents_detail -->
	<hr size="1" width="300px" noshade="noshade">
	
	<div class="review-star" >
		<h1 class="star-main">별점총점</h1>
		<div class="give-star">
			별점주기<br>
			<img src="${pageContext.request.contextPath}/images/give_star.jpg">
		</div>
	</div><!-- end of review_star -->
	
	<div class="review-grey">
		<div class="contents_plot"><!-- 배경 하얗게 -->
			<h2>작품 소개</h2>
			${contents.plot }
		</div>
		<h2>리뷰 쓰기</h2>
		<div class="review-form">
			<form action="reviewWrite.do" id="review_form">
				<textarea rows="10" cols="80" enterkeyhint="내용을 입력해주세요"></textarea>
				<input type="submit" value="등록">
			</form>
		</div><!-- end of review_form -->
	</div><!-- end of review_grey -->
	
	<h2>리뷰</h2>
	<div class="review-view">
		<div class="review-box">
			
		</div>
		<div class="review-box">
		
		</div>
	</div>


</div>
</body>
</html>



-







