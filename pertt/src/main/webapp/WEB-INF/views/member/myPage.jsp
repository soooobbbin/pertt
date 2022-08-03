<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>MY페이지</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/contents.js"></script>
</head>

<body>
<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		
			
				<h2>MY PAGE</h2>
				<div class="content-main">
				
					<div class="mp-my3">
					<form class=mypage_form id="mypage_form"
						action="myPage.do" method="post">
						<div class="mypage-inner">
					<!-- <input type="hidden" id="member_num" value="${member.member_num}">-->
					<table class="mp-info">
						<tr>
							<td colspan="2" class="mp-TxT">회원정보</td>
						</tr>
						
						<tr>
							<td class="mp-1">아이디</td>
							<td class="mp-4">${member.member_id}</td>
						</tr>
						<tr>
							<td class="mp-1">비밀번호</td>
							<td class="mp-4"><c:if test="${member.passwd ne null && member.passwd!=''}">${fn:substring(member.passwd,0,fn:length(member.passwd)-3)}***</c:if></td>
						</tr>
						<tr>
							<td class="mp-1">이름</td>
							<td class="mp-4">${member.name}</td>
						</tr>
						<tr>
							<td class="mp-1">생년월일</td>
							<td class="mp-4">${member.birth}</td>
						</tr>
						<tr>
							<td class="mp-1">휴대폰</td>
							<td class="mp-4">${member.phone}</td>
						</tr>
						<tr>
							<td class="mp-1">이메일</td>
							<td class="mp-4">${member.email}</td>
						</tr>
						<tr>
							<td class="mp-3">가입일</td>
							<td class="mp-5">${member.reg_date}</td>
						</tr>
					</table>
					</div><!-- 35번줄 mypage_inner  -->
					
					<ul class="mp-btn">
						<li><input type="button" value="수정" class="blue-btn"
							onclick="location.href='modifyUserForm.do'"> <input
							type="button" value="회원탈퇴" class="gray-btn"
							onclick="location.href='deleteUserForm.do'"></li>
					</ul>
					
				</form> <!-- 33번줄 mypage_form  -->
						
						 	
					<div class="myre_main">
						<form action="Review.do" id="Review" method="post" class="mypage-my4">
						 	
						 	<div class="mp-TxT">내 글 조회</div>
						 	
					<div class="re">

							<div class="my-ottRe">
							OTT 리뷰<input type="submit" value="삭제" class="delete-btn">
							</div>
							<ul>
								<li class="ottRe"><input type="checkbox" name="oRe_delete"></li>
								<li class="ottRe"><select name="ottReview">
									<option value="넷플릭스">넷플릭스</option>
									<option value="디즈니플러스">디즈니+</option>
									<option value="티빙">티빙</option>
									<option value="왓챠">왓챠</option>
									<option value="웨이브">웨이브</option>
							</select></li>
							<li class="ottRe" style="color:#fcbf19;"><b>★ 3</b></li>
							<li class="ottRe">오리지널 작품에 볼 게 없다</li>
							</ul>
					</div>
					
					<!-- 작품 리뷰 -->
			<div class="re">
						<div class="my-ottRe">
							작품 리뷰
							<div class="more">
							<!-- 더보기 아이콘 > 리뷰 리스트로 이동 -->									
								<img src="${pageContext.request.contextPath}/images/더보기.png"
									width="40px" height="25px" onclick="location.href='myReviewList.do'">
							</div> <!-- 더보기 아이콘 닫음 -->
							</div>
					
					
						<!-- 리뷰 목록 부분 -->
				
				<div class="rere">
						<c:forEach var="review" items="${review }" >

					<div class="rere2">
						<a href="${pageContext.request.contextPath}/review/reviewDetail.do?c_review_num=${review.c_review_num}&c_num=${review.c_num}">
							<img class="myre_poster" id="contents-image" src="${pageContext.request.contextPath}/images/${review.ott_num}/${review.poster}">
						 <span style="color:#fcbf19;" class="star_re"><b>★ ${review.star}</b></span><br>
							<span id="regdate">${review.c_review_reg_date}</span>
						</a>
					</div>	
						</c:forEach>
						
				 </div>
					
					 <div class="float-clear">
		    			<hr width="100%" size="1" noshade="noshade" class="hrmy">
		    		</div>
			</div>
				
				<!-- 댓글 목록 -->
						<div class="re">
							<div class="my-ottRe">
								내 댓글 목록			
								<div class="more">
									<img src="${pageContext.request.contextPath}/images/더보기.png"
										width="40px" height="25px" onclick="location.href='myCommentList.do'">
									
								</div>
							</div>
							<c:forEach var="comment" items="${comment }" >
							<div class="rere3">
									<div id="my_com" class="my_com">
									<div class="comment-box" 
									onclick="location.href='${pageContext.request.contextPath}/review/reviewDetail.do?c_review_num=${comment.c_review_num}&c_num=${comment.c_num}'">
										<span id="com_reg_date" style="color:#969696;" > ${comment.com_reg_date } | </span>
										<span id="review_content" style="color:#828282;"> 리뷰 내용 :  ${fn:substring(comment.review.c_review_content,0,12)}… </span><br>
										<span id="com_content"> ${fn:substring(comment.com_content, 0, 25)} </span>
										 <div class="float-clear"><hr class="hrmy2"></div>
									</div>
									</div></div>
								</c:forEach>
								</div>
						 	
						 		
						 	</form> <!-- mypage-my  -->
						 	
						 	</div> <!--  myre_main  -->
						 	</div></div><!--  mypage_main  -->
						 	</div>
						
					
				
				
</body>
</html>
