let allCategory = document.querySelectorAll(".section-box .mainMenu li");
allCategory.forEach(li=>{
	li.addEventListener("click",(event)=>{
		let categoryName = event.target.textContent;
		let hasOL =li.querySelector("ol");
		if(hasOL !== null){
			hasOL.classList.toggle("active");
			return;
		} 
		let categoryId = li.id;
		getSubCategories(categoryId).then(data=>{
	    let subMenu =document.createElement("ol");
			subMenu.classList.add("submenu");
			subMenu.classList.add("active");
		data.forEach(sub=>{
			let subcateforyID = sub.id ;
			let subMenuLi   = document.createElement("li");
			let anchorLink  = document.createElement("a");
			let subMenuText = document.createTextNode(sub.subCategoryName);
			anchorLink.appendChild(subMenuText);
			anchorLink.setAttribute("id",sub.id);
			subMenuLi .appendChild(anchorLink)
			subMenu   .appendChild(subMenuLi);
			anchorLink.addEventListener('click',()=>{
			setPathOfItem(categoryName,categoryId,sub.subCategoryName,subcateforyID);
			fetchItems(1,categoryId, subcateforyID);
			closeMenu();
			generatePagination(15)
			
		});
		li.appendChild(subMenu);
		});
	}).catch(error =>{
			    console.error("Error: " + error.message);
		});
});
});

function fetchItems(pageNumber , categoryID , subcategoryId ){
		    let items = getItemsinSubCategory(pageNumber,categoryID,subcategoryId);
			items.then(data=>{
			let section_items = document.querySelector(".section_items");
			removeChildern(section_items);
			data.forEach(item=>{ 
				let createCardDiv = document.createElement('div');
				createCardDiv.setAttribute("id",item.id);
				createCardDiv.classList.add('card');
			    section_items.appendChild(createCardDiv);
				let createCard_top = document.createElement('div');
				createCard_top.classList.add('card-top');
				createCardDiv.appendChild(createCard_top);
				let createSpanDiscount = document.createElement('span');
				if(item.discountPercentageCustomer  !=  '0'){
					createSpanDiscount.classList.add('discount');
					let discountTextValue = document.createTextNode(item.discountPercentageCustomer+"%")
					createSpanDiscount.appendChild(discountTextValue);
					createCard_top.appendChild(createSpanDiscount);								
				}								
				let divImg = document.createElement('div');
				divImg.classList.add('img');
				divImg.setAttribute("title",item.itemDescription);
				createCard_top.appendChild(divImg);
				let img = document.createElement('img');
				let base64ImageData = item.image ; 
				let imageType = detectMimeType(base64ImageData);
				let srcOfImage = "data:image/"+imageType+";base64,"+base64ImageData; 
				img.setAttribute('src',srcOfImage);
				divImg.appendChild(img);
				let divContainerBtn_a =document.createElement('div');
				divContainerBtn_a.classList.add('dis-flex');
				let buttonPlus = document.createElement('button');
				buttonPlus.classList.add('description');
				buttonPlus.setAttribute('id','addToCart');
				let descriptionLink = document.createElement('a');
				descriptionLink.setAttribute("title",item.itemDescription);
				descriptionLink.classList.add('descriptionLink');
				descriptionLink.classList.add('description');
				let textNode = document.createTextNode("المميزات");
				descriptionLink.appendChild(textNode);
				divContainerBtn_a.appendChild(buttonPlus);
				divContainerBtn_a.appendChild(descriptionLink);
				let icon = document.createElement('i');
				icon.setAttribute('class','fa-solid fa-plus');
				icon.classList.add('description');
				icon.setAttribute("title",item.itemDescription);
				buttonPlus.setAttribute("title",item.itemDescription);
				buttonPlus.appendChild(icon);

				createCard_top.appendChild(divContainerBtn_a);
				
				
				
				let card_bottomDiv = document.createElement('div');
				card_bottomDiv.classList.add('card-bottom');
				createCardDiv.appendChild(card_bottomDiv);
				
				let priceDiv = document.createElement('div');
				priceDiv.classList.add('price');
				
				card_bottomDiv.appendChild(priceDiv);
				
				let price = item.sellingPriceCustomer;
				let discountValue = item.discountPercentageCustomer;
				let originalPrice = price/(1-(discountValue/100));
				
										 	
				let divAfterDiscountDiv = document.createElement('div');
				divAfterDiscountDiv.setAttribute('id','after-discount');
				let afterDiscountValue = document.createTextNode(price);
				divAfterDiscountDiv.appendChild(afterDiscountValue);
				priceDiv.appendChild(divAfterDiscountDiv);
								
				let item_nameDiv = document.createElement('div');
				let item_nameDivValue = document.createTextNode(item.itemName);
				item_nameDiv.appendChild(item_nameDivValue);
				item_nameDiv.classList.add('item-name');
				card_bottomDiv.appendChild(item_nameDiv);
				
				
				let item_unitDiv = document.createElement('div');
				let unitNameValue = document.createTextNode(item.unitName);
				item_unitDiv.appendChild(unitNameValue);
				item_unitDiv.classList.add('unit');
				card_bottomDiv.appendChild(item_unitDiv);

				
				if(discountValue != 0 ){
				  let divBeforeDiscountDiv = document.createElement('div');
				  divBeforeDiscountDiv.setAttribute('id','befor-discount');
				  let beforeDiscountValue = document.createTextNode("LE "+originalPrice);
				  divBeforeDiscountDiv.appendChild(beforeDiscountValue);
				  priceDiv.appendChild(divBeforeDiscountDiv);	
				}		
				  let avability = item.avability;
				  if(avability == false){
				  createCardDiv.classList.add('sold-out');
				  buttonPlus.style.visibility  = "hidden";	
			}
				});
			});
}
async function getSubCategories(categoryId){
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'GET',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/subCategory/"+categoryId;
		const response = await fetch(link,requestOption);
		if(!response.ok){
			throw new Error("Network response was not ok");
		}
		const data = await response.json();
		return data;
	}
	catch(err){
		console.error("there was an error when fetching data (exception)" , err);
	}
}




