<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kr.contents.vo.ContentsVO" %>    
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
		
	 	<c:forEach var="category" items="${categoryList}" varStatus="status">
			<h2>${category.category_name}contents${status.index}</h2>
			<%
			List<ContentsVO> list = (List<ContentsVO>)request.getAttribute("${status.index}");
			%>
			<%= list %>
			<c:forEach var="contents" items="<%= list %>">
				<span>${contents}</span>
			</c:forEach>
		</c:forEach>
	</div>
</div>
</body>
</html>