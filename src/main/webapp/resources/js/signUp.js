showError();	
let btn = document.querySelector(".login button");
btn.addEventListener("mouseenter",goToServer);
const inputs = document.querySelectorAll(".wrap-input input	");
const pattern = {
  userName:  /^[\p{L}a-zA-Z\s]{7,100}$/u,
  phone:  /^(01|048)\d{7,9}$/,
  password: /^.{6,}$/,
  conformPassword:/^.{6,}$/,
  email: /^([a-z\d-.]+)@([a-z\d-]+)\.([a-z]{2,8})(\.[a-z]{2,20})?$/,
};
function validate(field, regex) {
  if (regex.test(field.value)) {
    field.className = "vaild";
  } else {
    field.className = "invalid";
  }
}
inputs.forEach((input) => {
  input.addEventListener("keyup", (event) => {
    validate(event.target, pattern[event.target.attributes.name.value]);
  });
});
let confirmedPassword = document.getElementById('confirmPassword');
  confirmedPassword.addEventListener("keyup", () => {
	validatePassword();
});
let governments = document.querySelector("#governments");
let cities = document.querySelector("#cities");

readJsonFile("../resources/js/governorates.json").then(data =>{
	data.forEach(government =>{
		let option = document.createElement("option");
		option.setAttribute("id",government.id);
		option.setAttribute("value",government.governorate_name_ar);
		let optionTxt = document.createTextNode(government.governorate_name_ar);
		option.appendChild(optionTxt);
		governments.appendChild(option);		
	});
});

governments.addEventListener('change',event =>{
	const selectedIndex = event.target.selectedIndex;
	let govermentId = event.target.options[selectedIndex].id;
	setCities(govermentId);
});


function setCities(govermentId){
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
function removeOptionsFromSelecTag(select){
	
	for(let i= select.options.length - 1; i > 0; i--) {
		select.remove(i);
}
}

function showError(){
let error = document.querySelector(".error");

if (error.firstElementChild.textContent.length > 1 || error.getElementsByClassName('row').length >1)
  {
    error.style.display = "block";
    let rows = error.querySelectorAll(".row");

    for (let row of rows) {
        if (row.textContent.includes('user') ){
            row.textContent = "*تأكد من اسم المستخدم";
        } else if (row.textContent.includes('password'))  {
			row.textContent = "*تأكد من الرقم السري";
        } else if (row.textContent.includes('government') ) {
            row.textContent = "*تأكد من المحافظة";
        } else if (row.textContent.includes('city') ) {
            row.textContent = "*تأكد من المركز";
        } else if (row.textContent.includes('email') ) {
            row.textContent = "*تأكد من البريد الإلكتروني";
        } else if (row.textContent.includes('address')) {
            row.textContent = "*تأكد من العنوان";
 		} else if (row.textContent.includes('phone')) {
            row.textContent = "*تأكد من رقم التليفون";
 }
}
}
}



function goToServer(){
	console.log("called");
	const inputs = document.querySelectorAll(".wrap-input");
	let emptyField = true; 
		for(input of inputs){
		if(input.firstElementChild.value.length > 2){
			emptyField = false;
		}else{
			emptyField = true;
			break;
		}
	}
	if(emptyField){
		disabledSubmit();
	}else{
		abledSubmit();
	}
	console.log(emptyField);
}

function disabledSubmit(){
		  alert("تاكد من انك ادخلت جميع البيانات");	
	      btn.disabled = true;
		  btn.style.cursor = 'not-allowed';
}
function abledSubmit(){
	      btn.disabled = false;
		  btn.style.cursor = 'pointer';
}
function showAlrertForEnteringData(message){
	alert(message);
}

function makeEmptyInputBorderRed(){
	console.log('called');
	let wrapInput = document.querySelectorAll('.wrap-input');
	wrapInput.forEach(one=>{
		let inputValue = one.firstElementChild.value;
		if(inputValue === '' || inputValue === 'اسم المحافظة'
		|| inputValue === 'اسم المركز'){
			one.firstElementChild.style.border="1px solid red";
		}else{
			one.firstElementChild.style.border="none";
		}
	});

}

function validatePassword(){	
	let password = document.getElementById('password');
	let confirmedPassword = document.getElementById('confirmPassword');
	if(password.value !== confirmedPassword.value){
		console.log('not equal');
		confirmedPassword.nextElementSibling.textContent="كلمة السر غير متطابقة";
		confirmedPassword.nextElementSibling.classList.toggle="invalid";
	}
	
}