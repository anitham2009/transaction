package com.app.transaction.service.intf;

import com.app.transaction.model.TransactionResponse;
/**
 * Search transaction service.
 * @author Anitha Manoharan
 *
 */
public interface ISearchTransactionService {
	public TransactionResponse getTxnDetailByAccountId(Long accountId);
}
