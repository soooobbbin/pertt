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
	
	//=====================리뷰 수정 ==========================================
	//리뷰 작성 폼 초기화
	function initModifyForm(){
		$('#review_content').show();
		$('.modify-btn').show();
		$('.delete-btn').show();
		$('.review_moddate').show();
		$('#mreview_form').remove();
	}
	
	//리뷰 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		//리뷰 번호
		let c_review_num = $(this).attr('data-reviewnum');
		
		//리뷰 내용
		let content = $('#review_content').html().replace(/<br>/gi,'\n');
		                                          //g:지정문자열 모두,i:대소문자 무시
		//리뷰 수정폼 UI
		let modifyUI = '<form id="mreview_form" style="width:800px;">';
		modifyUI += '<input type="hidden" name="c_review_num" id="mreview_num" value="'+c_review_num+'">';
		modifyUI += '<div style="clear:both;"></div>';
		modifyUI += '<textarea rows="10" cols="80" name="c_review_content" id="mreview_content" class="rep-content" ';
		modifyUI += 'style="height:120px;margin:20px 0 10px 0;float:left;width:760px;">';
		modifyUI += content+'</textarea>';
		modifyUI += '<div id="mreview_second" class="align-right" style="clear:both;">';
		modifyUI += ' <input type="submit" value="수정">';
		modifyUI += ' <input type="button" value="취소" class="review-reset" style="margin: 0 32px 0 0;">';
		modifyUI += '</div>';
		modifyUI += '</form>';
		
		//이전에 이미 수정하는 리뷰이 있을 경우 수정버튼을 클릭하면
		//숨김 sub-item을 환원시키고 수정폼을 초기화함
		initModifyForm();
		
		//지금 클릭해서 수정하고자 하는 데이터는 감추기
		//수정버튼을 감싸고 있는 div
		$('#review_content').hide();
		$('.modify-btn').hide();
		$('.delete-btn').hide();
		$('#review_moddate').hide();
		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
		$('.review-modify-box').append(modifyUI);
		
	});
	
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.review-reset',function(){
		initModifyForm();
	});

	//리뷰 수정
	$(document).on('submit','#mreview_form',function(event){
		if($('#mreview_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#mreview_content').val('').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		let form_data = $(this).serialize();
		
		//리뷰 수정을 위한 서버 프로그램 연동
		$.ajax({
			url:'updateReview.do',
			type:'post',
			data:form_data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(param.result == 'success'){
					$('#review_content').html($('#mreview_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\n/g,'<br>'));
					$('#review_moddate').text('최근 수정일 : 5초미만');
					//수정폼 삭제 및 초기화
					initModifyForm();
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 수정할 수 없습니다');
				}else{
					alert('수정 오류 발생');
				}
			},
			error:function(){
				alert('수정시 네크워크 오류 발생!');
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//리뷰 삭제
	$(document).on('click','.delete-btn',function(){
		//리뷰 번호
		let c_review_num = $(this).attr('data-reviewnum');
		
		$.ajax({
			url:'deleteReview.do',
			type:'post',
			data:{c_review_num:c_review_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(param.result == 'success'){
					alert('리뷰가 삭제되었습니다');
					location.replace('review.do?c_num='+$('#c_num').val());
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('삭제시 오류 발생!');
				}
			},
			error:function(){
				alert('삭제시 네트워크 오류 발생!');
			}
		});
		
	});
});




















