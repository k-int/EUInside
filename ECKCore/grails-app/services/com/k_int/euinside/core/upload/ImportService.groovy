package com.k_int.euinside.core.upload

/**
 * Service to provide metadata upload / import services to the ECK Core
 * @author rpb rich@k-int.com
 * @version 1.0 08.01.13
 */
class ImportService {

	def persistenceService;
	
	def storeMetadata(String cmsId, String persistentId, byte[] metadataFileContents) {
		def retval = [:];
		
		def existingRecord = persistenceService.lookupRecord(cmsId, "cms");
		if ( !existingRecord ) {
			existingRecord = persistenceService.lookupRecord(persistentId, "persistence");
		} 
		
		if ( existingRecord ) {
			// There's an existing record - update it
			log.debug("Updating an existing record");
			
			existingRecord.recordContents = metadataFileContents;
			
			log.debug("About to persist the updated record");
			def updateData = persistenceService.updateRecord(existingRecord);
			
			def messages = [];
			
			if ( updateData.successful == false ) {
				// An error occurred..
				messages.add("Unable to update the provided record.");
				updateData.messages.each() {
					messages.add(it);
				}
			} else {
				// Saved successfully..
				messages.add("Record updated successfully");
				updateData.messages.each() {
					messages.add(it);
				}
			}
			
			retval.successful = updateData.successful;
			retval.messages = messages;
			retval.record = existingRecord;

		} else {
			// No existing record - create a new one
			log.debug("Saving a new record");
			
			def newRecord = persistenceService.createRecord();
			newRecord.cmsId = cmsId;
			newRecord.persistentId = persistentId;
			newRecord.recordContents = metadataFileContents
			
			log.debug("Saving new record with details set");
			def saveData = persistenceService.saveRecord(newRecord);
			def messages = [];
			
			if ( saveData.successful == false ) {
				// An error occurred..
				messages.add("Unable to save the new record");
				saveData.messages.each() {
					messages.add(it);
				}
			} else {
				// Saved successfully
				messages.add("New record saved successfully");
				saveData.messages.each() {
					messages.add(it);
				}
			}
			
			retval.successful = saveData.successful;
			retval.messages = messages;
			retval.record = newRecord;
		}
		
		log.debug("Returning after storing metadata, etc. retval: " + retval);
		return retval;
	}
}
