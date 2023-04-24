package com.app.transaction.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description="Transction Request")
public class TransactionRequest {
	@NotNull
	@DecimalMin(value = "1")
	private Long accountId;
	@NotNull
	@DecimalMin(value = "1")
	private BigDecimal amount;	
	@NotNull
	@DecimalMin(value = "1")
	private BigDecimal balance;
	private String description;
	
}
