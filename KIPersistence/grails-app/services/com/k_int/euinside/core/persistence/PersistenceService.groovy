package com.k_int.euinside.core.persistence

import com.k_int.euinside.core.datamodel.Record
import com.k_int.euinside.shared.model.functions.ArgumentDefinition;
import com.k_int.euinside.shared.model.functions.MethodDefinition;

import com.k_int.euinside.kipersistence.persistence.PersistenceController;

import java.util.Set;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct

/**
 * Simple class to manage persistence until a persistence module
 * has been implemented
 * 
 * @author rpb rich@k-int.com
 * @version 1.0 08.01.13
 *
 */
class PersistenceService {

	/**************************************/
	// Initialisation
	/**************************************/
	@PostConstruct
	def init() {
		log.debug("PersistenceService::init called");
	}
	
	
	/**************************************/
	// Record related methods
	/**************************************/
	// Lookup
	
	/**
	 * Lookup a record using the ECK ID assigned to the record
	 * @param eckId The ID assigned to the record by the ECK on import
	 * @return The record with the given ID or null if none found
	 */
	def Record lookupRecordByEckId(eckId) {
		log.debug("PersistenceService::lookupRecordByEckId called with eckId: " + eckId);
		
		def retval = null;
		
		// Check that eckId is a number not a string
		if ( eckId != null ) {
			try {
				def eckIdAsNum = eckId.toLong()
				retval = Record.findById(eckIdAsNum);
					
			} catch (NumberFormatException nfe) {
				// Not a number - don't care
			}
		}
		return retval;
	}
	
	/**
	 * Lookup a record using the CMS ID assigned to the record
	 * @param cmsId The ID assigned to the record by the source CMS
	 * @return The record with the given CMS ID or null if none found
	 */
	def Record lookupRecordByCmsId(cmsId) {
		log.debug("PersistenceService::lookupRecordByCmsId called with cmsId: " + cmsId);

		def retval = Record.findByCmsId(cmsId);
		
		return retval;
	}
	
	/**
	 * Lookup a record using the Persistent ID assigned to the record
	 * @param persistentId The persistent ID assigned to the record
	 * @return The record with the given persistent ID or null if none found
	 */
	def Record lookupRecordByPersistentId(persistentId) {
		log.debug("PersistenceService::lookupRecordByPersistentId called with persistent id: " + persistentId);

		def retval = Record.findByPersistentId(persistentId);
		
		return retval;
	}
	
	/**
	 * Lookup a record using the specified combination of ID and id type
	 * @param id The ID assigned to the record of the type given in idType
	 * @param idType The type of ID to look for. Possible values are 'eck', 'cms' or 'persistent'
	 * @return The record with the given identifier and type or null if none found
	 */
	def Record lookupRecord(id, idType) {
		log.debug("PersistenceService::lookupRecord called with id: " + id + " and idType: " + idType);
		
		def retval = null;
		
		if ( "eck".equalsIgnoreCase(idType) ) {
			retval = lookupRecordByEckId(id);
		} else if ( "cms".equalsIgnoreCase(idType) ) {
			retval = lookupRecordByCmsId(id);
		} else if ( "persistent".equalsIgnoreCase(idType) ) {
			retval = lookupRecordByPersistentId(id);
		} else {
			log.error("Unrecognised id type specified when looking up a record by id. Specified type: " + idType);
		}
		
		return retval;
	}
	
	/**
	 * Lookup all records that have the specified ID - whatever type of ID matches
	 * @param id The id to look for whatever type of ID
	 * @return Set<Record> the set of records that match the given search
	 */
	def Set<Record> lookupRecords(id) {
		log.debug("PersistenceService::lookupRecords called with id: " + id);
		
		return lookupRecords(id, id, id);
	}
	
	/**
	 * Lookup all records that match any of the given IDs making sure that the identifiers
	 * only match the specified type
	 * @param cmsId The CMS id to look for
	 * @param persistentId The persistent ID to look for
	 * @param eckId The ECK assigned ID to look for
	 * @return Set<Record> the set of records that match the given search
	 */
	def Set<Record> lookupRecords(cmsId, persistentId, eckId) {
		
		def Set<Record> retval = new LinkedHashSet<Record>();
		
		def tempRes = lookupRecord(cmsId, "cms");
		if ( tempRes != null )
			retval.add(tempRes);
		
		tempRes = lookupRecord(persistentId, "persistent");
		if ( tempRes != null )
			retval.add(tempRes);
		
		tempRes = lookupRecord(eckId, "eck");
		if ( tempRes != null )
			retval.add(tempRes);
		
		return retval;
	}
	
	
	// Creation
	
	/**
	 * Return a new record for use. Doesn't persist the record into the database at this stage
	 * @return A new, blank record. Does not persist any data into the database at this stage
	 */
	def createRecord() {
		log.debug("PersistenceService::createRecord called");
		return new Record();
	}
	
	// Save / update
	
	def saveRecord(record) {
		log.debug("PersistenceService::saveRecord called");
		
		def retval = [:];
		def messages = [];
		
		// Try saving the record keeping hold of any errors thrown, etc.
		if ( !record.save(flush: true) ) {
			
			retval.successful = false;
			// Errors...
			record.errors.each() {
				log.error("Error: " + it);
				messages.add(it);
			}
			retval.messages = messages;
		} else {
			// Saved successfully
			retval.successful = true;
			retval.messages = [];
			retval.messages.add("Record stored successfully");
		}
		
		return retval;
	}
	
	def updateRecord(record) {
		log.debug("PersistenceService::updateRecord called");
		// For grails save and update are the same thing so just call saveRecord
		return saveRecord(record);
	}
	
	def saveOrUpdateRecord(record) {
		log.debug("PersistenceService::saveOrUpdateRecord called");
		// For grails save and update are the same thing so just call saveRecord
		return saveRecord(record);
	}
	
	// TODO - implement the following methods as and when required..
	
	/**************************************/
	// Publication related methods
	/**************************************/
	
	/**************************************/
	// Validation report related methods
	/**************************************/
	
	/**************************************/
	// Validation related methods
	/**************************************/
	
	/**************************************/
	// Harvest related methods
	/**************************************/
	
	
	/**************************************/
	// Identification for the service
	/**************************************/
	def identify() {
		return PersistenceController.identify();
	}	

}
