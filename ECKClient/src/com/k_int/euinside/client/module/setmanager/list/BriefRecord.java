package com.k_int.euinside.client.module.setmanager.list;

import java.util.Date;

public class BriefRecord {

	private String cmsId;
	private String persistentId;
	private Date lastUpdated;
	private boolean deleted;
	private String validationStatus;
	
	public BriefRecord() {
	}

	public String getCmsId() {
		return(cmsId);
	}

	public void setCmsID(String cmsId) {
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

	public boolean isDeleted() {
		return(deleted);
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getValidationStatus() {
		return(validationStatus);
	}

	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}
	
	public String toString() {
		String result = "Class: BriefRecordd:\n"; 
		result += "\tcmsId: " + cmsId + "\n";
		result += "\tpersistentId: " + persistentId + "\n";
		result += "\tdeleted: " + deleted + "\n";
		result += "\tValidation Status: " + validationStatus + "\n";
		return(result);
	}
}
