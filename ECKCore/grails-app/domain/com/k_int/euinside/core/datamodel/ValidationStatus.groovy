package com.k_int.euinside.core.datamodel

class ValidationStatus {

	// The name of the validation status 
	String name;
	// A description of its meaning if appropriate
	String description;
	
    static constraints = {
		name		nullable: false
		description	nullable: true
    }
}
