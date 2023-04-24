package com.app.transaction.service.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.app.transaction.util.TransactionConstants;

/**
 * This class is used to Create Transction Number
 * @author Anitha Manoharan
 *
 */
@Component
public class CreateTransactionNumber {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateTransactionNumber.class);
	/**
	 * Create transaction number to use while saving transaction.
	 * Use Random class to generate random numbers.
	 * @return String
	 */
	public static String createTransactionNumber() {
		LOGGER.debug("Inside createTransactionNumber method {}", CreateTransactionNumber.class);
		Random random = new Random();
		int randomNumber = Math.abs(random.nextInt());
		String transactionNumber =  TransactionConstants.TRANSACTION_PREFIX + System.currentTimeMillis()+randomNumber;
		return transactionNumber;
	}

}
