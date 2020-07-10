package com.example.setu.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.setu.models.Payments;
import com.example.setu.models.User;

@Transactional
public interface PaymentsDao extends CrudRepository<Payments, Long> {

	public Payments findByUserAndIsPaid(User user, boolean isPaid);

	public Payments findByRefIdAndIsPaid(String refId, boolean isPaid);
}