async function getItemsinSubCategory(pageNumber ,categoryId,subCategoryId){
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'GET',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/items/"+pageNumber+"/"+categoryId+"/"+subCategoryId;
		const response = await fetch(link,requestOption);
		if(!response.ok){
			throw new Error("Network response was not ok");
		}
		const data = await response.json();
		return data;
	}
	catch(err){
		console.error("there was an error when fetching data (exception)" , err);
	}
}


var signatures = {
  JVBERi0: "application/pdf",
  R0lGODdh: "image/gif",
  R0lGODlh: "image/gif",
  iVBORw0KGgo: "image/png",
  "/9j/": "image/jpg"
};

function detectMimeType(b64) {
  for (var s in signatures) {
    if (b64.indexOf(s) === 0) {
      return signatures[s];
    }
  }
}

function removeChildern(parent){
	 while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function removeParent (child){
	   parentElement.parentNode.removeChild(child);

}

function setPathOfItem(category,categoryId,subCategory,subCateoryId){
	let path = document.querySelector(".container .path");
	removeChildern(path);
	let p_category = document.createElement('p');
	p_category.classList.add("category");
	let p_categoryTextNode= document.createTextNode(category+" > ");
	p_category.setAttribute("id",categoryId)
	p_category.appendChild(p_categoryTextNode);
	
	let p_subcategory = document.createElement('p');
	let p_subcategoryTextNode= document.createTextNode(" "+subCategory+" > ");
	p_subcategory.setAttribute("id",subCateoryId)
	p_subcategory.classList.add("subCategory")
	p_subcategory.appendChild(p_subcategoryTextNode);

	path.appendChild(p_category);
	path.appendChild(p_subcategory);	
}

function closeMenu(){
  	sectionBox.classList.toggle("open");
}


function generatePagination(numberItemPerPage){

let category = document.querySelector(".category");
let subcategory = document.querySelector('.subCategory');

let categoryID = category.getAttribute('id');
let subcategoryID = subcategory.getAttribute('id');
 getCountOfItemsINSubCategory(categoryID,subcategoryID)
.then(data=>{
	let countOfItem = data;
	let pages = document.querySelector(".page div");
	removeChildern(pages);
	for(i = 1; i <  (countOfItem/numberItemPerPage+1) ; i++){
		let span = document.createElement('span');
		let a    = document.createElement('a');
		let content = document.createTextNode(i);
		a.appendChild(content);
		span.appendChild(a);
		a.addEventListener('click',(event)=>{
			let page = event.target.textContent;
			fetchItems(page,categoryID,subcategoryID);
		});
		pages.appendChild(span);		
	};
});

}

async function getCountOfItemsINSubCategory(categoryId,subCategoryId){
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'GET',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/itemsInSuchCategoryAndSubCategory/"+categoryId+"/"+subCategoryId;
		const response = await fetch(link,requestOption);
		if(!response.ok){
			throw new Error("Network response was not ok");
		}
		const data = await response.json();
		return data;
	}
	catch(err){
		console.error("there was an error when fetching data (exception)" , err);
	}
}
