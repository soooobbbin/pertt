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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reviewDetail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reviewComment.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>	
	<div class="align-center">
	<div class="review-align">
	<div class="contents-detail">
		<img src="${pageContext.request.contextPath}/images/dug_poster.jpg">
		<ul>
			<li class="review_title">${contents.title }
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
		<div class="review-detail-box">
			<span id="review_id">${review.id }</span>
			<p id="review_content">${review.c_review_content }</p>
			<span id="output_comment"> 6</span>
			<span id="comment"> 댓글 </span>
			<span id="output_lcount"> </span>
			<img id="output_like" src="${pageContext.request.contextPath }/images/like1.png">
			<span id="review_regdate">${review.c_review_reg_date } </span>
			<c:if test="${!empty review.c_review_mod_date}">
				<span id="review_moddate"> 최근 수정일 : ${review.c_review_mod_date} </span>
			</c:if>
			<c:if test="${user_num == review.member_num}">
			<input type="button" data-reviewnum="${review.c_review_num }" value="수정" class="modify-btn">
			<input type="button" data-reviewnum="${review.c_review_num }" value="삭제" class="delete-btn">
			</c:if>
			
		</div><!-- end of review_form -->
		<div id="item" class="review-detail-box"></div><!-- 리뷰 수정 폼 나타나는 div -->
		
		<!-- 댓글 시작 -->
		<div id="com_div">
			<span class="com-title">댓글 달기</span>
			<form id="com_form">
				<input type="hidden" id="c_num" name="c_num" value="${contents.c_num }">
				<input type="hidden" id="c_review_num" name="c_review_num" value="${review.c_review_num }">
				<!-- 로그인 안됐을 시 댓글창 비활성화 -->
				<textarea rows="3" cols="50" id="com_content" 
				name="com_content" class="com-content"
				<c:if test="${empty user_num }">disabled="disabled"</c:if>
				><c:if test="${empty user_num }">로그인이 필요합니다.</c:if></textarea>
				<c:if test="${!empty user_num }" >
					<div id="com_first">
						<span class="letter-count">300/300</span>
					</div>
					<div id="com_second" class="align-right">
						<input type="submit" value="전송"> 
					</div>
				</c:if>
			</form>
		</div>
		
		<!-- 댓글 목록 출력 시작 -->
		<!-- 댓글 페이지 처리 -->
		<div id="com_output"></div>
		<div class="paging-button" style="display:none">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none">
			<img src="${pageContext.request.contextPath }/images/ajax-loader.gif">
		</div>
		<!-- 댓글 끝 -->
		
	</div><!-- end of review_grey -->
	</div>
	</div>
</div>
</body>
</html>











