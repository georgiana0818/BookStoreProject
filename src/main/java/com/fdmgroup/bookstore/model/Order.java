package com.fdmgroup.bookstore.model;

import java.time.LocalDateTime;

public class Order {
	private int orderId;
	private Book bookOrdered;
	private User user;
	private LocalDateTime orderDatetime;
	
	public Order(int orderId, Book bookOrdered, User user, LocalDateTime orderDatetime) {
		this.orderId = orderId;
		this.bookOrdered = bookOrdered;
		this.user = user;
		this.orderDatetime = orderDatetime;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Book getBookOrdered() {
		return bookOrdered;
	}

	public void setBookOrdered(Book bookOrdered) {
		this.bookOrdered = bookOrdered;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(LocalDateTime orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookOrdered == null) ? 0 : bookOrdered.hashCode());
		result = prime * result + ((orderDatetime == null) ? 0 : orderDatetime.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (bookOrdered == null) {
			if (other.bookOrdered != null)
				return false;
		} else if (!bookOrdered.equals(other.bookOrdered))
			return false;
		if (orderDatetime == null) {
			if (other.orderDatetime != null)
				return false;
		} else if (!orderDatetime.equals(other.orderDatetime))
			return false;
		if (orderId != other.orderId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", bookOrdered=" + bookOrdered + ", user=" + user + ", orderDatetime="
				+ orderDatetime + "]";
	}
	
	
	
}
