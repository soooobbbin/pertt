$(function(){
	//리뷰 목록
	let currentPage;
	let count;
	let rowCount;
	
	//리뷰 목록
	function selectReviewList(pageNum){
		currentPage = pageNum;
		var c_num = $('#c_num2').val();
		$.ajax({
			url:'reviewList.do',
			type:'post',
			data:{pageNum:pageNum,c_num:c_num},
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
					/*
					<div class="review-box" onclick="location.href='reviewDetail.do?r_num=${review.c_review_num}'">
					<span id="id">${review.id }</span>
					<span id="star">별점</span>
					<p id="content">${fn:substring(review.c_review_content, 0, 106)}</p>
					<span id="like">추천수</span>
					</div>
					*/
					
					let reviewView = '<div class="review-box" onclick="location.href=&#39;reviewDetail.do';
					reviewView += '?c_review_num='+item.c_review_num;
					reviewView += '&c_num='+c_num;
					reviewView += '&#39;">';
					reviewView += '<span id="id">' +item.id +'</span>';
					reviewView += '<span id="star">starf;' +item.star +'</span>';
					reviewView += '<p id="content">${fn:substring(' +item.c_review_content +', 0, 106)}</p>';
					reviewView += '<span id="like">'+item.lcount +'</span>';
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
	
	//========================리뷰 쓰기===========================
	//빈내용 확인
	$('#review_form').submit(function(event){
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
					selectReviewList(1);
					//작성 완료하면 리뷰 작성했습니다로 바꾸기
					$('#review_duplicated').show();
					$('#review_notDuplicated').hide();
				}
			},
			error:function(){
				alert('리뷰 쓰기에서 네트워크 오류 발생');
			}	
		});
		event.preventDefault();
	});
	
	selectReviewList(1);
});




























