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
	
		let netflix = 0;
		let disney = 0;
		let watcha = 0;
		let tving = 0;
		let wavve = 0;
		
		if(!indi && !animation && !sf && !kbs && !tvn){
			alert('원하는 장르를 선택하세요.');
			return;
		}
		
		if(korea){
			watcha += 1;
			tving += 1;
			wavve += 1;
		}
		if(english){
			netflix += 1;
			disney += 1;
		}
		if(japan){
			netflix += 1;
			watcha += 1;
		}
		if(movie){
			netflix += 1;
			disney += 1;
			watcha += 1;
		}
		if(drama){
			netflix += 1;
			watcha += 1;
			tving += 1;
			wavve += 1;
		}
		if(variety){
			tving += 1;
			wavve += 1;
		}
		if(indi){
			watcha += 1;
		}
		if(animation){
			disney += 1;
		}
		if(sf){
			netflix += 1;
		}
		if(kbs){
			wavve += 1;
		}
		if(tvn){
			tving += 1;
		}
		
		const max = Math.max(netflix, disney, watcha, tving, wavve);
		//alert('최대값 :'+max);
		
		if(max == netflix){
			$('img#netflix').show(); 
		}
		if(max == disney){
			$('img#disney').show(); 
		}
		if(max == watcha){
			$('img#watcha').show(); 
		}
		if(max == tving){
			$('img#tving').show(); 
		}
		if(max == wavve){
			$('img#wavve').show(); 
		}
		$('img#down_arrow').show(); 
		$('#reset').show(); 
		
	});
		
	

	
});