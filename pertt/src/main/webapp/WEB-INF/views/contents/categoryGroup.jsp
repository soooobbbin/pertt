<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리별 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>등급별 작품 목록</h2>
		<form id="search_form" action="CategoryGroup.do" method="get">
			<ul class="list-search">
				<li>
					<select name="keyfield" size="1">
						<%-- 넷플릭스 카테고리 --%>
						<optgroup label="넷플릭스 카테고리">
							<option value="1">오리지널 영화</option>
							<option value="2">오리지널 드라마</option>
							<option value="3">오리지널 예능</option>
							<option value="4">영화/드라마/예능</option>
							<option value="5">지브리</option>
						</optgroup>
						<%-- 디즈니 카테고리 --%>
						<optgroup label="디즈니+ 카테고리">
							<option value="6">오리지널</option>
							<option value="7">디즈니</option>
							<option value="8">픽사</option>
							<option value="9">마블</option>
							<option value="10">내셔널지오그래픽</option>
						</optgroup>
						<%-- 왓챠 카테고리(오리지널 : 중복이라제외) --%>
						<optgroup label="왓챠 카테고리">
							<option value="11">오리지널</option>
							<option value="12">왓챠 독점</option>
							<option value="13">인디영화</option>
							<option value="14">일본작품</option>
							<option value="15">단편영화</option>
						</optgroup>
						<%-- 티빙 카테고리(오리지널드라마/예능 : 중복이라제외) --%>
						<optgroup label="티빙 카테고리">
							<option value="16">오리지널 드라마</option>
							<option value="17">오리지널 예능</option>
							<option value="18">TVN</option>
							<option value="19">Mnet</option>
							<option value="20">OCN</option>
						</optgroup>
						<%-- 웨이브 카테고리(오리지널 : 중복이라제외) --%>
						<optgroup label="웨이브 카테고리">
							<option value="21">오리지널</option>
							<option value="22">KBS</option>
							<option value="23">MBC</option>
							<option value="24">SBS</option>
							<option value="25">IDOL 리얼리티</option>
						</optgroup>
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
			     onclick="location.href='CategoryGroup.do'">  
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
				<th>카테고리</th>
				<th>장르</th>
				<th id="last-child">OTT</th>
			</tr>
			<c:forEach var="contents" items="${list}">
			<tr>
				<td>${contents.c_num}</td>
				<td><a href="detail.do?c_num=${contents.c_num}">${contents.title}</a></td>
				<td>${contents.release}</td>
				<td>${contents.category_name}</td>
				<%-- 카테고리 표시 
				<c:choose>
				<c:when test="${contents.category_num == 1}">
				<td>오리지널 영화</td>
				</c:when>
				<c:when test="${contents.category_num == 2}">
				<td>오리지널 드라마</td>
				</c:when>
				<c:when test="${contents.category_num == 3}">
				<td>오리지널 예능</td>
				</c:when>
				<c:when test="${contents.category_num == 4}">
				<td>영화/드라마/예능</td>
				</c:when>
				<c:when test="${contents.category_num == 5}">
				<td>지브리</td>
				</c:when>
				<c:otherwise>
				<td>wavve</td>
				</c:otherwise>
				</c:choose> --%> 
				
				<td>${contents.genre}</td>
				<%-- ott표시 --%>
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