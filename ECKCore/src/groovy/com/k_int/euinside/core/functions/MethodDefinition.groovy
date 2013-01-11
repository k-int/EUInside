package com.k_int.euinside.core.functions

class MethodDefinition {

	def methodName;
	def Set<ArgumentDefinition> arguments = new LinkedHashSet<ArgumentDefinition>();
	
	def getMethodName() {
		return this.methodName;
	}
	
	def setMethodName(String name) {
		this.methodName = name;
	}
	
	def getArguments() {
		return this.arguments;
	}
	
	def setArguments(Set<ArgumentDefinition> args) {
		this.arguments = args;
	}
	
	def MethodDefinition() {
		// Don't need to do anything..	
	}
	
	def MethodDefinition(name, arguments) {
		this.methodName = name;
		this.arguments = arguments;
	}
}
