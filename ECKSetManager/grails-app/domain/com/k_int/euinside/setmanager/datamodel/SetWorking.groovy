package com.k_int.euinside.setmanager.datamodel

import java.util.Date;

class SetWorking {

	// The provider this set belongs to
	static belongsTo = [set : ProviderSet]
	
	String status = ProviderSet.STATUS_DIRTY;
	Date lastUpdated;
	Integer numberWaitingToBeCommitted = 0;
	Integer numberWaitingToBeValidated = 0;
	Integer numberWithValidationErrors = 0;

    static constraints = {
		status                     maxSize : 20, nullable : false
		lastUpdated                              nullable : true
		numberWaitingToBeCommitted               nullable : false
		numberWaitingToBeValidated               nullable : false
		numberWithValidationErrors               nullable : false
    }
}
