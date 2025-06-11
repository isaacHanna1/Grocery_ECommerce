	let container = document.querySelector('.content');
	let totalOfInvoice = document.querySelector("#totalOfReceipt");
	createInvoice();
	numberOfItemsInCart();
	
	let sendOrder = document.querySelector("#send");
	/*
	sendOrder.addEventListener('click',function(){
		sendData();
	});
	 */
	
	// this checkbox change address if user clicked i will show the adress and make abled to change
	/*
	let changeAddress = document.querySelector(".changeAddress");
	
	changeAddress.addEventListener('click',function(){
		openAddressDiv();
	});
	 */
	
	async function createInvoice(){
	    var parsedList = await getItemsInCart();
		console.log("the list ", parsedList);
	    let container = document.querySelector('.content');
	    let table = document.createElement('table');
	    table.classList.add('invoice-table');
	
	    let thead = document.createElement('thead');
	    let headerRow = document.createElement('tr');
	    let headers = ['الصنف', 'السعر', 'الكمية', 'الاجمالي', 'مسح'];
	    headers.forEach(headerText => {
	        let th = document.createElement('th');
	        th.textContent = headerText;
	        headerRow.appendChild(th);
	    });
	    thead.appendChild(headerRow);
	    table.appendChild(thead);
	
	    let tbody = document.createElement('tbody');
	
	    parsedList.forEach(item => {
	        let row = document.createElement('tr');
	        row.setAttribute("id", item.itemID);
	        row.classList.add("invoice");
	
	        // Item cell
	        let itemCell = document.createElement('td');
	        let img = document.createElement('img');
	        img.setAttribute("src", item.imgSrc);
	        img.classList.add("item-img");
	        let itemName = document.createElement('span');
	        itemName.textContent = item.name;
	        itemName.classList.add("item-name");
	        itemCell.appendChild(img);
	        itemCell.appendChild(itemName);
	
	        // Price cell
	        let priceCell = document.createElement('td');
	        priceCell.textContent = item.price;
	        priceCell.classList.add("item-price");
	
	        // Quantity cell
	        let quantityCell = document.createElement('td');
	        let inputQuantity = document.createElement("input");
	        inputQuantity.setAttribute("type", "number");
	        inputQuantity.setAttribute("value", item.quantity);
	        inputQuantity.setAttribute("min", 1);
	        inputQuantity.setAttribute("max", 999);
	        inputQuantity.classList.add('quantity-input');
	        quantityCell.appendChild(inputQuantity);
	
	        // Total cell
	        let totalCell = document.createElement('td');
	        let theTotal = total(item.price, item.quantity);
	        totalCell.textContent = theTotal;
	        totalCell.classList.add("total-item");
	
	        // Actions cell
	        let actionCell = document.createElement('td');
	        let divIcon = document.createElement("div");
	        divIcon.addEventListener("click", () => {
	            confirmationMessage(item.itemID, row);
	        });
	        divIcon.classList.add("icon");
	        let i = document.createElement("i");
	        i.classList.add("fa-regular", "fa-trash-can");
	        divIcon.appendChild(i);
	        divIcon.appendChild(document.createTextNode(" إزالة "));
	        actionCell.appendChild(divIcon);
	
	        row.appendChild(itemCell);
	        row.appendChild(priceCell);
	        row.appendChild(quantityCell);
	        row.appendChild(totalCell);
	        row.appendChild(actionCell);
	        tbody.appendChild(row);
	
	        inputQuantity.addEventListener('input', () => {
	            let valueInput = inputQuantity.value;
	            if (valueInput < 1) {
	                valueInput = 1;
	            }
	            if (valueInput > 999) {
	                valueInput = 999;
	            }
	            inputQuantity.value = valueInput;
	            let theQuantity = inputQuantity.value;
	            let theTotal = total(item.price, theQuantity);
	            totalCell.innerHTML = theTotal;
	            calcAllItems();
	            updateItemInLocalStorage(item.id, theQuantity);
	        });
	    });
	
	    table.appendChild(tbody);
	    container.appendChild(table);
	    calcAllItems();
	}
	
	
	function total (quantity , price){
		return quantity * price ;
	}
	
