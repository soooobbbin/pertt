<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보(관리자 전용)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>${member.member_id}의 회원정보 (관리자 전용)</h2>
		<form action="detailUser.do" method="post"
		      id="detail_form">
			<input type="hidden" name="member_num"
			                    value="${member.member_num}">
			<ul>
				<li>
					<label>등급</label>
					<c:if test="${member.auth != 2}">
					<input type="radio" name="auth" value="0" id="auth0"
					    <c:if test="${member.auth == 0}">checked</c:if>/>탈퇴
				  <input type="radio" name="auth" value="1" id="auth1"
				    	<c:if test="${member.auth == 1}">checked</c:if>/>일반    
					</c:if>
					<c:if test="${member.auth == 2}">
					<input type="radio" name="auth" value="2"
					                    id="auth2" checked>관리
					</c:if>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="목록"
				      onclick="location.href='memberList.do'">
			</div>  
			
			<ul>
				<li>
					<label>이름</label>${member.name}
				</li>
				<li>
					<label>전화번호</label>${member.phone}
				</li>
				<li>
					<label>이메일</label>${member.email}
				</li>
				<li>
					<label>생년월일</label>${member.birth}
				</li>
			</ul>    
		</form>
	</div>
</div>
</body>
</html>







