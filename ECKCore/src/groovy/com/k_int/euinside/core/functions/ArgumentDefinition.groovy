package com.k_int.euinside.core.functions

class ArgumentDefinition {

	def argName;
	def argType;
	def required;
	
	
	def getArgName() {
		return this.argName;
	}
	
	def setArgName(name) {
		this.argName = name;
	}
	
	def getArgType() {
		return this.argType;
	}
	
	def setArgType(argType) {
		this.argType = argType;
	}
	
	def getRequired() {
		return this.required;
	}
	
	def setRequired(isRequired) {
		this.required = isRequired;
	}

	def ArgumentDefinition(name, argType, required) {
		this.argName = name;
		this.argType = argType;
		this.required = required;
	}	
	
}
