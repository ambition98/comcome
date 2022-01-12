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
}