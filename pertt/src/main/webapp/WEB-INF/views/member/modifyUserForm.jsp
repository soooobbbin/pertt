<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="mypage-main">
	<h2 class="mp-sub">MY PAGE</h2>
		
		 <form class="mp-form" action="modifyUser.do" method="post"> 
			<table  class="mp-info">
				<tr > 
					<td colspan=2 class="mp-info-T" > 회원정보</td>
				</tr>
				<tr >
					<td >내 OTT</td>
					<td class="mptd"><input type="radio"  name="ott_c" value="넷플릭스">넷플릭스 
                    <input type="radio" name="ott_c" value="디즈니+">디즈니+ <br> 
                    <input type="radio" name="ott_c" value="넷플릭스">왓챠
                    <input type="radio" name="ott_c" value="티빙">티빙
                    <input type="radio" name="ott_c" value="웨이브">웨이브</td>
				</tr>
				<tr>
					<td >아이디</td>
					<td ><input type="text" name="id" id="m_id"
					                     maxlength="12"></td>
				</tr>
				<tr>
					<td >현재 비밀번호</td>
					<td ><input type="password" name="origin_passwd" 
					        id="origin_passwd" maxlength="12"></td>
				</tr>
				<tr>
					<td >새 비밀번호</td>
					<td><input type="password" name="passwd" id="passwd"
					                     maxlength="12"></td>
				</tr>
				<tr class="mptr">
					<td >새 비밀번호 확인</td>
					<td><input type="password" name="cpasswd" id="cpasswd"
					                     maxlength="12"><br>
					 <span id="message_cpasswd"></span></td>
				</tr>
				<tr>
					<td>이름</td>
					<td ><input type="text" name="member_id" id="member_id"
					                     maxlength="12"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="birth1" id="birth1" maxlength="4" size="5" class="inBorder" />년
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
                </select>월
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
                </select>일</td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><select name="phone1" id="phone1" class="inBorder">
                    <option value="010">010
                    <option value="016">016
                    <option value="017">017
                    <option value="018">018
                </select> -
					<input type="text" name="phone2" id="phone2" maxlength="4" size="5" > -
					<input type="text" name="phone3" id="phone3" maxlength="4" size="5" >
					</td>
				</tr>
				<tr >
					<td class="mp-td2">이메일</td>
					<td class="mp-td2"><input type="text" name="email" id="email"
					                     maxlength="12"></td>
				</tr>
				
				
				</table>
			
			<ul class="mp-btn">
				<li>
				<input type="submit" value="수정" class="blue-btn" onclick="location.href='myPage.do'">
				
				<input type="button" class="gray-btn" value="취소"
				    onclick="location.href='myPage.do'">
				   </li> 
			</ul>
		 </form> 
	</div>
</div>
</body>
</html>