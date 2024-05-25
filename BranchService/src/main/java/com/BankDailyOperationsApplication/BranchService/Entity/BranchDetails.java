package com.BankDailyOperationsApplication.BranchService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BranchDetails {
	@Id
	private int branchId;
	private String branchName;
	private String branchStreet;
	private String branchCountry;
}
