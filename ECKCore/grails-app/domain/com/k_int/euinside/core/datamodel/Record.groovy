package com.k_int.euinside.core.datamodel

class Record {

	// The CMS ID for the record
	String cmsId;
	// The generated persistent ID for the record
	String persistentId;
	// Has the record been deleted? (true for deleted, not-true for live record)
	Boolean deleted;
	
    static constraints = {
		cmsId 				nullable: false
		persistentId 		nullable: false
		deleted				nullable: true		
    }
}
