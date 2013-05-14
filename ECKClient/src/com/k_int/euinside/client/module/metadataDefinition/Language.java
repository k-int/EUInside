package com.k_int.euinside.client.module.metadataDefinition;

public class Language {
	String code;
	String language;
	
	public Language() {
	}
	
	public String getCode() {
		return(code);
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getLanguage() {
		return(language);
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String toString() {
		String result = "Class: Language:\n"; 
		result += "\tcode: " + code + "\n";
		result += "\tlanguage: " + language + "\n";
		return(result);
	}

}
