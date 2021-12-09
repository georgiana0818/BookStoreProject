package com.fdmgroup.bookstore.model;

public class AudioBook extends Book{
	private int timeLengthSeconds;
	public AudioBook(int itemId, double price, String title, String author, BookGenre bookGenre, int timeLengthSeconds) {
		super(itemId, price, title, author, bookGenre);
		this.timeLengthSeconds = timeLengthSeconds;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + timeLengthSeconds;
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
		AudioBook other = (AudioBook) obj;
		if (timeLengthSeconds != other.timeLengthSeconds)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AudioBook [timeLengthSeconds=" + timeLengthSeconds + "]";
	}

	
	
}
