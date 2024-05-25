package com.BankDailyOperationsApplication.BranchService.BranchController;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.BankDailyOperationsApplication.BranchService.Entity.BranchDetails;
import com.BankDailyOperationsApplication.BranchService.Model.BankDetails;
import com.BankDailyOperationsApplication.BranchService.Model.User;
import com.BankDailyOperationsApplication.BranchService.Repositories.BranchDetailsRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping("/branch")
@RibbonClient(name="branchService")
public class BranchController {
	
	@Autowired
	BranchDetailsRepo repo;
	
	@Autowired
	RestTemplate restTemplate;
	
	/* without Rest Template
	 * @GetMapping("/branchId/{id}") public ResponseEntity<BranchDetails>
	 * getBranchData(@PathVariable int id){ BranchDetails branchDetails =
	 * repo.findByBranchId(id); return new
	 * ResponseEntity<>(branchDetails,HttpStatus.OK); }
	 */
	
	@GetMapping("/branchId/{id}")
	@HystrixCommand(fallbackMethod="getBranchDataFallBackMethod")
	public ResponseEntity<BankDetails> getBranchData(@PathVariable int id){	
		BankDetails bankDetails = new BankDetails();
		BranchDetails branchDetails = repo.findByBranchId(id);
		bankDetails.setBranchDetails(bankDetails);
		List<User> usersDataList = restTemplate.getForObject("http://user-service/user/branchId/"+id, List.class);
		//List<User> usersDataList = restTemplate.getForObject("http://localhost:8081/user/branchId/"+id, List.class);
		bankDetails.setUserList(usersDataList);
		return new ResponseEntity<BankDetails>(bankDetails,HttpStatus.OK);
      }
	
	private ResponseEntity<BankDetails> getBranchDataFallBackMethod(@PathVariable int id){	
		BankDetails bankDetails = new BankDetails();
		BranchDetails branchDetails = repo.findByBranchId(id);
		bankDetails.setBranchDetails(bankDetails);
		return new ResponseEntity<BankDetails>(bankDetails,HttpStatus.OK);
      }
	
	@PostMapping("/add")
	public ResponseEntity<BranchDetails> saveBranchDetails(@RequestBody BranchDetails branchDetails){
		BranchDetails branchDetailsData = repo.save(branchDetails);
		return new ResponseEntity<>(branchDetailsData,HttpStatus.OK);
	}
	
}
