package com.app.transaction.service.util;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.app.transaction.util.TransactionConstants;

@Component
public class CreateTransactionNumber {
	
	public static String createTransactionNumber() {
		Random random = new Random();
		int randomNumber = Math.abs(random.nextInt());
		String transactionNumber =  TransactionConstants.TRANSACTION_PREFIX + System.currentTimeMillis()+randomNumber;
		return transactionNumber;
	}

}
