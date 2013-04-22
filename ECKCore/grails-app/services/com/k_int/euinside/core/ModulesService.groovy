package com.k_int.euinside.core

import groovyx.net.http.Method
import groovyx.net.http.HTTPBuilder;
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
		
	def initialise() {
		modules = grailsApplication.config.modules;
	}

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
		
	private def determineURL(module, urlPath, format) {
		String url = modules[module].baseURL + modules[module].basePath;
		if (urlPath != null) {
			url += "/" + urlPath;
		}
		if (format != null) {
			url += "." + format;
		}
		return(url);
	} 

	def replacePathInHtml(module, html) {
		def url = modules[module].baseURL;
		def basePath = modules[module].basePath;
		return(html.replace(basePath, url + basePath));
	} 

	private def processResponse(httpResponse, content) {
		def result = [ : ];
		result.content = content;
		result.contentType = null;
		result.status = httpResponse.statusLine;
		httpResponse.headers.each {
			if (it.name == "Content-Type") {
				result.contentType = it.buffer.substring(it.valuePos + 1, it.buffer.length());
			}
		}
		return(result);
	}
		
	def httpGet(module, parameters, format, callerResponse) {

		def result = null;
		def extension = parameters.extension;		
		def url = determineURL(module, parameters.path, format);
		
		log.debug("making HTTP call to url: " + url);
		
		def http = new HTTPBuilder(url);
		def query = createURLArgs(module, parameters);
		
		http.get(query : query) { httpResponse, content ->
			result = processResponse(httpResponse, content);
//			responseValue = content;
		}
		return(result);
	}

	def httpPost(module, parameters, format, requestObject, callerResponse) {
		def result = null;
		
		def url = determineURL(module, parameters.path, format);
		def queryArguments = createURLArgs(module, parameters);
		log.debug("making HTTP call to url: " + url);

		// Determine if we have a file to send on
		def fileContents = null;
		String fileParameterName = null;
		def multipartFiles = null;
		
		// Only attempt to get the file parameter if this is a multipart request
		if (requestObject instanceof org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest) {
			multipartFiles = requestObject.getFileMap(); 
		}
	
		// Now we have everything we need, let us perform the post		
		def http = new HTTPBuilder(url);
		http.request(Method.POST) { req ->
			requestContentType : CONTENT_TYPE_FORM
			MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)

			// Only add the file contents, if we have been supplied with them
			if (multipartFiles != null) {
				multipartFiles.each() {parameterName, multiPartFile ->
					// Add the file contents to the request as the specified parameter
				    multiPartContent.addPart(parameterName, new ByteArrayBody(multiPartFile.bytes, multiPartFile.contentType, multiPartFile.originalFilename));
				}
			}
				
			// Now add all the parameters, seems a bit of an odd way of doing it, but hey it seems to work	   
			queryArguments.each() {argument ->
				multiPartContent.addPart(argument.key, new StringBody((argument.value == null) ? "" : argument.value))
			}

			// Now we can add the parts to the request
			req.setEntity(multiPartContent)

			// Deal with the response
			// We need to deal with failures in some sensible way	   
			response.success = { httpResponse, content ->
				result = processResponse(httpResponse, content);
//				responseValue = content;
			}
			
			// handler for any failure status code:
			response.failure = { httpResponse ->
				processResponse(httpResponse, null);
			}
		}
	   
		return(result);
	}
}
