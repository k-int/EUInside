package com.k_int.euinside.client.module.metadataDefinition;

import java.util.ArrayList;

public class Profiles extends ArrayList<Profile> {
	private static final long serialVersionUID = 1234504L;
	
	public String toString() {
		String result = "Class: Profiles:\n"; 
		for (Profile profile : this) {
			result += profile.toString();
		}
		return(result);
	}
}
