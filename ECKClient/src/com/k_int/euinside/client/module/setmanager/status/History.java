package com.k_int.euinside.client.module.setmanager.status;

import java.util.Date;

public class History {

	private String action;
	private Date when;
	private int numberOfRecords;
	private int duration;
	
	public History() {
	}

	public String getAction() {
		return(action);
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public Date getWhen() {
		return(when);
	}
	
	public void setWhen(Date when) {
		this.when = when;
	}

	public int getNumberOfRecords() {
		return(numberOfRecords);
	}
	
	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public int getDuration() {
		return(duration);
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String toString() {
		String result = "Class: History:\n"; 
		result += "\tAction: " + action + "\n";
		result += "\tWhen: " + when + "\n";
		result += "\tNumber of Records: " + numberOfRecords + "\n";
		result += "\tDuration: " + duration + "\n";
		return(result);
	}
}
