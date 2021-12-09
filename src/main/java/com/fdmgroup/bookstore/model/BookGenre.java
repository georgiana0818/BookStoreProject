package com.fdmgroup.bookstore.model;

public enum BookGenre {
	Classic("Classic"),
	Drama("Drama"),
	Horror("Horror"),
	Romance("Romance");
	
	private final String label;

	private BookGenre(String label) {
		// TODO Auto-generated constructor stub
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
