package com.k_int.euinside.core.datamodel

import java.util.Date;

class SetLive {

	// The provider this set belongs to
	static belongsTo = [set : ProviderSet]
	
	String status = ProviderSet.STATUS_COMMITTED;
	Date committed;
	Integer numberCommitted = 0;

    static constraints = {
		status          maxSize : 20, nullable : false
		committed                     nullable : true
		numberCommitted               nullable : false
    }
}
