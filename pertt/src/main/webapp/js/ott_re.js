$(function(){
	
	let values=[0,9900,12900,13900,17000];
	
	
	let input = document.getElementById('priceBar');
	let output = document.getElementById('output');
	
	input.oninput = function(){
		output.innerHTML = values[this.value].toLocaleString() +'원';
								//toLocaleString(): 1000단위 쉼표 표시
	};
	
	input.oninput();
	
	
	//=========== 결과 버튼 클릭 시 ===============
	$('#ottCheckBtn').click(function(){
		//let genre1 = document.getElementById('#cb3-1');
		let korea = $('#cb1').is(':checked');
		let english = $('#cb2').is(':checked');
		let japan = $('#cb3').is(':checked');
		let movie = $('#cb4').is(':checked');
		let drama = $('#cb5').is(':checked');
		let variety = $('#cb6').is(':checked');
		
		let indi = $('#cb7').is(':checked');
		let animation = $('#cb8').is(':checked');
		let sf = $('#cb9').is(':checked');
		let kbs = $('#cb10').is(':checked');
		let tvn = $('#cb11').is(':checked');
	
		
		if(!indi && !animation && !sf && !kbs && !tvn){
			alert('원하는 장르를 선택하세요.');
			return;
		}
		if(sf){
			$('img#netflix').show(); 
			
			}
		$('img#down_arrow').show(); 
		
	
		
		
	});
	
	
});