package com.k_int.euinside.core.lookup

import grails.converters.JSON

class LookupController {

	def persistenceService;
	
	/**
	 * Simple index action just to provide instructions on what to do
	 * in HTML format
	 */
    def index() { 
		log.debug("ImportController::index method called");
	}
	
	/**
	 * Simple action to mock up the submission of a search for records in the ECK
	 * by their ID, etc.
	 */
	def search() {
		log.debug("ImportController::search method called");
		
		def retVal = [:];
		
		return retVal;
	}
	
	/**
	 * Pull together and return everything we know about an uploaded record by its ID 
	 * in either HTML or JSON depending on the request
	 * @return Information about the specified record in HTML or JSON
	 */
	def show() {
		log.debug("ImportController::show method called with cms id: " + params.cmsId + " persistent id: " + params.persistentId + " and eck ID: " + params.eckId);
		
		def responseVal = [:];
		
		def records = persistenceService.lookupRecords(params.cmsId, params.persistentId, params.eckId);
		
		def responseRecords = [];
		records.each() {
			def nextResponseRecord = [:];
			nextResponseRecord.eckId = it.id;
			nextResponseRecord.cmsId = it.cmsId;
			nextResponseRecord.persistentId = it.persistentId;
			nextResponseRecord.deleted = it.deleted;
			def tempContents = new String(it.recordContents);
			nextResponseRecord.recordContents = tempContents; 
			
			responseRecords.add(nextResponseRecord);
		}
		
		responseVal.records = responseRecords;
		responseVal.recordCount = responseRecords.size();
		
		
		withFormat {
			html { return [responseVal: responseVal] }
			json { render responseVal as JSON }
		}
	}
}
