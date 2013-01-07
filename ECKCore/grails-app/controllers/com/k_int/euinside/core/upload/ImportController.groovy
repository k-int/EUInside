package com.k_int.euinside.core.upload

import grails.converters.JSON

class ImportController {

	// Ensure save only responds to POST requests
	static allowedMethods = [save: 'POST'];
	
	/**
	 * Simple index action just to provide instructions on what to do
	 * in HTML format
	 */
    def index() { 
		log.debug("ImportController::index method called");
	}
	
	/**
	 * Simple action to mock up the submission of a metadata file into the
	 * ECK - not intended to be used generally
	 */
	def create() {
		log.debug("ImportController::create method called");
		
		def retVal = [:];
		
		return retVal;
	}
	
	/**
	 * Method to accept uploaded metadata record and store it in the ECK for now
	 * @return Success or failure and information regarding the upload as either HTML or JSON
	 */
	def save() {
		log.debug("ImportController::save method called");
		
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		// TODO - get the file too..

		// Set up the response to be returned
		def responseVal = [:];
		
		// Check for the required fields and complain if not received
		def responseMessages = [];
		def okToContinue = true;
		
		if ( !cmsId || "".equals(cmsId.trim()) ) {
			okToContinue = false;
			responseMessages.add("A CMS ID is required and has not been specified");
		}
		if ( !persistentId || "".equals(persistentId.trim()) ) {
			okToContinue = false;
			responseMessages.add("A persistent ID is required and has not been specified");
		}
		
		if ( okToContinue ) {
			
			// TODO - actually do something about storing the uploaded file, etc.
			
			responseVal.success = true;
			responseVal.messages = [];
			responseVal.messages.add("Metadata record successfully imported");
			responseVal.eckId = System.currentTimeMillis();
		} else {
			// Can't continue as we don't have the required information - set up the return information
			// with this fact so that it can be returned as appropriate
			responseVal.success = false;
			responseVal.messages = responseMessages;
			responseVal.eckId = 0l;
		}
		
		withFormat {
			html { return [responseVal: responseVal] }
			json { render responseVal as JSON }
			
		}
	}
	
	/**
	 * Pull together and return everything we know about an uploaded record by its ID 
	 * in either HTML or JSON depending on the request
	 * @return Information about the specified record in HTML or JSON
	 */
	def show() {
		log.debug("ImportController::show method called with cms id: " + params.cmsId + " persistent id: " + params.persistentId + " and eck ID: " + params.eckId);
		
		def responseVal = [:];
		
		// TODO
		
		withFormat {
			html responseVal
			json { render responseVal as JSON }
		}
	}
}
