package com.k_int.euinside.core.datamodel

class HarvestEvent {

	// The start date of a harvest request
	Date harvestDate;
	// The IP address of the host initiating the harvest
	String ipAddress;
	// The set being harvested if specified
	String setSpec;
	
    static constraints = {
		harvestDate		nullable: false
		ipAddress		nullable: false
		setSpec				nullable: true
    }
}
