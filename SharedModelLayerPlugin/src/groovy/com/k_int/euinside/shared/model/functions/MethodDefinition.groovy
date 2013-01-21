package com.k_int.euinside.shared.model.functions

/**
 * Simple modelling of method calls provided by a module
 * @author rpb rich@k-int.com
 * @version 1.0 14.01.13
 */
class MethodDefinition {

	/**
	 * The name of the method within the module
	 */
	def methodName;
	/**
	 * The set of arguments that the method takes (in order)
	 */
	def Set<ArgumentDefinition> arguments = new LinkedHashSet<ArgumentDefinition>();
	/**
	 * The return type of the method
	 */
	def returnType;
	/**
	 * A description of the method's functino
	 */
	def description;
	
	
	/**
	 * Retrieve the name of the method
	 * @return String the name of the method
	 */
	def getMethodName() {
		return this.methodName;
	}
	
	/**
	 * Set the name of the method
	 * @param name The name of the method to set
	 */
	def setMethodName(String name) {
		this.methodName = name;
	}

	/**
	 * Retrieve the set of arguments that the method takes. The arguments are stored and returned in order.	
	 * @return Set<ArgumentDefinition> The set of arguments that the method takes in order
	 */
	def getArguments() {
		return this.arguments;
	}
	
	/**
	 * Set the set of arguments that the method takes in order.
	 * @param args The set of arguments that the method takes in the required order
	 */
	def setArguments(Set<ArgumentDefinition> args) {
		this.arguments = args;
	}

	/**
	 * Get the return type for the method
	 * @return String The return type for the method
	 */
	def getReturnType() {
		return this.returnType;
	}
	
	/**
	 * Set the return type for the method
	 * @param retType The return type that the method will return when called
	 */
	def setReturnType(String retType) {
		this.returnType = retType;
	}
	
	/**
	 * Retrieve the description assigned to the method
	 * @return String the description of the method function
	 */
	def getDescription() {
		return this.description;
	}
	
	/**
	 * Set the description of the method's function
	 * @param desc The description of the method's function
	 */
	def setDescription(desc) {
		this.description = desc;
	}
		
	/**
	 * Constructor to create and return a method definition with the name and arguments
	 * populated as specified
	 * @param name The name of the method
	 * @param arguments The set of argument definitions
	 * @param retType The return type of the method
	 * @param desc A description of the method's function
	 * @return MethodDefinition the new method definition with the name and arguments set as appropriate
	 */
	def MethodDefinition(name, arguments, retType, desc) {
		this.methodName = name;
		this.arguments = arguments;
		this.returnType = retType;
		this.description = desc;
	}
}
