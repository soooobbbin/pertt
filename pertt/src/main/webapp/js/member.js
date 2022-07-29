$(function(){
	let idChecked = 0;
	//--------회원가입--------//
	//아아디 중복 체크 이벤트 연결
	$('#id_check').click(function(){
		if($('#member_id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#member_id').val('').focus();
			return;
		}
		//아이디 중복 여부를 표시하는 메시지 초기화
		$('#message_id').text('');
		
		//서버와 데이터 통신
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#member_id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'idNotFound'){
					idChecked = 1;
					$('#message_id').css('color','#000000').text('등록 가능 ID');
				}else if(param.result == 'idDuplicated'){
					idChecked = 0;
					$('#message_id').css('color','red').text('중복된 ID');
					$('#id').val('').focus();
				}else{
					idChecked = 0;
					alert('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #member_id').keydown(function(){
		idChecked = 0;
		$('#message_id').text('');
	});
	
	//회원정보 등록 유효성 체크
	$('#register_form').submit(function(){
		if($('#member_id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#member_id').val('').focus();
			return false;
		}
		if(idChecked==0){
			alert('아이디 중복 체크 필수');
			return false;
		}
		if($('#name').val().trim()==''){
			alert('이름을 입력하세요!');
			$('#name').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#birth').val().trim()==''){
			alert('생년월일을 입력하세요!');
			$('#birth').val('').focus();
			return false;
		}
		
		if($('#phone').val().trim()==''){
			alert('휴대폰 번호를 입력하세요!');
			$('#phone').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
	});//end of submit
	
	//============= 로그인 ==============//
	//로그인 이벤트 연결
	$('#login_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
	});
	 
	//============= 회원정보수정 ==============//
	$('#modify_form').submit(function(){
		
		
		if($('#passwd').val().trim()==''){
			alert('새 비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#cpasswd').val().trim()==''){
			alert('새 비밀번호 확인을 입력하세요!');
			$('#cpasswd').val('').focus();
			return false;
		}
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('새 비밀번호와 새 비밀번호 확인 불일치');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
		
		
		if($('#name').val().trim()==''){
			alert('이름을 입력하세요!');
			$('#name').val('').focus();
			return false;
		}
		if($('#birth').val().trim()==''){
			alert('생년월일을 입력하세요!');
			$('#birth').val('').focus();
			return false;
		}
		if($('#phone').val().trim()==''){
			alert('휴대폰 번호를 입력하세요!');
			$('#phone').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
		
		
	});


		
	//새비밀번호 확인까지 한 후 다시 새비밀번호를 수정하려고 하면
	//새비밀번호 확인을 초기화
	$('#passwd').keyup(function(){
		$('#cpasswd').val('');
		$('#message_cpasswd').text('');
	});
	
	//새비밀번호와 새비밀번호 확인 일치시 메시지 처리
	$('#cpasswd').keyup(function(){
		if($('#passwd').val()==$('#cpasswd').val()){
			$('#message_cpasswd').text('새 비밀번호 일치');
		}else{
			$('#message_cpasswd').text('');
		}
	});//end of keyup
	
	//============= 회원탈퇴 ==============//
	$('#delete_form').submit(function(){
		if($('#member_id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#member_id').val('').focus();
			return false;
		}
		
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#cpasswd').val().trim()==''){
			alert('비밀번호 확인을 입력하세요!');
			$('#cpasswd').val('').focus();
			return false;
		}
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('비밀번호와 비밀번호 확인 불일치');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});//end of submit
	
	//비밀번호 확인까지 한 후 다시 비밀번호를 수정하면 비밀번호 확인 및
	//메시지 초기화
	$('#passwd').keyup(function(){
		$('#cpasswd').val('');
		$('#message_id').text('');
	});
	
	//비밀번호와 비밀번호 확인 일치 여부 체크
	$('#cpasswd').keyup(function(){
		if($('#passwd').val()==$('#cpasswd').val()){
			$('#message_id').text('비밀번호 일치');
		}else{
			$('#message_id').text('');
		}
	});
	
	
	
	
	//==========회원 탈퇴 약관 체크 확인==========//
	
	
	$('#delete_btn').click(function(){
		var cnt = $("input[name=DT-check]:checkbox:checked").length;    
		if(cnt < 1){
			alert('안내 사항을 확인 후 체크 바랍니다.');
			return false;
		 }
	});
	
	//============= 회원관리 목록 ==============//
	$('#search_form').submit(function(){
		if($('#keyword').val().trim()==''){
			alert('검색어를 입력하세요!');
			$('#keyword').val('').focus();
			return false;
		}
	});
	
});


