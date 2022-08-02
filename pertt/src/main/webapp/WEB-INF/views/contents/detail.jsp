<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header_admin.jsp"/>
	<div class="content-main">
		<h2>${contents.title}</h2>
		<form action="detail.do" method="post"
		      id="detail_form">
			<input type="hidden" name="c_num"
			                    value="${contents.c_num}">
			
			<div class="align-center">
				<input type="button" value="수정"
					onclick="location.href='updateContentsForm.do?c_num=${contents.c_num}'">
				<input type="button" value="목록"
				      onclick="location.href='list.do'">
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					let delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('delete.do?c_num=${contents.c_num}');
						}
					};
				</script>
			</div>  
			<hr size="1" noshade="noshade" width="80%">
			<ul>
				<li>
					<label>포스터</label>
					<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
					<img src="${pageContext.request.contextPath}/upload/${contents.poster}" width="300" height="170" class="con-poster">
					</c:if>
					<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
					<img src="${pageContext.request.contextPath}/upload/${contents.poster}" width="180" height="250" class="con-poster">
					</c:if>
				</li>
				<li>
					<label>제목</label>${contents.title}
				</li>
				<li>
					<label>개봉일</label>${contents.release}
				</li>
				<li>
					<label>국가</label>${contents.country}
				</li>
				<li>
					<label>장르</label>${contents.genre}
				</li>
				<li>
					<label>줄거리</label>${contents.plot}
				</li>
				<li>
					<label>러닝타임</label>${contents.tomato}
				</li>
				<li>
					<label>출연/제작</label>${contents.produce}
				</li>
				<li>
					<label>등급</label>${contents.grade}
			</ul>    
		</form>
	</div>
</div>

</body>
</html>