package com.app.transaction.message.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.app.transaction.model.ErrorMessage;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.util.TransactionConstants;

/**
 * This class is used to form error message to send it in Transaction service response.
 * @author Anitha Manoharan
 *
 */
@Component
public class TransactionErrorResponse {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionErrorResponse.class);

	/**
	 * Set message and status code in TransactionResponse
	 * @param message message
	 * @param statusCode status code
	 * @return TransactionResponse
	 */
	public static TransactionResponse formErrorMessage(String message, int statusCode) {
		LOGGER.debug("Inside formErrorMessage method {}", TransactionErrorResponse.class);
		ErrorMessage errorMessage = TransactionErrorMessage.formErrorMessage(message, statusCode);
		TransactionResponse errorResponse = TransactionResponse.builder().errorMessage(errorMessage).message(TransactionConstants.FAILURE).build();
		return errorResponse;
	}
}
