package com.fdmgroup.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.exception.ItemNotFoundException;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;

public class BookService {
	private BookRepository<Book> bookRepository;
	
	public BookService(BookRepository<Book> bookRepository) {
		// TODO Auto-generated constructor stub
		this.bookRepository = bookRepository;
	}

	public List<Book> getAllBooks() throws ItemNotFoundException {
		// TODO Auto-generated method stub
		List<Book> books = bookRepository.findAll();
		if(books.isEmpty()) {
			throw new ItemNotFoundException();
		
		}
		return books;
	}

	public List<Book> getBooksOfGenre(BookGenre romance) throws ItemNotFoundException {
		// TODO Auto-generated method stub
		List<Book> allBooks = bookRepository.findAll();
		List<Book> booksOfGenre = new ArrayList<>();
		
		for(Book book: allBooks) {
			if(book.getBookGenre() == romance) {
				booksOfGenre.add(book);
			}
		}
		
		if(booksOfGenre.isEmpty())
			throw new ItemNotFoundException();
		
		return booksOfGenre;
	}

	public List<Book> searchBooksByTitle(String title) throws ItemNotFoundException {
		// TODO Auto-generated method stub
		List<Book> allBooks = bookRepository.findAll();
		List<Book> booksOfTitle = new ArrayList<>();
		
		for(Book book: allBooks) {
			if(book.getTitle().contains(title)) {
				booksOfTitle.add(book);
			}
		}
		
		if(booksOfTitle.isEmpty())
			throw new ItemNotFoundException();
		
		return booksOfTitle;
	}

	public List<Book> searchBooksByAuthorName(String authorName) throws ItemNotFoundException {
		// TODO Auto-generated method stub
		List<Book> allBooks = bookRepository.findAll();
		List<Book> booksOfAuthor = new ArrayList<>();
		
		for(Book book: allBooks) {
			if(book.getAuthor().equals(authorName)) {
				booksOfAuthor.add(book);
			}
		}
		
		if(booksOfAuthor.isEmpty())
			throw new ItemNotFoundException();
		
		return booksOfAuthor;
	}

}
