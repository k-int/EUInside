package com.k_int.euinside.core.functions

import com.k_int.euinside.shared.model.functions.ArgumentDefinition;
import com.k_int.euinside.shared.model.functions.MethodDefinition;
import com.k_int.euinside.shared.model.functions.ModuleDefinition;

import grails.converters.JSON

class FunctionController {

	def internalFunctionsService;
	
	/**
	 * Index method - simply here to redirect to the list() method
	 */
    def index() {
		log.debug("FunctionController index method called - redirecting to list()");
		redirect(action: "list", params: params);
    }

	/**
	 * List available functions that are provided by modules (either internal to the core
	 * or external). Returns either html or json depending on requested response format	
	 * @return The available functions
	 */
	def list() {
		log.debug("FunctionController::list method called");
		
		// Use the functions service to build up the list
		def definitions = internalFunctionsService.list();
				
		// Convert the data into the relevant JSON that we want to return for HTML use
		// as we don't want to rely on it serialising the objects directly.
		def definitionsAsJson = definitions as JSON
		def asString = definitionsAsJson.toString();
		def parsedJson = JSON.parse(asString);
		
		log.debug("definitionsAsJson as string: " + asString);
		
		withFormat {
			html { return [availableCalls: parsedJson] }
			json { render definitions as JSON }
		}
	}
	
	/**
	 * Call the specified function that is provided by a module (either internal to the core
	 * or external). Returns the result of calling the module as either html or json depending
	 * on the requested response format
	 * @return The result of calling the specified function on the module
	 */
	def call() {
		log.debug("FunctionController::call method called");
		
		def responseVal = [:];
		
		// TODO
		
		withFormat {
			html responseVal
			json { render responseVal as JSON }
		}
		
	}
	
	/**
	 * Method to specify the methods, etc. provided by this controller for use
	 * in the function listing section of the system
	 * 
	 * @return Description of the 'module' and its available methods
	 */
	def static identify() {
		
		// Set up the index method
		def indexMethodDesc = new MethodDefinition("index",null,null, null);

		// Set up the list method
		def listMethodDesc = new MethodDefinition("list", null, null, null);

		// Set up the call method
		def callArgs = new LinkedHashSet<ArgumentDefinition>();
		def cmsIdArg = new ArgumentDefinition("TODO", "TODO", true);
		callArgs.add(cmsIdArg);

		def callMethodDesc = new MethodDefinition("call", callArgs, null, null);
		
		
		// Set up the 'module' description and add the various methods to it
		def lookupModule = new ModuleDefinition();
		lookupModule.name = "Lookup";
		lookupModule.methodDefinitions.add(indexMethodDesc);
		lookupModule.methodDefinitions.add(listMethodDesc);
		lookupModule.methodDefinitions.add(callMethodDesc);
		
		return lookupModule;
	}}
