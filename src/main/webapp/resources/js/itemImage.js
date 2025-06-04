
let deletedImagesBtn = document.querySelectorAll(".deleteIcon");

deletedImagesBtn.forEach(btn=>{

btn.addEventListener('click',()=>{
	let id = btn.id;
	deleteSumImages(id);
});
});

async function deleteSumImages(imageId) {
    try {
        const headers = {
            'Content-Type': 'application/json',
        };
        const requestOption = {
            method: 'DELETE',
            headers: headers,
        };
        const host = window.location.origin;
        const link = host + "/delSubImage/" + imageId;
        const response = await fetch(link, requestOption);

        if (!response.ok) {
            throw new Error("Network response was not ok");
        }

        // Reload the page after successful deletion
        location.reload();

        // Optionally, return a success message or handle the success event
        return "Image deleted successfully";
    } catch (err) {
        console.error("There was an error when fetching data:", err);
    }
}
