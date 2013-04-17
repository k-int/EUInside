package com.k_int.euinside.setmanager.action

import com.k_int.euinside.setmanager.datamodel.ProviderSet;
import com.k_int.euinside.setmanager.datamodel.Record;

class ValidationService {

	/**
	 * Returns all the validation errors for the specified set
	 * 
	 * @return A json compatiblee array of all the records that have validation errors 
	 */
    def list(ProviderSet set) {
		def recordsInError = [ ];
		Record.findAllWhere(set : set, live : false, validationStatus : Record.VALIDATION_STATUS_ERROR).each() {
			buildErrors(it, recordsInError);
		}
		return(recordsInError);
    }
	
	/**
	 * Returns all the validation errors for the specified set and record 
	 * 
	 * @return A json compatiblee array of all the records that have validation errors 
	 */
    def list(ProviderSet set, String cmsId) {
		if ((cmsId == null) || cmsId.isEmpty()) {
			return(list(set));
		} else {
			def recordsInError = [ ];
			Record.findAllWhere(set : set, live : false, validationStatus : Record.VALIDATION_STATUS_ERROR, cmsId : cmsId).each() {
				buildErrors(it, recordsInError);
			}
			return(recordsInError);
		}
    }
	
	private def buildErrors(Record record, recordsInError) {
		def errors = [ ];
		record.validationErrors.each() {
			def error = ["errorCode" : it.errorCode,
				         "additionalInformation" : it.additionalInformation];
			errors.push(error);
		}
	
		def details = ["cmsId"        : record.cmsId,
					   "persistentId" : record.persistentId,
					   "lastUpdated"  : record.lastUpdated,
					   "errors"       : errors];
		recordsInError.push(details);
	}
}
