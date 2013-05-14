package com.k_int.euinside.client.module.setmanager.status;

public class WorkingSet {

	private String status;
	private int numberOfRecords;
	private int numberOfRecordsValid;
	private int numberOfRecordsAwaitingValidation;
	private int numberOfRecordsValidationErrors;
	private int numberOfRecordsDeleted;
	
	public WorkingSet() {
	}

	public String getStatus() {
		return(status);
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumberOfRecords() {
		return(numberOfRecords);
	}
	
	public 	void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public int getNumberOfRecordsValid() {
		return(numberOfRecordsValid);
	}
	
	public 	void setNumberOfRecordsValid(int numberOfRecordsValid) {
		this.numberOfRecordsValid = numberOfRecordsValid;
	}

	public int getNumberOfRecordsAwaitingValidation() {
		return(numberOfRecordsAwaitingValidation);
	}
	
	public void setNumberOfRecordsAwaitingValidation(int numberOfRecordsAwaitingValidation) {
		this.numberOfRecordsAwaitingValidation = numberOfRecordsAwaitingValidation;
	}

	public int getNumberOfRecordsValidationErrors() {
		return(numberOfRecordsValidationErrors);
	}
	
	public void setNumberOfRecordsValidationErrors(int numberOfRecordsValidationErrors) {
		this.numberOfRecordsValidationErrors = numberOfRecordsValidationErrors;
	}

	public int getNumberOfRecordsDeleted() {
		return(numberOfRecordsDeleted);
	}
	
	public void setNumberOfRecordsDeleted(int numberOfRecordsDeleted) {
		this.numberOfRecordsDeleted = numberOfRecordsDeleted;
	}
	
	public String toString() {
		String result = "Class: WorkingSet:\n"; 
		result += "\tStatus: " + status + "\n";
		result += "\tNumber of Records (valid): " + numberOfRecordsValid + "\n";
		result += "\tNumber of Records (awaiting validation): " + numberOfRecordsAwaitingValidation + "\n";
		result += "\tNumber of Records (validation errors): " + numberOfRecordsValidationErrors + "\n";
		result += "\tNumber of Records (deleted): " + numberOfRecordsDeleted + "\n\n";
		return(result);
	}
}
