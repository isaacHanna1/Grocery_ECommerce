let parent_tr  = "";
let unitId = "";

let tableContent = document.querySelector(".table-container table tbody");

tableContent.addEventListener("click",e=>{
	if(e.target.classList.contains("delete-btn")){
	 e.preventDefault();	
	 parent_tr  = e.target.closest('tr');
	 unitId = parent_tr.querySelector('td:first-child').textContent;	
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
	  deleteUnit(unitId);
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
	 unitId = parent_tr.querySelector('td:first-child').textContent;
	 oldUnitName = parent_tr.querySelector('#unitName').textContent;
	 oldUnitDescription = parent_tr.querySelector('#unitDescription').textContent;	
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
	 let textForLable  =document.createTextNode("اسم الوحدة");
	 labelForInput.appendChild(textForLable);
     let editUnitName = document.createElement("input");
	 editUnitName.value = oldUnitName;
	 textContainer.appendChild(labelForInput);
     textContainer.appendChild(editUnitName);
     dialogContainer.appendChild(textContainer);
	
	 let labelForDescription = document.createElement("label");
	 let textForDescription  =document.createTextNode("وصف الوحدة");
	 labelForDescription.append(textForDescription);
		
	 let editUnitDescription = document.createElement("input");
	 editUnitDescription.value = oldUnitDescription;
	  textContainer.appendChild(labelForDescription);
     textContainer.appendChild(editUnitDescription);

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
	      overlay_div.classList.remove("overlay");
	      dialogContainer.classList.remove("dialogContainer");
		  dialogContainer.style.display="none";
		  const fetching = editUnit(unitId,editUnitName.value,editUnitDescription.value);
			fetching.then(value=>{
				if(value === null){
					alert("Unit Is already Exists");
					return;
				}
				else{
					 parent_tr.querySelector('#unitName')
					 .textContent = editUnitName.value ;
					 parent_tr.querySelector('#unitDescription')
					 .textContent = editUnitDescription.value;
						
					
				}
			});
				
	    };
		
	}


});


async function deleteUnit(unitID){
	try{
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'DELETE',
			headers:headers,
		};
		const host = window.location.origin;
		const link = host+"/deleteUnit/"+unitID;
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

async function editUnit(unitId ,unitName,unitDescription){
	
	let unitObj = {id:unitId,unitName:unitName.trim(),
							unitDecription:unitDescription };
	const unit = JSON.stringify(unitObj);	
		const headers={
			'Content-Type':'application/json',
		};
		const requestOption = {
			method:'PUT',
			headers:headers,
			body:unit,
		};
		try{
		const host = window.location.origin;
		const link = host+"/editUnit";
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

