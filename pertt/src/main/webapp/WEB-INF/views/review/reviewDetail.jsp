<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${contents.title }</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>	
	<div class="align-center">
	<div class="review-align">
	<div class="contents-detail">
		<img src="${pageContext.request.contextPath}/images/dug_poster.jpg">
		<ul>
			<li class="review_title">${contents.title } 컨텐츠이름영역 디즈니!
			<!-- ott_num에 따라 ott 이름 다르게 표시 -->
				<c:if test="${contents.ott_num == 1 }">
					 NETFLIX</li>
				</c:if>
				<c:if test="${contents.ott_num == 2 }">
					 DISNEY+</li>
				</c:if>
				<c:if test="${contents.ott_num == 3 }">
					WATCHA</li>
				</c:if>
				<c:if test="${contents.ott_num == 4 }">
					TVING</li>
				</c:if>
				<c:if test="${contents.ott_num == 5 }">
					WAVVE</li>
				</c:if>
		 <li><hr size="1" width="300px" noshade="noshade"></li>
		</ul>
		<div class="review-star" >
			<span>별점총점</span>
		</div><!-- end of review_star -->
	</div><!-- end of contents_detail -->
	</div>
	
	<div class="end-float"></div>
	
	<div class="review-grey">
	<div class="review-align">
		<div class="review-form">
			${review.contents }
			리뷰 내용 영역
		</div><!-- end of review_form -->
	
		<!-- 댓글 영역 -->
		
		<c:if test="${count != 0 }">
		<div class="align-center">
			댓글 영역
		</div>
		<div class="align-center">
			<div id="com-page">
				${page}
			</div>
		</div>
		</c:if>
		<c:if test="${count == 0 }">
			<h2>댓글이 없습니다.</h2>
		</c:if>
	</div><!-- end of review_grey -->
	</div>
	</div>
</div>
</body>
</html>











