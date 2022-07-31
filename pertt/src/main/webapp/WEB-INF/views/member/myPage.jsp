<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>MY페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/member.js"></script>

</head>

<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="mypage-main">
			<h2 class="mp-sub">MY PAGE</h2>


			<div class="mypage-my2">
				<form class="mp-form">
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
							<td class="mp-4">${member.passwd}</td>
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

					<ul class="mp-btn">
						<li><input type="button" value="수정" class="blue-btn"
							onclick="location.href='modifyUserForm.do'"> <input
							type="button" value="회원탈퇴" class="gray-btn"
							onclick="location.href='deleteUserForm.do'"></li>
					</ul>
				</form>
			</div>

			<form action="Review.do" id="Review" method="post" class="mypage-my">
			
					<div class="mp-TxT">내 글 조회</div>

					<div class="re">

						<div class="my-ottRe">
							OTT 리뷰 <input type="submit" value="삭제" class="delete-btn">
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
							<li class="ottRe">star ★★★</li>
							<li class="ottRe">감상평</li>
						</ul>
					</div>

					<div class="re">
						<div class="my-ottRe">
							작품 리뷰
							<div class="more">


								<img src="${pageContext.request.contextPath}/images/더보기.png"
									width="40px" height="25px"
									onclick="location.href='reviewDetail.do?c_review_num=0&c_num&member_num=${contents.c_num}'">


							</div>

						</div>
						<div>
							<ul class="more">
								<li class="sort" data-num="1">별점순</li>
								<li class="l">|</li>
								<li class="sort" data-num="2">최신순</li>
							</ul>
						</div>

						<div class="my-re">
							<!-- 클릭시 리뷰 페이지로 이동 -->
							<c:if test="${!empty contents.poster}">
								<img class="rp-image"
									src="${pageContext.request.contextPath}/upload/${contents.poster}"
									onclick="location.href='reviewDetail.do?c_review_num=0&c_num=${contents.c_num}'">
							</c:if>
							<c:if test="${empty contents.poster}">
								<img class="rp-image"
									src="${pageContext.request.contextPath}/images/blank2.png">
							</c:if>
							<!-- 내가 준 리뷰 - 작품 별점 -->
							<div class="review-star">${review.c_star_num}</div>
							<!-- 내가 준 리뷰 - 리뷰 등록일 -->
							<span id="review_regdate">${review.c_review_reg_date} </span>

						</div>
					</div>


					<form action="deleteComment.do" method="post" id="deleteC_form">
						<div class="re">
							<div class="my-ottRe">
								내 댓글 목록

								<div class="more">
									<img src="${pageContext.request.contextPath}/images/더보기.png"
										width="40px" height="25px">
								</div>

								<input type="submit" value="삭제" class="delete-btn">
							</div>
							<table>
								<tr>
									<td rowspan="2"><input type="checkbox" name="com_delete"
										value="삭제"></td>
									<!--등록일 -->
									<td colspan="2">등록일 ${comment.com_reg_date}</td>
									<!--리뷰 컨텐츠 이름-->
									<td>컨텐츠 명 : ${contents.title}</td>
								</tr>
								<tr>
									<!-- 댓글 내용 -->
									<td>내용${comment.com_content}</td>
								</tr>

							</table>
						</div>
					</form>
					
					
				
			</form>
		</div>
	</div>

</body>
</html>

