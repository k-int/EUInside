package com.k_int.euinside.client.module.pidgenerate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PIDComponents {
	@JsonProperty("InstitutionUrl")
	private String institutionURL;
	@JsonProperty("RecordType")
	private String recordType;
	@JsonProperty("AccessionNumber")
	private String accessionNumber;

	public PIDComponents() {
	}

	public PIDComponents(String institutionURL, String recordType, String accessionNumber) {
		this.institutionURL = institutionURL;
		this.recordType = recordType;
		this.accessionNumber = accessionNumber;
	}

	public String getInstitutionURL() {
		return(institutionURL);
	}
	
	public void setInstitutionURL(String institutionURL) {
		this.institutionURL = institutionURL;
	}
	
	public String getRecordType() {
		return(recordType);
	}
	
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAccessionNumber() {
		return(accessionNumber);
	}
	
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
	
	public String toString() {
		String result = "Class: PIDComponents:\n"; 
		result += "\taccessionNumber: \"" + accessionNumber + "\"\n";
		result += "\tinstitutionURL: \"" + institutionURL + "\"\n";
		result += "\trecordType: \"" + recordType + "\"\n";
		return(result);
	}
}
