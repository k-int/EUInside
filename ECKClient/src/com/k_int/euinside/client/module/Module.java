package com.k_int.euinside.client.module;

public enum Module {

	DEFINITION("Definition"),
	PID_GENERATE("PIDGenerate"),
	SET_MANAGER("SetManager/Set"),
	VALIDATION("Validation"),   // monguz
	VALIDATION2("Validation2"); // Semantika

	private String rootPath;
	
	Module(String rootPath) {
		this.rootPath = rootPath;
	}
	
	public String getRootPath() {
		return(rootPath);
	}
}
