$(function(){
	let currentPage;
	let count;
	let rowCount;
	
	//한줄평 목록
	function selectList(pageNum){
		currentPage = pageNum;
		
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'listOttReview.do',
			type:'post',
			data:{pageNum:pageNum,ott_num:$('#ott_num').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum == 1){
					//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
					$('#output').empty();
				}
				
				$(param.list).each(function(index,item){
					let output = '<div class="item">';
					output += '<h4>' + item.id +'</h4>';
					output += '<div class="sub-item">';
					output += '<p>' + item.ott_re_content + '</p>';
					output += '<span class="reg-date">등록일 : ' + item.reg_date + '</span>';
					
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if(param.user_num == item.member_num){
						output += ' <input type="button" data-renum="'+item.ott_review_num+'" value="삭제" class="delete-btn">';
					}
					
					output += '<hr size="1" noshade width="100%">';
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 추가
					$('#output').append(output);
				});//end of each
				
				//page button 처리
				if(currentPage>=Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 존재
					$('.paging-button').show();
				}
				
			},
			error:function(){
				$('#loading').hide();
				alert('네크워크 오류 발생');
			}
		});
		
	}
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1);
	});
	
	//한줄평 등록
	$('#re_form').submit(function(event){
		if($('#ott_re_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#ott_re_content').val('').focus();
			return false;
		}
		
		//form 이하의 태그에 입력한 데이터를 모두 읽어옴
		let form_data = $(this).serialize();
		
		//댓글 등록을 위한 서버 프로그램 연동
		$.ajax({
			url:'writeOttReview.do',
			type:'post',
			data:form_data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//댓글 작성이 성공하면 새로 삽입한 글을 포함해서
					//첫번째 페이지의 게시글을 다시 호출함
					selectList(1);
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//한줄평 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구함
		let inputLength = $(this).val().length;
		
		if(inputLength > 300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하인 경우
			let remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id') == 'ott_re_content'){//등록
				//등록폼 글자수 처리
				$('#re_first .letter-count').text(remain);
			}else{//수정
				//수정폼 글자수 처리
				$('#mre_first .letter-count').text(remain);
			}
		}
	});
	
	
	//한줄평 삭제
	$(document).on('click','.delete-btn',function(){
		//댓글 번호
		let ott_review_num = $(this).attr('data-renum');
		
		$.ajax({
			url:'deleteOttReview.do',
			type:'post',
			data:{ott_review_num:ott_review_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(param.result == 'success'){
					alert('삭제 완료');
					selectList(1);
				}else if(param.result == 'wrongAccess'){
					alert('타인의 한줄평을 삭제할 수 없습니다.');
				}else{
					alert('삭제시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
		
	});
	
	//초기 데이터(목록) 호출
	selectList(1);
	
});