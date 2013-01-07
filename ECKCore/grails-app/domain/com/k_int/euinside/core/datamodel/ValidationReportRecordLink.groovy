package com.k_int.euinside.core.datamodel

class ValidationReportRecordLink {

	// The validation information for the record in question
	Validation recordValidation;
	// The validation report to which this validation information is to be linked
	ValidationReport report
	
    static constraints = {
		recordValidation	nullable: false
		report				nullable: false
		
    }
}
