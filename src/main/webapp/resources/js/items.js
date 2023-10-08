

let customerPrice = 0;
let tradePrice = 0;


/*
i made this function , when user enter profit margin in textbox 
automatic set the profit  to final price to customer or trade 
*/
let calcFinalPriceFromProfitMargin = function(moneyAmount , margin){
	return (moneyAmount * margin) / 100 ;	
};



function updateSellingPriceCustomer(){

const purchasePrice = document.getElementById
							("purchasePrice").value;
							
const profitMarginCustomer = document.getElementById
							("profitMarginCustomer").value;
							
const sellingPriceCustomer = document.getElementById
							("sellingPriceCustomer");
	sellingPriceCustomer.value = "";						
let finalPrice = calcFinalPriceFromProfitMargin(purchasePrice,profitMarginCustomer)
let result = Number(purchasePrice)+Number(finalPrice);
sellingPriceCustomer.value = result.toFixed(2);			
customerPrice = result.toFixed(2);
updateSellingPriceCustomerAfterDiscount();
}


function updateSellingPriceCustomerAfterDiscount(){

const discountPercentageCustomer = document.getElementById
							("discountPercentageCustomer").value;
							
const sellingPriceCustomer = document.getElementById
							("sellingPriceCustomer")	

	sellingPriceCustomer.value="";
let finalPrice = calcFinalPriceFromProfitMargin(customerPrice,discountPercentageCustomer)
let result = (Number(customerPrice)) - (Number(finalPrice));
	sellingPriceCustomer.value = result.toFixed(2);	
}

function updateSellingPriceTraderAfterDiscount(){

const discountPercentageTrader = document.getElementById
							("discountPercentageTrader").value;
							
const sellingPriceTrader = document.getElementById
							("sellingPriceTrader")	


let finalPrice = calcFinalPriceFromProfitMargin(tradePrice,discountPercentageTrader)
let result = (Number(tradePrice)) - (Number(finalPrice));
	sellingPriceTrader.value = result.toFixed(2);	
}
							

function updateSellingPriceTrader(){
	
	
	const purchasePrice = document.getElementById
							("purchasePrice").value;
							
	const profitMarginTrader = document.getElementById
							("profitMarginTrader").value;
							
	const sellingPriceTrader = document.getElementById
							("sellingPriceTrader");
							
	let finalPrice = calcFinalPriceFromProfitMargin(purchasePrice,profitMarginTrader)
	let result = Number(purchasePrice)+Number(finalPrice);
	sellingPriceTrader.value = result.toFixed(2);								
	tradePrice = result.toFixed(2);
	
}

const profitMarginCustomer = document.getElementById
							("profitMarginCustomer");

const profitMarginTrader   = document.getElementById
							("profitMarginTrader");
							
const purchasePrice        = document.getElementById
							 ("purchasePrice");
const discountPercentageCustomer        = document.getElementById
							          ("discountPercentageCustomer");
														
const discountPercentageTrader = document.getElementById
							("discountPercentageTrader");
const sellingPriceCustomer = document.getElementById
							("sellingPriceCustomer");
							
const sellingPriceTrader = document.getElementById
							("sellingPriceTrader");
																
																				
profitMarginTrader.addEventListener("input",updateSellingPriceTrader);
profitMarginCustomer.addEventListener("input",updateSellingPriceCustomer);
purchasePrice.addEventListener("input",updateSellingPriceTrader);
purchasePrice.addEventListener("input",updateSellingPriceCustomer);
discountPercentageCustomer.addEventListener("input",updateSellingPriceCustomerAfterDiscount);
discountPercentageTrader.addEventListener("input",updateSellingPriceTraderAfterDiscount);



/* i want in this section when user enter first 
	the price that want be his profit 
	i will calc the ratio of profit 
 */



function calcMarginProfit(sellingPrice , purchasePrice){
	
	let profit =  sellingPrice - purchasePrice;
	let ratio = purchasePrice/profit;
	let profitMargin = 100/ratio;
	return profitMargin.toFixed(1); 
}							

function updateProfitMarginCustomer(){
						
	let sellingPriceCustomerValue = sellingPriceCustomer.value;
	customerPrice = sellingPriceCustomerValue;
	let purchasePriceValue= purchasePrice.value;
	let result = calcMarginProfit(sellingPriceCustomerValue,purchasePriceValue);
	profitMarginCustomer.value = result;
	discountPercentageCustomer.value="";
	
}
function updateProfitMarginTrader(){
						
	let sellingPriceTraderValue = sellingPriceTrader.value;
	tradePrice = sellingPriceTraderValue;
	let purchasePriceValue= purchasePrice.value;
	let result = calcMarginProfit(sellingPriceTraderValue,purchasePriceValue);
	profitMarginTrader.value = result;
	discountPercentageTrader.value="";

}

sellingPriceCustomer.addEventListener("input",updateProfitMarginCustomer);
sellingPriceTrader.addEventListener("input",updateProfitMarginTrader);




// change subCategory when category changed
document.addEventListener("DOMContentLoaded",function(){
const selectCategory       = document.querySelectorAll("select")[0];
const selectSubCategory    = document.querySelectorAll("select")[1];
selectCategory.addEventListener("change",event=>{
	removeAllChildNodes(selectSubCategory);
	const selectOption_CategoryID = event.target.value;
	getSubCategoryInSuchCategory(selectOption_CategoryID).
	then(value=>{
		value.forEach(item=>{
		let option = document.createElement("option");
		let textOptionNode = document.createTextNode(item);
		option.append(textOptionNode);
		selectSubCategory.appendChild(option);
		})
	});
	
});
});

async function getSubCategoryInSuchCategory (categoryId){
	
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'GET',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/getSubCategoriesByCategoryID/"+categoryId;
		console.log(link);
		const response = await fetch(link,requestOption);
		
		if(!response.ok){
			throw new Error("Network response was not ok");
		}
		const data = await response.json();
		console.log(data,"the data");
		return data;
	}
	catch(err){
		console.error("there was an error when fetching data (exception)" , err);
	}
}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}
