package com.k_int.euinside.core

import groovyx.net.http.Method
import groovyx.net.http.HTTPBuilder;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.mime.content.ByteArrayBody
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.StringBody

class ModulesService {
	def grailsApplication

	public static String MODULE_DEFINITION  = "Definition";
	public static String MODULE_PERSISTENCE = "Persistence";
	public static String MODULE_SET_MANAGER = "SetManager";
	
	private def static CONTENT_TYPE_FORM = "multipart/form-data";
	
	private static def modules;
	private static String contextPath;
		
	/**
	 * Initialiser called by bootstrap to setup this service
	 */
	def initialise() {
		modules = grailsApplication.config.modules;
	}

	/**
	 * Stores the context path that this webapp is being run in
	 *  
	 * @param contextPath ... The context path for this contet
	 */
	static public setContextPath(contextPath) {
		this.contextPath = contextPath;
	}

	/** 
	 * Determines the query arguments that are to be passed to the module
	 *  	
	 * @param module ....... The module we are performing the gateway operation for 
	 * @param parameters ... The parameters passed to usby the caller
	 * 
	 * @return The query arguments to be passed onto the module
	 */
	private def createURLArgs(module, parameters) {
		def arguments = [ : ];
		def moduleParameters = modules[module].parameters;
		if (moduleParameters != null) {
			moduleParameters.keySet().each {
				def value = parameters.getAt(it);
				if (value != null) {
					arguments.putAt(it, value);
				} 
			}
		}
		return(arguments);
	}

	/**
	 * Determines the proper url for the module
	 * 		
	 * @param module .... The module we are performing the gateway operation for 
	 * @param urlPath ... The path within this module that the caller is wanting to goto
	 * 
	 * @return The url where the module lives
	 */
	private def determineURL(module, urlPath) {
		String url = modules[module].baseURL + modules[module].basePath;
		if (urlPath != null) {
			url += "/" + urlPath;
		}
		return(url);
	} 

	/**
	 * Mangles the anchors in the html, so that links to the module still go through the gateway
	 * Links to css, js, etc. are changed so that they go directly to the module
	 *  
	 * @param module ... The module we are performing the gateway operation for 
	 * @param html   ... The html that has been returned
	 * 
	 * @return The html with the links modified
	 */
	def replacePathInHtml(module, html) {
		def url = modules[module].baseURL;
		def basePath = modules[module].basePath;
		
		// anchors we need to replace in a slightly different way to links as they can still go through the core
		String anchor = "\\<A .*\\" + basePath; 
		html = html.replaceAll(anchor) {
			it.replace(basePath, contextPath + "/" + module);
		};
	
		// Now replace all the other parts with direct paths
		return(html.replace(basePath, url + basePath));
	} 

	/**
	 * Interprets the response from the server to pull oyt the information we are interested in
	 *  
	 * @param httpResponse ... The response from the server
	 * @param content ........ The content returned from the server
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	private def processResponse(httpResponse, content) {
		def result = [ : ];
		result.content = content;
		result.status = httpResponse.statusLine;
		if (httpResponse.statusLine.statusCode == HttpServletResponse.SC_OK) {
			// We should have a content type if everything was OK
			result.contentType = httpResponse.getContentType();
		}
		return(result);
	}
		
	/**
	 * Performs a gateway GET request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param method .......... The http method to be performed, defined in groovyx.net.http.Method
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	def httpGet(module, parameters) {
		return(http(module, parameters, null, Method.GET));
	}

	/**
	 * Performs a gateway http POST request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	def httpPost(module, parameters, requestObject) {
		return(http(module, parameters, requestObject, Method.POST));
	}

	/**
	 * Performs a gateway http request
	 * 	
	 * @param module .......... The module we are performing the gateway operation for 
	 * @param parameters ...... Query parameters that may need passing onto the module
	 * @param requestObject ... The original request object, required for passing through any posted files
	 * @param method .......... The http method to be performed, defined in groovyx.net.http.Method
	 * 
	 * @return ... A map that contains the following obects
	 *                 content ....... The returned content
	 *                 status ........ The status line
	 *                 contentType ... The type of the content
	 */
	private def http(module, parameters, requestObject, method) {
		def result = null;
		
		def url = determineURL(module, parameters.path);
		def queryArguments = createURLArgs(module, parameters);
		log.debug("making HTTP call to url: " + url);

		// Determine if we have a file to send on
		def fileContents = null;
		String fileParameterName = null;
		def multipartFiles = null;

		// Only attempt to get the file parameter if this is a multipart request
		if ((requestObject != null) &&
			(requestObject instanceof org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest)) {
			multipartFiles = requestObject.getFileMap(); 
		}
	
		// Now we have everything we need, let us perform the post		
		def http = new HTTPBuilder(url);
		http.request(method) { req ->
			requestContentType : CONTENT_TYPE_FORM

			// Only add the file contents, if we have been supplied with them
			if (multipartFiles != null) {
				MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)
				multipartFiles.each() {parameterName, multiPartFile ->
					// Add the file contents to the request as the specified parameter
				    multiPartContent.addPart(parameterName, new ByteArrayBody(multiPartFile.bytes, multiPartFile.contentType, multiPartFile.originalFilename));
				}
				
				// Now we can add the parts to the request
				req.setEntity(multiPartContent)
			}
				
			// add all the arguments
			uri.query = queryArguments;

			// Deal with the response
			// We need to deal with failures in some sensible way	   
			response.success = { httpResponse, content ->
				result = processResponse(httpResponse, content);
			}
			
			// handler for any failure status code:
			response.failure = { httpResponse ->
				result = processResponse(httpResponse, null);
			}
		}
	   
		return(result);
	}
}
