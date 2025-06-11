/* Start on Load functions */

window.onload = function(){
	setPercentageToDiscount();
}

/* End on Load functions */



/// start show or disaapear menu icon
const sectionBox = document.querySelector(".section-box");
const menu_icon  = document.querySelector(".menu-icon");

menu_icon.onclick = function (event) {
  event.stopPropagation(); // Prevent event bubbling to document
  sectionBox.classList.toggle("open");
 const subMenu = document.querySelector(".section-box .sub-menu");
  if (subMenu && !subMenu.contains(event.target)) {
    subMenu.classList.remove("open");
  }
};

// Handle clicks outside the menu and icon
document.addEventListener("click", function (event) {
  if (!sectionBox.contains(event.target) && event.target !== menu_icon) {
    sectionBox.classList.remove("open");
  }
 // we query here case this sub menu added in runtime not in load page 
// we should query after event occur 
  const subMenu = document.querySelector(".section-box .sub-menu");
  if (subMenu && !subMenu.contains(event.target)) {
    subMenu.classList.remove("open");
  }
	
});
let mainAds = document.querySelector(".main-ad");

if(mainAds === null){
let header = document.querySelector(".header-area");
header.style.position = "fixed";
header.style.top = 0; 	
}

const input =  document.querySelector(".header-area .search-box form input");


input.addEventListener('keyup',debounce(callApiSearch,500));


function callApiSearch(){
	if(input.value.trim() !=""){
		console.log('hi');
		getSearch(input.value.trim());
	}else{
	
	   const searchBoxContainer = document.querySelector('.header-area .search-box');
       const resultList = searchBoxContainer.querySelector('.resultList');
  	   if (resultList) {
           resultList.remove();
       }
	}
}


function debounce(fn , wait){
	let timeOut ;
	return function(...args){
		 clearTimeout(timeOut);
		timeOut = setTimeout(()=>fn(...args),wait);
	}
	
}

function handleKeyup() {
  if (input.value.trim() !== '') {
    debounce(performSearch, 500)();
  }
}




async function getSearch(partOfItemName){
	const search = encodeURIComponent(partOfItemName);

	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'GET',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/search?partOfItemName="+search;
		console.log(link);
		const response = await fetch(link,requestOption);
		
		if(!response.ok){
			throw new Error("Network response was not ok");
		}
		const data = await response.json();
		console.log(data);
		
		showSearchList(data)
	}
	catch(err){
		console.error("there was an error when fetching data (exception)" , err);
	}
}

function showSearchList(data){
    const searchBoxContainer = document.querySelector('.header-area .search-box');

  const list = document.createElement('ul');
   list.classList.add("resultList");
   const resultList = searchBoxContainer.querySelector('.resultList');
  	if (resultList) {
        resultList.remove();
    }
  data.forEach(item => {
    const listItem = document.createElement('li');
	listItem.id =  item.id;
    listItem.textContent = item.itemName; 
    list.appendChild(listItem);
	addEventClicked(listItem);
  })

  searchBoxContainer.appendChild(list);
}

function addEventClicked(li) {
    li.addEventListener('click', function() {
    input.value = li.textContent;
	const parentElement = li.parentNode;
    parentElement.style.display = 'none';
    input.focus();
	navigateToItemPage(li.id);
    });
}
 function navigateToItemPage(itemId) {
    window.location.href = '/getItemPage/' + itemId;
}




function createPopup(message) {
    // Create popup elements
    let popup = document.createElement("div");
    popup.style.position = "fixed";
	popup.style.width = "250px";
    popup.style.top = "50%";
    popup.style.left = "50%";
    popup.style.transform = "translate(-50%, -50%)";
    popup.style.backgroundColor = "#fff";
    popup.style.padding = "20px";
    popup.style.boxShadow = "0 0 10px rgba(0,0,0,0.5)";
    popup.style.zIndex = "1000";
    
    let popupMessage = document.createElement("p");
    popupMessage.innerHTML = message;
    popupMessage.style.marginBottom = "20px";
    
    let closeButton = document.createElement("button");
    closeButton.textContent = "Close";
    closeButton.style.padding = "10px 20px";
    closeButton.style.backgroundColor = "#f00";
    closeButton.style.color = "#fff";
    closeButton.style.border = "none";
    closeButton.style.cursor = "pointer";
    
    closeButton.addEventListener("click", () => {
        document.body.removeChild(popup);
    });
    
    // Append elements to popup
    popup.appendChild(popupMessage);
    popup.appendChild(closeButton);
    
    // Append popup to body
    document.body.appendChild(popup);
}


/* Start fuction when loading anyPage contain discouts
   get this discounts and adding - NUM % to them
 */
function setPercentageToDiscount(){
	
	let discountElements = document.querySelectorAll(".discount");
	discountElements.forEach(dis =>{
		let convertedDis = parseFloat(dis.textContent);
		dis.textContent = "% "+convertedDis+" -";
	}); 
}

/* End fuction when loading anyPage contain discouts
   get this discounts and adding - NUM % to them
 */

/*
Start class side menu when user clicked on screen
 */