// start number of item in cart
async function numberOfItemsInCart() {
const cartIcon = document.querySelector(".beforeCart");

try{	
	const apiUrl = '/cart/length'
	const requestOption = {
      method: "GET",
    };
    const response = await fetch(apiUrl, requestOption);
   // Check if the response is successful
    if (!response.ok) {
      throw new Error("Failed In get cart size");
    }
	if(response.ok){
			const result         = await response.json()
			cartIcon.textContent = result.size;
			console.log("response :" , result);
	}
	}catch(error){
	console.log("error :" , error);
	}
}
	
	function calcAllItems(){
		let allItems = document.querySelectorAll(".total-item");
		let total = 0;
		allItems.forEach(item=>{
			total = total + Number(item.textContent);
		});
		totalOfInvoice.innerHTML = total.toFixed(2);
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


 async function removeItemFromCart(itemId){
	try {	
    const host = window.location.origin;
    const link = `${host}/cart/del/${itemId}`;

    const options = {
        method: "DELETE" 
    };

    let response = await fetch(link, options);

    if (!response.ok) {
        throw new Error(`Server error: ${response.status} - ${response.statusText}`);
    }

    console.log("Item deleted successfully");
} catch (error) {
    console.error("Error fetching data:", error);
}
	
		
}
	
	
	
	// i want when user press on delete getting the child number in list 
	// we the content class is the parent element and invoice is child  
	 
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
	    let okBtn        = document.createElement("button");
	    let cancelBtn    = document.createElement("button");
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
	    dialogContainer.style.display = "none";	    
	   	handleDeleteItem(itemId,removeElement)
		
	}
	    
	    
	}
	
	
	function getItemsUserChosen(addationalDetils) {
	    // Get the user ID from the HTML element
	    let userIdElement = document.getElementById("userId");
	    if (!userIdElement) {
	        console.error("User ID element not found.");
	        return null;
	    }
	    let userID = userIdElement.textContent.trim();
	    if (!userID) {
	        console.error("User ID is empty.");
	        return null;
	    }
	
	    // Initialize an empty array to store the order items
	    let orderItems = [];
	
	    // Loop through each invoice element
	    let invoices = document.querySelectorAll(".invoice-table .invoice");
	    invoices.forEach(invoice => {
	        let itemId = invoice.getAttribute('id');
	        let priceElement = invoice.querySelector(".item-price");
	        let quantityElement = invoice.querySelector(".quantity-input");
	
	        if (!itemId || !priceElement || !quantityElement) {
	            console.error("Invoice element is missing required fields.");
	            return;
	        }
	
	        let price = priceElement.textContent.trim();
	        let quantity = quantityElement.value.trim();
	
	        let parsedPrice = parseFloat(price);
	        let parsedQuantity = parseFloat(quantity);
	
	        if (isNaN(parsedPrice) || isNaN(parsedQuantity)) {
	            console.error("Invalid price or quantity:", price, quantity);
	            return;
	        }
	
	        // Push an object representing each order item to the orderItems array
	        orderItems.push({
	            item: { id: parseInt(itemId) },
	            quantity: parsedQuantity,
	            price: parsedPrice
	        });
	    });
	

	    let comment = addationalDetils;
		console.log(comment);
	    // Create an object containing both order and orderItems properties
	    let orderRequest = {
	        order: {
	            user: { id: parseInt(userID) },
	            details: comment
	        },
	        orderItems: orderItems
	    };
	
	    console.log("user id", userID);
	    // Convert the object to a JSON string
		console.log("orderRequest :-> ",orderRequest)
	    return orderRequest;
	}
	
	function sendData(addationalDetils){
	
	
			// if user check to change the address i will send as api 
			// to change the address of arriving 
			/*
		if(changeAddress.checked){
			updateAddress();
		}
		*/
		
	const data = getItemsUserChosen(addationalDetils);
		    const host = window.location.origin;
			const link = host+"/newOrder";
	fetch(link, {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify(data)
	})
	  .then(response => {
	        if (!response.ok) {
	            throw new Error('Failed to send data to the server');
	        }
	        return response.json();
	    })
	    .then(responseData => {
	        console.log('Response from server:', responseData);
	    })
	    .catch(error => {
		console.log(JSON.stringify(data));
	 		if (error instanceof TypeError) {
	            // Handle network or CORS errors
	            console.error('Network error:', error.message);
	        } else if (error instanceof SyntaxError) {
	            // Handle JSON parsing errors
	            console.error('JSON parsing error:', error.message);
	        } else {
	            // Handle other types of errors
	            console.error('Unexpected error:', error.message);
	        }
	    });
	
	clearContentAfteracceptOrder();
	}
	
	function updateAddress() {
	    let userID = document.getElementById("userId").textContent.trim();
		console.log(userID);
	    let goverment = document.getElementById("goverment").value.trim();
		console.log(goverment);
	    let city = document.getElementById("city").value.trim();
		console.log(city);
	    let userAddress = document.getElementById("address").value.trim();
		console.log(userAddress);
	    const host = window.location.origin;
	    const link = host + "/updateAddress";
	
	    fetch(link, {
	        method: 'PUT',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify({
	            id: userID,
	            government: goverment,
	            city: city,
	            userAddress: userAddress
	        })
	    })
	    .then(response => {
	        if (!response.ok) {
	            throw new Error('Failed to send data to the server');
	        }
	        return response.json();
	    })
	    .then(responseData => {
	        console.log('Response from server:', responseData);
	    })
	    .catch(error => {
	        console.error('Error:', error);
	    });
	}
	
	
	function openAddressDiv(){
		let address = document.querySelector(".address");
		if(changeAddress.checked){
			let inputs = address.querySelectorAll("input");
			inputs.forEach(input=>{
				input.removeAttribute("disabled");
			});
		}else{
	
		let inputs = address.querySelectorAll("input");
			inputs.forEach(input=>{
				input.setAttribute("disabled","disabled");
			});	
		}
	}
	
	function clearContentAfteracceptOrder(){
		
		let container = document.querySelector(".content");
		let allDiv = container.querySelectorAll("div");
		allDiv.forEach(div=>{
			div.remove();
		});
		let orderInfo = document.querySelector(".invoice-table");
		let finishReceipt = document.querySelector(".finishReceipt");
		
		orderInfo.remove();
		finishReceipt.remove();
		//localStorage.clear();
		removeCartFromSession();
		
		let beforeCart = document.querySelector(".beforeCart");
		beforeCart.textContent = 0 ;
		
		//let receiptLable = document.querySelector(".content h2");

    let message = `تم ارسال الطلب سنتواصل معك في خلال 24 ساعة لتأكيد الطلب <br/>
                   <span class="logoMessage">شركة وتد</span>`;		createPopup(message);
	}
	
	
	
	// Add an event listener to the "تأكيد الطلب" button
	let send_Order = document.querySelector("#send");
	send_Order.addEventListener('click', function() {
	    showPopup();
	});
	
	
	// show pop up for confirmation the data that user enter 
