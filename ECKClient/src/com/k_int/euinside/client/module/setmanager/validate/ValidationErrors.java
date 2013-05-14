package com.k_int.euinside.client.module.setmanager.validate;

import java.util.ArrayList;

public class ValidationErrors extends ArrayList<ValidationError>{

	private static final long serialVersionUID = 1234501L;

	public ValidationErrors() {
	}

	public String toString() {
		String result = "Class: ValidationErrors\n";
		for (ValidationError validationError : this) {
			result += validationError.toString();
		}
		return(result);
	}
}
