package com.BankDailyOperationsApplication.BranchService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankDailyOperationsApplication.BranchService.Entity.BranchDetails;



public interface BranchDetailsRepo extends JpaRepository<BranchDetails,Integer>{
	public BranchDetails findByBranchId(Integer id);
}
