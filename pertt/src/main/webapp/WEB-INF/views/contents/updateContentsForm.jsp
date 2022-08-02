<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>작품 정보 수정</h2>
		<form action="updateContents.do" method="post"
		      enctype="multipart/form-data" id="write_form">
			<input type="hidden" name="c_num" value="${contents.c_num}">
			<ul>
				<li>
					<c:if test="${contents.ott_num == 1 || contents.ott_num == 2}">
					<img src="${pageContext.request.contextPath}/upload/${contents.poster}" width="300" height="170" class="con-poster">
					</c:if>
					<c:if test="${contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
					<img src="${pageContext.request.contextPath}/upload/${contents.poster}" width="130" height="160" class="con-poster">
					</c:if>
					<label for="poster">포스터</label>
					<div class="poster-area">
						<input type="file" value="파일 선택" id="poster_btn" name="poster">
					</div>  
				</li>
				<li>
					<label for="title">제목</label>
					<input type="text" name="title" id="title"
					    value="${contents.title}" maxlength="50">
				</li>
				<li>
					<label for="release">개봉일</label>
					<input type="text" name="release" id="release"
					    value="${contents.release}" maxlength="50">
				</li>
				<li>
					<label for="country">국가</label>
					<input type="text" name="country" id="country"
					    value="${contents.country}" maxlength="50">
				</li>
				<li>
					<label for="genre">장르</label>
					<input type="text" name="genre" id="genre"
					    value="${contents.genre}" maxlength="50">
				</li>
				<li>
					<label for="plot">줄거리</label>
					<textarea rows="5" cols="30" name="plot"
					   id="plot">${contents.plot}</textarea>
				</li>
				<li>
					<label for="tomato">러닝타임</label>
					<input type="text" name="tomato" id="tomato"
					    value="${contents.tomato}" maxlength="50">
				</li>
				<li>
					<label for="produce">출연/제작</label>
					<input type="text" name="produce" id="produce"
					    value="${contents.produce}" maxlength="50">
				</li>
				<li>
					<label for="grade">등급</label>
					<input type="text" name="grade" id="grade"
					    value="${contents.grade}" maxlength="50">
				</li>
				<li>
					<label for="category">카테고리</label>
					<input type="number" name="category_num" id="category"
					    value="${contents.category_num}" maxlength="50">
				</li>
				<li>
					<label for="ott">OTT</label>
					<input type="radio" id="netfilx" name="ott_num" value="1">넷플릭스
					<input type="radio" id="disney" name="ott_num" value="2">디즈니
					<input type="radio" id="watcha" name="ott_num" value="3">왓챠
					<input type="radio" id="tving" name="ott_num" value="4">티빙
					<input type="radio" id="wavve" name="ott_num" value="5">웨이브
				</li>
			</ul> 
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="목록"
				         onclick="location.href='list.do'">
			</div>                      
		</form>
	</div>
</div>
</body>
</html>