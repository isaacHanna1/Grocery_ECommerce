function handle_forgetPassword() {
    const username = document.getElementById('username');
    const messageAlert = document.getElementById('messageAlert');
    
    // Create the message node outside the event listener to maintain the reference
    const messageText = 'برجاء ادخال رقم التليفون المسجل';
    let messageNode = document.createTextNode(messageText);

    let username_value = username.value.trim();
    if (username_value === "" || username == null) {
        if (!messageAlert.classList.contains('error')) {
            messageAlert.classList.add('error');
            messageAlert.appendChild(messageNode);
        }
    } else {
        if (messageAlert.classList.contains('error')) {
            messageAlert.classList.remove('error');
            messageAlert.textContent = '';
        }
        findEmailByPhone(username_value).then(email => {
            console.log('The email is:', email);
			sendForgetPasswordRequest(email).then(sent=>{
				const successMessage = "نرجوا التحقق من البريد الالكتروني الخاص بك";
                messageAlert.classList.remove('error');
                messageAlert.classList.add('success');
                messageAlert.textContent = '';  // Clear any existing messages
                messageAlert.appendChild(document.createTextNode(successMessage));
			});
        }).catch(error => {
            console.error('There was a problem fetching email:', error);
        });
    }
}
function findEmailByPhone(phone) {
    const host = window.location.origin;
    const apiUrl = host + '/findEmailByPhone?phone=' + phone;

    return fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text(); 
        })
        .then(data => {
            return data.trim();
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
            throw error; 
        });
}
function sendForgetPasswordRequest(email) {
	
	const host = window.location.origin;
    const apiUrl = host + '/forget';

	console.log('im called',apiUrl);
    const formData = new FormData();
    formData.append('email', email);  // Add email parameter to form data

    return fetch(apiUrl, {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();  // Assuming the response is plain text
    })
    .then(data => {
        return data;  // Return response data if needed
    })
    .catch(error => {
        throw error;  // Re-throw the error to propagate it
    });
}
