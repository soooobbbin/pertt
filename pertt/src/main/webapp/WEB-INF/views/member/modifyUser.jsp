<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check}">
	<script>
		alert('회원정보를 수정했습니다.');
		location.href='myPage.do';
=======
    
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check}">
	<script>
		alert('회원정보를 수정했습니다.');
		location.href='myPage.do';
	</script>
</c:if>
<c:if test="${!check}">
	<script>
		alert('현재 비밀번호 불일치');
		history.go(-1);
>>>>>>> branch 'main' of https://github.com/heyjon1984/pertt.git
	</script>
</c:if>

<<<<<<< HEAD
<c:if test="${!check}">
	<script>
		alert('현재 비밀번호 불일치');
		history.go(-1);
	</script>
</c:if>

=======
 
>>>>>>> branch 'main' of https://github.com/heyjon1984/pertt.git
