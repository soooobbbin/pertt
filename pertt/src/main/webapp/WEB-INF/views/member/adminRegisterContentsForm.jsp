<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>작품 정보 등록</h2>
		<form id="contentsRegister_form" action="adminRegisterContents.do" method="post" enctype="multipart/form-data">
	
			<%-- 작품 포스터 등록 --%>
			<div class="contents-poster">
				<ul>
					<li>
						<c:if test="${empty contents.poster}">
						<img src="${pageContext.request.contextPath}/images/no_image.png" width="130" height="160" class="con-poster">
						</c:if>
						<c:if test="${!empty contents.poster}">
						<img src="${pageContext.request.contextPath}/upload/${contents.poster}" width="130" height="160" class="con-poster">
						</c:if>
					</li>
					<li>
						<div class="align-center">
							<input type="button" value="파일 선택"
							       id="poster_btn">
						</div>
						<div id="poster_choice" style="display:none;">
							<input type="file" id="photo" 
							     accept="image/gif,image/png,image/jpeg">
							<input type="button" value="전송" id="photo_submit">
							<input type="button" value="취소" id="photo_reset">     
						</div>
					</li>
				</ul>
			</div>
			<%-- 작품 상세 정보 등록 --%>
			<div class="contents-details">
				<table>
					<tr>
						<td>제목</td>
						<td id="last-child"><input type="text" id="title" name="title" placeholder="제목을 입력하세요"></td>
					</tr>
					<tr>
						<td>개봉일</td>
						<td id="last-child"><input type="date" id="release" name="release" value="YYYY-MM-DD"></td>
					</tr>
					<tr>
						<td>국가</td>
						<td id="last-child"><input type="text" id="country" name="country" placeholder="국가를 입력하세요"></td>
					</tr>
					<tr>
						<td>장르</td>
						<td id="last-child">
							<select name="genre" size="1">
								<option label="장르">
								<option value="로맨스">로맨스</option>
								<option value="스릴러">스릴러</option>
								<option value="코미디">코미디</option>
								<option value="드라마">드라마</option>
								<option value="SF">SF</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>토마토 지수</td>
						<td id="last-child"><input type="text" id="tomato" name="tomato" placeholder="토마토 지수를 입력하세요"></td>
					</tr>
					<tr>
						<td>줄거리</td>
						<td id="last-child"><textarea rows="3" cols="30" name="plot" id="plot" placeholder="줄거리를 입력하세요"></textarea></td>
					</tr>
					<tr>
						<td>출연/제작</td>
						<td id="last-child"><input type="text" id="produce" name="produce" placeholder="출연/제작란을 입력하세요"></td>
					</tr>
					<tr>
						<td>등급</td>
						<td id="last-child">
							<select name="grade" size="1">
								<option value="등급">등급</option>
								<option value="ALL">ALL</option>
								<option value="12세 이상">12세 이상</option>
								<option value="15세 이상">15세 이상</option>
								<option value="19세 이상">19세 이상</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>카테고리</td>
						<td id="last-child">
							<select name="category" size="1">
								<%-- 넷플릭스 카테고리 --%>
								<optgroup label="넥플릭스 카테고리">
								<option value="오리지널 영화">오리지널 영화</option>
								<option value="오리지널 드라마">오리지널 드라마</option>
								<option value="오리지널 예능">오리지널 예능</option>
								<option value="영화/드라마/예능">영화/드라마/예능</option>
								<option value="지브리">지브리</option>
								</optgroup>
								<%-- 디즈니 카테고리 --%>
								<optgroup label="디즈니+ 카테고리">
								<option value="오리지널">오리지널</option>
								<option value="디즈니">디즈니</option>
								<option value="픽사">픽사</option>
								<option value="마블">마블</option>
								<option value="내셔널지오그래픽">내셔널지오그래픽</option>
								</optgroup>
								<%-- 왓챠 카테고리(오리지널 : 중복이라제외) --%>
								<optgroup label="왓챠 카테고리">
								<option value="오리지널">오리지널</option>
								<option value="왓챠 독점">왓챠 독점</option>
								<option value="인디영화">인디영화</option>
								<option value="일본작품">일본작품</option>
								<option value="단편영화">단편영화</option>
								</optgroup>
								<%-- 티빙 카테고리(오리지널드라마/예능 : 중복이라제외) --%>
								<optgroup label="티빙 카테고리">
								<option value="오리지널 드라마">오리지널 드라마</option>
								<option value="오리지널 예능">오리지널 예능</option>
								<option value="TVN">TVN</option>
								<option value="Mnet">Mnet</option>
								<option value="OCN">OCN</option>
								</optgroup>
								<%-- 웨이브 카테고리(오리지널 : 중복이라제외) --%>
								<optgroup label="웨이브 카테고리">
								<option value="오리지널">오리지널</option>
								<option value="KBS">KBS</option>
								<option value="MBC">MBC</option>
								<option value="SBS">SBS</option>
								<option value="IDOL 리얼리티">IDOL 리얼리티</option>
								</optgroup>
							</select>
						</td>
					</tr>
					<tr>
						<td>OTT</td>
						<td id="last-child">
							<input type="radio" id="netfilx" name="netfilx" value="1">넷플릭스
							<input type="radio" id="disney" name="disney" value="2">디즈니
							<input type="radio" id="watcha" name="watcha" value="3">왓챠
							<input type="radio" id="tving" name="tving" value="4">티빙
							<input type="radio" id="wavve" name="wavve" value="5">웨이브
						</td>
					</tr>
				</table>
		</div>
		<%-- 등록 / 목록 버튼 --%>
		<div class="mainBtn">
			<input type="submit" value="등록">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
			
		</form>
														
	</div> <%--  end of class=contents-main --%>
</div>
</body>
</html>