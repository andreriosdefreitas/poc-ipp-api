package com.rios.ipp.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rios.ipp.domain.User;
import com.rios.ipp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {

	private UserRepository userRepository;

	public UserController(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		log.info("Get User: {}", id);
		Optional<User> findById = userRepository.findById(id);
		return findById.map(user -> {
			return ResponseEntity.ok().body(user);
		}).orElse(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		log.info("Create User: {}", user);
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
}
