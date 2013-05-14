package com.k_int.euinside.client.module.metadataDefinition;

import java.util.ArrayList;

public class Languages extends ArrayList<Language> {
	private static final long serialVersionUID = 1234504L;

	public String toString() {
		String result = "Class: Languages:\n"; 
		for (Language language : this) {
			result += language.toString();
		}
		return(result);
	}
}
