package com.k_int.euinside.core.functions

class ModuleDefinition {
	
	def name;
	def Set<MethodDefinition> methodDefinitions = new LinkedHashSet<MethodDefinition>();
	
	
	def getName() {
		return this.name;
	}
	
	def setName(name) {
		this.name = name;
	}
	
	def getMethodDefinitions() {
		return this.methodDefinitions;
	}
	
	def setMethodDefinitions(Set<MethodDefinition> methodDefs) {
		this.methodDefinitions = methodDefs;
	}
	
	def ModuleDefinition() {
		// Don't need to do anything
	}
	
	def ModuleDefinition(name, methods) {
		this.name = name;
		this.methods = methods;
	}

}
