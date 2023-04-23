package com.app.transaction.service.impl;

import java.util.Collections;
import java.util.Date;

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

@Service
public class CreateTransactionServiceImpl implements ICreateTransactionService {

	@Autowired
	ITransactionRepository createTransactionRepository;
	
	@Override
	public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
		TransactionDetail transactionDtl;
		TransactionResponse successResponse = null;
		try {
			transactionDtl = formTransactionDtlInput(transactionRequest);
			transactionDtl = createTransactionRepository.saveAndFlush(transactionDtl);
			successResponse = TransactionSuccessResponse.formSuccessMsg(Collections.singletonList(transactionDtl));
		} catch (Exception e) {
			TransactionResponse errorResponse = TransactionErrorResponse.formErrorMessage(e.getMessage(), 422);
			return errorResponse;
		}
		return successResponse;
	}

	private TransactionDetail formTransactionDtlInput(TransactionRequest transactionRequest) {
		TransactionDetail transactionDtl = TransactionDetail.builder().transactionNumber(CreateTransactionNumber.createTransactionNumber()).accountId(transactionRequest.getAccountId()).transferredAmount(transactionRequest.getAmount())
				.balance(transactionRequest.getBalance()).description(transactionRequest.getDescription()).transferredDate(new Date()).build();
		return transactionDtl;
	}
}
