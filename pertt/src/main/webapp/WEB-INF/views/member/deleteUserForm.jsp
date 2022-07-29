<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" 	href="${pageContext.request.contextPath}/css/deleteM.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />


	<div id="deletePage">
		<form action="deleteUser.do" method="post" id="delete_form">

			<table>
				<tr class="dt-table">

					<td class="dt-txt1">탈퇴 안내</td>
					<td class="dt-txt2">회원 탈퇴에 앞서 유의 사항 및 안내를 읽고 동의시 체크 박스를 클릭
						해주십시오.</td>
				</tr>
			</table>
			<br><br>
			<table>
				<tr>
					<td rowspan="2"><img
						src="${pageContext.request.contextPath}/images/check.png"
						width="35px" height="35px"></td>
					<td class="dt-b">사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</td>
				</tr>
				<tr>
					<td class="dt-a"><span style="color: rgb(231, 76, 60);">탈퇴한
							아이디는 본인과 타인 모두 재사용 및 복구</span>가 불가하오니 신중하게 선택하시길 바랍니다</td>
				</tr>
				<tr>
					<td rowspan="2"><img
						src="${pageContext.request.contextPath}/images/check.png"
						width="35px" height="35px"></td>
					<td class="dt-b">내정보 및 사이트 이용기록이 모두 삭제됩니다</td>
				</tr>
				<tr>
					<td>내정보 및 사이트 이용기록이 모두 삭제되며, <span
						style="color: rgb(231, 76, 60);">삭제된 데이터는 복구되지 않습니다</span></td>
				</tr>
				
				<tr>
					<td rowspan="2"><img
						src="${pageContext.request.contextPath}/images/check.png"
						width="35px" height="35px"></td>
					<td class="dt-b">탈퇴 후에도 등록한 게시물 및 댓글은 그대로 남아 있습니다.</td>
				</tr>

				<tr>
					<td>삭제를 원하는 원하는 게시글 및 댓글이 있다면 반드시 회원 탈퇴 전 삭제하시길 바랍니다. <br>
						탈퇴 후에는 회원정보가 삭제되며 본인 여부를 확인할 수 있는 방법이 없어, <span
						style="color: rgb(231, 76, 60);">게시글을 임의로 삭제해드릴 수 없습니다.</span></td>
				</tr>

			</table>
			<div id="dt-check">
				<table>
					<tr>
						<td><input type="checkbox" name="DT-check" value='Y' id="input-check">
						<input type="hidden" name="DT-check" value='N' id="input-check_hidden">
						
						</td>
						<td class="dt-txt3"><b>안내사항을 모두 확인했으며, 이에 동의 합니다.</b></td>
					</tr>

				</table>
			</div>
			<hr><br>
			<table>
				<tr class="dt-table">
					<td class="dt-txt1">아이디 / 비밀번호 확인</td>
					<td class="dt-txt2">안전한 탈퇴를 위하여 아이디 및 비밀번호를 확인해주세요</td>
				</tr>
			</table>
			<br>
			<div class="align-center"> 

			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="member_id" id="member_id"
						maxlength="12"></td>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="passwd" id="passwd"
						maxlength="12"></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="cpasswd" id="cpasswd" 
						maxlength="12"> <span id="message_id"></span></td>
				</tr>

			</table>
			</div>
			
			<div class="align-right"> 
				<input type="button" 
					value="탈퇴취소" class="blue-btn2" onclick="location.href='myPage.do'">
					<input type="submit" value="회원탈퇴" class="gray-btn2" id="delete_btn"> 
			</div>
		</form>
	</div>


</body>
</html>





