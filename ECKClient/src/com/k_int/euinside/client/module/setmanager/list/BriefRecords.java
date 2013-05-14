package com.k_int.euinside.client.module.setmanager.list;

import java.util.ArrayList;

public class BriefRecords extends ArrayList<BriefRecord>{

	private static final long serialVersionUID = 1234502L;

	public BriefRecords() {
	}

	public String toString() {
		String result = "Class: BriefRecords\n";
		for (BriefRecord briefRecord : this) {
			result += briefRecord.toString();
		}
		return(result);
	}
}
