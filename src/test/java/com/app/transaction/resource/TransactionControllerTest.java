package com.app.transaction.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.app.transaction.TransactionApplication;
import com.app.transaction.model.TransactionRequest;
import com.app.transaction.model.TransactionResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = TransactionApplication.class, webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TransactionControllerTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private static final String LOCALHOST= "http://localhost:";
	public static final String BASE_FILE_PATH = "src/test/resources/";
	public static final String TRANSACTION_REQUEST_FILE = "transactionreqvalid.json";
	private String getRootURL() {
		 return LOCALHOST+ port+"/transaction";
	}
	
	@Test
	@Order(1)
	public void testCreateTransaction() throws Exception {
		TransactionRequest transactionRequest = (TransactionRequest) retrieveObject(TRANSACTION_REQUEST_FILE,
				TransactionRequest.class);
		TransactionResponse postResponse = restTemplate.postForObject(getRootURL(), transactionRequest, TransactionResponse.class);
		assertNotNull(postResponse);
		assertEquals("Success",postResponse.getMessage());
	}
	
	@Test
	@Order(2)
	public void testRetrieveTransaction() throws Exception {
		 String url = getRootURL()+"/"+3L;
		TransactionResponse postResponse = restTemplate.getForObject(url, TransactionResponse.class);
		assertNotNull(postResponse);
		assertEquals("Success",postResponse.getMessage());
	}

	<T> Object retrieveObject(String fileName, Class<T> contentClass)
			throws StreamReadException, DatabindException, IOException {
		File file = new File(BASE_FILE_PATH + fileName);
		ObjectMapper mapper = new ObjectMapper();
		Object object = mapper.readValue(file, contentClass);
		return object;

	}
}
