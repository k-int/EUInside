package com.k_int.euinside.client.module.metadataDefinition;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.metadataDefinition.error.Error;
import com.k_int.euinside.client.module.metadataDefinition.error.Errors;
import com.k_int.euinside.client.module.metadataDefinition.language.Languages;
import com.k_int.euinside.client.module.metadataDefinition.profile.Field;
import com.k_int.euinside.client.module.metadataDefinition.profile.Profiles;

public class Definition extends BaseModule {

	static private String BuildProfilePath(String language, String profile, String field) {
		String definitionPath = PATH_SEPARATOR + Action.DEFINITION_PROFILES.getName();
		if ((language != null) && !language.isEmpty()){
			definitionPath += PATH_SEPARATOR + language;
			if ((profile != null) && !profile.isEmpty()) {
				definitionPath += PATH_SEPARATOR + profile;
				if ((field != null) && !field.isEmpty()) {
					definitionPath += PATH_SEPARATOR + field;
				}
			}
		}
		return(buildPath(Module.DEFINITION, definitionPath));
	}
	
	static private String BuildLanguagePath() {
		String definitionPath = PATH_SEPARATOR + Action.DEFINITION_LANGUAGES.getName();
		return(buildPath(Module.DEFINITION, definitionPath));
	}
	
	static private String BuildErrorPath(String language, String errorCode) {
		String definitionPath = PATH_SEPARATOR + Action.DEFINITION_ERRORS.getName();
		if ((language != null) && !language.isEmpty()){
			definitionPath += PATH_SEPARATOR + language;
			if ((errorCode != null) && !errorCode.isEmpty()) {
				definitionPath += PATH_SEPARATOR + errorCode;
			}
		}
		return(buildPath(Module.DEFINITION, definitionPath));
	}
	
	static public Error getError(String language, String errorCode) {
		Error result = null;
		if (((language != null) && !language.isEmpty()) &&
			((errorCode != null) && !errorCode.isEmpty())) {
			result = ClientJSON.readJSON(BuildErrorPath(language, errorCode), Error.class);
		}
		return(result);
	}

	static public Errors getErrors(String language) {
		Errors result = ClientJSON.readJSON(BuildErrorPath(language, null), Errors.class);
		return(result);
	}

	static public Errors getErrors() {
		return(getErrors(null));
	}
	
	static public Languages getLanguages() {
		Languages result = ClientJSON.readJSON(BuildLanguagePath(), Languages.class);
		return(result);
	}

	static public Field getProfileField(String language, String profile, String field) {
		Field result = null;
		if (((language != null) && !language.isEmpty()) &&
			((profile != null) && !profile.isEmpty()) &&
			((field != null) && !field.isEmpty())) {
			result = ClientJSON.readJSON(BuildProfilePath(language, profile, field), Field.class);
		}
		return(result);
	}

	static public Profiles getProfiles(String language, String profile) {
		Profiles result = ClientJSON.readJSON(BuildProfilePath(language, profile, null), Profiles.class);
		return(result);
	}

	static public Profiles getProfiles(String language) {
		return(getProfiles(language, null));
	}

	static public Profiles getProfiles() {
		return(getProfiles(null, null));
	}

	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		System.out.println("All Errors for the default language");
		Errors errors = getErrors();
		if (errors == null) {
			System.out.println("Error obtaining the errors");
		} else {
			System.out.println(errors.toString());
		}
		
		System.out.println("All Errors for the language: \"" + arguments.getLanguage() + "\"");
		errors = getErrors(arguments.getLanguage());
		if (errors == null) {
			System.out.println("Error obtaining the errors");
		} else {
			System.out.println(errors.toString());
		}
		
		System.out.println("Error \"" + arguments.getErrorCode() + "\" for language: \"" + arguments.getLanguage() + "\"");
		Error error = getError(arguments.getLanguage(), arguments.getErrorCode());
		if (error == null) {
			System.out.println("Error obtaining the errors");
		} else {
			System.out.println(error.toString());
		}
	
		System.out.println("All languages");
		Languages languages = getLanguages();
		if (languages == null) {
			System.out.println("Error obtaining the languages");
		} else {
			System.out.println(languages.toString());
		}
		
		System.out.println("All Profiles for all languages");
		Profiles profiles = getProfiles();
		if (profiles == null) {
			System.out.println("Error obtaining the profiles");
		} else {
			System.out.println(profiles.toString());
		}
		
		System.out.println("All Profiles for language: \"" + arguments.getLanguage() + "\"");
		profiles = getProfiles(arguments.getLanguage());
		if (profiles == null) {
			System.out.println("Error obtaining the profiles");
		} else {
			System.out.println(profiles.toString());
		}
		
		System.out.println("Profile: \"" + arguments.getProfile() + "\" for language: \"" + arguments.getLanguage() + "\"");
		profiles = getProfiles(arguments.getLanguage(), arguments.getProfile());
		if (profiles == null) {
			System.out.println("Error obtaining the profiles");
		} else {
			System.out.println(profiles.toString());
		}
		
		System.out.println("Field: " + arguments.getField() + "\" for Profile: \"" + arguments.getProfile() + "\" for language: \"" + arguments.getLanguage() + "\"");
		Field field = getProfileField(arguments.getLanguage(), arguments.getProfile(), arguments.getField());
		if (field == null) {
			System.out.println("Error obtaining the field");
		} else {
			System.out.println(field.toString());
		}
	}
}
