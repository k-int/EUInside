package com.k_int.euinside.core.datamodel

import java.util.Date;

class ModuleStatistic {

	// The module this statistic belongs to
	static belongsTo = [module : Module]

	// When this statistic occurred
	Date statisticDate;
	
	// Number of items processed
	Integer numberProcessed;
	
	// The duration in ms
	Integer duration;
	
	// The number of successful items
	Integer numberSuccessful;
	
	// The number of failed items
	Integer numberFailed;
	
    static constraints = {
		statisticDate    nullable : false
		numberProcessed  nullable : false
		duration         nullable : false
		numberSuccessful nullable : false
		numberFailed     nullable : false
    }
}
