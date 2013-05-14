package com.k_int.euinside.client.module;

import com.k_int.euinside.client.module.setmanager.SetManager;

public class CommandLineArguments {
	String provider = SetManager.PROVIDER_DEFAULT;
	String set = SetManager.SET_DEFAULT;
	String coreBaseURL = "http://euinside.k-int.com/ECKCore2";
	boolean runCommit = false;
	boolean runList = false;
	boolean runStatus = false;
	boolean runUpdate = false;
	boolean runValidate = false;
	boolean runAll = false;
	String filenames = "";
	String badFilenames = "";
	boolean deleteAll = false;
	String recordsToDelete = null;
	String institutionURL = "";
	String recordType = "";
	String accessionNumber = "";
	String pid = "";

	public CommandLineArguments(String [] args) {
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "-accessionNumber":
					i++;
					accessionNumber = args[i];
					break;
					
				case "-all":
					runAll = true;
					break;
					
				case "-badFilenames":
					i++;
					badFilenames = args[i];
					break;
					
				case "-commit":
					runCommit = true;
					break;
					
				case "-coreBaseURL":
					i++;
					coreBaseURL = args[i];
					break;
					
				case "-deleteAll":
					deleteAll = true;
					break;
					
				case "-filenames":
					i++;
					filenames = args[i];
					break;
					
				case "-institutionURL":
					i++;
					institutionURL = args[i];
					break;
					
				case "-list":
					runList = true;
					break;
					
				case "-pid":
					i++;
					pid = args[i];
					break;
					
				case "-provider":
					i++;
					provider = args[i];
					break;
					
				case "-recordsToDelete":
					i++;
					recordsToDelete = args[i];
					break;
					
				case "-recordType":
					i++;
					recordType = args[i];
					break;

				case "-set":
					i++;
					set = args[i];
					break;
					
				case "-status":
					runStatus = true;
					break;
					
				case "-update":
					runUpdate = true;
					break;
					
				case "-validate":
					runValidate = true;
					break;
			}
		}
		BaseModule.setCoreBaseURL(coreBaseURL);
		System.out.println("Using \"" + coreBaseURL + "\" as the base url");
	}
	
	public String getInstitutionURL() {
		return(institutionURL);
	}

	public void setInstitutionURL(String institutionURL) {
		this.institutionURL = institutionURL;
	}

	public String getRecordType() {
		return(recordType);
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAccessionNumber() {
		return(accessionNumber);
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getPid() {
		return(pid);
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSet() {
		return(set);
	}
	
	public void setSet(String set) {
		this.set = set;
	}
	
	public String getProvider() {
		return(provider);
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getCoreBaseURL() {
		return(coreBaseURL);
	}
	
	public void setCoreBaseURL(String coreBaseURL) {
		this.coreBaseURL = coreBaseURL;
	}
	
	public boolean isRunCommit() {
		return(runCommit);
	}
	
	public void setRunCommit(boolean runCommit) {
		this.runCommit = runCommit;
	}
	
	public boolean isRunList() {
		return(runList);
	}
	
	public void setRunList(boolean runList) {
		this.runList = runList;
	}
	
	public boolean isRunStatus() {
		return(runStatus);
	}
	
	public void setRunStatus(boolean runStatus) {
		this.runStatus = runStatus;
	}
	
	public boolean isRunUpdate() {
		return(runUpdate);
	}
	
	public void setRunUpdate(boolean runUpdate) {
		this.runUpdate = runUpdate;
	}
	
	public boolean isRunValidate() {
		return(runValidate);
	}
	
	public void setRunValidate(boolean runValidate) {
		this.runValidate = runValidate;
	}
	
	public boolean isRunAll() {
		return(runAll);
	}
	
	public void setRunAll(boolean runAll) {
		this.runAll = runAll;
	}
	
	public String getFilenames() {
		return(filenames);
	}
	
	public void setFilenames(String filenames) {
		this.filenames = filenames;
	}
	
	public String getBadFilenames() {
		return(badFilenames);
	}
	
	public void setBadFilenames(String badFilenames) {
		this.badFilenames = badFilenames;
	}
	
	public boolean isDeleteAll() {
		return(deleteAll);
	}
	
	public void setDeleteAll(boolean deleteAll) {
		this.deleteAll = deleteAll;
	}
	
	public String getRecordsToDelete() {
		return(recordsToDelete);
	}
	
	public void setRecordsToDelete(String recordsToDelete) {
		this.recordsToDelete = recordsToDelete;
	}
}
