package com.k_int.euinside.client.module.metadataDefinition.error;

public class Error {

	String error;
	String definition;
	
	public Error() {
	}
	
	public String getError() {
		return(error);
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getDefinition() {
		return(definition);
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public String toString() {
		String result = "Class: Error:\n"; 
		result += "\terror: " + error + "\n";
		result += "\tdefinition: " + definition + "\n";
		return(result);
	}
}
