package com.k_int.euinside.core.datamodel

class ValidationReport {

	// The overall status of the validation operation
	ValidationStatus overallStatus
	// A message explaining the status
	String	message
	// The date at which the validation report was created
	Date	creationDate
	
    static constraints = {
		overallStatus		nullable: false
		message				nullable: false, blank: false
		creationDate		nullable: false
    }
}
