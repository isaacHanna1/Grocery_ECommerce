let container = document.querySelector('.content');
let totalOfInvoice = document.querySelector("#totalOfReceipt");
createInvoice();
numberOfItemsInCart();

function createInvoice(){
	
let chartList = localStorage.getItem('cart');
var parsedList = JSON.parse(chartList);

parsedList.forEach(item=>{
let divRow1 =  document.createElement('div');
let divRow2 =  document.createElement('div');
let invoiceRow =  document.createElement('div');
invoiceRow.setAttribute("id",item.id);
invoiceRow.classList.add("invoice");
container.appendChild(invoiceRow);
invoiceRow.appendChild(divRow1);
invoiceRow.appendChild(divRow2);

divRow1.classList.add("row");
divRow2.classList.add("row");
divRow2.classList.add("btns");

let divInfo       =  document.createElement('div');
let divItemPrice  =  document.createElement('div');

divInfo.classList.add("info");
divItemPrice.classList.add("item-price");

//in div info
let divImg      =  document.createElement('div');
divImg.classList.add("img");

let img      =  document.createElement('img');
img.setAttribute("src",item.imgSrc);

divImg.appendChild(img);

let h3      =  document.createElement('h3');
let h3Text      =  document.createTextNode(item.name);
h3.appendChild(h3Text);

// in div item-price 
let divPrice      =  document.createElement('div');
let divTotal      =  document.createElement('div');
divPrice.classList.add("price");

let span1      =  document.createElement('span');
let spanLabel       =  document.createElement('span');
 
let labelText  = document.createTextNode("السعر :");
let priceText  = document.createTextNode(item.price);

span1.appendChild(priceText);
spanLabel.appendChild(labelText);
divPrice.appendChild(spanLabel);
divPrice.appendChild(span1);

let span2      =  document.createElement('span');
span2.setAttribute("class","total-item");	
let spanLabel2       =  document.createElement('span');
let labelText2  = document.createTextNode("الاجمالي :");
let totalText = document.createTextNode("100");
span2.appendChild(totalText);
spanLabel2.appendChild(labelText2);
divTotal.classList.add("total");
divTotal.appendChild(spanLabel2);
divTotal.appendChild(span2);


divItemPrice.appendChild(divPrice);
divItemPrice.appendChild(divTotal);
divRow1.appendChild(divInfo);
divInfo.appendChild(divImg);
divInfo.appendChild(h3);
divRow1.appendChild(divItemPrice);


let divIcon = document.createElement("div");
divIcon.addEventListener("click",()=>{
	confirmationMessage(item.id , invoiceRow);
});
divIcon.classList.add("icon");

let iconText = document.createTextNode(" إزالة ");


let i = document.createElement("i");
i.classList.add("fa-regular");
i.classList.add("fa-trash-can");
divIcon.appendChild(i);
divIcon.appendChild(iconText);

divRow2.appendChild(divIcon);

let divQuantity = document.createElement("div");
divQuantity.classList.add("quantitiy");

let buttonAdd = document.createElement("button");
buttonAdd.appendChild(document.createTextNode("+"));

let inputQuanitiy = document.createElement("input");
inputQuanitiy.setAttribute("type","number");
inputQuanitiy.setAttribute("value",item.quantity);
inputQuanitiy.setAttribute("disabled","disabled");
let buttonMins = document.createElement("button");
buttonMins.appendChild(document.createTextNode("-"));

divQuantity.appendChild(buttonAdd);
divQuantity.appendChild(inputQuanitiy);
divQuantity.appendChild(buttonMins);

divRow2.appendChild(divQuantity);


buttonAdd.addEventListener('click',()=>{
			let valueInput = inputQuanitiy.value;
			valueInput = Number(valueInput) + 1
			if(valueInput === 0){
				valueInput = 1;
			}
			inputQuanitiy.value = valueInput;
			let theQuantity = inputQuanitiy.value;
			let theItemPrice = item.price;
			let theTotal = total(theItemPrice , theQuantity);
			span2.innerHTML = theTotal;
			calcAllItems();
			updateItemInLocalStorage(item.id,theQuantity);
		});
		buttonMins.addEventListener('click',()=>{
			let valueInput = inputQuanitiy.value;
			valueInput = Number(valueInput) - 1
			if(valueInput === 0){
				valueInput = 1;
			}
			inputQuanitiy.value = valueInput;
			let theQuantity = inputQuanitiy.value;
			let theItemPrice = item.price;
			let theTotal = total(theItemPrice , theQuantity);
			span2.innerHTML = theTotal;
			calcAllItems();
			updateItemInLocalStorage(item.id,theQuantity);
		});
let theQuantity = inputQuanitiy.value;
let theItemPrice = item.price;
let theTotal = total(theItemPrice , theQuantity);
span2.innerHTML = theTotal;

calcAllItems();

});


}
function total (quantity , price){
	return quantity * price ;
}

