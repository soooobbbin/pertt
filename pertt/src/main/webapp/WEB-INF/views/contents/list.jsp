<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>

<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>게시판 목록</h2>
		<form action="list.do" method="get" id="search_form">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1">제목</option>
						<option value="2">장르</option>
						<option value="3">제작사/배우</option>
					</select>     
				</li>
				<li>
					<input type="search" size="16" name="keyword" 
								id="keyword" value="${param.keyword}" >
				</li>				
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		
	
		<%--
		<c:forEach var="category" items="${list}">
		<h2>${category.category_name}</h2>
		</c:forEach> --%>
		<div class="list-space align-right">
			<c:if test="${!empty user_num}">
			<input type="button" value="작품 등록" onclick="location.href='adminRegisterContentsForm.do'">
			<input type="button" value="홈으로"
				 onclick="location.href='${pageContext.request.contextPath}/main/main_admin.do'">
			</c:if>
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>개봉일</th>
				<th>장르</th>
				<th>카테고리</th>
			</tr>
			<c:forEach var="contents" items="${list}">
			<tr>
				<td>${contents.c_num }</td>
				<td><a href="detail.do?c_num=${contents.c_num}">${contents.title}</a></td>
				<td>${contents.release}</td>
				<td>${contents.genre}</td>
				<c:if test="${content.category_num == 1}">
				<td>오리지널 영화</td>
				</c:if>
				<c:if test="${content.category_num == 6}">
				<td>오리지널 예능</td>
				</c:if>
				
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