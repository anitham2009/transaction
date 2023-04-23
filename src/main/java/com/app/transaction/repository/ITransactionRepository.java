package com.app.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.transaction.entity.TransactionDetail;

@Repository
public interface ITransactionRepository  extends JpaRepository<TransactionDetail,Long> {

	@Query(value = "select * from transaction_detail where account_id = :accountId",nativeQuery=true)
	public List<TransactionDetail> findByAccountId(Long accountId);
}
