package com.app.transaction.message.util;

import org.springframework.stereotype.Component;

import com.app.transaction.model.ErrorMessage;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.util.TransactionConstants;

@Component
public class TransactionErrorResponse {

	public static TransactionResponse formErrorMessage(String message, int statusCode) {
		ErrorMessage errorMessage = TransactionErrorMessage.formErrorMessage(message, statusCode);
		TransactionResponse errorResponse = TransactionResponse.builder().errorMessage(errorMessage).message(TransactionConstants.FAILURE).build();
		return errorResponse;
	}
}
