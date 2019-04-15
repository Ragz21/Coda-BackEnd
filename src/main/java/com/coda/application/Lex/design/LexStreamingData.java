package com.coda.application.Lex.design;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LEXDATA")
public class LexStreamingData {
	
	@Column(name = "request_content")
	private byte[] requestContent;
	
	@Column(name = "response_content")
	private byte[] responseContent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
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
