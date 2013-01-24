package com.k_int.euinside.core.wrapper.persistence

import com.k_int.euinside.core.dto.RecordDTO
import groovyx.net.http.HTTPBuilder;

class KiPersistenceWrapperService {

	@javax.annotation.PostConstruct
	def init() {
		log.debug("KiPersistenceWrapperService::init called");
	}
	
	// Config settings
	def persistenceModuleBaseUrl = "http://localhost:28080/KIPersistence";
	def callBase = "/persistence/";
	
	def lookupRecordByEckId(eckId) {
		
		def method = "lookupRecordByEckId";
		def format = "json";
		def action = "get"
		def args = ["eckId":eckId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def lookupRecordByCmsId(cmsId) {
		
		def method = "lookupRecordByCmsId";
		def format = "json";
		def action = "get"
		def args = ["cmsId":cmsId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def lookupRecordByPersistentId(persistentId) {
		
		def method = "lookupRecordByPersistentId";
		def format = "json";
		def action = "get"
		def args = ["persistentId":persistentId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def lookupRecord(id, idType) {
		def method = "lookupRecord";
		def format = "json";
		def action = "get"
		def args = ["id":id, "idType":idType];
		
		def httpResponse = makeHttpCall(method, format, action, args);
	
		return httpResponse;
	}
	
	def lookupRecordsAnyIdType(id) {
		
		def method = "lookupRecordsAnyIdType";
		def format = "json";
		def action = "get"
		def args = ["id":id];
		
		def httpResponse = makeHttpCall(method, format, action, args);
	
		return httpResponse;
	}
	
	def lookupRecords(cmsId, persistentId, eckId) {
		
		def method = "lookupRecords";
		def format = "json";
		def action = "get"
		def args = ["cmsId":cmsId, "persistentId": persistentId, "eckId":eckId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
	
		return httpResponse;
	}
	
	def createRecord() {
		
		def method = "createRecord";
		def format = "json";
		def action = "post"
		def args = [:];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def saveRecord(record) {
		return saveRecord(record.cmsId, record.persistentId, record.deleted, record.recordContents);
	}
	
	def saveRecord(cmsId, persistentId, deleted, recordContents) {
		
		def method = "saveRecord";
		def format = "json";
		def action = "post";
		def args = ["cmsId":cmsId, "persistentId":persistentId, "deleted":deleted, "recordContents":recordContents];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def updateRecord(record) {
		return updateRecord(record.id, record.cmsId, record.persistentId, record.deleted, record.recordContents);	
	}
	
	def updateRecord(eckId, cmsId, persistentId, deleted, recordContents) {
		
		def method = "updateRecord";
		def format = "json";
		def action = "post";
		def args = ["eckId": eckId, "cmsId":cmsId, "persistentId":persistentId, "deleted":deleted, "recordContents":recordContents];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def saveOrUpdateRecord(record) {
		return saveOrUpdateRecord(record.id, record.cmsId, record.persistentId, record.deleted, record.recordContents);	
	}
	
	def saveOrUpdateRecord(eckId, cmsId, persistentId, deleted, recordContents) {
		
		def method = "saveOrUpdateRecord";
		def format = "json";
		def action = "post";
		def args = ["eckId": eckId, "cmsId":cmsId, "persistentId":persistentId, "deleted":deleted, "recordContents":recordContents];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def makeHttpCall(method, format, action, args) {
		
		log.debug("KiPersistenceWrapperService::makeHTTPCall method called");
		
		def returnValue;
		
		def url = persistenceModuleBaseUrl + callBase;
		def path = method;
		if ( "json".equals(format) ) {
			path = path + ".json";
		}
		log.debug("Method URL worked out to be: " + url + " and path: " + path);
		
		def http = new HTTPBuilder(url);
		
		if ( "post".equals(action) ) {
			// A POST is required
			def postBody = [:];
			postBody.putAll(args);

			http.post(path: path, body: postBody) { httpResp, json ->
				
				log.debug("POST httpResp.statusLine.statusCode = " + httpResp.statusLine.statusCode);
				log.debug("returned json = " + json.toString());
				
				if ( "null".equals(json.toString()) )
					returnValue = null;
				else
					returnValue = json;
			}			
		} else {
			// Perform a GET
			def query = [:];
			query.putAll(args);
			
			http.get(path: path, query: query) { httpResp, json ->
				
				log.debug("GET httpResp.statusLine.statusCode = " + httpResp.statusLine.statusCode);
				log.debug("Returned json = " + json.toString());
				
				if ( "null".equals(json.toString()) )
					returnValue = null;
				else
					returnValue = json;
			}
		}
		
		return returnValue;
	}
	
	// FIXME - STILL NEED TO FIX THE MULTIPLE LOOKUP RECORDS METHODS..
	
}
