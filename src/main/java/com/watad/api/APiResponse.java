package com.watad.api;

public class APiResponse {
	
	private String messageHeader;
	private String messageDetails;
	
	
	
	public APiResponse(String messageHeader, String messageDetails) {
		this.messageHeader = messageHeader;
		this.messageDetails = messageDetails;
	}
	
	public String getMessageHeader() {
		return messageHeader;
	}
	public String getMessageDetails() {
		return messageDetails;
	}
	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}
	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}
	
	

}
