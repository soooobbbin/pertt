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
	//이미지 미리 보기
	//처음 화면에 보여지는 이미지 읽기
	let photo_path = $('.con-poster').attr('src');
	let con_poster;
	$('#poster_btn').change(function(){
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
		if($('#poster_btn').val()==''){
			alert('파일을 선택하세요!');
			$('#poster_btn').focus();
			return;
		}
	});
});