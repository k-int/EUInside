package com.k_int.euinside.client.module;

public enum Module {

	PID_GENERATE("PIDGenerate"),
	SET_MANAGER("SetManager/Set"),
	VALIDATION("Validation"),
	VALIDATION2("Validation2");

	private String rootPath;
	
	Module(String rootPath) {
		this.rootPath = rootPath;
	}
	
	public String getRootPath() {
		return(rootPath);
	}
}
