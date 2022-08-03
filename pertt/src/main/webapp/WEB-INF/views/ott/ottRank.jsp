<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 랭킹</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ott-rank.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main" style="padding:50px 0;">
	<div id="wrap">
		<div style="padding: 0 300px; font-size: 35px; font-weight: bold;">OTT 랭킹순위</div>
		<div class="rank" style="width:900px; margin:50px auto 80px;">
			<div style="position:absolute">
				<div style="position: relative; top: 10px; left: 10px;">
					<img src="../images/medal2.png" style="width:50px;"></img>
				</div>
			</div>
			<img id="rwatcha" src="../images/logo_none_watcha.png" style="width:250px; margin:0 20px;">
			<div style="position:absolute">
				<div style="position: relative; top: -170px; left: 280px;">
					<img src="../images/medal1.png" style="width:50px;"></img>
				</div>
			</div>
			<img id="rnetflix" src="../images/logo_none_netflix.png" style="width:300px;">
			<div style="position:absolute">
				<div style="position: relative; top: -150px; left: 600px;">
					<img src="../images/medal3.png" style="width:50px;"></img>
				</div>
			</div>
			<img id="rtving" src="../images/logo_none_tving.png" style="width:250px; margin:0 20px;">
			<div class="rpoint">
				<span style="margin: 0 120px;">3.8점</span>
				<span style="margin: 0 120px;">4.0점</span>
				<span style="margin: 0 120px;">3.4점</span>
			</div>
		</div>
		<div class="price" style="position:relative;">
			<span class="fs30 middle">가성비</span>
			<span class="basic middle">(5점 만점)</span>
			<div class="prank">
				<span class="p">1</span>
				<span class="p">2</span>
				<span class="p">3</span>
				<span class="p">4</span>
				<span>5</span>
			</div>
			<div class="lmiddle">
				<img class="disney" src="../images/logo_none_disney.png">
				<img class="watcha" src="../images/logo_none_watcha.png">
				<img class="tving" src="../images/logo_none_tving.png">
				<img class="netflix" src="../images/logo_none_netflix.png">
				<img class="wavve" src="../images/logo_none_wavve.png">
			</div>
			<div class="rpoint">
				<span class="p2">4.0</span>
				<span class="p2">3.8</span>
				<span class="p2">3.4</span>
				<span class="p2">3.2</span>
				<span>2.7</span>
			</div>
		</div>
		<div class="usability">
			<span class="fs30 middle">사용성</span>
			<span class="basic middle">(5점 만점)</span>
			<div class="prank">
				<span class="p">1</span>
				<span class="p">2</span>
				<span class="p">3</span>
				<span class="p">4</span>
				<span>5</span>
			</div>
			<div class="lmiddle">
				<img class="netflix" src="../images/logo_none_netflix.png">
				<img class="watcha" src="../images/logo_none_watcha.png">
				<img class="tving" src="../images/logo_none_tving.png">
				<img class="wavve" src="../images/logo_none_wavve.png">
				<img class="disney" src="../images/logo_none_disney.png">
			</div>
			<div class="rpoint">
				<span class="p2">4.5</span>
				<span class="p2">3.7</span>
				<span class="p2">3.5</span>
				<span class="p2">3.0</span>
				<span>2.8</span>
			</div>
		</div>
		<div class="price">
			<span class="fs30 middle">콘텐츠</span>
			<span class="basic middle">(5점 만점)</span>
			<div class="prank">
				<span class="p">1</span>
				<span class="p">2</span>
				<span class="p">3</span>
				<span class="p">4</span>
				<span>5</span>
			</div>
			<div class="lmiddle">
				<img class="netflix" src="../images/logo_none_netflix.png">
				<img class="watcha" src="../images/logo_none_watcha.png">
				<img class="tving" src="../images/logo_none_tving.png">
				<img class="disney" src="../images/logo_none_disney.png">
				<img class="wavve" src="../images/logo_none_wavve.png">
			</div>
			<div class="rpoint">
				<span class="p2">4.3</span>
				<span class="p2">3.9</span>
				<span class="p2">3.7</span>
				<span class="p2">3.5</span>
				<span>3.0</span>
			</div>
		</div>
	</div>
</div>
</body>
</html>