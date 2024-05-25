package com.microservices.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.BankDailyOperationsApplication.UserService.Entity.User;
import com.BankDailyOperationsApplication.UserService.repositories.UserRepo;

@RestController
@RequestMapping("/user")
public class UserServiceController {

	@Autowired
	private UserRepo userRepo;

	
	@GetMapping("/branchId/{id}")
	public ResponseEntity<List<User>> findByBranchId(@PathVariable Integer id){
		List<User> userList = userRepo.findByBranchId(id);
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
	
	@PostMapping(path="/add")
	public ResponseEntity<User> saveUserData(@RequestBody User user) {
		User userData = userRepo.save(user);
		return new ResponseEntity<>(userData,HttpStatus.OK);
	}
	
	
	
}
