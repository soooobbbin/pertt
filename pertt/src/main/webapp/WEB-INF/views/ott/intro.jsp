<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 소개</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div align="center"></div>
	<table border="1" style="width:100%;">
		<tr height="20%">
			<th width="33%"></th>
			<th width="33%">특징</th>
			<th width="33%">장단점</th>
		</tr>
		<tr align="center">
			<td><img src="../images/logo_netflix.png" width=300></td>
			<td>어쩌구저쩌구 특징</td>
			<td>어쩌구저쩌구 장단점</td>
		</tr>
		<tr align="center">
			<td><img src="../images/logo_disney.png" width=300></td>
			<td>어쩌구저쩌구 특징</td>
			<td>어쩌구저쩌구 장단점</td>
		</tr>
		<tr align="center">
			<td><img src="../images/logo_watcha.png" width=300></td>
			<td>어쩌구저쩌구 특징</td>
			<td>어쩌구저쩌구 장단점</td>
		</tr>
		<tr align="center">
			<td><img src="../images/logo_tving.png" width=300></td>
			<td>어쩌구저쩌구 특징</td>
			<td>어쩌구저쩌구 장단점</td>
		</tr>
		<tr align="center">
			<td><img src="../images/logo_wavve.png" width=300></td>
			<td>어쩌구저쩌구 특징</td>
			<td>어쩌구저쩌구 장단점</td>
		</tr>
	</table>
	<h2>가입비 비교</h2>
	<table border="1" style="width:50%;">
		<tr height="10%">
			<th width="16%"></th>
			<th width="16%">월 요금</th>
			<th width="16%">연 요금</th>
			<th width="16%">동시접속자</th>
			<th width="16%">프로필 수</th>
			<th width="16%">최대화질</th>
		</tr>
		<tr align="center">
			<td rowspan="3">넷플릭스</td>
			<td>9,500원</td>
			<td></td>
			<td>1명</td>
			<td></td>
			<td>HD이하</td>
		</tr>
		<tr align="center">
			<td>13,500원</td>
			<td></td>
			<td>2명</td>
			<td></td>
			<td>720P</td>
		</tr>
		<tr align="center">
			<td>17,000원</td>
			<td></td>
			<td>4명</td>
			<td>5개</td>
			<td>4K</td>
		</tr>
		<tr align="center">
			<td>디즈니+</td>
			<td>9,900원</td>
			<td>99,000원</td>
			<td>4명</td>
			<td>7개</td>
			<td>4K</td>
		</tr>
		<tr align="center">
			<td rowspan="2">왓챠</td>
			<td>7,900원</td>
			<td></td>
			<td>1명</td>
			<td>1개</td>
			<td>1080P</td>
		</tr>
		<tr align="center">
			<td>12,900원</td>
			<td></td>
			<td>4명</td>
			<td>4개</td>
			<td>4K</td>
		</tr>
		<tr align="center">
			<td rowspan="3">티빙</td>
			<td>7,900원</td>
			<td></td>
			<td>1명</td>
			<td>1개</td>
			<td>720P</td>
		</tr>
		<tr align="center">
			<td>10,900원</td>
			<td>98,100원</td>
			<td>2명</td>
			<td>2개</td>
			<td>1080P</td>
		</tr>
		<tr align="center">
			<td>13,900원</td>
			<td>125,100원</td>
			<td>4명</td>
			<td>4개</td>
			<td>4K</td>
		</tr>
		<tr align="center">
			<td rowspan="3">웨이브</td>
			<td>7,900원</td>
			<td></td>
			<td>1명</td>
			<td>1개</td>
			<td>720P</td>
		</tr>
		<tr align="center">
			<td>10,900원</td>
			<td></td>
			<td>2명</td>
			<td>2개</td>
			<td>1080P</td>
		</tr>
		<tr align="center">
			<td>13,900원</td>
			<td></td>
			<td>4명</td>
			<td>4개</td>
			<td>4K</td>
		</tr>
	</table>
</div>
</body>
</html>