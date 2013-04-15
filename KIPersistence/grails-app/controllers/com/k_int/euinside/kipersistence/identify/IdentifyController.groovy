package com.k_int.euinside.kipersistence.identify

import com.k_int.euinside.shared.model.functions.ModuleDefinition
import grails.converters.JSON;

import com.k_int.euinside.kipersistence.persistence.PersistenceController

/**
 * Controller to manage identify requests from the ECK Core, etc.
 * @author rpb rich@k-int.com
 * @version 1.0 15.01.13
 */
class IdentifyController {

    def index() { 

		def persistenceModule = PersistenceController.identify();
		
		// Convert the data into the relevant JSON that we want to return for HTML use
		// as we don't want to rely on it serialising the objects directly.
		def persistenceAsJson = persistenceModule as JSON
		def asString = persistenceAsJson.toString();
		asString = asString.replace("<","&lt;");
		asString = asString.replace(">","&gt;");
		def parsedJson = JSON.parse(asString);
		
		log.debug("persistenceAsJson as string: " + asString);
		
		withFormat {
			html { return [availableCalls: parsedJson] }
			json { render persistenceModule as JSON }
		}
	}
	
}
