package com.k_int.euinside.core

import groovy.xml.XmlUtil

import grails.converters.JSON

class GatewayController {

	def ModulesService;

	private def processResponse(responseValue, module) {

		def content = responseValue.content;		
		def contentType = responseValue.contentType;
		
		boolean rendered = false;	
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

		// If we have not rendered the response then just treat it as is 
		if (!rendered) {
			def statusCode = responseValue.status.statusCode;
			def statusPhrase =  responseValue.status.reasonPhrase;
			response.sendError(202, statusPhrase);
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
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def definitionPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_DEFINITION, params, format(), request, response);
		processResponse(responseValue, ModulesService.MODULE_DEFINITION);
	}
	
    def persistenceGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_PERSISTENCE, params, format(), response);
		processResponse(responseValue, ModulesService.MODULE_PERSISTENCE);
	}
	
    def persistencePostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_PERSISTENCE, params, format(), request, response);
		processResponse(responseValue, ModulesService.MODULE_PERSISTENCE);
	}

	def setManagerGetRelay() {
		def responseValue = ModulesService.httpGet(ModulesService.MODULE_SET_MANAGER, params, format(), response);
		processResponse(responseValue, ModulesService.MODULE_SET_MANAGER);
	}
	
    def setManagerPostRelay() {
		def responseValue = ModulesService.httpPost(ModulesService.MODULE_SET_MANAGER, params, format(), request, response);
		processResponse(responseValue, ModulesService.MODULE_SET_MANAGER);
	}
}
