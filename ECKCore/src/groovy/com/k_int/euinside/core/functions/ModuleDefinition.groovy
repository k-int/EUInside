package com.k_int.euinside.core.functions

/**
 * Simple modelling of a module definition for use in listing available module methods, etc.
 * 
 * @author rpb rich@k-int.com
 * @version 1.0 14.01.13
 */
class ModuleDefinition {
	
	/**
	 * The name of the module
	 */
	def name;
	/**
	 * The set of methods provided by the module
	 */
	def Set<MethodDefinition> methodDefinitions = new LinkedHashSet<MethodDefinition>();
	
	
	/**
	 * Retrieve the name of the module
	 * @return String The name of the module
	 */
	def getName() {
		return this.name;
	}
	
	/**
	 * Set the name of the module
	 * @param name The name of the module
	 */
	def setName(name) {
		this.name = name;
	}
	
	/**
	 * Get the set of methods provided by the module
	 * @return Set<MethodDefinition> The set of methods provided by the module
	 */
	def getMethodDefinitions() {
		return this.methodDefinitions;
	}
	
	/**
	 * Set the set of methods provided by the module
	 * @param methodDefs The set of methods provided by the module
	 */
	def setMethodDefinitions(Set<MethodDefinition> methodDefs) {
		this.methodDefinitions = methodDefs;
	}
	
	/**
	 * Simple constructor that just returns a new empty module definition for population later
	 * @return ModuleDefinition A new unpopulated module definition
	 */
	def ModuleDefinition() {
		// Don't need to do anything
	}
	
	/**
	 * Constructor that returns a new module definition with the name and methods populated appropriately
	 * @param name The name of the module
	 * @param methods The set of methods provided by the module
	 * @return ModuleDefinition The module definition with the name and methods populated as appropriate
	 */
	def ModuleDefinition(name, methods) {
		this.name = name;
		this.methods = methods;
	}

}
