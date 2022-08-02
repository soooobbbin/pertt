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
						
						let myCommnetView = '<ul class="mycom_list">'+'<li>'+'<div class="mycom-1" onclick="location.href=\'../review/reviewDetail.do';
						myCommnetView += '?c_review_num='+item.c_review_num;
						myCommnetView += '&c_num='+item.c_num;
						myCommnetView += '&#39;">';
						myCommnetView += '<div id="com_delete">'+ ' <input type="button" data-com_num="'+item.com_num+'" value="삭제" id="delete_com_btn">'+'</div>';
						myCommnetView += '<span id="reg_date" style="color:#969696"> '+ item.com_reg_date + ' | ' +'</span>';
						myCommnetView += '<span id="review_content"> ' +item.review.c_review_content +'</span>'+'<br>';
						myCommnetView += '<p id="com_content"> ' +item.com_content +'</p>';
						myCommnetView += '</div>'+'</li>'+'</ul>';
						
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
	
	//댓글 삭제
	$(document).on('click','#delete_com_btn',function(){
		//댓글 번호
		let com_num = $(this).attr('data-com_num');
		
		$.ajax({
			url:'deleteComment.do',
			type:'post',
			data:{com_num:com_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(param.result == 'success'){
					alert('댓글이 삭제되었습니다');
					myComment(1);
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('삭제시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
		
	});
	
	
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$(document).on('click', '.paging-button input',function(){
		myComment(currentPage + 1);
	});	
		
	myComment(1);
});






















