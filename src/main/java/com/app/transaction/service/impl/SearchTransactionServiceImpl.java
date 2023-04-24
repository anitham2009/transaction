package com.app.transaction.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.message.util.TransactionErrorResponse;
import com.app.transaction.message.util.TransactionSuccessResponse;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.repository.ITransactionRepository;
import com.app.transaction.service.intf.ISearchTransactionService;
import com.app.transaction.util.TransactionConstants;

/**
 * This class is used to get transaction details of the given account id.
 * 
 * @author Anitha Manoharan
 *
 */
@Service
public class SearchTransactionServiceImpl implements ISearchTransactionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchTransactionServiceImpl.class);
	@Autowired
	ITransactionRepository transactionRepository;

	/**
	 * Get transaction detail by account id. If record is not there or any exception
	 * then return error response. If record is there then return success response.
	 * 
	 * @param accountId account id
	 * @return TransactionResponse
	 */
	@Override
	public TransactionResponse getTxnDetailByAccountId(Long accountId) {
		TransactionResponse successResponse;
		try {
			LOGGER.debug("Inside  method {}", this.getClass());
			List<TransactionDetail> transactionDtls = transactionRepository.findByAccountId(accountId);
			// Check transaction detail data availability.
			if (transactionDtls.size() == 0) {
				LOGGER.error("Transaction detail is not available for given account {}", accountId);
				// Form error response
				TransactionResponse errorResponse = TransactionErrorResponse
						.formErrorMessage(TransactionConstants.TXN_NOT_EXISTS, 404);
				return errorResponse;
			}
			successResponse = TransactionSuccessResponse.formSuccessMsg(transactionDtls);
		} catch (Exception e) {
			LOGGER.error("Error occured while get transaction details {}", this.getClass());
			// Form error response message
			TransactionResponse errorResponse = TransactionErrorResponse.formErrorMessage(e.getMessage(), 422);
			return errorResponse;
		}
		return successResponse;
	}
}
