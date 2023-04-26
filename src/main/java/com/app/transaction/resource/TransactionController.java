package com.app.transaction.resource;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.transaction.model.ErrorMessage;
import com.app.transaction.model.TransactionRequest;
import com.app.transaction.model.TransactionResponse;
import com.app.transaction.service.intf.ICreateTransactionService;
import com.app.transaction.service.intf.ISearchTransactionService;
import com.app.transaction.util.TransactionConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * This class is used to invokes method to create Transaction and get
 * transaction detail.
 * 
 * @author Anitha Manoharan
 *
 */
@RestController
@RequestMapping("/transaction")
@Api(value = "/transaction", tags = "Transaction Resource")
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
	@Autowired
	ICreateTransactionService createTransactionService;

	@Autowired
	ISearchTransactionService searchTransactionService;

	/**
	 * Get transaction detail by account id.
	 * 
	 * @param accountId account Id
	 * @return TransactionResponse
	 */
	@ApiOperation(value = "Get Transaction details of Account")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponse.class))}),
            @ApiResponse(responseCode = "422", description = "Error Response",
            content = @Content)})
	@GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionResponse retrieveTransactionDtl(@PathVariable("accountId") Long accountId) {
		LOGGER.debug("Inside  retrieveTransactionDtl method {}", this.getClass());
		return searchTransactionService.getTxnDetailByAccountId(accountId);
	}

	/**
	 * Save transaction details given in the request
	 * 
	 * @param transactionRequest transaction request
	 * @return TransctionResponse
	 */
	@ApiOperation(value = "Save Transaction details of Account")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponse.class))}),
            @ApiResponse(responseCode = "422", description = "Error Response",
            content = @Content)})
	@PostMapping(consumes = "application/json", produces = "application/json")
	public TransactionResponse saveTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
		LOGGER.debug("Inside creditPayment method {}", this.getClass());
		TransactionResponse response = createTransactionService.createTransaction(transactionRequest);
		return response;
	}

	/**
	 * Handles Invalid request input.
	 * @param exception MethodArgumentNotValidException
	 * @return TransactionResponse
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public TransactionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		StringBuilder builder = new StringBuilder();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			builder.append(fieldName).append(TransactionConstants.BLANK_SPACE);
		});
		ErrorMessage errorMessage = ErrorMessage.builder()
				.message(TransactionConstants.INVALID_INPUT + builder.toString())
				.statusCode(TransactionConstants.STATUS_CODE_400).source(TransactionConstants.TRANSACTION_SERVICE)
				.build();
		TransactionResponse errorResponse = TransactionResponse.builder().message(TransactionConstants.FAILURE)
				.errorMessage(errorMessage).transaction(new ArrayList<>()).build();
		return errorResponse;
	}

}
