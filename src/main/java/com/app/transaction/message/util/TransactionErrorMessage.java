package com.app.transaction.message.util;

import org.springframework.stereotype.Component;

import com.app.transaction.model.ErrorMessage;
import com.app.transaction.util.TransactionConstants;

@Component
public class TransactionErrorMessage {
	public static ErrorMessage formErrorMessage(String message, int statusCode) {
		ErrorMessage errorMessage = ErrorMessage.builder().message(message).statusCode(statusCode).source(TransactionConstants.TRANSACTION_SERVICE).build();
		return errorMessage;
	}
}
