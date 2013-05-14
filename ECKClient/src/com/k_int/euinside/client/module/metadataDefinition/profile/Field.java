package com.k_int.euinside.client.module.metadataDefinition.profile;

public class Field {

	private String definition;
	private String example;
	private String field;
	private String xpath;
	
	public Field() {
	}
	
	public String getDefinition() {
		return(definition);
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public String getExample() {
		return(example);
	}
	
	public void setExample(String example) {
		this.example = example;
	}
	
	public String getField() {
		return(field);
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getXpath() {
		return(xpath);
	}
	
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	
	public String toString() {
		String result = "Class: Field:\n"; 
		result += "\tdefinition: " + definition + "\n";
		result += "\texample: " + example + "\n";
		result += "\tfield: " + field + "\n";
		result += "\txpath: " + xpath + "\n";
		return(result);
	}
}
