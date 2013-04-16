package com.k_int.euinside.setmanager.action

import com.k_int.euinside.setmanager.datamodel.ProviderSet;
import com.k_int.euinside.setmanager.datamodel.Record;

class ListService {

	/**
	 * Returns the brief details for all the records in the working set
	 * 
	 * @param set ... The set that you want the information for
	 * 
	 * @return A json compatible array of the brief details for all the records in the working set
	 */
    def workingSet(ProviderSet set) {
		def recordBriefDetails = [ ];
		Record.findAllWhere(set : set, live : false).each() {
			def briefDetails = ["cmsId"            : it.cmsId,
				                "persistentId"     : it.persistentId,
								"lastUpdated"      : it.lastUpdated,
								"deleted"          : it.deleted,
								"validationStatus" : it.validationStatus];
			recordBriefDetails.push(briefDetails);
		}
		return(recordBriefDetails);
    }
}
