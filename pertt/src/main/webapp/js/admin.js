$(function(){
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
			alert(Math.round(con_poster.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
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
	//========= 작품등록 유효성 체크 ==========//
	$('#write_form').submit(function(){
		if($('#title').val().trim()==''){
			alert('제목을 입력하세요!');
			$('#title').val('').focus();
			return false;
		}
		if($('#release').val().trim()==''){
			alert('개봉일을 입력하세요!');
			$('#release').val('').focus();
			return false;
		}
		if($('#country').val().trim()==''){
			alert('국가를 입력하세요!');
			$('#country').val('').focus();
			return false;
		}
		if($('#genre').val().trim() ==''){
			alert('장르를 선택하세요!');
			$('#genre').focus();
			return false;
		}
		if($('#tomato').val().trim()==''){
			alert('러닝타임을 입력하세요!');
			$('#tomato').val('').focus();
			return false;
		}
		if($('#plot').val().trim()==''){
			alert('줄거리를 입력하세요!');
			$('#plot').val('').focus();
			return false;
		}
		if($('#produce').val().trim()==''){
			alert('출연/제작을 입력하세요!');
			$('#produce').val('').focus();
			return false;
		}
		if($('#grade').val().trim() ==''){
			alert('등급을 선택하세요!');
			$('#grade').focus();
			return false;
		}
		if($('#category').val().trim() ==''){
			alert('카테고리를 선택하세요!');
			$('#category').focus();
			return false;
		}
		
		if( $("input[name='ott_num']:checked").length==0){  

      		alert("OTT를 선택하세요!");
			return false;
		}
   		
	
		
	}); //end of submit

});