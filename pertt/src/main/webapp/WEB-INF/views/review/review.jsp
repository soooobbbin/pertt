<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${contents.title }</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/review.css"
	type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/review.js"></script>
<script>
$(document).ready(function(){
   $(document).on('click','.rating',function(e){
      let elem = e.target;
        if(elem.classList.contains('rate-check')){
           $(this).find('.rate-check').each(function(index, item){
                if(index < elem.value){
                    item.checked = true;
                }else{
                    item.checked = false;
                }
            });
            $(this).find('.rate-star').val(elem.value);
        }
   });
});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="align-center">
			<div class="review-align">
				<div class="contents-detail">
					<img id="contents-image" src="${pageContext.request.contextPath}/images/${contents.ott_num }/${contents.poster}">
					<ul>
						<li class="review_title">${contents.title }<!-- ott_num에 따라 ott 이름 다르게 표시 -->
							<c:if test="${contents.ott_num == 1 }">
					 		<span class="ott-name"> NETFLIX</span></li>
							</c:if>
							<c:if test="${contents.ott_num == 2 }">
					 		<span class="ott-name"> DISNEY+</span></li>
							</c:if>
							<c:if test="${contents.ott_num == 3 }">
							<span class="ott-name"> WATCHA</span></li>
							</c:if>
							<c:if test="${contents.ott_num == 4 }">
							<span class="ott-name"> TVING</span></li>
							</c:if>
							<c:if test="${contents.ott_num == 5 }">
							<span class="ott-name"> WAVVE</span></li>
							</c:if>
						<li>${contents.release } ${contents.genre } ${contents.country }</li>
						<li>${contents.produce}</li>
						<li>${contents.tomato }</li>
						<li id="star_avg"> 
							<c:if test="${starAvg >= 0}">
							평균별점 :★${starAvg }
							</c:if>
							<c:if test="${starAvg <= 0}">
							아직 별점을 받지 않았어요
							</c:if>
						</li>
						<li><hr size="1" width="300px" noshade="noshade"></li>
					</ul>
					<div class="review-star">
						<span id="myStar">
						</span>
						<div class="give-star">
							<!--  -->
							<div class="rating">
						      <input type="hidden" name="star" value="0" class="rate-star">    
			                <!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
			                <c:forEach var="star" begin="1" end="5" varStatus="status">
			                <input type="checkbox" id="rating${status.index}" value="${status.index}" class="rate-check"
			                      <c:if test="${status.index<=review.star}">checked</c:if>>
			                <label for="rating${status.index}"></label>
			                </c:forEach>
							</div>  
							<input type="button" value="전송" id="star_btn">
						</div>
					</div><!-- end of  review_star -->
			<span id="rating_text"></span>
	</div><!-- end of  contents_detail -->
	</div>
	
	<div class="end-float"></div>
	
	<div class="review-grey">
		<input type="hidden" id="c_num" name="c_num"  value="${contents.c_num}">
		<input type="hidden" id="user_num" name="user_num"  value="${u_num}">
		<input type="hidden" id="starCheck" name="starCheck"  value="${starCheck}">
		<input type="hidden" id="reviewCheck" name="reviewCheck"  value="${reviewCheck}">
	<div class="review-align">
		<div class="contents_plot"><!-- 배경 하얗게 -->
			<p id="content-plot-title">작품 소개</p>
			<p id="content-plot">${contents.plot }<p>
		</div>
		
		<c:if test="${reviewCheck==0}">
		<h2 class="write-review">리뷰 쓰기</h2>
		</c:if>

		<div class="review-form">
			<!-- 리뷰를 이미 작성한 경우 리뷰쓰기 폼 안 보임 -->
			<div id="review_duplicated">
				<!-- 리뷰 작성한적 있는 사용자 -->
			</div>
			
			<div id="review_notDuplicated">
				<!-- 리뷰 쓰기 폼 -->
			</div>
		</div><!-- end of review_form -->
		
		<!-- 리뷰 목록 영역 -->
		<c:if test="${count != 0 }">
		<div class="align-center" style="margin:40px 0 0 0;">
		<span id="text-review">리뷰</span>
			<div class="review-dropdown">
					<ul class="myMenu">
					    <li class="menu">
					        정렬기준
					        <ul class="menu_s submenu">
					            <li class="sort" data-num="1">최신순</li>
					            <li class="sort" data-num="2">추천순</li>
					            <li class="sort" data-num="3">댓글순</li>
					        </ul>  
					    </li>
					</ul>
			</div>
		</div>
		<div class="review-view" id="review-view"></div>
		<div class="end-float"></div>
		<div class="align-center">
			<div id="review-page">
				${page}
			</div>
		</div>
		</c:if>
		<c:if test="${count == 0 }" >
			<h2>리뷰가 없습니다.</h2>
		</c:if>
		
		<div class="paging-button" style="display:none">
			<input type="button" value="다음글 보기">
		</div>
	</div><!-- end of review_grey -->
	</div>
	</div>
</div>
</body>
</html>







