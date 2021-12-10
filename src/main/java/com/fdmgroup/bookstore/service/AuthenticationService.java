package com.fdmgroup.bookstore.service;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.exception.UserNotFoundException;
import com.fdmgroup.bookstore.model.User;

public class AuthenticationService {
	private UserRepository userRepository;
	
	public AuthenticationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User authenticate(String username, String password) throws UserNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user == null)
			throw new UserNotFoundException();
		
		if(user.getPassword() != password)
			throw new UserNotFoundException();
		
		return user;
	}
	
}
