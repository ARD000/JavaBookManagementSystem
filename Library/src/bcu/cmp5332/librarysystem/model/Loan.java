package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

public class Loan {

	private Patron patron;
	private Book book;
	private LocalDate startDate;
	private LocalDate dueDate;

	public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
		// Initialized the loan objects. 
		this.patron = patron;
		this.book = book;
		this.startDate = startDate;
		this.dueDate = dueDate;

	}

	// Here I added getters and setters for each object. 
	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getBorrowerDetails() {
	    if (patron != null) {
	        return patron.getName();
	    } else {
	        return "No borrower information available";
	    }
	}
}
