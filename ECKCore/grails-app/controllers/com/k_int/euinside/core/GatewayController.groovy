package com.k_int.euinside.core

import grails.converters.JSON

import groovy.xml.XmlUtil

import javax.servlet.http.HttpServletResponse;

class GatewayController {

	def ModulesService;

	private def processResponse(responseValue, module) {

		boolean rendered = false;	
		if (responseValue.status.statusCode == HttpServletResponse.SC_OK) {
			def contentType = responseValue.contentType;
			def content = responseValue.content;
			
			if (contentType != null) {
				if (contentType.contains("json")) {
					rendered = true;
					render content as JSON;
				} else if (contentType.contains("xml")) {
					rendered = true;
					render(text: XmlUtil.serialize(content)as String, contentType:"text/xml", encoding:"UTF-8")
				} else if (contentType.contains("html")) {
					rendered = true;
					// Serialise the result, but we need to remove the xml header
					String html = XmlUtil.serialize(content).replaceFirst("\\<\\?.*\\?\\>", "");
					// We also need to fix the script tags as they cannot end "/>"
					html = html.replaceAll(/<SCRIPT.*\/>/) {
						int i = it.size() - 3;
						it[0..i] + "></SCRIPT>";
					};
					
					render(text: ModulesService.replacePathInHtml(module, html) as String, contentType:"text/html", encoding:"UTF-8")
				}
			}
		}

		// If we have not rendered the response then just pass back the status code 
		if (!rendered) {
			def statusCode = responseValue.status.statusCode;
			def statusPhrase =  responseValue.status.reasonPhrase;
			response.sendError(statusCode, statusPhrase);
		}
	}
	
    def definitionGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_DEFINITION, params);
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def definitionPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_DEFINITION, params, request);
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def persistenceGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_PERSISTENCE, params);
		processResponse(responseValue, ModulesService.MODULE_PERSISTENCE);
	}
	
    def persistencePostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_PERSISTENCE, params, request);
		processResponse(responseValue, ModulesService.MODULE_PERSISTENCE);
	}

	def setManagerGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_SET_MANAGER, params);
		processResponse(responseValue, ModulesService.MODULE_SET_MANAGER);
	}
	
    def setManagerPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_SET_MANAGER, params, request);
		processResponse(responseValue, ModulesService.MODULE_SET_MANAGER);
	}
}
