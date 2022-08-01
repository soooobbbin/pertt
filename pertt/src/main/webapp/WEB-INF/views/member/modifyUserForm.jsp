<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
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

				<form class="mp-form" action="modifyUser.do" method="post"
					id="modify_form">
					<table class="mp-info">
						<tr>
							<td colspan="2" class="mp-TxT">회원정보</td>
						</tr>

						<tr>
							<td class="mp-1">아이디</td>
							<td class="mp-4">${member.member_id}</td>
						</tr>
						<tr>
							<td class="mp-1">현재 비밀번호</td>
							<td class="mp-4"><input type="password" name="origin_passwd"
								id="origin_passwd" maxlength="12"></td>
						</tr>
						<tr>
							<td class="mp-1">새 비밀번호</td>
							<td><input type="password" name="passwd" id="passwd"
								maxlength="12"></td>
						</tr>
						<tr>
							<td class="mp-1">새 비밀번호 확인</td>
							<td><input type="password" name="cpasswd" id="cpasswd"
								maxlength="12"><br> <span id="message_cpasswd"
								style="color: rgb(46, 204, 113); font-weight: lighter;"></span></td>
						</tr>
						<tr>
							<td class="mp-1">이름</td>
							<td>${member.name}</td>
						</tr>
						<tr>
							<td class="mp-1">생년월일</td>
							<td>${member.birth}</td>
						</tr>
						<tr>
							<td class="mp-1">휴대폰</td>
							<td><select name="phone1" id="phone1" class="inBorder">
									<option value="010">010
									<option value="016">016
									<option value="017">017
									<option value="018">018
							</select> - <input type="number" name="phone2" id="phone2" maxlength="4"
								size="5"> - <input type="number" name="phone3" id="phone3"
								maxlength="4" size="5"></td>
						</tr>
						<tr>
							<td class="mp-3">이메일</td>
							<td class="mp-2"><input type="text" name="email" id="email">
								
								</td>
						</tr>


					</table>

					<ul class="mp-btn">
						<li><input type="submit" value="수정" class="blue-btn">
							<input type="button" value="뒤로가기" class="gray-btn"
							onclick="location.href='myPage.do'"></li>
					</ul>
				</form>
			</div>
			<div class="mypage-my">
				<div class="mp-TxT">내 글 조회</div>

				<div class="re">

					<div class="my-ottRe">OTT리뷰</div>
					<ul>
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
								width="40px" height="25px">
						</div>
					</div>
					<div>
						<ul class="more">
							<li class="sort">별점순</li>
							<li class="l">|</li>
							<li class="sort">최신순</li>
						</ul>
					</div>

				</div>

				<div class="re">
					<div class="my-ottRe">
						내 댓글 목록
						<div class="more">
							<img src="${pageContext.request.contextPath}/images/더보기.png"
								width="40px" height="25px">
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>