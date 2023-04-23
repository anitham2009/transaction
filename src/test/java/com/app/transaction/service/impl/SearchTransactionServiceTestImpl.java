package com.app.transaction.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.repository.ITransactionRepository;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class SearchTransactionServiceTestImpl {
	
	@InjectMocks
	SearchTransactionServiceImpl searchTransactionService;
	
	@Mock
	ITransactionRepository createTransactionRepository;
	
	public static final String BASE_FILE_PATH = "src/test/resources/";
	public static final String TRANSACTION_DETAIL_FILE = "transactiondetail.json";
	@Test
	public void testRetrieveTransaction() throws Exception {
		TransactionDetail transactionDetail = (TransactionDetail) retrieveObject(TRANSACTION_DETAIL_FILE,
				TransactionDetail.class);
		when(createTransactionRepository.findByAccountId(any())).thenReturn(Collections.singletonList(transactionDetail));
		TransactionResponse response = searchTransactionService.getTxnDetailByAccountId(3L);
		assertEquals("Success",response.getMessage());
		assertEquals(1,response.getTransaction().get(0).getTransactionId());
	}
	
	@Test
	public void testRetrieveTransactionFailure() throws Exception {
		when(createTransactionRepository.findByAccountId(any())).thenReturn(null);
		TransactionResponse response = searchTransactionService.getTxnDetailByAccountId(3L);
		assertEquals("Failure",response.getMessage());
	}
	
	@Test
	public void testRetrieveTransactionException() throws Exception {
		TransactionResponse response = searchTransactionService.getTxnDetailByAccountId(null);
		assertEquals("Failure",response.getMessage());
		assertNotNull(response.getErrorMessage());
		assertNotNull(response.getErrorMessage().getMessage());
		assertNotNull(response.getErrorMessage().getSource());
		assertNotNull(response.getErrorMessage().getStatusCode());
	}
	
	<T> Object retrieveObject(String fileName, Class<T> contentClass)
			throws StreamReadException, DatabindException, IOException {
		File file = new File(BASE_FILE_PATH + fileName);
		ObjectMapper mapper = new ObjectMapper();
		Object object = mapper.readValue(file, contentClass);
		return object;

	}

}
