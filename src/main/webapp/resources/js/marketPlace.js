class Item {
  constructor(itemId, name, quantity, price, imgSrc) {
    this.id = itemId;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.imgSrc = imgSrc;
  }
}
numberOfItemsInCart();


async function addNewItem(item) {
/*
  // this line of code getting the stored chart in local if not
  // they create new one || []
  // and add new item , before adding it check the item existence in list
  // if exist they update the quantity
  let cartList = JSON.parse(localStorage.getItem("cart")) || [];

  // if they  existing retrive its index else retrive -1  ;
  let existingItemIndex = cartList.findIndex(
    (cartItem) => cartItem.id === item.id
  );

  console.log("the existence :" + existingItemIndex);

  if (existingItemIndex !== -1) {
    // If the item is already in the cart, you may choose to update its quantity or take other action
    cartList[existingItemIndex].quantity =
      parseInt(cartList[existingItemIndex].quantity) + parseInt(item.quantity);
    console.log("Item already in cart. Quantity updated." + existingItemIndex);
    console.log("the quantity is:" + cartList[existingItemIndex].quantity);
    localStorage.setItem("cart", JSON.stringify(cartList));
  } else {
    userItems.push(item);
    let itemJson = JSON.stringify(userItems);
    localStorage.setItem("cart", itemJson);
    console.log(userItems.length);
  }
*/
	
	const apiUrl       = '/cart/add';
	const data         = {
				id           : item.id,
				itemName     : item.name,
				price        : item.price,
				imgSrc       : item.imgSrc,
				qunatity     : item.quantity
	};					
	try{
		const response   = await fetch(apiUrl ,{
			method :"POST",
			header : { 'Content-Type': 'application/x-www-form-urlencoded'},
			body   : new URLSearchParams(data)
		});
		if(response.ok){
			const result = await response.text();
			console.log("response :" , result);
		}else{
			console.error('Error:', response.status);
		}
	}catch (error) {
        console.error('Error:', error);
    }
	
	}
function updateNumOfItemInCart() {
const cartIcon = document.querySelector(".beforeCart");
  let itemNumber = cartIcon.textContent;
  itemNumber = Number(itemNumber) + 1;
  cartIcon.textContent = itemNumber;
}

