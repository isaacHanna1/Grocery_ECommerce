
let parent_tr  = "";
let itemId = "";
let tableContent = document.querySelector(".table-container table tbody");

tableContent.addEventListener("click",e=>{
	if(e.target.classList.contains("delete-btn")){
	 e.preventDefault();	
	 parent_tr  = e.target.closest('tr');
	 itemId = parent_tr.querySelector('td:first-child').textContent;	
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
	  deleteItem(itemId);
      overlay_div.classList.remove("overlay");
	
      dialogContainer.classList.remove("dialogContainer");
	  dialogContainer.style.display="none";
	  //reomve tr after deleted from database
	  parent_tr.remove();		
    }
}
});
 



async function deleteItem(itemId){
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'DELETE',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/delItem/"+itemId;
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



// show see more button in item name 

let itemNames = document.querySelectorAll(".textContainer");

itemNames.forEach(itemName =>{
	let size = itemName.textContent
	console.log(size);
	if( size.length >=10){
		// first next sibling get be the the text node but i want element 
		// so i used two siblings 
		const seeMoreButton = itemName.nextSibling.nextSibling;
		seeMoreButton.style.display = "block";
		seeMoreButton.addEventListener('click',()=>{
			if(itemName.style.whiteSpace === "normal"){
				itemName.style.whiteSpace = "nowrap"
			}else{
			itemName.style.whiteSpace = "normal";
			}
		});
	}
});



// this coming code for handling search in text box = search 

let allItems = [];  
fetchAllItems().then(items=>{
	allItems = items;
});

let itemInCurrentTable = document.querySelector(".allItems tbody");
let allTR              = itemInCurrentTable.querySelectorAll("tr");

let searchInput_admin        = document.querySelector("#searchInput_admin");
searchInput_admin.addEventListener('keyup',()=>{
	let searchValueInpt = searchInput_admin.value;
	if(searchValueInpt === ''){
		allTR.forEach(row=>{
				itemInCurrentTable.appendChild(row);
		});
	}else{
		itemInCurrentTable.innerHTML = '';
		let matchItem = filterItems(searchValueInpt);
		matchItem.forEach(item=>{
			let row = document.createElement('tr');
		    row.innerHTML = `
                    <td>${item.id}</td>
                    <td>${item.itemName}</td>
                    <td>${item.purchasePrice}</td>
                    <td>${item.sellingPriceCustomer}</td>
                    <td>${item.sellingPriceTrader}</td>
                    <td>
                        <a href="/showItem/${item.id}" class="btn edit-btn">تعديل</a>
                        <a href ="#" class="btn delete-btn">مسح</a>
                        <a href="/image/${item.id}" class="btn update-img">الصورة</a>
                    </td>
                `;
		     itemInCurrentTable.appendChild(row);
		});
	}
	
});

function filterItems(searchValue){
    searchValue = searchValue.trim().toLowerCase(); 
    let matchingItems = allItems.filter(item => {
        return item.itemName && item.itemName.toLowerCase().includes(searchValue);
    });
    return matchingItems;
}
async function fetchAllItems(){
	

	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'GET',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/allItems";
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
