package com.coda.application.Lex.design;


public class LexData {
	private String requestContent;
	private String responseContent;
	public LexData(String requestContent, String responseContent) {
		super();
		this.requestContent = requestContent;
		this.responseContent = responseContent;
	}
	protected LexData() {
		super();
	}
	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	
}


