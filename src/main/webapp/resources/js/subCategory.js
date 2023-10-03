let parent_tr  = "";
let subCategoryId = "";

let tableContentOfSubCategory = document.querySelector(".table-container table tbody")
let sendBtn    = document.getElementById("sendBtn");
let errMessage = document.getElementById("errMessage"); 
sendBtn.onclick = function (event){
event.preventDefault();
let  subCategoryName = document.getElementById("subCategoryName").value;

let  categorySelectElement = document.getElementById("categoriesOption")
let  selectedIndex         = categorySelectElement.selectedIndex;
let  selectOption          = categorySelectElement.options[selectedIndex];
let  selectOptionContent   = categorySelectElement.options[selectedIndex].textContent;
let  categoryID            = selectOption.value; 
               
//i use null the category name is not important i use foriegn key
addSubCategory(subCategoryName,categoryID,selectOptionContent);	
}

async function addSubCategory(subCategoryName,categoryId,categoryName){
	
	let subCategoryobj = {subCategoryName:subCategoryName.trim(),
							  category:{id:categoryId,
							  			categoryName:categoryName}};
	const subCategoryObject = JSON.stringify(subCategoryobj);	
	console.log("created ",subCategoryObject);
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'POST',
			headers:headers,
			body:subCategoryObject,
		};
		try{
		const host = window.location.origin;
		const link = host+"/addSubCategory";
		console.log(link);
		const response = await fetch(link,requestOption);
			console.log(response.status,"status code");
			console.log(response.statusText);
			if(response.status === 400){
				errMessage.textContent ="هناك قسم فرعي مسجل بنفس الاسم";
				return null;
			}
		if(!response.ok){
			throw new Error("NetWork response was not ok");
		}
		const data = await response.json();
		console.log(response.status);
		console.log(data);
		// adding new row of new subCategory in table 
		
		let tr = document.createElement("tr");		
		let td_id =document.createElement("td");
		let idTextNode = document.createTextNode(data.id);
		td_id.appendChild(idTextNode);
		
		let td_subCategoryName =document.createElement("td");
		let textNodeOfSubCategoryName = document.createTextNode(subCategoryName);
		td_subCategoryName.appendChild(textNodeOfSubCategoryName);
		
		let td_CategoryName =document.createElement("td");
		let textNodeOfCategoryName = document.createTextNode(categoryName);
		td_CategoryName.appendChild(textNodeOfCategoryName);
		
		let td_forButtons = document.createElement("td");
		let buttonEdit = document.createElement("a");
		let buttonEditTextNode = document.createTextNode("تعديل");
		buttonEdit.appendChild(buttonEditTextNode);
		buttonEdit.classList.add("btn");
		buttonEdit.classList.add("edit-btn");
		let buttonDelete = document.createElement("a");
		let buttonDeleteTextNode = document.createTextNode("مسح");
		buttonDelete.appendChild(buttonDeleteTextNode);
		buttonDelete.classList.add("btn");
		buttonDelete.classList.add("delete-btn");
		deleteBtns = document.querySelectorAll(".delete-btn");
		td_forButtons.appendChild(buttonEdit);
		td_forButtons.appendChild(buttonDelete);
		
		tr.appendChild(td_id);
		tr.appendChild(td_subCategoryName)
		tr.appendChild(td_CategoryName)
		tr.appendChild(td_forButtons);
		
		tableContentOfSubCategory.appendChild(tr);
		
		return {data , status: response.status};
		}catch(error){
			throw error;
		}
		}



