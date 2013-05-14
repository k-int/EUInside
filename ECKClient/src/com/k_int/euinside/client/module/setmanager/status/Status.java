package com.k_int.euinside.client.module.setmanager.status;

import java.util.ArrayList;
import java.util.Date;

public class Status {

	private String code;
	private String description;
	private Date created;
	LiveSet liveSet;
	WorkingSet workingSet;
	ArrayList<QueuedActions> queuedActions;
	ArrayList<History> history;
	
	public Status() {
	}
	
	public String getCode() {
		return(code);
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return(description);
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreated() {
		return(created);
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public LiveSet getLiveSet() {
		return(liveSet);
	}
	
	public void setLiveSet(LiveSet liveSet) {
		this.liveSet = liveSet;
	}
	
	public WorkingSet getWorkingSet() {
		return(workingSet);
	}
	
	public void setWorkingSet(WorkingSet workingSet) {
		this.workingSet = workingSet;
	}
	
	public ArrayList<QueuedActions> getQueuedActions() {
		return(queuedActions);
	}
	
	public void setQueuedActions(ArrayList<QueuedActions> queuedActions) {
		this.queuedActions = queuedActions;
	}
	
	public ArrayList<History> getHistory() {
		return(history);
	}
	
	public void setHistory(ArrayList<History> history) {
		this.history = history;
	}

	public String toString() {
		String result = "Class: Status:\n"; 
		result += "\tCode: " + code + "\n";
		result += "\tDescription: " + description + "\n";
		result += "\tCreated: " + created + "\n";
		if (liveSet != null) {
			result += liveSet.toString();
		}
		if (workingSet != null) {
			result += workingSet.toString();
		}
		for (QueuedActions queuedAction : queuedActions) {
			result += queuedAction.toString();
		}
		for (History h : history) {
			result += h.toString();
		}
		return(result);
	}
}
