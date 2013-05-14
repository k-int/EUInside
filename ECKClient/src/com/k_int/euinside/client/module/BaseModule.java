package com.k_int.euinside.client.module;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.BaseClient;

public class BaseModule {

	static public String PATH_SEPARATOR = "/";
	static public String QUERY_START = "?";

	static public String getCoreBaseURL() {
		return(BaseClient.getCoreBaseURL());
	}
	
	static public void setCoreBaseURL(String coreBaseURL) {
		BaseClient.setCoreBaseURL(coreBaseURL);
	}

	static public String buildPath(Module module, ArrayList<BasicNameValuePair> attributes) {
		return(buildPath(module, null, attributes));
	}
	
	static public String buildPath(Module module, String modulePath) {
		return(buildPath(module, modulePath, null));
	}
	
	static public String buildPath(Module module, String modulePath, ArrayList<BasicNameValuePair> attributes) {
		String path = module.getRootPath();
		if (modulePath != null) {
			path += modulePath;
		}
		
		if (attributes != null) {
			String paramString = URLEncodedUtils.format(attributes, StandardCharsets.UTF_8);
			if ((paramString != null) && !paramString.isEmpty()) {
				path += QUERY_START + paramString;
			}
		}
		return(path);
	}
	
	static public CommandLineArguments parseCommandLineArguments(String [] args) {
		return(new CommandLineArguments(args));
	}
}
