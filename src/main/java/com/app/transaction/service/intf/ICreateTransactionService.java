package com.app.transaction.service.intf;

import com.app.transaction.model.TransactionRequest;
import com.app.transaction.model.TransactionResponse;
/**
 * Create Transaction service.
 * @author Anitha Manoharan
 *
 */
public interface ICreateTransactionService {

	public TransactionResponse createTransaction(TransactionRequest transactionRequest);
}
