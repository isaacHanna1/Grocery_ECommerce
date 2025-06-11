
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
function swap(event) {
    let img = event.currentTarget;
    let productContainer = document.querySelector(".photo-container");
    let mainImage = productContainer.querySelector(".photo-main img");
    let temp = mainImage.src;
    mainImage.src = img.src;
    img.src = temp;
}

const allSubImages = document.querySelectorAll(".photo-album img");
allSubImages.forEach(img => {
    img.addEventListener('click', swap);
});

function addNewItem(item){
	
	// this line of code getting the stored chart in local if not 
	// they create new one || []
	// and add new item , before adding it check the item existence in list
	// if exist they update the quantity
	let cartList = JSON.parse(localStorage.getItem('cart')) || [];
	
	
	// if they  existing retrive its index else retrive -1  ; 
	let existingItemIndex = cartList.findIndex(cartItem => cartItem.id === item.id);
	
	console.log("the existence :"+existingItemIndex);
	
    if (existingItemIndex !==-1) {
        // If the item is already in the cart, you may choose to update its quantity or take other action
        cartList[existingItemIndex].quantity = parseInt(cartList[existingItemIndex].quantity)+parseInt(item.quantity);
        console.log('Item already in cart. Quantity updated.'+existingItemIndex);
		console.log('the quantity is:'+cartList[existingItemIndex].quantity);
	    localStorage.setItem('cart', JSON.stringify(cartList));
    } else {
	userItems.push(item);
	let itemJson = JSON.stringify(userItems);
	localStorage.setItem("cart",itemJson)
	console.log(userItems.length);
	}
}
let cartIcon = document.querySelector('.beforeCart');
function updateNumOfItemInCart(){
	let itemNumber = cartIcon.textContent;
	itemNumber = Number(itemNumber) + 1;
	 cartIcon.textContent= itemNumber;	
}
//create item to be add to chart 
function createItem(){
	
	let itemId   = document.getElementById("ItemId").innerHTML;
	let itemName = document.getElementById("itemName").innerHTML;
	let quantity = document.getElementById("itemQuantity").value;
	let price    = document.getElementById("itemPrice").innerHTML;
	let imgSrc   = document.getElementById("mainImgId").src;
	
	let item = new Item(itemId,itemName,quantity,price,imgSrc);
	addNewItem(item);
}
let btnAdd = document.getElementById("btnAdd");
let btnPlus = document.getElementById("btnPlus");
let btnMinus = document.getElementById("btnMinus");

btnAdd.onclick = function(){
	createItem();
	updateNumOfItemInCart();
    notifyUser();
}
btnPlus.onclick = function(){
changeCartNumber(1);
}

btnMinus.onclick = function(){
changeCartNumber(-1);
}


function changeCartNumber(add){	
	let currentQuantityElement = document.getElementById("itemQuantity");
    let currentQuantity = Number(currentQuantityElement.value);
    let valueInput = currentQuantity + add;
    if (valueInput < 1) {
        valueInput = 1;
    }
    currentQuantityElement.value = valueInput;
}

// i make this function to notify user when clicked button to add to chart
// and show message that item added to chart
function notifyUser() {
  let notifyMessage = document.getElementById("notify_id");
  notifyMessage.classList.remove("dis-none");
  hideNotify("notify_id", 2000);
}
function hideNotify(element_id, number_Of_MillSec) {
  let element = document.getElementById(element_id);
  if (element) {
    setTimeout(function () {
      element.classList.add("dis-none");
    }, number_Of_MillSec);
  }
}

const buyNowButton = document.getElementById('buyNow');
buyNowButton.addEventListener('click', function () {
	const itemId = document.querySelector('.product').id;
    const itemName = document.getElementById('itemName').textContent;
    const quantity = document.getElementById('itemQuantity').value;
    const itemPrice = document.getElementById('itemPrice').textContent;
    const imgsrc = document.getElementById('mainImgId').src;
    buyNow(itemId, itemName, quantity, itemPrice, imgsrc);
});
function buyNow(itemId, itemName, quantity, itemPrice, imgsrc) {
  let newItem = new Item(itemId, itemName, quantity, itemPrice, imgsrc);
  localStorage.removeItem('cart');
  let cartList = [newItem];
  localStorage.setItem("cart", JSON.stringify(cartList));
  const host = window.location.origin;
  const link = host+"/invoice";
  window.location.href = link;
}