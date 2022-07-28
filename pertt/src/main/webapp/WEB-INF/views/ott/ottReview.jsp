<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTT 별점 주기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ott-star.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ottStar.js"></script>
</head>
<body>
<div class="page-main">
<div class="align-center">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="botton">
		<a href="ottReview.do?ott_num=1"><img src="../images/logo_square_netflix.png"/></a>
		<a href="ottReview.do?ott_num=2"><img src="../images/logo_square_disney.png"/></a>
		<a href="ottReview.do?ott_num=3"><img src="../images/logo_square_watcha.png"/></a>
		<a href="ottReview.do?ott_num=4"><img src="../images/logo_square_tving.png"/></a>
		<a href="ottReview.do?ott_num=5"><img src="../images/logo_square_wavve.png"/></a>
	</div>
	<div align="center"></div>
    <img src="../images/logo_netflix.png" width=300>
    <h1>가성비</h1>
			<!--=======별점 부분=======-->
			<%--평가 기록이 없으면(OttStarVO == null) 평가할수있는 별점 div를 표시, 
			기록이 있으면(OttStarVO != null) OttStarVO에서 price,usability,quality(별점)을 불러와 
			점수에 따라 별점 width 변화 --%>
			<div class="star_area">
				<input type="hidden" value="${OttStarVO.ott_star_num }" id="ott_starnum_star">
				<c:if test="${OttStarVO == null}">
					<%--평가기록 없을 때 --%>
					<div class="rateit" id="starRate"
						data-contentsid="${OttStarVO.ott_num}" data-rateit-mode="font"
						style="font-size: 38px;"></div>
					<script type="text/javascript">	
			/* 비회원 체크 후 alert 호출  */
			  $("#starRate").bind('rated', function (event, value) {
				  var user_num = ${user_num};
				  if(user_num==0){ // 비회원상태: user_num=0
					  Swal.fire({			
						  title: ' ',						  
						  text: '평가하시려면 로그인하세요.',
						  imageUrl: '${pageContext.request.contextPath}/resources/images/star_icon.png',
						  imageWidth: 70,
						  imageHeight: 70,						  
						  imageAlt: 'Custom image',
						  confirmButtonColor: '#84d7fa',
						  confirmButtonText: '로그인',
						  width: 400,
						  padding: '2em'
						  })
					  //alert('평가하시려면 로그인이 필요해요.');
					  $('.rateit-selected').css('width',''); //클릭된 별점 reset
				  };				 
			  });			
			</script>
				</c:if>
				<c:if test="${OttStarVO != null}">
					<%--평가기록 있을때 --%>
					<div class="rateit" id="starRate"
						data-contentsid="${OttStarVO.ott_num}" data-rateit-mode="font"
						style="font-size: 38px;"></div>
					<script type="text/javascript">
			$(function(){
				$('#do_rating').hide(); //평가하기문구hide
				var rate = ${OttStarVO.price};				
				if(rate==0.5){
					$('.rateit-selected').css('width','18.9965px');
					$('#rating_text').text('최악이에요');	
					}
				if(rate==1){
					$('.rateit-selected').css('width','37.993px');
					$('#rating_text').text('싫어요');	
					}
				if(rate==1.5){
					$('.rateit-selected').css('width','56.9895px');
					$('#rating_text').text('재미없어요');	
					}
				if(rate==2){
					$('.rateit-selected').css('width','75.986px');
					$('#rating_text').text('별로예요');	
					}
				if(rate==2.5){
					$('.rateit-selected').css('width','94.9825px');
					$('#rating_text').text('부족해요');	
					}
				if(rate==3){
					$('.rateit-selected').css('width','113.979px');
					$('#rating_text').text('보통이에요');	
					}
				if(rate==3.5){
					$('.rateit-selected').css('width','132.976px');
					$('#rating_text').text('괜찮아요');	
					}
				if(rate==4){
					$('.rateit-selected').css('width','151.972px');
					$('#rating_text').text('재미있어요');	
					}
				if(rate==4.5){
					$('.rateit-selected').css('width','170.969px');
					$('#rating_text').text('훌륭해요!');	
					}
				if(rate==5){
					$('.rateit-selected').css('width','189.965px');
					$('#rating_text').text('최고예요!');					
				}
				var rate = ${OttStarVO.usability};				
				if(rate==0.5){
					$('.rateit-selected').css('width','18.9965px');
					$('#rating_text').text('최악이에요');	
					}
				if(rate==1){
					$('.rateit-selected').css('width','37.993px');
					$('#rating_text').text('싫어요');	
					}
				if(rate==1.5){
					$('.rateit-selected').css('width','56.9895px');
					$('#rating_text').text('재미없어요');	
					}
				if(rate==2){
					$('.rateit-selected').css('width','75.986px');
					$('#rating_text').text('별로예요');	
					}
				if(rate==2.5){
					$('.rateit-selected').css('width','94.9825px');
					$('#rating_text').text('부족해요');	
					}
				if(rate==3){
					$('.rateit-selected').css('width','113.979px');
					$('#rating_text').text('보통이에요');	
					}
				if(rate==3.5){
					$('.rateit-selected').css('width','132.976px');
					$('#rating_text').text('괜찮아요');	
					}
				if(rate==4){
					$('.rateit-selected').css('width','151.972px');
					$('#rating_text').text('재미있어요');	
					}
				if(rate==4.5){
					$('.rateit-selected').css('width','170.969px');
					$('#rating_text').text('훌륭해요!');	
					}
				if(rate==5){
					$('.rateit-selected').css('width','189.965px');
					$('#rating_text').text('최고예요!');					
				}
				var rate = ${OttStarVO.quality};				
				if(rate==0.5){
					$('.rateit-selected').css('width','18.9965px');
					$('#rating_text').text('최악이에요');	
					}
				if(rate==1){
					$('.rateit-selected').css('width','37.993px');
					$('#rating_text').text('싫어요');	
					}
				if(rate==1.5){
					$('.rateit-selected').css('width','56.9895px');
					$('#rating_text').text('재미없어요');	
					}
				if(rate==2){
					$('.rateit-selected').css('width','75.986px');
					$('#rating_text').text('별로예요');	
					}
				if(rate==2.5){
					$('.rateit-selected').css('width','94.9825px');
					$('#rating_text').text('부족해요');	
					}
				if(rate==3){
					$('.rateit-selected').css('width','113.979px');
					$('#rating_text').text('보통이에요');	
					}
				if(rate==3.5){
					$('.rateit-selected').css('width','132.976px');
					$('#rating_text').text('괜찮아요');	
					}
				if(rate==4){
					$('.rateit-selected').css('width','151.972px');
					$('#rating_text').text('재미있어요');	
					}
				if(rate==4.5){
					$('.rateit-selected').css('width','170.969px');
					$('#rating_text').text('훌륭해요!');	
					}
				if(rate==5){
					$('.rateit-selected').css('width','189.965px');
					$('#rating_text').text('최고예요!');					
				}
			});
			</script>
				</c:if>
			</div>
			<script type="text/javascript">	
		$(function(){
			//(1)별점입력 및 변경
			$('.star_area .rateit').bind('rated', function (e) { //rated reset		
		        var ri = $(this);
	      
		        var user_num = ${user_num};		        
				var value = ri.rateit('value'); 

		          $.ajax({
		            url: 'WriteOttStarAction.do', 
		            data: { 
						star: value,
						ott_num : $('#ott_num').val(),
						member_num : user_num,
						ott_star_num : ott_star_num
						}, 
		            dataType : 'json',
		            type: 'POST',
		            success: function (param) { 
		            	if(param.result == 'logout'){
							alert('로그인 후 사용하세요!');
		            	}else if(param.result == 'success'){ //별점기록없으면 insert
		            		$('#starnum_star').val(param.star_num);
		            	}else if(param.result == 'success2'){ //별점기록있으면  update
		            	}else{
							alert('별점입력 오류 발생');
						}	
		            },
		            error: function () {
		            	alert('네트워크 오류 발생');
		            }
		        });  //end of ajax
				
		    });//별점입력끝 
		    
		    //(2)별점 취소 (0.5점 클릭->취소버튼 노출->취소가능)
			 $("#starRate").bind('reset', function () { //reset버튼클릭시 이벤트 발생
				 var user_num = ${user_num};
			 
		    	 $('#rating_text').text('평가하기');	//평가하기문구다시노출 
		    	 $('#rateit-reset-2').css("visibility","hidden"); //리셋버튼감추기
		    	 /* alert('평가를 취소하시겠습니까?')  */
		    	 $.ajax({
						url:'resetRating.do',
						type:'post',
						data: {
							ott_num : $('#ott_num').val(),
							member_num : user_num
							},
						dataType: 'json',
						cache:false,
						timeout:30000,
						success:function(param){
							if(param.result == 'logout'){
								alert('로그인 후 사용하세요')					
							}else if(param.result == 'success'){
								/* alert('평가취소완료');	 */
							}else{
								alert('삭제시 오류 발생');
							}
						},
						error:function(){
							alert('네트워크 오류 발생')
						}
				}); 
			});	 
			    
			//(3)별점에 따른 평가 문구 설정	    	
		    $("#starRate").bind('rated', function (event, value) { //rated시 이벤트 발생
		    	
		    	$('#rateit-reset-2').css("visibility","hidden"); //리셋버튼hide		    	
		    	$('#do_rating').hide(); //평가하기문구hide
		    	
			   	 if(value === 5 ){ 
			   	 	$('#rating_text').text('최고예요!');		   
			   	 }
			   	 if(value === 4.5){
			   		 $('#rating_text').text('훌륭해요!');		   
			   	 }
			   	 if(value === 4 ){
			   		 $('#rating_text').text('재미있어요');		   
			   	 }
			   	 if(value === 3.5 ){
			   		 $('#rating_text').text('볼만해요');		   
			   	 }
			   	 if(value === 3 ){
			   		 $('#rating_text').text('보통이에요');		   
			   	 }
			   	 if(value === 2.5){
			   		 $('#rating_text').text('부족해요');		   
			   	 }
			   	 if(value === 2){
			   		 $('#rating_text').text('별로예요');		   
			   	 }
			   	 if(value === 1.5){
			   		 $('#rating_text').text('재미없어요');		   
			   	 }
			   	 if(value === 1){
			   		 $('#rating_text').text('싫어요');		   
			   	 }
			   	 if(value === 0.5){ 
			   		 $('#rating_text').text('최악이에요');	
			  
												//0.5 hover시 리셋버튼 클릭어려워서 0.5클릭 시 리셋버튼 뜨게 설정
																			$(
																					'#rateit-reset-2')
																					.css(
																							"visibility",
																							"visible");
																		}
																	});//평가문구끝
												});
											</script>
			<!--======별점 부분 끝======-->
		</div>
</div>
</body>
</html>