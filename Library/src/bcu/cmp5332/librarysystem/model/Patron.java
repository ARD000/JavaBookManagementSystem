package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patron {

	private int id;
	private String name;
	private String phone;
	private String email;
	private final List<Book> books = new ArrayList<>();

	// The constructor.
	public Patron(int id, String name, String phone, String email) {
		this.id=id;
		this.name=name;
		this.phone=phone;
		this.email=email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
		// This method will first check if the book is already on loan. If it is then it will throw a libraryexception. 
		if (book.isOnLoan()) {
			throw new LibraryException("This book is already borrowed.");
		}
		// if the book is not on loan then it will allow to set the loan for the book. It will assume current date as start date for the loan. 
		LocalDate startDate = LocalDate.now();
		Loan loan = new Loan(this, book, startDate, dueDate); 
		book.setLoan(loan);
		// Then finally this method will add the book to the patron's list of borrowed books
		books.add(book);
	}

	public void renewBook(Book book, LocalDate newDueDate) throws LibraryException {
		// This method will check if the patron has the book and if it's on loan
		if (!books.contains(book) || !book.isOnLoan()) {
			throw new LibraryException("This book is not borrowed by the patron.");
		}
		// This method will allow to update the due date of the loan
		book.setDueDate(newDueDate);
	}


	public void returnBook(Book book) throws LibraryException {
		// This method will first check if the patron has the book.
		if (!books.contains(book)) {
			throw new LibraryException("This book was not borrowed by the patron.");
		}
		// Once it is confirmed that the book was borrowed this method will return the book and remove it from the list of borrowed books. 
		book.returnToLibrary();
		books.remove(book);
	}

	public void addBook(Book book) {
		//This method will add a book to a patrons borrowed books storage. 
		if (!books.contains(book)) {
			books.add(book);
		}
	}  

}
