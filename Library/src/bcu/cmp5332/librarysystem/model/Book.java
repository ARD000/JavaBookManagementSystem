package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;

    private Loan loan;

    public Book(int id, String title, String author, String publicationYear, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
	
    public String getDetailsShort() {
        return "Book #" + id + " - " + title + " (" + publisher + ")";
    }

    public String getDetailsLong() {
        return "Book #" + id + ":\nTitle: " + title + "\nAuthor: " + author + "\nYear: " + publicationYear + "\nPublisher: " + publisher;
    }
    
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    public String getStatus() {
        // This will check and return an input based on if the book is on loan or not. 
        return isOnLoan() ? "On Loan" : "Available";
    }

    public LocalDate getDueDate() {
        // If a book is on loan this method will get the loan due date or if not it will return null. 
    	return isOnLoan() ? loan.getDueDate() : null;
    }
    
    public void setDueDate(LocalDate dueDate) throws LibraryException {
        // This method allows to set a due date for a book. If the book is on then you can set a due date, otherwise it will return a 
    	// LibraryException to prevent adding a due date on a book that is not loaned
    	if (isOnLoan()) {
    		loan.setDueDate(dueDate);
    	} else {
    		throw new LibraryException("Book is not on Loan");
    	}
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
}

