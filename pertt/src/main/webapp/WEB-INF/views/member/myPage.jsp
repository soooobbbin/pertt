<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="mypage-main">
		<h2 class="mp-sub">MY PAGE</h2>
		
		<form class="mp-form" > 
				<table  class="mp-info">
				<tr> 
					<td colspan=2 class="mp-info-T"> 회원정보</td>
				</tr>
				
				<tr>
					<td >아이디</td>
					<td >${member.member_id}</td>
				</tr>
				<tr>
					<td >비밀번호</td>
					<td >${member.passwd}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td >${member.name}</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>${member.birth}</td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td>${member.phone}</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td >${member.email}</td>
				</tr>
				<tr >
					<td class="mp-td2" >가입일</td>
					<td class="mp-td2">${member.reg_date}</td>
				</tr>
				
				</table>
			</form>	
				
			<ul class="mp-btn">
				<li>
					<input type="button" value="수정" class="blue-btn"
					    onclick="location.href='modifyUserForm.do'">
				
					<input type="button" value="회원탈퇴" class="gray-btn"
					onclick="location.href='deleteUserForm.do'">
				</li>
		
			</ul>
			
		</div>
		<div class="mypage-end"></div>
	</div>

</body>
</html>

