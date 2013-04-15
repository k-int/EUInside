package com.k_int.euinside.core

import grails.converters.JSON
import grails.converters.XML

class GatewayController {

	def ModulesService;

	private def processResponse(responseValue) {
		boolean rendered = false;	
		if (response.contentType != null) {
			if (response.contentType.contains("json")) {
				rendered = true;
				render responseValue as JSON;
			} else if (response.contentType.contains("xml")) {
				rendered = true;
				render responseValue as XML;
			}
		}
		
		// If we have not rendered the response then just treat it as is 
		if (!rendered) {
			render responseValue;
		}
	}
	
	private def format() {
		def format = "json";
		withFormat {
			json {
				format = "json";
				return(format);
			}
			html {
				format = "html";
				return(format);
			}
			xml {
				format = "xml"
				return(format);
			}
		}
		return(format);
	}
	
    def definitionGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_DEFINITION, params, format(), response);
		processResponse(responseValue);
	}
	
    def definitionPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_DEFINITION, params, format(), request, response);
		processResponse(responseValue);
	}
	
    def persistenceGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_PERSISTENCE, params, format(), response);
		processResponse(responseValue);
	}
	
    def persistencePostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_PERSISTENCE, params, format(), request, response);
		processResponse(responseValue);
	}
}
