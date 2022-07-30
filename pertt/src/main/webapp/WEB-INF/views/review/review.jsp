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
						<li>${contents.release } ${contents.genre } ${contents.country }</li>
						<li>${contents.produce}</li>
						<li>${contents.tomato }</li>
						<li id="star_avg">평균별점 : ★${starAvg }</li>
						<li><hr size="1" width="300px" noshade="noshade"></li>
					</ul>
					<div class="review-star">
						<span id="myStar">
							<c:if test="${starCheck}">내별점 ★${review.star }</c:if>
						</span>
						<div class="give-star">
							<!--  -->
							<div class="rating">
						      <input type="hidden" name="star" value="0" class="rate-star">    
						                <!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
						                <input type="checkbox" id="rating1" value="1" class="rate-check">
						                <label for="rating1"></label>
						                <input type="checkbox" id="rating2" value="2" class="rate-check">
						                <label for="rating2"></label>
						                <input type="checkbox" id="rating3" value="3" class="rate-check">
						                <label for="rating3"></label>
						                <input type="checkbox" id="rating4" value="4" class="rate-check">
						                <label for="rating4"></label>
						                <input type="checkbox" id="rating5" value="5" class="rate-check">
						                <label for="rating5"></label>
							</div>  
							<input type="button" value="전송" id="star_btn">
							<script>
							$(function(){
								$('#star_btn').click(function(){
									let star = $('.rate-star').val();
									alert(star);
									$.ajax({
										url:'giveStar.do',
										type:'post',
										data:{star:star, c_num:${contents.c_num}},
										dataType:'json',
										cache:false,
										timeout:30000,
										success:function(param){
											if(param.result == 'logout'){
												alert('로그인해야 작성할 수 있습니다.');
											}else if(param.result == 'success'){
												//위에 span 태그에 내 별점 띄워주기
												$('#myStar').html('내별점 ★'+star);
												$('.give-star').append(myStar);
												//별점 총점을 다시 호출함
												$('#star_avg').text('평균별점 : ★'+param.starAvg);
												//$('#after_star').css('display','block');
												$('#after_star').show();
												$('#review_notDuplicated').hide();
											}
										},
										error:function(){
											alert('별점 등록에서 네트워크 오류 발생');	
										}
									});
								});
							});
							</script>
							<!--  -->
						</div>
					</div><!-- end of review_star -->
			<span id="rating_text"></span>
	</div><!-- end of contents_detail -->
	</div>
	
	<div class="end-float"></div>
	
	<div class="review-grey">
	<div class="review-align">
		<div class="contents_plot"><!-- 배경 하얗게 -->
			<p id="content-plot-title">작품 소개</p>
			<p id="content-plot">${contents.plot }<p>
		</div>
		
		<c:if test="${!reviewCheck}">
		<h2 class="write-review">리뷰 쓰기</h2>
		</c:if>
		<div class="review-form">
		<input type="hidden" id="c_num2" name="c_num2" value="${contents.c_num}">
		<!-- 리뷰를 이미 작성한 경우 리뷰쓰기 폼 안 보임 -->
		<c:if test="${reviewCheck}">
		<div id="review_duplicated">
			<h2>이미 리뷰를 작성했습니다.</h2>
			<input id="myReview" type="button" value="내 리뷰 보러가기" 
				onclick="location.href='reviewDetail.do?c_review_num=0&c_num=${contents.c_num}'">
		</div>
		</c:if>
		<c:if test="${!reviewCheck}">
		<div id="review_notDuplicated">
			<!-- 리뷰 쓰기 폼 -->
			<form id="review_form">
				<input type="hidden" id="c_num" name="c_num"  value="${contents.c_num}">
				<textarea rows="10" cols="80" id="r_content" name="content" 
				<c:if test="${empty user_num or !starCheck}">disabled="disabled"</c:if>
				placeholder="리뷰를 입력해주세요"
				><c:if test="${empty user_num }">로그인이 필요합니다.</c:if><c:if test="${!starCheck }">별점을 준 후에 이용하세요.</c:if></textarea>
				<c:if test="${!empty user_num  and starCheck}">
					<input type="submit" value="등록">
				</c:if>
			</form>
		</div>
		</c:if>
		<div id="after_star" style="display:none;">
			<!-- 리뷰 쓰기 폼 -->
			<form id="review_form2">
				<input type="hidden" id="c_num" name="c_num"  value="${contents.c_num}">
				<textarea rows="10" cols="80" id="r_content" name="content" 
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				placeholder="리뷰를 입력해주세요"
				><c:if test="${empty user_num }">로그인이 필요합니다.</c:if></textarea>
				<c:if test="${!empty user_num }">
					<input type="submit" value="등록">
				</c:if>
			</form>
		</div>
		</div><!-- end of review_form -->
		
		<!-- 리뷰 목록 영역 -->
		<c:if test="${count != 0 }">
		<div class="align-center">
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
		<c:if test="${count == 0 }">
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