async function showPopup() {
    // Get the overlay element
    let overlay = document.createElement("div");
    overlay.classList.add("overlay");
    document.body.appendChild(overlay);

    // Create the popup container
    let popupContainer = document.createElement("div");
    popupContainer.classList.add("popup");

    // Create header for the popup
    let header = document.createElement("h3");
    header.textContent = "تأكيد البيانات التواصل";

    // Create the select menu for المحافظات
    let provinceSelect = document.createElement("select");
    provinceSelect.classList.add('province-select');
    await readJsonFile("../resources/js/governorates.json").then(data =>{
        data.forEach(government =>{
            let option = document.createElement("option");
            option.setAttribute("id",government.id);
            option.setAttribute("value",government.governorate_name_ar);
            let optionTxt = document.createTextNode(government.governorate_name_ar);
            option.appendChild(optionTxt);
            provinceSelect.appendChild(option);      
        });
    });

    // Create the select menu for المراكز
    let citySelect = document.createElement("select");
    citySelect.classList.add('city-select');
    provinceSelect.addEventListener('change',event =>{
        const selectedIndex = event.target.selectedIndex;
        let govermentId = event.target.options[selectedIndex].id;
        setCities(govermentId,citySelect);
    });
    
    // Create the textarea for العنوان
    let addressTextarea = document.createElement("textarea");
    addressTextarea.classList.add('address-textarea');
    addressTextarea.placeholder = "العنوان";

    // Create the input field for رقم الهاتف
    let phoneInput = document.createElement("input");
    phoneInput.setAttribute("type", "tel");
    phoneInput.placeholder = "رقم الهاتف";
    phoneInput.classList.add('phone-input');

    // Create the button for تأكيد
    let confirmButton = document.createElement("button");
    confirmButton.textContent = "تأكيد";
    confirmButton.addEventListener('click', function() {
        console.log('here 2');
		let addationalDetails = provinceSelect.value+" - "+citySelect.value+" - "+
								addressTextarea.value+" - "+phoneInput.value;
		console.log(addationalDetails);
		sendData(addationalDetails);
        closePopup();
    });

    // Create the button for closing the popup
    let closeButton = document.createElement("button");
    closeButton.classList.add('close');
    closeButton.textContent = "إغلاق";
    closeButton.addEventListener('click', function() {
        closePopup();
    });
    // Append elements to the popup container
    popupContainer.appendChild(header);
    popupContainer.appendChild(provinceSelect);
    popupContainer.appendChild(citySelect);
    popupContainer.appendChild(addressTextarea);
    popupContainer.appendChild(phoneInput);
    popupContainer.appendChild(confirmButton);
    popupContainer.appendChild(closeButton);

    // Append the popup container to the overlay
    overlay.appendChild(popupContainer);

    // Call setDataToPopup after the data is loaded
    setDataToPopup();
}

