package com.k_int.euinside.core.functions

import grails.converters.JSON

class FunctionController {

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
	 * @return Tje list of available functions
	 */
	def list() {
		log.debug("FunctionController::list method called");
		
		def args1 = ["argumentName":"arg1", "argumentType":"string", "required": true];
		def args2 = ["argumentName":"arg2", "argumentType":"integer", "required": false];
		def args3 = ["argumentName":"arg3", "argumentType":"boolean", "required": false];
		def args4 = ["argumentName":"arg4", "argumentType":"boolean", "required": false];
		
		def method1 = ["methodName": "method1-1", "arguments": [args1,args2,args3]];
		def method2 = ["methodName": "method1-2", "arguments": [args1,args3]];
		def method3 = ["methodName": "method1-3", "arguments": [args2, args4]];
		def method4 = ["methodName": "method1-4", "arguments": [args1,args4]];
		
		def module1 = ["name":"module1", "methods":[method1,method2,method3,method4]]
		def module2 = ["name":"module2", "methods":[method2, method4]]
		def module3 = ["name":"module3", "methods":[method3,method1]]

		def availableCalls = [module1, module2, module3]		
//		def availableCalls = [module1]		
		// TODO  - what to do about call signatures, etc.?
		
		withFormat {
			html { return [availableCalls: availableCalls] }
			json { render availableCalls as JSON }
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
}
