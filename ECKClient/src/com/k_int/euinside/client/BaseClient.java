package com.k_int.euinside.client;


public class BaseClient {
	private static String coreBaseURL = null;
	
	static public String getCoreBaseURL() {
		return(coreBaseURL);
	}
	
	static public void setCoreBaseURL(String coreBaseURL) {
		BaseClient.coreBaseURL = coreBaseURL;
	}

	static public String buildURL(String path) {
		return(coreBaseURL + "/" + path);
	}
}
