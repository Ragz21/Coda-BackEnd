package com.coda.application.Lex.design;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LexController {
	
	@Autowired
	private LexService lexService;
	
	@GetMapping("users")
	public List<LexData> getAllTodos() {
		return lexService.findAll();
	}
	
	@PostMapping("users")
	public ResponseEntity<byte[]> updateTodo(@RequestBody byte[] lexInputContent) throws IOException{
		LexStreamingData createdLex = lexService.lexCall(lexInputContent);
		lexService.saveStream(createdLex);
//		System.out.println(createdLex);
//		System.out.println(createdLex.getResponseContent());
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("users").build().toUri();
		String s = Base64.getEncoder().encodeToString(createdLex.getResponseContent());
		System.out.println(s);
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(createdLex.getResponseContent(),headers,HttpStatus.OK);
		return responseEntity;
	}
}