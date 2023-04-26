package com.app.transaction.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.model.TransactionRequest;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.repository.ITransactionRepository;
import com.app.transaction.util.CommonConstants;
import com.app.transaction.util.CommonUtil;

/**
 * Test CreateTransactionServiceTest class
 * 
 * @author Anitha Manoharan
 *
 */
@ExtendWith(MockitoExtension.class)
public class CreateTransactionServiceTestImpl {

	@InjectMocks
	CreateTransactionServiceImpl createTransctionService;
	@Mock
	ITransactionRepository createTransactionRepository;

	@DisplayName("Create Transaction")
	@Test
	public void testCreateTransaction() throws Exception {
		TransactionDetail transactionDetail = (TransactionDetail) CommonUtil
				.retrieveObject(CommonConstants.TRANSACTION_DETAIL_FILE, TransactionDetail.class);
		TransactionRequest transactionRequest = (TransactionRequest) CommonUtil
				.retrieveObject(CommonConstants.TRANSACTION_REQUEST_FILE, TransactionRequest.class);
		when(createTransactionRepository.saveAndFlush(any())).thenReturn(transactionDetail);
		TransactionResponse response = createTransctionService.createTransaction(transactionRequest);
		assertEquals(CommonConstants.SUCCESS, response.getMessage());
	}

	@DisplayName("Create Transction Failure response")
	@Test
	public void testCreateTransactionFail() throws Exception {
		TransactionResponse response = createTransctionService.createTransaction(null);
		assertEquals(CommonConstants.FAILURE, response.getMessage());
	}

}