let sectioItems = document.querySelector(".section_items");
sectioItems.addEventListener("click", (event) => {
  let link = event.target;
  // wen user click on image or description of product
  if (link.classList.contains("description") || link.tagName === "IMG") {
    let card = link.parentNode.parentNode.parentNode;
    if (card.classList.contains("card-top")) {
      card = card.parentNode;
    }
    let itemId = card.id;
    let card_bottom = card.querySelector(".card-bottom");
    console.log(card_bottom);
    let price = card_bottom.querySelector("#after-discount");
    let itemUnit = card_bottom.querySelector(".unit");
    let itemName = card_bottom.querySelector(".item-name");
    console.log(itemName);
    let imgDescription = link.title;
	if(imgDescription === ""){
		imgDescription = link.alt;
	}
    let imgElement = card.querySelector("img");
    let imgsrc = imgElement.getAttribute("src");

    overlay_div = document.createElement("div");
    overlay_div.classList.add("overlay");

    let product_container = document.createElement("div");
    product_container.classList.add("product-container");
    let h3 = document.createElement("h3");
    h3.setAttribute("id", itemId);
    let h3Text = document.createTextNode(itemName.textContent);
    h3.appendChild(h3Text);
    let spanClose = document.createElement("span");
    spanClose.addEventListener("click", () => {
      overlay_div.classList.remove("overlay");
      product_container.style.display = "none";
    });
    let spanCloseIcon = document.createElement("i");
    spanCloseIcon.classList.add("fa-solid");
    spanCloseIcon.classList.add("fa-circle-xmark");
    spanClose.appendChild(spanCloseIcon);

    product_container.appendChild(spanClose);
    let divInfo = document.createElement("div");
    divInfo.appendChild(h3);
    divInfo.classList.add("info");

    product_container.appendChild(divInfo);

    let divDescription = document.createElement("div");
    divDescription.classList.add("description");

    divInfo.appendChild(divDescription);

    let divP = document.createElement("p");
    let divPContent = document.createTextNode(imgDescription);
    divP.appendChild(divPContent);
    divDescription.appendChild(divP);

    divInfo.appendChild(divDescription);

    let divPrice = document.createElement("div");
    let priceText = price.textContent;
    let numericalPart = priceText.match(/\d+(\.\d+)?/); // Matches one or more digits optionally followed by a decimal and more digits
    let itemPrice = numericalPart ? Number(numericalPart[0]) : null; // Convert to number or null if no numerical part found

    // here i create span to contain the unit of item
    // that will appear with product-container overlay beside the price

    let unitSpan = document.createElement("span");
    let unitSpanText = document.createTextNode(itemUnit.textContent);
    unitSpan.appendChild(unitSpanText);
    unitSpan.classList.add("unit");
    let textPrice = document.createTextNode(price.textContent);
    divPrice.appendChild(textPrice);
    divPrice.appendChild(unitSpan);
    divPrice.classList.add("price");
    divDescription.appendChild(divPrice);
    let divQuantityContainer = document.createElement("div");
    let increaseButton = document.createElement("button");
    increaseButton.appendChild(document.createTextNode("+"));
    let quantityInput = document.createElement("input");
    quantityInput.setAttribute("type", "number");
    quantityInput.setAttribute("value", "1");
    let desreaseButton = document.createElement("button");
    desreaseButton.appendChild(document.createTextNode("-"));
    increaseButton.classList.add("change");
    quantityInput.classList.add("quantitiy");
    desreaseButton.classList.add("change");

    increaseButton.addEventListener("click", () => {
      let valueInput = quantityInput.value;
      valueInput = Number(valueInput) + 1;
      quantityInput.value = valueInput;
    });

    desreaseButton.addEventListener("click", () => {
      let valueInput = quantityInput.value;
      valueInput = Number(valueInput) - 1;
      if (valueInput === 0) {
        valueInput = 1;
      }
      quantityInput.value = valueInput;
    });

    let buttonAdd = document.createElement("button");
    let buttonBuyNow = document.createElement("button");
    let buttonContent = document.createTextNode("اضف الي العربة ");
	let buttonBuyNowContent = document.createTextNode("اشتري الأن ");
    buttonAdd.appendChild(buttonContent);
	buttonBuyNow.appendChild(buttonBuyNowContent);
	let iconBuy = document.createElement("i");
	buttonBuyNow.appendChild(iconBuy);
	iconBuy.classList.add('fa-solid', 'fa-money-bill-1');
    buttonAdd.classList.add("add");
	buttonBuyNow.id ="buyNow"
    let iconDescription = document.createElement("i");
    iconDescription.classList.add("fa-solid");
    iconDescription.classList.add("fa-cart-arrow-down");
    buttonAdd.appendChild(iconDescription);
    if (event.target.tagName !== "A") {
      divQuantityContainer.appendChild(increaseButton);
      divQuantityContainer.appendChild(quantityInput);
      divQuantityContainer.appendChild(desreaseButton);
      divQuantityContainer.appendChild(buttonAdd);
	  divQuantityContainer.appendChild(buttonBuyNow);
      divDescription.appendChild(divQuantityContainer);
    }
    buttonAdd.addEventListener("click", () => {
      let newItem = new Item(
        itemId,
        itemName.textContent,
        quantityInput.value,
        itemPrice,
        imgsrc
      );
      addNewItem(newItem);
      updateNumOfItemInCart();
      overlay_div.classList.remove("overlay");
      product_container.style.display = "none";
      notifyUser();
    });
 buttonBuyNow.addEventListener("click", () => {
      let newItem = new Item(
        itemId,
        itemName.textContent,
        quantityInput.value,
        itemPrice,
        imgsrc
      );
      buyNow(newItem.id,newItem.name,newItem.quantity,newItem.price,newItem.imgSrc);
    });
    let divImg = document.createElement("div");
    let divSubImg = document.createElement("div");
    divImg.classList.add("img");
    divSubImg.classList.add("subImg");
    let img = document.createElement("img");
    img.setAttribute("src", imgsrc);
    divImg.appendChild(img);
    divImg.appendChild(divSubImg);

    fetchSubImages(itemId)
      .then((subImages) => {
        // Handle the list of sub images here
        console.log(subImages);
        // Call a function to display the sub images, passing the subImages array as an argument
        displaySubImages(subImages, divSubImg, product_container);
      })
      .catch((error) => {
        // Handle errors if any
        console.error("Error fetching sub images:", error);
      });

    product_container.appendChild(divImg);
    overlay_div.appendChild(product_container);
    document.body.appendChild(overlay_div);
  }  else if (link.id === "buyNow") {
		let card = link.closest(".card"); // Find the parent card element
        let itemId = card.id;
        let card_bottom = card.querySelector(".card-bottom");
        let price = card_bottom.querySelector("#after-discount");
        let itemName = card_bottom.querySelector(".item-name");
        let imgElement = card.querySelector("img");
        let imgsrc = imgElement.getAttribute("src");

        let numericalPart = price.textContent.match(/\d+(\.\d+)?/);
        let itemPrice = numericalPart ? Number(numericalPart[0]) : null;
		buyNow(itemId,itemName.textContent,1,itemPrice,imgsrc);

    }
});
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

