$(function(){
	let user_num = $('#user_num').val();
	let starCheck = $('#starCheck').val();
	let reviewCheck = $('#reviewCheck').val();
	
	//=================== 별점주기 ========================
	let c_num = $('#c_num').val();
	
	$('#star_btn').click(function(){
		let star = $('.rate-star').val();
		$.ajax({
			url:'giveStar.do',
			type:'post',
			data:{star:star, c_num:c_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//별점 총점을 다시 호출함
					$('#star_avg').text('평균별점 : ★'+param.starAvg);
					starCheck = 1;

					showReviewForm(user_num,starCheck,reviewCheck);
				}
			},
			error:function(){
				alert('별점 등록에서 네트워크 오류 발생');	
			}
		});
	});
	//=================== 리뷰 목록=========================
	let currentPage;
	let count;
	let rowCount;
	
	//리뷰 목록
	function selectReviewList(pageNum,sort){
		currentPage = pageNum;
		$.ajax({
			url:'reviewList.do',
			type:'post',
			data:{pageNum:pageNum,c_num:c_num,sort:sort},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum == 1){
					//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
					$('#review-view').empty();
				}
				
				$(param.reviewList).each(function(index,item){
					
					let reviewView = '<div class="review-box" onclick="location.href=&#39;reviewDetail.do';
					reviewView += '?c_review_num='+item.c_review_num;
					reviewView += '&c_num='+item.c_num;
					reviewView += '&#39;">';
					reviewView += '<span id="id">' +item.id +'</span>';
					reviewView += '<span id="star">★' +item.star +'</span>';
					reviewView += '<p id="content">'+item.c_review_content +'</p>'; //106자 제한주기
					reviewView += '<span id="like">좋아요 '+item.lcount +'</span>';
					reviewView += '</div>';
					
					//문서 객체에 추가
					$('#review-view').append(reviewView);
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
				alert('리뷰 목록에서 네크워크 오류 발생');
			}
		});
	}
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1);
	});
	
	//==================리뷰 쓰기 창============================
	var review_form;
	
	//리뷰 쓰기 창 띄우기
	function showReviewForm(user_num, starCheck, reviewCheck){
		$('#review_notDuplicated').empty();
		$('#review_duplicated').empty();
		if(user_num == -1){
			review_form = '<form id="review_form">';				
			review_form += '<textarea rows="10" cols="80" id="r_content" name="content" disabled="disabled">';
			review_form += '로그인이 필요합니다.</textarea>';
			review_form += '</c:if></form>';
			$('#review_duplicated').hide();
			$('#review_notDuplicated').append(review_form);
		} else if(starCheck==0){
			review_form = '<form id="review_form">';				
			review_form += '<textarea rows="10" cols="80" id="r_content" name="content" disabled="disabled">';
			review_form += '별점을 준 후에 이용하세요.</textarea>';
			review_form += '</c:if></form>';
			$('#review_duplicated').hide();
			$('#review_notDuplicated').append(review_form);
		} else if(user_num != null && starCheck == 1 && reviewCheck == 0) {
			review_form = '<form id="review_form">';
			review_form += '<input type="hidden" name="c_num2"  value=' +c_num +'>';
			review_form += '<textarea rows="10" cols="80" id="r_content" name="content"';
			review_form += 'placeholder="리뷰를 입력해주세요"></textarea>';
			review_form += '<input type="submit" value="등록">';
			review_form += '</c:if></form>';
			$('#review_duplicated').hide();
			$('#review_notDuplicated').append(review_form);
		} else if(reviewCheck == 1) {
			review_form = '<h2>이미 리뷰를 작성했습니다.</h2>';
			review_form += '<input id="myReview" type="button" value="내 리뷰 보러가기"'; 
			review_form += 'onclick="location.href=&#39;reviewDetail.do?c_review_num=0&c_num='+c_num+'&#39;">';
			$('#review_notDuplicated').hide();
			$('#review_duplicated').append(review_form);
		}
	}
	
	//========================리뷰 쓰기===========================
	//빈내용 확인
	$(document).on('submit','#review_form', function(event){
		if($('#r_content').val().trim() == ''){
			alert('내용을 입력하세요');
			$('#r_content').val('').focus();
			return false;	
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
					//첫번째 페이지 댓글 목록 다시 읽어오기(방금 작성한 댓글도 포함해서)
					selectReviewList(1,1);
					//작성 완료하면 리뷰 작성했습니다로 바꾸기
					showReviewForm(user_num,starCheck,1);
				}
			},
			error:function(){
				alert('리뷰 쓰기에서 네트워크 오류 발생');
			}	
		});
		event.preventDefault();
	});
	//정렬하기
	$('.sort').click(function(){
		selectReviewList(1,$(this).attr('data-num'));
	});
	
	selectReviewList(1,1);
	showReviewForm(user_num,starCheck,reviewCheck);
	
});




