// deleted an row in subcategory
tableContentOfSubCategory.addEventListener("click",e=>{
	if(e.target.classList.contains("delete-btn")){
	 e.preventDefault();	
	 parent_tr  = e.target.closest('tr');
	 subCategoryId = parent_tr.querySelector('td:first-child').textContent;	
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
	  console.log(subCategoryId);
	  deleteCategory(subCategoryId);
      overlay_div.classList.remove("overlay");
	
      dialogContainer.classList.remove("dialogContainer");
	  dialogContainer.style.display="none";
	  //reomve tr after deleted from database
	  parent_tr.remove();		
    };
  }
	else if(e.target.classList.contains("edit-btn")){
	 e.preventDefault();
	 parent_tr  = e.target.closest('tr');
	 subCategoryId = parent_tr.querySelector('td:first-child').textContent;
	 subCategoryNameTable = parent_tr.querySelector('#subCategoryName').textContent;	
     let overlay_div = document.createElement("div");
     overlay_div.classList.add("overlay");
     document.body.appendChild(overlay_div);
     let dialogContainer = document.createElement("div");
     dialogContainer.classList.add("dialogContainer");
     document.body.appendChild(dialogContainer);

	 dialogContainer.style.borderTopColor="var(--main-color)";
     let textContainer = document.createElement("div");
     textContainer.classList.add("textContainer");
	 let labelForInput = document.createElement("label");
	 let textForLable  =document.createTextNode("اسم القسم الفرعي");
	 labelForInput.appendChild(textForLable);
     let editInput = document.createElement("input");
	 editInput.value = subCategoryNameTable;
	 textContainer.appendChild(labelForInput);
     textContainer.appendChild(editInput);
     dialogContainer.appendChild(textContainer);
	
	 let labelForSelect = document.createElement("label");
	 let textForSelect  =document.createTextNode("اسم القسم ");
	 labelForSelect.appendChild(textForSelect);
	 textContainer.appendChild(labelForSelect);
	 let selectElement  = document.createElement("select");
	 selectElement.setAttribute('name',"subCategoryId");
		getAllCategory().then(data=>{
			data.forEach(obj=>{
				console.log("obj.categoryName",obj.categoryName);
				let option   = document.createElement("option");
				option.setAttribute("value",obj.id);
				let textNode = document.createTextNode(obj.categoryName);
				option.appendChild(textNode);
				selectElement.appendChild(option);			
			});
		});
		textContainer.appendChild(selectElement);
	
	
     let btnContainer = document.createElement("div");
     let okBtn = document.createElement("button");
	 okBtn.classList.add("edit-btn")
     let cancelBtn = document.createElement("button");
     okBtn.appendChild(document.createTextNode("تعديل"));
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
      okBtn.onclick =  function () {
		  let subCategoryName = editInput.value
	      overlay_div.classList.remove("overlay");
	      dialogContainer.classList.remove("dialogContainer");
		  dialogContainer.style.display="none";
			let selectedOption = selectElement.options[selectElement.selectedIndex];
			let selectedValue  = selectedOption.value;  
			let SelectedTextValue = selectedOption.textContent;
   		    const fetching = editSubCategory(subCategoryId,subCategoryName,selectedValue,SelectedTextValue);
			fetching.then(value=>{
				if(value === null){
					alert("Sub Category Is already Exists");
					return;
				}
				else{
					let subCategoryNameTable = parent_tr.querySelector('#subCategoryName');
					subCategoryNameTable.textContent = subCategoryName;
					let CategoryNameTable = parent_tr.querySelector('#categoryName');
					CategoryNameTable.textContent = SelectedTextValue ;
				}
			});
				
	    };
		
	}

	
});

async function deleteCategory(subCategoryId){
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'DELETE',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/deleteSubCategory/"+subCategoryId;
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

// i fetching new categories from database ;
async function getAllCategory (){
	
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'GET',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/allMainCategoy";
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

async function editSubCategory(subCategoryId ,subCategoryName,categoryId,categoryName){
	
	let subCategoryobj = {id:subCategoryId,subCategoryName:subCategoryName.trim(),
							  category:{id:categoryId,
							  			categoryName:categoryName}};
	const subCategoryObject = JSON.stringify(subCategoryobj);	
	console.log("created ",subCategoryObject);
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'PUT',
			headers:headers,
			body:subCategoryObject,
		};
		try{
		const host = window.location.origin;
		const link = host+"/editSubCategory";
		console.log(link);
		const response = await fetch(link,requestOption);
			console.log(response.status,"status code");
			console.log(response.statusText);
			if(response.status === 400){
				return null;
			}
		if(!response.ok){
			throw new Error("NetWork response was not ok");
		}
		const data = await response.json();
		console.log(response.status);
		console.log(data);
} catch(error){
	throw new Error("NetWork response was not ok");
}
}


		
