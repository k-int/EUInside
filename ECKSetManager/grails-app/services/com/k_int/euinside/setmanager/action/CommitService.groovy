package com.k_int.euinside.setmanager.action

import java.util.Date;

import com.k_int.euinside.setmanager.datamodel.ProviderSet;
import com.k_int.euinside.setmanager.datamodel.Record;
import com.k_int.euinside.setmanager.datamodel.SetLive;
import com.k_int.euinside.setmanager.datamodel.SetQueuedAction;

import com.k_int.euinside.setmanager.persistence.PersistenceService;

class CommitService {

	def PersistenceService;
	
    def queue(ProviderSet set) {
		// We actually queue 3 actions
		def actions = [SetQueuedAction.ACTION_CONVERT_EDM, SetQueuedAction.ACTION_VALIDATE, SetQueuedAction.ACTION_COMMIT];
		actions.each() {
			// Create the queued action
			SetQueuedAction queuedAction = new SetQueuedAction();
			queuedAction.set = set;
			queuedAction.queued = new Date();
			queuedAction.action = it;
	
			// We can now save this record
			if (!queuedAction.save(flush: true)) {
				log.error("Failed to create SetQueuedAction record");
				// Errors...
				queuedAction.errors.each() {
					log.error("Error: " + it);
				}
			}
		}
    }
	
	def process(ProviderSet set) {
		// Ensure we have a live set
		if (set.liveSet == null) {
			set.liveSet = new SetLive();
			set.liveSet.set = set;
			set.liveSet.committed = new Date();
		}

		// Set the status of the live set to committing
		set.liveSet.status = ProviderSet.STATUS_COMMITTING;
		PersistenceService.saveRecord(set.liveSet, "SetLive", set.liveSet.id);
		
		// Set the status of the working set to committing
		set.workingSet.status = ProviderSet.STATUS_COMMITTING;
		PersistenceService.saveRecord(set.workingSet, "SetWorking", set.workingSet.id);

		// Keep track of the number of records we have processed
		int numberRecordsProcessed = 0;
				
		// We need to process all the records in the set where validationStatus is OK and move them over to the live set
		Record.findAllWhere(set : set, live : false, validationStatus : Record.VALIDATION_STATUS_OK).each() {
			// If we already have a live record, then copy the working details accross to the live version
			Record liveRecord = Record.findWhere(set : set, live : true, cmsId : it.cmsId);
			if (liveRecord == null) {
				// nice and easy, just change the live flag to true
				it.live = true;
				PersistenceService.saveRecord(it, "Record", it.id);
			} else {
				// if the checksums and the deleted flags are the same then we do not need to do anything
				if ((liveRecord.checksum != it.checksum) || (liveRecord.deleted != it.deleted)) {
					liveRecord.cmsId = it.cmsId;
					liveRecord.originalData = it.originalData;
					liveRecord.convertedData = it.convertedData;
					liveRecord.deleted = it.deleted;
					liveRecord.checksum = it.checksum;
					liveRecord.originalType = it.originalType;
					liveRecord.convertedType = it.convertedType;
					PersistenceService.saveRecord(liveRecord, "Record", liveRecord.id);
				}
			}
			
			// Not forgeting to increment the count of the number of records processed
			numberRecordsProcessed++;
		}

		// Set the status to committed for the live set		
		set.liveSet.committed = new Date();
		set.liveSet.status = ProviderSet.STATUS_COMMITTED;
		PersistenceService.saveRecord(set.liveSet, "SetLive", set.liveSet.id);

		// Set the status to committed for the working set		
		set.workingSet.lastUpdated = new Date();
		set.workingSet.status = ProviderSet.STATUS_COMMITTED;
		PersistenceService.saveRecord(set.workingSet, "SetWorking", set.workingSet.id);

		// Return the number of records we processed
		return(numberRecordsProcessed);
	}
}
