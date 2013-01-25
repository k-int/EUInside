package com.k_int.euinside.core.wrapper.persistence

import groovyx.net.http.HTTPBuilder;

class KiPersistenceWrapperService {

	@javax.annotation.PostConstruct
	def init() {
		log.debug("KiPersistenceWrapperService::init called");
	}
	
	// Config settings
	def static persistenceModuleBaseUrl = "http://localhost:28080/KIPersistence";
	def static callBase = "/persistence/";
	
	def lookupRecordByEckId(params) {
		
		def eckId = params.eckId;
		def method = "lookupRecordByEckId";
		def format = "json";
		def action = "get"
		def args = ["eckId":eckId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def lookupRecordByCmsId(params) {
		
		def cmsId = params.cmsId;
		
		def method = "lookupRecordByCmsId";
		def format = "json";
		def action = "get"
		def args = ["cmsId":cmsId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def lookupRecordByPersistentId(params) {
		
		def persistentId = params.persistentId;
		
		def method = "lookupRecordByPersistentId";
		def format = "json";
		def action = "get"
		def args = ["persistentId":persistentId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def lookupRecord(params) {
		
		def id = params.id;
		def idType = params.idType;
		
		def method = "lookupRecord";
		def format = "json";
		def action = "get"
		def args = ["id":id, "idType":idType];
		
		def httpResponse = makeHttpCall(method, format, action, args);
	
		return httpResponse;
	}
	
	def lookupRecordsAnyIdType(params) {
		
		def id = params.id;
		
		def method = "lookupRecordsAnyIdType";
		def format = "json";
		def action = "get"
		def args = ["id":id];
		
		def httpResponse = makeHttpCall(method, format, action, args);
	
		return httpResponse;
	}
	
	def lookupRecords(params) {
		
		def cmsId = params.cmsId;
		def persistentId = params.persistentId;
		def eckId = params.eckId;
		
		def method = "lookupRecords";
		def format = "json";
		def action = "get"
		def args = ["cmsId":cmsId, "persistentId": persistentId, "eckId":eckId];
		
		def httpResponse = makeHttpCall(method, format, action, args);
	
		return httpResponse;
	}
	
	def createRecord(params) {
		
		def method = "createRecord";
		def format = "json";
		def action = "post"
		def args = [:];
		
		def httpResponse = makeHttpCall(method, format, action, args);
		return httpResponse;
	}
	
	def saveRecord(params) {
		
		def record = params.record;
		return saveRecord(record.cmsId, record.persistentId, record.deleted, record.recordContents);
	}
	
	def saveRecord(cmsId, persistentId, deleted, recordContents) {
		
		def method = "saveRecord";
		def format = "json";
		def action = "post";
		def args = ["cmsId":cmsId, "persistentId":persistentId, "deleted":deleted, "recordContents":recordContents, "fileArgs":["recordContents"]];
		
		def httpResponse = makeHttpCallWithFile(method, format, action, args);
		return httpResponse;
	}
	
	def updateRecord(params) {
		
		def record = params.record;
		
		return updateRecord(record.id, record.cmsId, record.persistentId, record.deleted, record.recordContents);	
	}
	
	def updateRecord(eckId, cmsId, persistentId, deleted, recordContents) {
		
		def method = "updateRecord";
		def format = "json";
		def action = "post";
		def args = ["eckId": eckId, "cmsId":cmsId, "persistentId":persistentId, "deleted":deleted, "recordContents":recordContents, "fileArgs":["recordContents"]];
		
		def httpResponse = makeHttpCallWithFile(method, format, action, args);
		return httpResponse;
	}
	
	def saveOrUpdateRecord(params) {
		
		def record = params.record;
		return saveOrUpdateRecord(record.id, record.cmsId, record.persistentId, record.deleted, record.recordContents);	
	}
	
	def saveOrUpdateRecord(eckId, cmsId, persistentId, deleted, recordContents) {
		
		def method = "saveOrUpdateRecord";
		def format = "json";
		def action = "post";
		def args = ["eckId": eckId, "cmsId":cmsId, "persistentId":persistentId, "deleted":deleted, "recordContents":recordContents, "fileArgs":["recordContents"]];
		
		def httpResponse = makeHttpCallWithFile(method, format, action, args);
		return httpResponse;
	}
	
	def identify(params) {
		
		def callBaseOveride = "/";
		def method = "identify";
		def format = "json";
		def action = "get";
		def args = [:];
		
		def returnValue = makeHttpCall(method, format, action, args, callBaseOveride);
	}
	
	def makeHttpCall(method, format, action, args) {
		return makeHttpCall(method, format, action, args, null);
	}
	
	def makeHttpCall(method, format, action, args, callBaseOveride) {
		def returnValue;
		
		def url = persistenceModuleBaseUrl + callBase;
		if ( callBaseOveride != null ) {
			// We want to over ride the call base value (to call something like identify)
			url = persistenceModuleBaseUrl + callBaseOveride;
		}
		
		def path = method;
		if ( "json".equals(format) ) {
			path = path + ".json";
		}
		
		log.debug("making HTTP call to url: " + url + " path: " + path);
		
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
	
	def makeHttpCallWithFile(method, format, action, args, callBaseOveride) {
		def returnValue;
		
		def url = persistenceModuleBaseUrl + callBase;
		if ( callBaseOveride != null ) {
			// We want to over ride the call base value (to call something like identify)
			url = persistenceModuleBaseUrl + callBaseOveride;
		}
		
		def path = method;
		if ( "json".equals(format) ) {
			path = path + ".json";
		}
		
		log.debug("making HTTP call to url: " + url + " path: " + path);
		
		def http = new HTTPBuilder(url);
		
		// Work out which args should be passed as files
		def fileArgs = args.fileArgs;
		args.remove("fileArgs");
		
		def actualFiles = [:];
		
		fileArgs.each() { aFileArg ->
				
			def fileKey = aFileArg;
			def fileContents = args[fileKey];
			args.remove(fileKey);
		}
		
		// A POST is required
		def postBody = [:];
		// Add the non-file args
		postBody.putAll(args);
		// Add the file args
		// TODO
		
		// I think we need to do something like:
		// http://roshandawrani.wordpress.com/2011/02/12/grails-functional-testing-a-file-upload-using-httpbuilder-spock/ 
		// to attach the files
// FIXME
		
		http.post(path: path, body: postBody) { httpResp, json ->
			
			log.debug("POST httpResp.statusLine.statusCode = " + httpResp.statusLine.statusCode);
			log.debug("returned json = " + json.toString());
			
			if ( "null".equals(json.toString()) )
				returnValue = null;
			else
				returnValue = json;
		}
		
		return returnValue;
	}
}
