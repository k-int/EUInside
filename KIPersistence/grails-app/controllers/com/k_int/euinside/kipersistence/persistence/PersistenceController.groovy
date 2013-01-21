package com.k_int.euinside.kipersistence.persistence

import grails.converters.JSON

/**
 * Simple controller to act as an HTTP wrapper around the persistence service
 * Simply exposes each of the service methods to be called
 * @author rpb rich@k-int.com
 * @version 1.0 18.01.13
 *
 */
class PersistenceController {

	/**
	 * The injected persistence service to actually provide the persistence requirements
	 */
	def persistenceService;
	
    def index() { 
		// Don't do anything - just display some text..
	}
	
	def lookupRecordByEckId() {
		
		def eckId = params.eckId;
		
		if ( eckId != null ) {
			// We have an ID - ok to continue

			def record = persistenceService.lookupRecordByEckId(eckId);
		
			def htmlResp = [record: record]
			withFormat {
				html { return htmlResp; }
				json { render record as JSON }
			}
		} else {
			// No ID - can't continue return an error
			response.sendError(400, "An eckId must be specified when calling this function");
		}
	}	
	
	def lookupRecordByCmsId() {
		
		def cmsId = params.cmsId;
		
		if ( cmsId != null ) {
			// We have an ID - ok to continue

			def record = persistenceService.lookupRecordByCmsId(cmsId);
		
			def htmlResp = [record: record]
			withFormat {
				html { return htmlResp; }
				json { render record as JSON }
			}
		} else {
			// No ID - can't continue return an error
			response.sendError(400, "A cmsId must be specified when calling this function");
		}
	}
	
	def lookupRecordByPersistentId() {
		def persistentId = params.persistentId;
		
		if ( persistentId != null ) {
			// We have an ID - ok to continue

			def record = persistenceService.lookupRecordByPersistentId(persistentId);
		
			def htmlResp = [record: record]
			withFormat {
				html { return htmlResp; }
				json { render record as JSON }
			}
		} else {
			// No ID - can't continue return an error
			response.sendError(400, "A persistentId must be specified when calling this function");
		}
	}
	
	def lookupRecord() {
		
		def id = params.id;
		def idType = params.idType;
		
		if ( id != null && idType != null ) {
			// ID and type specified - ok to continue
			def records = persistenceService.lookupRecord(id, idType);
			
			def htmlResp = [records: records]
			withFormat {
				html { return htmlResp; }
				json { render records as JSON; }
			}
		} else {
			// ID or type missing (or both) - can't continue return an error
			response.sendError(400, "An id and an idType must be specified when calling this function");
		}
	} 
	
	
	def lookupRecords() {
		
		def id = params.id;
		
		if ( id != null ) {
			// Id specified - ok to continue
			def records = persistenceService.lookupRecords(id);
			
			def htmlResp = [records: records];
			
			withFormat {
				html { return htmlResp; }
				json { render records as JSON }
			}
			
		} else {
			// No ID - can't continue - return an error
			response.sendError(400, "An id must be specified when calling this function");
		}
	}
	
	def createRecord() {
		
		def record = persistenceService.createRecord();
		
		def htmlResp = [record: record];
		
		withFormat {
			html { return htmlResp; }
			json { render record as JSON }
		}
	}
	
	def saveRecord() {
		
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def deleted = params.deleted;
		def recordContents = params.recordContents;
		
		if ( cmsId != null && persistentId != null && recordContents != null ) {
			def newRecord = persistenceService.createRecord();
			newRecord.cmsId = cmsId;
			newRecord.persistentId = persistentId;
			newRecord.deleted = deleted;
			newRecord.recordContents = recordContents;
			
			def record = persistenceService.saveRecord(newRecord);
			
			def htmlResp = [record: record];
			
			withFormat {
				html { return htmlResp; }
				json { render record as JSON }
			}
		} else {
			// Missing required information - return an error
			response.sendError(400, "A cmsId, persistentId and recordContents must be specified when calling this function");
		}
	}
	
	def updateRecord() {
		
		def eckId = params.eckId;
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def deleted = params.deleted;
		def recordContents = params.recordContents;
		
		if ( eckId != null && cmsId != null && persistentId != null && recordContents != null ) {
			
			def existingRecord = persistenceService.lookupRecordByEckId(eckId);
			if ( existingRecord != null ) {
				existingRecord.cmsId = cmsId;
				existingRecord.persistentId = persistentId;
				existingRecord.deleted = deleted;
				existingRecord.recordContents = recordContents;
				
				def record = persistenceService.saveRecord(existingRecord);
				
				def htmlResp = [record: record];
				
				withFormat {
					html { return htmlResp; }
					json { render record as JSON }
				}
			} else {
				// No record with the specified eck ID - can't update..
				response.sendError(404, "No record found with the specified ECK Id - unable to update");
			}
		} else {
			// Missing required information - return an error
			response.sendError(400, "An eckId, cmsId, persistentId and recordContents must be specified when calling this function");
		}
	}
	
	def saveOrUpdateRecord() {
		
		def eckId = params.eckId;
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def deleted = params.deleted;
		def recordContents = params.recordContents;
		
		if ( cmsId != null && persistentId != null && recordContents != null ) {
			
			if ( eckId != null ) {
				// Existing record..
				def existingRecord = persistenceService.lookupRecordByEckId(eckId);
				if ( existingRecord != null ) {
					existingRecord.cmsId = cmsId;
					existingRecord.persistentId = persistentId;
					existingRecord.deleted = deleted;
					existingRecord.recordContents = recordContents;
					
					def record = persistenceService.saveRecord(existingRecord);
					
					def htmlResp = [record: record];
					
					withFormat {
						html { return htmlResp; }
						json { render record as JSON }
					}
				} else {
					// No record with the specified eck ID - can't update..
					response.sendError(404, "No record found with the specified ECK Id - unable to update");
				}
			} else {
				// New record..
				def newRecord = persistenceService.createRecord();
				newRecord.cmsId = cmsId;
				newRecord.persistentId = persistentId;
				newRecord.deleted = deleted;
				newRecord.recordContents = recordContents;
				
				def record = persistenceService.saveRecord(newRecord);
				
				def htmlResp = [record: record];
				
				withFormat {
					html { return htmlResp; }
					json { render record as JSON }
				}
			}
		} else {
			// Missing required information - return an error
			response.sendError(400, "An eckId, cmsId, persistentId and recordContents must be specified when calling this function");
		}
	}
	
	// TODO - add the identify method here instead of in the service... (Remember to change the save / update specifications)
	
}
