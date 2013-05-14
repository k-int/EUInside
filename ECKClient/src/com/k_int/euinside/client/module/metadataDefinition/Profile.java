package com.k_int.euinside.client.module.metadataDefinition;

import java.util.ArrayList;

public class Profile {
	private String definition;
	private String profile;
	private ArrayList<Field> fields;
	
	public Profile() {
	}

	public String getDefinition() {
		return(definition);
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getProfile() {
		return(profile);
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public ArrayList<Field> getFields() {
		return(fields);
	}

	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}
	
	public String toString() {
		String result = "Class: Profile:\n"; 
		result += "\tdefinition: " + definition + "\n";
		result += "\tprofile: " + profile + "\n";
		for (Field field : fields) {
			result += field.toString();
		}
		return(result);
		
	}
}
