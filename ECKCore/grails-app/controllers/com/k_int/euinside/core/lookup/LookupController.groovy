package com.k_int.euinside.core.lookup

import com.k_int.euinside.core.functions.ArgumentDefinition
import com.k_int.euinside.core.functions.MethodDefinition
import com.k_int.euinside.core.functions.ModuleDefinition
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
	
	/**
	 * Method to specify the methods, etc. provided by this controller for use
	 * in the function listing section of the system
	 * 
	 * @return Description of the 'module' and its available methods
	 */
	def static identify() {
		
		// Set up the index method
		def indexMethodDesc = new MethodDefinition();
		indexMethodDesc.methodName = "index";

		// Set up the search method
		def searchMethodDesc = new MethodDefinition();
		searchMethodDesc.methodName = "search";

		// Set up the show method
		def showMethodDesc = new MethodDefinition();
		showMethodDesc.methodName = "show";
		
		def cmsIdArg = new ArgumentDefinition("cmsId", "String", false);
		def persistentIdArg = new ArgumentDefinition("persistentId", "String", false);
		def eckIdArg = new ArgumentDefinition("eckId", "String", false);
		showMethodDesc.arguments.add(cmsIdArg);
		showMethodDesc.arguments.add(persistentIdArg);
		showMethodDesc.arguments.add(eckIdArg);
		
		
		// Set up the 'module' description and add the various methods to it
		def lookupModule = new ModuleDefinition();
		lookupModule.name = "Lookup";
		lookupModule.methodDefinitions.add(indexMethodDesc);
		lookupModule.methodDefinitions.add(searchMethodDesc);
		lookupModule.methodDefinitions.add(showMethodDesc);
		
		return lookupModule;
	}
}
