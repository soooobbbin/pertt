<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
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
				<h2>SIGN UP</h2>
				<div class="register-main">
					<form class=register_form id="register_form"
						action="registerUser.do" method="post">
						<div class="register-inner">
							
							<table class="tb-2">
							
							<tr class="tr1">
								<td rowspan="2" class="member_id">아이디</td> 
								
								<td class="li-reg"><input type="text" name="member_id" id="member_id" maxlength="12" class="id"
									autocomplete="off" placeholder="아이디를 입력하세요"></td>
									<td class="td3"><input type="button" value="ID중복체크" id="id_check" class="reg-b"> <span id="message_id"></span></td>
								<tr><td class="form-notice">*영문 또는 숫자(4자~12자)</td>
									
								<tr>
								<td class="passwd">비밀번호</td> 
								<td class="li-reg"><input type="password" class="pw" name="passwd" id="passwd" maxlength="12"
									placeholder="비밀번호를 입력하세요"></td>
								<tr>
								<td class="name">이름</td> 
								<td class="li-reg"><input type="text" name="name" class="txt" id="name" maxlength="20" placeholder="이름을 입력하세요">
								</td>
								<tr>
								<td class="birth0">생년월일</td> 
								<td class="li-reg"><input type="number" name="birth1" id="birth1" maxlength="4" size="5" class="inBorder" /><span class="s">년 </span> 
									<select name="birth2" class="inBorder">
										<option value="01">01
										<option value="02">02
										<option value="03">03
										<option value="04">04
										<option value="05">05
										<option value="06">06
										<option value="07">07
										<option value="08">08
										<option value="09">09
										<option value="10">10
										<option value="11">11
										<option value="12">12
								</select><span class="s">월 </span><select name="birth3" class="inBorder">
										<option value="01">01
										<option value="02">02
										<option value="03">03
										<option value="04">04
										<option value="05">05
										<option value="06">06
										<option value="07">07
										<option value="08">08
										<option value="09">09
										<option value="10">10
										<option value="11">11
										<option value="12">12
										<option value="13">13
										<option value="14">14
										<option value="15">15
										<option value="16">16
										<option value="17">17
										<option value="18">18
										<option value="19">19
										<option value="20">20
										<option value="21">21
										<option value="22">22
										<option value="23">23
										<option value="24">24
										<option value="25">25
										<option value="26">26
										<option value="27">27
										<option value="28">28
										<option value="29">29
										<option value="30">30
										<option value="31">31
								</select><span class="s">일 </span></td>
								<tr>
								<td class="phone0">핸드폰</td> 
								<td class="li-reg"><select name="phone1"
									id="phone1" class="inBorder">
										<option value="010">010
										<option value="016">016
										<option value="017">017
										<option value="018">018
								</select> - <input type="number" name="phone2" id="phone2" maxlength="4"
									size="5" class="phone" placeholder="0000"> - <input
									type="number" name="phone3" id="phone3" maxlength="4" size="5"
									class="phone" placeholder="0000"></td>
									<tr>
								<td class="email">이메일</td> 
								<td class="li-reg"><input type="email"
									name="email" id="email" maxlength="50" class="txt"
									placeholder="test@example.com"></td>
									</tr>
									<tr>
									<td> </td>
									</tr>
										<tr>
									<td> </td>
									</tr>
									
									</table>
									</div>	
									<table>
									<tr>
								<td class="ck"><input type="submit" value="등록" class="reg-b2">
								<input type="button"
									value="홈으로" class="reg-b2"
									onclick="location.href='${pageContext.request.contextPath}/main/main.do'"></td>
							</tr>
							
							</table>
					
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>




