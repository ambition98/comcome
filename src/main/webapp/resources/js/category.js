window.onload = function() {
	var sizeList = document.querySelectorAll('.size_cat');
	sizeList.forEach(li => {
		li.classList.add('hide');
	});
	
	var brandList = document.querySelectorAll('.brand_list');
	brandList.forEach(li => {
		li.addEventListener('mouseover', ()=>{
			var sizeCat = li.getElementsByClassName('size_cat')[0];
			sizeCat.classList.remove('hide');
			sizeCat.classList.add('active');
		});
	});
	
	brandList.forEach(li => {
		li.addEventListener('mouseout', ()=>{
			var sizeCat = li.getElementsByClassName('size_cat')[0];
			sizeCat.classList.remove('active');
			sizeCat.classList.add('hide');
		});
	});
	
	console.log(brandList);
	/*for(var brand of brandList) {
			//var sizeCat = document.getElementById('size_'+cnt++);
			var sizeCat = document.querySelectorAll('.size_cat');
			//sizeCat.classList.add('hide');
			sizeCat.forEach(li => {
				li.classList.add('hide');
			});
			
		brand.addEventListener('mouseover', function() {
			//console.log('in');
			sizeCat.classList.remove('hide');
			sizeCat.classList.add('active');	
			//e.stopPropagation();
		});
		
		brand.addEventListener('mouseout', function() {
			//console.log('out');
			sizeCat.classList.remove('active');
			sizeCat.classList.add('hide');	
			//e.stopPropagation();
		});
	}*/
/*	var sizeCat = document.getElementById('size_0');
	brandList[0].addEventListener('mouseover', function() {
		console.log('in');
		sizeCat.classList.remove('hide');
		sizeCat.classList.add('active');	
		//e.stopPropagation();
	});
	
	brandList[0].addEventListener('mouseout', function() {
		console.log('out');
		sizeCat.classList.remove('active');
		sizeCat.classList.add('hide');	
		//e.stopPropagation();
	});
	
	var sizeCat2 = document.getElementById('size_1');
	brandList[1].addEventListener('mouseover', function() {
		console.log('in');
		sizeCat2.classList.remove('hide');
		sizeCat2.classList.add('active');	
		//e.stopPropagation();
	});
	
	brandList[1].addEventListener('mouseout', function() {
		console.log('out');
		sizeCat2.classList.remove('active');
		sizeCat2.classList.add('hide');	
		//e.stopPropagation();
	});*/
	
	//console.log(brandList[0]);
	
/*	brandList[0].addEventListener('mouserover', function() {
		var sizeCat = document.getElementById('size_0');
		console.log(sizeCat.classList);
		sizeCat.classList.add('active');
	});
	
	brandList[0].addEventListener('mouserout', function() {
		var sizeCat = document.getElementById('size_0');
		sizeCat.classList.remove('active');
		sizeCat.classList.add('hide');
	});*/
}