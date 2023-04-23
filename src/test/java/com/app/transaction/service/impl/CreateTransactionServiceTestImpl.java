package com.app.transaction.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.model.TransactionRequest;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.repository.ITransactionRepository;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class CreateTransactionServiceTestImpl {
	
	@InjectMocks
	CreateTransactionServiceImpl createTransctionService; 
	@Mock
	ITransactionRepository createTransactionRepository;
	
	public static final String BASE_FILE_PATH = "src/test/resources/";
	public static final String TRANSACTION_DETAIL_FILE = "transactiondetail.json";
	public static final String TRANSACTION_REQUEST_FILE = "transactionreqvalid.json";
	
	@Test
	public void testCreateTransaction() throws Exception {
		TransactionDetail transactionDetail = (TransactionDetail) retrieveObject(TRANSACTION_DETAIL_FILE,
				TransactionDetail.class);
		TransactionRequest transactionRequest = (TransactionRequest) retrieveObject(TRANSACTION_REQUEST_FILE,
				TransactionRequest.class);
		when(createTransactionRepository.saveAndFlush(any())).thenReturn(transactionDetail);
		TransactionResponse response = createTransctionService.createTransaction(transactionRequest);
		assertEquals("Success",response.getMessage());
	}
	
	@Test
	public void testCreateTransactionFail() throws Exception {
		TransactionResponse response = createTransctionService.createTransaction(null);
		assertEquals("Failure",response.getMessage());
	}

	<T> Object retrieveObject(String fileName, Class<T> contentClass)
			throws StreamReadException, DatabindException, IOException {
		File file = new File(BASE_FILE_PATH + fileName);
		ObjectMapper mapper = new ObjectMapper();
		Object object = mapper.readValue(file, contentClass);
		return object;

	}
}
