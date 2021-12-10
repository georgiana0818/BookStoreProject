package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.exception.ItemNotFoundException;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.service.BookService;

public class BookServiceTest {
	BookService bookService;
	BookRepository<Book> bookRepositoryDAO;
	
	@BeforeEach
	public void init() {
		bookRepositoryDAO = mock(BookRepository.class);
		bookService = new BookService(bookRepositoryDAO);
	}
	
	@Test
	public void test_getAllBooksMethodCallsFindAllMethodOfBookRepository_WhenCall() throws ItemNotFoundException {
		//arrange
		Book b = mock(Book.class);
		List<Book> books = new ArrayList<>();
		books.add(b);
		when(bookRepositoryDAO.findAll()).thenReturn(books);
		
		//act
		bookService.getAllBooks();
		
		//assert
		verify(bookRepositoryDAO, times(1)).findAll();
	}
	
	@Test
	public void test_getAllBooksReturnsBookListReceivesFromFindAllMethodOfBookRepository_WhenCall() throws ItemNotFoundException {
		Book b = mock(Book.class);
		List<Book> expectedBooks = new ArrayList<>();
		expectedBooks.add(b);
		when(bookRepositoryDAO.findAll()).thenReturn(expectedBooks);
		
		//act
		List<Book> actualBooks = bookService.getAllBooks();
		assertEquals(expectedBooks, actualBooks);
	}
	
	@Test
	public void test_getAllBooksThrowItemNotFoundExceptionIfNoBookIsFound_WhenCall() throws ItemNotFoundException {
		//act
		
		assertThrows(ItemNotFoundException.class, ()-> bookService.getAllBooks());
	}
	
	@Test
	public void test_getBooksOfGenreReturnsListOfBookRelatedToTheBookGenre_WhenCall() throws ItemNotFoundException {
		Book book1 = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		Book book2 = new Book(1,23.99,"The Notebook", "Nicholas Sparks", BookGenre.Romance);
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryDAO.findAll()).thenReturn(books);
		
		List<Book> expectedBooks = new ArrayList<>();;
		expectedBooks.add(book2);
		
		List<Book> actualBooks = bookService.getBooksOfGenre(BookGenre.Romance);
		assertEquals(expectedBooks,actualBooks);
		
	}
	
	@Test
	public void test_getBooksOfGenreThrownItemNotFoundExceptionIfNoBookRelatedToThePassedBookGenreIsFound_WhenCall() {
		Book book1 = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		Book book2 = new Book(1,23.99,"The Notebook", "Nicholas Sparks", BookGenre.Romance);
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryDAO.findAll()).thenReturn(books);
		
		assertThrows(ItemNotFoundException.class, ()-> bookService.getBooksOfGenre(BookGenre.Drama));
		
	}
	
	@Test
	public void test_searchBooksByTitleReturnsListOfBookMatchingPassedTitle_WhenCall() throws ItemNotFoundException {
		Book book1 = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		Book book2 = new Book(1,23.99,"The Notebook", "Nicholas Sparks", BookGenre.Romance);
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryDAO.findAll()).thenReturn(books);
		
		List<Book> expectedBooks = new ArrayList<>();;
		expectedBooks.add(book2);
		
		List<Book> actualBooks = bookService.searchBooksByTitle("The Notebook");
		assertEquals(expectedBooks,actualBooks);
		
	}
	
	@Test
	public void test_searchBooksByTitleThrownItemNotFoundExceptionIfNoBookMatchingToPassedTitle_WhenCall() {
		Book book1 = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		Book book2 = new Book(1,23.99,"The Notebook", "Nicholas Sparks", BookGenre.Romance);
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryDAO.findAll()).thenReturn(books);
		
		assertThrows(ItemNotFoundException.class, ()-> bookService.searchBooksByTitle("Spiderman"));
		
	}
	
	@Test
	public void test_searchBooksByAuthorNameReturnsListOfBookBelongToThePassedAutherName_WhenCall() throws ItemNotFoundException {
		Book book1 = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		Book book2 = new Book(1,23.99,"The Notebook", "Nicholas Sparks", BookGenre.Romance);
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryDAO.findAll()).thenReturn(books);
		
		List<Book> expectedBooks = new ArrayList<>();;
		expectedBooks.add(book2);
		
		List<Book> actualBooks = bookService.searchBooksByAuthorName("Nicholas Sparks");
		assertEquals(expectedBooks,actualBooks);
		
	}
	
	@Test
	public void test_searchBooksByAuthorNameThrownItemNotFoundExceptionIfNoBookMatchingToPassedAutherName_WhenCall() {
		Book book1 = new Book(1,25.99,"Introduction to Java", "John Doe", BookGenre.Classic);
		Book book2 = new Book(1,23.99,"The Notebook", "Nicholas Sparks", BookGenre.Romance);
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		when(bookRepositoryDAO.findAll()).thenReturn(books);
		
		assertThrows(ItemNotFoundException.class, ()-> bookService.searchBooksByAuthorName("Stephen King"));
		
	}
	
}
