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
	def LegacyPersistenceService;
	
    def index() { 
		// Don't do anything - just display some text..
	}
	
	def lookupRecordByEckId() {
		
		def eckId = params.eckId;
		
		if ( eckId != null ) {
			// We have an ID - ok to continue

			def record = LegacyPersistenceService.lookupRecordByEckId(eckId);
		
			// Only support json		
			render record as JSON
		} else {
			// No ID - can't continue return an error
			response.sendError(400, "An eckId must be specified when calling this function");
		}
	}	
	
	def lookupRecordByCmsId() {
		
		def cmsId = params.cmsId;
		
		if ( cmsId != null ) {
			// We have an ID - ok to continue

			def record = LegacyPersistenceService.lookupRecordByCmsId(cmsId);
		
			// Only support json		
			render record as JSON
		} else {
			// No ID - can't continue return an error
			response.sendError(400, "A cmsId must be specified when calling this function");
		}
	}
	
	def lookupRecordByPersistentId() {
		def persistentId = params.persistentId;
		
		if ( persistentId != null ) {
			// We have an ID - ok to continue

			def record = LegacyPersistenceService.lookupRecordByPersistentId(persistentId);
		
			// Only support json		
			render record as JSON
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
			def records = LegacyPersistenceService.lookupRecord(id, idType);
			
			// Only support json		
			render records as JSON
		} else {
			// ID or type missing (or both) - can't continue return an error
			response.sendError(400, "An id and an idType must be specified when calling this function");
		}
	} 
	
	
	def lookupRecordsAnyIdType() {
		
		def id = params.id;
		
		if ( id != null ) {
			// Id specified - ok to continue
			def records = LegacyPersistenceService.lookupRecordsAnyIdType(id);
			
			// Only support json		
			render records as JSON
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
			def records = LegacyPersistenceService.lookupRecords(cmsId, persistentId, eckId);
			
			// Only support json			
			render records as JSON
		} else {
			// No ID - can't continue - return an error
			response.sendError(400, "An id must be specified when calling this function");
		}
	}
	
	def createRecord() {
		
		def record = LegacyPersistenceService.createRecord();
		
		// Only support json		
		render record as JSON
	}
	
	def saveRecord() {
		
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def deleted = Boolean.parseBoolean(params.deleted);
		def recordContents = ((request instanceof org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest) ? request.getFile("recordContents") : null);
		def providerCode = request.provider;
		def setCode = request.set; 
		
		if ((cmsId != null) &&
			(persistentId != null) &&
			(recordContents != null) &&
			 !recordContents.isEmpty()) {
			def newRecord = LegacyPersistenceService.createRecord();
			newRecord.cmsId = cmsId;
			newRecord.persistentId = persistentId;
			newRecord.deleted = deleted;
			newRecord.originalData = recordContents.getBytes();
			newRecord.set = LegacyPersistenceService.checkProviderSet(providerCode, setCode, true);
			
			def recordStatus = LegacyPersistenceService.saveRecord(newRecord);
			
			if ( recordStatus.successful ) {
				// Saved successfully
				// Only support json				
				render recordStatus.record as JSON
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

	private def localUpdateRecord(record) {
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def deleted = Boolean.parseBoolean(params.deleted);
		def recordContents = ((request instanceof org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest) ? request.getFile("recordContents") : null);
			
		if ( record != null ) {
			if (cmsId != null) {
				record.cmsId = cmsId;
			}
			if (persistentId != null) {
				record.persistentId = persistentId;
			}
			if (params.deleted != null) {
				record.deleted = deleted;
			}
			if (recordContents != null) {
				record.originalData = recordContents.getBytes();
			}
			
			def recordStatus = LegacyPersistenceService.saveRecord(record);
			
			if (recordStatus.successful) {
				// Saved successfully
				// We only support a json response				
				render recordStatus.record as JSON
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
		}
	}
			
	def updateRecord() {
		
		def eckId = params.eckId;

		if (eckId != null) {
			def existingRecord = LegacyPersistenceService.lookupRecordByEckId(eckId);
			if ( existingRecord != null ) {
				localUpdateRecord(existingRecord);
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
		
		if (eckId != null) {
			// Existing record..
			def existingRecord = LegacyPersistenceService.lookupRecordByEckId(eckId);
			if ( existingRecord != null ) {
				localUpdateRecord(existingRecord);
			} else {
				// No record with the specified eck ID - can't update..
				response.sendError(404, "No record found with the specified ECK Id - unable to update");
			}
		} else {
			// New record..
			saveRecord();
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
