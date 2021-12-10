package com.fdmgroup.bookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.exception.ItemNotFoundException;
import com.fdmgroup.bookstore.exception.OrderNotFoundException;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class OrderingService {
	private OrderRepository<Order> orderRepository;
	
	public OrderingService(OrderRepository<Order> oderRepository) {
		this.orderRepository = oderRepository;
	}

	public void placeOrder(Book book, User customer) {
		// TODO Auto-generated method stub
		int orderId = book.hashCode();
		Order order = new Order(orderId, book, customer, LocalDateTime.now());
		
		orderRepository.save(order);
	}

	public List<Order> getOrdersForUser(User user) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		List<Order> allOrders = orderRepository.findAll();
		List<Order> orderofUser = new ArrayList<>();
		
		for(Order order: allOrders) {
			if(order.getUser().equals(user)) {
				orderofUser.add(order);
			}
		}
		
		if(orderofUser.isEmpty())
			throw new OrderNotFoundException();
		
		return orderofUser;
		
	}

	public List<Order> getOrdersForBook(Book book) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		List<Order> allOrders = orderRepository.findAll();
		List<Order> orderofBook = new ArrayList<>();
		
		for(Order order: allOrders) {
			if(order.getBookOrdered().equals(book)) {
				orderofBook.add(order);
			}
		}
		
		if(orderofBook.isEmpty())
			throw new OrderNotFoundException();
		
		return orderofBook;
	}

}
