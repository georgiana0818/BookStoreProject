package com.fdmgroup.bookstore.model;

public class EBook extends Book {
	private double sizeMegaBytes;
	public double getSizeMegaBytes() {
		return sizeMegaBytes;
	}
	
	public void setSizeMegaBytes(double sizeMegaBytes) {
		this.sizeMegaBytes = sizeMegaBytes;
	}
	public EBook(int itemId, double price, String title, String author, BookGenre bookGenre, double sizeMegaBytes) {
		super(itemId, price, title, author, bookGenre);
		this.sizeMegaBytes = sizeMegaBytes;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(sizeMegaBytes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EBook other = (EBook) obj;
		if (Double.doubleToLongBits(sizeMegaBytes) != Double.doubleToLongBits(other.sizeMegaBytes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EBook [sizeMegaBytes=" + sizeMegaBytes + "]";
	}
	
	

	
}
