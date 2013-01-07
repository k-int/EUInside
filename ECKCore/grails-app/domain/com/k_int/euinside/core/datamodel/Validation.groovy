package com.k_int.euinside.core.datamodel

class Validation {

	// The record to which this validation information applies
	Record eckRecord
	// The status of validation for the record
	ValidationStatus status
	// A message related to the validation
	String	message
	
    static constraints = {
		status		nullable: false
		message		nullable: true
    }
	
}
