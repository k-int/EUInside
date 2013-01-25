package com.k_int.euinside.kipersistence.persistence

import com.k_int.euinside.shared.model.functions.ArgumentDefinition
import com.k_int.euinside.shared.model.functions.MethodDefinition
import com.k_int.euinside.shared.model.functions.ModuleDefinition
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
	
	
	def lookupRecordsAnyIdType() {
		
		def id = params.id;
		
		if ( id != null ) {
			// Id specified - ok to continue
			def records = persistenceService.lookupRecordsAnyIdType(id);
			
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
	
	def lookupRecords() {
		
		def eckId = params.eckId;
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		
		if ( eckId != null || cmsId != null || persistentId != null ) {
			// Id specified - ok to continue
			def records = persistenceService.lookupRecords(cmsId, persistentId, eckId);
			
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
		def recordContents = request.getFile("recordContents");
		
		if ( cmsId != null && persistentId != null && recordContents != null && !recordContents.isEmpty()) {
			def newRecord = persistenceService.createRecord();
			newRecord.cmsId = cmsId;
			newRecord.persistentId = persistentId;
			newRecord.deleted = deleted;
			newRecord.recordContents = recordContents.getBytes();
			
			def recordStatus = persistenceService.saveRecord(newRecord);
			
			if ( recordStatus.successful ) {
				// Saved successfully
				def htmlResp = [record: recordStatus.record];
				
				withFormat {
					html { return htmlResp; }
					json { render recordStatus.record as JSON }
				}
			} else {
				// Failure during save
				String errors = "";
				recordStatus.messages.each() { aMessage ->
					if ( !"".equals(errors) )
						errors.append("; ");
					errors.append(aMessage);
				}
				response.sendError(500, errors);
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
		def recordContents = request.getFile("recordContents");
		
		if ( eckId != null && cmsId != null && persistentId != null && recordContents != null && !recordContents.isEmpty() ) {
			
			def existingRecord = persistenceService.lookupRecordByEckId(eckId);
			if ( existingRecord != null ) {
				existingRecord.cmsId = cmsId;
				existingRecord.persistentId = persistentId;
				existingRecord.deleted = deleted;
				existingRecord.recordContents = recordContents.getBytes();
				
				def recordStatus = persistenceService.saveRecord(existingRecord);
				
				if ( recordStatus.successful ) {
					// Saved successfully
					def htmlResp = [record: recordStatus.record];
					
					withFormat {
						html { return htmlResp; }
						json { render recordStatus.record as JSON }
					}
				} else {
					// Failure during save
					String errors = "";
					recordStatus.messages.each() { aMessage ->
						if ( !"".equals(errors) )
							errors.append("; ");
						errors.append(aMessage);
					}
					response.sendError(500, errors);
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
		def recordContents = request.getFile("recordContents");
		
		if ( cmsId != null && persistentId != null && recordContents != null && !recordContents.isEmpty() ) {
			
			if ( eckId != null ) {
				// Existing record..
				def existingRecord = persistenceService.lookupRecordByEckId(eckId);
				if ( existingRecord != null ) {
					existingRecord.cmsId = cmsId;
					existingRecord.persistentId = persistentId;
					existingRecord.deleted = deleted;
					existingRecord.recordContents = recordContents.getBytes();
					
					def recordStatus = persistenceService.saveRecord(existingRecord);
					
					if ( recordStatus.successful ) {
						// Saved successfully
						def htmlResp = [record: recordStatus.record];
						
						withFormat {
							html { return htmlResp; }
							json { render recordStatus.record as JSON }
						}
					} else {
						// Failure during save
						String errors = "";
						recordStatus.messages.each() { aMessage ->
							if ( !"".equals(errors) )
								errors.append("; ");
							errors.append(aMessage);
						}
						response.sendError(500, errors);
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
	
	/**
	 * construct and return information about the methods provided by this service
	 * @return Set<MethodDefinition> The set of methods that this service provides
	 */
	def static identify() {
		
		Set<MethodDefinition> methods = new LinkedHashSet<MethodDefinition>();
		
		//*********************
		// Lookup methods
		//*********************
		
		// By ECK ID
		def lookupEckIdArg = new ArgumentDefinition("eckId", "Long", true);
		def args = new LinkedHashSet<ArgumentDefinition>();
		args.add(lookupEckIdArg);
		
		def lookupRecordByEckIdMethod = new MethodDefinition("lookupRecordByEckId", args, "Record", "Lookup a record by its assigned ECK ID");

		methods.add(lookupRecordByEckIdMethod);
		
		// By CMS ID
		def lookupCmsIdArg = new ArgumentDefinition("cmsId", "String", true);
		def lookupCmsArgs = new LinkedHashSet<ArgumentDefinition>();
		lookupCmsArgs.add(lookupCmsIdArg);
		
		def lookupRecordByCmsIdMethod = new MethodDefinition("lookupRecordByCmsId", lookupCmsArgs, "Record", "Lookup a record by its assigned CMS ID");
		
		methods.add(lookupRecordByCmsIdMethod);
		
		// By Persistent ID
		def lookupPersistentIdArg = new ArgumentDefinition("persistentId", "String", true);
		def lookupPersistArgs = new LinkedHashSet<ArgumentDefinition>();
		lookupPersistArgs.add(lookupPersistentIdArg);
		
		def lookupRecordByPersistentIdMethod = new MethodDefinition("lookupRecordByPersistentId", lookupPersistArgs, "Record", "Lookup a record by its assigned persistent ID");
		
		methods.add(lookupRecordByPersistentIdMethod);
		
		// By type of ID
		def lookupIdArg = new ArgumentDefinition("id", "String", true);
		def lookupTypeArg = new ArgumentDefinition("idType", "String", true);
		def lookupByTypeArgs = new LinkedHashSet<ArgumentDefinition>();
		lookupByTypeArgs.add(lookupIdArg);
		lookupByTypeArgs.add(lookupTypeArg);
		
		def lookupRecordWithTypeMethod = new MethodDefinition("lookupRecord", lookupByTypeArgs, "Record", "Lookup a record by its ID with the given type");
		methods.add(lookupRecordWithTypeMethod);
		
		// By any type of ID
		def lookupJustIdArg = new ArgumentDefinition("id", "String", true);
		def lookupArgs = new LinkedHashSet<ArgumentDefinition>();
		lookupArgs.add(lookupJustIdArg);
		
		def lookupRecordAnyIdTypeMethod = new MethodDefinition("lookupRecordsAnyIdType", lookupArgs, "Set<Record>", "Lookup a record or records with any type of identifier");
		methods.add(lookupRecordAnyIdTypeMethod);
		
		// Lookup by multiple identifiers
		def optionalCmsIdArg = new ArgumentDefinition("cmsId", "String", false);
		def optionalPersistentIdArg = new ArgumentDefinition("persistentId", "String", false);
		def optionalEckIdArg = new ArgumentDefinition("eckId", "Long", false);
		def lookupMultiArgs = new LinkedHashSet<ArgumentDefinition>();
		lookupMultiArgs.add(optionalCmsIdArg);
		lookupMultiArgs.add(optionalPersistentIdArg);
		lookupMultiArgs.add(optionalEckIdArg);
		
		def lookupWithMultipleIdsMethod = new MethodDefinition("lookupRecords", lookupMultiArgs, "Set<Record>", "Lookup a record or records with multiple identifiers of different types");
		methods.add(lookupWithMultipleIdsMethod);
		
		//********************
		// Creation methods
		//********************
		def createArgs = new LinkedHashSet<ArgumentDefinition>();
		def recordCreationMethod = new MethodDefinition("createRecord", createArgs, "Record", "Create a new empty record");
		methods.add(recordCreationMethod);
		
		//********************
		// Save / update methods
		//********************
		
		// Save method
		def cmsIdArg = new ArgumentDefinition("cmsId", "String", true);
		def persistentIdArg = new ArgumentDefinition("persistentId", "String", true);
		def deletedArg = new ArgumentDefinition("deleted", "boolean", false);
		def recordContentsArg = new ArgumentDefinition("recordContents", "File", true); 
		
		def saveArgs = new LinkedHashSet<ArgumentDefinition>();
		saveArgs.add(cmsIdArg);
		saveArgs.add(persistentIdArg);
		saveArgs.add(deletedArg);
		saveArgs.add(recordContentsArg);
		
		def saveRecordMethod = new MethodDefinition("saveRecord", saveArgs, "void", "Save a record in the persistence layer. Note this method must be POSTed to with an encoding type of \"multipart/form-data\"");
		methods.add(saveRecordMethod);
		
		// Update method
		def eckIdArg = new ArgumentDefinition("eckId", "Long", true);
		def updateArgs = new LinkedHashSet<ArgumentDefinition>();
		updateArgs.add(eckIdArg);
		updateArgs.addAll(saveArgs);
		
		def updateRecordMethod = new MethodDefinition("updateRecord", updateArgs, "void", "Update a record in the persistence layer. Note this method must be POSTed to with an encoding type of \"multipart/form-data\"");
		methods.add(updateRecordMethod);
		
		// Save or update method
		def saveOrUpdateRecordMethod = new MethodDefinition("saveOrUpdateRecord", updateArgs, "void", "Update an existing record in the persistence layer or save it if one does not already exist. Note this method must be POSTed to with an encoding type of \"multipart/form-data\"");
		methods.add(saveOrUpdateRecordMethod);
		
		// TODO - add more methods as they are implemented above..
		
		
		// Set up the overall persistence module information
		def retval = new ModuleDefinition();
		retval.name = "/KIPersistence/persistence";
		retval.methodDefinitions = methods;
		retval.moduleType = "external";
		
		return retval;
	}
	
	
}
