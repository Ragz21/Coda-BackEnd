package com.coda.application.Lex.design;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;
import com.amazonaws.services.lexruntime.model.PostContentRequest;
import com.amazonaws.services.lexruntime.model.PostContentResult;
import com.amazonaws.util.IOUtils;

@Service
public class LexService {
	private static List<LexData> lexList = new ArrayList<LexData>();
	private static List<LexStreamingData> lexList1 = new ArrayList<LexStreamingData>();

	@Autowired
	private LexRepo LexRepo;

	public Page<LexStreamingData> findAll(int page, int size) {	
		return LexRepo.findAll(PageRequest.of(page, size));
	}

	public LexData save(LexData lexData) {
		lexList.add(lexData);
		return lexData;
	}

	public LexStreamingData saveStream(LexStreamingData lexData) {
		lexList1.add(lexData);
		LexRepo.save(lexData);
		return lexData;
	}

	public LexStreamingData lexCall(byte[] lexInputByteContent) throws IOException {
		InputStream lexInputContentStream = new ByteArrayInputStream(lexInputByteContent);
		AmazonLexRuntime client = AmazonLexRuntimeClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		PostContentRequest contentRequest = new PostContentRequest();
		contentRequest.setBotName("TodaysWeather");
		contentRequest.setAccept("audio/mpeg");
		contentRequest.setContentType("audio/x-l16; sample-rate=16000; channel-count=1");
		contentRequest.setInputStream(lexInputContentStream);
		contentRequest.setBotAlias("Test");
		contentRequest.setUserId("testuser");
		PostContentResult resultContent = client.postContent(contentRequest);
		LexData lexData = new LexData();
		lexData.setRequestContent(resultContent.getInputTranscript());
		LexStreamingData streamingData = new LexStreamingData();
		byte[] lexOutputContent = IOUtils.toByteArray(resultContent.getAudioStream());
		streamingData.setRequestContent(lexInputByteContent);
		streamingData.setResponseContent(lexOutputContent);
		if (resultContent.getDialogState().startsWith("Elicit") || resultContent.getDialogState().startsWith("ConfirmIntent")) {
			lexData.setResponseContent(resultContent.getMessage());
		}
		else if (resultContent.getDialogState().equals("ReadyForFulfillment"))
			lexData.setResponseContent(String.format("%s: %s", resultContent.getIntentName(), resultContent.getSlots()));
		else
			lexData.setResponseContent(resultContent.toString());
		save(lexData);
		System.out.println(lexData.getResponseContent());
		return streamingData;
	}

}
