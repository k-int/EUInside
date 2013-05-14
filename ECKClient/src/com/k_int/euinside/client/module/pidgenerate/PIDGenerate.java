package com.k_int.euinside.client.module.pidgenerate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;

public class PIDGenerate extends BaseModule {
	private static Log log = LogFactory.getLog(PIDGenerate.class);

	static public String generate(String institutionURL, String recordType, String accessionNumber) {
		return(generate(new PIDComponents(institutionURL, recordType, accessionNumber)));
	}
	
	static public String generate(PIDComponents components) {
		String result;
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		attributes.add(new BasicNameValuePair(Attribute.ACCESSION_NUMBER.getName(), components.getAccessionNumber()));
		attributes.add(new BasicNameValuePair(Attribute.INSTITUTION_URL.getName(), components.getInstitutionURL()));
		attributes.add(new BasicNameValuePair(Attribute.RECORD_TYPE.getName(), components.getRecordType()));
		String path = buildPath(Module.PID_GENERATE, PATH_SEPARATOR + Action.PID_GENERATE_GENERATE.getName(), attributes);

		result = ClientJSON.readJSON(path, String.class);
		
		return(result);
	}
	
	static public PIDComponents lookUp(String pid) {
		PIDComponents result = null;
		try {
			String path = buildPath(Module.PID_GENERATE, PATH_SEPARATOR + Action.PID_GENERATE_LOOKUP.getName() + PATH_SEPARATOR + URLEncoder.encode(pid, StandardCharsets.UTF_8.toString()));
			result = ClientJSON.readJSON(path, PIDComponents.class);
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException encodeing pid: \"" + pid + "\"", e);
		}
		
		return(result);
	}
	
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		System.out.println("Using accessionNumber: \"" + arguments.getAccessionNumber() + "\"");
		System.out.println("      institutionURL: \"" + arguments.getInstitutionURL() + "\"");
		System.out.println("      recordType: \"" + arguments.getRecordType() + "\"");

		String result = generate(arguments.getInstitutionURL(), arguments.getRecordType(), arguments.getAccessionNumber());
		System.out.println("Result from generate: " + result.toString() + "\n");
		
		if (arguments.getPid().isEmpty()) {
			arguments.setPid(result);
		}

		System.out.println("Looking up PID: \"" + arguments.getPid() + "\"");
		PIDComponents pidComponents = lookUp(arguments.getPid());
		if (pidComponents == null) {
			System.out.println("Failed to lookup pid");
		} else {
			System.out.println("Result from lookup");
			System.out.println(pidComponents.toString());
		}
	}
}
