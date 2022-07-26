<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kr.contents.vo.ContentsVO" %> 
<%@ page import="kr.contents.vo.CategoryVO" %>    
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 작품</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/contents.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/contents.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
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
		<c:forEach var="list" items="${categoryList}" varStatus="status">
		<h2>${list.category_name}</h2>
			<c:forEach var="content" items="${requestScope['contents'+=status.index]}">
			<div class="contentsList">
				<img src="${pageContext.request.contextPath}/upload/${content.poster}">
			</div>
			</c:forEach>
		</c:forEach>
	</div>
</div>
</body>
</html>






