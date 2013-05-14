package com.k_int.euinside.client.module.setmanager.status;

import java.util.Date;

public class LiveSet {

	private String status;
	private Date dateCommitted;
	private int numberOfRecords;
	
	public LiveSet() {
	}

	public String getStatus() {
		return(status);
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCommitted() {
		return(dateCommitted);
	}
	
	public void setDateCommitted(Date dateCommitted) {
		this.dateCommitted = dateCommitted;
	}

	public int getNumberOfRecords() {
		return(numberOfRecords);
	}
	
	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	
	public String toString() {
		String result = "Class: LiveSet:\n"; 
		result += "\tStatus: " + status + "\n";
		result += "\tDate Committed: " + dateCommitted + "\n";
		result += "\tNumber of Records: " + numberOfRecords + "\n\n";
		return(result);
	}
}
