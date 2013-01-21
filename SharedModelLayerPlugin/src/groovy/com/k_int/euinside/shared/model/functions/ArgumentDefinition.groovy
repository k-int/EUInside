package com.k_int.euinside.shared.model.functions

/**
 * A class to handle the definition of method arguments
 * @author rpb rich@k-int.com
 * @version 1.0 14.01.13
 *
 */
class ArgumentDefinition {

	/**
	 * The argument name
	 */
	def argName;
	/**
	 * The type of the argument (string, boolean, etc.)
	 */
	def argType;
	/**
	 * Whether or not the argument is required
	 */
	def required;
	
	/**
	 * Retrieve the specified argument name 
	 * @return The name of the argument
	 */
	def getArgName() {
		return this.argName;
	}
	
	/**
	 * Set the name of the argument
	 * @param name The name of the argument
	 */
	def setArgName(name) {
		this.argName = name;
	}
	
	/**
	 * Retrieve the argument type
	 * @return String The argument's type
	 */
	def getArgType() {
		return this.argType;
	}
	
	/**
	 * Set the argument type
	 * @param argType The type of the argument
	 */
	def setArgType(argType) {
		this.argType = argType;
	}
	
	/**
	 * Retrieve whether or not the argument is required to be specified
	 * @return boolean Whether or not the argument must be specified when calling the relevant method
	 */
	def getRequired() {
		return this.required;
	}
	
	/**
	 * Set whether or not the argument is required to be specified
	 * @param isRequired Whether or not the argument must be specified when calling the relevant method
	 */
	def setRequired(isRequired) {
		this.required = isRequired;
	}

	/**
	 * ArgumentDefinition constructor that takes the name, type and required status and populates a new object as appropriate
	 * @param name The name of the argument
	 * @param argType The argument type
	 * @param required Whether or not the argument is required when used in the relevant method
	 */
	def ArgumentDefinition(name, argType, required) {
		this.argName = name;
		this.argType = argType;
		this.required = required;
	}	
	
}
