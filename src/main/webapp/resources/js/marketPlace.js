class Item {
	
	constructor(itemId , name , quantity ,price ,imgSrc){
		this.id = itemId ;
		this.name = name ; 
		this.quantity = quantity;
		this.price = price;
		this.imgSrc = imgSrc;
	}
	
}
numberOfItemsInCart();
// this array to save all items that user needed 
let userItems = [];
loadTheItemsInCart();
function loadTheItemsInCart(){
	let cartList = localStorage.getItem('cart');
	var parsedList = JSON.parse(cartList);
	if(parsedList !== null){
		parsedList.forEach(item=>{
		userItems.push(item);
	});
	}
}
function addNewItem(item){
	userItems.push(item);
	let itemJson = JSON.stringify(userItems);
	localStorage.setItem("cart",itemJson)
	console.log(userItems.length);

}
let cartIcon = document.querySelector('.beforeCart');
function updateNumOfItemInCart(){
	let itemNumber = cartIcon.textContent;
	itemNumber = Number(itemNumber) + 1;
	 cartIcon.textContent= itemNumber;	
}


let sectioItems = document.querySelector(".section_items");
sectioItems.addEventListener('click',event=>{
	let link = event.target;
		if (link.classList.contains('description')) {
		let card = link.parentNode.parentNode.parentNode;
		if(card.classList.contains('card-top')){
			card = card.parentNode;
		}
		let itemId = card.id;
		let card_bottom = card.querySelector('.card-bottom');
		console.log(card_bottom);
		let price = card_bottom.querySelector('#after-discount'); 
		let itemName = card_bottom.querySelector('.item-name');
		console.log(itemName);
		let imgDescription = link.title;
		let imgElement = card.querySelector('img');
		let imgsrc = imgElement.getAttribute('src');
		
		overlay_div = document.createElement("div");
     	overlay_div.classList.add("overlay");
		
		let product_container = document.createElement('div');
		product_container.classList.add('product-container');
		let h3 = document.createElement('h3');
		h3.setAttribute("id",itemId);
		let h3Text = document.createTextNode(itemName.textContent);
		h3.appendChild(h3Text);
		let spanClose = document.createElement('span');
		spanClose.addEventListener('click',()=>{
			overlay_div.classList.remove("overlay");
			product_container.style.display = "none";
		});
		let spanCloseIcon = document.createElement('i');
		spanCloseIcon.classList.add('fa-solid');
		spanCloseIcon.classList.add('fa-circle-xmark');
		spanClose.appendChild(spanCloseIcon)
		
		product_container.appendChild(spanClose);
		let divInfo = document.createElement('div');
		divInfo.appendChild(h3);
		divInfo.classList.add('info');
		
		product_container.appendChild(divInfo);
		
		
		let divDescription = document.createElement('div');
		divDescription.classList.add('description');
		
		divInfo.appendChild(divDescription);
		
		let divP = document.createElement('p');
		let divPContent = document.createTextNode(imgDescription);
		divP.appendChild(divPContent);
		divDescription.appendChild(divP);
		
		divInfo.appendChild(divDescription);
		
		let divPrice = document.createElement('div');
		let priceText = price.textContent; 
		let numericalPart = priceText.match(/\d+(\.\d+)?/); // Matches one or more digits optionally followed by a decimal and more digits
		let itemPrice = numericalPart ? Number(numericalPart[0]) : null; // Convert to number or null if no numerical part found 
		let textPrice = document.createTextNode(price.textContent);
		divPrice.appendChild(textPrice);
		divPrice.classList.add("price");
		divDescription.appendChild(divPrice);
		let divQuantityContainer = document.createElement('div');
		let increaseButton = document.createElement('button');
		increaseButton.appendChild(document.createTextNode("+"));
		let quantityInput = document.createElement('input');
		quantityInput.setAttribute('type','number');
		quantityInput.setAttribute('value','1');
		let desreaseButton = document.createElement('button');
		desreaseButton.appendChild(document.createTextNode("-"));
		increaseButton.classList.add('change');
		quantityInput.classList.add('quantitiy');
		desreaseButton.classList.add('change');
		
		increaseButton.addEventListener('click',()=>{
			let valueInput = quantityInput.value;
			valueInput = Number(valueInput) + 1
			quantityInput.value = valueInput;
		});
		
		desreaseButton.addEventListener('click',()=>{
			let valueInput = quantityInput.value;
			valueInput = Number(valueInput) - 1
			if(valueInput === 0){
				valueInput = 1;
			}
			quantityInput.value = valueInput;
		});
			
	
		let buttonAdd = document.createElement('button');
		let buttonContent = document.createTextNode('اضف ');
		buttonAdd.appendChild(buttonContent);
		buttonAdd.classList.add('add');
		
		
		
		let iconDescription = document.createElement('i');
		iconDescription.classList.add('fa-solid');
		iconDescription.classList.add('fa-cart-arrow-down');
		buttonAdd.appendChild(iconDescription);
		if(event.target.tagName !=="A"){
		divQuantityContainer.appendChild(increaseButton);
		divQuantityContainer.appendChild(quantityInput);
		divQuantityContainer.appendChild(desreaseButton);
		divQuantityContainer.appendChild(buttonAdd);
		divDescription.appendChild(divQuantityContainer);
		}
		buttonAdd.addEventListener("click",()=>{
			let newItem = new Item(itemId,itemName.textContent,quantityInput.value,itemPrice,imgsrc);
			addNewItem(newItem);
			updateNumOfItemInCart();
			overlay_div.classList.remove("overlay");
			product_container.style.display = "none";
		});
		
		let divImg = document.createElement('div');
		divImg.classList.add('img');
		let img = document.createElement('img');
		img.setAttribute('src',imgsrc);
		divImg.appendChild(img);
		
		product_container.appendChild(divImg);
		
		overlay_div.appendChild(product_container);
		
		document.body.appendChild(overlay_div);
	}

});
// start number of item in cart
function numberOfItemsInCart(){
	let cartIcon = document.querySelector('.beforeCart');
	let cartList = localStorage.getItem('cart');
	var parsedList = JSON.parse(cartList);
	let numbers = 0;
     if (parsedList !== null){
		numbers = parsedList.length ;
	}
	

    cartIcon.textContent= numbers;	

}

// end  number of item in cart 

	