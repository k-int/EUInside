package com.k_int.euinside.client;

public class HttpResult {
	private Error callResult;
	private int httpStatusCode;
	private String content;

	public HttpResult() {
		callResult = Error.NONE;
	}
	
	public Error getCallResult() {
		return(callResult);
	}
	
	public void setCallResult(Error callResult) {
		this.callResult = callResult;
	}
	
	public int getHttpStatusCode() {
		return(httpStatusCode);
	}
	
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	
	public String getContent() {
		return(content);
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		String result = "Class: HttpResult:\n"; 
		result += "\tcallResult: " + callResult + "\n";
		result += "\tHhttpStatusCode: " + httpStatusCode + "\n";
		result += "\tContent: " + ((content == null) ? "" : content) + "\n";
		return(result);
	}
}
