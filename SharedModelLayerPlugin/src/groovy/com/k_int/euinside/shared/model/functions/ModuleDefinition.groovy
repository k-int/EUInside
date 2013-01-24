package com.k_int.euinside.shared.model.functions

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
	 * The type of the module (internal / external)
	 */
	def moduleType = "external";
	
	
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
	 * Get the type of the module
	 * @return String the module type - either 'internal' or 'external'
	 */
	def getModuleType() {
		return this.moduleType;
	}
	
	/**
	 * Set the type of the module
	 * @param moduleType The module type - either 'internal' or 'external'
	 */
	def setModuleType(String moduleType) {
		if ( "internal".equalsIgnoreCase(moduleType) )
			this.moduleType = "internal";
		else 
			this.moduleType = "external";
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
	 * @param moduleType The type of the module ('internal' or 'external')
	 * @return ModuleDefinition The module definition with the name and methods populated as appropriate
	 */
	def ModuleDefinition(name, methods, moduleType) {
		this.name = name;
		this.methods = methods;
		this.moduleType = moduleType;
	}

}
