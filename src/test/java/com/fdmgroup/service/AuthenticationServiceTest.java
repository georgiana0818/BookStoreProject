package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.exception.UserNotFoundException;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;

public class AuthenticationServiceTest<T> {
	AuthenticationService authenticationService;
	UserRepository<User> userRepositoryDAO;
	
	@BeforeEach
	public void init() {
		userRepositoryDAO = mock(UserRepository.class);
		authenticationService = new AuthenticationService(userRepositoryDAO);
	}
	
	@Test
	public void test_authenticateMethodCallsFindByNameFromUserRepository() throws UserNotFoundException {
		//arrange
		User user = mock(User.class);
		when(userRepositoryDAO.findByUsername(user.getUsername())).thenReturn(user);
		
		//act
		authenticationService.authenticate(user.getUsername(), user.getPassword());
		
		//assert
		verify(userRepositoryDAO, times(1)).findByUsername(user.getUsername());
	}
	
	@Test 
	public void test_authenticateThrowUserNotFoundExceptionPassNotExistUser_WhenCall() {

		assertThrows(UserNotFoundException.class,
					()-> authenticationService.authenticate("John.Doe","654321"));		
	}
	
	@Test
	public void test_authenticateReturnsUserPassExistUsernameAndCorrectPassword_WhenCall() throws UserNotFoundException {
		User user = new User(1, "John", "Doe", "John.Doe", "123456","Joe.Doe@gmail.com", new ArrayList<Order>());
	
			when(userRepositoryDAO.findByUsername("John.Doe")).thenReturn(user);
			
			User actualUser = authenticationService.authenticate("John.Doe", "123456");
			
			assertEquals(user,actualUser);
			
		
	}
	
	@Test 
	public void test_authenticateThrowUserNotFoundExceptionPassExistUsernameAndIncorrectPassword_WhenCall() {
		User user = new User(1, "John", "Doe", "John.Doe", "123456","Joe.Doe@gmail.com", new ArrayList<Order>());
		
		when(userRepositoryDAO.findByUsername("John.Doe")).thenReturn(user);
			
		assertThrows(UserNotFoundException.class,
					()-> authenticationService.authenticate("John.Doe","654321"));
					
			
		
	}
	
	
}
