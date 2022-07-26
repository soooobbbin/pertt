<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>회원탈퇴</h2>
		<form action="deleteUser.do" method="post"
		                                id="delete_form">
		                                
		        <table >
		         <tr class="dt-table">
		        	 <td class="dt-txt1">탈퇴 안내</td>
		        	 <td class="dt-txt2">회원 탈퇴에 앞서 유의 사항 및 안내를 읽고 동의시 체크 박스를 클릭 해주십시오. </td>
		        </tr>
		        </table>  
		        <table>
		        <tr>
		        <td rowspan=2> 체크v </td>
		        </tr>
		        	<tr>
		        	<td> 사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능 합니다.</td>
		        	<td>탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가하오니 신중하게 선택하시길 바랍니다</td>
		        	</tr>
		        <tr>
		        <td colspan=2> 체크v </td>
		        </tr>
		        	<tr>
		        	<td>내정보 및 사이트 이용기록이 모두 삭제됩니다</td>
		        	<td>내정보 및 사이트 이용기록이 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.</td>
		        	</tr>
		        	<tr>
		        <td colspan=2> 체크v </td>
		        </tr>
		        	<tr>
		        	<td>탈퇴 후에도 등록한 게시물 및 댓글은 그대로 남아 있습니다.</td>
		        	<td>삭제를 원하는  원하는 게시글 및 댓글이 있다면 반드시 회원 탈퇴 전 삭제하시길 바랍니다. <br>
		        	탈퇴 후에는 회원정보가 삭제되며 본인 여부를 확인할 수 있는 방법이 없어, 게시글을 임의로 삭제해드릴 수 없습니다.</td>
		        	</tr>
		        <tr>
		        <td> 체크</td>
		        <td>안내사항을 모두 확인했으며, 이에 동의 합니다</td>
		        </tr>
		        </table>
		          	                 
		          <table>
		         <tr class="dt-table">
		        	 <td class="dt-txt1">아이디 / 비밀번호 확인</td>
		        	 <td class="dt-txt2">회원 탈퇴에 앞서 유의 사항 및 안내를 읽고 동의시 체크 박스를 클릭 해주십시오. </td>
		        </tr>
		        </table>                          
		
			<ul>
				<li>
					<label for="member_id">아이디</label>
					<input type="text" name="member_id" id="member_id"
					                      maxlength="12">
				</li>
				
				<li>
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd" 
					       id="passwd" maxlength="12">
				</li>
				<li>
					<label for="cpasswd">비밀번호 확인</label>
					<input type="password" name="cpasswd" 
					       id="cpasswd" maxlength="12">
					<span id="message_id"></span>       
				</li>	
			</ul> 
			<div class="align-center">
				<input type="submit" value="회원탈퇴">
				<input type="button" value="My페이지"
				       onclick="location.href='myPage.do'">
			</div>                              
		</form>
	</div>
</div>
</body>
</html>





