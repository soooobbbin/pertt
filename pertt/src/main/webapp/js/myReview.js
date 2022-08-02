$(function(){
	
	let sort;
	//별점순 선택
	$(document).on('click', '#sortStar', function(){
		sort="1";
		$('#sortDate').css('font-weight','normal');
		$('#sortStar').css('font-weight','bold');
		myReview(1, sort);
	});
	$(document).on('click', '#sortDate', function(){
		sort="2";
		$('#sortDate').css('font-weight','bold');
		$('#sortStar').css('font-weight','normal');
		myReview(1, sort);
	});
	
	if(sort == null) sort = '1'; //처음 페이지 들어와서 sort 값 없으면 별점순으로 정렬
	let currentPage;
	let count;
	let rowCount;
	
	//내 리뷰 정보를 읽어와서 html 태그를 추가하는 ajax 
	function myReview (pageNum, sort){
		currentPage = pageNum;		
		$.ajax({
			url:'myPageReview.do',
			type:'post',
			data:{pageNum:pageNum,sort:sort},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				count = param.count;
				rowCount = param.rowCount;
				
				if(param.result == 'logout'){
					alert('로그인한 후에 이용할 수 있습니다.');
				} else if (param.result =='success'){
					count = param.count;
					rowCount = param.rowCount;
				
					if(pageNum == 1){
						//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
						$('#my_re').empty();
					}
					
					$(param.myReview).each(function(index,item){
						
						let myReviewView = '<div class="review-box" onclick="location.href=\'../review/reviewDetail.do';
						myReviewView += '?c_review_num='+item.c_review_num;
						myReviewView += '&c_num='+item.c_num;
						myReviewView += '&#39;">';
						myReviewView += '<img id="contents-image" src="..';
						myReviewView += '/images/'+item.ott_num +'/'+item.poster +'">';
						myReviewView += '<span id="star"> ★' +item.star +'</span>';
						myReviewView += '<span id="reg_date"> ' +item.c_review_reg_date +'</span>';
						myReviewView += '</div>';
						$('#my_re').append(myReviewView);
					});//end of each
					
					//page button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//다음 페이지가 없음
						$('.paging-button').hide();
					}else{
						//다음 페이지가 존재
						$('.paging-button').show();
					}
				} 
			},
			error:function(){
				alert('내 리뷰 불러오기에서 네트워크 오류');
			} 
		});
	}//end of my review function
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$(document).on('click', '.paging-button input',function(){
		myReview(currentPage + 1, sort);
	});	
		
	myReview(1, sort);
});






















