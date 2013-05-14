package com.k_int.euinside.client.module.validation.semantika;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Errors {

	@JsonProperty("BrokenRules")
	ArrayList<Error> brokenRules;
	@JsonProperty("IsValid")
	boolean isValid;
	@JsonProperty("Message")
	String message;
	
	public Errors() {
	}
	
	public ArrayList<Error> getBrokenRules() {
		return(brokenRules);
	}
	
	public void setBrokenRules(ArrayList<Error> brokenRules) {
		this.brokenRules = brokenRules;
	}
	
	public boolean isValid() {
		return(isValid);
	}
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	public String getMessage() {
		return(message);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		String result = "Class: Errors:\n"; 
		result += "\tisValid: " + isValid + "\n";
		result += "\tmessage: " + message + "\n";
		for (Error error : brokenRules) {
			result += error.toString();
		}
		return(result);
	}
}
