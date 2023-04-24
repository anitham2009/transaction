package com.app.transaction.message.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.app.transaction.model.ErrorMessage;
import com.app.transaction.util.TransactionConstants;
/**
 * This class is used to form error message.
 * @author Anitha Manoharan
 *
 */
@Component
public class TransactionErrorMessage {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionErrorMessage.class);
	/**
	 * Set error message and status code in ErrorMessage to send it in response.
	 * @param message message
	 * @param statusCode status code
	 * @return ErrorMessage
	 */
	public static ErrorMessage formErrorMessage(String message, int statusCode) {
		LOGGER.debug("Inside formErrorMessage method {}", TransactionErrorMessage.class);
		ErrorMessage errorMessage = ErrorMessage.builder().message(message).statusCode(statusCode).source(TransactionConstants.TRANSACTION_SERVICE).build();
		return errorMessage;
	}
}
