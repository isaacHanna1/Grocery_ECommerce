

// Start confirmation dialog for deleting btn
// btn as a href ok 
let deleteBtns = document.querySelectorAll(".delete-btn");
let editBtns = document.querySelectorAll  (".edit-btn");
let parent_tr  = "";
let categoryId = "";
let categoryName = "";


const categoryForm = document.getElementById("categoryForm");
const allInputs = categoryForm.querySelectorAll('input');
categoryForm.addEventListener("submit",(event)=>{
	allInputs.forEach(input=>{
		if(!input.value.trim()){
			event.preventDefault();	
			input.style.borderColor = "red";	
		}
		input.value =input.value.trim();
	})
	
})

const sendBtn = document.getElementById("sendBtn");

// important note im note use delegation event in parent 
// because i reload page after adding new category 
	/* when user click on delete <a> element
	 make full screen grba transparent color to show confirmation
	 div to delete category*/	
deleteBtns.forEach((btn) => {
  btn.addEventListener("click", (e) => {
	e.preventDefault();
	
	 parent_tr  = e.target.closest('tr');
	 categoryId = parent_tr.querySelector('td:first-child').textContent;	
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
	
	  deleteCategory(categoryId);
      overlay_div.classList.remove("overlay");
	
      dialogContainer.classList.remove("dialogContainer");
	  dialogContainer.style.display="none";
	  //reomve tr after deleted from database
	  parent_tr.remove();		
    };

  });
  });

async function deleteCategory(categoryId){
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'DELETE',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/categoryApi/deleteCategory/"+categoryId;
		console.log(link);
		const response = await fetch(link,requestOption);
		
		if(!response.ok){
			throw new Error("Network response was not ok");
		}
		const data = await response.json();
		console.log(data)
		return data;
	}
	catch(err){
		console.error("there was an error when fetching data (exception)" , err);
	}
}
// End confirmation dialog for deleting btn

// start dialog for editing category btn
editBtns.forEach((btn) => {
  btn.addEventListener("click", (e) => {
	e.preventDefault();
	
	 parent_tr  = e.target.closest('tr');
	 categoryId = parent_tr.querySelector('td:first-child').textContent;
	 categoryName = parent_tr.querySelector('#categoryNameCol').textContent;	
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
	 let textForLable  =document.createTextNode("اسم القسم");
	 labelForInput.appendChild(textForLable);
     let editInput = document.createElement("input");
	 editInput.value = categoryName;
	 textContainer.appendChild(labelForInput);
     textContainer.appendChild(editInput);
     dialogContainer.appendChild(textContainer);
	
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
		  categoryName = editInput.value
	      overlay_div.classList.remove("overlay");
	      dialogContainer.classList.remove("dialogContainer");
		  dialogContainer.style.display="none";
		  const fetching = editCategory(categoryId ,editInput.value);
			fetching.then(value=>{
				if(value === null){
					alert("Category Is already Exists");
					return;
				}
				else{
					parent_tr.querySelector('#categoryNameCol').textContent = editInput.value;
				}
			});
				
	    };
  });
  });
async function editCategory(categoryId,updatedCategoryName){
	
	let categoryobj = {id:categoryId , categoryName:updatedCategoryName.trim()};
	const editObject = JSON.stringify(categoryobj);	
	console.log("created ",editCategory);
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'PUT',
			headers:headers,
			body:editObject,
		};
		try{
		const host = window.location.origin;
		const link = host+"/categoryApi/editCategory";
		console.log(link);
		const response = await fetch(link,requestOption);
			console.log();
			console.log(response.statusText);
			if(response.status === 400){
				return null;
			}
		if(!response.ok){
			throw new Errror("NetWork response was not ok");
		}
		const data = await response.json();
		console.log(response.status);
		return {data , status: response.status};
		}catch(error){
			throw error;
		}
		}
		
// End dialog for editing category btn  


