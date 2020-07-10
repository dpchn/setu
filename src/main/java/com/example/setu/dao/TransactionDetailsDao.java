package com.example.setu.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.setu.models.TransactionDetails;

@Transactional
public interface TransactionDetailsDao extends CrudRepository<TransactionDetails, Long> {

	public List<TransactionDetails> findByTransactionId(String transactionId);
}
