package com.example.springJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springJPA.model.User;
import com.example.springJPA.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/getUserById/{empId}")
	public ResponseEntity<User> getEmployeeById(@PathVariable("empId") int empId) {
		User user = userRepository.findById(empId).orElseThrow(() -> new IllegalArgumentException("Not Found"));
		if (user == null) {
			return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/User")
	public ResponseEntity<User> createEmployee(@RequestBody User user) {
		User actualUser = userRepository.save(user);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(actualUser, headers, HttpStatus.CREATED);
	}

	@PutMapping("/UpdateUser/{empId}")
	public ResponseEntity<User> updateEmployee(@PathVariable("empId") int empId, @RequestBody User CurrentUser) {
		User user = userRepository.save(CurrentUser);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/DeleteUser/{empId}")
	public ResponseEntity<User> deleteEmployee(@PathVariable("empId") int empId) {
		userRepository.deleteById(empId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Spring data JPA Query

	// Find By designation using JPQL query
	@GetMapping("/User/UserId/{designation}")
	public ResponseEntity<List<User>> UserId(@PathVariable("designation") String designation) {
		List<User> users = userRepository.UserDesignation(designation);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Find By FirstName using JPQL query
	@GetMapping("/User/FirstName/{firstName}")
	public ResponseEntity<?> FirstNameDetails(@PathVariable("firstName") String firstName) {
		List<User> users = userRepository.FirstNameDetails(firstName);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Find By FirstName using JPQL query with index parameters
	@GetMapping("/User/NameByIndex/{lastName}/{firstName}")
	public ResponseEntity<?> NameWithIndex(@PathVariable("lastName") String lastName,
			@PathVariable("firstName") String firstName) {
		List<User> users = userRepository.NameWithIndex(lastName, firstName);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Find By designation using JPQL query
	@GetMapping("/User/Designation/{designation}")
	public ResponseEntity<?> DesignationDetails(@PathVariable("designation") String designation) {
		List<User> users = userRepository.DesignationDetails(designation);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Find By LastName using Native query
	@GetMapping("/User/LastName/{lastName}")
	public ResponseEntity<?> LastNameDetails(@PathVariable("lastName") String lastName) {
		List<User> users = userRepository.LastNameDetails(lastName);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Find By LastName using Native query with index parameters
	@GetMapping("/User/FirstLast/{firstName}/{lastName}")
	public ResponseEntity<?> NamesWithIndex(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		List<User> users = userRepository.NamesWithIndex(firstName, lastName);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	//Sorting by Descending order
	@GetMapping("/User/desc/{field}")
	public ResponseEntity<List<User>> getSortedBy(@PathVariable String field){
		List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,field));
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	//Sorting by Ascending order
	@GetMapping("/User/asc/{field}")
	public ResponseEntity<List<User>> getSorted(@PathVariable String field){
		List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	

}
