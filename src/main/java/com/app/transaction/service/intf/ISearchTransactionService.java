package com.app.transaction.service.intf;

import com.app.transaction.model.TransactionResponse;

public interface ISearchTransactionService {
	public TransactionResponse getTxnDetailByAccountId(Long accountId);
}
