
let sendBtn = document.getElementById("sendBtn");
sendBtn.onclick = function (event){
event.preventDefault();
let  subCategoryName = document.getElementById("subCategoryName").value;

let  categorySelectElement = document.getElementById("categoriesOption")
let  selectedIndex         = categorySelectElement.selectedIndex;
let  selectOption          = categorySelectElement.options[selectedIndex];
let  categoryID             = selectOption.value; 

//i use null the category name is not important i use foriegn key
addSubCategory(subCategoryName,categoryID,null);	
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
		
