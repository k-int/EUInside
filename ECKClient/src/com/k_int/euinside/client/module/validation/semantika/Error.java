package com.k_int.euinside.client.module.validation.semantika;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
	@JsonProperty("BrokenRuleCode")
	String brokenRuleCode;
	@JsonProperty("Message")
	String message;
	@JsonProperty("Severity")
	int severity;

	public Error() {
	}
	
	public String getBrokenRuleCode() {
		return(brokenRuleCode);
	}
	
	public void setBrokenRuleCode(String brokenRuleCode) {
		this.brokenRuleCode = brokenRuleCode;
	}
	
	public String getMessage() {
		return(message);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getSeverity() {
		return(severity);
	}
	
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	
	public String toString() {
		String result = "Class: Error:\n"; 
		result += "\tbrokenRuleCode: " + brokenRuleCode + "\n";
		result += "\tmessage: " + message + "\n";
		result += "\tseverity: " + severity + "\n";
		return(result);
	}
}
