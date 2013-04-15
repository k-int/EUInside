package com.k_int.euinside.core.datamodel

class Module {

	// The validation errors for this record
	static hasMany = [statistics : ModuleStatistic]
		
	// The code for this module
	String code;
	
	// A brief description of this item
	String description;
	
   static constraints = {
		code        maxSize : 20,  nullable : false, unique : true
		description maxSize : 200, nullable : false
    }
}
