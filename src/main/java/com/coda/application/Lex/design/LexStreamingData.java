package com.coda.application.Lex.design;

import java.util.Arrays;

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
	
	protected LexStreamingData() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	public LexStreamingData(byte[] requestContent, byte[] responseContent) {
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
	@Override
	public String toString() {
		return "LexStreamingData [requestContent=" + Arrays.toString(requestContent) + ", responseContent="
				+ Arrays.toString(responseContent) + "]";
	}
	
	

}
