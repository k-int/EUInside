package com.k_int.euinside.client.module.validation;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.validation.semantika.Errors;

public class Validate2 extends BaseModule {

	static private String buildPath() {
		return(buildPath(Module.VALIDATION2, PATH_SEPARATOR + Action.VALIDATION_VALIDATE.getName()));
	}

	static private Errors mapHttpResultToErrors(HttpResult result) {
		Errors errors = null;
		if (result.getHttpStatusCode() == HttpServletResponse.SC_OK) {
			errors = ClientJSON.readJSONString(result.getContent(), Errors.class);
		}

		return(errors);
	}
	
	static public Errors sendBytes(ArrayList<byte[]> xmlData, ArrayList<byte[]> zipData) {
		return(mapHttpResultToErrors(ClientHTTP.sendBytes(buildPath(), xmlData, zipData)));
	}

	static public Errors sendFiles(ArrayList<String> filenames) {
		return(mapHttpResultToErrors(ClientHTTP.sendFiles(buildPath(), filenames)));
	}
	
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		String[] files = arguments.getFilenames().split(";"); 
		ArrayList<String> arrayFilenames = new ArrayList<String>(Arrays.asList(files));
		Errors errors = sendFiles(arrayFilenames);

		if (errors == null) {
			System.out.println("Failed to validate file");
		} else {
			System.out.println("Result from validate2");
			System.out.println(errors.toString());
		}
		
		files = arguments.getBadFilenames().split(";"); 
		arrayFilenames = new ArrayList<String>(Arrays.asList(files));
		errors = sendFiles(arrayFilenames);

		if (errors == null) {
			System.out.println("Failed to validate file");
		} else {
			System.out.println("Result from validate2");
			System.out.println(errors.toString());
		}
	}
}
