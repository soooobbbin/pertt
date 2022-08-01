<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급별 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>등급별 작품 목록</h2>
		<form id="search_form" action="gradeGroup.do" method="get">
			<ul class="list-search">
				<li>
					<select name="keyfield">
						<option value="1">등급</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword"
					       id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="찾기">
				</li>
			</ul>
		
		<div class="list-space align-right">
			<input type="button" value="목록" 
			     onclick="location.href='gradeGroup.do'">  
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 내용이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table class="list-tb">
			<tr>
				<th id="first-th">글번호</th>
				<th>제목</th>
				<th>개봉일</th>
				<th>장르</th>
				<th id="last-child">OTT</th>
			</tr>
			<c:forEach var="contents" items="${list}">
			<tr>
				<td>${contents.c_num}</td>
				<td><a href="detail.do?c_num=${contents.c_num}">${contents.title}</a></td>
				<td>${contents.release}</td>
				<td>${contents.genre}</td>
				<c:choose>
				<c:when test="${contents.ott_num == 1}">
				<td id="last-child">netflix</td>
				</c:when>
				<c:when test="${contents.ott_num == 2}">
				<td id="last-child">disney</td>
				</c:when>
				<c:when test="${contents.ott_num == 3}">
				<td id="last-child">watcha</td>
				</c:when>
				<c:when test="${contents.ott_num == 4}">
				<td id="last-child">tving</td>
				</c:when>
				<c:otherwise>
				<td id="last-child">wavve</td>
				</c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			${page}
		</div>
		</c:if>
		</form>
	</div>
</div>
</body>
</html>