function closePopup() {
	    let overlay = document.querySelector(".overlay");
	    overlay.remove();
}
	
	async  function readJsonFile(jsonFilePath){
		try {
	    const response = await fetch(jsonFilePath);
	
	    if (!response.ok) {
	      throw new Error('Network response was not ok');
	    }
	
	    const data = await response.json();
	    return data;
	  } catch (error) {
	    console.error('Error:', error);
	    throw error; // Re-throw the error to handle it outside of the function if needed
	  }
	}
	function setCities(govermentId,cities){
	readJsonFile("../resources/js/cities.json").then(data=>{
		const founded = findCitiesInGovernment(data,govermentId);
		removeOptionsFromSelecTag(cities);
		founded.forEach(city=>{
			let option = document.createElement("option");
			option.setAttribute("id",city.id);
			option.setAttribute("value",city.city_name_ar);
			let optionTxt = document.createTextNode(city.city_name_ar);
			option.appendChild(optionTxt);
			cities.appendChild(option);
		});
	});
	}
	
	function findCitiesInGovernment(data,governmentID){
		const cities = new Set();
		data.forEach(city=>{
			if(city.governorate_id == governmentID){
				cities.add(city);
			}
		});
	
	return cities;
	}
	
	function removeOptionsFromSelecTag(select){
	    while (select.options.length > 0) {
	        select.remove(0);
	    }
	}
async function setDataToPopup() {
    // Get hidden input values
    let goverment = document.getElementById("goverment").value;
    let city = document.getElementById("city").value;
    let address = document.getElementById("address").value;
    let phone = document.getElementById("phone").value;

    console.log('the goverment ', goverment);
    console.log('the city ', city);
    // Set values to popup inputs
    let provinceSelect = document.querySelector(".province-select");
    let citySelect = document.querySelector(".city-select");
    let addressTextarea = document.querySelector(".address-textarea");
    let phoneInput = document.querySelector(".phone-input");

    if (provinceSelect && citySelect) {
        // Set the selected government directly
        provinceSelect.value = goverment;

        provinceSelect.dispatchEvent(new Event('change'));

        // Wait for the cities to be populated
        await new Promise(resolve => {
            let checkCitiesLoaded = setInterval(() => {
                if (citySelect.options.length > 0) {
                    clearInterval(checkCitiesLoaded);
                    resolve();
                }
            }, 100);
        });

        // Set the selected city
        citySelect.value = city;
    } else {
        console.error("Select elements not found");
    }

    if (addressTextarea) {
        addressTextarea.value = address;
    } else {
        console.error("Address textarea element not found");
    }

    if (phoneInput) {
        phoneInput.value = phone;
    } else {
        console.error("Phone input element not found");
    }
}


async function getItemsInCart(){
	
 try{	
	const apiUrl   = "/cart/items";
	const requestOption  = {
		method  : "GET",
		headers : {"Accept": "application/json" },

	};
	const response = await fetch(apiUrl, requestOption);
	 if (!response.ok) {
      throw new Error("Failed In get cart size");
    }
	 if(response.ok){
			const result         =  response.json()
			console.log("response :" , result);
			return result;
	 }
   }catch(error){
			console.log("error :" , error);
	}
}

async function removeCartFromSession (){
	const apiUrl  = "/cart/del";
	const header  = {
		method : "DELETE"
	}
	const response = await fetch(apiUrl,header);
	if(!response.ok){
		console.log('error', 'server issue when Remove item from Cart!');
	}
	if(response.ok){
		console.log("session removed!");
	}
}
// removing item from cart UI 
function removeItemFromUI(removeElement){
	  let parentRow = removeElement.closest('tr');
	    parentRow.remove();
}
async function handleDeleteItem(itemID, removeElement){
	
	try{
		await removeItemFromCart(itemID);
		await numberOfItemsInCart();
		calcAllItems();
		removeItemFromUI(removeElement);
		
		
	}catch(error){
		console.log("Error when removing item ",error)	
	}
}