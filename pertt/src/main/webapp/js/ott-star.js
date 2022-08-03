$(function(){
	let user_num = $('#user_num').val();
	let starCheck = $('#starCheck').val();
	let reviewCheck = $('#reviewCheck').val();
   
   function selectStar(ott_num){
		$.ajax({
			url:'ottReview.do',
			type:'post',
			data:{ott_num:ott_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				alert(param.price);
			},
			error:function(){
				alert('별점 불러오기에서 네트워크 오류 발생');	
			}
		});
	}


   $('#ottLike_form').submit(function(event){
		let form_data = $(this).serialize();
		
		$.ajax({
			url:'writeOttStar.do',
			type:'post',
			data:form_data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					alert('등록이 완료되었습니다.');
					//별점 총점을 다시 호출함
					$('#star_avg').text('평균별점 : ★'+param.starAvg);
					starCheck = 1;

					showOttReviewForm(user_num,starCheck,reviewCheck);
				}
			},
			error:function(){
				alert('이미 별점이 등록되었습니다.');	
			}
		});
		
		
		//기본 이벤트 제거
		event.preventDefault();
	});
	//초기 데이터 호출
	//selectStar(1);
});
