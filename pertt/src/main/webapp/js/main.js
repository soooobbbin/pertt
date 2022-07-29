$(function(){
	 $('.moveDown').click(function(){
		 $('html, body').animate({
			 scrollTop:$('.page-main').offset().top
		 }, 'slow');
	 });
	 
	
	$('#main_nav').hover(function(){
		$('.dropdown-content',this).animate('slideDown',500);
	},
	function(){
		$('.dropdown-content',this).animate('slideUp',500);
		
	});

	 
 
 });


