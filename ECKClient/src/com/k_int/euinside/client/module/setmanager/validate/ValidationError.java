package com.k_int.euinside.client.module.setmanager.validate;

import java.util.ArrayList;
import java.util.Date;

public class ValidationError {

	String cmsId;
	String persistentId;
	Date lastUpdated;
	ArrayList<Error> errors;
	
	public ValidationError() {
	}

	public String getCmsId() {
		return(cmsId);
	}
	
	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}
	
	public String getPersistentId() {
		return(persistentId);
	}
	
	public void setPersistentId(String persistentId) {
		this.persistentId = persistentId;
	}
	
	public Date getLastUpdated() {
		return(lastUpdated);
	}
	
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public ArrayList<Error> getErrors() {
		return(errors);
	}
	
	public void setErrors(ArrayList<Error> errors) {
		this.errors = errors;
	}

	public String toString() {
		String result = "Class: ValidationError:\n"; 
		result += "\tcmsId: " + cmsId + "\n";
		result += "\tpersistentId: " + persistentId + "\n";
		result += "\tlastUpdated: " + lastUpdated + "\n";
		for (Error error : errors) {
			result += error.toString();
		}
		return(result);
	}
}
