package com.k_int.euinside.core.datamodel

class Record {

	// The CMS ID for the record
	String cmsId;
	// The generated persistent ID for the record
	String persistentId;
	// The actual contents of the record
	byte[] recordContents;
	
	// Has the record been deleted? (true for deleted, not-true for live record)
	Boolean deleted;
	
    static constraints = {
		cmsId 				nullable: false
		persistentId 		nullable: false
		deleted				nullable: true	
		recordContents		nullable: true, maxSize: 10 * 1024 * 1024 
    }
}