// end  number of item in cart

// when user click on the item , (about) and the product container will appear
//  and the iamge ans sun image will also appear
// so this api will retive the sub image os product

//Function to fetch sub images for an item
async function fetchSubImages(itemId) {
  try {
    const headers = {
      "Content-Type": "application/json",
    };
    const requestOption = {
      method: "GET",
      headers: headers,
    };
    const host = window.location.origin;
    const link = host + "/subImages/" + itemId;
    console.log(link);
    const response = await fetch(link, requestOption);
    // Check if the response is successful
    if (!response.ok) {
      throw new Error("Failed to fetch sub images");
    }

    // Parse the JSON response
    const subImages = await response.json();

    // Return the list of base64 encoded images
    return subImages;
  } catch (error) {
    console.error("Error fetching sub images:", error);
    return []; // Return an empty array if an error occurs
  }
}

function displaySubImages(subImages, subImgDiv, product_container) {
  // Select the container where you want to display the images
  const subImgContainer = subImgDiv;

  subImages.forEach((base64Image) => {
    console.log("the sub image length is :", subImages.length);
    const imgContainer = document.createElement("div");
    imgContainer.classList.add("imgContainer");
    const img = document.createElement("img");
    img.src = `data:image/jpeg;base64,${base64Image}`;
    imgContainer.appendChild(img);
    subImgContainer.appendChild(imgContainer);
    img.addEventListener("click", () => {
      swap(img, product_container);
    });
  });
}

// this code to swap between the image and subimages when user clicked on it
//img = subImgSrc
function swap(img, productContainer) {
  let mainImage = productContainer.querySelector(".img img");
  let temp = mainImage.src;
  let subImgSrc = img.src;
  mainImage.src = subImgSrc;
  subImgSrc.src = temp;
  showBoder(productContainer, img);
}
function showBoder(productContainer, current_img) {
  let mainImage = productContainer.querySelectorAll(
    ".img .subImg .imgContainer img"
  );
  console.log(mainImage.length);
  mainImage.forEach((img) => {
    img.classList.remove("showBoder");
  });
  current_img.classList.add("showBoder");
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

function buyNow(itemId, itemName, quantity, itemPrice, imgsrc) {
  let newItem = new Item(itemId, itemName, quantity, itemPrice, imgsrc);
  addNewItem(newItem);
  updateNumOfItemInCart();
  const host = window.location.origin;
  const link = host+"/invoice";
  window.location.href = link;
}
