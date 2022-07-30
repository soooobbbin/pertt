$(function(){
	 $('.moveDown').click(function(){
		 $('html, body').animate({
			 scrollTop:$('.page-main').offset().top
		 }, 'slow');
	 });
	 
	
	$('#drop_menu').click(function(){
		$('.dropdown-content',this).animate('slideDown',500);
	},
	function(){
		$('.dropdown-content',this).animate('slideUp',500);
		
	});

	 
 
 });


