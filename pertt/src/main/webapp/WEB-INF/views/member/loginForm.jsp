<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="align-center">
			<div class="content-main">

				<h2>LOGIN</h2>
				<div class="login-main">
					<form id="login_form" action="login.do" method="post">

						<table class="td1" >
							<tr>
								<td><b>아이디</b></td>
								<td class="td1"><input type="text" name="member_id" id="member_id" class="log-id"
									maxlength="12" autocomplete="off" class="ip" placeholder="아이디를 입력하세요"></td>
							<tr>
								<td><b>비밀번호</b></td>
								<td class="td1"><input type="password" name="passwd" id="passwd" class="log-pw"
									maxlength="12" class="ip" placeholder="비밀번호를 입력하세요"></td>
						</table>
						<div class="login-btn">
							<input type="submit" value="로그인" class="log-b"> 
							<input type="button" value="홈으로" class="log-b"
								onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>







