<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" type="text/css">
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
					<input type="text" name="birth1" id="birth1" maxlength="4" size="5" class="inBorder" />년
                <select name = "birth2" class="inBorder">
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
                </select> 월
                <select name = "birth3" class="inBorder">
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
                </select> 일
					 
				</li>
				<li>
					<label for="phone">핸드폰</label>
					<select name="phone1" id="phone1" class="inBorder">
                    <option value="010">010
                    <option value="016">016
                    <option value="017">017
                    <option value="018">018
                </select>-
					<input type="text" name="phone2" id="phone2" maxlength="4" size="5" placeholder="0000">-
					<input type="text" name="phone3" id="phone3" maxlength="4" size="5" placeholder="0000">
					
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




