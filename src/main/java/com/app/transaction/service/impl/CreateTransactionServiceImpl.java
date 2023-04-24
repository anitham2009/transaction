package com.app.transaction.service.impl;

import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.message.util.TransactionErrorResponse;
import com.app.transaction.message.util.TransactionSuccessResponse;
import com.app.transaction.model.TransactionRequest;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.repository.ITransactionRepository;
import com.app.transaction.service.intf.ICreateTransactionService;
import com.app.transaction.service.util.CreateTransactionNumber;

/**
 * This class is used to save transaction of the given account 
 * and return success/failure response message.
 * @author Anitha Manoharan
 *
 */
@Service
public class CreateTransactionServiceImpl implements ICreateTransactionService {

	@Autowired
	ITransactionRepository createTransactionRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateTransactionServiceImpl.class);
	
	/**
	 * Save transaction detail of given account and form success/failure 
	 * response message.
	 * 
	 * @param TransactionRequest transaction request
	 * @return TransactionResponse
	 */
	@Override
	public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
		TransactionDetail transactionDtl;
		TransactionResponse successResponse = null;
		try {
			LOGGER.debug("Inside createTransaction method {}", this.getClass());
			//Form input data to save transaction detail.
			transactionDtl = formTransactionDtlInput(transactionRequest);
			transactionDtl = createTransactionRepository.saveAndFlush(transactionDtl);
			LOGGER.info("Saved Transaction");
			//Form success response message.
			successResponse = TransactionSuccessResponse.formSuccessMsg(Collections.singletonList(transactionDtl));
		} catch (Exception e) {
			LOGGER.error("Error occured while save transaction {}", this.getClass());
			//Form Error response message.
			TransactionResponse errorResponse = TransactionErrorResponse.formErrorMessage(e.getMessage(), 422);
			return errorResponse;
		}
		return successResponse;
	}

	/**
	 * Set input data into TransactionDetail entity object to save transaction in table.
	 * @param transactionRequest transaction request
	 * @return TransactionDetail
	 */
	private TransactionDetail formTransactionDtlInput(TransactionRequest transactionRequest) {
		TransactionDetail transactionDtl = TransactionDetail.builder().transactionNumber(CreateTransactionNumber.createTransactionNumber()).accountId(transactionRequest.getAccountId()).transferredAmount(transactionRequest.getAmount())
				.balance(transactionRequest.getBalance()).description(transactionRequest.getDescription()).transferredDate(new Date()).build();
		return transactionDtl;
	}
}