// start number of item in cart
function numberOfItemsInCart(){
	let cartIcon = document.querySelector('.beforeCart');
	let cartList = localStorage.getItem('cart');
	var parsedList = JSON.parse(cartList);
	let numbers = parsedList.length;
    cartIcon.textContent= numbers;	
}

function calcAllItems(){
	console.log("called");
	let allItems = document.querySelectorAll(".total-item");
	let total = 0;
	allItems.forEach(item=>{
		total = total + Number(item.textContent);
	});
	totalOfInvoice.innerHTML = total;
}
function updateItemInLocalStorage(itemId , newQuantity){
	  let cartList = localStorage.getItem('cart');
	  if (cartList) {
        let parsedList = JSON.parse(cartList);
        if (Array.isArray(parsedList)) {
         let itemIndex = parsedList.findIndex(item => item.id === itemId.toString());
		 if (itemIndex !== -1) {
			parsedList[itemIndex].quantity = newQuantity;
			localStorage.setItem('cart', JSON.stringify(parsedList));
			                console.log('Quantity updated successfully.');
            } else {
                console.error('Item not found in cart.');
            }
        } else {
            console.error('Invalid data in localStorage. Expected an array.');
        }
    } else {
        console.error('No data found in localStorage.');
    }
}
 function removeItemFromCart(itemId){
	let cartList = localStorage.getItem('cart');
	  if (cartList) {
        let parsedList = JSON.parse(cartList);
        if (Array.isArray(parsedList)) {
        	let updatedList = parsedList.filter(item => item.id !== itemId.toString());
            // Update the localStorage with the updated list
            localStorage.setItem('cart', JSON.stringify(updatedList));
            console.log('Item removed successfully.');
            } else {
                console.error('Item not found in cart.');
            }
        } else {
	        console.error('No data found in localStorage.');
        }
}
	
// end  number of item in cart 

function confirmationMessage(itemId,removeElement){
	overlay_div = document.createElement("div");
     overlay_div.classList.add("overlay");
    document.body.appendChild(overlay_div);
    let dialogContainer = document.createElement("div");
    dialogContainer.classList.add("dialogContainer");
    document.body.appendChild(dialogContainer);

    let textContainer = document.createElement("div");
    textContainer.classList.add("textContainer");
    let textAlert = document.createTextNode("تاكيد المسح");
    textContainer.appendChild(textAlert);
    dialogContainer.appendChild(textContainer);

    let btnContainer = document.createElement("div");
    let okBtn = document.createElement("button");
    let cancelBtn = document.createElement("button");
    okBtn.appendChild(document.createTextNode("تاكيد"));
    cancelBtn.appendChild(document.createTextNode("الغاء"));

	btnContainer.appendChild(okBtn);
    btnContainer.appendChild(cancelBtn);
    btnContainer.classList.add("btnContainer");
    dialogContainer.appendChild(btnContainer);

cancelBtn.onclick = function () {
      overlay_div.classList.remove("overlay");
      dialogContainer.classList.remove("dialogContainer");
	  dialogContainer.style.display="none";
    };
    okBtn.onclick = function () {
      overlay_div.classList.remove("overlay");
      dialogContainer.classList.remove("dialogContainer");
	  dialogContainer.style.display="none";
	  removeItemFromCart(itemId);
	  container.removeChild(removeElement);
	  calcAllItems();
	  numberOfItemsInCart();
}
}