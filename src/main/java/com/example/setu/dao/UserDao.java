package com.example.setu.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.setu.models.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
	public User findByContactNumberAndIsDeleted(String contactNo, boolean isDeleted);
}
