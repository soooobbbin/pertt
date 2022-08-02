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
		<form action="detail.do" method="post"
		      id="detail_form">
			<input type="hidden" name="c_num"
			                    value="${contents.c_num}">
			
			
			<div class="detail-poster">
				<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
				<img src="${pageContext.request.contextPath}/upload/${contents.poster}" width="300" height="170" class="con-poster">
				</c:if>
				<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
				<img src="${pageContext.request.contextPath}/upload/${contents.poster}" width="180" height="250" class="con-poster">
				</c:if>
			</div>
			<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
			<div class="detail-h2"><h2>${contents.title}</h2></div>
			</c:if>
			<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
			<div class="detail2-h2"><h2>${contents.title}</h2></div>
			</c:if>
			
			<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
			<div class="detail-release" id="detail-div2">
				<label>개봉일</label>${contents.release}
			</div>
			</c:if>
			<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
			<div class="detail-release" id="detail-div">
				<label>개봉일</label>${contents.release}
			</div>
			</c:if>
			
			<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
			<div class="detail-country" id="detail-div2">
				<label>국가</label>${contents.country}
			</div>
			</c:if>
			<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
			<div class="detail-country" id="detail-div">
				<label>국가</label>${contents.country}
			</div>
			</c:if>
			
			<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
			<div class="detail-genre" id="detail-div2">
				<label>장르</label>${contents.genre}
			</div>
			</c:if>
			<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
			<div class="detail-genre" id="detail-div">
				<label>장르</label>${contents.genre}
			</div>
			</c:if>
			
			<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
			<div class="detail-tomato" id="detail-div2">
				<label>러닝타임</label>${contents.tomato}
			</div>
			</c:if>
			<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
			<div class="detail-tomato" id="detail-div">
				<label>러닝타임</label>${contents.tomato}
			</div>
			</c:if>
			
			<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
			<div class="detail-produce" id="detail-div2">
				<label>출연/제작</label>${contents.produce}
			</div>
			</c:if>
			<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
			<div class="detail-produce" id="detail-div">
				<label>출연/제작</label>${contents.produce}
			</div>
			</c:if>
			
			<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
			<div class="detail-grade" id="detail-div2">
				<label>등급</label>${contents.grade}
			</div>   
			</c:if>
			<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
			<div class="detail-grade" id="detail-div">
				<label>등급</label>${contents.grade}
			</div>   
			</c:if>
			
			
			<div class="detail-plot">
				<c:if test="${!empty contents.poster && contents.ott_num == 1 || contents.ott_num == 2}">
				<label class="label-plot">줄거리</label><br><br>
				</c:if>
				<c:if test="${!empty contents.poster && contents.ott_num == 3 || contents.ott_num == 4 || contents.ott_num == 5}">
				<label class="label2-plot">줄거리</label><br><br>
				</c:if>
				<textarea rows="10" cols="80" class="text-plot">${contents.plot}</textarea>
			</div>
		</form>
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
	</div>
</div>

</body>
</html>