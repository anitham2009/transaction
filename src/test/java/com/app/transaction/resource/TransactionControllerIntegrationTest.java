package com.app.transaction.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
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
import com.app.transaction.util.CommonConstants;
import com.app.transaction.util.CommonUtil;

/**
 * Integration testing of all endpoints
 * @author Anitha Manoharan
 *
 */
@SpringBootTest(classes = TransactionApplication.class, webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TransactionControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootURL() {
		 return CommonConstants.LOCALHOST+ port+ CommonConstants.TRANSACTION_URL;
	}
	
	@DisplayName("Create transaction")
	@Test
	@Order(1)
	public void testCreateTransaction() throws Exception {
		TransactionRequest transactionRequest = (TransactionRequest) CommonUtil.retrieveObject(CommonConstants.TRANSACTION_REQUEST_FILE,
				TransactionRequest.class);
		TransactionResponse postResponse = restTemplate.postForObject(getRootURL(), transactionRequest, TransactionResponse.class);
		assertNotNull(postResponse);
		assertEquals(CommonConstants.SUCCESS,postResponse.getMessage());
	}
	
	@DisplayName("Get transaction details")
	@Test
	@Order(2)
	public void testRetrieveTransaction() throws Exception {
		 String url = getRootURL()+ CommonConstants.BACK_SLASH+3L;
		TransactionResponse postResponse = restTemplate.getForObject(url, TransactionResponse.class);
		assertNotNull(postResponse);
		assertEquals(CommonConstants.SUCCESS,postResponse.getMessage());
	}
	

	@DisplayName("Create transaction Failure")
	@Test
	@Order(3)
	public void testCreateTransactionException() throws Exception {
		TransactionRequest transactionRequest = (TransactionRequest) CommonUtil.retrieveObject(CommonConstants.TRANSACTION_INVALID_REQ_FILE,
				TransactionRequest.class);
		TransactionResponse postResponse = restTemplate.postForObject(getRootURL(), transactionRequest, TransactionResponse.class);
		assertNotNull(postResponse);
		assertEquals(CommonConstants.FAILURE,postResponse.getMessage());
	}

	@DisplayName("Get transaction details Failure")
	@Test
	@Order(4)
	public void testRetrieveTransactionFailure() throws Exception {
		 String url = getRootURL()+ CommonConstants.BACK_SLASH+5L;
		TransactionResponse postResponse = restTemplate.getForObject(url, TransactionResponse.class);
		assertNotNull(postResponse);
		assertEquals(CommonConstants.FAILURE,postResponse.getMessage());
	}
	

}
