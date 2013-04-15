package com.k_int.euinside.core.datamodel

import java.util.Date;

class SetQueuedAction {

	// The set this queued action belongs to
	static belongsTo = [set : ProviderSet]

	// The action to perform
	String action;
	
	// Date this qction was queued
	Date queued;

	// Path to the records that we need to update
	String pathToRecords;
	
	// comma delimited list of records that needs to be updated
	String recordsToBeDeleted;
	
	// Do we delete all the records first
	String deleteAll;
	
    static constraints = {
		action             maxSize : 20,   nullable : false
		queued                             nullable : false
		pathToRecords      maxSize : 256,  nullable : true
		recordsToBeDeleted maxSize : 2000, nullable : true
		deleteAll          maxSize : 3,    nullable : true
    }
}
