package com.BankDailyOperationsApplication.UserService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankDailyOperationsApplication.UserService.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public List<User> findByBranchId(Integer id);

}
