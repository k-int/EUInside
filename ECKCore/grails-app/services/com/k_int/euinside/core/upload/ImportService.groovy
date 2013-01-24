package com.k_int.euinside.core.upload

/**
 * Service to provide metadata upload / import services to the ECK Core
 * @author rpb rich@k-int.com
 * @version 1.0 08.01.13
 */
class ImportService {

	def kiPersistenceWrapperService;
	
	def storeMetadata(String cmsId, String persistentId, byte[] metadataFileContents) {
		def retval = [:];
		
		def existingRecord = kiPersistenceWrapperService.lookupRecord(cmsId, "cms");
		if ( !existingRecord ) {
			existingRecord = kiPersistenceWrapperService.lookupRecord(persistentId, "persistent");
		} 
		
		if ( existingRecord != null && existingRecord.id != null ) {
			// There's an existing record - update it
			log.debug("Updating an existing record");
			
			existingRecord.recordContents = metadataFileContents;
			
			log.debug("About to persist the updated record");
			existingRecord = kiPersistenceWrapperService.updateRecord(existingRecord);
			
			retval.record = existingRecord;

		} else {
			// No existing record - create a new one
			log.debug("Saving a new record");
			
			def newRecord = kiPersistenceWrapperService.createRecord();
			newRecord.cmsId = cmsId;
			newRecord.persistentId = persistentId;
			newRecord.recordContents = metadataFileContents
			
			log.debug("Saving new record with details set");
			newRecord = kiPersistenceWrapperService.saveRecord(newRecord);
			
			retval.record = newRecord;
		}
		
		log.debug("Returning after storing metadata, etc. retval: " + retval);
		return retval;
	}
}
