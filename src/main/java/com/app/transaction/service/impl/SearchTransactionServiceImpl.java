package com.app.transaction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.message.util.TransactionErrorResponse;
import com.app.transaction.message.util.TransactionSuccessResponse;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.repository.ITransactionRepository;
import com.app.transaction.service.intf.ISearchTransactionService;
import com.app.transaction.util.TransactionConstants;

@Service
public class SearchTransactionServiceImpl implements ISearchTransactionService {

	@Autowired
	ITransactionRepository transactionRepository;
	
	@Override
	public TransactionResponse getTxnDetailByAccountId(Long accountId) {
		TransactionResponse successResponse;
		try {
			List<TransactionDetail> transactionDtls = transactionRepository.findByAccountId(accountId);
			if( transactionDtls.size() == 0) {
				TransactionResponse errorResponse = TransactionErrorResponse.formErrorMessage(TransactionConstants.TXN_NOT_EXISTS, 404);
				return errorResponse;
			}
			successResponse = TransactionSuccessResponse.formSuccessMsg( transactionDtls);
		} catch (Exception e) {
			TransactionResponse errorResponse = TransactionErrorResponse.formErrorMessage(e.getMessage(), 422);
			return errorResponse;
		}
		return successResponse;
	}
}
