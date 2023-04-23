package com.app.transaction.message.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.transaction.entity.TransactionDetail;
import com.app.transaction.model.Transaction;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.util.TransactionConstants;

@Component
public class TransactionSuccessResponse {

	public static TransactionResponse formSuccessMsg(List<TransactionDetail> transactionDetail) {
		TransactionResponse response = TransactionResponse.builder().message(TransactionConstants.SUCCESS)
				.transaction(formTransactionResponse(transactionDetail)).build();
		return response;
	}
	
	public static List<Transaction> formTransactionResponse( List<TransactionDetail> transactionDetail) {
		
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
