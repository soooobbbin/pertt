$(function(){
   $(document).on('click','.rating',function(e){
      let elem = e.target;
        if(elem.classList.contains('rate-check')){
           $(this).find('.rate-check').each(function(index, item){
                if(index < elem.value){
                    item.checked = true;
                }else{
                    item.checked = false;
                }
            });
            $(this).find('.rate-star').val(elem.value);
        }
   });
   
   $('#ottLike_form').submit(function(evnet){
		let form_data = $(this).serialize();
		alert(form_data);
		
		
		
		//기본 이벤트 제거
		event.preventDefault();
	});
   
   
   
});
