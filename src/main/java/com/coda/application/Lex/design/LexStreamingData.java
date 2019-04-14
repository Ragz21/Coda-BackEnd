package com.coda.application.Lex.design;

public class LexStreamingData {
	private byte[] requestContent;
	private byte[] responseContent;
	protected LexStreamingData(byte[] requestContent, byte[] responseContent) {
		super();
		this.requestContent = requestContent;
		this.responseContent = responseContent;
	}
	public byte[] getRequestContent() {
		return requestContent;
	}
	public void setRequestContent(byte[] requestContent) {
		this.requestContent = requestContent;
	}
	public byte[] getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(byte[] responseContent) {
		this.responseContent = responseContent;
	}
	
	

}
