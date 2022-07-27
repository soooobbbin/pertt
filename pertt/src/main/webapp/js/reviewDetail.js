$(function(){
	//좋아요 선택 여부와 선택한 총 개수 읽기
	function selectData(c_review_num){
		$.ajax({
			url:'getLike.do',
			type:'post',
			data:{c_review_num:c_review_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				displayLike(param);
			},
			error:function(){
				alert('좋아요 개수 네트워크 오류');
			}
		});
	}
	
	//좋아요 등록
	$('#output_like').click(function(){
		$.ajax({
			url:'writeLike.do',
			type:'post',
			data:{c_review_num:$('#c_review_num').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='logout'){
					alert('로그인 후 좋아요를 놀러주세요!');
				}else if(param.result == 'success'){
					displayLike(param);
				}else{
					alert('등록시 오류 발생!');
				}
			},
			error:function(){
				alert('좋아요 등록 네트워크 오류!');
			}
		});
	});
	//좋아요 표시
	function displayLike(param){
		let output;
		if(param.status == 'noLike'){
			output = '../images/like1.png';
		}else{
			output = '../images/like2.png';
		}
		//문서 객체에 추가
		$('#output_like').attr('src',output);
		$('#output_lcount').text(param.count);
	}
	
	//초기 데이터 표시
	selectData($('#c_review_num').val());
	
});




















