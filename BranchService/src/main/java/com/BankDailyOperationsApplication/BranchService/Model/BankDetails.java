package com.BankDailyOperationsApplication.BranchService.Model;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {
	private BankDetails branchDetails;
	private List<User> userList;
}
