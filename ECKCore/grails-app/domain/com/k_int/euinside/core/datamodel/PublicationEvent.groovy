package com.k_int.euinside.core.datamodel

class PublicationEvent {
	
	// The record to which this event relates
	Record eckRecord;
	
	// The date of publication
	Date publicationDate;
	// The username associated with the publication
	String userName;
	// The IP to which the publication was aimed
	String targetIP;
	// The actual EDM data published
	String edmData;

    static constraints = {
		eckRecord		nullable: false
		publicationDate	nullable: false
		userName		nullable: false, blank: false
		targetIP		nullable: false, blank: false
		edmData			nullable: false, blank: false
    }
	
}
