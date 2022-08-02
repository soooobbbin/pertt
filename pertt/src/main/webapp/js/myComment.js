$(function(){
	
	let currentPage;
	let count;
	let rowCount;
	
	//내 리뷰 정보를 읽어와서 html 태그를 추가하는 ajax 
	function myComment (pageNum){
		currentPage = pageNum;		
		$.ajax({
			url:'myPageComment.do',
			type:'post',
			data:{pageNum:pageNum},
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
						$('#my_com').empty();
					}
					
					$(param.myComment).each(function(index,item){
						
						let myCommnetView = '<div class="review-box" onclick="location.href=&#39;reviewDetail.do';
						myCommnetView += '?c_review_num='+item.c_review_num;
						myCommnetView += '&c_num='+item.c_num;
						myCommnetView += '&#39;">';
						myCommnetView += '<span id="reg_date"> ' +item.c_review_reg_date +'</span>';
						myCommnetView += '<span id="reg_date"> ' +item.c_review_reg_date +'</span>';
						myCommnetView += '</div>';
						$('#my_com').append(myCommnetView);
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
		
	myComment(1);
});






















