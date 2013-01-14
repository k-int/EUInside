package com.k_int.euinside.core.functions

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
	 * Simple constructor to return an empty method definition to populate later
	 * @return MethodDefinition an empty method definition	
	 */
	def MethodDefinition() {
		// Don't need to do anything..	
	}
	
	/**
	 * Constructor to create and return a method definition with the name and arguments
	 * populated as specified
	 * @param name The name of the method
	 * @param arguments The set of argument definitions
	 * @return MethodDefinition the new method definition with the name and arguments set as appropriate
	 */
	def MethodDefinition(name, arguments) {
		this.methodName = name;
		this.arguments = arguments;
	}
}
