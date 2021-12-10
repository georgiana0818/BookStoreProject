package com.fdmgroup.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.exception.OrderNotFoundException;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.OrderingService;

public class OrderServiceTest {
	OrderingService orderService;
	OrderRepository<Order> orderRepositoryDAO;
	
	@BeforeEach
	public void init() {
		orderRepositoryDAO = mock(OrderRepository.class);
		orderService = new OrderingService(orderRepositoryDAO);
	}
	
	@Test
	public void test_placeOrderPassCreatedOrderToSaveMethodOfOrderRepository_WhenCall() {
		Book book = mock(Book.class);
		User customer = mock(User.class);
		
		orderService.placeOrder(book, customer);
		verify(orderRepositoryDAO, times(1)).save(any(Order.class));
	}
	
	@Test
	public void test_getOrdersForUserReturnsListOfOrderMatchingToProvidedUser_WhenCall() throws OrderNotFoundException {
		Book book = mock(Book.class);
		User customer = new User(1, "John", "Doe", "John.Doe", "123456","Joe.Doe@gmail.com", new ArrayList<Order>());
		
		List<Order> expectedOrders = new ArrayList<>();
		
		expectedOrders.add(new Order(1,book,customer,LocalDateTime.now()));
		when(orderRepositoryDAO.findAll()).thenReturn(expectedOrders);
		
		List<Order> actualOrders = orderService.getOrdersForUser(customer);
		assertEquals(expectedOrders, actualOrders);
	}
	
	@Test
	public void test_getOrdersForUserThrownOrderNotFoundExceptionWithNoMatchingOrderForPassedUser_WhenCall() {
		Book book = mock(Book.class);
		User customer = new User(1, "John", "Doe", "John.Doe", "123456","Joe.Doe@gmail.com", new ArrayList<Order>());
		
		List<Order> expectedOrders = new ArrayList<>();
		
		expectedOrders.add(new Order(1,book,customer,LocalDateTime.now()));
		when(orderRepositoryDAO.findAll()).thenReturn(expectedOrders);
		
		assertThrows(OrderNotFoundException.class, ()-> orderService.getOrdersForUser(new User(2, "Mary", "Jane", "Mary.Jane", "654321","Mary.Jane@gmail.com", new ArrayList<Order>())));
	}
	
	@Test
	public void test_getOrdersForBookReturnsListOfOrderMatchingToProvidedBook_WhenCall() throws OrderNotFoundException {
		Book book = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		User customer = mock(User.class);
		
		List<Order> expectedOrders = new ArrayList<>();
		
		expectedOrders.add(new Order(1,book,customer,LocalDateTime.now()));
		when(orderRepositoryDAO.findAll()).thenReturn(expectedOrders);
		
		List<Order> actualOrders = orderService.getOrdersForBook(book);
		assertEquals(expectedOrders, actualOrders);
	}
	
	@Test
	public void test_getOrdersForBookThrownOrderNotFoundExceptionWithNoMatchingOrderForPassedBook_WhenCall() {
		Book book = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		User customer = mock(User.class);
		
		List<Order> expectedOrders = new ArrayList<>();
		
		expectedOrders.add(new Order(1,book,customer,LocalDateTime.now()));
		when(orderRepositoryDAO.findAll()).thenReturn(expectedOrders);
		
		assertThrows(OrderNotFoundException.class, ()-> orderService.getOrdersForBook(new Book(1,23.99,"The Notebook", "Nicholas Sparks", BookGenre.Romance)));
	}
}
