package com.k_int.euinside.client.module.setmanager.update;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.module.Attribute;

public class Update {

	static private ArrayList<BasicNameValuePair> buildattributeArray(boolean deleteAll, ArrayList<String> recordsToDelete) {
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		
		// Add the possible attributes to our attribute array
		if (deleteAll) {
			attributes.add(new BasicNameValuePair(Attribute.DELETE_ALL.getName(), "yes"));
		}
		if ((recordsToDelete != null) && !recordsToDelete.isEmpty()) {
			String commaSeparated = "";
			for (String recordId : recordsToDelete) {
				if (!commaSeparated.isEmpty()) {
					commaSeparated += ",";
				}
				commaSeparated += recordId;
			}
			attributes.add(new BasicNameValuePair(Attribute.DELETE.getName(), commaSeparated));
		}
		
		// Now we can make the http call
		return(attributes);
	}

	static public HttpResult sendBytes(String path, ArrayList<byte[]> xmlData, ArrayList<byte[]> zipData, boolean deleteAll, ArrayList<String> recordsToDelete) {
		return(ClientHTTP.sendBytes(path, xmlData, zipData, buildattributeArray(deleteAll, recordsToDelete)));
	}

	static public HttpResult sendFiles(String path, ArrayList<String> filenames, boolean deleteAll, ArrayList<String> recordsToDelete) {
		return(ClientHTTP.sendFiles(path, filenames, buildattributeArray(deleteAll, recordsToDelete)));
	}
}
