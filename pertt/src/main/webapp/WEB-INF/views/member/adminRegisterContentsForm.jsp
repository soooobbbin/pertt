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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/contents.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>작품 정보 등록</h2>
		<form action="adminRegisterContents.do" method="post" name="contentsRegister_form" id="contentsRegister_form"  enctype="multipart/form-data">
	
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
						<div class="poster-area">
							<input id="poster" type="file" value="파일 선택" id="poster_btn" name="poster">
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
							<select name="genre" size="1" id="genre">
								<option label="장르">
								<option value="#로맨스">로맨스</option>
								<option value="#스릴러">스릴러</option>
								<option value="#코미디">코미디</option>
								<option value="#드라마">드라마</option>
								<option value="#뮤지컬">뮤지컬</option>
								<option value="#미스터리">미스터리</option>
								<option value="#리얼리티">리얼리티</option>
								<option value="#SF">SF</option>
								<option value="#액션">액션</option>
								<option value="#범죄">범죄</option>
								<option value="#공포">공포</option>
								<option value="#판타지">판타지</option>
								<option value="#애니메이션">애니메이션</option>
								<option value="#다큐멘터리">다큐멘터리</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>러닝 타임</td>
						<td id="last-child"><input type="text" id="tomato" name="tomato" placeholder="러닝타임을 입력하세요" autocomplete="off"></td>
					</tr>
					<tr>
						<td>줄거리</td>
						<td id="last-child"><textarea rows="3" cols="50" name="plot" id="plot" placeholder="줄거리를 입력하세요"></textarea></td>
					</tr>
					<tr>
						<td>출연/제작</td>
						<td id="last-child"><textarea rows="3" cols="50" id="produce" name="produce" placeholder="출연/제작란을 입력하세요"></textarea></td>
					</tr>
					<tr>
						<td>등급</td>
						<td id="last-child">
							<select name="grade" size="1" id="grade">
								<option label="등급">
								<option value="ALL">ALL</option>
								<option value="12+">12세 이상</option>
								<option value="15+">15세 이상</option>
								<option value="19+">19세 이상</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>카테고리</td>
						<td id="last-child">
							<select name="category_num" size="1" id="category">
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
						</td>
					</tr>
					<tr>
						<td>OTT</td>
						<td id="last-child">
							<input type="radio" id="ott" name="ott_num" value="1">넷플릭스
							<input type="radio" id="ott" name="ott_num" value="2">디즈니
							<input type="radio" id="ott" name="ott_num" value="3">왓챠
							<input type="radio" id="ott" name="ott_num" value="4">티빙
							<input type="radio" id="ott" name="ott_num" value="5">웨이브
						</td>
					</tr>
				</table>
		</div>
		<%-- 등록 / 목록 버튼 --%>
		<div class="mainBtn">
			<input type="submit" value="등록">
			<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/contents/list.do'">
		</div>
			
		</form>
														
	</div> <%--  end of class=contents-main --%>
</div>
</body>
</html>