<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 소개</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ott-intro.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="intro">
		<div align="center"></div>
		<h2 align="center">OTT 소개</h2>
		<table class="intro-table" style="width:100%;">
			<tr class="top">
				<th width="26%" class="line"></th>
				<th width="37%" class="line" style="border-left:none;">특징</th>
				<th width="37%" class="line" style="border-left:none;">장단점</th>
			</tr>
			<tr class="colored">
				<td align="center" class="line"><img src="../images/logo_none_netflix.png"></td>
				<td class="line"><b>OTT 서비스의 강자!</b><br>한 시즌의 모든 에피소드가 하루에 전부 공개되는 것이 특징(방영 중인 작품 제외)</td>
				<td class="line">세계 최대 유료 스트리밍 서비스 업체로,<br><b>전세계 각지에서 제작된 오리지널 콘텐츠</b>가 많음</td>
			</tr>
			<tr class="colored">
				<td align="center" class="line"><img src="../images/logo_none_disney.png"></td>
				<td class="line"><b>디즈니, 픽사, 마블, 네셔널 지오그래픽</b> 등 단독 콘텐츠를 제공하는 스트리밍 서비스</td>
				<td class="line"><b>저렴한 단일 가격</b>에 4개의 기기 동시 시청과 7개의 프로필 설정 가능</td>
			</tr>
			<tr class="white">
				<td align="center" class="line"><img src="../images/logo_none_watcha.png"></td>
				<td class="line"><b>고전 영화나 인디영화, 일본 콘텐츠</b> 등을 다수 보유</td>
				<td class="line">시청자의 취향을 분석한 <b>영화 추천 서비스</b></td>
			</tr>
			<tr class="colored">
				<td align="center" class="line"><img src="../images/logo_none_tving.png"></td>
				<td class="line">CJ ENM, JTBC 등 <b>CJ 기반 OTT 플랫폼</b></td>
				<td class="line"><b>TVN 방송의 다시보기</b>를 유일하게 제공<br>영화는 많지 않은 것이 단점</td>
			</tr>
			<tr class="colored">
				<td align="center" class="line"><img src="../images/logo_none_wavve.png"></td>
				<td class="line"><b>지상파 3사의 콘텐츠</b>를 중심으로 제공하는 OTT 플랫폼</td>
				<td class="line"><b>TV가 없어도 실시간 방송 시청 가능</b><br>4K 화질을 서비스하지 않음</td>
			</tr>
		</table>
	</div>
	<div class="price">
		<h2 align="center">가입비 비교</h2>
		<div class="align-center" style="margin:20px 0 100px 0;">
		<table class="price-table" border="1" style="width:80%; margin-left: auto; margin-right: auto;">
			<tr height="10%">
				<th width="16%"></th>
				<th width="16%">월 요금</th>
				<th width="16%">연 요금</th>
				<th width="16%">동시접속자</th>
				<th width="16%">프로필 수</th>
				<th width="16%">최대화질</th>
			</tr>
			<tr align="center" class="net">
				<td rowspan="3" class="bold">넷플릭스</td>
				<td>9,500원</td>
				<td></td>
				<td>1명</td>
				<td></td>
				<td>HD이하</td>
			</tr>
			<tr align="center" class="net">
				<td>13,500원</td>
				<td></td>
				<td>2명</td>
				<td></td>
				<td>720P</td>
			</tr>
			<tr align="center" class="net">
				<td>17,000원</td>
				<td></td>
				<td>4명</td>
				<td>5개</td>
				<td>4K</td>
			</tr>
			<tr align="center" class="dis">
				<td class="bold">디즈니+</td>
				<td>9,900원</td>
				<td>99,000원</td>
				<td>4명</td>
				<td>7개</td>
				<td>4K</td>
			</tr>
			<tr align="center" class="wat">
				<td rowspan="2" class="bold">왓챠</td>
				<td>7,900원</td>
				<td></td>
				<td>1명</td>
				<td>1개</td>
				<td>1080P</td>
			</tr>
			<tr align="center" class="wat">
				<td>12,900원</td>
				<td></td>
				<td>4명</td>
				<td>4개</td>
				<td>4K</td>
			</tr>
			<tr align="center" class="dis">
				<td rowspan="3" class="bold">티빙</td>
				<td>7,900원</td>
				<td></td>
				<td>1명</td>
				<td>1개</td>
				<td>720P</td>
			</tr>
			<tr align="center" class="dis">
				<td>10,900원</td>
				<td>98,100원</td>
				<td>2명</td>
				<td>2개</td>
				<td>1080P</td>
			</tr>
			<tr align="center" class="dis">
				<td>13,900원</td>
				<td>125,100원</td>
				<td>4명</td>
				<td>4개</td>
				<td>4K</td>
			</tr>
			<tr align="center" class="net">
				<td rowspan="3" class="bold">웨이브</td>
				<td>7,900원</td>
				<td></td>
				<td>1명</td>
				<td>1개</td>
				<td>720P</td>
			</tr>
			<tr align="center" class="net">
				<td>10,900원</td>
				<td></td>
				<td>2명</td>
				<td>2개</td>
				<td>1080P</td>
			</tr>
			<tr align="center" class="net">
				<td>13,900원</td>
				<td></td>
				<td>4명</td>
				<td>4개</td>
				<td>4K</td>
			</tr>
		</table>
		</div>
	</div>
</div>
</body>
</html>