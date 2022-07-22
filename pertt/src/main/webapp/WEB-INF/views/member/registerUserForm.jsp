<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="content-main">
		<h2>회원가입</h2>
		<form id="register_form" action="registerUser.do"
		                           method="post">
			<ul>
				<li>
					<label for="member_id">아이디</label>
					<input type="text" name="member_id" id="member_id"
					  maxlength="12" autocomplete="off" placeholder="아이디를 입력하세요">
					<input type="button" value="ID중복체크"
					      id="id_check">
					<span id="message_id"></span>
					<div class="form-notice">*영문 또는 숫자(4자~12자)</div>
				</li>
				<li>
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd" id="passwd" maxlength="12" placeholder="비밀번호를 입력하세요">
				</li>
				<li>
					<label for="name">이름</label>
					<input type="text" name="name" id="name" maxlength="10" placeholder="이름을 입력하세요">
				</li>
				<li>
					<label for="birth">생년월일</label>
					<input type="text" name="birth" id="birth" maxlength="10" placeholder="생년월일 6자리를 입력하세요">
				</li>
				<li>
					<label for="phone">핸드폰</label>
					<input type="text" name="phone" id="phone" maxlength="15" placeholder="휴대폰번호를 입력하세요">
					<div class="form-notice">*번호만 입력해주세요(예시:01012345678)</div>
				</li>
				<li>
					<label for="email">이메일</label>
					<input type="email" name="email" id="email" maxlength="50" placeholder="이메일을 입력하세요">
				</li>
				
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="홈으로"
				    onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</form>
	</div>



</div>
</body>
</html>




