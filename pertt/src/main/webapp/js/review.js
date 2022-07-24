$(function(){
	//========================리뷰 쓰기===========================
	$('#review_form').submit(function(){
		if($('#title').val().trim() == ''){
			alert('제목을 입력하세요');
			$('#title').val('').focus();
			return false;
		}
		if($('#content').val().trim() == ''){
			alert('내용을 입력하세요');
			$('#content').val('').focus();
			return false;
		}
	});//글쓰기 submit
	
});