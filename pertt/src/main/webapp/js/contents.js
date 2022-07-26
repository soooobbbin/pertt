$(function(){
	//================목록 검색창====================//
	$('#search_form').submit(function(){
		if($('#keyword').val().trim()==''){
			alert('검색어를 입력하세요!');
			$('#keyword').val('').focus();
			return false;
		}
	});
	
	//============= 작품 포스터 등록 ==============//
	//작품 포스터 등록 이벤트 연결
	$('#poster_btn').click(function(){
		$('#poster_choice').show();
		$(this).hide();//수정 버튼 감추기
	});
	
	//이미지 미리 보기
	//처음 화면에 보여지는 이미지 읽기
	let photo_path = $('.con-poster').attr('src');
	let con_poster;
	$('#photo').change(function(){
		con_poster = this.files[0];
		if(!con_poster){
			$('.con-poster').attr('src',photo_path);
			return;
		}
		
		//파일의 용량 체크
		if(con_poster.size > 1024*1024){
			alert(Math.round(my_photo.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.con-poster').attr('src',photo_path);
			$(this).val('');
			return;
		}
		
		var reader = new FileReader();
		reader.readAsDataURL(con_poster);
		
		reader.onload=function(){
			$('.con-poster').attr('src',reader.result);
		}
	});//end of change
	
	//이미지 전송
	$('#photo_submit').click(function(){
		if($('#photo').val()==''){
			alert('파일을 선택하세요!');
			$('#photo').focus();
			return;
		}
		
		//파일 전송
		let form_data = new FormData();
		form_data.append('photo',con_poster);
		$.ajax({
			url:'updatePoster.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false,//데이터 객체를 문자열로 바꿀지에 대한 값. true면 일반문자
			processData:false,//해당 타입을 true로 하면 일반 text로 구분
			enctype:'multipart/form-data',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					photo_path = $('.con-poster').attr('src');
					$('#photo').val('');
					$('#poster_choice').hide();
					$('#poster_btn').show();//수정 버튼 노출
				}else{
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
			
		});
	});//end of click
	
	//이미지 미리보기 취소
	$('#photo_reset').click(function(){
		//이미지 미리보기 전 원래 이미지로 되돌리기
		$('.con-poster').attr('src',photo_path);
		$('#photo').val('');
		$('#poster_choice').hide();
		$('#poster_btn').show();//수정 버튼 노출
	});//end of click
});