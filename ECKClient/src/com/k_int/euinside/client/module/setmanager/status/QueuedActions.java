package com.k_int.euinside.client.module.setmanager.status;

import java.util.Date;

public class QueuedActions {

	private String action;
	private Date queued;
	private String contentType;
	private String recordsToBeDeleted;
	private boolean deleteAll;
	
	public QueuedActions() {
	}

	public String getAction() {
		return(action);
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public Date getQueued() {
		return(queued);
	}
	
	public void setQueued(Date queued) {
		this.queued = queued;
	}
	
	public String getContentType() {
		return(contentType);
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getRecordsToBeDeleted() {
		return(recordsToBeDeleted);
	}
	
	public void setRecordsToBeDeleted(String recordsToBeDeleted) {
		this.recordsToBeDeleted = recordsToBeDeleted;
	}
	
	public boolean getDeleteAll() {
		return(deleteAll);
	}
	
	public void setDeleteAll(boolean deleteAll) {
		this.deleteAll = deleteAll;
	}
	
	public String toString() {
		String result = "Class: QueuedAction:\n"; 
		result += "\tAction: " + action + "\n";
		result += "\tQueued: " + queued + "\n";
		result += "\tContent Type: " + contentType + "\n";
		result += "\tRecords to Delete: " + recordsToBeDeleted + "\n";
		result += "\tDelete All: " + deleteAll + "\n\n";
		return(result);
	}
}
