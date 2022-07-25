<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_admin.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<div>
		<h2 class="admin_main_h2">전체 작품</h2></div>
		<div>
		<p class="admin_main_p">관리메뉴에서 회원 등급 변경이 가능하며, 내보내기(탈퇴)기능이 가능합니다.</p>
		</div>
		
		<div>
			<form action="ottContents.do" method="get" id="search_form">
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
			</form> 
		
			<c:forEach var="category" items="${list}">
			<h2>${category.category_name}</h2>
			</c:forEach>
			<c:forEach var="category" items="${list}">
			<h2>${category.category_name}</h2>
			</c:forEach>
			<div class="list-space align-right">
				<c:if test="${!empty user_num}">
				<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
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
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
				</tr>
				<c:forEach var="board" items="${list}">
				<tr>
					<td>${board.board_num }</td>
					<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td>
					<td>${board.id}</td>
					<td>${board.reg_date}</td>
					<td>${board.hit}</td>
				</tr>
				</c:forEach>
			</table>
			<div class="align-center">
				${page}
			</div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>





