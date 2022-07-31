$(function(){
	
	let values=[0,9900,12900,13900,17000];
	
	
	let input = document.getElementById('priceBar');
	let output = document.getElementById('output');
	
	input.oninput = function(){
		output.innerHTML = values[this.value].toLocaleString() +'원';
								//toLocaleString(): 1000단위 쉼표 표시
	};
	
	input.oninput();
	
});