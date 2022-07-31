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
		<form action="update.do" method="post"
		      enctype="multipart/form-data" id="write_form">
			<input type="hidden" name="c_num" 
			                       value="${contents.c_num}">
			<ul>
				<li>
					<label for="poster">파일</label>
					<input type="file" name="poster"
					  id="poster" 
					  accept="image/gif,image/png,image/jpeg">
					<c:if test="${!empty contents.poster}">
					<br>
					<span id="file_detail">
						(${contents.poster})파일이 등록되어 있습니다. 
						다시 파일을 업로드하면 기존 파일은 삭제됩니다.
						<input type="button" value="파일삭제" id="file_del">
					</span>
					<script type="text/javascript">
					$(function(){
						//이벤트 연결
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteContents.do',
									type:'post',
									data:{c_num:${contents.c_num}},
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
										}else if(param.result == 'wrongAccess'){
											alert('잘못된 접속입니다.');
										}else{
											alert('파일 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
					</script>
					</c:if>  
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