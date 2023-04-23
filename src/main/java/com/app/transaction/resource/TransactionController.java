package com.app.transaction.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.transaction.model.TransactionRequest;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.service.intf.ICreateTransactionService;
import com.app.transaction.service.intf.ISearchTransactionService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/transaction")
@Api(value = "/transaction", tags = "Transaction Resource")
public class TransactionController {
	
	@Autowired
	ICreateTransactionService createTransactionService;
	
	@Autowired
	ISearchTransactionService searchTransactionService;

	@GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionResponse retrieveTransactionDtl(@PathVariable("accountId") Long accountId) {
		return searchTransactionService.getTxnDetailByAccountId(accountId);
	}
	
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public TransactionResponse creditPayment(@RequestBody TransactionRequest transactionRequest) {
		TransactionResponse response = createTransactionService.createTransaction(transactionRequest);
		
		return response;
	}
	
}
