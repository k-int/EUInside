package com.k_int.euinside.client.module.metadataDefinition.error;

import java.util.ArrayList;

public class Errors extends ArrayList<Error> {
	private static final long serialVersionUID = 1234503L;
	
	public String toString() {
		String result = "Class: Errors:\n"; 
		for (Error error : this) {
			result += error.toString();
		}
		return(result);
	}
}
