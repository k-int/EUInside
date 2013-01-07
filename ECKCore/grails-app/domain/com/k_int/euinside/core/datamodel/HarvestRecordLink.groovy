package com.k_int.euinside.core.datamodel

class HarvestRecordLink {

	// The harvest event that this record was harvested as part of
	HarvestEvent harvest
	// The record that was harvested
	Record	eckRecord
	
    static constraints = {
		harvest		nullable: false
		eckRecord	nullable: false
    }
}
