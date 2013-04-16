package com.k_int.euinside.setmanager.controllers

import grails.converters.JSON

import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.k_int.euinside.setmanager.datamodel.ProviderSet
import com.k_int.euinside.setmanager.datamodel.SetQueuedAction;
import com.k_int.euinside.setmanager.persistence.PersistenceService;
import com.k_int.euinside.setmanager.action.StatusService;
import com.k_int.euinside.setmanager.action.UpdateService;

class SetController {

	def PersistenceService;
	def StatusService;
	def UpdateService;
	
//    def index() { }
	
	def commit() {
		
	}
	
	def list() {
		
	}
	
	def preview() {
		
	}
	
	def record() {
		
	}
	
	def statistics() {
		
	}
	
	def status() {
		ProviderSet set = determineSet();
		if (set != null) {
			render StatusService.setStatus(set) as JSON;
		}
	}

	private def determineSet() {
		ProviderSet set = null;
		String providerCode = params.provider;
		def validIPResult = PersistenceService.checkValidIP(providerCode, request.getRemoteHost());
		if (validIPResult.validIP) {
			// We have a valid IP address so do something ...
			String setCode = params.setname;
			set = PersistenceService.lookupProviderSet(providerCode, setCode, true, params.setDescription);
			if (set == null) {
				response.sendError(403, "Error obtaining set")
			}
		} else {
			response.sendError(403, validIPResult.message);
		}
		return(set);
	}

	def update() {
		ProviderSet set = determineSet();
		if (set != null) {
			// We have a set so we can continue
			def files = null; 
			if (request instanceof org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest) {
				// Get hold of the supplied files 
				files = request.getFileMap();
			}
				
			// Queue the action to be processed later
			def deleteAll = params.deleteAll;
			UpdateService.queue(set, files, ((deleteAll != null) && deleteAll.equalsIgnoreCase("yes")), params.delete);

			// Inform the caller that we have queued for processing
			response.sendError(202, "Request has been queued for processing");
		}
	}
	
	def validate() {
		
	}

	def test() {
		ProviderSet set = determineSet();
		if (set != null) {
			// throws up a test page
		}
	}
}
