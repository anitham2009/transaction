package com.app.transaction.message.util;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.model.Transaction;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.util.TransactionConstants;

/**
 * This class is used to form Transaction success response. 
 * @author Anitha Manoharan
 *
 */
@Component
public class TransactionSuccessResponse {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionSuccessResponse.class);
	/**
	 * Iterate list of TransactionDetail and set it in response.
	 * @param transactionDetail transaction detail
	 * @return TransactionResponse
	 */
	public static TransactionResponse formSuccessMsg(List<TransactionDetail> transactionDetail) {
		LOGGER.debug("Inside formSuccessMsg method {}", TransactionSuccessResponse.class);
		TransactionResponse response = TransactionResponse.builder().message(TransactionConstants.SUCCESS)
				.transaction(formTransactionResponse(transactionDetail)).build();
		return response;
	}
	
	/**
	 * Iterate transactionDetail list and set it into response model.
	 * @param transactionDetail transaction detail
	 * @return List<Transaction>
	 */
	public static List<Transaction> formTransactionResponse( List<TransactionDetail> transactionDetail) {
		LOGGER.debug("Inside formTransactionResponse method {}", TransactionSuccessResponse.class);
		List<Transaction> transactionDtls = transactionDetail.stream().map( transaction -> {
			Transaction response = Transaction.builder()
					.accountId(transaction.getAccountId())
					.balance(transaction.getBalance())
					.description(transaction.getDescription())
					.transactionId(transaction.getTransactionId())
					.transactionNumber(transaction.getTransactionNumber())
					.transferredAmount(transaction.getTransferredAmount())
					.transferredDate(transaction.getTransferredDate()).build(); 
				return response;
			}).collect(Collectors.toList());
			return transactionDtls;
		
		
	}
}
