package com.k_int.euinside.client.module.setmanager.validate;

public class Error {

	String errorCode;
	String additionalInformation;
	
	public Error() {
	}

	public String getErrorCode() {
		return(errorCode);
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getAdditionalInformation() {
		return(additionalInformation);
	}
	
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	

	public String toString() {
		String result = "Class: Error:\n"; 
		result += "\tError Code: " + errorCode + "\n";
		result += "\tAdditional Information: " + additionalInformation + "\n";
		return(result);
	}
}
