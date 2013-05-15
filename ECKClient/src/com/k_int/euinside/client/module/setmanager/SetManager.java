package com.k_int.euinside.client.module.setmanager;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.setmanager.list.BriefRecords;
import com.k_int.euinside.client.module.setmanager.status.Status;
import com.k_int.euinside.client.module.setmanager.update.Update;
import com.k_int.euinside.client.module.setmanager.validate.ValidationErrors;

/**
 * This class provides the interface for the Set Manager module
 *  
 */
public class SetManager extends BaseModule {

	static public String PROVIDER_DEFAULT = "default";
	static public String SET_DEFAULT = "default";

	static private String buildPath(String provider, String set, Action action) {
		return(buildPath(provider, set, action, null));
	}
	
	static private String buildPath(String provider, String set, Action action, ArrayList<BasicNameValuePair> attributes) {
		if (provider == null) {
			provider = PROVIDER_DEFAULT;
		}
		if (set == null) {
			set = SET_DEFAULT;
		}
		String modulePath = PATH_SEPARATOR + provider + PATH_SEPARATOR + set + PATH_SEPARATOR + action.getName();
		return(buildPath(Module.SET_MANAGER, modulePath, attributes));
	}
	
	static public Status getStatus() {
		return(getStatus(null, null, 0));
	}
	
	static public Status getStatus(String provider, String set) {
		return(getStatus(provider, set, 0));
	}
	
	static public BriefRecords getList(String provider, String set) {
		BriefRecords result = null;
		String path = buildPath(provider, set, Action.SET_MANAGER_LIST);

		result = ClientJSON.readJSON(path, BriefRecords.class);
		return(result);
	}

	static public Status getStatus(String provider, String set, int historyItems) {
		Status result = null;
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		if (historyItems > 0) {
			attributes.add(new BasicNameValuePair(Attribute.HISTORY_ITEMS.getName(), Integer.toString(historyItems)));
		}
		String path = buildPath(provider, set, Action.SET_MANAGER_STATUS, attributes);

		result = ClientJSON.readJSON(path, Status.class);
		return(result);
	}

	static public HttpResult commit(String provider, String set) {
		// Just call update
		return(update(provider, set, null, true, false, null));
	}

	static public HttpResult update(String provider, String set, ArrayList<String> filenames, boolean commit, boolean deleteAll, String recordsToDelete) {
		// Are we going to commit or just update
		String path = buildPath(provider, set, commit ? Action.SET_MANAGER_COMMIT : Action.SET_MANAGER_UPDATE);
		return(Update.sendFiles(path, filenames, deleteAll, recordsToDelete));
	}

	static public HttpResult update(String provider, String set, ArrayList<byte[]> xmlData, ArrayList<byte[]> zipData, boolean commit, boolean deleteAll, String recordsToDelete) {
		// Are we going to commit or just update
		String path = buildPath(provider, set, commit ? Action.SET_MANAGER_COMMIT : Action.SET_MANAGER_UPDATE);
		return(Update.sendBytes(path, xmlData, zipData, deleteAll, recordsToDelete));
	}

	static public ValidationErrors getValidate(String provider, String set) {
		ValidationErrors result = null;
		String path = buildPath(provider, set, Action.SET_MANAGER_VALIDATE);

		result = ClientJSON.readJSON(path, ValidationErrors.class);
		return(result);
	}

	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		System.out.println("Using provider: \"" + arguments.getProvider() + "\" and set: \"" + arguments.getSet() + "\"");

		if (arguments.isRunAll() || arguments.isRunUpdate()) {
			System.out.println("Calling Action update");
			
			// Parse the filename into an ArrayList
			String[] files = arguments.getFilenames().split(";"); 
			HttpResult result = update(arguments.getProvider(), arguments.getSet(), new ArrayList<String>(Arrays.asList(files)), false, arguments.isDeleteAll(), arguments.getRecordsToDelete());
			System.out.println("Result from update: " + result.toString() + "\n");
		}
		
		if (arguments.isRunAll() || arguments.isRunStatus()) {
			System.out.println("Calling Action status");
			Status status = getStatus(arguments.getProvider(), arguments.getSet(), 10);
			if (status == null) {
				System.out.println("Failed to get hold of the status\n");
			} else {
				System.out.println("Result:\n" + status.toString());
			}
		}
		
		if (arguments.isRunAll() || arguments.isRunList()) {
			System.out.println("Calling Action list");
			BriefRecords briefRecords = getList(arguments.getProvider(), arguments.getSet());
			if (briefRecords == null) {
				System.out.println("Failed to get hold of the list\n");
			} else {
				System.out.println("Result:\n" + briefRecords.toString());
			}
		}
		
		if (arguments.isRunAll() || arguments.isRunValidate()) {
			System.out.println("Calling Action validate");
			ValidationErrors validationErrors = getValidate(arguments.getProvider(), arguments.getSet());
			if (validationErrors == null) {
				System.out.println("Failed to get hold of the validation errors\n");
			} else {
				System.out.println("Result:\n" + validationErrors.toString());
			}
		}

		if (arguments.isRunAll() || arguments.isRunCommit()) {
			System.out.println("Calling Action commit");
			HttpResult result = commit(arguments.getProvider(), arguments.getSet());
			System.out.println("Result from update: " + result.toString() + "\n");
		}
	}
}
