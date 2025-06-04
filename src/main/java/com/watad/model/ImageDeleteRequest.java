package com.watad.model;



// i make this class to delete the subImages of item 
//so when i want to send the page that display the image 
// i want to send the base64 of image and the id of the image itSelf 
public class ImageDeleteRequest {
	
	private Long imageId;
    private String base64Data;
    
    
	public ImageDeleteRequest(Long imageId, String base64Data) {
		super();
		this.imageId = imageId;
		this.base64Data = base64Data;
	}
	public Long getImageId() {
		return imageId;
	}
	public String getBase64Data() {
		return base64Data;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public void setBase64Data(String base64Data) {
		this.base64Data = base64Data;
	}
    
    
    

}
