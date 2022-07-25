$(function(){
	//========================리뷰 쓰기===========================
	//빈내용 확인
	$('#review_form').submit(function(event){
		if($('#r_content').val().trim() == ''){
			alert('내용을 입력하세요');
			$('#r_content').val('').focus();
			return false;	
		}
		
		var reviewCheck = '${reviewCheck}';
		if(reviewCheck){//리뷰 이미 작성했음
			$('#review_duplicated').show();
			$('#review_notDuplicated').hide();
		} else {//리뷰 작성한 적 없음
			$('#review_duplicated').hide();
			$('#review_notDuplicated').show();
		}
		
		//리뷰 content 등록 
		let form_data = $(this).serialize();
		
		//리뷰등록을 위한 서버 프로그램 연결
		$.ajax({
			url:'reviewWrite.do',
			type:'post',
			data:form_data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인한 후에 이용할 수 있습니다.');
				}else if (param.result =='success'){
					//폼 초기화
					
					//첫번쨰 페이지 댓글 목록 다시 읽어오기(방금 작성한 댓글도 포함해서)
					
					//작성 완료하면 리뷰 작성했습니다로 바꾸기
					$('#review_duplicated').show();
					$('#review_notDuplicated').hide();
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}	
		});
		event.preventDefault();
	});
	
});