package com.coda.application.Lex.design;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class LexController {
	
	@Autowired
	private LexService lexService;
	
	@GetMapping("users")
	public Page<LexStreamingData> getAllTodos(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "0") int size) {
		return lexService.findAll(page,size);
	}
	
	@PostMapping("users")
	public ResponseEntity<byte[]> updateTodo(@RequestBody byte[] lexInputContent) throws IOException{
		LexStreamingData createdLex = lexService.lexCall(lexInputContent);
		lexService.saveStream(createdLex);
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(createdLex.getResponseContent(),headers,HttpStatus.OK);
		return responseEntity;
	}
}